package com.pastdev.liferay.scripting.service.impl;


import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_BINDING_LOGGER;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_BINDING_PROGRESS_MONITOR;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_OUTPUT_NAMES_STATUS;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_OUTPUT_NAMES_STATUS_MESSAGE;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_PROGRESS_MONITOR_BACKGROUND_TASK_ID;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_PROGRESS_MONITOR_MESSAGE;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_PROGRESS_MONITOR_PERCENT_COMPLETE;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageTranslator;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.backgroundtask.BaseBackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplayFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.scripting.ScriptingUtil;


@Component( immediate = true, service = BackgroundTaskExecutor.class, property = {
        "background.task.executor.class.name=com.pastdev.liferay.scripting.service.impl.SpawnedTask"
} )
public final class SpawnedTask extends BaseBackgroundTaskExecutor {
    private static final Logger logger = LoggerFactory.getLogger( SpawnedTask.class );

    public SpawnedTask() {
        setBackgroundTaskStatusMessageTranslator( new SpawnedTaskStatusMessageTranslator() );
        setIsolationLevel( BackgroundTaskConstants.ISOLATION_LEVEL_COMPANY );
    }

    @Override
    public BackgroundTaskResult execute( BackgroundTask backgroundTask ) throws Exception {
        SpawnedTaskContextMap taskContextMap = new SpawnedTaskContextMap( backgroundTask );

        Map<String, Object> input = taskContextMap.getInput();
        if ( !input.containsKey( KEY_BINDING_LOGGER ) ) {
            input.put( KEY_BINDING_LOGGER, logger );
        }
        input.put( KEY_BINDING_PROGRESS_MONITOR, new SpawnedTaskProgressMonitor() );

        Set<String> outputNames = taskContextMap.getOutputNames();
        if ( outputNames == null ) {
            outputNames = new HashSet<String>();
        }
        outputNames.add( KEY_OUTPUT_NAMES_STATUS );
        outputNames.add( KEY_OUTPUT_NAMES_STATUS_MESSAGE );

        Map<String, Object> output = ScriptingUtil.eval( null, input, outputNames,
                taskContextMap.getLanguage(), taskContextMap.getScript() );

        return new BackgroundTaskResult( (int) output.get( KEY_OUTPUT_NAMES_STATUS ),
                (String) output.get( KEY_OUTPUT_NAMES_STATUS_MESSAGE ) );
    }

    public static final class SpawnedTaskProgressMonitor extends BaseProgressMonitor {
        @Override
        public void update( String message, Double percentComplete,
                Map<String, Object> attributes ) {
            Message messageBusMessage = new Message();
            if ( attributes != null ) {
                messageBusMessage.setValues( attributes );
            }
            messageBusMessage.put( KEY_PROGRESS_MONITOR_BACKGROUND_TASK_ID,
                    BackgroundTaskThreadLocal.getBackgroundTaskId() );
            messageBusMessage.put( KEY_PROGRESS_MONITOR_MESSAGE, message );
            messageBusMessage.put( KEY_PROGRESS_MONITOR_PERCENT_COMPLETE, percentComplete );

            MessageBusUtil.sendMessage( DestinationNames.BACKGROUND_TASK_STATUS,
                    messageBusMessage );
        }
    }

    public static final class SpawnedTaskStatusMessageTranslator
            implements BackgroundTaskStatusMessageTranslator {
        @Override
        public void translate( BackgroundTaskStatus backgroundTaskStatus,
                Message message ) {
            for ( Map.Entry<String, Object> entry : message.getValues().entrySet() ) {
                backgroundTaskStatus.setAttribute( entry.getKey(),
                        (Serializable) entry.getValue() );
            }
        }
    }

    @Override
    public BackgroundTaskDisplay getBackgroundTaskDisplay( BackgroundTask backgroundTask ) {
        return BackgroundTaskDisplayFactoryUtil.getBackgroundTaskDisplay( backgroundTask );
    }

    @Override
    public BackgroundTaskExecutor clone() {
        return new SpawnedTask();
    }
}
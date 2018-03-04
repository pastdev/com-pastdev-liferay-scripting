package com.pastdev.liferay.scripting.service.impl;


import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_PROGRESS_MONITOR_MESSAGE;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_PROGRESS_MONITOR_PERCENT_COMPLETE;


import java.io.Serializable;
import java.util.Map;


import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.pastdev.liferay.scripting.service.SpawnedTaskStatus;


public class SpawnedTaskStatusImpl implements SpawnedTaskStatus {
    private long backgroundTaskId;
    private Map<String, Serializable> progressAttributes;
    private String progressMessage;
    private Double progressPercentComplete;
    private long status;
    private String statusLabel;
    private String statusMessage;
    private String stringRepresentation;

    public SpawnedTaskStatusImpl( long backgroundTaskId ) throws PortalException, SystemException {
        this.backgroundTaskId = backgroundTaskId;

        BackgroundTask backgroundTask = BackgroundTaskManagerUtil
                .getBackgroundTask( backgroundTaskId );
        status = backgroundTask.getStatus();
        statusLabel = backgroundTask.getStatusLabel();
        statusMessage = backgroundTask.getStatusMessage();

        if ( backgroundTask.isInProgress() ) {
            BackgroundTaskStatus backgroundTaskStatus = BackgroundTaskStatusRegistryUtil
                    .getBackgroundTaskStatus( backgroundTaskId );
            progressAttributes = backgroundTaskStatus.getAttributes();
            progressMessage = getProgressAttribute( KEY_PROGRESS_MONITOR_MESSAGE );
            progressPercentComplete = getProgressAttribute(
                    KEY_PROGRESS_MONITOR_PERCENT_COMPLETE );
        }
    }

    @SuppressWarnings( "unchecked" )
    public <T> T getProgressAttribute( String key ) {
        return (T) progressAttributes.get( key );
    }

    public Double getProgressPercentComplete() {
        return progressPercentComplete;
    }

    public String getProgressMessage() {
        return progressMessage;
    }

    public long getStatus() {
        return status;
    }

    public String getStatusLabel() {
        return statusLabel;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    @Override
    public String toString() {
        if ( stringRepresentation == null ) {
            StringBuilder builder = new StringBuilder( "{" )
                    .append( "backgroundTaskId:'" ).append( backgroundTaskId ).append( "'" )
                    .append( ",status:'" ).append( getStatusLabel() ).append( "'" )
                    .append( ",statusMessage:'" ).append( getStatusMessage() ).append( "'" );

            String progressMessage = getProgressMessage();
            if ( progressMessage != null ) {
                builder.append( ",progressMessage:'" )
                        .append( progressMessage ).append( "'" );
            }

            Double progressPercentComplete = getProgressPercentComplete();
            if ( progressPercentComplete != null ) {
                builder.append( ",progressPercentComplete:'" )
                        .append( progressPercentComplete ).append( "'" );
            }

            stringRepresentation = builder.append( "}" ).toString();
        }
        return stringRepresentation;
    }
}

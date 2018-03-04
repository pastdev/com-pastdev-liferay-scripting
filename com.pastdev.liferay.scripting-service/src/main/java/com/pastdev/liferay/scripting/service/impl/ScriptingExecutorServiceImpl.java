/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.pastdev.liferay.scripting.service.impl;


import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_BINDING_LOGGER;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_BINDING_META;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_BINDING_OUT;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_BINDING_PROGRESS_MONITOR;


import java.io.Serializable;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.liferay.petra.encryptor.EncryptorException;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.pastdev.liferay.scripting.service.SpawnedTaskStatus;
import com.pastdev.liferay.scripting.service.base.ScriptingExecutorServiceBaseImpl;


/**
 * The implementation of the scripting executor remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.pastdev.liferay.scripting.service.ScriptingExecutorService}
 * interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScriptingExecutorServiceBaseImpl
 * @see com.pastdev.liferay.scripting.service.ScriptingExecutorServiceUtil
 */
public class ScriptingExecutorServiceImpl
        extends ScriptingExecutorServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Always use {@link
     * com.pastdev.liferay.scripting.service.ScriptingExecutorServiceUtil} to
     * access the scripting executor remote service.
     */
    private static final Logger logger = LoggerFactory.getLogger(
            ScriptingExecutorServiceImpl.class );

    @Override
    public String eval( String language, String script )
            throws PortalException, SystemException {
        return eval( (Map<String, Object>)null, language, script );
    }

    @Override
    public String eval( Map<String, Object> input, String language, String script )
            throws PortalException, SystemException {
        StringWriter out = new StringWriter();
        if ( input == null ) {
            input = new HashMap<String, Object>();
        }
        if ( ! input.containsKey( KEY_BINDING_OUT ) ) {
            input.put( KEY_BINDING_OUT, out );
        }
        eval( input, null, language, script );
        return out.toString();
    }

    @Override
    public Map<String, Object> eval( List<String> outputNames, String language, String script )
            throws PortalException, SystemException {
        return eval( null, outputNames, language, script );
    }

    @Override
    public Map<String, Object> eval( Map<String, Object> input, List<String> outputNames,
            String language, String script )
            throws PortalException, SystemException {
        if ( !getPermissionChecker().isOmniadmin() ) {
            throw new PrincipalException( getUser().getEmailAddress()
                    + " is not an administrator" );
        }

        input = inputWithMeta( input );
        if ( !input.containsKey( KEY_BINDING_LOGGER ) ) {
            input.put( KEY_BINDING_LOGGER, logger );
        }
        input.put( KEY_BINDING_PROGRESS_MONITOR, new LoggerProgressMonitor() );

        logger.info( "executing {} script", language );
        if ( outputNames != null ) {
            logger.debug( "expecting {}", outputNames );
        }

        return ScriptingUtil.eval( null, input,
                (outputNames == null ? null : new HashSet<String>( outputNames )),
                language, script );
    }

    private Map<String, Object> inputWithMeta( Map<String, Object> input )
            throws PortalException {
        if ( input == null ) {
            input = new HashMap<String, Object>();
        }
        if ( !input.containsKey( KEY_BINDING_META ) ) {
            Map<String, Object> meta = new HashMap<>();
            meta.put( "user", getUser() );
            input.put( KEY_BINDING_META, meta );
        }
        return input;
    }

    @Override
    public BackgroundTask spawn( String name, String language, String script )
            throws PortalException, SystemException {
        return spawn( name, null, null, language, script );
    }

    @Override
    public BackgroundTask spawn( String name, List<String> outputNames,
            String language, String script )
            throws PortalException, SystemException {
        return spawn( name, null, outputNames, language, script );
    }

    @Override
    public BackgroundTask spawn( String name, Map<String, Object> input,
            String language, String script )
            throws PortalException, SystemException {
        return spawn( name, input, null, language, script );
    }

    @Override
    public BackgroundTask spawn( String name, Map<String, Object> input,
            List<String> outputNames, String language, String script )
            throws PortalException, SystemException {
        if ( !getPermissionChecker().isOmniadmin() ) {
            throw new PrincipalException( getUser().getEmailAddress()
                    + " is not an administrator" );
        }

        Map<String, Serializable> taskContextMap;
        try {
            taskContextMap = new SpawnedTaskContextMap( getUser().getCompanyId() )
                    .setInput( inputWithMeta( input ) )
                    .setOutputNames( outputNames )
                    .setScript( script )
                    .setLanguage( language )
                    .encoded();
        }
        catch ( EncryptorException e ) {
            logger.error( "Failed to encrypt possibly sensitive data: {}", e.getMessage() );
            logger.debug( "Failed to encrypt possibly sensitive data:", e );
            throw new PortalException( e );
        }

        return BackgroundTaskManagerUtil.addBackgroundTask(
                getUserId(), 0L, name, SpawnedTask.class.getName(),
                taskContextMap, new ServiceContext() );
    }

    @Override
    public SpawnedTaskStatus status( int backgroundTaskId )
            throws PortalException, SystemException {
        return new SpawnedTaskStatusImpl( backgroundTaskId );
    }
}
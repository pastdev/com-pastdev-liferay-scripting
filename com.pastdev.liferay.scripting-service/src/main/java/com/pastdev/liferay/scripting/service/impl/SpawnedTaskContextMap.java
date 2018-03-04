package com.pastdev.liferay.scripting.service.impl;


import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_TASK_CONTEXT_INPUT;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_TASK_CONTEXT_LANGUAGE;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_TASK_CONTEXT_OUTPUT_NAMES;
import static com.pastdev.liferay.scripting.service.impl.ScriptingExecutorConstants.KEY_TASK_CONTEXT_SCRIPT;


import java.io.Serializable;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import com.liferay.petra.encryptor.Encryptor;
import com.liferay.petra.encryptor.EncryptorException;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;


public class SpawnedTaskContextMap {
    private Key encryptionKey;
    private Map<String, Object> input;
    private Set<String> outputNames;
    private String language;
    private String script;

    public SpawnedTaskContextMap( long companyId ) throws PortalException, SystemException {
        encryptionKey = CompanyLocalServiceUtil.getCompany( companyId ).getKeyObj();
    }

    @SuppressWarnings( "unchecked" )
    public SpawnedTaskContextMap( BackgroundTask backgroundTask )
            throws PortalException, SystemException, EncryptorException {
        this( backgroundTask.getCompanyId() );

        Map<String, Serializable> map = backgroundTask.getTaskContextMap();
        input = decrypt( (String) map.get( KEY_TASK_CONTEXT_INPUT ) );
        outputNames = (Set<String>) map.get( KEY_TASK_CONTEXT_OUTPUT_NAMES );
        script = decrypt( (String) map.get( KEY_TASK_CONTEXT_SCRIPT ) );
        language = (String) map.get( KEY_TASK_CONTEXT_LANGUAGE );
    }

    @SuppressWarnings( "unchecked" )
    private <T> T decrypt( String value ) throws EncryptorException {
        if ( value == null ) {
            return null;
        }
        return (T) JSONFactoryUtil.looseDeserialize(
                Encryptor.decrypt( encryptionKey, value ) );
    }

    private <T> String encrypt( T value ) throws EncryptorException {
        if ( value == null ) {
            return null;
        }
        return Encryptor.encrypt( encryptionKey,
                JSONFactoryUtil.looseSerializeDeep( value ) );
    }

    public Map<String, Serializable> encoded() throws EncryptorException {
        Map<String, Serializable> encoded = new HashMap<String, Serializable>();
        encoded.put( KEY_TASK_CONTEXT_INPUT, encrypt( input ) );
        encoded.put( KEY_TASK_CONTEXT_OUTPUT_NAMES, (Serializable) outputNames );
        encoded.put( KEY_TASK_CONTEXT_SCRIPT, encrypt( script ) );
        encoded.put( KEY_TASK_CONTEXT_LANGUAGE, language );
        return encoded;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public String getLanguage() {
        return language;
    }

    public Set<String> getOutputNames() {
        return outputNames;
    }

    public String getScript() {
        return script;
    }

    public SpawnedTaskContextMap setInput( Map<String, Object> input ) {
        this.input = input;
        return this;
    }

    public SpawnedTaskContextMap setLanguage( String language ) {
        this.language = language;
        return this;
    }

    public SpawnedTaskContextMap setOutputNames( Collection<String> outputNames ) {
        this.outputNames = (outputNames == null || outputNames instanceof Set)
                ? (HashSet<String>) outputNames
                : new HashSet<String>( outputNames );
        return this;
    }

    public SpawnedTaskContextMap setScript( String script ) {
        this.script = script;
        return this;
    }
}

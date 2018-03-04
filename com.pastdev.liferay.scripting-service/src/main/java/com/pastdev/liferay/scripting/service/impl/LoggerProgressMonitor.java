package com.pastdev.liferay.scripting.service.impl;


import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerProgressMonitor extends BaseProgressMonitor {
    private static final Logger logger = LoggerFactory.getLogger( LoggerProgressMonitor.class );

    @Override
    public void update( String message, Double percentComplete, Map<String, Object> attributes ) {
        logger.info( "{}%: {}", percentComplete, message );
        logger.trace( "{}", attributes );
    }
}

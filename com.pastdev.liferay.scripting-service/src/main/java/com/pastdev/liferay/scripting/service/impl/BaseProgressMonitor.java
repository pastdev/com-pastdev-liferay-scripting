package com.pastdev.liferay.scripting.service.impl;

import java.util.Map;

abstract public class BaseProgressMonitor implements ProgressMonitor {
    @Override
    public void update( String message ) {
        update( message, null, null );
    }

    @Override
    public void update( String message, Map<String, Object> attributes ) {
        update( message, null, attributes );
    }

    @Override
    public void update( String message, int index, int count ) {
        update( message, index, count, null );
    }

    @Override
    public void update( String message, Double percentComplete ) {
        update( message, percentComplete, null );
    }

    @Override
    public void update( String message, int index, int count, Map<String, Object> attributes ) {
        update( message, (index*10000/count)/100.0, attributes );
    }
}

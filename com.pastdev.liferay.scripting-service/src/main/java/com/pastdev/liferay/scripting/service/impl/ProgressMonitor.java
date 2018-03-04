package com.pastdev.liferay.scripting.service.impl;


import java.util.Map;


public interface ProgressMonitor {
    public void update( String message );

    public void update( String message, Map<String, Object> attributes );

    public void update( String message, int index, int count );

    public void update( String message, Double percentComplete );

    public void update( String message, int index, int count,
            Map<String, Object> attributes );

    public void update( String message, Double percentComplete,
            Map<String, Object> attributes );
}

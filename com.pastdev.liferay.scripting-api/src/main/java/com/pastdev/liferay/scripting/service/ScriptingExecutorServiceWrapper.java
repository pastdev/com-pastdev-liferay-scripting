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

package com.pastdev.liferay.scripting.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScriptingExecutorService}.
 *
 * @author Brian Wing Shun Chan
 * @see ScriptingExecutorService
 * @generated
 */
@ProviderType
public class ScriptingExecutorServiceWrapper implements ScriptingExecutorService,
	ServiceWrapper<ScriptingExecutorService> {
	public ScriptingExecutorServiceWrapper(
		ScriptingExecutorService scriptingExecutorService) {
		_scriptingExecutorService = scriptingExecutorService;
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> eval(
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.eval(outputNames, language, script);
	}

	@Override
	public java.util.Map<java.lang.String, java.lang.Object> eval(
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.eval(input, outputNames, language,
			script);
	}

	@Override
	public java.lang.String eval(
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.eval(input, language, script);
	}

	@Override
	public java.lang.String eval(java.lang.String language,
		java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.eval(language, script);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _scriptingExecutorService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name, java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.spawn(name, outputNames, language,
			script);
	}

	@Override
	public com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name,
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.spawn(name, input, outputNames,
			language, script);
	}

	@Override
	public com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name,
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.spawn(name, input, language, script);
	}

	@Override
	public com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name, java.lang.String language,
		java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.spawn(name, language, script);
	}

	@Override
	public SpawnedTaskStatus status(int backgroundTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scriptingExecutorService.status(backgroundTaskId);
	}

	@Override
	public ScriptingExecutorService getWrappedService() {
		return _scriptingExecutorService;
	}

	@Override
	public void setWrappedService(
		ScriptingExecutorService scriptingExecutorService) {
		_scriptingExecutorService = scriptingExecutorService;
	}

	private ScriptingExecutorService _scriptingExecutorService;
}
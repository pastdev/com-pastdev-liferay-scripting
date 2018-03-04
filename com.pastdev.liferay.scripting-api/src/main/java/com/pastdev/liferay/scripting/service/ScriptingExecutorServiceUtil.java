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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for ScriptingExecutor. This utility wraps
 * {@link com.pastdev.liferay.scripting.service.impl.ScriptingExecutorServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ScriptingExecutorService
 * @see com.pastdev.liferay.scripting.service.base.ScriptingExecutorServiceBaseImpl
 * @see com.pastdev.liferay.scripting.service.impl.ScriptingExecutorServiceImpl
 * @generated
 */
@ProviderType
public class ScriptingExecutorServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.pastdev.liferay.scripting.service.impl.ScriptingExecutorServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static java.util.Map<java.lang.String, java.lang.Object> eval(
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().eval(outputNames, language, script);
	}

	public static java.util.Map<java.lang.String, java.lang.Object> eval(
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().eval(input, outputNames, language, script);
	}

	public static java.lang.String eval(
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().eval(input, language, script);
	}

	public static java.lang.String eval(java.lang.String language,
		java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().eval(language, script);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name, java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().spawn(name, outputNames, language, script);
	}

	public static com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name,
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().spawn(name, input, outputNames, language, script);
	}

	public static com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name,
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().spawn(name, input, language, script);
	}

	public static com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name, java.lang.String language,
		java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().spawn(name, language, script);
	}

	public static SpawnedTaskStatus status(int backgroundTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().status(backgroundTaskId);
	}

	public static ScriptingExecutorService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ScriptingExecutorService, ScriptingExecutorService> _serviceTracker =
		ServiceTrackerFactory.open(ScriptingExecutorService.class);
}
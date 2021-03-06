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

package com.pastdev.liferay.scripting.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.pastdev.liferay.scripting.service.ScriptingExecutorServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link ScriptingExecutorServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScriptingExecutorServiceHttp
 * @see ScriptingExecutorServiceUtil
 * @generated
 */
@ProviderType
public class ScriptingExecutorServiceSoap {
	public static java.lang.String eval(java.lang.String language,
		java.lang.String script) throws RemoteException {
		try {
			java.lang.String returnValue = ScriptingExecutorServiceUtil.eval(language,
					script);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name, java.lang.String language,
		java.lang.String script) throws RemoteException {
		try {
			com.liferay.portal.kernel.backgroundtask.BackgroundTask returnValue = ScriptingExecutorServiceUtil.spawn(name,
					language, script);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		java.lang.String name, java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.backgroundtask.BackgroundTask returnValue = ScriptingExecutorServiceUtil.spawn(name,
					outputNames, language, script);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.pastdev.liferay.scripting.service.SpawnedTaskStatus status(
		int backgroundTaskId) throws RemoteException {
		try {
			com.pastdev.liferay.scripting.service.SpawnedTaskStatus returnValue = ScriptingExecutorServiceUtil.status(backgroundTaskId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ScriptingExecutorServiceSoap.class);
}
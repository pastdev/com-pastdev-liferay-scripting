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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.pastdev.liferay.scripting.service.ScriptingExecutorServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link ScriptingExecutorServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScriptingExecutorServiceSoap
 * @see HttpPrincipal
 * @see ScriptingExecutorServiceUtil
 * @generated
 */
@ProviderType
public class ScriptingExecutorServiceHttp {
	public static java.lang.String eval(HttpPrincipal httpPrincipal,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(ScriptingExecutorServiceUtil.class,
					"eval", _evalParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					language, script);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.Map<java.lang.String, java.lang.Object> eval(
		HttpPrincipal httpPrincipal,
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(ScriptingExecutorServiceUtil.class,
					"eval", _evalParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, input,
					language, script);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.Map<java.lang.String, java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.Map<java.lang.String, java.lang.Object> eval(
		HttpPrincipal httpPrincipal,
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(ScriptingExecutorServiceUtil.class,
					"eval", _evalParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					outputNames, language, script);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.Map<java.lang.String, java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.Map<java.lang.String, java.lang.Object> eval(
		HttpPrincipal httpPrincipal,
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(ScriptingExecutorServiceUtil.class,
					"eval", _evalParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, input,
					outputNames, language, script);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.Map<java.lang.String, java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.Map<java.lang.String, java.lang.Object> eval(
		HttpPrincipal httpPrincipal,
		java.util.List<java.lang.String> allowedClasses,
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(ScriptingExecutorServiceUtil.class,
					"eval", _evalParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					allowedClasses, input, outputNames, language, script);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.Map<java.lang.String, java.lang.Object>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		HttpPrincipal httpPrincipal, java.lang.String name,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(ScriptingExecutorServiceUtil.class,
					"spawn", _spawnParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					language, script);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.backgroundtask.BackgroundTask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.backgroundtask.BackgroundTask spawn(
		HttpPrincipal httpPrincipal, java.lang.String name,
		java.util.Map<java.lang.String, java.lang.Object> input,
		java.util.List<java.lang.String> outputNames,
		java.lang.String language, java.lang.String script)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(ScriptingExecutorServiceUtil.class,
					"spawn", _spawnParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					input, outputNames, language, script);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.backgroundtask.BackgroundTask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.pastdev.liferay.scripting.service.SpawnedTaskStatus status(
		HttpPrincipal httpPrincipal, int backgroundTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(ScriptingExecutorServiceUtil.class,
					"status", _statusParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					backgroundTaskId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.pastdev.liferay.scripting.service.SpawnedTaskStatus)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ScriptingExecutorServiceHttp.class);
	private static final Class<?>[] _evalParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _evalParameterTypes1 = new Class[] {
			java.util.Map.class, java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _evalParameterTypes2 = new Class[] {
			java.util.List.class, java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _evalParameterTypes3 = new Class[] {
			java.util.Map.class, java.util.List.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _evalParameterTypes4 = new Class[] {
			java.util.List.class, java.util.Map.class, java.util.List.class,
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _spawnParameterTypes6 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
	private static final Class<?>[] _spawnParameterTypes7 = new Class[] {
			java.lang.String.class, java.util.Map.class, java.util.List.class,
			java.lang.String.class, java.lang.String.class
		};
	private static final Class<?>[] _statusParameterTypes8 = new Class[] {
			int.class
		};
}
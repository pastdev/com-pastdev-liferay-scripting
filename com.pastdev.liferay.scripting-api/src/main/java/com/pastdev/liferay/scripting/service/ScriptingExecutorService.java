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

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service interface for ScriptingExecutor. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ScriptingExecutorServiceUtil
 * @see com.pastdev.liferay.scripting.service.base.ScriptingExecutorServiceBaseImpl
 * @see com.pastdev.liferay.scripting.service.impl.ScriptingExecutorServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=pastdev_scripting", "json.web.service.context.path=ScriptingExecutor"}, service = ScriptingExecutorService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ScriptingExecutorService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScriptingExecutorServiceUtil} to access the scripting executor remote service. Add custom service methods to {@link com.pastdev.liferay.scripting.service.impl.ScriptingExecutorServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public Map<java.lang.String, java.lang.Object> eval(
		List<java.lang.String> outputNames, java.lang.String language,
		java.lang.String script) throws PortalException, SystemException;

	public Map<java.lang.String, java.lang.Object> eval(
		Map<java.lang.String, java.lang.Object> input,
		List<java.lang.String> outputNames, java.lang.String language,
		java.lang.String script) throws PortalException, SystemException;

	public java.lang.String eval(
		Map<java.lang.String, java.lang.Object> input,
		java.lang.String language, java.lang.String script)
		throws PortalException, SystemException;

	public java.lang.String eval(java.lang.String language,
		java.lang.String script) throws PortalException, SystemException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public BackgroundTask spawn(java.lang.String name,
		List<java.lang.String> outputNames, java.lang.String language,
		java.lang.String script) throws PortalException, SystemException;

	public BackgroundTask spawn(java.lang.String name,
		Map<java.lang.String, java.lang.Object> input,
		List<java.lang.String> outputNames, java.lang.String language,
		java.lang.String script) throws PortalException, SystemException;

	public BackgroundTask spawn(java.lang.String name,
		Map<java.lang.String, java.lang.Object> input,
		java.lang.String language, java.lang.String script)
		throws PortalException, SystemException;

	public BackgroundTask spawn(java.lang.String name,
		java.lang.String language, java.lang.String script)
		throws PortalException, SystemException;

	public SpawnedTaskStatus status(int backgroundTaskId)
		throws PortalException, SystemException;
}
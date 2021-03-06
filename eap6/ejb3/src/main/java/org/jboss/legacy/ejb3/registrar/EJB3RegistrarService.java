/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.legacy.ejb3.registrar;

import org.jboss.as.core.security.ServerSecurityManager;
import org.jboss.legacy.common.EJB3Logger;
import org.jboss.legacy.common.EJB3Messages;
import org.jboss.legacy.spi.common.SecurityActions;
import org.jboss.legacy.spi.connector.ConnectorProxy;
import org.jboss.legacy.spi.ejb3.registrar.EJB3RegistrarProxy;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;
import org.jboss.msc.value.InjectedValue;

/**
 * @author baranowb
 * 
 */
public class EJB3RegistrarService implements Service<EJB3RegistrarProxy> {

    public static final ServiceName SERVICE_NAME = ServiceName.JBOSS.append(EJB3RegistrarModel.LEGACY).append(
            EJB3RegistrarModel.SERVICE_NAME);
    private static final String AOP_FILE = "ejb3-interceptors-aop.xml";

    private EJB3RegistrarProxy value;

    private InjectedValue<ConnectorProxy> connector = new InjectedValue<ConnectorProxy>();
    private final InjectedValue<ServerSecurityManager> serverSecurityManagerInjectedValue = new InjectedValue<ServerSecurityManager>();

    public EJB3RegistrarService() {
        super();
        this.value = new EJB3RegistrarProxy();
    }

    @Override
    public EJB3RegistrarProxy getValue() throws IllegalStateException, IllegalArgumentException {
        return value;
    }

    @Override
    public void start(StartContext startContext) throws StartException {
        EJB3Logger.ROOT_LOGGER.startRegistrar();
        try {
            final ClassLoader currentClassLoader = SecurityActions.getContextClassLoader();
            try {
                this.value.setConnector(this.connector.getValue());
                this.value.setEjb3AOPInterceptorsURL(currentClassLoader.getResource(AOP_FILE));
                this.value.start();
            } finally {
                //TODO: remove this
                SecurityActions.setContextClassLoader(currentClassLoader);
            }
        } catch (Exception e) {
            throw EJB3Messages.MESSAGES.couldNotStartRegistrar(e);
        }

    }

    @Override
    public void stop(StopContext stopContext) {
        EJB3Logger.ROOT_LOGGER.stoppingRegistrar();
        try {
            this.value.stop();
        } catch (Exception e) {
            EJB3Logger.ROOT_LOGGER.couldNotStopRegistrar(e);
        }
    }

    public InjectedValue<ConnectorProxy> getInjectedValueConnector() {
        return this.connector;
    }

    public InjectedValue<ServerSecurityManager> getServerSecurityManagerInjectedValue() {
        return serverSecurityManagerInjectedValue;
    }

}

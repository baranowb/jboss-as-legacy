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

package org.jboss.legacy.ejb3;

import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.SimpleResourceDefinition;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.as.controller.registry.OperationEntry;
import org.jboss.legacy.ejb3.registrar.LegacyEJB3RegistrarResourceDefinition;
import org.jboss.legacy.ejb3.remoting.LegacyRemotingResourceDefinition;
import org.jboss.legacy.jnp.LegacyJNPServerResourceDefinition;

/**
 * @author baranowb
 *
 */
public class LegacyEJB3SubsystemRootResourceDefinition extends SimpleResourceDefinition{

    public static final LegacyEJB3SubsystemRootResourceDefinition INSTANCE = new LegacyEJB3SubsystemRootResourceDefinition();

    LegacyEJB3SubsystemRootResourceDefinition() {
        super(PathElement.pathElement(ModelDescriptionConstants.SUBSYSTEM, LegacyEJB3Extension.SUBSYSTEM_NAME),
                LegacyEJB3Extension.getResourceDescriptionResolver(LegacyEJB3Extension.SUBSYSTEM_NAME),
                LegacyEJB3SubsystemAdd.INSTANCE, LegacyEJB3SubsystemRemove.INSTANCE,
                OperationEntry.Flag.RESTART_ALL_SERVICES, OperationEntry.Flag.RESTART_ALL_SERVICES);
    }

    @Override
    public void registerChildren(ManagementResourceRegistration resourceRegistration) {
        super.registerChildren(resourceRegistration);
        //subsystem=legacy-ejb3/service=remoting
        resourceRegistration.registerSubModel(LegacyRemotingResourceDefinition.INSTANCE);
        //subsystem=legacy-ejb3/service=ejb3-registrar
        resourceRegistration.registerSubModel(LegacyEJB3RegistrarResourceDefinition.INSTANCE);
        //subsystem=legacy-ejb3/service=jnp-server
        resourceRegistration.registerSubModel(LegacyJNPServerResourceDefinition.INSTANCE);
    }

}

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

import java.util.HashMap;
import java.util.Map;

import org.jboss.legacy.ejb3.registrar.LegacyEJB3RegistrarModel;
import org.jboss.legacy.ejb3.remoting.LegacyRemotingModel;
import org.jboss.legacy.jnp.LegacyJNPServerModel;

/**
 * @author baranowb
 *
 */
public enum LegacyEJB3SybsystemXMLElement {
 // must be first
    UNKNOWN(null),

    REMOTING(LegacyRemotingModel.SERVICE_NAME),
    JNP_SERVER(LegacyJNPServerModel.SERVICE_NAME),
    EJB3_REGISTRAR(LegacyEJB3RegistrarModel.SERVICE_NAME);

    private final String name;

    LegacyEJB3SybsystemXMLElement(final String name) {
        this.name = name;
    }

    /**
     * Get the local name of this element.
     *
     * @return the local name
     */
    public String getLocalName() {
        return name;
    }

    private static final Map<String, LegacyEJB3SybsystemXMLElement> MAP;

    static {
        final Map<String, LegacyEJB3SybsystemXMLElement> map = new HashMap<String, LegacyEJB3SybsystemXMLElement>();
        for (LegacyEJB3SybsystemXMLElement element : values()) {
            final String name = element.getLocalName();
            if (name != null) map.put(name, element);
        }
        MAP = map;
    }

    public static LegacyEJB3SybsystemXMLElement forName(String localName) {
        final LegacyEJB3SybsystemXMLElement element = MAP.get(localName);
        return element == null ? UNKNOWN : element;
    }
}

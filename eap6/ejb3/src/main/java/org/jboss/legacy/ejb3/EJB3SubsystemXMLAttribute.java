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


/**
 * @author baranowb
 *
 */
public enum EJB3SubsystemXMLAttribute {
    UNKNOWN(null);

    private final String name;

    EJB3SubsystemXMLAttribute(final String name) {
        this.name = name;
    }

    /**
     * Get the local name of this attribute.
     * @return the local name
     */
    public String getLocalName() {
        return name;
    }

    private static final Map<String, EJB3SubsystemXMLAttribute> MAP;

    static {
        final Map<String, EJB3SubsystemXMLAttribute> map = new HashMap<String, EJB3SubsystemXMLAttribute>();
        for (EJB3SubsystemXMLAttribute element : values()) {
            final String name = element.getLocalName();
            if (name != null)
                map.put(name, element);
        }
        MAP = map;
    }

    public static EJB3SubsystemXMLAttribute forName(String localName) {
        final EJB3SubsystemXMLAttribute element = MAP.get(localName);
        return element == null ? UNKNOWN : element;
    }

    @Override
    public String toString() {
        return getLocalName();
    }
}

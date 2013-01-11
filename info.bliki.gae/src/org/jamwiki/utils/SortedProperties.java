/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, version 2.1, dated February 1999.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the latest version of the GNU Lesser General
 * Public License as published by the Free Software Foundation;
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program (LICENSE.txt); if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.jamwiki.utils;

import info.bliki.gae.db.PropertyService;
import info.bliki.gae.model.PropertyEntity;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.google.appengine.api.datastore.QueryResultIterable;

/**
 * This class acts as a utility class for providing the capability of a property
 * file that is sorted alphabetically by key value. It is useful for things like
 * translation files where having the file in a logical order is useful for
 * maintainers.
 */
public class SortedProperties extends Properties {

  /**
   * 
   */
  private static final long serialVersionUID = 4517413327492115930L;

  /** Logger */
  public static final WikiLogger logger = WikiLogger
      .getLogger(SortedProperties.class.getName());

  /**
   * Standard constructor for creating a sorted properties file.
   */
  public SortedProperties() {
    super();
  }

  /**
   * Copy constructor used to create a sorted properties file.
   */
  public SortedProperties(Properties properties) {
    super();

    this.putAll(properties);
  }

  public void loadFromDatastore() {
    // load additional properties from datastore
    QueryResultIterable<PropertyEntity> list = PropertyService.getAll();
    for (PropertyEntity propertyEntity : list) {
      super.setProperty(propertyEntity.getKey(), propertyEntity.getValue());
    }
  }

  /**
   * Override the Properties.keys() method so that the keyset returned is
   * sorted.
   */
  @Override
  public Enumeration<java.lang.Object> keys() {
    Enumeration keyEnum = super.keys();
    Vector keys = new Vector();
    while (keyEnum.hasMoreElements()) {
      keys.add(keyEnum.nextElement());
    }
    Collections.sort(keys);
    return keys.elements();
  }

  @Override
  public synchronized Object setProperty(String key, String value) {
    PropertyEntity pe = new PropertyEntity(key, value);
    PropertyService.save(pe);
    return super.put(key, value);
  }
}

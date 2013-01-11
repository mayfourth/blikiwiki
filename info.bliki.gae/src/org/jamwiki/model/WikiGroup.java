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
package org.jamwiki.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.jamwiki.utils.WikiLogger;

/**
 * Provides an object representing a Wiki group.
 */
@Entity
public class WikiGroup implements Serializable {

  private static final WikiLogger logger = WikiLogger.getLogger(WikiGroup.class
      .getName());

  @Id
  private Long groupId;
  private String name = null;
  private String description = null;

  public static final String GROUP_ANONYMOUS = "GROUP_ANONYMOUS";
  public static final String GROUP_REGISTERED_USER = "GROUP_REGISTERED_USER";

  /**
	 *
	 */
  public WikiGroup() {
  }

  /**
	 *
	 */
  public String getDescription() {
    return this.description;
  }

  /**
	 *
	 */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
	 *
	 */
  public Long getGroupId() {
    return this.groupId;
  }

  /**
	 *
	 */
  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  /**
   * Retrieve the group name. Group names are returned in uppercase.
   */
  public String getName() {
    return (this.name == null) ? null : this.name.toUpperCase();
  }

  /**
   * Set the group name. Group names will be forced to uppercase.
   */
  public void setName(String name) {
    this.name = ((name == null) ? null : name.toUpperCase());
  }
}
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
 * Provides an object representing a Wiki category.
 */
@Entity
public class Category implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -3069986232853921729L;

  private static final WikiLogger logger = WikiLogger.getLogger(Category.class
      .getName());

  @Id
  private Long categoryId = null;
  private String name = null;
	private String childTopicName = null;

	private String sortKey = null;
  private int topicType = -1;
  private String virtualWiki = null;
  /**
	 *
	 */
  public Category() {
  }
  /**
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

  /**
	 *
	 */
  public String getChildTopicName() {
    return this.childTopicName;
  }

  /**
	 *
	 */
  public String getName() {
    return this.name;
  }

  /**
	 *
	 */
  public String getSortKey() {
    return this.sortKey;
  }

  /**
	 *
	 */
  public int getTopicType() {
    return this.topicType;
  }

  /**
	 *
	 */
  public String getVirtualWiki() {
    return this.virtualWiki;
  }

  /**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

  /**
	 *
	 */
  public void setChildTopicName(String childTopicName) {
    this.childTopicName = childTopicName;
  }

  /**
	 *
	 */
  public void setName(String name) {
    this.name = name;
  }

  /**
	 *
	 */
  public void setSortKey(String sortKey) {
    this.sortKey = sortKey;
  }

  /**
	 *
	 */
  public void setTopicType(int topicType) {
    this.topicType = topicType;
  }

  /**
	 *
	 */
  public void setVirtualWiki(String virtualWiki) {
    this.virtualWiki = virtualWiki;
  }
}
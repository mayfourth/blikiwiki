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
 * Provides an object representing a virtual wiki.
 */
@Entity
public class VirtualWiki implements Serializable {

	private static final WikiLogger logger = WikiLogger.getLogger(VirtualWiki.class.getName());
	@Id
	private Long virtualWikiId;
	private String name = "";
	private String defaultTopicName = "";

	/**
	 *
	 */
	public VirtualWiki() {
	}
	public VirtualWiki(String name, String defaultTopicName) {
	  this.name=name;
	  this.defaultTopicName = defaultTopicName;
  }
	/**
	 *
	 */
	public String getDefaultTopicName() {
		return this.defaultTopicName;
	}

	/**
	 *
	 */
	public void setDefaultTopicName(String defaultTopicName) {
		this.defaultTopicName = defaultTopicName;
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
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 */
	public Long getVirtualWikiId() {
		return this.virtualWikiId;
	}

	/**
	 *
	 */
	public void setVirtualWikiId(Long virtualWikiId) {
		this.virtualWikiId = virtualWikiId;
	}
}
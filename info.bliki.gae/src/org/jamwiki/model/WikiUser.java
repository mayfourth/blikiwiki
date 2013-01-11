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
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;
import org.jamwiki.Environment;
import org.jamwiki.utils.WikiLogger;

import com.google.appengine.api.users.User;

/**
 * Provides an object representing Wiki-specific information about a user of the
 * Wiki.
 */
@Entity
public class WikiUser implements Serializable {
  public static WikiUser ANONYOUS_WIKIUSER = new WikiUser("anonymous");
  /**
   * 
   */
  private static final long serialVersionUID = -6932030556150670383L;

  private static final WikiLogger logger = WikiLogger.getLogger(WikiUser.class
      .getName());

  @Id
  private Long userId=null;
  private String username = null;
  private String email = null;
  private Date createDate = new Date(System.currentTimeMillis());
  private String createIpAddress = "0.0.0.0";
  private String defaultLocale = null;
  private String displayName = null;

  /** The user's preferred editor (if any). */
  private String editor = Environment.getValue(Environment.PROP_TOPIC_EDITOR);

  private Date lastLoginDate = new Date(System.currentTimeMillis());
  private String lastLoginIpAddress = "0.0.0.0";
  /** The user's custom signature (if any). */
  private String signature = null;

//  private User gaeUser;
//
//  private boolean topicEditor = false;
//  private boolean systemAdmin = false;

  /**
	 *
	 */
  public WikiUser() {

  }

  /**
	 *
	 */
  public WikiUser(String username) {
    this.username = username;
  }

  /**
	 *
	 */
  public Date getCreateDate() {
    return this.createDate;
  }

  /**
	 *
	 */
  public String getCreateIpAddress() {
    return this.createIpAddress;
  }

  /**
	 *
	 */
  public String getDefaultLocale() {
    return this.defaultLocale;
  }

  /**
	 *
	 */
  public String getDisplayName() {
    return this.displayName;
  }

  /**
	 *
	 */
  public String getEditor() {
    return (StringUtils.isBlank(this.editor)) ? Environment
        .getValue(Environment.PROP_TOPIC_EDITOR) : this.editor;
  }

  /**
	 *
	 */
  public String getEmail() {
    return this.email;
  }

  /**
   * @return the google appengine user
   */
//  public User getGAEUser() {
//    return gaeUser;
//  }

  /**
	 *
	 */
  public Date getLastLoginDate() {
    return this.lastLoginDate;
  }

  /**
	 *
	 */
  public String getLastLoginIpAddress() {
    return this.lastLoginIpAddress;
  }

  /**
	 *
	 */
  public String getSignature() {
    return this.signature;
  }

  /**
	 *
	 */
  public Long getUserId() {
    return this.userId;
  }

  /**
	 *
	 */
  public String getUsername() {
    return username;
  }

  /**
   * Is this user allowed to adminitrate th system?
   * 
   * @return the systemAdmin
   */
//  public boolean isSystemAdmin() {
//    return systemAdmin;
//  }

  /**
   * Is this user allowed to edit topics?
   * 
   * @return the topicEditor
   */
//  public boolean isTopicEditor() {
//    return topicEditor;
//  }

  /**
	 *
	 */
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  /**
	 *
	 */
  public void setCreateIpAddress(String createIpAddress) {
    this.createIpAddress = createIpAddress;
  }

  /**
	 *
	 */
  public void setDefaultLocale(String defaultLocale) {
    this.defaultLocale = defaultLocale;
  }

  /**
	 *
	 */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  /**
	 *
	 */
  public void setEditor(String editor) {
    this.editor = editor;
  }

  /**
	 *
	 */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @param gaeUser
   *          the google appengine user
   */
//  public void setGAEUser(User gaeUser) {
//    this.gaeUser = gaeUser;
//  }

  /**
	 *
	 */
  public void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  /**
	 *
	 */
  public void setLastLoginIpAddress(String lastLoginIpAddress) {
    this.lastLoginIpAddress = lastLoginIpAddress;
  }

  /**
	 *
	 */
  public void setSignature(String signature) {
    this.signature = signature;
  }

  /**
   * @param systemAdmin
   *          the systemAdmin to set
   */
//  public void setSystemAdmin(boolean systemAdmin) {
//    this.systemAdmin = systemAdmin;
//  }

  /**
   * @param topicEditor
   *          the topicEditor to set
   */
//  public void setTopicEditor(boolean topicEditor) {
//    this.topicEditor = topicEditor;
//  }

  /**
	 *
	 */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}

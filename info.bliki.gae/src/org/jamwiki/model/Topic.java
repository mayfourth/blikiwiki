package org.jamwiki.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.jamwiki.WikiBase;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

@Entity
public class Topic implements Serializable {
  /**
   *  
   */
  private static final long serialVersionUID = 5518810488698138847L;
 
  /* Standard topic type. */
  public static final int TYPE_ARTICLE = 1;
  /* Topic redirects to another topic. */
  public static final int TYPE_REDIRECT = 2;
  /* Topic is an image. */
  public static final int TYPE_IMAGE = 4;
  /* Topic is a category. */
  public static final int TYPE_CATEGORY = 5;
  /* Topic is a non-image file. */
  public static final int TYPE_FILE = 6;
  /* Internal files, do not display on Special:Allpages */
  public static final int TYPE_SYSTEM_FILE = 7;
  /* Wiki templates. */
  public static final int TYPE_TEMPLATE = 8;

  @Id
  private String title;

  private Key<WikiUser> author; 

  private Text content = null;

  private String redirect;

  private boolean adminOnly = false;

  private Date date;

  private Date deleteDate = null;

  private int topicType = TYPE_ARTICLE;

  private String virtualWiki = "";

  private boolean readOnly = false;

  private Long currentVersionId;

  @Transient
  private String htmlContent;

  public Topic() {
  }

  public Topic(String title) {
    this.title = title;
    this.content = new Text("");
    htmlContent = "";
    this.date = new Date();
  }

  public Topic(String title, String content, WikiUser gaeUser) {
    this.title = title;
    // renderHtml(content);
    if (content == null) {
      this.content = new Text("");
    } else {
      this.content = new Text(content);
    }
    // setAuthor(gaeUser);
    this.date = new Date();
  }

  public WikiUser getAuthor() {
    Objectify ofy = OS.begin();
    try {
      return ofy.get(author);
    } catch (EntityNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @return the content
   */
  public Text getContent() {
    return content;
  }

  public Long getCurrentVersionId() {
    return currentVersionId;
  }

  public Date getDate() {
    return date;
  }

  /**
  *
  */
  public boolean getDeleted() {
    return (this.deleteDate != null);
  }

  /**
   * @return the deleteDate
   */
  public Date getDeleteDate() {
    return deleteDate;
  }

  public String getHtmlContent() {
    return htmlContent;
  }

  /**
   * @return the htmlContent
   */
  // public String getHtmlContent() {
  // if (htmlContent == null) {
  // renderHtml(content.getValue());
  // }
  // return htmlContent;
  // }

  public String getName() {
    return title;
  }

  public String getRedirectTo() {
    return redirect;
  }

  public String getTopicContent() {
    if (content == null) {
      return "";
    }
    return content.getValue();
  }

  /**
   * @return the topicType
   */
  public int getTopicType() {
    return topicType;
  }

  /**
   * @return the virtualWiki
   */
  public String getVirtualWiki() {
    if (virtualWiki == null || virtualWiki.length() == 0) {
      return WikiBase.DEFAULT_VWIKI;
    }
    return virtualWiki;
  }

  /**
   * @return the adminOnly
   */
  public boolean isAdminOnly() {
    return adminOnly;
  }

  /**
   * @return the readOnly
   */
  public boolean isReadOnly() {
    return readOnly;
  }

  /**
   * @param adminOnly
   *          the adminOnly to set
   */
  public void setAdminOnly(boolean adminOnly) {
    this.adminOnly = adminOnly;
  }

  public void setAuthor(WikiUser author) {
    Objectify ofy = OS.begin();
    this.author = new Key<WikiUser>(WikiUser.class, author.getUserId());
  }

  public void setCurrentVersionId(Long currentVersionId) {
    this.currentVersionId = currentVersionId;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @param deleteDate
   *          the deleteDate to set
   */
  public void setDeleteDate(Date deleteDate) {
    this.deleteDate = deleteDate;
  }

  // private void renderHtml(String content) {
  // IWikiModel model = BlikiModel.get();
  // this.htmlContent = model.render(content);
  // }

  /**
   * @param htmlContent
   *          the htmlContent to set
   */
  public void setHtmlContent(String htmlContent) {
    this.htmlContent = htmlContent;
  }

  public void setName(String title) {
    this.title = title;
  }

  /**
   * @param readOnly
   *          the readOnly to set
   */
  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }

  public void setTopicContent(String content) {
    // renderHtml(content);
    this.content = new Text(content);
  }

  /**
   * @param topicType
   *          the topicType to set
   */
  public void setTopicType(int topicType) {
    this.topicType = topicType;
  }

  /**
   * @param virtualWiki
   *          the virtualWiki to set
   */
  public void setVirtualWiki(String virtualWiki) {
    this.virtualWiki = virtualWiki;
  }

  @Override
  public String toString() {
    return this.title;
  }
}

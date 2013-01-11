package info.bliki.gae.db;

import java.util.Collections;
import java.util.LinkedHashMap;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import org.jamwiki.WikiException;
import org.jamwiki.WikiMessage;
import org.jamwiki.model.OS;
import org.jamwiki.model.Topic;
import org.jamwiki.model.WikiUser;
import org.jamwiki.parser.ParserException;
import org.jamwiki.parser.ParserInput;
import org.jamwiki.parser.ParserOutput;
import org.jamwiki.parser.ParserUtil;
import org.jamwiki.servlets.ServletUtil;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class PageService {
  public static Cache cache = null;

  static {
    try {
      CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
      cache = cacheFactory.createCache(Collections.emptyMap());
    } catch (CacheException e) {
      // ...
      // e.printStackTrace();
    }
  }

  public static Topic save(Topic page, LinkedHashMap<String, String> categories) {
    Objectify ofy = OS.begin();
    ofy.put(page);
    // if (catList != null && catList.size() > 0) {
    // ofy.put(catList);
    // }
    cache.put(page.getName(), page);
    return page;
  }

  public static Topic update(Topic page,
      LinkedHashMap<String, String> categories) {
    Topic existingEntity = null;
    try {
      Objectify ofy = OS.begin();
      existingEntity = ofy.get(Topic.class, page.getName());
      existingEntity.setName(page.getName());
      existingEntity.setTopicContent(page.getTopicContent());
      ofy.put(existingEntity);
      // if (catList != null && catList.size() > 0) {
      // ofy.put(catList);
      // }
      cache.put(existingEntity.getName(), existingEntity);

    } catch (EntityNotFoundException enf) {
    }
    return existingEntity;
  }

  public static void delete(Topic page) {
    cache.remove(page.getName());
    Objectify ofy = OS.begin();
    ofy.delete(page);
  }

  public static Topic findByTitle(String title) {
    Topic page = (Topic) cache.get(title);
    if (page != null) {
      return page;
    }
    try {
      Objectify ofy = OS.begin();
      page = ofy.get(new Key<Topic>(Topic.class, title));
      // Query<Topic> q = ofy.query(Topic.class);
      // q.filter("title", title);
      // page = ofy.prepare(q).asSingle();
      cache.put(page.getName(), page);
      return page;
    } catch (EntityNotFoundException enfe) {
    }
    return null;
  }

  public static String getHTMLContent(String title) {
    Topic topic = findByTitle(title);
    if (topic != null) {

      String virtualWiki = topic.getVirtualWiki();
      String topicName = topic.getName();
      // WikiUserDetails userDetails = ServletUtil.currentUserDetails();
      // if (sectionEdit && !ServletUtil.isEditable(virtualWiki, topicName,
      // userDetails)) {
      boolean sectionEdit = false;
      // }
      WikiUser user = ServletUtil.currentWikiUser();
      ParserInput parserInput = new ParserInput();
      // parserInput.setContext(request.getContextPath());
      // parserInput.setLocale(request.getLocale());
      parserInput.setWikiUser(user);
      parserInput.setTopicName(topicName);
      // parserInput.setUserIpAddress(ServletUtil.getIpAddress(request));
      parserInput.setVirtualWiki(virtualWiki);
      parserInput.setAllowSectionEdit(sectionEdit);
      ParserOutput parserOutput = new ParserOutput();
      String content = null;
      try {
        content = ParserUtil.parse(parserInput, parserOutput, topic
            .getTopicContent());
      } catch (ParserException e) {
        throw new WikiException(
            new WikiMessage("error.unknown", e.getMessage()), e);
      }
      return content;
      // return topic.getHtmlContent();
    }
    return "";
  }

  public static QueryResultIterable<Topic> getAll() {
    // List<Topic> resultList = null;
    Objectify ofy = OS.begin();
    // OQuery<Topic> q = OS.createQuery(Topic.class);
    Query<Topic> q = ofy.query(Topic.class);
    // resultList = ofy.prepare(q).asList();
    return q;
  }

  // Topic save(Topic page);
  //
  // Topic update(Topic page);
  //
  // void delete(Topic page);
  //
  // Topic findByTitle(String title);
  //
  // String getHTMLContent(String title);
  //
  // List<Topic> getAll();
}

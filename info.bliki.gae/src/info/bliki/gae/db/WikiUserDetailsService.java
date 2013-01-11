package info.bliki.gae.db;

import java.util.Collections;
import java.util.List;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import org.jamwiki.authentication.WikiUserDetails;
import org.jamwiki.model.OS;
import org.jamwiki.model.WikiGroup;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.Objectify;



public class WikiUserDetailsService {
  public static Cache WIKIUSER_CACHE = null;

  static {
    try {
      CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
      WIKIUSER_CACHE = cacheFactory.createCache(Collections.emptyMap());
    } catch (CacheException e) {
      // ...
      // e.printStackTrace();
    }
  }

  public static WikiUserDetails save(WikiUserDetails wikiUser) {
    Objectify ofy = OS.begin();
    ofy.put(wikiUser);
    WIKIUSER_CACHE.put(wikiUser.getUsername(), wikiUser);
    return wikiUser;
  }

  public static void delete(WikiUserDetails wikiUser) {
    WIKIUSER_CACHE.remove(wikiUser.getUsername());
    Objectify ofy = OS.begin();
    ofy.delete(wikiUser);
  }

  public static WikiUserDetails findByName(String username) {
    if (username==null) {
      return null;
    }
    Objectify ofy = OS.begin();
    return ofy.find(WikiUserDetails.class, username);
  }

  public static QueryResultIterable<WikiUserDetails> getAll() {
    Objectify ofy = OS.begin();
    Query<WikiUserDetails> q = ofy.query(WikiUserDetails.class);
    return q;
  }
}


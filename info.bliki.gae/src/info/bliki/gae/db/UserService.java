package info.bliki.gae.db;

import info.bliki.gae.model.UserAuthorityEntity;
import info.bliki.gae.model.UserEntity;

import java.util.Collections;
import java.util.List;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import org.jamwiki.model.OS;
import org.jamwiki.model.WikiUser;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.Objectify;

public class UserService {

  public static UserEntity save(UserEntity page) {
    Objectify ofy = OS.begin();
    ofy.put(page);
    return page;
  }

  public static UserEntity update(UserEntity role) {
    UserEntity existingEntity = null;
    try {
      Objectify ofy = OS.begin();
      existingEntity = ofy.get(UserEntity.class, role.getUsername());
      existingEntity.setPassword(role.getPassword());
      ofy.put(existingEntity);
    } catch (EntityNotFoundException enf) {
    }
    return existingEntity;
  }

  public static void delete(UserEntity role) {
    Objectify ofy = OS.begin();
    ofy.delete(role);
  }

  public static boolean isAuthenticated(String username,
      String encryptedPassword) {
    Objectify ofy = OS.begin();
    // OQuery<WikiUser> q = OS.createQuery(WikiUser.class);
    Query<WikiUser> q = ofy.query(WikiUser.class);

    q.filter("username", username);
    q.filter("password", encryptedPassword);
    return q.countAll() > 0;
    // return ofy.prepare(q).asSingle() != null;
  }

  public static UserEntity findByName(String name) {
    UserEntity role = null;
    try {
      Objectify ofy = OS.begin();
      // OQuery<UserEntity> q = OS.createQuery(UserEntity.class);
      Query<WikiUser> q = ofy.query(WikiUser.class);
      return ofy.get(UserEntity.class, name);
      // q.filter("username", name);
      // role = ofy.prepare(q).asSingle();
      // return role;
    } catch (EntityNotFoundException enfe) {
    }
    return null;
  }

  public static QueryResultIterable<UserEntity> getAll() {
    Objectify ofy = OS.begin();
    Query<UserEntity> q = ofy.query(UserEntity.class);
    return q;
  }
}

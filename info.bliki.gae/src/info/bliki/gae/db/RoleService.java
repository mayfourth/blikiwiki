package info.bliki.gae.db;

import info.bliki.gae.model.PropertyEntity;
import info.bliki.gae.model.RoleEntity;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

import org.jamwiki.model.OS;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class RoleService {
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

  public static RoleEntity save(RoleEntity page) {
    Objectify ofy = OS.begin();
    ofy.put(page);
    return page;
  }

  // public static RoleEntity update(RoleEntity role) {
  // RoleEntity existingEntity = null;
  // try {
  // Objectify ofy = OS.begin();
  // existingEntity = ofy.get(RoleEntity.class, role.getName());
  // existingEntity.setDescription(role.getDescription());
  // ofy.put(existingEntity);
  // cache.put(existingEntity.getName(), existingEntity);
  // } catch (EntityNotFoundException enf) {
  // }
  // return existingEntity;
  // }

  public static void delete(RoleEntity role) {
    cache.remove(role.getName());
    Objectify ofy = OS.begin();
    ofy.delete(role);
  }

  public static RoleEntity findByName(String name) {
    RoleEntity role = (RoleEntity) cache.get(name);
    if (role != null) {
      return role;
    }
    try {
      Objectify ofy = OS.begin();
      role = ofy.get(RoleEntity.class, name);
      // Query<PropertyEntity> q = ofy.query(PropertyEntity.class);
      // q.filter("name", name);
      // role = ofy.prepare(q).asSingle();
      cache.put(role.getName(), role);
      return role;
    } catch (EntityNotFoundException enfe) {
    }
    return null;
  }

  public static QueryResultIterable<RoleEntity> getAll() {
    Objectify ofy = OS.begin();
    Query<RoleEntity> q = ofy.query(RoleEntity.class);
    return q;
  }

}

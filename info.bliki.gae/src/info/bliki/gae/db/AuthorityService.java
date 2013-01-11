package info.bliki.gae.db;

import info.bliki.gae.model.AuthorityEntity;

import java.util.List;

import org.jamwiki.model.OS;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class AuthorityService {

  public static AuthorityEntity save(AuthorityEntity page) {
    Objectify ofy = OS.begin();
    ofy.put(page);
    return page;
  }

  public static AuthorityEntity update(AuthorityEntity role) {
    AuthorityEntity existingEntity = null;
    try {
      Objectify ofy = OS.begin();
      existingEntity = ofy.get(AuthorityEntity.class, role.getAuthorityId());
      existingEntity.setUsername(role.getUsername());
      existingEntity.setAuthority(role.getAuthority());
      ofy.put(existingEntity);
    } catch (EntityNotFoundException enf) {
    }
    return existingEntity;
  }

  public static void delete(AuthorityEntity role) {
    Objectify ofy = OS.begin();
    ofy.delete(role);
  }

  public static void deleteByName(String name) {
    AuthorityEntity role;
    try {
      Objectify ofy = OS.begin();
      Query<AuthorityEntity> q = ofy.query(AuthorityEntity.class);
      // OS.createQuery(AuthorityEntity.class);
      q.filter("username", name);
      // Iterable<AuthorityEntity> it = ofy.prepare(q).asIterable();
      for (AuthorityEntity authorityEntity : q) {
        ofy.delete(authorityEntity);
      }
    } catch (NullPointerException npe) {
    }
  }

  public static QueryResultIterable<AuthorityEntity> findByName(String name) {
    try {
      Objectify ofy = OS.begin();
      List<AuthorityEntity> resultList = null;
      // OQuery<AuthorityEntity> q = OS.createQuery(AuthorityEntity.class);
      Query<AuthorityEntity> q = ofy.query(AuthorityEntity.class);
      q.filter("username", name);
      // resultList = ofy.prepare(q).asList();
      return q;
    } catch (NullPointerException npe) {
    }
    return null;
  }

  public static QueryResultIterable<AuthorityEntity> getAll() {
    List<AuthorityEntity> resultList = null;
    Objectify ofy = OS.begin();
    // OQuery<AuthorityEntity> q = OS.createQuery(AuthorityEntity.class);
    Query<AuthorityEntity> q = ofy.query(AuthorityEntity.class);
    // resultList = ofy.prepare(q).asList();
    return q;
  }

}

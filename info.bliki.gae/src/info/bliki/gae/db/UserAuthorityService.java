package info.bliki.gae.db;

import info.bliki.gae.model.UserAuthorityEntity;

import org.jamwiki.model.OS;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class UserAuthorityService {

  public static UserAuthorityEntity save(UserAuthorityEntity page) {
    Objectify ofy = OS.begin();
    ofy.put(page);
    return page;
  }

  // public static UserAuthorityEntity update(UserAuthorityEntity role) {
  // UserAuthorityEntity existingEntity = null;
  // try {
  // Objectify ofy = OS.begin();
  // existingEntity = ofy.get(UserAuthorityEntity.class,
  // role.getUserAuthorityId());
  // existingEntity.setGroupId(role.getGroupId());
  // existingEntity.setAuthority(role.getAuthority());
  // ofy.put(existingEntity);
  // } catch (EntityNotFoundException enf) {
  // }
  // return existingEntity;
  // }

  public static void delete(UserAuthorityEntity role) {
    Objectify ofy = OS.begin();
    ofy.delete(role);
  }

  public static void deleteByUsername(String username) {
    UserAuthorityEntity role;
    try {
      Objectify ofy = OS.begin();
      // OQuery<UserAuthorityEntity> q =
      // OS.createQuery(UserAuthorityEntity.class);
      Query<UserAuthorityEntity> q = ofy.query(UserAuthorityEntity.class);
      q.filter("username", username);
      ofy.delete(q);
      // Iterable<UserAuthorityEntity> it = ofy.prepare(q).asIterable();
      // for (UserAuthorityEntity groupAuthorityEntity : it) {
      // ofy.delete(groupAuthorityEntity);
      // }
    } catch (NullPointerException npe) {
    }
  }

  public static QueryResultIterable<UserAuthorityEntity> getAll() {
    Objectify ofy = OS.begin();
    Query<UserAuthorityEntity> q = ofy.query(UserAuthorityEntity.class);
    return q;
  }
}

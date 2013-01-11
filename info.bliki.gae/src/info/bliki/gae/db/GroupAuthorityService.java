package info.bliki.gae.db;

import info.bliki.gae.model.GroupAuthorityEntity;

import org.jamwiki.model.OS;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class GroupAuthorityService {

  public static GroupAuthorityEntity save(GroupAuthorityEntity page) {
    Objectify ofy = OS.begin();
    ofy.put(page);
    return page;
  }

  public static GroupAuthorityEntity update(GroupAuthorityEntity role) {
    GroupAuthorityEntity existingEntity = null;
    try {
      Objectify ofy = OS.begin();
      existingEntity = ofy.get(GroupAuthorityEntity.class, role
          .getGroupAuthorityId());
      existingEntity.setGroupId(role.getGroupId());
      existingEntity.setAuthority(role.getAuthority());
      ofy.put(existingEntity);
    } catch (EntityNotFoundException enf) {
    }
    return existingEntity;
  }

  public static void delete(GroupAuthorityEntity role) {
    Objectify ofy = OS.begin();
    ofy.delete(role);
  }

  public static void deleteByGroupId(Long groupId) {
    // GroupAuthorityEntity role;
    try {
      Objectify ofy = OS.begin();
      // OQuery<GroupAuthorityEntity> q = OS
      // .createQuery(GroupAuthorityEntity.class);
      Query<GroupAuthorityEntity> q = ofy.query(GroupAuthorityEntity.class);
      q.filter("groupId", groupId);
      // Iterable<GroupAuthorityEntity> it = ofy.prepare(q).asIterable();
      ofy.delete(q);
      // for (GroupAuthorityEntity groupAuthorityEntity : q) {
      // ofy.delete(groupAuthorityEntity);
      // }
    } catch (NullPointerException npe) {
    }
  }

  public static QueryResultIterable<GroupAuthorityEntity> getByGroupname(
      String groupName) {
    try {
      Objectify ofy = OS.begin();
      // OQuery<GroupAuthorityEntity> q = OS
      // .createQuery(GroupAuthorityEntity.class);
      Query<GroupAuthorityEntity> q = ofy.query(GroupAuthorityEntity.class);
      q.filter("groupName", groupName);
      return q;// ofy.prepare(q).asList();
    } catch (NullPointerException npe) {
    }
    return null;
  }

  public static QueryResultIterable<GroupAuthorityEntity> getAll() {
    // List<GroupAuthorityEntity> resultList = null;
    Objectify ofy = OS.begin();
    // OQuery<GroupAuthorityEntity> q =
    // OS.createQuery(GroupAuthorityEntity.class);
    Query<GroupAuthorityEntity> q = ofy.query(GroupAuthorityEntity.class);
    // resultList = ofy.prepare(q).asList();
    return q;
  }

}

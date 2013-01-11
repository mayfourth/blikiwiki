package info.bliki.gae.db;

import info.bliki.gae.model.GroupMemberEntity;

import java.util.List;

import org.jamwiki.model.OS;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class GroupMemberService {

  public static GroupMemberEntity save(GroupMemberEntity page) {
    Objectify ofy = OS.begin();
    ofy.put(page);
    return page;
  }

  public static void delete(GroupMemberEntity role) {
    Objectify ofy = OS.begin();
    ofy.delete(role);
  }

  public static QueryResultIterable<GroupMemberEntity> findByName(String name) {
    // GroupMemberEntity role = null;
    try {
      Objectify ofy = OS.begin();
      // OQuery<GroupMemberEntity> q = OS.createQuery(GroupMemberEntity.class);
      Query<GroupMemberEntity> q = ofy.query(GroupMemberEntity.class);
      q.filter("username", name);
      // role = ofy.prepare(q).asSingle();
      return q;
    } catch (NullPointerException npe) {
    }
    return null;
  }

  public static QueryResultIterable<GroupMemberEntity> getAll() {
    List<GroupMemberEntity> resultList = null;
    Objectify ofy = OS.begin();
    // OQuery<GroupMemberEntity> q = OS.createQuery(GroupMemberEntity.class);
    Query<GroupMemberEntity> q = ofy.query(GroupMemberEntity.class);
    // resultList = ofy.prepare(q).asList();
    return q;
  }

}

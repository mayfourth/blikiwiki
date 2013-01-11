package info.bliki.gae.db;

import info.bliki.gae.model.RecentChangeEntity;

import org.jamwiki.model.OS;
import org.jamwiki.model.Topic;
import org.jamwiki.utils.Pagination;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class RecentChangeService {

  public static RecentChangeEntity save(RecentChangeEntity version) {
    Objectify ofy = OS.begin();
    ofy.put(version);
    return version;
  }

  public static void delete(RecentChangeEntity version) {
    Objectify ofy = OS.begin();
    ofy.delete(version);
  }

  public static RecentChangeEntity findById(Long versionId) {
    if (versionId == null) {
      return null;
    }
    Objectify ofy = OS.begin();
    return ofy.find(RecentChangeEntity.class, versionId);
  }

  public static QueryResultIterable<RecentChangeEntity> findByTopic(Topic topic) {
    Objectify ofy = OS.begin();
    Query<RecentChangeEntity> q = ofy.query(RecentChangeEntity.class);
    q.filter("topicId", new Key<Topic>(Topic.class, topic.getName()));
    return q;
  }

  public static QueryResultIterable<RecentChangeEntity> findRecentChanges(Pagination pagination, boolean descending) {
    Objectify ofy = OS.begin();
    Query<RecentChangeEntity> q = ofy.query(RecentChangeEntity.class);
    return q;
  }
  
  public static QueryResultIterable<RecentChangeEntity> getAll() {
    Objectify ofy = OS.begin();
    Query<RecentChangeEntity> q = ofy.query(RecentChangeEntity.class);
    return q;
  }

}

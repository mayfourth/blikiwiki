package info.bliki.gae.db;

import info.bliki.gae.model.RoleEntity;

import java.util.List;

import org.jamwiki.model.OS;
import org.jamwiki.model.Topic;
import org.jamwiki.model.TopicVersion;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class TopicVersionService {

  public static TopicVersion save(TopicVersion version) {
    Objectify ofy = OS.begin();
    ofy.put(version);
    return version;
  }

  public static void delete(TopicVersion version) {
    Objectify ofy = OS.begin();
    ofy.delete(version);
  }

  public static TopicVersion findById(Long versionId) {
    if (versionId == null) {
      return null;
    }
    Objectify ofy = OS.begin();
    return ofy.find(TopicVersion.class, versionId);
  }

  public static QueryResultIterable<TopicVersion> findByTopic(Topic topic) {
    Objectify ofy = OS.begin();
    // OQuery<TopicVersion> q = OS.createQuery(TopicVersion.class);
    Query<TopicVersion> q = ofy.query(TopicVersion.class);
    q.filter("topicId", new Key<Topic>(Topic.class, topic.getName()));
    // return ofy.prepare(q).asList();
    return q;
  }

  public static QueryResultIterable<TopicVersion> getAll() {
    Objectify ofy = OS.begin();
    Query<TopicVersion> q = ofy.query(TopicVersion.class);
    return q;
  }

}

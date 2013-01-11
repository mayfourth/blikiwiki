package info.bliki.gae.db;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;

import org.jamwiki.model.LogItem;
import org.jamwiki.model.OS;
import org.jamwiki.model.Topic;
import org.jamwiki.utils.Pagination;

import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class LogItemService {

  public static LogItem save(LogItem version) {
    Objectify ofy = OS.begin();
    ofy.put(version);
    return version;
  }

  public static void delete(LogItem version) {
    Objectify ofy = OS.begin();
    ofy.delete(version);
  }

  public static LogItem findById(Long versionId) {
    if (versionId == null) {
      return null;
    }
    Objectify ofy = OS.begin();
    return ofy.find(LogItem.class, versionId);
  }

  public static QueryResultIterable<LogItem> findByTopic(Topic topic) {
    Objectify ofy = OS.begin();
    Query<LogItem> q = ofy.query(LogItem.class);
    q.filter("topicId", new Key<Topic>(Topic.class, topic.getName()));
    return q;
  }

  // public static QueryResultIterable<LogItem> getAll(Pagination pagination) {
  // Objectify ofy = OS.begin();
  // Query<LogItem> q = ofy.query(LogItem.class);
  // // limit 10, offset 5
  // ofy.prepare(q).asList(withLimit(10).offset(5));
  //
  // q.limit(arg0)filter(FetchOptions.Builder.withLimit(pagination.getNumResults()));
  // pagination.getNumResults();
  // pagination.getOffset();
  // return q;
  // }

  public static QueryResultIterable<LogItem> getAll(String virtualWiki, int logType,
      Pagination pagination, boolean descending) {
    Objectify ofy = OS.begin();
    Query<LogItem> q = ofy.query(LogItem.class);
    return q;
  }

}

package info.bliki.gae.db;

import info.bliki.gae.model.PropertyEntity;

import java.util.Enumeration;
import java.util.Properties;

import org.jamwiki.model.OS;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class PropertyService {

  public static PropertyEntity save(PropertyEntity page) {
    Objectify ofy = OS.begin();
    ofy.put(page);
    return page;
  }

  public static void delete(PropertyEntity property) {
    Objectify ofy = OS.begin();
    ofy.delete(property);
  }

  public static PropertyEntity findByKey(String key) {
    try {
      Objectify ofy = OS.begin();
      return ofy.get(PropertyEntity.class, key);
    } catch (EntityNotFoundException enf) {
    }
    return null;
  }

  public static QueryResultIterable<PropertyEntity> getAll() {
    Objectify ofy = OS.begin();
    Query<PropertyEntity> q = ofy.query(PropertyEntity.class);
    return q;
  }

  public static void saveAll(Properties properties) {
    PropertyEntity page;
    Objectify ofy = OS.begin();

    Enumeration<Object> keyEnum = properties.keys();
    while (keyEnum.hasMoreElements()) {
      String key = (String) keyEnum.nextElement();
      String value = (String) properties.get(key.toString());
      if (value != null) {
        page = new PropertyEntity(key, value);
        ofy.put(page);
      }
    }
  }
}

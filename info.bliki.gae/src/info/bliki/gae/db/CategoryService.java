package info.bliki.gae.db;

import org.jamwiki.model.Category;
import org.jamwiki.model.OS;
import org.jamwiki.model.Topic;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Query;

public class CategoryService {
//	public static Cache cache = null;
//
//	static {
//		try {
//			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
//			cache = cacheFactory.createCache(Collections.emptyMap());
//		} catch (CacheException e) {
//			// ...
//			// e.printStackTrace();
//		}
//	}

	public static Category save(Category page) {
		Objectify ofy = OS.begin();
		ofy.put(page);
		return page;
	}

	public static Category update(Category category) {
		Category existingEntity = null;
		try {
			Objectify ofy = OS.begin();
			existingEntity = ofy.get(Category.class, category.getCategoryId());
			existingEntity.setChildTopicName(category.getChildTopicName());
			existingEntity.setSortKey(category.getSortKey());
			existingEntity.setVirtualWiki(category.getVirtualWiki());
			existingEntity.setTopicType(category.getTopicType());
			ofy.put(existingEntity);
//			cache.put(existingEntity.getName(), existingEntity);
		} catch (EntityNotFoundException enf) {
		}
		return existingEntity;
	}

	public static void delete(Category page) {
//		cache.remove(page.getName());
		Objectify ofy = OS.begin();
		ofy.delete(page);
	}

	public static void deleteTopicCategories(Topic topic) {
		Objectify ofy = OS.begin();
		Query<Category> q = ofy.query(Category.class);
		q.filter("childTopicName", topic.getName());
		ofy.delete(q);
	}

	// public static Category findByName(String name) {
	// Category category = (Category) cache.get(name);
	// if (category != null) {
	// return category;
	// }
	// try {
	// Objectify ofy = OS.begin();
	// return ofy.get(new Key<Category>(Category.class, name));
	// } catch (EntityNotFoundException enfe) {
	// }
	// return null;
	// }

	public static QueryResultIterable<Category> lookupCategoryTopics(String virtualWiki, String categoryName) {
		Objectify ofy = OS.begin();
		Query<Category> q = ofy.query(Category.class);
		q.filter("name", categoryName);
		q.filter("virtualWiki", virtualWiki);
		return q;
	}

	public static QueryResultIterable<Category> getAll(String virtualWiki) {
		// List<Category> resultList = null;
		Objectify ofy = OS.begin();
		Query<Category> q = ofy.query(Category.class);
		q.filter("virtualWiki", virtualWiki);
		// resultList = ofy.prepare(q).asList();
		return q;
	}

	public static QueryResultIterable<Category> getAll() {
		// List<Category> resultList = null;
		Objectify ofy = OS.begin();
		Query<Category> q = ofy.query(Category.class);
		// resultList = ofy.prepare(q).asList();
		return q;
	}

	// Category save(Category page);
	//
	// Category update(Category page);
	//
	// void delete(Category page);
	//
	// Category findByTitle(String title);
	//
	// String getHTMLContent(String title);
	//
	// List<Category> getAll();
}

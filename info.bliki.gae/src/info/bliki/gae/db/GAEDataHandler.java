package info.bliki.gae.db;

import info.bliki.gae.model.AuthorityEntity;
import info.bliki.gae.model.GroupAuthorityEntity;
import info.bliki.gae.model.GroupMemberEntity;
import info.bliki.gae.model.RecentChangeEntity;
import info.bliki.gae.model.RoleEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.jamwiki.DataAccessException;
import org.jamwiki.DataHandler;
import org.jamwiki.Environment;
import org.jamwiki.WikiBase;
import org.jamwiki.WikiException;
import org.jamwiki.WikiMessage;
import org.jamwiki.authentication.JAMWikiAuthenticationConfiguration;
import org.jamwiki.authentication.RoleImpl;
import org.jamwiki.authentication.WikiUserDetails;
import org.jamwiki.db.WikiDatabase;
import org.jamwiki.model.Category;
import org.jamwiki.model.LogItem;
import org.jamwiki.model.RecentChange;
import org.jamwiki.model.Role;
import org.jamwiki.model.RoleMap;
import org.jamwiki.model.Topic;
import org.jamwiki.model.TopicVersion;
import org.jamwiki.model.VirtualWiki;
import org.jamwiki.model.Watchlist;
import org.jamwiki.model.WikiGroup;
import org.jamwiki.model.WikiUser;
import org.jamwiki.utils.LinkUtil;
import org.jamwiki.utils.NamespaceHandler;
import org.jamwiki.utils.Pagination;
import org.jamwiki.utils.WikiLink;
import org.jamwiki.utils.WikiUtil;

import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;

public class GAEDataHandler implements DataHandler {
	/**
  *
  */
	private static void checkLength(String value, int maxLength) throws WikiException {
		if (value != null && value.length() > maxLength) {
			throw new WikiException(new WikiMessage("error.fieldlength", value, Integer.valueOf(maxLength).toString()));
		}
	}

	private void addCategory(Category category, Topic topic) throws DataAccessException, WikiException {
		// TODO Auto-generated method stub
		// int virtualWikiId = this.lookupVirtualWikiId(category.getVirtualWiki());
		this.validateCategory(category);
		CategoryService.save(category);
		// try {
		// this.queryHandler()
		// .insertCategory(category, virtualWikiId, topicId, conn);
		// } catch (SQLException e) {
		// throw new DataAccessException(e);
		// }
	}

	/**
  * 
  */
	private void addGroupMember(String username, Long groupId) throws DataAccessException {
		GroupMemberEntity group = new GroupMemberEntity(username, groupId);
		GroupMemberService.save(group);
	}

	private void addRecentChangeEntity(RecentChangeEntity change) {
		RecentChangeService.save(change);
		// PreparedStatement stmt = null;
		// try {
		// stmt = conn.prepareStatement(STATEMENT_INSERT_RECENT_CHANGE);
		// if (change.getTopicVersionId() == null) {
		// stmt.setNull(1, Types.INTEGER);
		// } else {
		// stmt.setInt(1, change.getTopicVersionId());
		// }
		// if (change.getPreviousTopicVersionId() == null) {
		// stmt.setNull(2, Types.INTEGER);
		// } else {
		// stmt.setInt(2, change.getPreviousTopicVersionId());
		// }
		// if (change.getTopicId() == null) {
		// stmt.setNull(3, Types.INTEGER);
		// } else {
		// stmt.setInt(3, change.getTopicId());
		// }
		// stmt.setString(4, change.getTopicName());
		// stmt.setTimestamp(5, change.getChangeDate());
		// stmt.setString(6, change.getChangeComment());
		// if (change.getAuthorId() == null) {
		// stmt.setNull(7, Types.INTEGER);
		// } else {
		// stmt.setInt(7, change.getAuthorId());
		// }
		// stmt.setString(8, change.getAuthorName());
		// if (change.getEditType() == null) {
		// stmt.setNull(9, Types.INTEGER);
		// } else {
		// stmt.setInt(9, change.getEditType());
		// }
		// stmt.setInt(10, virtualWikiId);
		// stmt.setString(11, change.getVirtualWiki());
		// if (change.getCharactersChanged() == null) {
		// stmt.setNull(12, Types.INTEGER);
		// } else {
		// stmt.setInt(12, change.getCharactersChanged());
		// }
		// if (change.getLogType() == null) {
		// stmt.setNull(13, Types.INTEGER);
		// } else {
		// stmt.setInt(13, change.getLogType());
		// }
		// stmt.setString(14, change.getParamString());
		// stmt.executeUpdate();
		// } finally {
		// DatabaseConnection.closeStatement(stmt);
		// }
	}

	private void addTopic(Topic topic, LinkedHashMap<String, String> categories) throws DataAccessException, WikiException {
		this.validateTopic(topic);
		PageService.save(topic, categories);
	}

	private void addTopicVersion(TopicVersion topicVersion) throws DataAccessException, WikiException {
		this.validateTopicVersion(topicVersion);
		TopicVersionService.save(topicVersion);
	}

	@Override
	public boolean authenticate(String username, String password) throws DataAccessException {
		return UserService.isAuthenticated(username, password);
	}

	@Override
	public boolean canMoveTopic(Topic fromTopic, String destination) throws DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("canMoveTopic");
		// return false;
	}

	@Override
	public void deleteTopic(Topic topic, TopicVersion topicVersion) throws DataAccessException, WikiException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("deleteTopic");
	}

	private void deleteTopicCategories(Topic topic) throws DataAccessException {
		CategoryService.deleteTopicCategories(topic);
		// TODO Auto-generated method stub
		// try {
		// this.queryHandler().deleteTopicCategories(topic.getTopicId(), conn);
		// } catch (SQLException e) {
		// throw new DataAccessException(e);
		// }
		// throw new NotImplementedException("deleteTopicCategories");
	}

	@Override
	public Iterable<Category> getAllCategories(String virtualWiki, Pagination pagination) throws DataAccessException {
		return CategoryService.getAll(virtualWiki);
	}

	@Override
	public List<Role> getAllRoles() throws DataAccessException {
		QueryResultIterable<RoleEntity> rs = RoleService.getAll();
		List<Role> roles = new ArrayList<Role>();
		for (RoleEntity roleEntity : rs) {
			roles.add(initRole(roleEntity));
		}
		return roles;
	}

	@Override
	public List<String> getAllTopicNames(String virtualWiki) throws DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("getAllTopicNames");
		// return null;
	}

	@Override
	public List<LogItem> getLogItems(String virtualWiki, int logType, Pagination pagination, boolean descending) {
		QueryResultIterable<LogItem> iter = LogItemService.getAll(virtualWiki, logType, pagination, descending);
		List<LogItem> logItems = new ArrayList<LogItem>();
		for (LogItem logItem : iter) {
			logItems.add(logItem);
		}
		return logItems;
	}

	@Override
	public List<RecentChange> getRecentChanges(String virtualWiki, Pagination pagination, boolean descending)
			throws DataAccessException {
		// TODO Auto-generated method stub
		// throw new NotImplementedException("getRecentChanges");

		QueryResultIterable<RecentChangeEntity> list = RecentChangeService.findRecentChanges(pagination, descending);
		List<RecentChange> recentChanges = new ArrayList<RecentChange>();
		for (RecentChangeEntity recentChangeEntity : list) {
			recentChanges.add(this.initRecentChange(recentChangeEntity));
		}
		return recentChanges;
	}

	@Override
	public List<RoleMap> getRoleMapByLogin(String loginFragment) throws DataAccessException {
		if (StringUtils.isBlank(loginFragment)) {
			return new ArrayList<RoleMap>();
		}
		// Connection conn = null;
		// PreparedStatement stmt = null;
		// ResultSet rs = null;
		// try {
		// conn = DatabaseConnection.getConnection();
		// stmt = conn.prepareStatement(STATEMENT_SELECT_AUTHORITIES_LOGIN);
		// loginFragment = '%' + loginFragment.toLowerCase() + '%';
		// stmt.setString(1, loginFragment);
		// rs = stmt.executeQuery();
		// WikiUserService.getAll();
		LinkedHashMap<Long, RoleMap> roleMaps = new LinkedHashMap<Long, RoleMap>();
		// while (rs.next()) {
		// Long userId = rs.getInt("wiki_user_id");
		// RoleMap roleMap = new RoleMap();
		// if (roleMaps.containsKey(userId)) {
		// roleMap = roleMaps.get(userId);
		// } else {
		// roleMap.setUserId(userId);
		// roleMap.setUserLogin(rs.getString("username"));
		// }
		// roleMap.addRole(rs.getString("authority"));
		// roleMaps.put(userId, roleMap);
		// }
		return new ArrayList<RoleMap>(roleMaps.values());
		// } finally {
		// DatabaseConnection.closeConnection(conn, stmt, rs);
		// }
	}

	@Override
	public List<RoleMap> getRoleMapByRole(String roleName) throws DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("getRoleMapByRole");
		// return null;
	}

	@Override
	public List<Role> getRoleMapGroup(String groupName) throws DataAccessException {
		QueryResultIterable<GroupAuthorityEntity> list = GroupAuthorityService.getByGroupname(groupName);
		if (list != null) {
			List<Role> roles = new ArrayList<Role>();
			RoleImpl role;
			for (GroupAuthorityEntity entity : list) {
				role = new RoleImpl(entity.getAuthority());
				role.setDescription(entity.getGroupName());
				roles.add(role);
			}
			return roles;
		}
		return null;
	}

	@Override
	public List<RoleMap> getRoleMapGroups() throws DataAccessException {
		LinkedHashMap<Long, RoleMap> roleMaps = new LinkedHashMap<Long, RoleMap>();
		QueryResultIterable<GroupAuthorityEntity> list = GroupAuthorityService.getAll();
		for (GroupAuthorityEntity groupAuthorityEntity : list) {
			Long groupId = groupAuthorityEntity.getGroupId();
			RoleMap roleMap = new RoleMap();
			if (roleMaps.containsKey(groupId)) {
				roleMap = roleMaps.get(groupId);
			} else {
				roleMap.setGroupId(groupId);
				roleMap.setGroupName(groupAuthorityEntity.getGroupName());
			}
			roleMap.addRole(groupAuthorityEntity.getAuthority());
			roleMaps.put(groupId, roleMap);

		}
		return new ArrayList<RoleMap>(roleMaps.values());
	}

	@Override
	public List<Role> getRoleMapUser(String login) throws DataAccessException {
		// TODO Auto-generated method stub
		QueryResultIterable<AuthorityEntity> list = AuthorityService.findByName(login);
		RoleImpl roleImpl;
		List<Role> roles = new ArrayList<Role>();
		for (AuthorityEntity authorityEntity : list) {
			roleImpl = new RoleImpl(authorityEntity.getAuthority());
			roleImpl.setDescription(roleImpl.getDescription());
		}
		return roles;
	}

	// private TopicVersion initTopicVersion(ResultSet rs) throws SQLException {
	// TopicVersion topicVersion = new TopicVersion();
	// topicVersion.setTopicVersionId(rs.getInt("topic_version_id"));
	// topicVersion.setTopicId(rs.getInt("topic_id"));
	// topicVersion.setEditComment(rs.getString("edit_comment"));
	// topicVersion.setVersionContent(rs.getString("version_content"));
	// // FIXME - Oracle cannot store an empty string - it converts them
	// // to null - so add a hack to work around the problem.
	// if (topicVersion.getVersionContent() == null) {
	// topicVersion.setVersionContent("");
	// }
	// int previousTopicVersionId = rs.getInt("previous_topic_version_id");
	// if (previousTopicVersionId > 0) {
	// topicVersion.setPreviousTopicVersionId(previousTopicVersionId);
	// }
	// int userId = rs.getInt("wiki_user_id");
	// if (userId > 0) {
	// topicVersion.setAuthorId(userId);
	// }
	// topicVersion.setCharactersChanged(rs.getInt("characters_changed"));
	// topicVersion.setVersionParamString(rs.getString("version_params"));
	// topicVersion.setEditDate(rs.getTimestamp("edit_date"));
	// topicVersion.setEditType(rs.getInt("edit_type"));
	// topicVersion.setAuthorDisplay(rs.getString("wiki_user_display"));
	// return topicVersion;
	// }

	private RecentChange initRecentChange(RecentChangeEntity rs) {
		RecentChange change = new RecentChange();

		change.setTopicVersionId(rs.getTopicVersionId());
		change.setPreviousTopicVersionId(rs.getPreviousTopicVersionId());
		Key<Topic> topicId = rs.getTopicKey();
		change.setTopicOKey(topicId);

		Topic topic = rs.getTopicId();
		change.setTopicName(topic.getName());
		change.setCharactersChanged(rs.getCharactersChanged());
		change.setChangeDate(rs.getChangeDate());
		change.setChangeComment(rs.getChangeComment());

		change.setAuthorId(rs.getAuthorId());

		change.setAuthorName(rs.getAuthorName());
		int editType = rs.getEditType();
		if (editType > 0) {
			change.setEditType(editType);
			// change.initChangeWikiMessageForVersion(editType,
			// .getString("log_params"));
		}
		// int logType = rs.getInt("log_type");
		// if (logType > 0) {
		// change.setLogType(logType);
		// change.initChangeWikiMessageForLog(logType, rs.getString("log_params"));
		// }
		change.setVirtualWiki(topic.getVirtualWiki());
		return change;
	}

	private RecentChange initRecentChange(TopicVersion rs) {
		RecentChange change = new RecentChange();

		change.setTopicVersionId(rs.getTopicVersionId());
		change.setPreviousTopicVersionId(rs.getPreviousTopicVersionId());
		Key<Topic> topicId = rs.getTopicOKey();
		change.setTopicOKey(topicId);

		Topic topic = rs.getTopicId();
		change.setTopicName(topic.getName());
		change.setCharactersChanged(rs.getCharactersChanged());
		change.setChangeDate(rs.getEditDate());
		change.setChangeComment(rs.getEditComment());

		change.setAuthorId(rs.getAuthorId());

		change.setAuthorName(rs.getAuthorDisplay());
		int editType = rs.getEditType();
		if (editType > 0) {
			change.setEditType(editType);
			// change.initChangeWikiMessageForVersion(editType,
			// .getString("log_params"));
		}
		// int logType = rs.getInt("log_type");
		// if (logType > 0) {
		// change.setLogType(logType);
		// change.initChangeWikiMessageForLog(logType, rs.getString("log_params"));
		// }
		change.setVirtualWiki(topic.getVirtualWiki());
		return change;
	}

	@Override
	public List<RecentChange> getTopicHistory(String virtualWiki, String topicName, Pagination pagination, boolean descending)
			throws DataAccessException {
		Topic topic = this.lookupTopic(virtualWiki, topicName, true, null);
		if (topic == null) {
			return new ArrayList<RecentChange>();
		}
		QueryResultIterable<TopicVersion> list = TopicVersionService.findByTopic(topic);
		List<RecentChange> recentChanges = new ArrayList<RecentChange>();
		for (TopicVersion topicVersion : list) {
			recentChanges.add(this.initRecentChange(topicVersion));
		}
		return recentChanges;
	}

	@Override
	public List<String> getTopicsAdmin(String virtualWiki, Pagination pagination) throws DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("getTopicsAdmin");
		// return null;
	}

	@Override
	public List<VirtualWiki> getVirtualWikiList() throws DataAccessException {
		QueryResultIterable<VirtualWiki> results = VirualWikiService.getAll();
		ArrayList<VirtualWiki> list = new ArrayList<VirtualWiki>();
		for (VirtualWiki virtualWiki : results) {
			list.add(virtualWiki);
		}
		if (list.size() == 0) {
			// TODO allow multiple virtual wikis
			VirtualWiki vw = new VirtualWiki(WikiBase.DEFAULT_VWIKI, Environment.getValue(Environment.PROP_BASE_DEFAULT_TOPIC));
			list.add(vw);
			return list;
		}
		return list;
	}

	@Override
	public Watchlist getWatchlist(String virtualWiki, Long userId) throws DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("getWatchlist");
		// return null;
	}

	@Override
	public List<RecentChange> getWatchlist(String virtualWiki, Long userId, Pagination pagination) throws DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("getWatchlist");
		// return null;
	}

	private Role initRole(RoleEntity rs) {
		Role role = new RoleImpl(rs.getName());
		role.setDescription(rs.getDescription());
		return role;
	}

	@Override
	public List<Category> lookupCategoryTopics(String virtualWiki, String categoryName) throws DataAccessException {
		QueryResultIterable<Category> iter = CategoryService.lookupCategoryTopics(virtualWiki, categoryName);
		;
		List<Category> resultList = new ArrayList<Category>();
		for (Category category : iter) {
			resultList.add(category);
		}
		return resultList;
	}

	@Override
	public Topic lookupTopic(String virtualWiki, String topicName, boolean deleteOK, Object transactionObject) {
		// TODO check this

		// if (StringUtils.isBlank(virtualWiki) || StringUtils.isBlank(topicName)) {
		if (StringUtils.isBlank(topicName)) {
			return null;
		}
		// String key = WikiCache.key(virtualWikiName, topicName);
		// if (transactionObject == null) {
		// // retrieve topic from the cache only if this call is not currently a
		// part
		// // of a transaction to avoid retrieving data that might have been updated
		// // as part of this transaction and would thus now be out of date
		// Element cacheElement = WikiCache.retrieveFromCache(CACHE_TOPICS, key);
		// if (cacheElement != null) {
		// Topic cacheTopic = (Topic)cacheElement.getObjectValue();
		// return (cacheTopic == null || (!deleteOK && cacheTopic.getDeleteDate() !=
		// null)) ? null : new Topic(cacheTopic);
		// }
		// }
		WikiLink wikiLink = LinkUtil.parseWikiLink(topicName);
		String namespace = wikiLink.getNamespace();
		boolean caseSensitive = true;
		if (namespace != null) {
			if (namespace.equals(NamespaceHandler.NAMESPACE_SPECIAL)) {
				// invalid namespace
				return null;
			}
			if (namespace.equals(NamespaceHandler.NAMESPACE_TEMPLATE) || namespace.equals(NamespaceHandler.NAMESPACE_USER)
					|| namespace.equals(NamespaceHandler.NAMESPACE_CATEGORY)) {
				// user/template/category namespaces are case-insensitive
				caseSensitive = false;
			}
		}
		// Topic topic = new Topic();
		// topic.setAdminOnly(false);
		// topic.setName(topicName);
		// topic.setVirtualWiki(virtualWikiName);
		// long currentVersionId = 0;
		// if (currentVersionId > 0) {
		// topic.setCurrentVersionId(currentVersionId);
		// }
		return PageService.findByTitle(topicName);
	}

	@Override
	public List<String> lookupTopicByType(String virtualWiki, int topicType1, int topicType2, Pagination pagination)
			throws DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("lookupTopicByType");
		// return null;
	}

	public TopicVersion lookupTopicVersion(Long topicVersionId) throws DataAccessException {
		// Element cacheElement = WikiCache.retrieveFromCache(CACHE_TOPIC_VERSIONS,
		// topicVersionId);
		// if (cacheElement != null) {
		// return (TopicVersion)cacheElement.getObjectValue();
		// }
		TopicVersion topicVersion = TopicVersionService.findById(topicVersionId);
		// WikiCache.addToCache(CACHE_TOPIC_VERSIONS, topicVersionId, topicVersion);
		return topicVersion;
	}

	@Override
	public VirtualWiki lookupVirtualWiki(String virtualWikiName) throws DataAccessException {
		// TODO allow multiple virtual wikis
		return new VirtualWiki(WikiBase.DEFAULT_VWIKI, Environment.getValue(Environment.PROP_BASE_DEFAULT_TOPIC));
	}

	/**
	 * Retrieve a WikiGroup object for a given group name.
	 * 
	 * @param groupName
	 *          The group name for the group being queried.
	 * @return The WikiGroup object for the given group name, or <code>null</code>
	 *         if no matching group exists.
	 * @throws DataAccessException
	 *           Thrown if any error occurs during method execution.
	 */
	@Override
	public WikiGroup lookupWikiGroup(String groupName) throws DataAccessException {
		return WikiGroupService.findByName(groupName);
	}

	@Override
	public WikiUser lookupWikiUser(Long userId) throws org.jamwiki.DataAccessException {
		return WikiUserService.findById(userId);
	}

	@Override
	public WikiUser lookupWikiUser(String username) throws org.jamwiki.DataAccessException {
		return WikiUserService.findByName(username);
	}

	@Override
	public int lookupWikiUserCount() throws org.jamwiki.DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("lookupWikiUserCount");
		// return 0;
	}

	@Override
	public String lookupWikiUserEncryptedPassword(String username) throws org.jamwiki.DataAccessException {
		WikiUserDetails userDetails = WikiUserDetailsService.findByName(username);
		if (userDetails == null) {
			return null;
		}
		return userDetails.getPassword();
	}

	@Override
	public List<String> lookupWikiUsers(Pagination pagination) throws org.jamwiki.DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("lookupWikiUsers");
		// return null;
	}

	/**
  *
  */
	// protected void validateLogItem(LogItem logItem) throws WikiException {
	// checkLength(logItem.getUserDisplayName(), 200);
	// checkLength(logItem.getLogParamString(), 500);
	// logItem.setLogComment(StringUtils.substring(logItem.getLogComment(), 0,
	// 200));
	// }

	@Override
	public void moveTopic(Topic fromTopic, TopicVersion fromVersion, String destination) throws DataAccessException, WikiException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reloadLogItems() throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void reloadRecentChanges() throws DataAccessException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("reloadRecentChanges");
	}

	/**
  *
  */
	// protected void validateRecentChange(RecentChange change) throws
	// WikiException {
	// checkLength(change.getTopicName(), 200);
	// checkLength(change.getAuthorName(), 200);
	// checkLength(change.getVirtualWiki(), 100);
	// change.setChangeComment(StringUtils.substring(change.getChangeComment(), 0,
	// 200));
	// checkLength(change.getParamString(), 500);
	// }

	@Override
	public void setup(Locale locale, WikiUser user, String username, String encryptedPassword) throws DataAccessException,
			WikiException {
		WikiDatabase.initialize();

		WikiDatabase.setup(locale, user, username, encryptedPassword);
	}

	@Override
	public void setupSpecialPages(Locale locale, WikiUser user, VirtualWiki virtualWiki) throws DataAccessException, WikiException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("setupSpecialPages");
	}

	@Override
	public void undeleteTopic(Topic topic, TopicVersion topicVersion) throws DataAccessException, WikiException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("undeleteTopic");
	}

	/**
  *
  */
	protected void validateAuthority(String role) throws WikiException {
		checkLength(role, 30);
	}

	/**
  *
  */
	protected void validateCategory(Category category) throws WikiException {
		checkLength(category.getName(), 200);
		checkLength(category.getSortKey(), 200);
	}

	/**
  *
  */
	protected void validateRole(Role role) throws WikiException {
		checkLength(role.getAuthority(), 30);
		role.setDescription(StringUtils.substring(role.getDescription(), 0, 200));
	}

	/**
  *
  */
	protected void validateTopic(Topic topic) throws WikiException {
		checkLength(topic.getName(), 200);
		checkLength(topic.getRedirectTo(), 200);
	}

	/**
  *
  */
	protected void validateTopicVersion(TopicVersion topicVersion) throws WikiException {
		checkLength(topicVersion.getAuthorDisplay(), 100);
		checkLength(topicVersion.getVersionParamString(), 500);
		topicVersion.setEditComment(StringUtils.substring(topicVersion.getEditComment(), 0, 200));
	}

	/**
  *
  */
	protected void validateUserDetails(WikiUserDetails userDetails) throws WikiException {
		checkLength(userDetails.getUsername(), 100);
		// do not throw exception containing password info
		if (userDetails.getPassword() != null && userDetails.getPassword().length() > 100) {
			throw new WikiException(new WikiMessage("error.fieldlength", "-", "100"));
		}
	}

	/**
  *
  */
	protected void validateVirtualWiki(VirtualWiki virtualWiki) throws WikiException {
		checkLength(virtualWiki.getName(), 100);
		checkLength(virtualWiki.getDefaultTopicName(), 200);
	}

	/**
  *
  */
	protected void validateWatchlistEntry(String topicName) throws WikiException {
		checkLength(topicName, 200);
	}

	/**
  *
  */
	protected void validateWikiGroup(WikiGroup group) throws WikiException {
		checkLength(group.getName(), 30);
		group.setDescription(StringUtils.substring(group.getDescription(), 0, 200));
	}

	/**
  *
  */
	protected void validateWikiUser(WikiUser user) throws WikiException {
		checkLength(user.getUsername(), 100);
		checkLength(user.getDisplayName(), 100);
		checkLength(user.getCreateIpAddress(), 39);
		checkLength(user.getLastLoginIpAddress(), 39);
		checkLength(user.getDefaultLocale(), 8);
		checkLength(user.getEmail(), 100);
		checkLength(user.getEditor(), 50);
		checkLength(user.getSignature(), 255);
	}

	@Override
	public void writeRole(Role role, boolean update) throws DataAccessException, WikiException {
		this.validateRole(role);
		RoleEntity re = new RoleEntity();
		re.setDescription(role.getDescription());
		re.setName(role.getAuthority());
		// if (update) {
		// RoleService.update(re);
		// } else {
		RoleService.save(re);
		// }

	}

	/**
  *
  */
	// protected void validateWikiFile(WikiFile wikiFile) throws WikiException {
	// checkLength(wikiFile.getFileName(), 200);
	// checkLength(wikiFile.getUrl(), 200);
	// checkLength(wikiFile.getMimeType(), 100);
	// }

	@Override
	public void writeRoleMapGroup(Long groupId, String groupName, List<String> roles) throws DataAccessException, WikiException {
		try {
			// status = DatabaseConnection.startTransaction();
			GroupAuthorityService.deleteByGroupId(groupId);
			for (String authority : roles) {
				this.validateAuthority(authority);
				// this.queryHandler().insertUserAuthority(username, authority, conn);
				GroupAuthorityService.save(new GroupAuthorityEntity(groupId, groupName, authority));
			}
			// refresh the current role requirements
			JAMWikiAuthenticationConfiguration.resetJamwikiAnonymousAuthorities();
			JAMWikiAuthenticationConfiguration.resetDefaultGroupRoles();
		} catch (WikiException e) {
			// DatabaseConnection.rollbackOnException(status, e);
			throw e;
		}
	}

	@Override
	public void writeRoleMapUser(String username, List<String> roles) throws DataAccessException, WikiException {
		try {
			// status = DatabaseConnection.startTransaction();
			AuthorityService.deleteByName(username);
			for (String authority : roles) {
				this.validateAuthority(authority);
				// this.queryHandler().insertUserAuthority(username, authority, conn);
				AuthorityService.save(new AuthorityEntity(username, authority));
			}
		} catch (WikiException e) {
			// DatabaseConnection.rollbackOnException(status, e);
			throw e;
		}
	}

	@Override
	public void writeTopic(Topic topic, TopicVersion topicVersion, LinkedHashMap<String, String> categories, List<String> links)
			throws DataAccessException, WikiException {
		WikiUtil.validateTopicName(topic.getName());
		addTopic(topic, categories);
		if (topicVersion != null) {
			if (topicVersion.getPreviousTopicVersionId() == null && topic.getCurrentVersionId() != null) {
				topicVersion.setPreviousTopicVersionId(topic.getCurrentVersionId());
			}
			topicVersion.setTopicId(topic);
			topicVersion.initializeVersionParams(topic);
			// write version
			addTopicVersion(topicVersion);
			topic.setCurrentVersionId(topicVersion.getTopicVersionId());
			// update the topic AFTER creating the version so that the
			// current_topic_version_id parameter is set properly
			// this.updateTopic(topic, conn);
			PageService.save(topic, categories);
			String authorName = topicVersion.getAuthorDisplay();
			// String authorName = this.authorName(topicVersion.getAuthorId(),
			// topicVersion.getAuthorDisplay());
			// LogItem logItem = LogItem.initLogItem(topic, topicVersion, authorName);
			RecentChangeEntity change = null;
			// if (logItem != null) {
			// this.addLogItem(logItem, conn);
			// change = RecentChange.initRecentChange(logItem);
			// } else {
			change = RecentChangeEntity.initRecentChangeEntity(topic, topicVersion, authorName);
			// }
			if (topicVersion.isRecentChangeAllowed()) {
				addRecentChangeEntity(change);
			}
		}
		if (categories != null) {
			// add / remove categories associated with the topic
			this.deleteTopicCategories(topic);
			if (topic.getDeleteDate() == null) {
				for (String categoryName : categories.keySet()) {
					Category category = new Category();
					category.setName(categoryName);
					category.setSortKey(categories.get(categoryName));
					category.setVirtualWiki(topic.getVirtualWiki());
					category.setChildTopicName(topic.getName());
					this.addCategory(category, topic);
				}
			}
		}
	}

	/**
  *
  */
	// protected void validateWikiFileVersion(WikiFileVersion wikiFileVersion)
	// throws WikiException {
	// checkLength(wikiFileVersion.getUrl(), 200);
	// checkLength(wikiFileVersion.getMimeType(), 100);
	// checkLength(wikiFileVersion.getAuthorDisplay(), 100);
	// wikiFileVersion.setUploadComment(StringUtils.substring(wikiFileVersion.getUploadComment(),
	// 0, 200));
	// }

	@Override
	public void writeVirtualWiki(VirtualWiki virtualWiki) throws DataAccessException, WikiException {
		VirualWikiService.save(virtualWiki);
	}

	@Override
	public void writeWatchlistEntry(Watchlist watchlist, String virtualWiki, String topicName, Long userId)
			throws DataAccessException, WikiException {
		// TODO Auto-generated method stub
		throw new NotImplementedException("writeWatchlistEntry");
	}

	@Override
	public void writeWikiGroup(WikiGroup group) throws DataAccessException, WikiException {
		WikiGroupService.save(group);
	}

	@Override
	public void writeWikiUser(WikiUser user, String username, String encryptedPassword) throws org.jamwiki.DataAccessException,
			WikiException {
		WikiUtil.validateUserName(user.getUsername());

		try {
			// status = DatabaseConnection.startTransaction();
			if (user.getUserId() == null) {
				WikiUserDetails userDetails = new WikiUserDetails(username, encryptedPassword, true, true, true, true,
						JAMWikiAuthenticationConfiguration.getDefaultGroupRoles());
				WikiUserDetailsService.save(userDetails);
				WikiUserService.save(user);
				// this.addWikiUser(user, conn);
				// add all users to the registered user group
				this.addGroupMember(user.getUsername(), WikiBase.getGroupRegisteredUser().getGroupId());
				// FIXME - reconsider this approach of separate entries for every
				// virtual wiki
				// List<VirtualWiki> virtualWikis = this.getVirtualWikiList();
				// for (VirtualWiki virtualWiki : virtualWikis) {
				// LogItem logItem = LogItem.initLogItem(user, virtualWiki.getName());
				// this.addLogItem(logItem, conn);
				// RecentChange change = RecentChange.initRecentChange(logItem);
				// this.addRecentChange(change, conn);
				// }
			} else {
				if (!StringUtils.isBlank(encryptedPassword)) {
					WikiUserDetails userDetails = new WikiUserDetails(username, encryptedPassword, true, true, true, true,
							JAMWikiAuthenticationConfiguration.getDefaultGroupRoles());
					// this.updateUserDetails(userDetails, conn);
				}
				// this.updateWikiUser(user, conn);
				WikiUserService.update(user);
			}
			// } catch (DataAccessException e) {
			// DatabaseConnection.rollbackOnException(status, e);
			// throw e;
		} catch (WikiException e) {
			// DatabaseConnection.rollbackOnException(status, e);
			throw e;
		}
	}
}

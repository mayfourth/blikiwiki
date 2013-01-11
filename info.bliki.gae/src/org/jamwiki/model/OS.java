package org.jamwiki.model;

import info.bliki.gae.model.AuthorityEntity;
import info.bliki.gae.model.GroupAuthorityEntity;
import info.bliki.gae.model.GroupMemberEntity;
import info.bliki.gae.model.PropertyEntity;
import info.bliki.gae.model.RecentChangeEntity;
import info.bliki.gae.model.RoleEntity;
import info.bliki.gae.model.UserEntity;

import org.jamwiki.authentication.WikiUserDetails;

import com.googlecode.objectify.ObjectifyService;

public class OS extends ObjectifyService {
  public static void initialize() {
    factory.register(AuthorityEntity.class);
    factory.register(Category.class);
    factory.register(GroupAuthorityEntity.class);
    factory.register(GroupMemberEntity.class);
    factory.register(PropertyEntity.class);
    factory.register(RecentChangeEntity.class);
    factory.register(RoleEntity.class);
    factory.register(TopicVersion.class);
    factory.register(TopicVersion.class);
    factory.register(Topic.class);
    // not used anymore:
    // factory.register(UserAuthorityEntity.class);
    factory.register(UserEntity.class);
    factory.register(VirtualWiki.class);
    factory.register(WikiGroup.class);
    factory.register(WikiUser.class);
    factory.register(WikiUserDetails.class);

    factory.setDatastoreTimeoutRetryCount(2);
  }
}
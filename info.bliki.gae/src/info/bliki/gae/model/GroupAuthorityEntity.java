package info.bliki.gae.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroupAuthorityEntity {
  @Id
  Long groupAuthorityId;

  Long groupId;

  String groupName;
  
  String authority;

  public GroupAuthorityEntity() {

  }

  public GroupAuthorityEntity(Long groupId, String groupName, String authority) {
    this.groupId = groupId;
    this.groupName = groupName;
    this.authority = authority;
  }

  /**
   * @return the authority
   */
  public String getAuthority() {
    return authority;
  }

  /**
   * @return the groupAuthorityId
   */
  public Long getGroupAuthorityId() {
    return groupAuthorityId;
  }

  /**
   * @return the groupId
   */
  public Long getGroupId() {
    return groupId;
  }

  /**
   * @return the groupName
   */
  public String getGroupName() {
    return groupName;
  }

  /**
   * @param authority the authority to set
   */
  public void setAuthority(String authority) {
    this.authority = authority;
  }

  /**
   * @param groupAuthorityId the groupAuthorityId to set
   */
  public void setGroupAuthorityId(Long groupAuthorityId) {
    this.groupAuthorityId = groupAuthorityId;
  }

  /**
   * @param groupId the groupId to set
   */
  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  /**
   * @param groupName the groupName to set
   */
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

}

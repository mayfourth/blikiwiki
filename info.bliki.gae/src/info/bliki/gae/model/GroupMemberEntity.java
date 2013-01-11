package info.bliki.gae.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroupMemberEntity {
  @Id
  private Long id=null;

  private String username;

  private Long groupId;

  public GroupMemberEntity() {
    super();
  }

  public GroupMemberEntity(String username, Long groupId) {
    super();
    this.username = username;
    this.groupId = groupId;
  }

  /**
   * @return the groupId
   */
  public Long getGroupId() {
    return groupId;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param groupId
   *          the groupId to set
   */
  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @param username
   *          the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }
}

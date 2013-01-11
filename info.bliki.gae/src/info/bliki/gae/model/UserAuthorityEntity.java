package info.bliki.gae.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAuthorityEntity {
  @Id
  Long userAuthorityId;

  String username;

  String authority;

  String roleName;

  private String roleDescription;

  public UserAuthorityEntity() {

  }

  /**
   * @return the userAuthorityId
   */
  public Long getUserAuthorityId() {
    return userAuthorityId;
  }

  /**
   * @param userAuthorityId the userAuthorityId to set
   */
  public void setUserAuthorityId(Long userAuthorityId) {
    this.userAuthorityId = userAuthorityId;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the authority
   */
  public String getAuthority() {
    return authority;
  }

  /**
   * @param authority the authority to set
   */
  public void setAuthority(String authority) {
    this.authority = authority;
  }

  /**
   * @return the roleName
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * @param roleName the roleName to set
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  /**
   * @return the roleDescription
   */
  public String getRoleDescription() {
    return roleDescription;
  }

  /**
   * @param roleDescription the roleDescription to set
   */
  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }

}

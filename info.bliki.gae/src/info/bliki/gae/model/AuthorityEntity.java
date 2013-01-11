package info.bliki.gae.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthorityEntity {
  @Id
  Long authorityId;

  String username;

  String authority;

  public AuthorityEntity() {

  }

  public AuthorityEntity(String username, String authority) {
    this.username = username;
    this.authority = authority;
  }

  /**
   * @return the authority
   */
  public String getAuthority() {
    return authority;
  }

  /**
   * @return the authorityId
   */
  public Long getAuthorityId() {
    return authorityId;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param authority
   *          the authority to set
   */
  public void setAuthority(String authority) {
    this.authority = authority;
  }

  /**
   * @param authorityId
   *          the authorityId to set
   */
  public void setAuthorityId(Long authorityId) {
    this.authorityId = authorityId;
  }

  /**
   * @param username
   *          the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }
}

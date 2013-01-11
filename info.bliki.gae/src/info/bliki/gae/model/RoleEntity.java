package info.bliki.gae.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoleEntity {
  @Id
  private String name;

  private String description;

  public RoleEntity() {

  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
}

package info.bliki.gae.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PropertyEntity {
  @Id
  private String key;
  private String value; 

  public PropertyEntity() {
  }
  
  public PropertyEntity(String key, String value) {
    this.key = key;
    this.value = value;
  }

  /**
   * @return the key
   */
  public String getKey() {
    return key;
  }

  /**
   * @param key
   *          the key to set
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }
}

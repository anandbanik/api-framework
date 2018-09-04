package com.walmart.scm.framework.services.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;

public class Attribute
  implements Serializable
{
  private String key = null;
  private String value = null;
  
  @JsonProperty("key")
  public String getKey()
  {
    return this.key;
  }
  
  public void setKey(String key)
  {
    this.key = key;
  }
  
  @JsonProperty("value")
  public String getValue()
  {
    return this.value;
  }
  
  public void setValue(String value)
  {
    this.value = value;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    Attribute attribute = (Attribute)o;
    
    return (Objects.equals(this.key, attribute.key)) && (Objects.equals(this.value, attribute.value));
  }
  
  public int hashCode()
  {
    return Objects.hash(new Object[] { this.key, this.value });
  }
  
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Attribute {\n");
    
    sb.append("    key: ").append(toIndentedString(this.key)).append("\n");
    sb.append("    value: ").append(toIndentedString(this.value)).append("\n");
    sb.append("}");
    return sb.toString();
  }
  
  private String toIndentedString(Object o)
  {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

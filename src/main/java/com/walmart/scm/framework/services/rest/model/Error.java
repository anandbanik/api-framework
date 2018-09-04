package com.walmart.scm.framework.services.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Error
  implements Serializable
{
  private String requestId = null;
  private String code = null;
  private String message = null;
  private List<Attribute> details = new ArrayList();
  
  @JsonProperty("requestId")
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public void setRequestId(String requestId)
  {
    this.requestId = requestId;
  }
  
  @JsonProperty("code")
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  @JsonProperty("message")
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  @JsonProperty("details")
  public List<Attribute> getDetails()
  {
    return this.details;
  }
  
  public void setDetails(List<Attribute> details)
  {
    this.details = details;
  }
  
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }
    Error error = (Error)o;
    
    return (Objects.equals(this.requestId, error.requestId)) && (Objects.equals(this.code, error.code)) && (Objects.equals(this.message, error.message)) && (Objects.equals(this.details, error.details));
  }
  
  public int hashCode()
  {
    return Objects.hash(new Object[] { this.requestId, this.code, this.message, this.details });
  }
  
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    requestId: ").append(toIndentedString(this.requestId)).append("\n");
    sb.append("    code: ").append(toIndentedString(this.code)).append("\n");
    sb.append("    message: ").append(toIndentedString(this.message)).append("\n");
    sb.append("    details: ").append(toIndentedString(this.details)).append("\n");
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

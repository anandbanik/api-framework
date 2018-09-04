package com.walmart.scm.framework.services.rest.exception;

import java.util.HashMap;
import java.util.Map;

public class ApplicationRuntimeException
  extends BaseException
{
  private static final long serialVersionUID = -2597298816248413539L;
  private int status = -1;
  private String requestId;
  private String errorCode;
  private String message;
  private Map<String, String> errorAttributes = new HashMap();
  
  public ApplicationRuntimeException() {}
  
  public ApplicationRuntimeException(String errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public ApplicationRuntimeException(String errorCode, String message)
  {
    this.errorCode = errorCode;
    this.message = message;
  }
  
  public ApplicationRuntimeException(int status)
  {
    this.status = status;
  }
  
  public ApplicationRuntimeException(int status, String errorCode, String message)
  {
    this.status = status;
    this.errorCode = errorCode;
    this.message = message;
  }
  
  public String getRequestId()
  {
    return this.requestId;
  }
  
  public void setRequestId(String requestId)
  {
    this.requestId = requestId;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public void setStatus(int status)
  {
    this.status = status;
  }
  
  public Map<String, String> getErrorAttributes()
  {
    return this.errorAttributes;
  }
  
  public void addErrorAttribute(String key, String value)
  {
    this.errorAttributes.put(key, value);
  }
  
  public String getErrorCode()
  {
    return this.errorCode;
  }
  
  public void setErrorCode(String errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public void setErrorAttributes(Map<String, String> errorAttributes)
  {
    this.errorAttributes = errorAttributes;
  }
  
  public String toString()
  {
    return "ApplicationRuntimeException [status=" + this.status + ", requestId=" + this.requestId + ", errorCode=" + this.errorCode + ", message=" + this.message + ", errorAttributes=" + this.errorAttributes + "]";
  }
}

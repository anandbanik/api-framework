package com.walmart.scm.framework.services.rest.exception.mapper;

import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.walmart.scm.framework.services.rest.exception.ApplicationRuntimeException;
import com.walmart.scm.framework.services.rest.model.Error;
import com.walmart.scm.framework.services.rest.model.Attribute;

@Provider
public class AppRuntimeExceptionMapper
  implements ExceptionMapper<ApplicationRuntimeException>
{
  public Response toResponse(ApplicationRuntimeException sysException)
  {
    int httpStatus = -1;
    Error error = new Error();
    if (null != sysException)
    {
      error.setCode(sysException.getErrorCode());
      error.setMessage(sysException.getMessage());
      httpStatus = sysException.getStatus();
      for (Map.Entry<String, String> attr : sysException.getErrorAttributes().entrySet())
      {
        Attribute a = new Attribute();
        a.setKey((String)attr.getKey());
        a.setValue((String)attr.getValue());
        error.getDetails().add(a);
      }
    }
    else
    {
      error.setCode("UNKNOW_ERROR");
      error.setMessage("Your request could not be served. Please try again later");
    }
    if (httpStatus > 0) {
      return Response.status(httpStatus).entity(error).type("application/json").build();
    }
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).type("application/json").build();
  }
}

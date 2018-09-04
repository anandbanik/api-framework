package com.walmart.scm.framework.services.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.walmart.scm.framework.services.rest.model.Error;
@Provider
public class UnknownExceptionMapper
  implements ExceptionMapper<Exception>
{
  public Response toResponse(Exception exception)
  {
    Error error = new Error();
    error.setCode("UNKNOW_ERROR");
    if (null != exception) {
      error.setMessage(exception.getMessage());
    } else {
      error.setMessage("Your request could not be served. Please try again later");
    }
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).type("application/json").build();
  }
}
package com.walmart.scm.framework.services.rest.exception.mapper;

import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.walmart.scm.framework.services.rest.model.Error;
import com.walmart.scm.framework.services.rest.model.Attribute;

@Provider
public class DataValidationExceptionMapper
  implements ExceptionMapper<ConstraintViolationException>
{
  public Response toResponse(ConstraintViolationException arg0)
  {
    Error error = new Error();
    error.setCode(Response.Status.BAD_REQUEST.toString());
    error.setMessage("Data validation error");
    Set<ConstraintViolation<?>> constraintViolations = arg0.getConstraintViolations();
    for (ConstraintViolation<?> constraintViolation : constraintViolations)
    {
      Path propertyPath = constraintViolation.getPropertyPath();
      String propertyPathWithMethodName = propertyPath.toString();
      
      String propertyName = propertyPathWithMethodName;
      
      String message = constraintViolation.getMessage();
      Attribute a = new Attribute();
      a.setKey(propertyName);
      a.setValue(message);
      
      error.getDetails().add(a);
    }
    return Response.status(Response.Status.BAD_REQUEST).entity(error).type("application/json").build();
  }
}

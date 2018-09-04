package com.walmart.scm.framework.services.rest.util;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import com.walmart.scm.framework.services.rest.annotations.Compress;

@Provider
@Compress
public class FieldsFilterWriterInterceptor
  implements WriterInterceptor
{
  @Context
  private UriInfo uriInfo;
  
  public void setUriInfo(UriInfo uriInfo)
  {
    this.uriInfo = uriInfo;
  }
  
  public void aroundWriteTo(WriterInterceptorContext context)
    throws IOException, WebApplicationException
  {
    if (null != this.uriInfo)
    {
      String fieldsParam = null;
      
      MultivaluedMap<String, String> queryParameters = this.uriInfo.getQueryParameters();
      if ((null != queryParameters) && (queryParameters.size() > 0)) {
        fieldsParam = (String)queryParameters.getFirst("fields");
      }
      if (null != fieldsParam) {
        String[] arrayOfString = fieldsParam.split(",");
      }
    }
    context.proceed();
  }
}

package com.walmart.scm.framework.services.rest.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import com.walmart.scm.framework.services.rest.annotations.Compress;

@Provider
@Compress
public class GZIPWriterInterceptor
  implements WriterInterceptor
{
  @Context
  private HttpHeaders httpHeaders;
  
  public void aroundWriteTo(WriterInterceptorContext context)
    throws IOException, WebApplicationException
  {
    if (null != this.httpHeaders)
    {
      MultivaluedMap<String, String> requestHeaders = this.httpHeaders.getRequestHeaders();
      
      List<String> acceptEncoding = new ArrayList();
      if ((null != requestHeaders) && (requestHeaders.size() > 0)) {
        acceptEncoding = (List)requestHeaders.get("Accept-Encoding");
      }
      for (String s : acceptEncoding) {
        if (s.contains("gzip"))
        {
          MultivaluedMap<String, Object> headers = context.getHeaders();
          headers.add("Content-Encoding", "gzip");
          
          OutputStream outputStream = context.getOutputStream();
          context.setOutputStream(new GZIPOutputStream(outputStream));
          
          break;
        }
      }
    }
    context.proceed();
  }
}

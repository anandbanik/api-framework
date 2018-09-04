package com.walmart.scm.framework.services.rest.service;

import java.io.File;
import java.net.URL;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import com.walmart.scm.framework.services.rest.service.Service;

public class AbstractService
  implements Service
{
  @Context
  ServletContext ctx;
  
  public String ping()
  {
    return "{\"status\" :\"OK\"}";
  }
  
  @GET
  @Path("/swagger/{filename}.{ext:json|yaml}")
  @Produces({"application/octet-stream"})
  public Response swagger(@PathParam("filename") String filename, @PathParam("ext") String ext)
  {
    String _filename = filename + "." + ext;
    File file;
    try
    {
      URL resource = this.ctx.getResource("/" + _filename);
      if (resource == null) {
        return Response.status(Response.Status.NOT_FOUND).entity(_filename + " does not exist!").build();
      }
      file = new File(resource.getFile());
    }
    catch (Exception e)
    {
      return Response.status(Response.Status.NOT_FOUND).entity(_filename + " does not exist!").build();
    }
    return Response.ok(file).type("application/octet-stream").header("content-disposition", "attachment; filename = \"" + _filename + "\"").build();
  }
  
  @GET
  @Path("/application.wadl")
  @Produces({"application/xml"})
  public Response wadl()
  {
    String _filename = "application.wadl";
    File file;
    try
    {
      URL resource = this.ctx.getResource("/" + _filename);
      if (resource == null) {
        return Response.status(Response.Status.NOT_FOUND).entity(_filename + " does not exist!").build();
      }
      file = new File(resource.getFile());
    }
    catch (Exception e)
    {
      return Response.status(Response.Status.NOT_FOUND).entity(_filename + " does not exist!").build();
    }
    return Response.ok(file).type("application/xml").build();
  }
}

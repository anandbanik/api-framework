package com.walmart.scm.framework.services.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public abstract interface Service {
	@GET
	@Path("/ping")
	public abstract String ping();

}

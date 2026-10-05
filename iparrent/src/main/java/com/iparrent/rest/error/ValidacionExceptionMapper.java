package com.iparrent.rest.error;

import com.iparrent.validacion.ValidacionException;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidacionExceptionMapper implements ExceptionMapper<ValidacionException> {

	@Context
	private UriInfo uriInfo;

	@Override
	public Response toResponse(ValidacionException exception) {

		ProblemaHttp problema = new ProblemaHttp("about:blank", Status.BAD_REQUEST.getReasonPhrase(),
				Status.BAD_REQUEST.getStatusCode(), exception.getMessage(), uriInfo.getRequestUri().getPath());

		return Response.status(Status.BAD_REQUEST).type("application/problem+json").entity(problema).build();
	}
}
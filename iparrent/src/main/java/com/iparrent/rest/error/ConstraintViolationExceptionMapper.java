package com.iparrent.rest.error;

import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Context
	private UriInfo uriInfo;

	@Override
	public Response toResponse(ConstraintViolationException exception) {

		String detalle = exception.getConstraintViolations().stream().map(error -> error.getMessage())
				.collect(Collectors.joining("; "));

		ProblemaHttp problema = new ProblemaHttp("about:blank", Status.BAD_REQUEST.getReasonPhrase(),
				Status.BAD_REQUEST.getStatusCode(), detalle, uriInfo.getRequestUri().getPath());

		return Response.status(Status.BAD_REQUEST).type("application/problem+json").entity(problema).build();
	}
}
package com.iparrent.rest.error;

import com.iparrent.excepciones.AlquilerException;
import com.iparrent.excepciones.AlquilerException.Tipo;

import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AlquilerExceptionMapper implements ExceptionMapper<AlquilerException> {

	@Context
	private UriInfo uriInfo;

	@Override
	public Response toResponse(AlquilerException exception) {

		Status status = determinarEstado(exception.getTipo());

		ProblemaHttp problema = new ProblemaHttp("about:blank", status.getReasonPhrase(), status.getStatusCode(),
				exception.getMessage(), uriInfo.getRequestUri().getPath());

		return Response.status(status).type("application/problem+json").entity(problema).build();
	}

	private Status determinarEstado(Tipo tipo) {

		return switch (tipo) {

		case SOLICITUD_INVALIDA -> Status.BAD_REQUEST;

		case RECURSO_NO_ENCONTRADO -> Status.NOT_FOUND;

		case VEHICULO_NO_DISPONIBLE -> Status.CONFLICT;
		};
	}
}
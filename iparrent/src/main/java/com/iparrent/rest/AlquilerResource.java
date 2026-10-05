package com.iparrent.rest;

import java.net.URI;
import java.util.Map;

import com.iparrent.configuracion.ServiciosApplicacion;
import com.iparrent.modelo.Alquiler;
import com.iparrent.negocio.AlquilerNegocio;
import com.iparrent.rest.dto.SolicitudAlquiler;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/alquileres")
@Produces(MediaType.APPLICATION_JSON)
public class AlquilerResource {

	private final AlquilerNegocio negocio = ServiciosApplicacion.getAlquilerNegocio();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alquilar(@NotNull(message = "La solicitud es obligatoria") @Valid SolicitudAlquiler solicitud,
			@Context UriInfo uriInfo) {

		Alquiler alquiler = negocio.alquilar(solicitud.getIdCliente(), solicitud.getIdVehiculo(), solicitud.getDias());

		URI location = uriInfo.getAbsolutePathBuilder().path(alquiler.getId().toString()).build();

		return Response.created(location).entity(alquiler).build();
	}

	@PUT
	@Path("/{id}/devolucion")
	public Response devolver(@PathParam("id") Long id) {

		negocio.devolver(id);

		return Response.ok(Map.of("mensaje", "Devolución registrada")).build();
	}
}
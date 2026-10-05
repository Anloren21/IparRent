package com.iparrent.rest;

import java.math.BigDecimal;
import java.util.Map;

import com.iparrent.configuracion.ServiciosApplicacion;
import com.iparrent.negocio.AlquilerNegocio;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/informes")
@Produces(MediaType.APPLICATION_JSON)
public class InformeResource {

	private final AlquilerNegocio negocio = ServiciosApplicacion.getAlquilerNegocio();

	@GET
	@Path("/facturacion")
	public Map<String, BigDecimal> obtenerFacturacion() {

		return negocio.informeFacturacion();
	}
}
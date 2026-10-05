package com.iparrent.rest;

import java.util.List;
import java.util.stream.StreamSupport;

import com.iparrent.accesodatos.DaoVehiculo;
import com.iparrent.configuracion.ServiciosApplicacion;
import com.iparrent.excepciones.AlquilerException;
import com.iparrent.excepciones.AlquilerException.Tipo;
import com.iparrent.modelo.Vehiculo;
import com.iparrent.negocio.AlquilerNegocio;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/vehiculos")
@Produces(MediaType.APPLICATION_JSON)
public class VehiculoResource {

	private final DaoVehiculo daoVehiculo = ServiciosApplicacion.getDaoVehiculo();

	private final AlquilerNegocio negocio = ServiciosApplicacion.getAlquilerNegocio();

	@GET
	public List<Vehiculo> listar(@QueryParam("disponibles") @DefaultValue("false") boolean disponibles) {

		if (disponibles) {
			return negocio.listarDisponibles();
		}

		return StreamSupport.stream(daoVehiculo.obtenerTodos().spliterator(), false).toList();
	}

	@GET
	@Path("/{id}")
	public Vehiculo obtenerPorId(@PathParam("id") Long id) {

		return daoVehiculo.obtenerPorId(id).orElseThrow(
				() -> new AlquilerException(Tipo.RECURSO_NO_ENCONTRADO, "No existe el vehículo con id " + id));
	}
}
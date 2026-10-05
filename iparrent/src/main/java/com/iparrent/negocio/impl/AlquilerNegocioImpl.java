package com.iparrent.negocio.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.iparrent.accesodatos.DaoAlquiler;
import com.iparrent.accesodatos.DaoCliente;
import com.iparrent.accesodatos.DaoVehiculo;
import com.iparrent.excepciones.AlquilerException;
import com.iparrent.excepciones.AlquilerException.Tipo;
import com.iparrent.modelo.Alquiler;
import com.iparrent.modelo.Cliente;
import com.iparrent.modelo.Vehiculo;
import com.iparrent.negocio.AlquilerNegocio;
import com.iparrent.validacion.Validador;

public class AlquilerNegocioImpl implements AlquilerNegocio {

	private final DaoCliente daoCliente;
	private final DaoVehiculo daoVehiculo;
	private final DaoAlquiler daoAlquiler;
	private final Validador validador;

	public AlquilerNegocioImpl(DaoCliente daoCliente, DaoVehiculo daoVehiculo, DaoAlquiler daoAlquiler,
			Validador validador) {

		this.daoCliente = daoCliente;
		this.daoVehiculo = daoVehiculo;
		this.daoAlquiler = daoAlquiler;
		this.validador = validador;
	}

	@Override
	public List<Vehiculo> listarDisponibles() {
		return daoVehiculo.obtenerDisponibles();
	}

	@Override
	public Alquiler alquilar(Long idCliente, Long idVehiculo, int dias) {

		if (idCliente == null) {
			throw new AlquilerException(Tipo.SOLICITUD_INVALIDA, "El id del cliente es obligatorio");
		}

		if (idVehiculo == null) {
			throw new AlquilerException(Tipo.SOLICITUD_INVALIDA, "El id del vehículo es obligatorio");
		}

		Cliente cliente = daoCliente.obtenerPorId(idCliente).orElseThrow(
				() -> new AlquilerException(Tipo.RECURSO_NO_ENCONTRADO, "No existe el cliente con id " + idCliente));

		Vehiculo vehiculo = daoVehiculo.obtenerPorId(idVehiculo).orElseThrow(
				() -> new AlquilerException(Tipo.RECURSO_NO_ENCONTRADO, "No existe el vehículo con id " + idVehiculo));

		if (!vehiculo.isDisponible()) {
			throw new AlquilerException(Tipo.VEHICULO_NO_DISPONIBLE,
					"El vehículo con id " + idVehiculo + " no está disponible");
		}

		Alquiler alquiler = new Alquiler(null, cliente, vehiculo, LocalDate.now(), dias);

		validador.validar(cliente);
		validador.validar(vehiculo);
		validador.validar(alquiler);

		BigDecimal importe = vehiculo.calcularPrecio(dias);

		alquiler.setImporte(importe);

		vehiculo.setDisponible(false);

		daoVehiculo.modificar(vehiculo);

		return daoAlquiler.insertar(alquiler);
	}

	@Override
	public void devolver(Long idAlquiler) {

		if (idAlquiler == null) {
			throw new AlquilerException(Tipo.SOLICITUD_INVALIDA, "El id del alquiler es obligatorio");
		}

		Alquiler alquiler = daoAlquiler.obtenerPorId(idAlquiler).orElseThrow(
				() -> new AlquilerException(Tipo.RECURSO_NO_ENCONTRADO, "No existe el alquiler con id " + idAlquiler));

		Vehiculo vehiculo = alquiler.getVehiculo();

		vehiculo.setDisponible(true);

		daoVehiculo.modificar(vehiculo);
	}

	@Override
	public Map<String, BigDecimal> informeFacturacion() {

		return StreamSupport.stream(daoAlquiler.obtenerTodos().spliterator(), false)
				.collect(Collectors.groupingBy(alquiler -> alquiler.getVehiculo().getTipo(),

						Collectors.reducing(BigDecimal.ZERO, Alquiler::getImporte, BigDecimal::add)));
	}
}
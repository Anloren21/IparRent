package com.iparrent.negocio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.iparrent.modelo.Alquiler;
import com.iparrent.modelo.Vehiculo;

public interface AlquilerNegocio {

	List<Vehiculo> listarDisponibles();

	Alquiler alquilar(Long idCliente, Long idVehiculo, int dias);

	void devolver(Long idAlquiler);

	Map<String, BigDecimal> informeFacturacion();
}
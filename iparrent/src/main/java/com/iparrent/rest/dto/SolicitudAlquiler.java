package com.iparrent.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SolicitudAlquiler {

	@NotNull(message = "El id del cliente es obligatorio")
	private Long idCliente;

	@NotNull(message = "El id del vehículo es obligatorio")
	private Long idVehiculo;

	@NotNull(message = "El número de días es obligatorio")
	@Min(value = 1, message = "El alquiler debe durar al menos un día")
	@Max(value = 30, message = "El alquiler no puede superar los 30 días")
	private Integer dias;

	public SolicitudAlquiler() {
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}
}
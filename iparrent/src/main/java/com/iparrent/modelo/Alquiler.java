package com.iparrent.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Alquiler {

	private Long id;

	@NotNull(message = "El cliente es obligatorio")
	private Cliente cliente;

	@NotNull(message = "El vehículo es obligatorio")
	private Vehiculo vehiculo;

	@NotNull(message = "La fecha de inicio es obligatoria")
	private LocalDate fechaInicio;

	@Min(value = 1, message = "El alquiler debe durar al menos un día")
	@Max(value = 30, message = "El alquiler no puede superar los 30 días")
	private int dias;

	private BigDecimal importe;

	public Alquiler() {
	}

	public Alquiler(Long id, Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, int dias) {

		this.id = id;
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.fechaInicio = fechaInicio;
		this.dias = dias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Alquiler [id=" + id + ", cliente=" + cliente + ", vehiculo=" + vehiculo + ", fechaInicio=" + fechaInicio
				+ ", dias=" + dias + ", importe=" + importe + "]";
	}
}
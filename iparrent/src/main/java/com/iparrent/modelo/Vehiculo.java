package com.iparrent.modelo;

import java.math.BigDecimal;

public abstract class Vehiculo {
	private Long id;
	private String matricula;
	private String marca;
	private String modelo;
	private BigDecimal precioDia;
	private boolean disponible;

	protected Vehiculo(Long id, String matricula, String marca, String modelo, BigDecimal precioDia,
			boolean disponible) {
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.precioDia = precioDia;
		this.disponible = disponible;
	}

	public abstract BigDecimal calcularPrecio(int dias);

	public String getTipo() {
		return getClass().getSimpleName();
	}
}

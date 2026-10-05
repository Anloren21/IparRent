package com.iparrent.modelo;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public abstract class Vehiculo {
	// Definiciñon de la información y sus validaciones
	private Long id;

	@NotBlank(message = "La matrículo es obligatoria")
	@Pattern(regexp = "^\\d{4}[A-Z]{3}$", message = "La matrícula debe tener el formato 1234ABC")
	private String matricula;

	@NotBlank(message = "La matrículo es obligatoria")
	private String marca;

	@NotBlank(message = "La matrículo es obligatoria")
	private String modelo;

	@NotNull(message = "El precio por día es obligatorio")
	@Positive(message = "El precio por día debe ser mayor que cero")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getPrecioDia() {
		return precioDia;
	}

	public void setPrecioDia(BigDecimal precioDia) {
		this.precioDia = precioDia;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public abstract BigDecimal calcularPrecio(int dias);

	public String getTipo() {
		return getClass().getSimpleName();
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) { // comprueba si estamos comparando exactamente el mismo objeto.
			return true;
		}

		if (!(obj instanceof Vehiculo otro)) { // comprueba que el objeto sea algún tipo de Vehiculo
			return false;
		}

		return id != null && Objects.equals(id, otro.id); // la identidad del vehículo depende únicamente de su id
	}

	@Override
	public final int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	public String toString() {
		return "Vehiculo [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo
				+ ", precioDia=" + precioDia + ", disponible=" + disponible + "]";
	}
}

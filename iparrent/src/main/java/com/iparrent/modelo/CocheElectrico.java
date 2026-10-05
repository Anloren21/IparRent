package com.iparrent.modelo;

import java.math.BigDecimal;

public class CocheElectrico extends Coche implements Electrico {
	private int autonomiaKm;

	public CocheElectrico(Long id, String matricula, String marca, String modelo, BigDecimal precioDia,
			boolean disponible, int plazas, int autonomiaKm) {

		super(id, matricula, marca, modelo, precioDia, disponible, plazas);

		this.autonomiaKm = autonomiaKm;
	}

	@Override
	public BigDecimal calcularPrecio(int dias) {

		BigDecimal precioCoche = super.calcularPrecio(dias);

		return precioCoche.multiply(new BigDecimal("0.95"));
	}

	@Override
	public int getAutonomiaKm() {
		return autonomiaKm;
	}

	public void setAutonomiaKm(int autonomiaKm) {
		this.autonomiaKm = autonomiaKm;
	}
}

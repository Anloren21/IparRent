package com.iparrent.modelo;

import java.math.BigDecimal;

public class Furgoneta extends Vehiculo {

	 private double cargaMaximaKg;

	    private static final BigDecimal COSTE_LIMPIEZA =
	            new BigDecimal("15.00");

	    public Furgoneta(
	            Long id,
	            String matricula,
	            String marca,
	            String modelo,
	            BigDecimal precioDia,
	            boolean disponible,
	            double cargaMaximaKg) {

	        super(id, matricula, marca, modelo, precioDia, disponible);

	        this.cargaMaximaKg = cargaMaximaKg;
	    }

	    @Override
	    public BigDecimal calcularPrecio(int dias) {

	        BigDecimal precioAlquiler =
	                getPrecioDia().multiply(BigDecimal.valueOf(dias));

	        return precioAlquiler.add(COSTE_LIMPIEZA);
	    }

	    public double getCargaMaximaKg() {
	        return cargaMaximaKg;
	    }

	    public void setCargaMaximaKg(double cargaMaximaKg) {
	        this.cargaMaximaKg = cargaMaximaKg;
	    }

}

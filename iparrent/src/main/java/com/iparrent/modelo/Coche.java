package com.iparrent.modelo;

import java.math.BigDecimal;

public class Coche extends Vehiculo {
	private int plazas;
	
	public Coche(Long id, String matricula, String marca, String modelo, BigDecimal precioDia,
			boolean disponible, int plazas) {
		super (id, matricula, marca, modelo, precioDia, disponible);
		
		this.plazas = plazas;
	}
	
	@Override
	public BigDecimal calcularPrecio(int dias) {
		BigDecimal total =
                getPrecioDia().multiply(BigDecimal.valueOf(dias));

        if (dias >= 7) { //esstablecer un descuento tras 7 dias de alquiles -10%
            total = total.multiply(new BigDecimal("0.90"));
        }

        return total;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

}

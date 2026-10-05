package com.iparrent.modelo;

import java.math.BigDecimal;

public class Moto extends Vehiculo {
    private int cilindrada;

    public Moto(
            Long id,
            String matricula,
            String marca,
            String modelo,
            BigDecimal precioDia,
            boolean disponible,
            int cilindrada) {

        super(id, matricula, marca, modelo, precioDia, disponible);

        this.cilindrada = cilindrada;
    }

    @Override
    public BigDecimal calcularPrecio(int dias) {

        BigDecimal total =
                getPrecioDia().multiply(BigDecimal.valueOf(dias));

        if (cilindrada > 500) {
            total = total.multiply(new BigDecimal("1.20"));
        }

        return total;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

}

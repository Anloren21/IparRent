package com.iparrent.pruebas;

import java.math.BigDecimal;
import java.util.List;

import com.iparrent.modelo.Coche;
import com.iparrent.modelo.CocheElectrico;
import com.iparrent.modelo.Furgoneta;
import com.iparrent.modelo.Moto;
import com.iparrent.modelo.Vehiculo;

public class PruebaPolimorfismo {

    public static void main(String[] args) {

        List<Vehiculo> vehiculos = List.of(

            new Coche(
                1L,
                "1234ABC",
                "Toyota",
                "Corolla",
                new BigDecimal("50.00"),
                true,
                5
            ),

            new Furgoneta(
                2L,
                "2345BCD",
                "Ford",
                "Transit",
                new BigDecimal("70.00"),
                true,
                1200
            ),

            new Moto(
                3L,
                "3456CDE",
                "Honda",
                "CB600",
                new BigDecimal("30.00"),
                true,
                600
            ),

            new CocheElectrico(
                4L,
                "4567DEF",
                "Tesla",
                "Model 3",
                new BigDecimal("60.00"),
                true,
                5,
                450
            )
        );

        int dias = 8;

        System.out.println("PRECIOS PARA " + dias + " DÍAS");
        System.out.println("--------------------------------");

        for (Vehiculo vehiculo : vehiculos) {

            BigDecimal precio =
                    vehiculo.calcularPrecio(dias);

            System.out.println(
                vehiculo.getTipo()
                + " - "
                + vehiculo.getMarca()
                + " "
                + vehiculo.getModelo()
                + ": "
                + precio
                + " €"
            );
        }
    }
}
package com.iparrent.pruebas;

import java.math.BigDecimal;
import java.util.Map;

import com.iparrent.accesodatos.DaoAlquiler;
import com.iparrent.accesodatos.DaoCliente;
import com.iparrent.accesodatos.DaoVehiculo;
import com.iparrent.configuracion.ContenedorDependencias;
import com.iparrent.excepciones.AlquilerException;
import com.iparrent.modelo.Alquiler;
import com.iparrent.negocio.AlquilerNegocio;
import com.iparrent.negocio.impl.AlquilerNegocioImpl;
import com.iparrent.validacion.ValidacionException;
import com.iparrent.validacion.Validador;
import com.iparrent.validacion.ValidadorJakarta;

public class PruebaNegocio {

	public static void main(String[] args) {

		DaoVehiculo daoVehiculo = ContenedorDependencias.obtener("dao.vehiculo", DaoVehiculo.class);

		DaoCliente daoCliente = ContenedorDependencias.obtener("dao.cliente", DaoCliente.class);

		DaoAlquiler daoAlquiler = ContenedorDependencias.obtener("dao.alquiler", DaoAlquiler.class);

		Validador validador = new ValidadorJakarta();

		AlquilerNegocio negocio = new AlquilerNegocioImpl(daoCliente, daoVehiculo, daoAlquiler, validador);

		System.out.println("DISPONIBLES AL INICIO");
		System.out.println("---------------------");

		negocio.listarDisponibles().forEach(System.out::println);

		System.out.println();

		System.out.println("ALQUILER");
		System.out.println("--------");

		Alquiler alquiler = negocio.alquilar(1L, 1L, 8);

		System.out.println("Alquiler creado: " + alquiler);

		System.out.println();

		System.out.println("DISPONIBLES DESPUÉS DEL ALQUILER");
		System.out.println("-------------------------------");

		negocio.listarDisponibles().forEach(System.out::println);

		System.out.println();

		System.out.println("INTENTO DE DOBLE ALQUILER");
		System.out.println("-------------------------");

		try {

			negocio.alquilar(2L, 1L, 3);

		} catch (AlquilerException e) {

			System.out.println("Error esperado: " + e.getMessage());
		}

		System.out.println();

		System.out.println("VALIDACIÓN DE DÍAS");
		System.out.println("------------------");

		try {

			negocio.alquilar(1L, 2L, 35);

		} catch (ValidacionException e) {

			System.out.println("Error esperado: " + e.getMessage());
		}

		System.out.println();

		System.out.println("DEVOLUCIÓN");
		System.out.println("----------");

		negocio.devolver(alquiler.getId());

		System.out.println("Vehículo devuelto correctamente");

		System.out.println();

		System.out.println("INFORME DE FACTURACIÓN");
		System.out.println("----------------------");

		Map<String, BigDecimal> informe = negocio.informeFacturacion();

		informe.forEach((tipo, total) -> System.out.println(tipo + ": " + total + " €"));
	}
}
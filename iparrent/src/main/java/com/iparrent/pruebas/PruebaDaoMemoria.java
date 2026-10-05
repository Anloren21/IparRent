package com.iparrent.pruebas;

import com.iparrent.accesodatos.DaoCliente;
import com.iparrent.accesodatos.DaoVehiculo;
import com.iparrent.configuracion.ContenedorDependencias;
import com.iparrent.modelo.Vehiculo;

public class PruebaDaoMemoria {

	public static void main(String[] args) {

		DaoVehiculo daoVehiculo = ContenedorDependencias.obtener("dao.vehiculo", DaoVehiculo.class);

		DaoCliente daoCliente = ContenedorDependencias.obtener("dao.cliente", DaoCliente.class);

		System.out.println("IMPLEMENTACIONES CARGADAS");
		System.out.println("--------------------------");

		System.out.println("Vehículos: " + daoVehiculo.getClass().getSimpleName());

		System.out.println("Clientes: " + daoCliente.getClass().getSimpleName());

		System.out.println();

		System.out.println("TODOS LOS VEHÍCULOS");
		System.out.println("-------------------");

		daoVehiculo.obtenerTodos().forEach(System.out::println);

		System.out.println();

		System.out.println("VEHÍCULOS DISPONIBLES");
		System.out.println("---------------------");

		daoVehiculo.obtenerDisponibles().forEach(System.out::println);

		System.out.println();

		System.out.println("BUSCAR MARCA TOYOTA");
		System.out.println("-------------------");

		daoVehiculo.buscarPorMarca("Toyota").forEach(System.out::println);

		System.out.println();

		System.out.println("BUSCAR VEHÍCULO ID 1");
		System.out.println("--------------------");

		daoVehiculo.obtenerPorId(1L).ifPresentOrElse(System.out::println,
				() -> System.out.println("Vehículo no encontrado"));

		System.out.println();

		System.out.println("BUSCAR VEHÍCULO ID 999");
		System.out.println("----------------------");

		daoVehiculo.obtenerPorId(999L).ifPresentOrElse(System.out::println,
				() -> System.out.println("Vehículo no encontrado"));

		System.out.println();

		System.out.println("TOTAL VEHÍCULOS");
		System.out.println("---------------");

		int total = 0;

		for (Vehiculo vehiculo : daoVehiculo.obtenerTodos()) {

			total++;
		}

		System.out.println(total);
	}
}
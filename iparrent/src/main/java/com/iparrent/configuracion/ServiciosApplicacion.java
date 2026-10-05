package com.iparrent.configuracion;

import com.iparrent.accesodatos.DaoAlquiler;
import com.iparrent.accesodatos.DaoCliente;
import com.iparrent.accesodatos.DaoVehiculo;
import com.iparrent.negocio.AlquilerNegocio;
import com.iparrent.negocio.impl.AlquilerNegocioImpl;
import com.iparrent.validacion.Validador;
import com.iparrent.validacion.ValidadorJakarta;

public final class ServiciosApplicacion {

	private static final DaoVehiculo DAO_VEHICULO = ContenedorDependencias.obtener("dao.vehiculo", DaoVehiculo.class);

	private static final DaoCliente DAO_CLIENTE = ContenedorDependencias.obtener("dao.cliente", DaoCliente.class);

	private static final DaoAlquiler DAO_ALQUILER = ContenedorDependencias.obtener("dao.alquiler", DaoAlquiler.class);

	private static final Validador VALIDADOR = new ValidadorJakarta();

	private static final AlquilerNegocio ALQUILER_NEGOCIO = new AlquilerNegocioImpl(DAO_CLIENTE, DAO_VEHICULO,
			DAO_ALQUILER, VALIDADOR);

	public static DaoVehiculo getDaoVehiculo() {
		return DAO_VEHICULO;
	}

	public static AlquilerNegocio getAlquilerNegocio() {
		return ALQUILER_NEGOCIO;
	}
}
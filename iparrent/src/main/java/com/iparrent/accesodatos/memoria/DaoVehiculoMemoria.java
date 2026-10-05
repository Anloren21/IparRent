package com.iparrent.accesodatos.memoria;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import com.iparrent.accesodatos.AccesoDatosException;
import com.iparrent.accesodatos.DaoVehiculo;
import com.iparrent.modelo.Coche;
import com.iparrent.modelo.CocheElectrico;
import com.iparrent.modelo.Furgoneta;
import com.iparrent.modelo.Moto;
import com.iparrent.modelo.Vehiculo;

public class DaoVehiculoMemoria implements DaoVehiculo {

	private final TreeMap<Long, Vehiculo> vehiculos = new TreeMap<>();

	private long siguienteId = 1L;

	public DaoVehiculoMemoria() {
		cargarDatosIniciales();
	}

	private void cargarDatosIniciales() {

		insertar(new Coche(null, "1234ABC", "Toyota", "Corolla", new BigDecimal("50.00"), true, 5));

		insertar(new Furgoneta(null, "2345BCD", "Ford", "Transit", new BigDecimal("70.00"), true, 1200));

		insertar(new Moto(null, "3456CDE", "Honda", "CB600", new BigDecimal("30.00"), true, 600));

		insertar(new CocheElectrico(null, "4567DEF", "Tesla", "Model 3", new BigDecimal("60.00"), true, 5, 450));
	}

	@Override
	public Iterable<Vehiculo> obtenerTodos() {
		return List.copyOf(vehiculos.values());
	}

	@Override
	public Optional<Vehiculo> obtenerPorId(Long id) {
		return Optional.ofNullable(vehiculos.get(id));
	}

	@Override
	public Vehiculo insertar(Vehiculo vehiculo) {

		if (vehiculo == null) {
			throw new AccesoDatosException("No se puede insertar un vehículo nulo");
		}

		if (vehiculo.getId() == null) {
			vehiculo.setId(siguienteId++);
		} else {
			siguienteId = Math.max(siguienteId, vehiculo.getId() + 1);
		}

		if (vehiculos.containsKey(vehiculo.getId())) {
			throw new AccesoDatosException("Ya existe un vehículo con id " + vehiculo.getId());
		}

		vehiculos.put(vehiculo.getId(), vehiculo);

		return vehiculo;
	}

	@Override
	public Vehiculo modificar(Vehiculo vehiculo) {

		if (vehiculo == null || vehiculo.getId() == null) {
			throw new AccesoDatosException("El vehículo debe tener un id para modificarse");
		}

		if (!vehiculos.containsKey(vehiculo.getId())) {
			throw new AccesoDatosException("No existe el vehículo con id " + vehiculo.getId());
		}

		vehiculos.put(vehiculo.getId(), vehiculo);

		return vehiculo;
	}

	@Override
	public void borrar(Long id) {

		Vehiculo eliminado = vehiculos.remove(id);

		if (eliminado == null) {
			throw new AccesoDatosException("No existe el vehículo con id " + id);
		}
	}

	@Override
	public List<Vehiculo> obtenerDisponibles() {

		return vehiculos.values().stream().filter(Vehiculo::isDisponible).toList();
	}

	@Override
	public List<Vehiculo> buscarPorMarca(String marca) {

		if (marca == null) {
			return List.of();
		}

		return vehiculos.values().stream().filter(v -> v.getMarca().equalsIgnoreCase(marca)).toList();
	}
}
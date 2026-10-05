package com.iparrent.accesodatos.memoria;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import com.iparrent.accesodatos.AccesoDatosException;
import com.iparrent.accesodatos.DaoAlquiler;
import com.iparrent.modelo.Alquiler;

public class DaoAlquilerMemoria implements DaoAlquiler {

	private final TreeMap<Long, Alquiler> alquileres = new TreeMap<>();

	private long siguienteId = 1L;

	public DaoAlquilerMemoria() {
	}

	@Override
	public Iterable<Alquiler> obtenerTodos() {
		return List.copyOf(alquileres.values());
	}

	@Override
	public Optional<Alquiler> obtenerPorId(Long id) {
		return Optional.ofNullable(alquileres.get(id));
	}

	@Override
	public Alquiler insertar(Alquiler alquiler) {

		if (alquiler == null) {
			throw new AccesoDatosException("No se puede insertar un alquiler nulo");
		}

		if (alquiler.getId() == null) {
			alquiler.setId(siguienteId++);
		} else {
			siguienteId = Math.max(siguienteId, alquiler.getId() + 1);
		}

		if (alquileres.containsKey(alquiler.getId())) {
			throw new AccesoDatosException("Ya existe un alquiler con id " + alquiler.getId());
		}

		alquileres.put(alquiler.getId(), alquiler);

		return alquiler;
	}

	@Override
	public Alquiler modificar(Alquiler alquiler) {

		if (alquiler == null || alquiler.getId() == null) {
			throw new AccesoDatosException("El alquiler debe tener un id para modificarse");
		}

		if (!alquileres.containsKey(alquiler.getId())) {
			throw new AccesoDatosException("No existe el alquiler con id " + alquiler.getId());
		}

		alquileres.put(alquiler.getId(), alquiler);

		return alquiler;
	}

	@Override
	public void borrar(Long id) {

		Alquiler eliminado = alquileres.remove(id);

		if (eliminado == null) {
			throw new AccesoDatosException("No existe el alquiler con id " + id);
		}
	}

	@Override
	public List<Alquiler> buscarPorCliente(Long idCliente) {

		if (idCliente == null) {
			return List.of();
		}

		return alquileres.values().stream().filter(a -> a.getCliente() != null)
				.filter(a -> a.getCliente().getId() != null).filter(a -> a.getCliente().getId().equals(idCliente))
				.toList();
	}
}
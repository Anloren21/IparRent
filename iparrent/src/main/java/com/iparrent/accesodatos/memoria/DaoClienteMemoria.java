package com.iparrent.accesodatos.memoria;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import com.iparrent.accesodatos.AccesoDatosException;
import com.iparrent.accesodatos.DaoCliente;
import com.iparrent.modelo.Cliente;

public class DaoClienteMemoria implements DaoCliente {

	private final TreeMap<Long, Cliente> clientes = new TreeMap<>();

	private long siguienteId = 1L;

	public DaoClienteMemoria() {
		cargarDatosIniciales();
	}

	private void cargarDatosIniciales() {

		insertar(new Cliente(null, "Ana García", "ana@iparrent.es", "12345678A"));

		insertar(new Cliente(null, "Carlos López", "carlos@iparrent.es", "87654321B"));
	}

	@Override
	public Iterable<Cliente> obtenerTodos() {
		return List.copyOf(clientes.values());
	}

	@Override
	public Optional<Cliente> obtenerPorId(Long id) {
		return Optional.ofNullable(clientes.get(id));
	}

	@Override
	public Cliente insertar(Cliente cliente) {

		if (cliente == null) {
			throw new AccesoDatosException("No se puede insertar un cliente nulo");
		}

		if (cliente.getId() == null) {
			cliente.setId(siguienteId++);
		} else {
			siguienteId = Math.max(siguienteId, cliente.getId() + 1);
		}

		if (clientes.containsKey(cliente.getId())) {
			throw new AccesoDatosException("Ya existe un cliente con id " + cliente.getId());
		}

		clientes.put(cliente.getId(), cliente);

		return cliente;
	}

	@Override
	public Cliente modificar(Cliente cliente) {

		if (cliente == null || cliente.getId() == null) {
			throw new AccesoDatosException("El cliente debe tener un id para modificarse");
		}

		if (!clientes.containsKey(cliente.getId())) {
			throw new AccesoDatosException("No existe el cliente con id " + cliente.getId());
		}

		clientes.put(cliente.getId(), cliente);

		return cliente;
	}

	@Override
	public void borrar(Long id) {

		Cliente eliminado = clientes.remove(id);

		if (eliminado == null) {
			throw new AccesoDatosException("No existe el cliente con id " + id);
		}
	}
}
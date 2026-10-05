package com.iparrent.accesodatos;

import java.util.List;

import com.iparrent.modelo.Vehiculo;

public interface DaoVehiculo extends Dao<Vehiculo> {

    List<Vehiculo> obtenerDisponibles();

    List<Vehiculo> buscarPorMarca(String marca);
}
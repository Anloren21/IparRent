package com.iparrent.accesodatos;

import java.util.List;

import com.iparrent.modelo.Alquiler;

public interface DaoAlquiler extends Dao<Alquiler> {

    List<Alquiler> buscarPorCliente(Long idCliente);
}
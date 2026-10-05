package com.iparrent.accesodatos;

public class AccesoDatosException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccesoDatosException(String mensaje) {
		super(mensaje);
	}

	public AccesoDatosException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}

package com.iparrent.excepciones;

public class AlquilerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public enum Tipo {

		SOLICITUD_INVALIDA,

		RECURSO_NO_ENCONTRADO,

		VEHICULO_NO_DISPONIBLE
	}

	private final Tipo tipo;

	public AlquilerException(Tipo tipo, String mensaje) {

		super(mensaje);

		this.tipo = tipo;
	}

	public AlquilerException(String mensaje) {

		this(Tipo.SOLICITUD_INVALIDA, mensaje);
	}

	public Tipo getTipo() {
		return tipo;
	}
}
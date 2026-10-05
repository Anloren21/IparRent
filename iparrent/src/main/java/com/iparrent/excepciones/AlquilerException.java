package com.iparrent.excepciones;

public class AlquilerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlquilerException(String mensaje) {
        super(mensaje);
    }

    public AlquilerException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
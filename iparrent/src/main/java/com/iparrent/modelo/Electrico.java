package com.iparrent.modelo;

public interface Electrico {
	int getAutonomiaKm();

    default boolean esSoloUrbano() {
        return getAutonomiaKm() < 200;
    }
}

package com.iparrent.configuracion;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ContenedorDependencias {

	private static final Properties PROPIEDADES = new Properties();

	private static final Map<String, Object> INSTANCIAS = new HashMap<>();

	static {

		try (InputStream entrada = ContenedorDependencias.class.getClassLoader()
				.getResourceAsStream("aplicacion.properties")) {

			if (entrada == null) {
				throw new IllegalStateException("No se encuentra aplicacion.properties");
			}

			PROPIEDADES.load(entrada);

		} catch (IOException e) {

			throw new IllegalStateException("No se pudo cargar aplicacion.properties", e);
		}
	}

	private ContenedorDependencias() {
	}

	public static <T> T obtener(String clave, Class<T> tipo) {

		Object instancia = INSTANCIAS.computeIfAbsent(clave, ContenedorDependencias::crearInstancia);

		return tipo.cast(instancia);
	}

	private static Object crearInstancia(String clave) {

		String nombreClase = PROPIEDADES.getProperty(clave);

		if (nombreClase == null || nombreClase.isBlank()) {

			throw new IllegalStateException("No existe configuración para " + clave);
		}

		try {

			Class<?> clase = Class.forName(nombreClase);

			return clase.getConstructor().newInstance();

		} catch (ReflectiveOperationException e) {

			throw new IllegalStateException("No se pudo crear la implementación " + nombreClase, e);
		}
	}
}

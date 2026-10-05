package com.iparrent.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.validation.ValidationFeature;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class AplicacionRest extends ResourceConfig {

	public AplicacionRest() {

		packages(true, "com.iparrent.rest");

		register(JacksonFeature.class);
		register(JacksonConfig.class);
		register(ValidationFeature.class);
	}
}
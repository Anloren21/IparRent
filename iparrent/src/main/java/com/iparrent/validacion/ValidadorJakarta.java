package com.iparrent.validacion;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ValidadorJakarta implements Validador {

	private final Validator validator;

	public ValidadorJakarta() {

		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Override
	public <T> void validar(T objeto) {

		if (objeto == null) {
			throw new ValidacionException("No se puede validar un objeto nulo");
		}

		Set<ConstraintViolation<T>> errores = validator.validate(objeto);

		if (!errores.isEmpty()) {

			String mensaje = errores.stream().map(error -> error.getPropertyPath() + ": " + error.getMessage())
					.collect(Collectors.joining("; "));

			throw new ValidacionException(mensaje);
		}
	}
}
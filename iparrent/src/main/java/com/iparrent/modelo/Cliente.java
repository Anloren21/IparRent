package com.iparrent.modelo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Cliente {
	private Long id;

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El formato del email no es válido")
	private String email;

	@NotBlank(message = "El DNI es obligatorio")
	@Pattern(regexp = "^\\d{8}[A-Z]$", message = "El DNI debe contener 8 dígitos y una letra mayúscula")
	private String dni;

	public Cliente() {
	}

	public Cliente(Long id, String nombre, String email, String dni) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + ", dni=" + dni + "]";
	}
}

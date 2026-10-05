<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>

<!doctype html>
<html lang="es" class="h-100">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>IparRent | Alquiler de Vehículos</title>
	<base href="${pageContext.request.contextPath}/">

	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-icons.min.css">
	<link rel="stylesheet" href="css/iparrent.css">
</head>

<body class="h-100 d-flex flex-column">

<nav class="navbar navbar-expand-lg bg-dark sticky-top" data-bs-theme="dark">
	<div class="container-fluid">

		<a class="navbar-brand d-flex align-items-center gap-2" href="index.jsp">
			<img src="images/logo-iparrent.png" alt="IparRent" width="42" height="42">
			<span><strong>IparRent</strong> <small class="d-none d-md-inline text-secondary">| Alquiler de Vehículos</small></span>
		</a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarIparRent" aria-controls="navbarIparRent"
			aria-expanded="false" aria-label="Mostrar navegación">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarIparRent">

			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="index.jsp"><i class="bi bi-house-door-fill"></i> Principal</a></li>
				<li class="nav-item"><a class="nav-link" href="vehiculos.jsp"><i class="bi bi-car-front-fill"></i> Vehículos</a></li>
				<li class="nav-item"><a class="nav-link" href="alquileres.jsp"><i class="bi bi-calendar-check"></i> Alquileres</a></li>
				<li class="nav-item"><a class="nav-link" href="informes.jsp"><i class="bi bi-bar-chart-fill"></i> Informes</a></li>
			</ul>

			<form class="d-flex me-lg-3" role="search" action="vehiculos.jsp" method="get">
				<input name="texto" class="form-control me-2" type="search" placeholder="Buscar vehículo">
				<button class="btn btn-outline-light" type="submit"><i class="bi bi-search"></i> Buscar</button>
			</form>

			<ul class="navbar-nav ms-auto">
				<li class="nav-item">
					<a class="nav-link" href="alquileres.jsp"><i class="bi bi-key-fill"></i> Nuevo alquiler</a>
				</li>
			</ul>

		</div>
	</div>
</nav>

<c:if test="${alerta != null}">
	<div class="container mt-3">
		<div class="alert alert-${alerta.tipo} alert-dismissible fade show" role="alert">
			${alerta.mensaje}
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
		</div>
	</div>
</c:if>
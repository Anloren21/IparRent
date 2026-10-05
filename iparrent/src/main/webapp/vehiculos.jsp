<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ include file="/includes/cabecera.jsp"%>

<main class="container py-5">

	<div class="d-flex flex-wrap justify-content-between align-items-center mb-4">
		<div>
			<h1 class="fw-bold">Vehículos</h1>
			<p class="text-secondary mb-0">Consulta el catálogo y la disponibilidad actual.</p>
		</div>

		<div class="form-check form-switch mt-3 mt-md-0">
			<input class="form-check-input" type="checkbox" id="soloDisponibles">
			<label class="form-check-label" for="soloDisponibles">Solo disponibles</label>
		</div>
	</div>

	<div id="vehiculosContainer" class="row g-4">
		<div class="col-12 text-secondary">Cargando vehículos...</div>
	</div>

</main>

<%@ include file="/includes/pie.jsp"%>
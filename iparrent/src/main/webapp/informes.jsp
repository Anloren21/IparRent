<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ include file="/includes/cabecera.jsp"%>

<main class="container py-5">

	<div class="d-flex flex-wrap justify-content-between align-items-center mb-4">
		<div>
			<h1 class="fw-bold">Informe de facturación</h1>
			<p class="text-secondary mb-0">Facturación acumulada por tipo de vehículo.</p>
		</div>

		<button id="btnInforme" class="btn btn-primary mt-3 mt-md-0">
			<i class="bi bi-arrow-clockwise"></i> Actualizar
		</button>
	</div>

	<div id="informeContainer" class="row g-4">
		<div class="col-12 text-secondary">Cargando informe...</div>
	</div>

</main>

<%@ include file="/includes/pie.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ include file="/includes/cabecera.jsp"%>

<main>
	<section class="hero-iparrent">
		<div class="container py-5">
			<div class="row align-items-center min-vh-75">

				<div class="col-lg-7">
					<span class="text-primary fw-bold text-uppercase small">Movilidad · Flexibilidad · Control</span>
					<h1 class="display-3 fw-bold mt-3 mb-4">Encuentra el vehículo adecuado para cada viaje</h1>
					<p class="lead text-secondary mb-4">Consulta disponibilidad, crea alquileres y gestiona devoluciones desde IparRent.</p>

					<div class="d-flex flex-wrap gap-2">
						<a href="vehiculos.jsp" class="btn btn-primary btn-lg"><i class="bi bi-car-front-fill"></i> Ver vehículos</a>
						<a href="alquileres.jsp" class="btn btn-outline-dark btn-lg"><i class="bi bi-key-fill"></i> Crear alquiler</a>
					</div>
				</div>

				<div class="col-lg-5 mt-5 mt-lg-0">
					<div class="card shadow-lg border-0">
						<div class="card-body p-5 text-center">
							<i class="bi bi-car-front-fill display-1 text-primary"></i>
							<h2 class="mt-4">IparRent</h2>
							<p class="text-secondary">Gestión inteligente de alquiler de vehículos.</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>

	<section class="bg-light py-5">
		<div class="container">
			<div class="row g-4">

				<div class="col-md-4">
					<div class="card h-100 border-0 shadow-sm">
						<div class="card-body text-center p-4">
							<i class="bi bi-car-front fs-1 text-primary"></i>
							<h3 class="h5 mt-3">Vehículos</h3>
							<p class="text-secondary">Consulta el catálogo y la disponibilidad.</p>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card h-100 border-0 shadow-sm">
						<div class="card-body text-center p-4">
							<i class="bi bi-calendar-check fs-1 text-primary"></i>
							<h3 class="h5 mt-3">Alquileres</h3>
							<p class="text-secondary">Crea y gestiona alquileres fácilmente.</p>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card h-100 border-0 shadow-sm">
						<div class="card-body text-center p-4">
							<i class="bi bi-bar-chart fs-1 text-primary"></i>
							<h3 class="h5 mt-3">Facturación</h3>
							<p class="text-secondary">Consulta los informes de facturación.</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
</main>

<%@ include file="/includes/pie.jsp"%>
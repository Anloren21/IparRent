<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ include file="/includes/cabecera.jsp"%>

<main class="container py-5">

	<h1 class="fw-bold mb-2">Gestión de alquileres</h1>
	<p class="text-secondary mb-5">Crea nuevos alquileres y registra devoluciones.</p>

	<div class="row g-4">

		<div class="col-lg-6">
			<div class="card border-0 shadow-sm h-100">
				<div class="card-body p-4">
					<h2 class="h4 mb-4"><i class="bi bi-key-fill text-primary"></i> Nuevo alquiler</h2>

					<form id="formAlquiler">
						<div class="mb-3">
							<label for="idCliente" class="form-label">ID cliente</label>
							<input id="idCliente" class="form-control" type="number" min="1" required>
						</div>

						<div class="mb-3">
							<label for="idVehiculo" class="form-label">ID vehículo</label>
							<input id="idVehiculo" class="form-control" type="number" min="1" required>
						</div>

						<div class="mb-3">
							<label for="dias" class="form-label">Días</label>
							<input id="dias" class="form-control" type="number" min="1" max="30" required>
						</div>

						<button class="btn btn-primary w-100" type="submit">Crear alquiler</button>
					</form>

					<div id="resultadoAlquiler" class="mt-3"></div>
				</div>
			</div>
		</div>

		<div class="col-lg-6">
			<div class="card border-0 shadow-sm h-100">
				<div class="card-body p-4">
					<h2 class="h4 mb-4"><i class="bi bi-arrow-return-left text-primary"></i> Devolución</h2>

					<form id="formDevolucion">
						<div class="mb-3">
							<label for="idAlquiler" class="form-label">ID alquiler</label>
							<input id="idAlquiler" class="form-control" type="number" min="1" required>
						</div>

						<button class="btn btn-dark w-100" type="submit">Registrar devolución</button>
					</form>

					<div id="resultadoDevolucion" class="mt-3"></div>
				</div>
			</div>
		</div>

	</div>

</main>

<%@ include file="/includes/pie.jsp"%>
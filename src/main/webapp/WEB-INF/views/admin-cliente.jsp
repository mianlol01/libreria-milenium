<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista clientes</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/admin.css" />
</head>
<body>
	<div class="d-flex">
		<jsp:include page="c-admin-nav.jsp"></jsp:include>
		<!-- Contenido Principal -->
		<main class="flex-grow-1 p-4">
			<div class="container mt-5">
				<!-- Datos del Cliente -->
				<div class="card mb-4">
					<div class="card-header">
						<h4>Datos del Cliente</h4>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<p>
									<strong>ID Cliente:</strong> ${cliente.id_cliente}
								</p>
								<p>
									<strong>Nombre:</strong> ${cliente.nombre_cliente}
									${cliente.apellido_cliente}
								</p>
							</div>
							<div class="col-md-6">
								<p>
									<strong>Correo:</strong> ${cliente.correo_cliente}
								</p>
								<p>
									<strong>Username:</strong> ${cliente.username_cliente}
								</p>
							</div>
						</div>
					</div>
				</div>

				<!-- Lista de Boletas -->
				<div class="card mb-4">
					<div class="card-header">
						<h4>Boletas del Cliente</h4>
					</div>
					<div class="card-body">
						<c:if test="${not empty cliente.boletas}">
							<table class="table table-striped">
								<thead class="thead-dark">
									<tr>
										<th>ID Boleta</th>
										<th>Total</th>
										<th>Fecha</th>
										<th>Detalles</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="boleta" items="${cliente.boletas}">
										<tr>
											<td>${boleta.id_boleta}</td>
											<td>S/. ${boleta.total}</td>
											<td>${boleta.fecha_boleta}</td>
											<td>
												<button class="btn btn-info btn-sm" type="button"
													data-toggle="collapse"
													data-target="#detalles-${boleta.id_boleta}">Ver
													Detalles</button>
											</td>
										</tr>
										<!-- Detalles de la Boleta -->
										<tr>
											<td colspan="4">
												<div id="detalles-${boleta.id_boleta}" class="collapse">
													<table class="table table-bordered mt-2">
														<thead>
															<tr>
																<th>Título del Libro</th>
																<th>Cantidad</th>
																<th>Precio Unitario</th>
																<th>Descuento</th>
																<th>Total</th>
																<th>Importe</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="detalle" items="${boleta.listaDetalle}">
																<tr>
																	<td>${detalle.titulo}</td>
																	<td>${detalle.cantidad}</td>
																	<td>S/. ${detalle.precio_unitario}</td>
																	<td>S/. ${detalle.descuento_unidad}</td>
																	<td>S/. ${detalle.total}</td>
																	<td>S/. ${detalle.importe}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<c:if test="${empty cliente.boletas}">
							<p class="text-danger">El cliente no tiene boletas
								registradas.</p>
						</c:if>
					</div>
				</div>
			</div>
		</main>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
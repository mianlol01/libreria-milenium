<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Compras</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/styles.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
</head>
<body>
	<!-- Navbar -->
	<jsp:include page="c-nav.jsp"></jsp:include>
	<!-- Fin Navbar -->
	<!--MAIN-->
	<div class="container-sm main-container p-0">
		<div class="container-fluid p-0">
			<div class="container my-4">
				<c:choose>
					<c:when test="${not empty listaBoletas}">
						<c:forEach items="${listaBoletas}" var="boleta">
							<div class="bg-light-subtle m-3">
								<div class=" row">
									<div class="col text-center">
										<h2>Boleta de Venta</h2>
										<p>Fecha: ${boleta.fecha_boleta}</p>
										<p>N° Boleta: ${boleta.id_boleta}</p>
									</div>
								</div>

								<!-- Información del cliente -->
								<div class="row my-3">
									<div class="col-md-6">
										<h5>Cliente:</h5>
										<p>${cliente.nombre_cliente}${cliente.apellido_cliente}</p>
									</div>
									<div class="col-md-6">
										<h5>Codigo de cliente:</h5>
										<p>${cliente.id_cliente }</p>
									</div>
								</div>

								<!-- Detalles de la compra -->
								<div class="row">
									<div class="col">
										<table class="table table-bordered">
											<thead class="table-light">
												<tr>
													<th>#</th>
													<th>Descripción</th>
													<th>Cantidad</th>
													<th>Precio Unitario</th>
													<th>Subtotal</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${boleta.getListaDetalle()}" var="detalle"
													varStatus="status">
													<tr>
														<td>${status.index + 1}</td>
														<td>${detalle.titulo}</td>
														<td>${detalle.cantidad}</td>
														<td>${detalle.precio_unitarioConDescuento()}</td>
														<td>${detalle.importe()}</td>
													</tr>
												</c:forEach>
											</tbody>
											<tfoot>
												<tr>
													<td colspan="4" class="text-end"><strong>Total:</strong></td>
													<td><strong>${boleta.total}</strong></td>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
								<div class="row mt-3">
									<div class="col text-center">
										<p>Gracias por su compra.</p>
										<p>
											<small>Este documento no es válido como comprobante
												fiscal.</small>
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>

						<div
							class="d-flex flex-column justify-content-center align-items-center m-5 w-100 text-center">
							<div class="text-center m-5 w-50">
								<img
									src="${pageContext.request.contextPath}/resources/images/not-found.png"
									alt="No Purchases" class="img-fluid w-50">
								<h1 class="display-4">Sin compras registradas</h1>
								<p class="lead">Parece que aún no has realizado ninguna
									compra.</p>
								<a href="home" class="btn btn-primary">Volver a la tienda</a>
							</div>
						</div>

					</c:otherwise>
				</c:choose>
			</div>
			<!-- Detalles de la compra -->
		</div>
	</div>
	<jsp:include page="c-footer.jsp"></jsp:include>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
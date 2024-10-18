<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte Categoria</title>
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
			<div class="container">
				<h2 class="my-4">Reporte de Categorías Más Vendidas</h2>

				<table class="table table-bordered table-hover">
					<thead class="table-dark">
						<tr>
							<th>Categoría</th>
							<th>Cantidad Vendida</th>
						</tr>
					</thead>
					<tbody>
						<!-- Mostrar los datos de la lista de reporte -->
						<c:forEach var="reporte" items="${listaReporte}">
							<tr>
								<td>${reporte.nombreCategoria}</td>
								<td>${reporte.cantidadVendida}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="text-center mt-3">
					<a href="admin?action=reportes" class="btn btn-primary">Volver</a>
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
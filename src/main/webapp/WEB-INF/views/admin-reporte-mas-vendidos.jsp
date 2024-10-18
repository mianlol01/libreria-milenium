<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reportes</title>
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
				<h2 class="text-center">Reporte de Libros Más Vendidos</h2>

				<table class="table table-striped table-bordered mt-4">
					<thead class="thead-dark">
						<tr>
							<th>Título</th>
							<th>Cantidad Vendida</th>
							<th>Total Ingresos</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="libro" items="${listaLibros}">
							<tr>
								<td>${libro.titulo}</td>
								<td>${libro.cantidadVendida}</td>
								<td>S/. ${libro.totalIngresos}</td>
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
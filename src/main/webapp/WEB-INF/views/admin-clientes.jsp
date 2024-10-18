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
				<h2>Lista de Clientes</h2>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID Cliente</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Correo</th>
							<th>Username</th>
							<th>Número de Compras</th>
							<th>Detalles</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cliente" items="${clientes}">
							<tr>
								<td>${cliente.id_cliente}</td>
								<td>${cliente.nombre_cliente}</td>
								<td>${cliente.apellido_cliente}</td>
								<td>${cliente.correo_cliente}</td>
								<td>${cliente.username_cliente}</td>
								<td>${cliente.boletas.size()}</td>
								<td><a href="admin?action=cliente&id_cliente=${cliente.id_cliente}" class="btn btn-primary">Detalles</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
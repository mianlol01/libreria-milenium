<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
				<h1 class="fs-1 fw-bold pb-5">Gestión de Libros</h1>
				<div class="vstack gap-3">
					<jsp:include page="c-admin-form-libro.jsp"></jsp:include>
					<button id="toggleAutorButton"
						class="btn btn-primary p-2 fs-3 fw-bold">Registrar Autor</button>
					<div id="formAutor" class="card p-4" style="display: none">
						<h4 class="mb-3">Registrar Autor</h4>
						<form action="admin" method="post">
							<div class="mb-3">
								<label for="nombre" class="form-label">Nombre del Autor</label>
								<input name="autor" name="nombre_autor" type="text"
									class="form-control" id="nombre"
									placeholder="Ingresa nombre del autor a registrar" required />
							</div>
							<input type="hidden" name="action" value="registrar-autor" />
							<button type="submit" class="btn btn-success">Registrar</button>
						</form>
					</div>
					<button id="toggleListarAutorButton"
						class="btn btn-secondary p-2 fs-3 fw-bold">Listar Autores</button>
					<div id="tablaAutores" class="card p-4" style="display: none">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Autor</th>
									<th scope="col">Número de libros</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaAutores}" var="autor">
									<tr>
										<td>${autor.nombre_autor}</td>
										<td>${autor.cantidad_libros}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<button id="toggleListarLibrosButton"
						class="btn btn-secondary p-2 fs-3 fw-bold">Listar Libros</button>
					<div id="tablaLibros" class="card p-4" style="display: none">
						<table class="table table-striped">
							<thead>
								<tr>
									<th scope="col">Título</th>
									<th scope="col">Autor</th>
									<th scope="col">Precio</th>
									<th scope="col">Stock</th>
									<th scope="col">Descuento (%)</th>
									<th scope="col">Acciones</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaLibros}" var="libro">
									<tr>
										<td>${libro.titulo}</td>
										<td>${libro.nombre_autor}</td>
										<td>S/${libro.precio()}</td>
										<td>${libro.stock}</td>
										<td>${libro.descuento}%</td>
										<td><a href="admin?action=editar-libro&id_libro=${libro.id_libro}" class="btn btn-warning btn-sm">Editar</a></td>
									</tr>
								</c:forEach>
								<!-- Más libros aquí -->
							</tbody>
						</table>
					</div>
					<div class="alert ${cssmensaje}" role="alert">${mensaje}</div>
				</div>
			</div>
		</main>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/scripts/admin.js"></script>
</body>
</html>
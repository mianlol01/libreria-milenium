<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css"/>
</head>
<body>
	<div class="d-flex">
		<jsp:include page="c-admin-nav.jsp"></jsp:include>
		<!-- Contenido Principal -->
		<main class="flex-grow-1 p-4">
			<h1>Bienvenido ${admin.username_empleado}</h1>
			<p>¿Qué desea realizar?</p>
			<div class="container mt-4">
				<div class="row text-center">
					<!-- Tarjeta 1 -->
					<div class="col-sm-6 col-md-4 col-lg-3 mb-4">
						<div class="card">
							<img
								src="${pageContext.request.contextPath}/resources/images/admin01.png"
								class="card-img-top" alt="Imagen de tarjeta" />
							<div class="card-body">
								<a href="admin?action=libros" class="btn btn-secondary">Gestionar
									Libros</a>
							</div>
						</div>
					</div>

					<!-- Tarjeta 2 -->
					<div class="col-sm-6 col-md-4 col-lg-3 mb-4">
						<div class="card">
							<img
								src="${pageContext.request.contextPath}/resources/images/admin02.png"
								class="card-img-top" alt="Imagen de tarjeta" />
							<div class="card-body">
								<a href="admin?action=clientes" class="btn btn-secondary">Gestionar
									Clientes</a>
							</div>
						</div>
					</div>

					<!-- Tarjeta 3 -->
					<div class="col-sm-6 col-md-4 col-lg-3 mb-4">
						<div class="card">
							<img
								src="${pageContext.request.contextPath}/resources/images/admin03.png"
								class="card-img-top" alt="Imagen de tarjeta" />
							<div class="card-body">
								<a href="admin?action=reportes" class="btn btn-secondary">Ver
									Reportes</a>
							</div>
						</div>
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
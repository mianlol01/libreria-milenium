<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
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
	<!-- Banner -->
	<div class="full-width-div bg-primary text-white banner">
		<div id="carouselExample" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active" data-bs-interval="4000">
					<img
						src="${pageContext.request.contextPath}/resources/images/banner01.png"
						class="d-block w-100" alt="..." />
				</div>
				<div class="carousel-item" data-bs-interval="4000">
					<img
						src="${pageContext.request.contextPath}/resources/images/banner02.png"
						class="d-block w-100" alt="..." />
				</div>
				<div class="carousel-item" data-bs-interval="4000">
					<img
						src="${pageContext.request.contextPath}/resources/images/banner03.png"
						class="d-block w-100" alt="..." />
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExample" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExample" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>
	<!--MAIN-->
	<div class="container-sm main-container">
		<div class="container-fluid text-center">
			<h1 class="main-titulo fs-1">PRODUCTOS DESTACADOS</h1>
		</div>
		<div class="container-fluid main-grid">
			<div class="container mt-5">
				<ul class="article-grid">
					<c:forEach items="${listaDestacados}" var="destacado">
						<!-- Puedes agregar más artículos aquí -->
						<li class="card-custom">
							<div class="portada">
								<img
									src="https://images.cdn1.buscalibre.com/fit-in/360x360/65/22/6522fbd45002dedf398aba22c041d4c6.jpg"
									alt="" /> <span class="descuento fs-6 font-monospace">-${destacado.descuento}%</span>
							</div>
							<div class="datos text-center">
								<p class="fs-5 titulo">${destacado.titulo}</p>
								<p class="fs-6 autor">${destacado.nombre_autor}</p>
								<div class="price-container">
									<p class="fs-6 font-monospace transparente">${destacado.precio()}</p>
									<p class="fs-6 font-monospace">${destacado.descuento()}</p>
								</div>
							</div>
							<form class="btn-container">
								<a class="btn btn-custom">COMPRAR</a>
							</form>
						</li>
					</c:forEach>
					<!-- Puedes agregar más artículos aquí -->
				</ul>
			</div>
		</div>
	</div>
	<!--FIN MAIN-->
	<!--FOOTER-->
	<div class="container-fluid footer-container text-center">
		<h5 class="footer-titulo">Special title treatment</h5>
		<p class="footer-p">With supporting text below as a natural
			lead-in to additional content.</p>
		<p class="footer-p">2024 © Librería MILENIUM. Todos los Derechos
			Reservados</p>
	</div>
	<!--FIN FOOTER-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

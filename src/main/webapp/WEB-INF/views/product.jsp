<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>PRODUCTO</title>
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
	<!--MAIN-->
	<div class="container-fluid main-blanco">
		<div class="container-sm main-container producto-main">
			<div class="container-fluid text-center">
				<h1 class="main-titulo fs-1">DETALLES DEL PRODUCTO</h1>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-6 col-12">
						<div class="p-3 text-center">
							<img class="img_libro"
								src="${pageContext.request.contextPath}/resources/images/${libro.ruta()}.png"
								alt="" />
						</div>
					</div>
					<div class="col-lg-4 col-md-6 col-12">
						<div class="p-3 col-compra">
							<p class="titulo fs-5 text-center">INFORMACIÓN DEL PRODUCTO</p>
							<div class="row dato-producto">
								<div class="col">
									<p class="titulo">TITULO:</p>
								</div>
								<div class="col">
									<p>${libro.titulo}</p>
								</div>
								<hr />
							</div>
							<div class="row dato-producto">
								<div class="col">
									<p class="titulo">AUTOR:</p>
								</div>
								<div class="col">
									<p>${libro.nombre_autor}</p>
								</div>
								<hr />
							</div>
							<div class="row dato-producto">
								<div class="col">
									<p class="titulo">FECHA DE DE PUBLICACIÓN:</p>
								</div>
								<div class="col col-dato">
									<p>${libro.fecha_publicacion}</p>
								</div>
								<hr />
							</div>
							<div class="row dato-producto">
								<p class="titulo text-center">SINOPSIS</p>
								<p>${libro.sinopsis}</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-6 col-12 container-compra">
						<div class="p-3 col-compra">
							<form>
								<p class="titulo fs-5 text-center">COMPRAR</p>
								<div class="row dato-producto">
									<div class="col">
										<p class="titulo">PRECIO:</p>
									</div>
									<div class="col col-precio">
										<p class="col-precio-descuento font-monospace transparente">
											S/${libro.precio()}</p>
										<p class="titulo font-monospace">S/${libro.descuento()}</p>
									</div>
									<hr />
								</div>
								<div class="row dato-producto">
									<div class="col">
										<p class="titulo">UNIDADES DISPONIBLES:</p>
									</div>
									<div class="col col-dato">
										<p>${libro.stock}</p>
									</div>
									<hr />
								</div>
								<div class="row dato-producto">
									<div class="col">
										<p class="titulo">UNIDADES A COMPRAR:</p>
									</div>
									<div class="col form-compra">
										<input type="number" class="form-control" id="inputNumeros"
											name="cantidad" min="0" step="1"
											placeholder="Ingrese cantidad" />
									</div>
									<hr />
								</div>
								<div class="text-center">
									<button class="btn btn-custom btn-compra">COMPRAR</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--FIN MAIN-->
	<!--SIMILARES-->
	<div class="container-sm main-container">
		<div class="container-fluid text-center">
			<h1 class="main-titulo fs-1">PRODUCTOS SIMILARES</h1>
		</div>
		<div class="container-fluid main-grid">
			<div class="container mt-5">
				<ul class="article-grid">
					<c:forEach items="${listaSimilares}" var="destacado">
						<li class="card-custom">
							<div class="portada">
								<img class="portada-img"
									src="${pageContext.request.contextPath}/resources/images/${destacado.ruta()}.png"
									alt="" /> <span class="descuento fs-6 font-monospace">-${destacado.descuento}%</span>
							</div>
							<div class="datos text-center">
								<p class="fs-5 titulo">${destacado.titulo}</p>
								<p class="fs-6 autor">${destacado.nombre_autor}</p>
								<div class="price-container">
									<p class="fs-6 font-monospace transparente">S/${destacado.precio()}</p>
									<p class="fs-6 font-monospace">S/${destacado.descuento()}</p>
								</div>
							</div>
							<div class="btn-container">
								<a href="product?id=${destacado.id_libro}"
									class="btn btn-custom">COMPRAR</a>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!--FIN SIMILARES-->
	<jsp:include page="c-footer.jsp"></jsp:include>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
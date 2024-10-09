<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>resultados: ${frase}</title>
</head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/styles.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
<body>
	<!-- Navbar -->
	<jsp:include page="c-nav.jsp"></jsp:include>
	<!-- Fin Navbar -->
	<!--MAIN-->
	<div class="container-sm main-container">
		<div class="container-fluid text-center">
			<h1 class="main-titulo fs-1">RESULTADOS DE LA BÚSQUEDA PARA:
				${frase}</h1>
		</div>
		<div class="container-fluid main-grid">
			<div class="container mt-5">
				<ul class="article-grid">
					<!-- Puedes agregar más artículos aquí -->
					<c:forEach items="${listaLibrosEncontrados}" var="libro">
						<!-- Puedes agregar más artículos aquí -->
						<li class="card-custom">
							<div class="portada">
								<img class="portada-img"
									src="${pageContext.request.contextPath}/resources/images/${libro.ruta()}.png"
									alt="" /> <span class="descuento fs-6 font-monospace">-${libro.descuento}%</span>
							</div>
							<div class="datos text-center">
								<p class="fs-5 titulo">${libro.titulo}</p>
								<p class="fs-6 autor">${libro.nombre_autor}</p>
								<div class="price-container">
									<p class="fs-6 font-monospace transparente">S/${libro.precio()}</p>
									<p class="fs-6 font-monospace">S/${libro.descuento()}</p>
								</div>
							</div>
							<div class="btn-container">
								<a href="product?id=${libro.id_libro}" class="btn btn-custom">COMPRAR</a>
							</div>
						</li>
					</c:forEach>
					<!-- Puedes agregar más artículos aquí -->
				</ul>
			</div>
		</div>
	</div>
	<!--FIN MAIN-->
	<jsp:include page="c-footer.jsp"></jsp:include>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
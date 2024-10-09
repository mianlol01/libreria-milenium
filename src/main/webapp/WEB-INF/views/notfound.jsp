<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>not found :(</title>
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
		<div class="container-fluid text-center mt-3 mb-5">
			<h1 class="main-titulo fs-1 mt-3 mb-3">SIN RESULTADOS PARA:
				${frase}</h1>
			<div class="container w-50">
				<img class="img-fluid w-50 mb-3" src="${pageContext.request.contextPath}/resources/images/not-found.png" alt="" />
				<p>No obtuvimos resultados para su búsqueda.</p>
				<p>Por favor intente buscar algo diferente.</p>
				<div class="buscador-container">
					<form action="search" method="get" class="d-flex" role="search">
						<input name="q" class="form-control custom-input" type="search"
							placeholder="Buscar" aria-label="Search" required />
						<button class="btn btn-outline-warning custom-btn-search"
							type="submit">Buscar</button>
					</form>
				</div>
			</div>
		</div>
		<div class="container-fluid text-center mt-3 mb-5">
			<h1 class="main-titulo fs-1 mt-3 mb-3">PUEDES EXPLORAR POR
				NUESTRAS CATEGORÍAS</h1>
		</div>
		<ul class="tags-grid mb-5 text-center">
			<c:forEach items="${listaCategorias}" var="categoria">
				<li class="tag-container"><a class="tag-custom fs-5"
					href="category?id=${categoria.id_categoria}">${categoria.nombre_categoria}</a></li>
			</c:forEach>
		</ul>
	</div>
	<!--FIN MAIN-->
	<jsp:include page="c-footer.jsp"></jsp:include>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
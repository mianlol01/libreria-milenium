<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-expand-xl custom-navbar sticky-top">
	<div class="container-fluid nav-custom-container">
		<img class="img-fluid logo navbar-brand"
			src="${pageContext.request.contextPath}/resources/images/logo.png"
			alt="" />
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<!-- Men� alineado a la derecha -->
			<ul class="navbar-nav ms-auto ul-nav-container">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="home"><i class="material-icons">home</i>INICIO</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> <i
						class="material-icons">book</i>LIBROS
				</a>
					<ul class="dropdown-menu dropdown-menu-dark">
						<li><a class="dropdown-item">NOVEDADES</a></li>
						<li><hr class="dropdown-divider" /></li>
						<c:forEach items="${listaCategorias}" var="categoria">
							<li><a class="dropdown-item" href="category?category=${categoria.id_categoria}">${categoria.nombre_categoria}</a></li>
						</c:forEach>
					</ul></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="material-icons">add_shopping_cart</i>CARRITO</a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i
						class="material-icons">person</i>CUENTA</a></li>
				<li class="nav-item">
					<div class="nav-link">
						<div class="buscador-container">
							<form action="buscador" method="post" class="d-flex"
								role="search">
								<input name="txtFrase" class="form-control custom-input"
									type="search" placeholder="Buscar" aria-label="Search" />
								<button class="btn btn-outline-warning custom-btn-search"
									type="submit">Buscar</button>
							</form>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</nav>
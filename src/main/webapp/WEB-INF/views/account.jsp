<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<!--MAIN-->
	<div class="container-sm main-container">
		<div class="container-fluid">
			<h1 class="main-titulo fs-1 text-center">GESTIONAR CUENTA</h1>
			<div class="container mt-3 mb-5 p-3 container-gestion">
				<div class="container-fluid main-grid">
					<div class="container">
						<!-- Puedes agregar más artículos aquí -->
						<p class="fs-2">
							Bienvenido: <strong>${cliente.username_cliente}</strong>
						</p>
						<p class="fs-2">¿Que desea realizar?</p>
						<ul class="tags-grid mb-5 text-center">
							<li class="tag-container"><a
								class="tag-account fs-5 fw-bolder" href="cart"><img
									class="img-fluid"
									src="${pageContext.request.contextPath}/resources/images/c01.png"
									alt="" />VER CARRITO DE COMPRAS</a></li>
							<li class="tag-container"><a
								class="tag-account fs-5 fw-bolder" href="account?action=manage"><img
									class="img-fluid"
									src="${pageContext.request.contextPath}/resources/images/c02.png"
									alt="" />CONFIGURAR CUENTA</a></li>
							<li class="tag-container"><a
								class="tag-account fs-5 fw-bolder" href="account?action=receipt"><img
									class="img-fluid"
									src="${pageContext.request.contextPath}/resources/images/c03.png"
									alt="" />VER DETALLES DE COMPRAS</a></li>
							<li class="tag-container"><a
								class="tag-account fs-5 fw-bolder" href="home"><img
									class="img-fluid"
									src="${pageContext.request.contextPath}/resources/images/c04.png"
									alt="" />SEGUIR COMPRANDO</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--FinMAIN-->
	<!-- Footer -->
	<jsp:include page="c-footer.jsp"></jsp:include>
	<!--Fin Footer -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
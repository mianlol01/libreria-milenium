<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Carrito</title>
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
	<div class="container-sm main-container p-0">
		<div class="container-fluid p-0">
			<c:if test="${mensaje==1}">
				<div class="container mt-3 mb-5 p-0 text-center">
					<div class="alert alert-success" role="alert">
						Su pedido fue procesado correctamente. <a href="account?action=receipt"
							class="alert-link">Ver pedidos.</a>.
					</div>
				</div>
			</c:if>
			<c:if test="${cart.cantidadArticulos() < 1 && empty mensaje}">
				<h1 class="main-titulo fs-1 text-center mt-5">CARRITO DE
					COMPRAS VACÍO</h1>
				<div class="container-fluid p-0 text-center bg-white">
					<img alt="" class="img-fluid "
						src="${pageContext.request.contextPath}/resources/images/cart.png">
					<p class="fs-3">Su carrito de compras esta vacío.</p>
					<p class="fs-3">Por favor agregue productos.</p>
				</div>
			</c:if>

			<c:if test="${cart.cantidadArticulos() > 0}">
				<h1 class="main-titulo fs-1 text-center mt-5">CARRITO DE
					COMPRAS</h1>
				<div class="container mt-3 mb-5 p-0">
					<!-- Tabla de productos -->
					<div class="table-responsive">
						<p class="fs-3">DETALLES</p>
						<table class="table custom-table text-center">
							<thead class="text-center">
								<tr>
									<th class="th-portada"></th>
									<th>Título</th>
									<th class="th-custom-2">Precio unitario</th>
									<th class="th-custom-2">Descuento</th>
									<th class="th-custom">Cantidad</th>
									<th class="th-custom-2">Importe</th>
									<th class="th-custom">Eliminar</th>
								</tr>
							</thead>
							<tbody>
								<!-- FILA -->
								<c:forEach items="${cart}" var="detail">
									<tr>
										<td class="td-portada"><img
											class="img-fluid carro-portada"
											src="${pageContext.request.contextPath}/resources/images/${detail.ruta()}.png"
											alt="" /></td>
										<td>${detail.titulo}</td>
										<td class="">
											<p class="transparente">S/${detail.precio_unitario()}</p>
											<p class="fw-bold">S/${detail.precio_unitarioConDescuento()}</p>
										</td>
										<td>${detail.descuento_unidad}%</td>
										<td>
											<form action="cart" method="post" class="form-editar">
												<input type="hidden" name="action" value="edit" /><input
													type="hidden" name="id_libro" value="${detail.id_libro}" />
												<input type="number" name="cantidad"
													class="form-control text-center input-editar"
													value="${detail.cantidad}" min="1" max="${detail.stock}" />
												<button type="submit" class="btn btn-warning m-1">
													Editar</button>
											</form>
										</td>
										<td class="fw-bold">S/${detail.importe()}</td>
										<td class="text-center">
											<form action="cart" method="post">
												<input type="hidden" name="action" value="delete" /> <input
													type="hidden" name="id_libro" value="${detail.id_libro}" />
												<button class="btn btn-danger">Eliminar</button>
											</form>
										</td>
									</tr>
								</c:forEach>
								<!-- FILA -->
							</tbody>
						</table>
					</div>
					<!--Fin Tabla de productos -->
					<!-- Resumen de compra -->
					<div class="d-flex justify-content-end">
						<div class="table-responsive">
							<table class="table table-bordered table-striped">
								<thead class="text-center">
									<tr>
										<th>Cantidad de artículos</th>
										<th>Total a pagar</th>
										<th>Comprar</th>
									</tr>
								</thead>
								<tbody class="text-center">
									<tr>
										<td>${cart.cantidadArticulos()}</td>
										<td class="fw-bold">S/${cart.totalPagar()}</td>
										<td>
											<form action="cart" method="post">
												<input type="hidden" name="action" value="buy" />
												<button class="btn btn-success">Finalizar compra</button>
											</form>
										</td>
									</tr>
								</tbody>
							</table>
							<c:if test="${cliente==null}">
								<div class="alert alert-info" role="alert">
									Debe <a href="login" class="alert-link">iniciar sesión</a> para
									poder procesar su compra.
								</div>
							</c:if>
						</div>
					</div>
					<!--Fin Resumen de compra -->
				</div>
			</c:if>



		</div>
	</div>
	<!--FinMAIN-->
	<!--SIMILARES-->
	<div class="container-sm main-container">
		<div class="container-fluid text-center">
			<h1 class="main-titulo fs-1">PRODUCTOS RECOMENDADOS</h1>
		</div>
		<div class="container-fluid main-grid">
			<div class="container mt-5">
				<ul class="article-grid">
					<c:forEach items="${listaRecomendados}" var="destacado">
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
	<!--FIN MAIN-->
	<jsp:include page="c-footer.jsp"></jsp:include>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
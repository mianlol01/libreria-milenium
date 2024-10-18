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
				<h2>Editar Libro</h2>
				<form action="admin" method="POST">
					<div class="form-group">
						<label for="id_libro">ID del Libro</label> <input type="text"
							class="form-control" id="id_libro" name="id_libro"
							value="${libro.id_libro}" readonly>
					</div>

					<div class="form-group">
						<label for="titulo">Título</label> <input type="text"
							class="form-control" id="titulo" name="titulo"
							value="${libro.titulo}" readonly>
					</div>

					<div class="form-group">
						<label for="nombre_autor">Nombre del Autor</label> <input
							type="text" class="form-control" id="" name="nombre_autor"
							value="${libro.nombre_autor}" readonly>
					</div>

					<div class="form-group">
						<label for="fecha_registro">Fecha de Registro</label> <input
							type="text" class="form-control" name="fecha_registro"
							value="${libro.fecha_registro}" readonly>
					</div>

					<div class="form-group">
						<label for="categorias">Categorías</label> <select
							class="form-control" id="categorias" name="categorias" multiple>
							<c:forEach items="${libro.categorias}" var="categoria">
								<option value="${categoria.id_categoria}">${categoria.nombre_categoria}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="sinopsis">Sinopsis</label>
						<textarea class="form-control" id="sinopsis" name="sinopsis"
							rows="3" readonly>${libro.sinopsis}</textarea>
					</div>

					<h4>Datos Modificables</h4>

					<div class="form-group">
						<label for="precio">Precio</label> <input type="number"
							class="form-control" id="precio" name="precio"
							value="${libro.precio}" required disabled>
					</div>

					<div class="form-group">
						<label for="descuento">Descuento (%)</label> <input type="number"
							class="form-control" id="descuento" name="descuento"
							value="${libro.descuento}" min="0" max="100" required disabled>
					</div>

					<div class="form-group">
						<label for="stock">Stock</label> <input type="number"
							class="form-control" id="stock" name="stock"
							value="${libro.stock}" required disabled>
					</div>
					<div class="alert ${cssmensaje} mt-2 mb-2" role="alert">${mensaje}</div>
					<input type="hidden" name="action" value="editar-libro" />
					<div class="mt-2">
						<button type="button" class="btn btn-primary" id="editarBtn"
							onclick="habilitarFormulario()">Editar</button>
						<button type="submit" class="btn btn-success" id="actualizarBtn"
							disabled>Actualizar</button>
						<button type="button" class="btn btn-secondary" id="cancelarBtn"
							disabled onclick="cancelar()">Cancelar</button>
					</div>
				</form>
			</div>
		</main>
	</div>
	<script>
		function habilitarFormulario() {
			// Habilitar campos de entrada
			document.getElementById("precio").disabled = false;
			document.getElementById("descuento").disabled = false;
			document.getElementById("stock").disabled = false;

			// Habilitar botones
			document.getElementById("actualizarBtn").disabled = false;
			document.getElementById("cancelarBtn").disabled = false;
			document.getElementById("editarBtn").disabled = true;
		}

		function cancelar() {
			// Recargar la página
			location.reload();
		}
	</script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
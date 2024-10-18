<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<button id="toggleFormButton" class="btn btn-primary p-2 fs-3 fw-bold">Registrar
	Libro</button>
<div id="formLibro" class="card p-4" style="display: none">
	<h4 class="mb-3">Registrar Libro</h4>
	<form action="admin" method="post" accept-charset="UTF-8">
		<div class="mb-3">
			<label for="titulo" class="form-label">Título</label> <input
				name="titulo" type="text" class="form-control" id="titulo"
				placeholder="Ingresa el título del libro" required />
		</div>

		<div class="mb-3">
			<label for="autor" class="form-label">Autor</label> <input
				name="autor" type="text" class="form-control" id="autor"
				placeholder="Ingresa el nombre del autor" required />
		</div>

		<div class="mb-3">
			<label class="form-label">Categorías</label>
			<div class="d-grid gap-2"
				style="grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));">
				<c:forEach items="${listaCategorias}" var="categoria">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="categorias"
							value="${categoria.id_categoria}"
							id="${categoria.nombre_categoria}" /> <label
							class="form-check-label" for="${categoria.nombre_categoria}">${categoria.nombre_categoria}</label>
					</div>
				</c:forEach>
				<!-- Más categorías aquí -->
			</div>
		</div>

		<div class="mb-3">
			<label for="precio" class="form-label">Precio</label> <input
				name="precio" type="number" class="form-control" id="precio"
				placeholder="Ingresa el precio del libro" step="0.01" min="0"
				required />
		</div>

		<div class="mb-3">
			<label for="stock" class="form-label">Stock</label> <input
				name="stock" type="number" class="form-control" id="stock"
				placeholder="Ingresa la cantidad de libros en stock" min="0"
				required />
		</div>

		<div class="mb-3">
			<label for="descuento" class="form-label">Descuento (%)</label> <input
				name="descuento" type="number" class="form-control" id="descuento"
				placeholder="Ingresa el porcentaje de descuento" min="0" max="100" />
		</div>

		<div class="mb-3">
			<label for="sinopsis" class="form-label">Sinopsis</label>
			<textarea name="sinopsis" class="form-control" id="sinopsis" rows="4"
				placeholder="Escribe una breve sinopsis del libro"></textarea>
		</div>
		<input type="hidden" name="action" value="registrar-libro" />
		<button type="submit" class="btn btn-success">Registrar Libro</button>
	</form>
</div>
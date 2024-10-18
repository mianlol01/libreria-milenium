<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Administrador</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<style>
/* Estilos adicionales para centrar el formulario */
.login-container {
	height: 100vh; /* Altura de la ventana */
	display: flex; /* Usar flexbox */
	align-items: center; /* Centrar verticalmente */
	justify-content: center; /* Centrar horizontalmente */
}
</style>
</head>
<body>
	<div class="login-container">
		<form action="admin" method="post" class="bg-light p-4 rounded shadow"
			style="width: 300px">
			<h3 class="text-center">Iniciar Sesión</h3>
			<div class="form-group">
				<label for="username">Usuario</label> <input name="admin_username"
					type="text" class="form-control" id="username"
					placeholder="Ingresa tu usuario" required />
			</div>
			<div class="form-group">
				<label for="password">Contraseña</label> <input
					name="admin_password" type="password" class="form-control"
					id="password" placeholder="Ingresa tu contraseña" required />
			</div>
			<p class="text-danger">${mensaje}</p>
			<input type="hidden" name="action" value="login" />
			<button type="submit" class="btn btn-primary btn-block">Entrar</button>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
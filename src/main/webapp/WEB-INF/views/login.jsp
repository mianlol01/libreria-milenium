<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Iniciar sesión</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet" />
</head>
<body>
	<div class="container-fluid contenedor-login">
		<div class="inner-box">
			<!-- Contenido centrado -->
			<div class="form">
				<form action="signup" method="post"
					onsubmit="return validarContraseñas()" class="register-form">
					<h1 class="fs-2 titulo-form">REGISTRARSE</h1>
					<label for="name" class="form-label custom-label">Nombre</label> <input
						name="name" id="name" type="text" placeholder="Ingrese su nombre"
						required /> <label for="lastname" class="form-label custom-label">Apellido</label>
					<input name="lastname" id="lastname" type="text"
						placeholder="Ingrese su apellido" required /> <label for="email"
						class="form-label custom-label">Correo</label> <input name="email"
						id="email" type="email"
						placeholder="Ingrese su correo electrónico" required /> <label
						for="username" class="form-label custom-label">Usuario</label> <input
						name="username" id="username" type="text"
						placeholder="Ingrese un nombre de usuario" required /> <label
						for="password" class="form-label custom-label">Contraseña</label>
					<input name="password" id="password" type="password"
						placeholder="Ingrese una contraseña" required /> <label
						for="confirmPassword" class="form-label custom-label">Vuelva
						a introducir la contraseña</label> <input name="confirmPassword"
						id="confirmPassword" type="password"
						placeholder="Vuelva a introducir su contraseña" required />
					<div id="error-message" class="text-danger mb-3"></div>
					<button class="form-btn">REGISTRAR</button>
					<div>${mensajesignup}</div>
					<p class="message">
						¿Ya está registrado? <a id="login-toggle" href="#">Iniciar Sesión</a>
					</p>
				</form>
				<form action="login" method="post" class="login-form">
					<h1 class="fs-2 titulo-form">INICIAR SESIÓN</h1>
					<label for="username" class="form-label custom-label">Usuario</label>
					<input name="username" id="username" type="text"
						placeholder="Ingrese su nombre de usuario" required /> <label
						for="password" class="form-label custom-label">Contraseña</label>
					<input name="password" type="password"
						placeholder="Ingrese su contraseña" required />
					<button class="form-btn">ingresar</button>
					<div>${mensaje}</div>
					<p class="message">
						¿Aún no está registrado? <a id="signup-toggle" href="#">Crear cuenta</a>
					</p>
					<p class="message">
						<a href="home">Volver a la tienda</a>
					</p>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<c:if test="${error==1}">
		<script>
			document.addEventListener("DOMContentLoaded", function() {
				$('form').animate({
					height : "toggle",
					opacity : "toggle"
				}, "slow");
			});
		</script>
	</c:if>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/scripts/login.js"></script>
</body>
</html>
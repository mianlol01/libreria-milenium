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
	<!--MAIN-->
	<div class="container-sm main-container">
		<div class="container-fluid">
			<h1 class="main-titulo fs-1 text-center">CONFIGURAR CUENTA</h1>
			<div class="container mt-3 mb-5 p-3 container-gestion">
				<div class="container-fluid main-grid">
					<div class="container">
						<!-- Puedes agregar más artículos aquí -->
						<p class="fs-2">
							SU CÓDIGO ÚNICO DE CLIENTE: <strong>${cliente.id_cliente}</strong>
						</p>
						<p class="fs-2">SUS DATOS</p>
						<form action="account" method="post" id="myForm"
							onsubmit="return validarContraseñas()">
							<div class="row mb-3">
								<div class="col-md-6">
									<label for="username" class="form-label">Usuario</label> <input
										name="username_cliente" type="text" class="form-control"
										id="username" disabled value="${cliente.username_cliente}"
										autocomplete="username" />
									<div class="text-danger">${mensaje_username}</div>
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-6">
									<label for="nombre" class="form-label">Nombre</label> <input
										name="nombre_cliente" type="text" class="form-control"
										id="nombre" disabled value="${cliente.nombre_cliente}" />
								</div>
								<div class="col-md-6">
									<label for="apellido" class="form-label">Apellido</label> <input
										name="apellido_cliente" type="text" class="form-control"
										id="apellido" placeholder="Ingresa tu apellido" disabled
										value="${cliente.apellido_cliente}" />
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-12">
									<label for="email" class="form-label">Email</label> <input
										name="correo_cliente" type="email" class="form-control"
										id="email" disabled value="${cliente.correo_cliente}"
										autocomplete="email" />
									<div class="text-danger">${mensaje_correo}</div>
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-md-6">
									<label for="password" class="form-label">Contraseña</label> <input
										name="password_cliente" type="password" class="form-control"
										id="password" value="${cliente.password_cliente}" disabled />
								</div>
								<div class="col-md-6">
									<label for="password_2" class="form-label">Repetir
										Contraseña</label> <input name="password_cliente" type="password"
										class="form-control" id="password_2"
										value="${cliente.password_cliente}" disabled />
								</div>
								<div id="error-message" class="text-danger mb-3"></div>
							</div>
							<div>
								<button type="button" id="editBtn" class="btn btn-warning">
									Editar</button>
								<button type="submit" id="updateBtn" class="btn btn-primary"
									disabled>Actualizar</button>
								<button type="button" id="cancelBtn" class="btn btn-danger"
									disabled>Cancelar</button>
							</div>
							<input name="action" type="hidden" value="edit" />
						</form>
						<div class="text-success">${mensaje_ok}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--FinMAIN-->
	<!--FinMAIN-->
	<!-- Footer -->
	<jsp:include page="c-footer.jsp"></jsp:include>
	<!--Fin Footer -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
      // Obtenemos los elementos
      const editBtn = document.getElementById("editBtn");
      const updateBtn = document.getElementById("updateBtn");
      const cancelBtn = document.getElementById("cancelBtn");
      const inputs = document.querySelectorAll("#myForm input");

      // Evento para habilitar los campos, el botón de actualizar y cancelar
      editBtn.addEventListener("click", function () {
        inputs.forEach((input) => (input.disabled = false)); // Habilitar inputs
        updateBtn.disabled = false; // Habilitar botón de actualizar
        cancelBtn.disabled = false; // Habilitar botón de cancelar
        editBtn.disabled = true; // Deshabilitar botón de editar
      });

      // Evento para recargar la página cuando se haga clic en "Cancelar"
      cancelBtn.addEventListener("click", function () {
        location.reload(); // Recargar la página
      });
    </script>
	<script>
      function validarContraseñas() {
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("password_2").value;

        if (password !== confirmPassword) {
          document.getElementById("error-message").textContent =
            "Las contraseñas no coinciden";
          return false; // Evitar el envío del formulario
        }
        return true; // Permitir el envío del formulario si las contraseñas coinciden
      }
    </script>
</body>
</html>
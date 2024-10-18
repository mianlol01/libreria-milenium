package com.milenium;

public class Mensaje {
	public String errorLogin() {
		return "<div class=\"custom-alert\">\r\n" + "              <div\r\n"
				+ "                class=\"alert alert-danger alert-dismissible custom-succes-alert\"\r\n"
				+ "                role=\"alert\"\r\n" + "              >\r\n"
				+ "                <div>Usuario o contraseña incorrecta. Intente otra vez</div>\r\n"
				+ "                <button\r\n" + "                  type=\"submit\"\r\n"
				+ "                  class=\"btn-close btn-close-custom\"\r\n"
				+ "                  data-bs-dismiss=\"alert\"\r\n" + "                  aria-label=\"Close\"\r\n"
				+ "                ></button>\r\n" + "              </div>\r\n" + "            </div>";
	}

	public String notAvailableUser() {
		return "<div class=\"custom-alert\">\r\n" + "              <div\r\n"
				+ "                class=\"alert alert-danger alert-dismissible custom-succes-alert\"\r\n"
				+ "                role=\"alert\"\r\n" + "              >\r\n"
				+ "                <div>Usuario no disponible. Ingrese un usuario diferente</div>\r\n"
				+ "                <button\r\n" + "                  type=\"submit\"\r\n"
				+ "                  class=\"btn-close btn-close-custom\"\r\n"
				+ "                  data-bs-dismiss=\"alert\"\r\n" + "                  aria-label=\"Close\"\r\n"
				+ "                ></button>\r\n" + "              </div>\r\n" + "            </div>";
	}

	public String notAvailableEmail() {
		return "<div class=\"custom-alert\">\r\n" + "              <div\r\n"
				+ "                class=\"alert alert-danger alert-dismissible custom-succes-alert\"\r\n"
				+ "                role=\"alert\"\r\n" + "              >\r\n"
				+ "                <div>Correo no disponible. Ingrese un correo diferente</div>\r\n"
				+ "                <button\r\n" + "                  type=\"submit\"\r\n"
				+ "                  class=\"btn-close btn-close-custom\"\r\n"
				+ "                  data-bs-dismiss=\"alert\"\r\n" + "                  aria-label=\"Close\"\r\n"
				+ "                ></button>\r\n" + "              </div>\r\n" + "            </div>";
	}

	public String stockInsuficiente() {
		return "<div class=\"custom-alert\">\r\n"
				+ "  <div class=\"alert alert-danger alert-dismissible custom-succes-alert\" role=\"alert\">\r\n"
				+ "    <div>Ya cuenta con este producto en su carrito. La cantidad a ingresar no debe superar ${excedente} unidades</div>\r\n"
				+ "    <button type=\"submit\" class=\"btn-close btn-close-custom\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n"
				+ "  </div>\r\n" + "</div>";
	}
}

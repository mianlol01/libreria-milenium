$('#signup-toggle').click(function() {
    $('form').animate({
        height: "toggle",
        opacity: "toggle"
    }, "slow");
});
$('#login-toggle').click(function() {
    $('form').animate({
        height: "toggle",
        opacity: "toggle"
    }, "slow");
});
const alertPlaceholder = document.getElementById('liveAlertPlaceholder')
const appendAlert = (message, type) => {
	const wrapper = document.createElement('div')
	wrapper.innerHTML = [
		`<div class="alert alert-${type} alert-dismissible" role="alert">`,
		`   <div>${message}</div>`,
		'   <button type="button" class="btn-close btn-close-custom" data-bs-dismiss="alert" aria-label="Close"></button>',
		'</div>'
	].join('')

	alertPlaceholder.append(wrapper)
}

const alertTrigger = document.getElementById('liveAlertBtn')
if (alertTrigger) {
	alertTrigger.addEventListener('click', () => {
		appendAlert('Nice, you triggered this alert message!', 'success')
	})
}

function validarContraseñas() {
	const password = document.getElementById("password").value;
	const confirmPassword = document.getElementById("confirmPassword").value;

	if (password !== confirmPassword) {
		document.getElementById("error-message").textContent = "Las contraseñas no coinciden";
		return false; // Evitar el envío del formulario
	}
	return true; // Permitir el envío del formulario si las contraseñas coinciden
}
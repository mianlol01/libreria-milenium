// Obtener los elementos
const toggleListarLibrosButton = document.getElementById(
	"toggleListarLibrosButton"
);
const tablaLibros = document.getElementById("tablaLibros");

// Agregar evento para mostrar/ocultar el formulario
toggleListarLibrosButton.addEventListener("click", function() {
	if (
		tablaLibros.style.display === "none" ||
		tablaLibros.style.display === ""
	) {
		tablaLibros.style.display = "block";
	} else {
		tablaLibros.style.display = "none";
	}
});
const toggleListarAutorButton = document.getElementById(
	"toggleListarAutorButton"
);
const tablaAutores = document.getElementById("tablaAutores");

// Agregar evento para mostrar/ocultar el formulario
toggleListarAutorButton.addEventListener("click", function() {
	if (
		tablaAutores.style.display === "none" ||
		tablaAutores.style.display === ""
	) {
		tablaAutores.style.display = "block";
	} else {
		tablaAutores.style.display = "none";
	}
});
// Obtener los elementos
const toggleButtonLibro = document.getElementById("toggleFormButton");
const formLibro = document.getElementById("formLibro");

// Agregar evento para mostrar/ocultar el formulario
toggleButtonLibro.addEventListener("click", function() {
	if (
		formLibro.style.display === "none" ||
		formLibro.style.display === ""
	) {
		formLibro.style.display = "block";
	} else {
		formLibro.style.display = "none";
	}
});

// Obtener los elementos
const toggleAutorButton = document.getElementById("toggleAutorButton");
const formAutor = document.getElementById("formAutor");

// Agregar evento para mostrar/ocultar el formulario
toggleAutorButton.addEventListener("click", function() {
	if (
		formAutor.style.display === "none" ||
		formAutor.style.display === ""
	) {
		formAutor.style.display = "block";
	} else {
		formAutor.style.display = "none";
	}
});
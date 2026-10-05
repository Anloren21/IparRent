"use strict";

var base = document.querySelector("base");
var contexto = base ? base.getAttribute("href") : "/iparrent/";
var api = contexto + "api";

function formatearEuro(valor) {
    return new Intl.NumberFormat("es-ES", {
        style: "currency",
        currency: "EUR"
    }).format(Number(valor));
}

function leerRespuesta(respuesta) {
    var tipo = respuesta.headers.get("content-type") || "";

    if (tipo.indexOf("json") !== -1) {
        return respuesta.json();
    }

    return Promise.resolve({});
}

function mostrarAlerta(elemento, mensaje, correcto) {
    if (!elemento) {
        return;
    }

    var tipo = correcto ? "success" : "danger";

    elemento.innerHTML =
        '<div class="alert alert-' + tipo + ' alert-dismissible fade show" role="alert">' +
        mensaje +
        '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>' +
        '</div>';
}



function imagenVehiculo(tipo) {
    var imagenes = {
        "Coche": "images/coche.webp",
        "Furgoneta": "images/furgoneta.webp",
        "Moto": "images/moto.webp",
        "CocheElectrico": "images/coche-electrico.webp"
    };

    if (imagenes[tipo]) {
        return imagenes[tipo];
    }

    return "images/coche.webp";
}


var vehiculosContainer = document.getElementById("vehiculosContainer");
var soloDisponibles = document.getElementById("soloDisponibles");


function cargarVehiculos() {

	if (!vehiculosContainer) return;

	vehiculosContainer.innerHTML =
		'<div class="col-12 text-secondary">Cargando vehículos...</div>';

	var url = api + "/vehiculos";

	if (soloDisponibles && soloDisponibles.checked) {
		url += "?disponibles=true";
	}

	fetch(url)
		.then(function(respuesta) {

			if (!respuesta.ok) {
				throw new Error(
					"Error HTTP " + respuesta.status +
					" al cargar los vehículos"
				);
			}

			return respuesta.json();
		})
		.then(function(vehiculos) {

			var parametros = new URLSearchParams(window.location.search);
			var texto = parametros.get("texto");

			if (texto) {
				var buscar = texto.toLowerCase();

				vehiculos = vehiculos.filter(function(v) {
					var contenido =
						v.id + " " +
						v.tipo + " " +
						v.marca + " " +
						v.modelo + " " +
						v.matricula;

					return contenido.toLowerCase().indexOf(buscar) !== -1;
				});
			}

			mostrarVehiculos(vehiculos);
		})
		.catch(function(error) {

			vehiculosContainer.innerHTML =
				'<div class="col-12">' +
					'<div class="alert alert-danger">' +
						error.message +
					'</div>' +
				'</div>';

			console.error("IparRent:", error);
		});
}


function mostrarVehiculos(vehiculos) {

    if (!vehiculos || vehiculos.length === 0) {

        vehiculosContainer.innerHTML =
            '<div class="col-12">' +
            '<div class="alert alert-info">' +
            'No se encontraron vehículos.' +
            '</div>' +
            '</div>';

        return;
    }

    var html = "";

    for (var i = 0;i < vehiculos.length;i++) {

        var v = vehiculos[i];

        var estadoClase = v.disponible
            ? "text-bg-success"
            : "text-bg-danger";

        var estadoTexto = v.disponible
            ? "Disponible"
            : "No disponible";

        var boton;

        if (v.disponible) {

            boton =
                '<a href="alquileres.jsp?idVehiculo=' + v.id + '" class="btn btn-primary w-100">' +
                '<i class="bi bi-key-fill"></i> Alquilar' +
                '</a>';

        } else {

            boton =
                '<button class="btn btn-secondary w-100" disabled>' +
                'No disponible' +
                '</button>';
        }


        html +=
            '<div class="col-md-6 col-xl-4">' +

            '<div class="card border-0 shadow-sm h-100">' +

            '<img src="' + imagenVehiculo(v.tipo) + '" ' +
            'class="card-img-top vehicle-img" ' +
            'alt="' + v.marca + ' ' + v.modelo + '">' +

            '<div class="card-body p-4">' +

            '<div class="d-flex justify-content-between mb-3">' +

            '<span class="badge text-bg-primary">' +
            v.tipo +
            '</span>' +

            '<span class="badge ' + estadoClase + '">' +
            estadoTexto +
            '</span>' +

            '</div>' +

            '<h2 class="h4">' +
            v.marca + " " + v.modelo +
            '</h2>' +

            '<p class="text-secondary">' +
            v.matricula +
            '</p>' +

            '<hr>' +

            '<p class="mb-1">' +
            '<strong>ID:</strong> ' +
            v.id +
            '</p>' +

            '<p class="mb-3">' +
            '<strong>Precio/día:</strong> ' +
            formatearEuro(v.precioDia) +
            '</p>' +

            boton +

            '</div>' +

            '</div>' +

            '</div>';
    }

    vehiculosContainer.innerHTML = html;
}


if (soloDisponibles) {

    soloDisponibles.addEventListener("change", function() {
        cargarVehiculos();
    });
}


var campoBusqueda = document.querySelector('input[name="texto"]');

if (campoBusqueda) {

    var parametrosBusqueda = new URLSearchParams(window.location.search);
    var textoBusqueda = parametrosBusqueda.get("texto");

    if (textoBusqueda) {
        campoBusqueda.value = textoBusqueda;
    }
}


var campoVehiculo = document.getElementById("idVehiculo");

if (campoVehiculo) {

    var parametrosVehiculo = new URLSearchParams(window.location.search);
    var idVehiculoParametro = parametrosVehiculo.get("idVehiculo");

    if (idVehiculoParametro) {
        campoVehiculo.value = idVehiculoParametro;
    }
}


var formAlquiler = document.getElementById("formAlquiler");
var resultadoAlquiler = document.getElementById("resultadoAlquiler");


if (formAlquiler) {

    formAlquiler.addEventListener("submit", function(evento) {

        evento.preventDefault();

        var idCliente = document.getElementById("idCliente").value;
        var idVehiculo = document.getElementById("idVehiculo").value;
        var dias = document.getElementById("dias").value;

        var solicitud = {
            idCliente: Number(idCliente),
            idVehiculo: Number(idVehiculo),
            dias: Number(dias)
        };

        fetch(api + "/alquileres", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(solicitud)
        })
            .then(function(respuesta) {

                return leerRespuesta(respuesta)
                    .then(function(datos) {

                        return {
                            respuesta: respuesta,
                            datos: datos
                        };
                    });
            })
            .then(function(resultado) {

                var respuesta = resultado.respuesta;
                var datos = resultado.datos;

                if (!respuesta.ok) {

                    var mensajeError =
                        datos.detail ||
                        "No se pudo crear el alquiler";

                    throw new Error(mensajeError);
                }

                var mensaje =
                    '<strong>Alquiler creado correctamente</strong><br>' +
                    'ID alquiler: ' + datos.id + '<br>' +
                    'Vehículo: ' +
                    datos.vehiculo.marca + ' ' +
                    datos.vehiculo.modelo + '<br>' +
                    'Días: ' + datos.dias + '<br>' +
                    'Importe: ' + formatearEuro(datos.importe);

                mostrarAlerta(
                    resultadoAlquiler,
                    mensaje,
                    true
                );

                formAlquiler.reset();
            })
            .catch(function(error) {

                mostrarAlerta(
                    resultadoAlquiler,
                    error.message,
                    false
                );
            });
    });
}

var formDevolucion = document.getElementById("formDevolucion");
var resultadoDevolucion = document.getElementById("resultadoDevolucion");


if (formDevolucion) {

    formDevolucion.addEventListener("submit", function(evento) {

        evento.preventDefault();

        var idAlquiler =
            document.getElementById("idAlquiler").value;

        fetch(
            api + "/alquileres/" +
            idAlquiler +
            "/devolucion",
            {
                method: "PUT"
            }
        )
            .then(function(respuesta) {

                return leerRespuesta(respuesta)
                    .then(function(datos) {

                        return {
                            respuesta: respuesta,
                            datos: datos
                        };
                    });
            })
            .then(function(resultado) {

                var respuesta = resultado.respuesta;
                var datos = resultado.datos;

                if (!respuesta.ok) {

                    var mensajeError =
                        datos.detail ||
                        "No se pudo registrar la devolución";

                    throw new Error(mensajeError);
                }

                var mensaje =
                    datos.mensaje ||
                    "Devolución registrada correctamente";

                mostrarAlerta(
                    resultadoDevolucion,
                    mensaje,
                    true
                );

                formDevolucion.reset();
            })
            .catch(function(error) {

                mostrarAlerta(
                    resultadoDevolucion,
                    error.message,
                    false
                );
            });
    });
}

var informeContainer = document.getElementById("informeContainer");
var btnInforme = document.getElementById("btnInforme");


function cargarInforme() {

    if (!informeContainer) {
        return;
    }

    informeContainer.innerHTML =
        '<div class="col-12 text-secondary">' +
        'Cargando informe...' +
        '</div>';

    fetch(api + "/informes/facturacion")
        .then(function(respuesta) {

            if (!respuesta.ok) {
                throw new Error(
                    "No se pudo cargar el informe de facturación"
                );
            }

            return respuesta.json();
        })
        .then(function(informe) {

            var tipos = Object.keys(informe);

            if (tipos.length === 0) {

                informeContainer.innerHTML =
                    '<div class="col-12">' +
                    '<div class="alert alert-info">' +
                    'Todavía no hay facturación.' +
                    '</div>' +
                    '</div>';

                return;
            }

            var html = "";

            for (var i = 0;i < tipos.length;i++) {

                var tipo = tipos[i];
                var importe = informe[tipo];

                html +=
                    '<div class="col-md-6 col-xl-4">' +

                    '<div class="card border-0 shadow-sm h-100">' +

                    '<div class="card-body p-4">' +

                    '<span class="text-secondary">' +
                    tipo +
                    '</span>' +

                    '<h2 class="display-6 fw-bold text-primary mt-2">' +
                    formatearEuro(importe) +
                    '</h2>' +

                    '</div>' +

                    '</div>' +

                    '</div>';
            }

            informeContainer.innerHTML = html;
        })
        .catch(function(error) {

            informeContainer.innerHTML =
                '<div class="col-12">' +
                '<div class="alert alert-danger">' +
                error.message +
                '</div>' +
                '</div>';
        });
}


if (btnInforme) {

    btnInforme.addEventListener("click", function() {
        cargarInforme();
    });
}

if (vehiculosContainer) {
    cargarVehiculos();
}

if (informeContainer) {
    cargarInforme();
}
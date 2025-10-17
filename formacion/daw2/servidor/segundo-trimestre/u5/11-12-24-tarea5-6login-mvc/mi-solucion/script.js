/**
 * No usar la variable "var", está en deshuso y no es profesional, usar
 * let o const en su lugar.
 */

function controlLogin() {
    // Guardo en una variable el objeto "formulario" extradio desde el cliente usando su ID.
    let formulario = document.getElementById("formulario");

    /**
     * Para evitar que se envie vacio, es decir, por "default" agregamos un
     * event listenerg que cuando detecte que se pinche el botón submit
     * evite que se envie el por defecto.
     */
    formulario.addEventListener("submit", function(event) {
        event.preventDefault();
    })

    /**
     * Dentro del objeto "formulario" existen otros dos objetos que son
     * "usuario" y "contrasena" identificados por sus ID's, entonces de
     * ellos extramos sus valores.
     */
    const usuario = formulario.usuario.value;
    const contrasena = formulario.contrasena.value;

    // Creamos un array que luego sera convertido en un JSON
    const datos =
        /***
         * Esto lo vimos en cliente, podemos crear objetos dentro
         * de otros objetos (anidación de objetos), usando llaves
         * cremos los objetos "usuario" y "contrasena" con sus
         * respectivos valores que extraimos antés del formulario.
         */
        {
            usuario: usuario,
            contrasena: contrasena
        };

    // Convertimos este objeto en un JSON.
    const datosJSON = JSON.stringify(datos);

    // Instanciamos una nueva conexión con XMLHTTPREQUEST();
    const conexion = new XMLHttpRequest();

    /**
     * Abrimos una nueva conexión con el servidor (PHP), dentro
     * de las variables de apertura de la conexión indicamos
     * que el método de envio de datos es POST, también a que
     * servidor (PHP) estamos llamado y si la conexión es
     * asincrona (TRUE) o sincrona (FALSE).
     */
    conexion.open("POST", "servidor.php", true);

    /**
     * Ya abierta la conexión debemos indicar que tipo de contenido
     * (Content-Type), en nuestro caso estamos enviando un JSON
     * (application/json).
     */
    conexion.setRequestHeader("Content-Type", "application/json");

    // Enviamos a través de la conexión nuestro JSON.
    conexion.send(datosJSON);


    /**
     * Este siguiente código ocurre después del anterior (el send)
     * ya que es el que recibe la respuesta (códigos) del servidor
     * y en base a ellos nos envia una respuesta valida o un error.
     * 
     * Como muchas cosas puede salir mal durante la conexión y el
     * envio de datos verificamos que todo sea correcto, primero
     * con "readyState" que tiene nos indíca el estado actual
     * del envio, sus posibles códigos son:
     * 
     * 0 (UNSENT): El método open() no ha sido llamado aún.
     * 1 (OPENED): Se ha hecho "open()" pero no se ha enviado nada.
     * 2 (HEADERS_RECEIVED): El servidor ha recibido la solicitud
     * y ha enviado la respuesta inicial.
     * 3 (LOADING): El servidor está enviando la respuesta.
     * 4 (DONE): La solicitud ha terminado y la respuesta está completamente recibida.
     * 
     * Nos interesa el código "4" que es un rotundo "Todo OK".
     * 
     * El segundo es "status" que también tienen diferentes códigos
     * con diferentes significados, los cuales son:
     * 
     * 200: Solicitud exitosa.
     * 404: Recurso no encontrado.
     * 500: Error interno del servidor.
     * 
     * En nuestro caso, nos interesa confirmar que todo este "OK",
     * es decir, el código "200".
     */
    conexion.onreadystatechange = function () {
        // Verificamos que el estado sea "DONE" (4).
        if (conexion.readyState === 4) {
            if (conexion.status === 200) {
                // Respuesta correcta.
                console.log("Respuesta del servidor: " + conexion.responseText);
            } else {
                // Error en la solicitud, imprimo los códigos para buscar errores.
                console.log("¡Error! Código: " + conexion.status);
                console.log("¡Error! Código: " + conexion.readyState);
            }
        }
    };

    let mensaje = document.getElementById("mensaje");

    mensaje.innerHTML = responseText;

    mensaje.style.opacity = "1";
}

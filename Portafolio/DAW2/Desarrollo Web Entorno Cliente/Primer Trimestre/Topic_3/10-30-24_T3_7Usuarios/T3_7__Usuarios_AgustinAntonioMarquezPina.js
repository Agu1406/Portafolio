/**
 * Realizar un programa que reciba una cadena con el siguiente formato: “nombre:apellidos:dni:telefono:email:codigopostal”
 * 
 * Tras recibir la cadena, se debe desglosar la información. Crear una estructura de datos que recoja la información extraída
 * de la cadena de caracteres.
 * 
 * Validar si la letra del DNI introducido es correcta. (Para la validación usar aproximación sin utilizar Expresiones 
 * regulares). Añadir el campo de tipo boolean “dniValido” cuyo valor será true en caso de DNI correcto o false en caso
 * de que la letra del DNI del usuario no coincida.
 * 
 * Suponiendo un formato de email “direccion@servidor”, se debe crear un campo llamado “servidor” para asignarle el nombre
 * del servidor asociado (sin la @).
 * 
 * Generar un nombre de usuario con la primera letra del nombre, las tres últimas del apellido separadas por un carácter “_”.
 * 
 * Guardar el nombre de usuario generado en el campo “username” de la estructura creada, asociado a cada usuario.
 * 
 * Añadir un campo a la estructura llamado “país” y asignar como valor una cadena de caracteres que represente la bandera
 * de su país (elegir cualquiera). *Dependiendo del navegador, en algunos casos se puede visualizar un icono de la
 * bandera o en otros casos la combinación de los caracteres únicos para cada país. (Ejemplo: España se podría
 * visualizar: Bandera de España o 🇪🇸).
 * 
 * Mostrar los nombres de usuario y las banderas correspondientes a su país en la página del lado del cliente.
 * 
 * Por último, si la inicial del nombre y los dos primeros caracteres del apellido forman la palabra “oso” (sin distinguir
 * mayúsculas o minúsculas), la página lanzará una ventana de dialogo de tipo alert: “¡Cuidado, hay # osos cerca!”, 
 * indicando en # el número de usuarios con la cadena.
 * 
 * Unos usuarios de ejemplo serían:
 * 
 * Rocio:Osorno:01101315Y:612345649:rocio@outlook.com:28001
 * Sonia: Sastre:15340807Z:765430987:ssastre@gmail.com:43071
 * Olivia:Soriano:46216401X:654567098:osor01@educa.madrid.org:28660
 * Elena:Costaguta:70068005T:7123098654:ecostag@madrid.org:45230
 * Orestes:Sosa:52243028T::sosaorestes2000@yahoo.com:28001
 * 
 * */

// Creamos todas las variables que creamos convenients para usar en el programa.
let nombre, apellido, dni, movil, correo, codPostal, letrasValidasDNI, expRegDNI;

// Extraemos del formulario toda la información utilizando "getElementByID".
nombre = document.getElementById("nombre").value;
apellido = document.getElementById("apellido").value;
dni = document.getElementById("DNI").value;
movil = document.getElementById("movil").value;
correo = document.getElementById("correo").value;
codPostal = document.getElementById("codPostal").value;

// Letras validas del DNI posibles mediante calcúlo divisor.
letrasValidasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";

// Expresión regular para validar si un DNI es valido o no.
expRegDNI = /\d{7}[a-zA-Z]/;

/**
 * Método que valida si un DNI es valido o no, separando sus números
 * de la letra y utilizando un calcúlo que en base a una posición en
 * el String de letras validas verifica si es valido o no.
 * 
 * @param {*} dni es un posible DNI valido obtenido del formulario.
 * @returns "true" si el DNI es valido, "false" si no lo es.
 */
function validarDNI (dni) {
    // Variable que responde si es valido o no un DNI.
    let valido;

    // Extraemos los números y la letra del DNI
    const numerosDelDNI = parseInt(dni.slice(0, 7), 10);
    const letraDelDNI = dni.slice(-1).toUpperCase();

    // Calculamos la letra correcta
    const letraCorrecta = letrasValidasDNI[numerosDelDNI % 23];

    // Comparamos la letra introducida con la letra calculada
    valido = (letraDelDNI === letraCorrecta);

    return valido;
}

/**
 * Método que lee una dirección de correo electronico, posiciona el ultimo
 * punto y la primera arroba y en base a esas dos posiciones extrae el
 * servidor de un correo y lo almacena y returna en un String.
 * 
 * @param {*} correo recibido directamente desde el formulario. 
 * @returns el servidor extraido de la dirección de correo.
 */
function extraerServidorCorreo (correo) {
    // Contendra en su valor el servidor extraido del String del correo.
    let servidor;

    // Encontramos la posición del "@" en el String + 1 para que no incluya el arroba.
    let posicion1 = correo.indexOf("@") + 1;

    // Encontramos la posición del punto que separa dominio de servidor.
    let posicion2 = correo.lastIndexOf(".");

    // Separamos en base a esas dos posiciones el servidor.
    servidor = correo.slice(posicion1, posicion2);

    // Devolvemos el servidor extraido como return.
    return servidor;
}

/**
 * Método que, tomando la primera letra de un nombre y las ultimas tres letras de un apellido
 * genera un nombre de usuario con una barra baja de por medio ("_") concatenando la primera
 * letra, junto al "_" con las ultimas tres letras del apellido.
 * 
 * @param {*} nombre que se obtiene del formulario con getElementById();
 * @param {*} apellido que se obtiene del formulario con getElementById();
 * @returns El nombre de usuario generado con los datos proporcionados.
 */
function generarNombreUsuario (nombre, apellido) {
    // Creamos la variables que usaremos en el método
    let nombreDeUsuario, primeraLetraNombre, tresLetrasApellido;

    // Toma la primera letra del nombre.
    primeraLetraNombre = nombre.charAt(0);
    
    // Toma las ultimas tres letras del apellido.
    tresLetrasApellido = apellido.slice(-3);

    // Concatenamos ambas cosas
    nombreDeUsuario = primeraLetraNombre + "_" + tresLetrasApellido;

    return nombreDeUsuario;
}
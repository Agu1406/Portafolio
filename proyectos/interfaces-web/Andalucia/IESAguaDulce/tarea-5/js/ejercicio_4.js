/**
 * Función que dibuja la mesa
 */
function dibujarMesa() {
  //Atrapamos la etiqueta html en una variable
  let miCanvas = document.getElementById("billar-canva");
  //creamos un objeto para poder dibujar en 2d
  let dibujo = miCanvas.getContext("2d");

  // Configuración del rectángulo (mesa de billar)
  //Le damos un color de relleno
  dibujo.fillStyle = "#0a5c36";
  //Le damos un color al borde
  dibujo.strokeStyle = "#000000";
  //Grosor del borde
  dibujo.lineWidth = 4;

  // Guardamos las dimensiones del lienzo en dos variables
  let canvasWidth = miCanvas.width;
  let canvasHeight = miCanvas.height;

  // Definifimos el tamaño de la mesa más pequeño que el lienzo
  let rectWidth = canvasWidth * 0.9;
  let rectHeight = canvasHeight * 0.9;

  // Buscamos la mitad del tamaño para centrar la mesa en el lienzo
  var x = (canvasWidth - rectWidth) / 2;
  var y = (canvasHeight - rectHeight) / 2;

  // Radio para las esquinas redondeadas
  let r = 20;

  // Coordenadas de las esquinas.Empezamos en superior-izquierda y en el orden del reloj
  let x0 = x,
    y0 = y; // Superior izquierda
  let x1 = x + rectWidth,
    y1 = y; // Superior derecha
  let x2 = x + rectWidth,
    y2 = y + rectHeight; // Inferior derecha
  let x3 = x,
    y3 = y + rectHeight; // Inferior izquierda

  // Empezamos a dibujar el rectangulo
  dibujo.beginPath(); //Indicamos se va a dibujar un nuevo camino
  // Empezamos desde el lado izquierdo (justo debajo de la esquina)
  dibujo.moveTo(x0, y0 + r);
  // Dibujamos los 4 lados con esquinas redondeadas
  dibujo.arcTo(x0, y0, x1, y0, r);
  dibujo.arcTo(x1, y1, x2, y2, r);
  dibujo.arcTo(x2, y2, x3, y3, r);
  dibujo.arcTo(x3, y3, x0, y0, r);
  dibujo.closePath();

  dibujo.fill(); //Dibuja el relleno
  dibujo.stroke(); //Dibuja el grosor
}

/*Definimos la función para dibujar el palo del billar*/
function dibujarPaloDeBillar(x, y) {
  // Obtenemos el mismo canvas y contexto
  let miCanvas = document.getElementById("billar-canva");
  let dibujo = miCanvas.getContext("2d");

  // Decidimos que estilos va a tener el aplo
  dibujo.fillStyle = "#8B4513"; //Color de relleno
  dibujo.strokeStyle = "#5D2906"; //Color del borde
  dibujo.lineWidth = 2; // Grosor del borde

  // tamaño del palo
  let paloAncho = 90;
  let paloAlto = 10;

  // Dibujamos el palo
  dibujo.beginPath();
  dibujo.rect(x, y, paloAncho, paloAlto);
  dibujo.fill();
  dibujo.stroke();
}

/*Definimos la función para dibujar la bola de billar*/
function dibujarBolaBlanca(x, y) {
  let miCanvas = document.getElementById("billar-canva");
  let dibujo = miCanvas.getContext("2d");

  // Estilos para la bola
  dibujo.fillStyle = "white"; // Le damos un color blanco
  dibujo.strokeStyle = "#cccccc"; // Color del borde
  dibujo.lineWidth = 2; // Grosor del borde

  let radio = 15; // Radio de la bola

  // Dibujar la bola (círculo)
  dibujo.beginPath();
  dibujo.arc(x, y, radio, 0, Math.PI * 2);
  dibujo.fill();
  dibujo.stroke();
}

/*Definimos la función para dibujar el mensaje*/
function dibujarMensaje() {
  let miCanvas = document.getElementById("billar-canva");
  let ctx = miCanvas.getContext("2d");

  // Configuración del texto
  ctx.fillStyle = "red"; // Color del texto
  ctx.font = "bold 22px Arial"; // Tamaño más grande
  ctx.textAlign = "center"; // Centrado horizontal
  ctx.strokeStyle = "white";
  ctx.lineWidth = 3;

  // Posición centrada en el canvas
  let x = miCanvas.width / 2;
  let y = 200;

  // Dibujamos primero el borde blanco
  ctx.strokeText("BILLAR AGUADULCE PRO 24/25", x, y);

  // Dibujamos luego el texto en color rojo
  ctx.fillText("BILLAR AGUADULCE PRO 24/25", x, y);
}

/**
 * Variables globales que controlan el estado de la animación:
 * - animacionEnCurso: un "interruptor" que nos dice si la animación está activa (true) o parada (false)
 * - posicionPaloX y posicionPaloY: coordenadas donde se dibuja el palo de billar
 * - posicionBolaX y posicionBolaY: coordenadas donde se dibuja la bola
 * - velocidadBola: qué tan rápido se mueve la bola (píxeles por frame)
 * - mensajeMostrado: otro "interruptor" que nos dice si ya mostramos el mensaje (true) o no (false)
 */
let animacionEnCurso = false;
let posicionPaloX = 80;
let posicionPaloY = 100;
let posicionBolaX = 240;
let posicionBolaY = 100;
let velocidadBola = 5;
let mensajeMostrado = false;

/**
 * Esta función se ejecuta automáticamente cuando la página web termina de cargar.
 * Es como decirle al navegador: "cuando termines de cargar todo, haz esto:"
 * 1. Dibuja la mesa de billar
 * 2. Dibuja el palo en su posición inicial
 * 3. Dibuja la bola en su posición inicial
 * 4. Prepara el botón para que cuando alguien haga clic, empiece la animación
 */
window.onload = function () {
  dibujarMesa();
  dibujarPaloDeBillar(posicionPaloX, posicionPaloY);
  dibujarBolaBlanca(posicionBolaX, posicionBolaY);

  // Buscamos el botón por su ID y le decimos qué hacer cuando alguien haga clic
  document
    .getElementById("iniciar-animacion")
    .addEventListener("click", iniciarAnimacion);
};

/**
 * Esta función prepara todo para empezar una nueva animación:
 * 1. Activa la animación poniendo animacionEnCurso = true
 * 2. Devuelve todos los elementos a sus posiciones iniciales
 * 3. Reinicia la velocidad de la bola
 * 4. Marca el mensaje como no mostrado
 * 5. Inicia la animación llamando a la función animar()
 */
function iniciarAnimacion() {
  animacionEnCurso = true;
  posicionPaloX = 80;
  posicionPaloY = 100;
  posicionBolaX = 240;
  posicionBolaY = 100;
  velocidadBola = 5;
  mensajeMostrado = false;

  animar();
}

/**
 * Esta es la función principal de la animación. Se ejecuta muchas veces por segundo
 * (como si fueran los fotogramas de una película o un bucle) y en cada ejecución:
 *
 * 1. Primero comprueba si debe seguir animando
 * 2. Limpia el canvas (como borrar un pizarrón)
 * 3. Dibuja la mesa de nuevo
 * 4. Según la fase de la animación:
 *    - Fase 1: Mueve el palo hacia la bola
 *    - Fase 2: Mueve la bola hacia la derecha
 *    - Fase 3: Termina la animación
 * 5. Dibuja el palo y la bola en sus nuevas posiciones
 * 6. Si el mensaje está activado, lo muestra
 * 7. Pide al navegador que ejecute el siguiente fotograma
 */
function animar() {
  // Si la animación está parada, no hacemos nada
  if (!animacionEnCurso) return;

  // Borramos todo para dibujar el nuevo fotograma
  limpiarCanvas();
  // Dibujamos la mesa de nuevo
  dibujarMesa();

  // Fase 1: Movimiento del palo
  if (posicionPaloX < 150) {
    // El palo se mueve 2 píxeles a la derecha en cada fotograma
    posicionPaloX += 2;
  }
  // Fase 2: Movimiento de la bola
  else if (posicionBolaX < 450) {
    // La bola se mueve según su velocidad
    posicionBolaX += velocidadBola;

    // Si la bola pasa el punto 300, activamos el mensaje
    if (posicionBolaX > 300 && !mensajeMostrado) {
      mensajeMostrado = true;
    }
  }
  // Fase 3: Fin de la animación
  else {
    // Paramos la animación
    animacionEnCurso = false;
    // Mostramos el mensaje final
    dibujarMensaje();
    return;
  }

  // Dibujamos el palo y la bola en sus nuevas posiciones
  dibujarPaloDeBillar(posicionPaloX, posicionPaloY);
  dibujarBolaBlanca(posicionBolaX, posicionBolaY);

  // Si el mensaje está activado, lo mostramos
  if (mensajeMostrado) {
    dibujarMensaje();
  }

  // Pedimos al navegador que ejecute el siguiente fotograma
  // requestAnimationFrame es como decirle: "cuando puedas, ejecuta animar() otra vez"
  requestAnimationFrame(animar);
}

/**
 * Esta función limpia todo el canvas, como si borráramos un pizarrón.
 * Es necesaria para que cada fotograma de la animación empiece desde cero
 * y no se vean los dibujos anteriores.
 */
function limpiarCanvas() {
  let miCanvas = document.getElementById("billar-canva");
  let ctx = miCanvas.getContext("2d");
  // clearRect(x, y, ancho, alto) borra un rectángulo en el canvas
  // En este caso, borramos todo el canvas desde (0,0) hasta su ancho y alto total
  ctx.clearRect(0, 0, miCanvas.width, miCanvas.height);
}

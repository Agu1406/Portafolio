/*Definimos la función para dibujar la mesa sin troneras*/
function dibujarMesa() {
    //Atrapamos la etiqueta html en una variable
    var miCanvas = document.getElementById("billar-canva"); 
    //creamos un objeto para poder dibujar en 2d
    var dibujo = miCanvas.getContext("2d"); 
    
    // Configuración del rectángulo (mesa de billar)
    //Le damos un color de relleno
    dibujo.fillStyle = "#0a5c36"; 
    //Le damos un color al borde
    dibujo.strokeStyle = "#000000";
    //Grosor del borde
    dibujo.lineWidth = 4; 

    // Guardamos las dimensiones del lienzo en dos variables
    var canvasWidth = miCanvas.width;
    var canvasHeight = miCanvas.height;

    // Definifimos el tamaño de la mesa más pequeño que el lienzo
    var rectWidth = canvasWidth * 0.9; 
    var rectHeight = canvasHeight * 0.9; 

    // Buscamos la mitad del tamaño para centrar la mesa en el lienzo
    var x = (canvasWidth - rectWidth) / 2;
    var y = (canvasHeight - rectHeight) / 2;

    // Radio para las esquinas redondeadas
    var r = 20;

    // Coordenadas de las esquinas.Empezamos en superior-izquierda y en el orden del reloj
    var x0 = x, y0 = y; // Superior izquierda
    var x1 = x + rectWidth, y1 = y; // Superior derecha
    var x2 = x + rectWidth, y2 = y + rectHeight; // Inferior derecha
    var x3 = x, y3 = y + rectHeight; // Inferior izquierda

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
    dibujo.stroke();//Dibuja el grosor
}

/*Definimos la función para dibujar el palo del billar*/
function dibujarPaloDeBillar() {
    // Obtenemos el mismo canvas y contexto
    var miCanvas = document.getElementById("billar-canva");
    var dibujo = miCanvas.getContext("2d");
    
    // Decidimos que estilos va a tener el aplo
    dibujo.fillStyle = "#8B4513";//Color de relleno
    dibujo.strokeStyle = "#5D2906"; //Color del borde
    dibujo.lineWidth = 2; // Grosor del borde
    
    // tamaño del palo
    var paloAncho = 90;  
    var paloAlto = 10; 
    // Posición del palo
    var paloX = 80;      
    var paloY = 100;      
    
    // Dibujamos el palo
    dibujo.beginPath();
    dibujo.rect(paloX, paloY, paloAncho, paloAlto);
    dibujo.fill();
    dibujo.stroke();
}

/*Definimos la función para dibujar la bola de billar*/
function dibujarBolaBlanca() {
    var miCanvas = document.getElementById("billar-canva");
    var dibujo = miCanvas.getContext("2d");
    
    // Estilos para la bola
    dibujo.fillStyle = "white"; // Le damos un color blanco
    dibujo.strokeStyle = "#cccccc"; // Color del borde
    dibujo.lineWidth = 2; // Grosor del borde
    
     var radio = 15; // Radio de la bola
    // Posición indicial de la bola
    var bolaX = 240; 
    var bolaY = 100;
    
    // Dibujar la bola (círculo)
    dibujo.beginPath();
    dibujo.arc(bolaX, bolaY, radio, 0, Math.PI * 2);
    dibujo.fill();
    dibujo.stroke();

}
/*Definimos la función para dibujar el mensaje*/
function dibujarMensaje() {
    var miCanvas = document.getElementById("billar-canva");
    var ctx = miCanvas.getContext("2d");
    
    // Configuración del texto
    ctx.fillStyle = "red"; // Color del texto
    ctx.font = "bold 22px Arial"; // Tamaño más grande
    ctx.textAlign = "center"; // Centrado horizontal
    ctx.strokeStyle = "white";
    ctx.lineWidth = 3;
    ctx.strokeText("BILLAR ACUDULCE PRO 24/25", x, y);
    
    
    // Posición centrada en el canvas
    var x = miCanvas.width / 2;
    var y = 200;
    
    // Dibujamos primero el borde blanco
    ctx.strokeText("BILLAR AGUADULCE PRO 24/25", x, y);
     
    // Dibujamos luego el texto en color rojo
    ctx.fillText("BILLAR AGUADULCE PRO 24/25", x, y);  
}
// Actualizamos el window.onload para dibujar los 3 elementos
window.onload = function() {
    dibujarMesa();
    dibujarPaloDeBillar();
    dibujarBolaBlanca();
    dibujarMensaje();

};
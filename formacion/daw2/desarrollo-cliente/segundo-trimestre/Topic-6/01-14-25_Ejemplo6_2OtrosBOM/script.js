// NAVIGATOR
console.log('¿Navegador?\n' + navigator.userAgent);
console.log('¿Cookies?\n' + navigator.cookieEnabled);
console.log('¿Idioma?\n' + navigator.language);
console.log('¿Conectado a Internet?\n' + navigator.onLine);

// SCREEN
console.log('Primera coordenada útil de arriba: ' + screen.availTop);
console.log('Primera coordenada útil de la izquierda: ' + screen.availLeft);
console.log('Píxeles de altura: ' + screen.availHeight);
console.log('Alto completo: ' + screen.height);
console.log('Píxeles de ancho: ' + screen.availWidth);
console.log('Ancho completo: ' + screen.width);
console.log('Bits para colores de pantalla (2^?): ' + screen.colorDepth);
console.log('Orientación de la pantalla: ' + screen.orientation.type);

// LOCATION 
console.log('URL: ' + location.href);
// Es equivalente a document.location y tiene algunas propiedades como la dirección del jhost, la ruta de directorios, etc.

// HISTORY
console.log(history.length);
//console.log(history.go(-(history.length - 1)));

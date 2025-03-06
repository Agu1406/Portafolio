/**
 * No utilizar variables del tipo var (var = numero), están obsoletas.
 */

document.addEventListener("mousemove", ev);

function ev (raton) {
    
    console.log("Atributo ScreenX: " + raton.screenX);
    console.log("Atributo ScreenY: " + raton.screenY);

    console.log("Atributo clientX: " + raton.clientX);
    console.log("Atributo clientY: " + raton.clientY);

    console.log("Atributo pageX: " + raton.pageX);
    console.log("Atributo pageY: " + raton.pageY);


}
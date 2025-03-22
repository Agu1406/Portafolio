/*
Dentro de la variable "bola" he guardado lo siguiente:

<div class="bola-billar">
<!-- Número de la bola de billar -->
<span class="numero">8</span>
</div>

.bola-billar {
    width: 60px;
    height: 60px;
    background-color: black;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: transform 1s ease;
}
*/
let bola = document.querySelector(".bola-billar");
/*
Dentro de la variable "numero" he guardado lo siguiente:

<span class="numero">8</span>


.numero {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    color: black;
    background-color: white;
    height: 30px;
    width: 30px;
    border-radius: 50%;
    font-weight: bold;
    font-size: 20px;
    transition: transform 1s ease;
}
*/
let numero = document.querySelector(".numero");
/*
Dentro de la variable "texto" he guardado lo siguiente:

<p id="transformacion-texto">
¡Haz click en un botón para aplicar una transformación!
</p>
*/
let texto = document.getElementById("transformacion-texto");

let btnEscalar = document.getElementById("btn-escalar");
let btnDesplazar = document.getElementById("btn-desplazar");
let btnRotar = document.getElementById("btn-rotar");
let reseteada = true;

function resetearBola () {
        /*
    .bola-billar {
    ... Resto de los estilos...
    
    transition: transform 1s ease;
    transform = none;
    }
    */
    bola.style.transform = "none";
    numero.style.transform = "none";
    reseteada = true;
}


btnEscalar.addEventListener("click", function () {
    // Reseteamos la bola a su estado original.
    resetearBola();
    if (reseteada = true) {
    /*
    .bola-billar {
    ... Resto de los estilos...

    transition: transform 1s ease;
    transform = scale(1.5);
    }
    */
    bola.style.transform = "scale(1.5)";
    // Modifica la descripción de la bola de billar
    texto.textContent = "Transformación: Escalado (scale)";
    } else {
        resetearBola();
    }
});
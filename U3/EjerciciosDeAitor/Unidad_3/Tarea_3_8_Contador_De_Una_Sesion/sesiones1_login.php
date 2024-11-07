<?php

redirigir("sesiones1_logout.php"); //simula la redirección a un dashboard

function estoyAutenticado () : bool {
    session_start();
    return isset($_SESSION["user"]);
}

//manda al login si no está autenticado y a la página destino si lo está
function redirigir (string $redireccionCorrecta) : void {
    if(estoyAutenticado()) {
        header("Location: " . $redireccionCorrecta);
    } else {
        header("Location: form_en_uno_Sesion.php");
    }
}

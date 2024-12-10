<?php

if (isset($_POST["idioma"])) {
    setcookie("idioma", $_POST["idioma"], time() + 3600, "/");
    header("Location: redirigir.php"); //recargamos el script y cargamos la cookie en el array
    exit();
}

if (isset($_COOKIE["idioma"])) {
    if ($_COOKIE["idioma"] == "es") {
        header("Location: español.html");
    } elseif ($_COOKIE["idioma"] == "en") {
        header("Location: ingles.html");
    } elseif ($_COOKIE["idioma"] == "ja") {
        header("Location: japones.html");
    }
} else { //por defecto envía a la página en castellano
    header("Location: español.html");
    exit();
}
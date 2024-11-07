<?php
if (isset($_COOKIE['idioma'])) {
    
} else {
    $idiomas = ["español", "ingles", "aleman"];
    if (in_array($idiomas, $_POST['idioma'])) {
        setcookie("idioma", $_POST['idioma'], 1800,"./", "", true, false)
    }
}


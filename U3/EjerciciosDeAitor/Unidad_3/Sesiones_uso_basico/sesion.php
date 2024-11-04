<?php

session_start(); //inicia sesion
if(!isset($_SESSION['contador'])) {
    $_SESSION['contador'] = 0;
} else {
    $_SESSION['contador']++;
}
echo "Holaaa -> " . $_SESSION['contador'];
echo "<br><a href='sesiones_uso_basico2.php'>Siguiente</a>";
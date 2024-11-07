<?php 

// Al igual que en archivo PHP anterior, "iniciamos" una sesión aquí también e imprimmimos la variable contador.
session_start();

// Nos damos cuenta que guarda el valor de la sesión anterior y su valor de "contador".
echo "La variable contador tiene un valor actual de: " . $_SESSION['contador'];

<?php
// Contraseña original
$password = "1234ABCD";

// Hashear la contraseña utilizando bcrypt
$hashedPassword = password_hash($password, PASSWORD_BCRYPT);

// Mostrar la contraseña hasheada
echo "La contraseña hasheada es: " . $hashedPassword;
?>
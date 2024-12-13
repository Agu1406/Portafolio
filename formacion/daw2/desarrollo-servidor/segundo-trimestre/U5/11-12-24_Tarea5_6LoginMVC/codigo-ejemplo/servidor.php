<?php
header('Content-Type: text/plain');

$datos = json_decode(file_get_contents('php://input'), true);

$usuarioValido = "agu1406";
$contrasenaValida = "1234";

$response = '';

if ($datos['usuario'] === $usuarioValido && $datos['contrasena'] === $contrasenaValida) {
    $response = 'Login exitoso';
} else {
    $response = 'Usuario o contraseña incorrectos';
}

echo $response;
?>

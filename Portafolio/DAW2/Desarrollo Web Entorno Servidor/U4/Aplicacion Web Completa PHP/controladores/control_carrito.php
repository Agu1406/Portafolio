<?php
// Iniciar la sesión
session_start(); 

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");
    exit(); // Asegurarse de salir del script
}

// Importamos ambos, la conexión-instancia a la base de datos y el CRUD de productos.
include_once __DIR__ . "/../modelos/conexion_bd.php";
include_once __DIR__ . "/../modelos/crud_carrito.php";


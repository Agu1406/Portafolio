<?php

include __DIR__ . '/../Connection/bd_conexion.php';

//Consulta para mostrar todos los productos del carrito
function obtenerProductosEnCarrito(){

    //Llamamos a la función para obtener los datos de conexión.
    $bd = dataBaseConnection();

    $idSesion = $_SESSION['idEmpresa'];

    $sql = "SELECT p.ID_Productos, p.nombre, p.precio, sc.Cantidad 
    FROM ShoppingCart sc
    INNER JOIN Productos p ON sc.Productos_ID_Productos = p.ID_Productos
    WHERE sc.Empresa_ID_Empresa = :idEmpresa";

    $stmt = $bd->prepare($sql);
    //Asocia la variable idEmpresa a idEmpresa como int, para evitar SQL Injection
    $stmt->bindParam(':idEmpresa', $idSesion, PDO::PARAM_INT);
    $stmt->execute();
    
    //Devuelve todos los productos encontrados
    return $stmt->fetchAll(PDO::FETCH_ASSOC);
}
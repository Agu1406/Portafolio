<?php
//
include './Connection/bd_conexion.php';

//Consulta para mostrar todos los productos
function productList(){
    //Llamamos a la función para obtener los datos de conexión.
    $bd = dataBaseConnection();
    $sql = "SELECT ID_Productos, nombre, precio, imagen FROM productos";
    $stmt = $bd->prepare($sql);
    $stmt = $bd->query($sql);
    return $productos = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
}
<?php
// Importamos la conexión de la base de datos.
include_once "conexion_bd.php";

// Al haber importado el script, tenemos acceso a sus métodos y funciones, los usamos.
try {
    // Obtener la instancia de la clase ConexionBaseDeDatos
    $baseDeDatos = ConexionBaseDeDatos::obtenerInstancia();
    // Obtener la conexión de la base de datos desde la instancia.
    $conexion = $baseDeDatos->obtenerConexion();
} catch (Exception $e) {
    // Manejo de errores más específico
    die("Error: No se ha podido conectar a la base de datos, " . $e->getMessage());
}

class categoriaCRUD {
    public static function crearCategoria ($nombre_categoria) {
        // Obtenemos la instancia y la conexion de la base de datos.
        $conexion = ConexionBaseDeDatos::obtenerInstancia() -> obtenerConexion();

        // Encerramos todo en un try-catch que en caso de excepción hace rollBack y deshace los cambios.
        try {
            // Iniciamos una transacción para manejar con cautela los cambios a la base de datos.
            $conexion -> beginTransaction();

            /**
             * Verificamos si ya existe la categoria en la base de datos creada, si es así
             * avisamos del error, si no, el script continua y la crea.
             */
            
             // Guardamos en una variable todos los resultados que concuerden como un número.
             $sql = $conexion -> prepare("SELECT COUNT(*) FROM Categoria WHERE nombre_categoria = :nombre_categoria");

             // Asociamos a ":nombre_categoria" el obtenido en los argumentos del método.
             $sql -> bindParam(":nombre_categoria", $nombre_categoria);

             // Ejecutamos la consulta
             $sql -> execute();

             // Guardamos el resultado de la consulta en una variable
             $categoriaExistente = $sql -> fetchColumn();

             if ($categoriaExistente > 0) {
                // Deshacemos los cambios que se hayan hecho con rollBack.
                $conexion -> rollBack();
                
                // Avisamos por pantalla que esa categoría ya existe
                return "Ya existe una categoría con ese nombre.";
            }

            // Si no existia, procedemos a insertar la nueva categoria en la base de datos.
            $sql = $conexion -> prepare("INSERT INTO Categoria (nombre_categoria)
            VALUES (:nombre_categoria)");

            // Remplazamos con bindParam el valor ":nombre_categoria".
            $sql -> bindParam(":nombre_categoria", $nombre_categoria);

            // Ejecutamos la consulta agregando la nueva categoría a la base de datos.
            $sql -> execute();

            // Si todo ha ido bien hasta aquí hacemos el commit de la transacción.
            $conexion -> commit();

            return true;

        } catch (Exception $e) {
            // Si ocurre un error, se hace rollback, se revierte la transacción.
            $conexion -> rollBack();

            // Devuelve un mensaje de error en caso de excepción.
            return "Error al insertar la categoría: " . $e -> getMessage();
        }
    }

    public static function leerCategorias () {        
        // Obtenemos la conexión de la base de datos.
        $conexion = ConexionBaseDeDatos::obtenerInstancia()->obtenerConexion();

        try {
            // Preparamos la consulta para obtener todas las categorías
            $sql = $conexion->prepare("SELECT id_categoria, nombre_categoria FROM Categoria");

            // Ejecutamos la consulta
            $sql->execute();

            // Obtenemos todos los resultados en un array asociativo
            $categorias = $sql->fetchAll(PDO::FETCH_ASSOC);

            // Si no hay categorías, devolvemos un array vacío
            if (count($categorias) == 0) {
                return "No hay categorías disponibles.";
            }

            // Devolvemos el array con las categorías
            return $categorias;

        } catch (Exception $e) {
            // En caso de error, lo manejamos y devolvemos un mensaje
            return "Error al leer las categorías: " . $e->getMessage();
        }
    }
}
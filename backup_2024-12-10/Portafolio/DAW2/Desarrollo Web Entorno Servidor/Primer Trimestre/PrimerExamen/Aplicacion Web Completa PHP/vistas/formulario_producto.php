<?php
// Iniciar sesión
session_start();

// Verificar si el usuario no ha iniciado sesión
if (!isset($_SESSION['usuario'])) {
    // Si no ha iniciado sesión, redirigir al login
    header("Location: formulario_login.php");

    // Sale del script luego de redirigir.
    exit();
}

// Incluir el archivo que contiene la clase categoriaCRUD
include_once __DIR__ . "/../modelos/crud_categorias.php";

// Obtener las categorías
$categorias = categoriaCRUD::leerCategorias();

// Verifica si hay categorías para mostrar en el formulario
if (count($categorias) == 0) {
    echo "[1] No hay categorías disponibles.";
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Producto</title>
    <link rel="stylesheet" href="../archivos/estilos.css">
</head>
<body>
    <?php include_once "header.php"; ?>
    <main>
    <div class="contenedor-principal">
        <h1>Bienvenido a tumercado.com</h1>
        <div class="seccion">
            <div class="izquierda">
                <h1>¡Añade un nuevo producto a tu tienda!</h1>
                <p>&emsp; Rellena los datos del producto para incluirlo en nuestro catálogo de productos.</p>
            </div>
            <div class="derecha">
                <form class="login-form" action="../controladores/agregar_producto.php" method="post" enctype="multipart/form-data">
                    <label for="nombre_producto">Nombre del Producto</label>
                    <input type="text" name="nombre_producto" id="nombre_producto" placeholder="Ingresa el nombre del producto" required>
                    <br>
                    <label for="descripcion_producto">Descripción</label>
                    <input type="text" name="descripcion_producto" id="descripcion_producto" placeholder="Ingresa la descripción del producto">
                    <br>
                    <label for="precio_producto">Precio</label>
                    <input type="number" name="precio_producto" id="precio_producto" step="0.01" placeholder="Precio del producto" required>
                    <br>
                    <label for="stock">Stock</label>
                    <input type="number" name="stock" id="stock" placeholder="Cantidad disponible" required>
                    <br>
                    <label for="categoria">Categoría</label>
                    <select name="categoria" id="categoria">
                        <!-- Aquí se insertan las categorías obtenidas directamente del CRUD -->
                        <?php if (is_array($categorias)): ?>
                            <?php foreach ($categorias as $categoria): ?>
                                <option value="<?= $categoria['codigo_categoria']; ?>"><?= $categoria['nombre_categoria']; ?></option>
                            <?php endforeach; ?>
                        <?php else: ?>
                            <option value="">No hay categorías disponibles.</option>
                        <?php endif; ?>
                    </select>
                    <br>
                    <label for="imagen">Imagen del Producto</label>
                    <input type="file" name="imagen" id="imagen" accept="image/*" required>
                    <br>
                    
                    <input type="submit" value="Añadir Producto">
                </form>
            </div>
        </div>
    </div>
    </main>
    <?php include_once "footer.php"; ?>
</body>
</html>
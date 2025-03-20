<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
</head>
<body>
    <div class="container py-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1>Gestión de Productos</h1>
            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/productos/agregar" class="btn btn-success">
                <i class="fas fa-plus"></i> Añadir Producto
            </a>
        </div>

        <?php if (isset($_GET['mensaje']) && $_GET['mensaje'] === 'producto_eliminado'): ?>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <i class="fas fa-check-circle me-2"></i> El producto ha sido eliminado correctamente.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <?php endif; ?>

        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Imagen</th>
                        <th>Nombre</th>
                        <th>Categoría</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <?php if (isset($productos) && !empty($productos)): ?>
                        <?php foreach ($productos as $producto): ?>
                            <tr>
                                <td><?php echo $producto['id']; ?></td>
                                <td>
                                    <?php 
                                    $nombreImagen = 'default.jpg';
                                    switch(strtolower($producto['nombre'])) {
                                        case 'aceite esencial de lavanda':
                                            $nombreImagen = 'aceite-lavanda.jpg';
                                            break;
                                        case 'vitamina c 1000mg':
                                            $nombreImagen = 'vitamina-c.jpg';
                                            break;
                                        case 'crema hidratante natural':
                                            $nombreImagen = 'crema-hidratante.jpg';
                                            break;
                                        case 'gel de aloe vera':
                                            $nombreImagen = 'aloe-vera.jpg';
                                            break;
                                    }
                                    $rutaImagen = 'imagenes/productos/' . $nombreImagen;
                                    $rutaCompleta = $GLOBALS['configuracion']['rutaBase'] . '/publico/' . $rutaImagen;
                                    ?>
                                    <img src="<?php echo $rutaCompleta; ?>" alt="<?php echo htmlspecialchars($producto['nombre']); ?>" style="height: 50px; width: 50px; object-fit: cover;">
                                </td>
                                <td><?php echo htmlspecialchars($producto['nombre']); ?></td>
                                <td><?php echo htmlspecialchars($producto['categoria_nombre']); ?></td>
                                <td><?php echo number_format($producto['precio'], 2); ?> €</td>
                                <td><?php echo $producto['stock']; ?></td>
                                <td>
                                    <?php if ($producto['activo']): ?>
                                        <span class="badge bg-success">Activo</span>
                                    <?php else: ?>
                                        <span class="badge bg-danger">Inactivo</span>
                                    <?php endif; ?>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/productos/editar?id=<?php echo $producto['id']; ?>" class="btn btn-sm btn-primary">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                        <button type="button" class="btn btn-sm btn-danger" onclick="confirmarBorrado(<?php echo $producto['id']; ?>)">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        <?php endforeach; ?>
                    <?php else: ?>
                        <tr>
                            <td colspan="8" class="text-center">No hay productos disponibles</td>
                        </tr>
                    <?php endif; ?>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal de confirmación de borrado -->
    <div class="modal fade" id="modalConfirmarBorrado" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Confirmar borrado</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ¿Estás seguro de que deseas eliminar este producto? Esta acción no se puede deshacer.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <form id="formBorrarProducto" action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/productos/borrar" method="post" style="display: inline;">
                        <input type="hidden" name="id" id="productoIdBorrar">
                        <button type="submit" class="btn btn-danger">Eliminar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>

    <script>
        function confirmarBorrado(id) {
            document.getElementById('productoIdBorrar').value = id;
            var modal = new bootstrap.Modal(document.getElementById('modalConfirmarBorrado'));
            modal.show();
        }
    </script>
</body>
</html> 
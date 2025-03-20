<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
</head>
<body>
    <div class="container py-5">
        <h1 class="mb-4">Panel de Administración</h1>
        
        <div class="row">
            <div class="col-md-6 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Gestión de Productos</h5>
                        <p class="card-text">Administra los productos de la tienda: añadir, editar o eliminar productos.</p>
                        <div class="d-flex gap-2">
                            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/productos" class="btn btn-primary">
                                <i class="fas fa-list"></i> Ver Productos
                            </a>
                            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/productos/agregar" class="btn btn-success">
                                <i class="fas fa-plus"></i> Añadir Producto
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-md-6 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Gestión de Pedidos</h5>
                        <p class="card-text">Visualiza y gestiona los pedidos realizados por los clientes.</p>
                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/pedidos" class="btn btn-primary">
                            <i class="fas fa-box"></i> Ver Pedidos
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
</body>
</html> 
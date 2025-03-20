<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
    <title>Subir Imagen - Panel de Administración</title>
</head>
<body>
    <div class="container py-5">
        <div class="row">
            <div class="col-md-8 mx-auto">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Subir Imagen de Producto</h4>
                    </div>
                    <div class="card-body">
                        <?php if (isset($mensaje)): ?>
                            <div class="alert alert-<?php echo $mensaje['tipo']; ?> alert-dismissible fade show" role="alert">
                                <?php echo $mensaje['texto']; ?>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        <?php endif; ?>
                        
                        <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/subir-imagen" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label for="producto_id" class="form-label">Producto</label>
                                <select class="form-select" id="producto_id" name="producto_id" required>
                                    <option value="">Selecciona un producto</option>
                                    <?php foreach ($productos as $producto): ?>
                                        <option value="<?php echo $producto['id']; ?>"><?php echo htmlspecialchars($producto['nombre']); ?></option>
                                    <?php endforeach; ?>
                                </select>
                            </div>
                            
                            <div class="mb-3">
                                <label for="imagen" class="form-label">Imagen</label>
                                <input type="file" class="form-control" id="imagen" name="imagen" accept="image/*" required>
                                <div class="form-text">Formatos permitidos: JPG, PNG, GIF. Tamaño máximo: 2MB.</div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="es_principal" class="form-check-label">
                                    <input type="checkbox" class="form-check-input" id="es_principal" name="es_principal" value="1">
                                    Establecer como imagen principal
                                </label>
                            </div>
                            
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-upload me-2"></i> Subir Imagen
                                </button>
                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/productos" class="btn btn-outline-secondary">
                                    <i class="fas fa-arrow-left me-2"></i> Volver
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
</body>
</html> 
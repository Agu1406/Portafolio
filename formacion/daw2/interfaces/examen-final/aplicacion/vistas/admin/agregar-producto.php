<?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>

<div class="container mt-4">
    <h1><?php echo $tituloPagina; ?></h1>
    
    <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/productos/guardar" method="POST" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre del producto</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        
        <div class="mb-3">
            <label for="categoria_id" class="form-label">Categoría</label>
            <select class="form-select" id="categoria_id" name="categoria_id" required>
                <option value="">Selecciona una categoría</option>
                <?php foreach ($categorias as $categoria): ?>
                    <option value="<?php echo $categoria['id']; ?>"><?php echo htmlspecialchars($categoria['nombre']); ?></option>
                <?php endforeach; ?>
            </select>
        </div>
        
        <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
        </div>
        
        <div class="mb-3">
            <label for="precio" class="form-label">Precio</label>
            <input type="number" class="form-control" id="precio" name="precio" step="0.01" required>
        </div>
        
        <div class="mb-3">
            <label for="stock" class="form-label">Stock</label>
            <input type="number" class="form-control" id="stock" name="stock" required>
        </div>
        
        <div class="mb-3">
            <label for="imagen" class="form-label">Imagen principal</label>
            <input type="file" class="form-control" id="imagen" name="imagen" accept="image/*" required>
        </div>
        
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="destacado" name="destacado" value="1">
            <label class="form-check-label" for="destacado">Producto destacado</label>
        </div>
        
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="activo" name="activo" value="1" checked>
            <label class="form-check-label" for="activo">Producto activo</label>
        </div>
        
        <button type="submit" class="btn btn-primary">Guardar producto</button>
        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/productos" class="btn btn-secondary">Cancelar</a>
    </form>
</div>

<?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?> 
<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
    <style>
        /* Estilos para limitar la altura de las secciones de categorías y filtros */
        .sidebar-section {
            max-height: 400px;
            overflow-y: auto;
            margin-bottom: 20px;
        }
        
        /* Estilizar la barra de desplazamiento */
        .sidebar-section::-webkit-scrollbar {
            width: 8px;
        }
        
        .sidebar-section::-webkit-scrollbar-track {
            background: #f1f1f1;
            border-radius: 4px;
        }
        
        .sidebar-section::-webkit-scrollbar-thumb {
            background: #888;
            border-radius: 4px;
        }
        
        .sidebar-section::-webkit-scrollbar-thumb:hover {
            background: #555;
        }
    </style>
</head>
<body>
    <!-- Cabecera de la página -->
    <div class="bg-light py-4">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h1 class="mb-0">
                        <?php if (isset($categoriaActual)): ?>
                            <?php echo htmlspecialchars($categoriaActual['nombre']); ?>
                        <?php else: ?>
                            Todos los productos
                        <?php endif; ?>
                    </h1>
                    <?php if (isset($categoriaActual) && isset($categoriaActual['descripcion'])): ?>
                        <p class="lead mb-0"><?php echo htmlspecialchars($categoriaActual['descripcion']); ?></p>
                    <?php endif; ?>
                </div>
                <div class="col-md-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 justify-content-md-end">
                            <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/">Inicio</a></li>
                            <?php if (isset($categoriaActual)): ?>
                                <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos">Productos</a></li>
                                <li class="breadcrumb-item active" aria-current="page"><?php echo htmlspecialchars($categoriaActual['nombre']); ?></li>
                            <?php else: ?>
                                <li class="breadcrumb-item active" aria-current="page">Productos</li>
                            <?php endif; ?>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>

    <!-- Contenido principal -->
    <div class="container py-5">
        <div class="row">
            <!-- Filtros y categorías -->
            <div class="col-md-3 mb-4">
                <div class="card shadow-sm sidebar-section">
                    <div class="card-header bg-primary text-white">
                        <h5 class="mb-0">Categorías</h5>
                    </div>
                    <div class="card-body">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item <?php echo !isset($categoriaActual) ? 'active' : ''; ?>">
                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos" class="text-decoration-none <?php echo !isset($categoriaActual) ? 'text-white' : 'text-dark'; ?>">
                                    Todos los productos
                                </a>
                            </li>
                            <?php if (isset($categorias) && !empty($categorias)): ?>
                                <?php foreach ($categorias as $categoria): ?>
                                    <li class="list-group-item <?php echo (isset($categoriaActual) && $categoriaActual['id'] == $categoria['id']) ? 'active' : ''; ?>">
                                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos?categoria=<?php echo $categoria['id']; ?>" class="text-decoration-none <?php echo (isset($categoriaActual) && $categoriaActual['id'] == $categoria['id']) ? 'text-white' : 'text-dark'; ?>">
                                            <?php echo htmlspecialchars($categoria['nombre']); ?>
                                        </a>
                                    </li>
                                <?php endforeach; ?>
                            <?php endif; ?>
                        </ul>
                    </div>
                </div>

                <div class="card shadow-sm mt-4 sidebar-section">
                    <div class="card-header bg-primary text-white">
                        <h5 class="mb-0">Filtros</h5>
                    </div>
                    <div class="card-body">
                        <form>
                            <?php if (isset($categoriaActual)): ?>
                                <input type="hidden" name="categoria" value="<?php echo $categoriaActual['id']; ?>">
                            <?php endif; ?>
                            
                            <div class="mb-3">
                                <label for="orden" class="form-label">Ordenar por</label>
                                <select class="form-select" id="orden" name="orden">
                                    <option value="nombre_asc" <?php echo (isset($_GET['orden']) && $_GET['orden'] == 'nombre_asc') ? 'selected' : ''; ?>>Nombre (A-Z)</option>
                                    <option value="nombre_desc" <?php echo (isset($_GET['orden']) && $_GET['orden'] == 'nombre_desc') ? 'selected' : ''; ?>>Nombre (Z-A)</option>
                                    <option value="precio_asc" <?php echo (isset($_GET['orden']) && $_GET['orden'] == 'precio_asc') ? 'selected' : ''; ?>>Precio (menor a mayor)</option>
                                    <option value="precio_desc" <?php echo (isset($_GET['orden']) && $_GET['orden'] == 'precio_desc') ? 'selected' : ''; ?>>Precio (mayor a menor)</option>
                                    <option value="nuevos" <?php echo (isset($_GET['orden']) && $_GET['orden'] == 'nuevos') ? 'selected' : ''; ?>>Más recientes</option>
                                </select>
                            </div>
                            
                            <div class="mb-3">
                                <label for="precio_min" class="form-label">Precio mínimo</label>
                                <input type="range" class="form-range" id="precio_min" name="precio_min" min="0" max="100" step="5" value="<?php echo isset($_GET['precio_min']) ? (int)$_GET['precio_min'] : 0; ?>">
                                <div class="d-flex justify-content-between">
                                    <span>0€</span>
                                    <span id="precio_min_valor"><?php echo isset($_GET['precio_min']) ? (int)$_GET['precio_min'] : 0; ?>€</span>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="precio_max" class="form-label">Precio máximo</label>
                                <input type="range" class="form-range" id="precio_max" name="precio_max" min="0" max="100" step="5" value="<?php echo isset($_GET['precio_max']) ? (int)$_GET['precio_max'] : 100; ?>">
                                <div class="d-flex justify-content-between">
                                    <span>0€</span>
                                    <span id="precio_max_valor"><?php echo isset($_GET['precio_max']) ? (int)$_GET['precio_max'] : 100; ?>€</span>
                                </div>
                            </div>
                            
                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-filter me-2"></i> Aplicar filtros
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            
            <!-- Listado de productos -->
            <div class="col-md-9">
                <?php if (isset($productos) && !empty($productos)): ?>
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <p class="mb-0">Mostrando <?php echo count($productos); ?> productos</p>
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-outline-primary active" id="viewGrid">
                                <i class="fas fa-th"></i>
                            </button>
                            <button type="button" class="btn btn-outline-primary" id="viewList">
                                <i class="fas fa-list"></i>
                            </button>
                        </div>
                    </div>
                    
                    <div class="row" id="productosGrid">
                        <?php foreach ($productos as $producto): ?>
                            <div class="col-md-4 mb-4">
                                <div class="card h-100">
                                    <div class="card-img-container">
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
                                        <img src="<?php echo $rutaCompleta; ?>" class="card-img-top" alt="<?php echo htmlspecialchars($producto['nombre']); ?>" style="height: 200px; object-fit: cover;">
                                    </div>
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title"><?php echo htmlspecialchars($producto['nombre']); ?></h5>
                                        <p class="card-text"><?php echo htmlspecialchars($producto['descripcion_corta']); ?></p>
                                        <div class="mt-auto">
                                            <p class="precio"><?php echo number_format($producto['precio'], 2); ?> €</p>
                                            <div class="d-flex justify-content-between">
                                                <a href="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/producto/<?php echo $producto['id']; ?>" class="btn btn-sm btn-primary">Ver detalles</a>
                                                <form action="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/carrito/agregar" method="post">
                                                    <input type="hidden" name="idProducto" value="<?php echo $producto['id']; ?>">
                                                    <input type="hidden" name="cantidad" value="1">
                                                    <button type="submit" class="btn btn-sm btn-success">
                                                        <i class="fas fa-cart-plus"></i> Añadir
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <?php endforeach; ?>
                    </div>
                    
                    <div class="d-none" id="productosList">
                        <?php foreach ($productos as $producto): ?>
                            <div class="card mb-3 shadow-sm">
                                <div class="row g-0">
                                    <div class="col-md-3">
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
                                        <img src="<?php echo $rutaCompleta; ?>" class="img-fluid rounded-start h-100 object-fit-cover" alt="<?php echo htmlspecialchars($producto['nombre']); ?>">
                                    </div>
                                    <div class="col-md-9">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between align-items-start">
                                                <div>
                                                    <span class="producto-categoria"><?php echo htmlspecialchars($producto['categoria_nombre']); ?></span>
                                                    <h5 class="card-title"><?php echo htmlspecialchars($producto['nombre']); ?></h5>
                                                </div>
                                                <p class="producto-precio"><?php echo number_format($producto['precio'], 2, ',', '.'); ?> €</p>
                                            </div>
                                            <p class="card-text"><?php echo htmlspecialchars($producto['descripcion_corta']); ?></p>
                                            <div class="d-flex justify-content-end gap-2">
                                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/producto/detalle?id=<?php echo $producto['id']; ?>" class="btn btn-sm btn-outline-primary">
                                                    <i class="fas fa-eye"></i> Ver detalles
                                                </a>
                                                <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/carrito/agregar" method="post">
                                                    <input type="hidden" name="idProducto" value="<?php echo $producto['id']; ?>">
                                                    <input type="hidden" name="cantidad" value="1">
                                                    <button type="submit" class="btn btn-sm btn-primary">
                                                        <i class="fas fa-cart-plus"></i> Añadir
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <?php endforeach; ?>
                    </div>
                <?php else: ?>
                    <div class="alert alert-info">
                        <i class="fas fa-info-circle me-2"></i> No se encontraron productos.
                    </div>
                <?php endif; ?>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Botones de cambio de vista
            const viewGridBtn = document.getElementById('viewGrid');
            const viewListBtn = document.getElementById('viewList');
            const productosGrid = document.getElementById('productosGrid');
            const productosList = document.getElementById('productosList');

            // Función para cambiar la vista
            function cambiarVista(tipo) {
                if (tipo === 'grid') {
                    viewGridBtn.classList.add('active');
                    viewListBtn.classList.remove('active');
                    productosGrid.classList.remove('d-none');
                    productosList.classList.add('d-none');
                } else {
                    viewListBtn.classList.add('active');
                    viewGridBtn.classList.remove('active');
                    productosList.classList.remove('d-none');
                    productosGrid.classList.add('d-none');
                }
            }

            // Eventos de click para los botones
            viewGridBtn.addEventListener('click', () => cambiarVista('grid'));
            viewListBtn.addEventListener('click', () => cambiarVista('list'));

            // Actualizar valores de los rangos de precio
            const precioMin = document.getElementById('precio_min');
            const precioMax = document.getElementById('precio_max');
            const precioMinValor = document.getElementById('precio_min_valor');
            const precioMaxValor = document.getElementById('precio_max_valor');
            
            if (precioMin && precioMinValor) {
                precioMin.addEventListener('input', function() {
                    precioMinValor.textContent = this.value + '€';
                    
                    // Asegurarse de que el precio mínimo no sea mayor que el máximo
                    if (parseInt(precioMin.value) > parseInt(precioMax.value)) {
                        precioMax.value = precioMin.value;
                        precioMaxValor.textContent = precioMax.value + '€';
                    }
                });
            }
            
            if (precioMax && precioMaxValor) {
                precioMax.addEventListener('input', function() {
                    precioMaxValor.textContent = this.value + '€';
                    
                    // Asegurarse de que el precio máximo no sea menor que el mínimo
                    if (parseInt(precioMax.value) < parseInt(precioMin.value)) {
                        precioMin.value = precioMax.value;
                        precioMinValor.textContent = precioMin.value + '€';
                    }
                });
            }
        });
    </script>
</body>
</html> 
<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
</head>
<body>
    <!-- Contenido principal -->
    <div class="container py-5">
        <?php if (isset($producto)): ?>
            <!-- Migas de pan -->
            <nav aria-label="breadcrumb" class="mb-4">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/">Inicio</a></li>
                    <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/productos">Productos</a></li>
                    <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/productos?categoria=<?php echo $producto['categoria_id']; ?>"><?php echo htmlspecialchars($producto['categoria_nombre']); ?></a></li>
                    <li class="breadcrumb-item active" aria-current="page"><?php echo htmlspecialchars($producto['nombre']); ?></li>
                </ol>
            </nav>
            
            <div class="row">
                <!-- Imágenes del producto -->
                <div class="col-md-6 mb-4">
                    <div class="producto-imagen-principal mb-3">
                        <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/<?php echo isset($producto['imagen_principal']) ? $producto['imagen_principal'] : 'imagenes/productos/default.jpg'; ?>" class="img-fluid" alt="<?php echo htmlspecialchars($producto['nombre']); ?>">
                    </div>
                    
                    <!-- Miniaturas (simuladas, en un caso real vendrían de la base de datos) -->
                    <div class="producto-miniaturas">
                        <div class="producto-miniatura active">
                            <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/<?php echo isset($producto['imagen_principal']) ? $producto['imagen_principal'] : 'imagenes/productos/default.jpg'; ?>" alt="<?php echo htmlspecialchars($producto['nombre']); ?>">
                        </div>
                        <div class="producto-miniatura">
                            <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/imagenes/productos/miniatura1.jpg" alt="<?php echo htmlspecialchars($producto['nombre']); ?>">
                        </div>
                        <div class="producto-miniatura">
                            <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/imagenes/productos/miniatura2.jpg" alt="<?php echo htmlspecialchars($producto['nombre']); ?>">
                        </div>
                        <div class="producto-miniatura">
                            <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/imagenes/productos/miniatura3.jpg" alt="<?php echo htmlspecialchars($producto['nombre']); ?>">
                        </div>
                    </div>
                </div>
                
                <!-- Información del producto -->
                <div class="col-md-6">
                    <h1 class="mb-2"><?php echo htmlspecialchars($producto['nombre']); ?></h1>
                    <span class="producto-categoria mb-3 d-inline-block"><?php echo htmlspecialchars($producto['categoria_nombre']); ?></span>
                    
                    <div class="mb-3">
                        <div class="d-flex align-items-center">
                            <div class="me-3">
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star-half-alt text-warning"></i>
                                <span class="ms-2 text-muted">(4.5/5 - 12 valoraciones)</span>
                            </div>
                        </div>
                    </div>
                    
                    <p class="producto-precio fs-3 mb-3"><?php echo number_format($producto['precio'], 2, ',', '.'); ?> €</p>
                    
                    <?php if (isset($producto['precio_oferta']) && $producto['precio_oferta'] > 0): ?>
                        <p class="mb-3">
                            <span class="text-decoration-line-through text-muted me-2"><?php echo number_format($producto['precio'], 2, ',', '.'); ?> €</span>
                            <span class="badge bg-danger">-<?php echo round((1 - $producto['precio_oferta'] / $producto['precio']) * 100); ?>%</span>
                        </p>
                    <?php endif; ?>
                    
                    <p class="mb-4"><?php echo htmlspecialchars($producto['descripcion_corta']); ?></p>
                    
                    <div class="mb-4">
                        <div class="d-flex align-items-center mb-3">
                            <span class="me-3">Disponibilidad:</span>
                            <?php if (isset($producto['stock']) && $producto['stock'] > 0): ?>
                                <span class="badge bg-success">En stock (<?php echo $producto['stock']; ?> unidades)</span>
                            <?php else: ?>
                                <span class="badge bg-danger">Agotado</span>
                            <?php endif; ?>
                        </div>
                        
                        <div class="d-flex align-items-center">
                            <span class="me-3">Código:</span>
                            <span><?php echo isset($producto['codigo']) ? htmlspecialchars($producto['codigo']) : 'PROD-' . $producto['id']; ?></span>
                        </div>
                    </div>
                    
                    <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/carrito/agregar" method="post" class="mb-4">
                        <input type="hidden" name="idProducto" value="<?php echo $producto['id']; ?>">
                        
                        <div class="row g-3 align-items-center mb-3">
                            <div class="col-auto">
                                <label for="cantidad" class="col-form-label">Cantidad:</label>
                            </div>
                            <div class="col-auto">
                                <div class="input-group">
                                    <button type="button" class="btn btn-outline-secondary" id="decrementarCantidad">
                                        <i class="fas fa-minus"></i>
                                    </button>
                                    <input type="number" class="form-control text-center" id="cantidad" name="cantidad" value="1" min="1" max="<?php echo isset($producto['stock']) ? $producto['stock'] : 10; ?>">
                                    <button type="button" class="btn btn-outline-secondary" id="incrementarCantidad">
                                        <i class="fas fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary btn-lg">
                                <i class="fas fa-cart-plus me-2"></i> Añadir al carrito
                            </button>
                            <button type="button" class="btn btn-outline-primary">
                                <i class="fas fa-heart me-2"></i> Añadir a favoritos
                            </button>
                        </div>
                    </form>
                    
                    <div class="d-flex flex-wrap gap-3 mb-4">
                        <div>
                            <i class="fas fa-truck text-primary me-2"></i>
                            <span>Envío en 24/48h</span>
                        </div>
                        <div>
                            <i class="fas fa-undo text-primary me-2"></i>
                            <span>Devolución gratuita</span>
                        </div>
                        <div>
                            <i class="fas fa-lock text-primary me-2"></i>
                            <span>Pago seguro</span>
                        </div>
                    </div>
                    
                    <div class="d-flex gap-3">
                        <a href="#" class="text-decoration-none">
                            <i class="fas fa-share-alt me-1"></i> Compartir
                        </a>
                        <a href="#" class="text-decoration-none">
                            <i class="fas fa-question-circle me-1"></i> Preguntar
                        </a>
                    </div>
                </div>
            </div>
            
            <!-- Descripción detallada y pestañas -->
            <div class="row mt-5">
                <div class="col-12">
                    <ul class="nav nav-tabs" id="productTabs" role="tablist">
                        <li class="nav-item" role="presentation">
                            <button class="nav-link active" id="description-tab" data-bs-toggle="tab" data-bs-target="#description" type="button" role="tab" aria-controls="description" aria-selected="true">Descripción</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="details-tab" data-bs-toggle="tab" data-bs-target="#details" type="button" role="tab" aria-controls="details" aria-selected="false">Detalles</button>
                        </li>
                        <li class="nav-item" role="presentation">
                            <button class="nav-link" id="reviews-tab" data-bs-toggle="tab" data-bs-target="#reviews" type="button" role="tab" aria-controls="reviews" aria-selected="false">Valoraciones</button>
                        </li>
                    </ul>
                    <div class="tab-content p-4 border border-top-0 rounded-bottom" id="productTabsContent">
                        <div class="tab-pane fade show active" id="description" role="tabpanel" aria-labelledby="description-tab">
                            <h4>Descripción del producto</h4>
                            <p><?php echo nl2br(htmlspecialchars($producto['descripcion'])); ?></p>
                        </div>
                        <div class="tab-pane fade" id="details" role="tabpanel" aria-labelledby="details-tab">
                            <h4>Características</h4>
                            <table class="table table-striped">
                                <tbody>
                                    <tr>
                                        <th scope="row">Marca</th>
                                        <td>NaturalShop</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Origen</th>
                                        <td>España</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Categoría</th>
                                        <td><?php echo htmlspecialchars($producto['categoria_nombre']); ?></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Código</th>
                                        <td><?php echo isset($producto['codigo']) ? htmlspecialchars($producto['codigo']) : 'PROD-' . $producto['id']; ?></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
                            <h4>Valoraciones de clientes</h4>
                            <div class="mb-4">
                                <div class="d-flex align-items-center mb-2">
                                    <div class="me-3">
                                        <i class="fas fa-star text-warning"></i>
                                        <i class="fas fa-star text-warning"></i>
                                        <i class="fas fa-star text-warning"></i>
                                        <i class="fas fa-star text-warning"></i>
                                        <i class="fas fa-star-half-alt text-warning"></i>
                                    </div>
                                    <span class="fw-bold">4.5 de 5</span>
                                </div>
                                <p>Basado en 12 valoraciones</p>
                            </div>
                            
                            <div class="mb-4">
                                <div class="card mb-3">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between mb-2">
                                            <div>
                                                <span class="fw-bold">María García</span>
                                                <div>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                </div>
                                            </div>
                                            <small class="text-muted">Hace 2 días</small>
                                        </div>
                                        <p class="card-text">Excelente producto, cumple con todas mis expectativas. Lo recomiendo totalmente.</p>
                                    </div>
                                </div>
                                
                                <div class="card mb-3">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between mb-2">
                                            <div>
                                                <span class="fw-bold">Carlos Rodríguez</span>
                                                <div>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="far fa-star text-warning"></i>
                                                </div>
                                            </div>
                                            <small class="text-muted">Hace 1 semana</small>
                                        </div>
                                        <p class="card-text">Muy buen producto, aunque el envío tardó un poco más de lo esperado.</p>
                                    </div>
                                </div>
                                
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between mb-2">
                                            <div>
                                                <span class="fw-bold">Laura Martínez</span>
                                                <div>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star text-warning"></i>
                                                    <i class="fas fa-star-half-alt text-warning"></i>
                                                </div>
                                            </div>
                                            <small class="text-muted">Hace 2 semanas</small>
                                        </div>
                                        <p class="card-text">Me encanta este producto, lo uso a diario y los resultados son visibles. Repetiré sin duda.</p>
                                    </div>
                                </div>
                            </div>
                            
                            <a href="#" class="btn btn-outline-primary">Ver todas las valoraciones</a>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Productos relacionados -->
            <?php if (isset($productosRelacionados) && !empty($productosRelacionados)): ?>
                <div class="mt-5">
                    <h3 class="mb-4">Productos relacionados</h3>
                    <div class="row">
                        <?php foreach ($productosRelacionados as $productoRelacionado): ?>
                            <div class="col-md-4 mb-4">
                                <div class="card h-100 shadow-sm">
                                    <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/<?php echo isset($productoRelacionado['imagen_principal']) ? $productoRelacionado['imagen_principal'] : 'imagenes/productos/default.jpg'; ?>" class="card-img-top" alt="<?php echo htmlspecialchars($productoRelacionado['nombre']); ?>">
                                    <div class="card-body">
                                        <span class="producto-categoria"><?php echo htmlspecialchars($productoRelacionado['categoria_nombre']); ?></span>
                                        <h5 class="card-title"><?php echo htmlspecialchars($productoRelacionado['nombre']); ?></h5>
                                        <p class="card-text"><?php echo htmlspecialchars($productoRelacionado['descripcion_corta']); ?></p>
                                        <p class="producto-precio"><?php echo number_format($productoRelacionado['precio'], 2, ',', '.'); ?> €</p>
                                    </div>
                                    <div class="card-footer bg-white border-top-0">
                                        <div class="d-flex justify-content-between">
                                            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/producto/detalle?id=<?php echo $productoRelacionado['id']; ?>" class="btn btn-sm btn-outline-primary">
                                                <i class="fas fa-eye"></i> Ver detalles
                                            </a>
                                            <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/carrito/agregar" method="post">
                                                <input type="hidden" name="idProducto" value="<?php echo $productoRelacionado['id']; ?>">
                                                <input type="hidden" name="cantidad" value="1">
                                                <button type="submit" class="btn btn-sm btn-primary">
                                                    <i class="fas fa-cart-plus"></i> Añadir
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <?php endforeach; ?>
                    </div>
                </div>
            <?php endif; ?>
        <?php else: ?>
            <div class="alert alert-danger">
                <i class="fas fa-exclamation-circle me-2"></i> El producto no existe o ha sido eliminado.
            </div>
            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/productos" class="btn btn-primary">
                <i class="fas fa-arrow-left me-2"></i> Volver a productos
            </a>
        <?php endif; ?>
    </div>

    <!-- Footer -->
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Manejo de miniaturas
            const miniaturas = document.querySelectorAll('.producto-miniatura');
            const imagenPrincipal = document.querySelector('.producto-imagen-principal img');
            
            if (miniaturas.length > 0 && imagenPrincipal) {
                miniaturas.forEach(miniatura => {
                    miniatura.addEventListener('click', function() {
                        // Quitar clase active de todas las miniaturas
                        miniaturas.forEach(m => m.classList.remove('active'));
                        
                        // Añadir clase active a la miniatura clickeada
                        this.classList.add('active');
                        
                        // Cambiar la imagen principal
                        const nuevaImagen = this.querySelector('img').getAttribute('src');
                        imagenPrincipal.setAttribute('src', nuevaImagen);
                    });
                });
            }
            
            // Manejo de cantidad
            const decrementarBtn = document.getElementById('decrementarCantidad');
            const incrementarBtn = document.getElementById('incrementarCantidad');
            const cantidadInput = document.getElementById('cantidad');
            
            if (decrementarBtn && incrementarBtn && cantidadInput) {
                decrementarBtn.addEventListener('click', function() {
                    const valorActual = parseInt(cantidadInput.value);
                    if (valorActual > 1) {
                        cantidadInput.value = valorActual - 1;
                    }
                });
                
                incrementarBtn.addEventListener('click', function() {
                    const valorActual = parseInt(cantidadInput.value);
                    const max = parseInt(cantidadInput.getAttribute('max'));
                    if (valorActual < max) {
                        cantidadInput.value = valorActual + 1;
                    }
                });
            }
        });
    </script>
</body>
</html> 
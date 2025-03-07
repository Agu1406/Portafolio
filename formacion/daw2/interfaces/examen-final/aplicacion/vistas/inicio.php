<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
</head>
<body>
    <!-- Hero section -->
    <section class="hero">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <h1 class="animar-entrada">Bienvenido a NaturalShop</h1>
                    <p class="lead animar-entrada">Tu tienda online de productos naturales, parafarmacia y cosméticos ecológicos.</p>
                    <div class="mt-4 animar-entrada">
                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/productos" class="btn btn-light btn-lg me-2">
                            <i class="fas fa-shopping-bag"></i> Ver productos
                        </a>
                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/categorias" class="btn btn-outline-light btn-lg">
                            <i class="fas fa-th-large"></i> Categorías
                        </a>
                    </div>
                </div>
                <div class="col-md-6 d-none d-md-block">
                    <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/imagenes/hero-image.jpg" alt="NaturalShop" class="img-fluid rounded shadow">
                </div>
            </div>
        </div>
    </section>

    <!-- Categorías destacadas -->
    <section class="py-5">
        <div class="container">
            <h2 class="text-center mb-4">Nuestras categorías</h2>
            <div class="row">
                <?php if (isset($categorias) && !empty($categorias)): ?>
                    <?php foreach (array_slice($categorias, 0, 3) as $categoria): ?>
                        <div class="col-md-4 mb-4">
                            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/productos?categoria=<?php echo $categoria['id']; ?>" class="text-decoration-none">
                                <div class="categoria-card shadow-sm">
                                    <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/<?php echo isset($categoria['imagen']) ? $categoria['imagen'] : 'imagenes/categorias/default.jpg'; ?>" alt="<?php echo htmlspecialchars($categoria['nombre']); ?>" class="categoria-img">
                                    <div class="categoria-overlay">
                                        <h3><?php echo htmlspecialchars($categoria['nombre']); ?></h3>
                                        <p class="mb-0"><?php echo htmlspecialchars($categoria['descripcion']); ?></p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    <?php endforeach; ?>
                <?php else: ?>
                    <div class="col-12 text-center">
                        <p>No hay categorías disponibles en este momento.</p>
                    </div>
                <?php endif; ?>
            </div>
            <div class="text-center mt-3">
                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/categorias" class="btn btn-outline-primary">
                    Ver todas las categorías <i class="fas fa-arrow-right"></i>
                </a>
            </div>
        </div>
    </section>

    <!-- Productos destacados -->
    <section class="py-5 bg-light">
        <div class="container">
            <h2 class="text-center mb-4">Productos destacados</h2>
            <div class="row">
                <?php if (isset($productosDestacados) && !empty($productosDestacados)): ?>
                    <?php foreach ($productosDestacados as $producto): ?>
                        <div class="col-md-3 mb-4">
                            <div class="card h-100">
                                <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/<?php echo isset($producto['imagen_principal']) ? $producto['imagen_principal'] : 'imagenes/productos/default.jpg'; ?>" class="card-img-top" alt="<?php echo htmlspecialchars($producto['nombre']); ?>">
                                <div class="card-body">
                                    <span class="producto-categoria"><?php echo htmlspecialchars($producto['categoria_nombre']); ?></span>
                                    <h5 class="card-title"><?php echo htmlspecialchars($producto['nombre']); ?></h5>
                                    <p class="card-text"><?php echo htmlspecialchars($producto['descripcion_corta']); ?></p>
                                    <p class="producto-precio"><?php echo number_format($producto['precio'], 2, ',', '.'); ?> €</p>
                                </div>
                                <div class="card-footer bg-white border-top-0">
                                    <div class="d-flex justify-content-between">
                                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/producto/detalle?id=<?php echo $producto['id']; ?>" class="btn btn-sm btn-outline-primary">
                                            <i class="fas fa-eye"></i> Ver detalles
                                        </a>
                                        <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/carrito/agregar" method="post">
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
                    <?php endforeach; ?>
                <?php else: ?>
                    <div class="col-12 text-center">
                        <p>No hay productos destacados disponibles en este momento.</p>
                    </div>
                <?php endif; ?>
            </div>
            <div class="text-center mt-3">
                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/productos" class="btn btn-outline-primary">
                    Ver todos los productos <i class="fas fa-arrow-right"></i>
                </a>
            </div>
        </div>
    </section>

    <!-- Características -->
    <section class="py-5">
        <div class="container">
            <h2 class="text-center mb-5">¿Por qué elegirnos?</h2>
            <div class="row text-center">
                <div class="col-md-3 mb-4">
                    <div class="p-3">
                        <i class="fas fa-leaf fa-3x text-primary mb-3"></i>
                        <h4>Productos naturales</h4>
                        <p>Todos nuestros productos son 100% naturales y respetuosos con el medio ambiente.</p>
                    </div>
                </div>
                <div class="col-md-3 mb-4">
                    <div class="p-3">
                        <i class="fas fa-truck fa-3x text-primary mb-3"></i>
                        <h4>Envío rápido</h4>
                        <p>Entrega en 24/48 horas para todos los pedidos realizados antes de las 18:00.</p>
                    </div>
                </div>
                <div class="col-md-3 mb-4">
                    <div class="p-3">
                        <i class="fas fa-undo fa-3x text-primary mb-3"></i>
                        <h4>Devolución gratuita</h4>
                        <p>Si no estás satisfecho, tienes 14 días para devolver tu compra sin coste.</p>
                    </div>
                </div>
                <div class="col-md-3 mb-4">
                    <div class="p-3">
                        <i class="fas fa-headset fa-3x text-primary mb-3"></i>
                        <h4>Atención al cliente</h4>
                        <p>Estamos disponibles para ayudarte de lunes a viernes de 9:00 a 18:00.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Testimonios -->
    <section class="py-5 bg-light">
        <div class="container">
            <h2 class="text-center mb-5">Lo que dicen nuestros clientes</h2>
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="mb-3">
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                            </div>
                            <p class="card-text">"Excelentes productos naturales. He notado una gran mejora en mi piel desde que uso la crema hidratante. Además, el envío fue muy rápido."</p>
                        </div>
                        <div class="card-footer bg-white">
                            <div class="d-flex align-items-center">
                                <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/imagenes/testimonios/usuario1.jpg" alt="Usuario" class="rounded-circle me-3" width="50" height="50">
                                <div>
                                    <h5 class="mb-0">María García</h5>
                                    <small class="text-muted">Cliente desde 2022</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="mb-3">
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                            </div>
                            <p class="card-text">"Los suplementos de vitamina C son de excelente calidad. Llevo tomándolos 3 meses y he notado una gran mejora en mi sistema inmunológico."</p>
                        </div>
                        <div class="card-footer bg-white">
                            <div class="d-flex align-items-center">
                                <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/imagenes/testimonios/usuario2.jpg" alt="Usuario" class="rounded-circle me-3" width="50" height="50">
                                <div>
                                    <h5 class="mb-0">Carlos Rodríguez</h5>
                                    <small class="text-muted">Cliente desde 2021</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="mb-3">
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star text-warning"></i>
                                <i class="fas fa-star-half-alt text-warning"></i>
                            </div>
                            <p class="card-text">"Atención al cliente excepcional. Tuve un problema con mi pedido y lo solucionaron de inmediato. Los productos son de gran calidad."</p>
                        </div>
                        <div class="card-footer bg-white">
                            <div class="d-flex align-items-center">
                                <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/imagenes/testimonios/usuario3.jpg" alt="Usuario" class="rounded-circle me-3" width="50" height="50">
                                <div>
                                    <h5 class="mb-0">Laura Martínez</h5>
                                    <small class="text-muted">Cliente desde 2023</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
</body>
</html> 
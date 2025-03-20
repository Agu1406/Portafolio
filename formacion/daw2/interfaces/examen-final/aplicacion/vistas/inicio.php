<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
    <style>
        .categoria-card {
            position: relative;
            overflow: hidden;
            border-radius: 8px;
            height: 250px; /* Altura fija para todas las tarjetas */
        }
        
        .categoria-img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        
        .categoria-overlay {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(0, 0, 0, 0.7);
            color: white;
            padding: 20px;
            min-height: 100px; /* Altura mínima fija para el overlay */
        }
        
        .categoria-overlay h3 {
            margin: 0 0 10px 0;
            font-size: 1.5rem;
        }
        
        .categoria-overlay p {
            margin: 0;
            font-size: 1rem;
            line-height: 1.4;
        }
    </style>
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
                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos" class="btn btn-light btn-lg me-2">
                            <i class="fas fa-shopping-bag"></i> Ver productos
                        </a>
                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/categorias" class="btn btn-outline-light btn-lg">
                            <i class="fas fa-th-large"></i> Categorías
                        </a>
                    </div>
                </div>
                <div class="col-md-6 d-none d-md-block text-center">
                    <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/imagenes/natushopt_logo.webp" alt="NaturalShop Logo" class="img-fluid" style="max-height: 200px;">
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
                            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos?categoria=<?php echo $categoria['id']; ?>" class="text-decoration-none">
                                <div class="categoria-card shadow-sm">
                                    <?php 
                                    $nombreCategoria = strtolower(str_replace(' ', '', $categoria['nombre']));
                                    if ($nombreCategoria == 'suplementosalimenticios') {
                                        $nombreCategoria = 'alimenticios';
                                    } elseif ($nombreCategoria == 'cosméticosnaturales') {
                                        $nombreCategoria = 'cosmeticos';
                                    }
                                    $rutaImagen = 'categoria_' . $nombreCategoria . '.jpeg';
                                    $rutaCompleta = $GLOBALS['configuracion']['rutaBase'] . '/publico/imagenes/' . $rutaImagen;
                                    ?>
                                    <img src="<?php echo $rutaCompleta; ?>" class="categoria-img" alt="<?php echo htmlspecialchars($categoria['nombre']); ?>">
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
                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/categorias" class="btn btn-outline-primary">
                    Ver todas las categorías <i class="fas fa-arrow-right"></i>
                </a>
            </div>
        </div>
    </section>

    <!-- Productos destacados -->
    <section class="productos-destacados py-5">
        <div class="container">
            <h2 class="text-center mb-4">Productos Destacados</h2>
            <div class="row">
                <?php if (isset($productosDestacados) && !empty($productosDestacados)): ?>
                    <?php foreach ($productosDestacados as $producto): ?>
                    <div class="col-md-3 mb-4">
                        <div class="card h-100">
                            <div class="card-img-container">
                                <?php 
                                $rutaImagen = isset($producto['imagen_principal']) ? $producto['imagen_principal'] : 'imagenes/productos/default.jpg';
                                $rutaCompleta = $GLOBALS['configuracion']['rutaPublica'] . '/' . $rutaImagen;
                                
                                // Verificar si es un archivo de imagen o un archivo de texto
                                $extension = strtolower(pathinfo($rutaImagen, PATHINFO_EXTENSION));
                                $esImagen = in_array($extension, ['jpg', 'jpeg', 'png', 'gif']);
                                
                                if ($esImagen):
                                ?>
                                <img src="<?php echo $rutaCompleta; ?>" class="card-img-top" alt="<?php echo htmlspecialchars($producto['nombre']); ?>">
                                <?php else: ?>
                                <div class="text-center p-4 bg-light">
                                    <i class="fas fa-image fa-3x text-muted mb-2"></i>
                                    <p class="mb-0">Imagen no disponible</p>
                                </div>
                                <?php endif; ?>
                            </div>
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title"><?php echo htmlspecialchars($producto['nombre']); ?></h5>
                                <p class="card-text"><?php echo htmlspecialchars($producto['descripcion_corta']); ?></p>
                                <div class="mt-auto">
                                    <p class="precio"><?php echo number_format($producto['precio'], 2); ?> <?php echo $GLOBALS['configuracion']['moneda']; ?></p>
                                    <a href="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/producto/<?php echo $producto['id']; ?>" class="btn btn-primary">Ver detalles</a>
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
        </div>
    </section>

    <!-- Categorías -->
    <section class="categorias py-5 bg-light">
        <div class="container">
            <h2 class="text-center mb-4">Nuestras Categorías</h2>
            <div class="row">
                <?php if (isset($categorias) && !empty($categorias)): ?>
                    <?php foreach ($categorias as $categoria): ?>
                    <div class="col-md-4 mb-4">
                        <div class="card categoria-card">
                            <?php 
                            $imagenCategoria = '';
                            switch($categoria['nombre']) {
                                case 'Parafarmacia':
                                    $imagenCategoria = 'parafarmacia.jpg';
                                    break;
                                case 'Cosméticos Naturales':
                                    $imagenCategoria = 'cosmeticos.jpeg';
                                    break;
                                case 'Suplementos Alimenticios':
                                    $imagenCategoria = 'suplementos.webp';
                                    break;
                                case 'Aromaterapia':
                                    $imagenCategoria = 'aromaterapia.jpg';
                                    break;
                                case 'Herbolario':
                                    $imagenCategoria = 'herbolario.webp';
                                    break;
                                default:
                                    $imagenCategoria = 'imagenes/categorias/default.jpg';
                            }
                            ?>
                            <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/imagenes/<?php echo $imagenCategoria; ?>" class="card-img-top" alt="<?php echo htmlspecialchars($categoria['nombre']); ?>">
                            <div class="card-body">
                                <h5 class="card-title"><?php echo htmlspecialchars($categoria['nombre']); ?></h5>
                                <p class="card-text"><?php echo htmlspecialchars($categoria['descripcion']); ?></p>
                                <a href="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/categoria/<?php echo $categoria['id']; ?>" class="btn btn-outline-primary">Ver productos</a>
                            </div>
                        </div>
                    </div>
                    <?php endforeach; ?>
                <?php else: ?>
                    <div class="col-12 text-center">
                        <p>No hay categorías disponibles en este momento.</p>
                    </div>
                <?php endif; ?>
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
                                <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/imagenes/usuario_maria_garcia.jpeg" alt="Usuario" class="rounded-circle me-3" width="50" height="50">
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
                                <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/imagenes/usuario_carlos_rodriguez.jpeg" alt="Usuario" class="rounded-circle me-3" width="50" height="50">
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
                                <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/imagenes/usuario_laura_martinez.jpeg" alt="Usuario" class="rounded-circle me-3" width="50" height="50">
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
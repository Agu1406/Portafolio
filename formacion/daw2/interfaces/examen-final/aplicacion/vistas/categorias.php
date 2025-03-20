<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
    <style>
        .categoria-card {
            position: relative;
            overflow: hidden;
            border-radius: 8px;
            height: 200px;
            transition: transform 0.3s;
        }
        
        .categoria-card:hover {
            transform: translateY(-5px);
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
            padding: 15px;
        }
        
        .categoria-overlay h3 {
            margin: 0 0 5px 0;
            font-size: 1.2rem;
        }
        
        .categoria-overlay p {
            font-size: 0.9rem;
            margin-bottom: 0;
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <!-- Cabecera de la página -->
    <div class="bg-light py-4">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h1 class="mb-0">Categorías</h1>
                    <p class="lead mb-0">Explora nuestras categorías de productos naturales</p>
                </div>
                <div class="col-md-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 justify-content-md-end">
                            <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/">Inicio</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Categorías</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>

    <!-- Contenido principal -->
    <div class="container py-5">
        <div class="row">
            <?php if (isset($categorias) && !empty($categorias)): ?>
                <?php foreach ($categorias as $categoria): ?>
                    <div class="col-md-4 mb-4">
                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos?categoria=<?php echo $categoria['id']; ?>" class="text-decoration-none">
                            <div class="categoria-card shadow-sm">
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
                                <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/imagenes/<?php echo $imagenCategoria; ?>" alt="<?php echo htmlspecialchars($categoria['nombre']); ?>" class="categoria-img">
                                <div class="categoria-overlay">
                                    <h3><?php echo htmlspecialchars($categoria['nombre']); ?></h3>
                                    <p><?php echo htmlspecialchars($categoria['descripcion']); ?></p>
                                </div>
                            </div>
                        </a>
                    </div>
                <?php endforeach; ?>
            <?php else: ?>
                <div class="col-12 text-center">
                    <div class="alert alert-info">
                        <i class="fas fa-info-circle me-2"></i> No hay categorías disponibles en este momento.
                    </div>
                </div>
            <?php endif; ?>
        </div>
    </div>

    <!-- Footer -->
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
</body>
</html> 
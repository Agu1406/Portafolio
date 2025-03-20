<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
    <style>
        .pedido-card {
            transition: transform 0.2s;
        }
        
        .pedido-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        
        .estado-pedido {
            font-size: 0.875rem;
            padding: 0.25rem 0.75rem;
            border-radius: 1rem;
        }
        
        .estado-pendiente {
            background-color: #fff3cd;
            color: #856404;
        }
        
        .estado-pagado {
            background-color: #d4edda;
            color: #155724;
        }
        
        .estado-enviado {
            background-color: #cce5ff;
            color: #004085;
        }
        
        .estado-entregado {
            background-color: #d1e7dd;
            color: #0f5132;
        }
        
        .estado-cancelado {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
</head>
<body>
    <!-- Cabecera de la página -->
    <div class="bg-light py-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h1 class="h3 mb-0">Mis Pedidos</h1>
                    <p class="lead mb-0 fs-6">Historial de tus compras</p>
                </div>
                <div class="col-md-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 justify-content-md-end">
                            <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/">Inicio</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Mis Pedidos</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>

    <!-- Contenido principal -->
    <div class="container py-4">
        <?php if (isset($_SESSION['mensaje'])): ?>
            <div class="alert alert-<?php echo $_SESSION['mensaje']['tipo'] === 'error' ? 'danger' : $_SESSION['mensaje']['tipo']; ?> alert-dismissible fade show py-2" role="alert">
                <?php echo $_SESSION['mensaje']['texto']; ?>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <?php unset($_SESSION['mensaje']); ?>
        <?php endif; ?>

        <?php if (empty($pedidos)): ?>
            <div class="text-center py-5">
                <i class="fas fa-box-open fa-4x text-muted mb-3"></i>
                <h2 class="h4 mb-3">No tienes pedidos realizados todavía</h2>
                <p class="text-muted mb-4">¡Explora nuestro catálogo y realiza tu primera compra!</p>
                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos" class="btn btn-primary">
                    <i class="fas fa-shopping-bag me-2"></i> Ver productos
                </a>
            </div>
        <?php else: ?>
            <div class="row g-4">
                <?php foreach ($pedidos as $pedido): ?>
                    <div class="col-md-6">
                        <div class="card pedido-card">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h5 class="card-title mb-0">Pedido #<?php echo htmlspecialchars($pedido['codigo']); ?></h5>
                                    <span class="estado-pedido estado-<?php echo strtolower($pedido['estado']); ?>">
                                        <?php echo ucfirst($pedido['estado']); ?>
                                    </span>
                                </div>
                                
                                <div class="mb-3">
                                    <small class="text-muted">
                                        <i class="far fa-calendar-alt me-1"></i>
                                        <?php echo date('d/m/Y H:i', strtotime($pedido['fecha_pedido'])); ?>
                                    </small>
                                </div>
                                
                                <div class="mb-3">
                                    <div class="row">
                                        <div class="col-6">
                                            <small class="text-muted d-block">Productos:</small>
                                            <strong><?php echo count($pedido['detalles']); ?> artículos</strong>
                                        </div>
                                        <div class="col-6 text-end">
                                            <small class="text-muted d-block">Total:</small>
                                            <strong><?php echo number_format($pedido['total'], 2, ',', '.'); ?> €</strong>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="d-flex justify-content-between align-items-center">
                                    <small class="text-muted">
                                        <i class="fas fa-truck me-1"></i>
                                        Envío a: <?php echo htmlspecialchars($pedido['ciudad_envio']); ?>
                                    </small>
                                    <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/pedidos/<?php echo $pedido['id']; ?>" class="btn btn-outline-primary btn-sm">
                                        Ver detalles
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                <?php endforeach; ?>
            </div>
        <?php endif; ?>
    </div>
    
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
</body>
</html> 
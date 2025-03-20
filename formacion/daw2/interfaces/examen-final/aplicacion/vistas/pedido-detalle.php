<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
    <style>
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
        
        .timeline {
            position: relative;
            padding-left: 3rem;
        }
        
        .timeline::before {
            content: '';
            position: absolute;
            left: 0;
            top: 0;
            bottom: 0;
            width: 2px;
            background: #e9ecef;
        }
        
        .timeline-item {
            position: relative;
            padding-bottom: 1.5rem;
        }
        
        .timeline-item::before {
            content: '';
            position: absolute;
            left: -3rem;
            top: 0.25rem;
            width: 1rem;
            height: 1rem;
            border-radius: 50%;
            background: #fff;
            border: 2px solid #6c757d;
        }
        
        .timeline-item.active::before {
            background: #198754;
            border-color: #198754;
        }
    </style>
</head>
<body>
    <!-- Cabecera de la página -->
    <div class="bg-light py-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h1 class="h3 mb-0">Detalles del Pedido</h1>
                    <p class="lead mb-0 fs-6">Pedido #<?php echo htmlspecialchars($pedido['codigo']); ?></p>
                </div>
                <div class="col-md-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 justify-content-md-end">
                            <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/">Inicio</a></li>
                            <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/pedidos">Mis Pedidos</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Detalles</li>
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

        <div class="row g-4">
            <!-- Detalles del pedido -->
            <div class="col-lg-8">
                <!-- Estado del pedido -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title mb-4">Estado del Pedido</h5>
                        <div class="timeline">
                            <div class="timeline-item <?php echo in_array($pedido['estado'], ['pendiente', 'pagado', 'enviado', 'entregado']) ? 'active' : ''; ?>">
                                <h6 class="mb-1">Pedido Recibido</h6>
                                <small class="text-muted"><?php echo date('d/m/Y H:i', strtotime($pedido['fecha_pedido'])); ?></small>
                            </div>
                            <div class="timeline-item <?php echo in_array($pedido['estado'], ['pagado', 'enviado', 'entregado']) ? 'active' : ''; ?>">
                                <h6 class="mb-1">Pago Confirmado</h6>
                                <small class="text-muted"><?php echo $pedido['fecha_pago'] ? date('d/m/Y H:i', strtotime($pedido['fecha_pago'])) : '-'; ?></small>
                            </div>
                            <div class="timeline-item <?php echo in_array($pedido['estado'], ['enviado', 'entregado']) ? 'active' : ''; ?>">
                                <h6 class="mb-1">Pedido Enviado</h6>
                                <small class="text-muted"><?php echo $pedido['fecha_envio'] ? date('d/m/Y H:i', strtotime($pedido['fecha_envio'])) : '-'; ?></small>
                            </div>
                            <div class="timeline-item <?php echo $pedido['estado'] === 'entregado' ? 'active' : ''; ?>">
                                <h6 class="mb-1">Pedido Entregado</h6>
                                <small class="text-muted"><?php echo $pedido['fecha_entrega'] ? date('d/m/Y H:i', strtotime($pedido['fecha_entrega'])) : '-'; ?></small>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Productos -->
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title mb-4">Productos</h5>
                        <div class="table-responsive">
                            <table class="table table-borderless">
                                <thead class="table-light">
                                    <tr>
                                        <th>Producto</th>
                                        <th class="text-center">Cantidad</th>
                                        <th class="text-end">Precio</th>
                                        <th class="text-end">Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php foreach ($detalles as $detalle): ?>
                                        <tr>
                                            <td>
                                                <div class="d-flex align-items-center">
                                                    <?php if ($detalle['imagen_principal']): ?>
                                                        <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/<?php echo htmlspecialchars($detalle['imagen_principal']); ?>" alt="<?php echo htmlspecialchars($detalle['nombre_producto']); ?>" class="img-thumbnail me-3" style="width: 50px;">
                                                    <?php endif; ?>
                                                    <div>
                                                        <h6 class="mb-0"><?php echo htmlspecialchars($detalle['nombre_producto']); ?></h6>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-center"><?php echo $detalle['cantidad']; ?></td>
                                            <td class="text-end"><?php echo number_format($detalle['precio_unitario'], 2, ',', '.'); ?> €</td>
                                            <td class="text-end"><?php echo number_format($detalle['subtotal'], 2, ',', '.'); ?> €</td>
                                        </tr>
                                    <?php endforeach; ?>
                                </tbody>
                                <tfoot class="table-light">
                                    <tr>
                                        <td colspan="3" class="text-end"><strong>Subtotal</strong></td>
                                        <td class="text-end"><?php echo number_format($pedido['subtotal'], 2, ',', '.'); ?> €</td>
                                    </tr>
                                    <?php if ($pedido['impuestos'] > 0): ?>
                                        <tr>
                                            <td colspan="3" class="text-end">Impuestos</td>
                                            <td class="text-end"><?php echo number_format($pedido['impuestos'], 2, ',', '.'); ?> €</td>
                                        </tr>
                                    <?php endif; ?>
                                    <?php if ($pedido['gastos_envio'] > 0): ?>
                                        <tr>
                                            <td colspan="3" class="text-end">Gastos de envío</td>
                                            <td class="text-end"><?php echo number_format($pedido['gastos_envio'], 2, ',', '.'); ?> €</td>
                                        </tr>
                                    <?php endif; ?>
                                    <?php if ($pedido['descuento'] > 0): ?>
                                        <tr>
                                            <td colspan="3" class="text-end">Descuento</td>
                                            <td class="text-end">-<?php echo number_format($pedido['descuento'], 2, ',', '.'); ?> €</td>
                                        </tr>
                                    <?php endif; ?>
                                    <tr>
                                        <td colspan="3" class="text-end"><strong>Total</strong></td>
                                        <td class="text-end"><strong><?php echo number_format($pedido['total'], 2, ',', '.'); ?> €</strong></td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Información del pedido -->
            <div class="col-lg-4">
                <!-- Estado actual -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title mb-3">Estado actual</h5>
                        <span class="estado-pedido estado-<?php echo strtolower($pedido['estado']); ?>">
                            <?php echo ucfirst($pedido['estado']); ?>
                        </span>
                        <hr>
                        <div class="mb-2">
                            <small class="text-muted d-block">Método de pago</small>
                            <strong><?php echo ucfirst($pedido['metodo_pago']); ?></strong>
                        </div>
                        <div>
                            <small class="text-muted d-block">Fecha del pedido</small>
                            <strong><?php echo date('d/m/Y H:i', strtotime($pedido['fecha_pedido'])); ?></strong>
                        </div>
                    </div>
                </div>

                <!-- Dirección de envío -->
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title mb-3">Dirección de envío</h5>
                        <p class="mb-1"><strong><?php echo htmlspecialchars($pedido['nombre_envio']); ?></strong></p>
                        <p class="mb-1"><?php echo htmlspecialchars($pedido['direccion_envio']); ?></p>
                        <p class="mb-1">
                            <?php echo htmlspecialchars($pedido['codigo_postal_envio']); ?> 
                            <?php echo htmlspecialchars($pedido['ciudad_envio']); ?>
                        </p>
                        <p class="mb-1"><?php echo htmlspecialchars($pedido['provincia_envio']); ?></p>
                        <?php if ($pedido['telefono_envio']): ?>
                            <p class="mb-0">Tel: <?php echo htmlspecialchars($pedido['telefono_envio']); ?></p>
                        <?php endif; ?>
                        <?php if ($pedido['notas']): ?>
                            <hr>
                            <small class="text-muted d-block">Notas</small>
                            <p class="mb-0"><?php echo nl2br(htmlspecialchars($pedido['notas'])); ?></p>
                        <?php endif; ?>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
</body>
</html> 
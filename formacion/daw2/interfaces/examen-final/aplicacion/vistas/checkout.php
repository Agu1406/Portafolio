<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
    <style>
        /* Estilos para hacer el formulario más compacto */
        .form-control, .form-select {
            padding: 0.25rem 0.5rem;
            font-size: 0.875rem;
            min-height: 32px;
            height: calc(1.5em + 0.5rem + 2px);
        }
        
        .form-label {
            margin-bottom: 0.25rem;
            font-size: 0.875rem;
            font-weight: 500;
        }
        
        .card {
            margin-bottom: 1rem;
        }
        
        .card-body {
            padding: 1rem;
        }
        
        .card-header {
            padding: 0.75rem 1rem;
        }
        
        /* Reducir espacios entre elementos */
        .mb-2 {
            margin-bottom: 0.5rem !important;
        }
        
        .mb-3 {
            margin-bottom: 0.75rem !important;
        }
        
        .mb-4 {
            margin-bottom: 1rem !important;
        }
        
        /* Hacer que el formulario de pago sea más compacto */
        #datosTarjeta .row {
            margin-bottom: 0;
        }
        
        #datosTarjeta .form-control {
            margin-bottom: 0.5rem;
        }
        
        .form-check {
            margin-bottom: 0.25rem;
            padding-left: 1.75rem;
        }
        
        .form-check-input {
            margin-top: 0.25rem;
        }
        
        /* Reducir el tamaño del textarea */
        textarea.form-control {
            min-height: 40px;
            max-height: 60px;
        }
        
        /* Asegurar que el footer esté al final */
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        
        .container.py-5 {
            flex: 1;
            padding-top: 1rem !important;
            padding-bottom: 1rem !important;
        }
        
        footer {
            margin-top: auto;
        }
        
        /* Ajustes para el resumen del pedido */
        .resumen-producto {
            font-size: 0.875rem;
            margin-bottom: 0.25rem;
        }
    </style>
</head>
<body>
    <!-- Cabecera de la página -->
    <div class="bg-light py-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h1 class="h3 mb-0">Finalizar compra</h1>
                    <p class="lead mb-0 fs-6">Completa tus datos para realizar el pedido</p>
                </div>
                <div class="col-md-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0 justify-content-md-end">
                            <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/">Inicio</a></li>
                            <li class="breadcrumb-item"><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/carrito">Carrito</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Finalizar compra</li>
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
        
        <?php if (empty($productosDetalle)): ?>
            <div class="alert alert-warning py-2">
                <i class="fas fa-exclamation-circle me-2"></i> Tu carrito está vacío. Añade productos antes de finalizar la compra.
            </div>
            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos" class="btn btn-primary">
                <i class="fas fa-shopping-bag me-2"></i> Ir a comprar
            </a>
        <?php else: ?>
            <div class="row g-4">
                <div class="col-lg-8">
                    <form id="formEnvio" method="post" action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/checkout/procesar">
                        <!-- Datos de envío -->
                        <div class="card">
                            <div class="card-header bg-white">
                                <h5 class="mb-0 fs-6">Datos de envío</h5>
                            </div>
                            <div class="card-body">
                                <div class="row g-2">
                                    <div class="col-md-6">
                                        <label for="nombre" class="form-label">Nombre completo *</label>
                                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="email" class="form-label">Email *</label>
                                        <input type="email" class="form-control" id="email" name="email" required>
                                    </div>
                                </div>
                                
                                <div class="row g-2 mt-2">
                                    <div class="col-md-6">
                                        <label for="telefono" class="form-label">Teléfono *</label>
                                        <input type="tel" class="form-control" id="telefono" name="telefono" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="direccion" class="form-label">Dirección *</label>
                                        <input type="text" class="form-control" id="direccion" name="direccion" required>
                                    </div>
                                </div>
                                
                                <div class="row g-2 mt-2">
                                    <div class="col-md-4">
                                        <label for="codigoPostal" class="form-label">C.P. *</label>
                                        <input type="text" class="form-control" id="codigoPostal" name="codigoPostal" required>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="ciudad" class="form-label">Ciudad *</label>
                                        <input type="text" class="form-control" id="ciudad" name="ciudad" required>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="provincia" class="form-label">Provincia *</label>
                                        <input type="text" class="form-control" id="provincia" name="provincia" required>
                                    </div>
                                </div>
                                
                                <div class="mt-2">
                                    <label for="observaciones" class="form-label">Observaciones</label>
                                    <textarea class="form-control" id="observaciones" name="observaciones" rows="2"></textarea>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Métodos de pago -->
                        <div class="card mt-3">
                            <div class="card-header bg-white">
                                <h5 class="mb-0 fs-6">Método de pago</h5>
                            </div>
                            <div class="card-body">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="metodoPago" id="tarjeta" value="tarjeta" checked required>
                                    <label class="form-check-label" for="tarjeta">
                                        <i class="fas fa-credit-card me-1"></i> Tarjeta de crédito/débito
                                    </label>
                                </div>
                                
                                <div id="datosTarjeta" class="ps-4 py-2">
                                    <div class="row g-2">
                                        <div class="col-md-6">
                                            <input type="text" class="form-control" id="numeroTarjeta" name="numeroTarjeta" placeholder="Número de tarjeta" required>
                                        </div>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="fechaExpiracion" name="fechaExpiracion" placeholder="MM/AA" required>
                                        </div>
                                        <div class="col-md-3">
                                            <input type="text" class="form-control" id="cvv" name="cvv" placeholder="CVV" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="metodoPago" id="paypal" value="paypal" required>
                                    <label class="form-check-label" for="paypal">
                                        <i class="fab fa-paypal me-1"></i> PayPal
                                    </label>
                                </div>
                                
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="metodoPago" id="transferencia" value="transferencia" required>
                                    <label class="form-check-label" for="transferencia">
                                        <i class="fas fa-university me-1"></i> Transferencia bancaria
                                    </label>
                                </div>
                                
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="metodoPago" id="contrareembolso" value="contrareembolso" required>
                                    <label class="form-check-label" for="contrareembolso">
                                        <i class="fas fa-money-bill-wave me-1"></i> Pago contra reembolso
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                
                <div class="col-lg-4">
                    <!-- Resumen del pedido -->
                    <div class="card">
                        <div class="card-header bg-white">
                            <h5 class="mb-0 fs-6">Resumen del pedido</h5>
                        </div>
                        <div class="card-body">
                            <div class="mb-3">
                                <?php foreach ($productosDetalle as $producto): ?>
                                    <div class="d-flex justify-content-between resumen-producto">
                                        <span><?php echo htmlspecialchars($producto['nombre']); ?> x <?php echo $producto['cantidad']; ?></span>
                                        <span><?php echo number_format($producto['subtotal'], 2, ',', '.'); ?> €</span>
                                    </div>
                                <?php endforeach; ?>
                            </div>
                            
                            <hr class="my-2">
                            
                            <div class="d-flex justify-content-between mb-1">
                                <span>Subtotal</span>
                                <span><?php echo number_format($total, 2, ',', '.'); ?> €</span>
                            </div>
                            <div class="d-flex justify-content-between mb-1">
                                <span>Envío</span>
                                <span>Gratis</span>
                            </div>
                            <div class="d-flex justify-content-between mt-2">
                                <span class="fw-bold">Total</span>
                                <span class="fw-bold fs-5"><?php echo number_format($total, 2, ',', '.'); ?> €</span>
                            </div>
                            
                            <button type="submit" form="formEnvio" class="btn btn-primary w-100 mt-3">
                                <i class="fas fa-lock me-2"></i> Finalizar compra
                            </button>
                            
                            <div class="text-center mt-3">
                                <small class="text-muted">
                                    <i class="fas fa-shield-alt me-1"></i> Pago seguro
                                </small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <?php endif; ?>
    </div>
    
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
</body>
</html> 
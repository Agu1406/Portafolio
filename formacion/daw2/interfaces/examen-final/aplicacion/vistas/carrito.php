<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
    <style>
        /* Estilos personalizados para el input de cantidad */
        .carrito-cantidad {
            display: flex;
            flex-direction: row;
            align-items: center;
            width: fit-content;
        }
        
        .carrito-cantidad .form-control {
            width: 50px;
            padding: 0.25rem;
            font-size: 1rem;
            border-radius: 0;
            text-align: center;
            font-weight: bold;
            background-color: #fff;
            color: #000;
        }
        
        .carrito-cantidad .btn {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
            padding: 0.25rem 0.5rem;
        }
        
        /* Ocultar flechas de incremento/decremento en navegadores webkit */
        input[type="number"]::-webkit-inner-spin-button,
        input[type="number"]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        
        /* Ocultar flechas de incremento/decremento en Firefox */
        input[type="number"] {
            -moz-appearance: textfield;
        }
        
        /* Estilos para hacer más compacto el lateral derecho */
        .col-lg-4 .card {
            margin-bottom: 0.5rem;
        }
        
        .col-lg-4 .card-header {
            padding: 0.4rem 0.6rem;
            font-size: 0.9rem;
        }
        
        .col-lg-4 .card-body {
            padding: 0.6rem;
        }
        
        .col-lg-4 .mb-3, .col-lg-4 .mb-2 {
            margin-bottom: 0.3rem !important;
        }
        
        .col-lg-4 .mb-4 {
            margin-bottom: 0.5rem !important;
        }
        
        .col-lg-4 h6, .col-lg-4 h5 {
            font-size: 0.85rem;
            margin-bottom: 0.3rem;
        }
        
        .col-lg-4 .mt-3 {
            margin-top: 0.5rem !important;
        }
        
        .col-lg-4 .mt-2 {
            margin-top: 0.3rem !important;
        }
        
        .col-lg-4 .form-control, .col-lg-4 .btn {
            font-size: 0.8rem;
            padding: 0.2rem 0.5rem;
        }
        
        .col-lg-4 .fa-lg {
            font-size: 1.2rem;
        }
        
        /* Asegurar espacio para el footer */
        .container.py-3 {
            margin-bottom: 2rem;
        }
        
        /* Asegurar que el contenido se ajuste correctamente */
        .carrito-sidebar {
            display: flex;
            flex-direction: column;
            height: auto;
        }
        
        /* Ajustar el layout para evitar superposiciones */
        .row {
            display: flex;
            flex-wrap: wrap;
        }
        
        /* Evitar que el footer se superponga con el contenido */
        footer, .footer-container {
            clear: both;
            position: relative;
            width: 100%;
            margin-top: 2rem;
            height: auto;
        }
        
        /* Eliminar espacios innecesarios */
        .container.py-5 {
            padding-bottom: 1rem !important;
        }
        
        /* Ajustar el espacio entre el contenido y el footer */
        .main-content {
            margin-bottom: 2rem;
        }
        
        /* Estilos para el botón de checkout en escritorio */
        .desktop-checkout-button {
            display: block !important;
            margin-top: 15px;
            margin-bottom: 10px;
        }
        
        .desktop-checkout-button .btn {
            padding: 0.5rem 1rem;
            font-size: 1rem;
            width: 100%;
        }
        
        /* Botón flotante para móviles */
        .mobile-checkout-button {
            display: none;
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            background-color: #fff;
            padding: 10px;
            box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
            z-index: 1000;
        }
        
        /* Mostrar el botón flotante solo en dispositivos móviles */
        @media (max-width: 991.98px) {
            .mobile-checkout-button {
                display: block;
            }
            
            .desktop-checkout-button {
                display: none !important;
            }
            
            /* Añadir padding al final del contenido para que no quede oculto detrás del botón flotante */
            .main-content {
                padding-bottom: 70px !important;
            }
        }
    </style>
</head>
<body>
    <!-- Contenido principal -->
    <div class="container py-5 main-content">
        <h1 class="mb-4">Carrito de compra</h1>
        
        <?php if (isset($_SESSION['mensaje'])): ?>
            <div class="alert alert-<?php echo $_SESSION['mensaje']['tipo'] === 'error' ? 'danger' : $_SESSION['mensaje']['tipo']; ?> alert-dismissible fade show" role="alert">
                <?php echo $_SESSION['mensaje']['texto']; ?>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <?php unset($_SESSION['mensaje']); ?>
        <?php endif; ?>
        
        <!-- Depuración del carrito -->
        <?php if (isset($_GET['debug']) && $_GET['debug'] === '1'): ?>
            <div class="alert alert-info">
                <h5>Depuración del carrito:</h5>
                <pre><?php print_r($_SESSION['carrito']); ?></pre>
                <h5>Productos detalle:</h5>
                <pre><?php print_r($productosDetalle); ?></pre>
            </div>
        <?php endif; ?>
        
        <?php if (empty($productosDetalle)): ?>
            <div class="text-center py-5">
                <i class="fas fa-shopping-cart fa-5x text-muted mb-3"></i>
                <h3>Tu carrito está vacío</h3>
                <p class="mb-4">Parece que aún no has añadido productos a tu carrito.</p>
                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos" class="btn btn-primary">
                    <i class="fas fa-shopping-bag me-2"></i> Ir a comprar
                </a>
            </div>
        <?php else: ?>
            <div class="row">
                <div class="col-lg-8">
                    <!-- Productos en el carrito -->
                    <div class="card mb-4">
                        <div class="card-header bg-white">
                            <div class="d-flex justify-content-between align-items-center">
                                <h5 class="mb-0">Productos (<?php echo count($productosDetalle); ?>)</h5>
                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/carrito/vaciar" class="btn btn-sm btn-outline-danger" onclick="return confirm('¿Estás seguro de que deseas vaciar el carrito?')">
                                    <i class="fas fa-trash-alt me-1"></i> Vaciar carrito
                                </a>
                            </div>
                        </div>
                        <div class="card-body p-0">
                            <?php foreach ($productosDetalle as $producto): ?>
                                <div class="carrito-item p-3 border-bottom">
                                    <div class="row align-items-center">
                                        <div class="col-md-2 mb-2 mb-md-0">
                                            <div class="carrito-item-img">
                                                <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/<?php echo $producto['imagen']; ?>" alt="<?php echo htmlspecialchars($producto['nombre']); ?>" class="img-fluid rounded">
                                            </div>
                                        </div>
                                        <div class="col-md-4 mb-2 mb-md-0">
                                            <h5 class="carrito-item-titulo">
                                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/producto/detalle?id=<?php echo $producto['id']; ?>">
                                                    <?php echo htmlspecialchars($producto['nombre']); ?>
                                                </a>
                                            </h5>
                                            <p class="text-muted small mb-0">Código: PROD-<?php echo $producto['id']; ?></p>
                                        </div>
                                        <div class="col-md-2 mb-2 mb-md-0">
                                            <span class="carrito-item-precio"><?php echo number_format($producto['precio'], 2, ',', '.'); ?> €</span>
                                        </div>
                                        <div class="col-md-2 mb-2 mb-md-0">
                                            <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/carrito/actualizar" method="post" class="form-actualizar">
                                                <input type="hidden" name="idProducto" value="<?php echo $producto['id']; ?>">
                                                <div class="input-group carrito-cantidad">
                                                    <button type="button" class="btn btn-outline-secondary btn-sm decrementar">
                                                        <i class="fas fa-minus"></i>
                                                    </button>
                                                    <input type="text" name="cantidad" class="form-control form-control-sm text-center fw-bold" value="<?php echo $producto['cantidad']; ?>" min="1" max="99" style="width: 50px; padding: 0.25rem; font-size: 1rem; background-color: #fff; color: #000;">
                                                    <button type="button" class="btn btn-outline-secondary btn-sm incrementar">
                                                        <i class="fas fa-plus"></i>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="col-md-1 text-end mb-2 mb-md-0">
                                            <span class="fw-bold"><?php echo number_format($producto['subtotal'], 2, ',', '.'); ?> €</span>
                                        </div>
                                        <div class="col-md-1 text-end">
                                            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/carrito/eliminar?idProducto=<?php echo $producto['id']; ?>" class="text-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar este producto?')">
                                                <i class="fas fa-trash-alt"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            <?php endforeach; ?>
                        </div>
                    </div>
                    
                    <!-- Botones de acción -->
                    <div class="d-flex justify-content-between mb-3">
                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos" class="btn btn-outline-primary">
                            <i class="fas fa-arrow-left me-2"></i> Seguir comprando
                        </a>
                        <button type="button" id="actualizarCarrito" class="btn btn-outline-secondary">
                            <i class="fas fa-sync-alt me-2"></i> Actualizar carrito
                        </button>
                    </div>
                </div>
                
                <div class="col-lg-4 mt-3 mt-lg-0 carrito-sidebar">
                    <!-- Resumen del pedido -->
                    <div class="card mb-3">
                        <div class="card-header bg-primary text-white">
                            <h5 class="mb-0">Resumen del pedido</h5>
                        </div>
                        <div class="card-body">
                            <div class="d-flex justify-content-between mb-2">
                                <span>Subtotal</span>
                                <span><?php echo number_format($total, 2, ',', '.'); ?> €</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Envío</span>
                                <span>Gratis</span>
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-4">
                                <span class="fw-bold">Total</span>
                                <span class="fw-bold fs-5"><?php echo number_format($total, 2, ',', '.'); ?> €</span>
                            </div>
                            
                            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/checkout" class="btn btn-success btn-lg w-100" style="font-size: 1.2rem; padding: 15px;">
                                <i class="fas fa-credit-card me-2"></i> PROCEDER AL PAGO
                            </a>
                        </div>
                    </div>
                    
                    <!-- Código promocional -->
                    <div class="card mb-3">
                        <div class="card-body">
                            <h6 class="mb-3">¿Tienes un código promocional?</h6>
                            <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/carrito/aplicar-cupon" method="post">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="cupon" placeholder="Introduce tu código">
                                    <button class="btn btn-outline-primary" type="submit">Aplicar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    
                    <!-- Métodos de pago -->
                    <div class="card">
                        <div class="card-body">
                            <h6 class="mb-3">Métodos de pago aceptados</h6>
                            <div class="d-flex justify-content-between">
                                <i class="fab fa-cc-visa fa-2x text-primary"></i>
                                <i class="fab fa-cc-mastercard fa-2x text-primary"></i>
                                <i class="fab fa-cc-paypal fa-2x text-primary"></i>
                                <i class="fab fa-cc-amex fa-2x text-primary"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <?php endif; ?>
        
        <!-- Botón grande para proceder al pago en escritorio -->
        <?php if (!empty($productosDetalle)): ?>
        <div class="row mt-4 mb-4 d-none d-lg-block">
            <div class="col-lg-4 offset-lg-8">
                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/checkout" class="btn btn-success btn-lg w-100" style="font-size: 1.2rem; padding: 15px;">
                    <i class="fas fa-credit-card me-2"></i> PROCEDER AL PAGO
                </a>
            </div>
        </div>
        <?php endif; ?>
    </div>
    
    <!-- Botón flotante para proceder al pago en móviles -->
    <?php if (!empty($productosDetalle)): ?>
    <div class="mobile-checkout-button">
        <div class="container">
            <div class="d-flex justify-content-between align-items-center mb-2">
                <span class="fw-bold">Total:</span>
                <span class="fw-bold fs-5"><?php echo number_format($total, 2, ',', '.'); ?> €</span>
            </div>
            <div class="d-grid">
                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/checkout" class="btn btn-primary">
                    <i class="fas fa-credit-card me-2"></i> Proceder al pago
                </a>
            </div>
        </div>
    </div>
    <?php endif; ?>

    <!-- Footer -->
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Manejar incremento y decremento de cantidad
            const decrementarBtns = document.querySelectorAll('.decrementar');
            const incrementarBtns = document.querySelectorAll('.incrementar');
            const cantidadInputs = document.querySelectorAll('input[name="cantidad"]');
            const forms = document.querySelectorAll('.form-actualizar');
            
            // Función para actualizar la cantidad y enviar el formulario
            function actualizarCantidad(index, nuevaCantidad) {
                const input = cantidadInputs[index];
                const min = parseInt(input.getAttribute('min')) || 1;
                const max = parseInt(input.getAttribute('max')) || 99;
                
                // Validar el valor
                if (nuevaCantidad < min) {
                    nuevaCantidad = min;
                } else if (nuevaCantidad > max) {
                    nuevaCantidad = max;
                }
                
                // Solo actualizar si la cantidad ha cambiado
                if (parseInt(input.value) !== nuevaCantidad) {
                    // Actualizar el valor del input
                    input.value = nuevaCantidad;
                    
                    // Mostrar un indicador de carga
                    const form = forms[index];
                    const loadingIndicator = document.createElement('div');
                    loadingIndicator.className = 'position-absolute w-100 h-100 d-flex justify-content-center align-items-center';
                    loadingIndicator.style.top = '0';
                    loadingIndicator.style.left = '0';
                    loadingIndicator.style.backgroundColor = 'rgba(255, 255, 255, 0.7)';
                    loadingIndicator.style.zIndex = '10';
                    loadingIndicator.innerHTML = '<div class="spinner-border spinner-border-sm text-primary" role="status"></div>';
                    
                    form.style.position = 'relative';
                    form.appendChild(loadingIndicator);
                    
                    // Enviar el formulario
                    form.submit();
                }
            }
            
            decrementarBtns.forEach((btn, index) => {
                btn.addEventListener('click', function() {
                    const input = cantidadInputs[index];
                    let valor = parseInt(input.value) || 1;
                    if (valor > 1) {
                        actualizarCantidad(index, valor - 1);
                    }
                });
            });
            
            incrementarBtns.forEach((btn, index) => {
                btn.addEventListener('click', function() {
                    const input = cantidadInputs[index];
                    let valor = parseInt(input.value) || 1;
                    actualizarCantidad(index, valor + 1);
                });
            });
            
            // Manejar cambio manual de cantidad
            cantidadInputs.forEach((input, index) => {
                // Solo permitir números
                input.addEventListener('input', function() {
                    this.value = this.value.replace(/[^0-9]/g, '');
                    if (this.value === '') {
                        this.value = '1';
                    }
                });
                
                // Cuando el input pierde el foco
                input.addEventListener('blur', function() {
                    let valor = parseInt(this.value) || 1;
                    const min = parseInt(this.getAttribute('min')) || 1;
                    const max = parseInt(this.getAttribute('max')) || 99;
                    
                    if (valor < min) valor = min;
                    if (valor > max) valor = max;
                    
                    this.value = valor;
                    
                    // Solo enviar si el valor ha cambiado
                    const originalValue = parseInt(this.defaultValue);
                    if (valor !== originalValue) {
                        actualizarCantidad(index, valor);
                    }
                });
                
                // Prevenir el envío del formulario al presionar Enter
                input.addEventListener('keydown', function(e) {
                    if (e.key === 'Enter') {
                        e.preventDefault();
                        this.blur();
                    }
                });
            });
            
            // Manejar actualización del carrito
            const actualizarBtn = document.getElementById('actualizarCarrito');
            if (actualizarBtn) {
                actualizarBtn.addEventListener('click', function() {
                    // Enviar todos los formularios
                    forms.forEach(form => form.submit());
                });
            }
        });
    </script>
</body>
</html> 
<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
</head>
<body>
    <!-- Contenido principal -->
    <div class="container py-5">
        <h1 class="mb-4">Carrito de compra</h1>
        
        <?php if (isset($_SESSION['mensaje'])): ?>
            <div class="alert alert-<?php echo $_SESSION['mensaje']['tipo'] === 'error' ? 'danger' : $_SESSION['mensaje']['tipo']; ?> alert-dismissible fade show" role="alert">
                <?php echo $_SESSION['mensaje']['texto']; ?>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <?php unset($_SESSION['mensaje']); ?>
        <?php endif; ?>
        
        <?php if (empty($productosDetalle)): ?>
            <div class="text-center py-5">
                <i class="fas fa-shopping-cart fa-5x text-muted mb-3"></i>
                <h3>Tu carrito está vacío</h3>
                <p class="mb-4">Parece que aún no has añadido productos a tu carrito.</p>
                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/productos" class="btn btn-primary">
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
                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/carrito/vaciar" class="btn btn-sm btn-outline-danger" onclick="return confirm('¿Estás seguro de que deseas vaciar el carrito?')">
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
                                                <img src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/<?php echo $producto['imagen']; ?>" alt="<?php echo htmlspecialchars($producto['nombre']); ?>" class="img-fluid rounded">
                                            </div>
                                        </div>
                                        <div class="col-md-4 mb-2 mb-md-0">
                                            <h5 class="carrito-item-titulo">
                                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/producto/detalle?id=<?php echo $producto['id']; ?>">
                                                    <?php echo htmlspecialchars($producto['nombre']); ?>
                                                </a>
                                            </h5>
                                            <p class="text-muted small mb-0">Código: PROD-<?php echo $producto['id']; ?></p>
                                        </div>
                                        <div class="col-md-2 mb-2 mb-md-0">
                                            <span class="carrito-item-precio"><?php echo number_format($producto['precio'], 2, ',', '.'); ?> €</span>
                                        </div>
                                        <div class="col-md-2 mb-2 mb-md-0">
                                            <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/carrito/actualizar" method="post">
                                                <input type="hidden" name="idProducto" value="<?php echo $producto['id']; ?>">
                                                <div class="input-group carrito-cantidad">
                                                    <button type="button" class="btn btn-outline-secondary btn-sm decrementar">
                                                        <i class="fas fa-minus"></i>
                                                    </button>
                                                    <input type="number" name="cantidad" class="form-control form-control-sm text-center" value="<?php echo $producto['cantidad']; ?>" min="1" max="99" readonly>
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
                                            <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/carrito/eliminar?id=<?php echo $producto['id']; ?>" class="text-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar este producto?')">
                                                <i class="fas fa-trash-alt"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            <?php endforeach; ?>
                        </div>
                    </div>
                    
                    <!-- Botones de acción -->
                    <div class="d-flex justify-content-between">
                        <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/productos" class="btn btn-outline-primary">
                            <i class="fas fa-arrow-left me-2"></i> Seguir comprando
                        </a>
                        <button type="button" id="actualizarCarrito" class="btn btn-outline-secondary">
                            <i class="fas fa-sync-alt me-2"></i> Actualizar carrito
                        </button>
                    </div>
                </div>
                
                <div class="col-lg-4 mt-4 mt-lg-0">
                    <!-- Resumen del pedido -->
                    <div class="card">
                        <div class="card-header bg-white">
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
                            
                            <div class="d-grid gap-2">
                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/checkout" class="btn btn-primary">
                                    <i class="fas fa-credit-card me-2"></i> Proceder al pago
                                </a>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Código promocional -->
                    <div class="card mt-3">
                        <div class="card-body">
                            <h6 class="mb-3">¿Tienes un código promocional?</h6>
                            <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/carrito/aplicar-cupon" method="post">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="cupon" placeholder="Introduce tu código">
                                    <button class="btn btn-outline-primary" type="submit">Aplicar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    
                    <!-- Métodos de pago -->
                    <div class="card mt-3">
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
    </div>

    <!-- Footer -->
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Manejar incremento y decremento de cantidad
            const decrementarBtns = document.querySelectorAll('.decrementar');
            const incrementarBtns = document.querySelectorAll('.incrementar');
            const cantidadInputs = document.querySelectorAll('input[name="cantidad"]');
            
            decrementarBtns.forEach((btn, index) => {
                btn.addEventListener('click', function() {
                    const input = cantidadInputs[index];
                    let valor = parseInt(input.value);
                    if (valor > 1) {
                        input.value = valor - 1;
                    }
                });
            });
            
            incrementarBtns.forEach((btn, index) => {
                btn.addEventListener('click', function() {
                    const input = cantidadInputs[index];
                    let valor = parseInt(input.value);
                    if (valor < parseInt(input.getAttribute('max'))) {
                        input.value = valor + 1;
                    }
                });
            });
            
            // Manejar actualización del carrito
            const actualizarBtn = document.getElementById('actualizarCarrito');
            if (actualizarBtn) {
                actualizarBtn.addEventListener('click', function() {
                    const forms = document.querySelectorAll('.carrito-item form');
                    
                    // Crear un array de promesas para enviar todos los formularios
                    const promesas = Array.from(forms).map(form => {
                        const formData = new FormData(form);
                        return fetch(form.action, {
                            method: 'POST',
                            body: formData
                        });
                    });
                    
                    // Cuando todas las promesas se resuelvan, recargar la página
                    Promise.all(promesas)
                        .then(() => {
                            window.location.reload();
                        })
                        .catch(error => {
                            console.error('Error al actualizar el carrito:', error);
                        });
                });
            }
        });
    </script>
</body>
</html> 
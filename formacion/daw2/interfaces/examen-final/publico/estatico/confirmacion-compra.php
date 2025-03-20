<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmación de Compra - NaturalShop</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    
    <!-- Estilos personalizados -->
    <link rel="stylesheet" href="../css/estilos.css">
    
    <style>
        .confirmation-icon {
            font-size: 5rem;
            color: #28a745;
            margin-bottom: 1rem;
        }
        
        .confirmation-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 2rem;
            text-align: center;
        }
        
        .order-details {
            background-color: #f8f9fa;
            border-radius: 10px;
            padding: 1.5rem;
            margin-top: 2rem;
            text-align: left;
        }
        
        .thank-you-message {
            font-size: 1.8rem;
            margin-bottom: 1rem;
            color: #343a40;
        }
        
        .confirmation-number {
            font-weight: bold;
            color: #007bff;
        }
    </style>
</head>
<body>
    <!-- Cabecera de la página -->
    <header class="bg-primary text-white py-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-4">
                    <h1 class="mb-0">NaturalShop</h1>
                </div>
                <div class="col-md-8">
                    <nav class="text-md-end">
                        <a href="../" class="btn btn-outline-light">
                            <i class="fas fa-home me-2"></i> Volver a la tienda
                        </a>
                    </nav>
                </div>
            </div>
        </div>
    </header>

    <!-- Contenido principal -->
    <div class="container py-5">
        <div class="confirmation-container">
            <div class="confirmation-icon">
                <i class="fas fa-check-circle"></i>
            </div>
            
            <h2 class="thank-you-message">¡Gracias por tu compra!</h2>
            <p class="lead mb-4">Tu pedido ha sido procesado correctamente.</p>
            
            <div class="order-details">
                <h4 class="mb-3">Detalles del pedido</h4>
                <p>Número de confirmación: <span class="confirmation-number">#<?php echo rand(100000, 999999); ?></span></p>
                <p>Fecha: <?php echo date('d/m/Y H:i'); ?></p>
                
                <hr>
                
                <p>Hemos enviado un correo electrónico con los detalles de tu compra a la dirección proporcionada.</p>
                <p>Si tienes alguna pregunta sobre tu pedido, no dudes en contactarnos.</p>
                
                <div class="mt-4">
                    <h5>¿Qué sucede ahora?</h5>
                    <ul class="text-start">
                        <li>Recibirás un correo electrónico de confirmación con los detalles de tu pedido.</li>
                        <li>Nuestro equipo preparará tu pedido para el envío.</li>
                        <li>Te notificaremos cuando tu pedido haya sido enviado.</li>
                        <li>¡Disfruta de tus productos naturales!</li>
                    </ul>
                </div>
            </div>
            
            <div class="mt-4">
                <a href="../" class="btn btn-primary">
                    <i class="fas fa-shopping-bag me-2"></i> Seguir comprando
                </a>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white py-4">
        <div class="container">
            <div class="row">
                <div class="col-md-4 mb-3 mb-md-0">
                    <h5>NaturalShop</h5>
                    <p class="text-muted">Tu tienda de productos naturales</p>
                </div>
                <div class="col-md-4 mb-3 mb-md-0">
                    <h5>Enlaces rápidos</h5>
                    <ul class="list-unstyled">
                        <li><a href="../" class="text-decoration-none text-white">Inicio</a></li>
                        <li><a href="../productos" class="text-decoration-none text-white">Productos</a></li>
                        <li><a href="../carrito" class="text-decoration-none text-white">Carrito</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h5>Contacto</h5>
                    <ul class="list-unstyled text-muted">
                        <li><i class="fas fa-envelope me-2"></i> info@naturalshop.com</li>
                        <li><i class="fas fa-phone me-2"></i> +34 900 123 456</li>
                        <li><i class="fas fa-map-marker-alt me-2"></i> Calle Principal 123, Madrid</li>
                    </ul>
                </div>
            </div>
            <hr>
            <div class="text-center">
                <p class="mb-0">&copy; <?php echo date('Y'); ?> NaturalShop. Todos los derechos reservados.</p>
            </div>
        </div>
    </footer>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 
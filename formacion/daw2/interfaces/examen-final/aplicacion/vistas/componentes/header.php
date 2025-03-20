<!-- Header component -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><?php echo isset($tituloPagina) ? $tituloPagina : 'NaturalShop'; ?></title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<!-- CSS personalizado -->
<link rel="stylesheet" href="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/css/estilos.css">

<!-- Barra de navegación -->
<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top shadow-sm">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/">
            <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/imagenes/natushopt_logo.webp" alt="NaturalShop Logo" height="40" class="me-2">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/">Inicio</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Categorías
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <?php if (isset($categorias) && !empty($categorias)): ?>
                            <?php foreach ($categorias as $categoria): ?>
                                <li>
                                    <a class="dropdown-item d-flex align-items-center" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos?categoria=<?php echo $categoria['id']; ?>">
                                        <?php if ($categoria['nombre'] === 'Parafarmacia'): ?>
                                            <img src="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/imagenes/categoria_parafarmacia.webp" alt="Parafarmacia" class="me-2" style="height: 24px; width: auto;">
                                        <?php endif; ?>
                                        <?php echo htmlspecialchars($categoria['nombre']); ?>
                                    </a>
                                </li>
                            <?php endforeach; ?>
                            <li><hr class="dropdown-divider"></li>
                        <?php endif; ?>
                        <li>
                            <a class="dropdown-item" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/categorias">
                                Ver todas las categorías
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos">Productos</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link position-relative" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/carrito">
                        <i class="fas fa-shopping-cart"></i> Carrito
                        <?php if (isset($_SESSION['carrito']) && !empty($_SESSION['carrito'])): ?>
                            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                <?php echo count($_SESSION['carrito']); ?>
                            </span>
                        <?php endif; ?>
                    </a>
                </li>
                <?php if (isset($usuarioLogueado) && $usuarioLogueado): ?>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="fas fa-user"></i> <?php echo htmlspecialchars($_SESSION['usuario']['nombre']); ?>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                            <?php if (isset($_SESSION['usuario']['rol']) && $_SESSION['usuario']['rol'] === 'admin'): ?>
                                <li>
                                    <a class="dropdown-item" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin">
                                        <i class="fas fa-cog"></i> Panel de administración
                                    </a>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                            <?php endif; ?>
                            <li>
                                <a class="dropdown-item" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/perfil">
                                    <i class="fas fa-user-circle"></i> Mi perfil
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/pedidos">
                                    <i class="fas fa-box"></i> Mis pedidos
                                </a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/logout">
                                    <i class="fas fa-sign-out-alt"></i> Cerrar sesión
                                </a>
                            </li>
                        </ul>
                    </li>
                <?php else: ?>
                    <li class="nav-item">
                        <a class="nav-link" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/login">
                            <i class="fas fa-sign-in-alt"></i> Iniciar sesión
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/registro">
                            <i class="fas fa-user-plus"></i> Registrarse
                        </a>
                    </li>
                <?php endif; ?>
            </ul>
        </div>
    </div>
</nav> 
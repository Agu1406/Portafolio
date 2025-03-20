<!DOCTYPE html>
<html lang="es" class="h-100">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo isset($data['title']) ? $data['title'] . ' - ' . APP_NAME : APP_NAME; ?></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <!-- Estilos personalizados -->
    <link rel="stylesheet" href="<?php echo asset('css/style.css'); ?>">
    <!-- Meta descripción para SEO -->
    <meta name="description" content="<?php echo isset($data['description']) ? $data['description'] : 'La red social gastronómica más accesible para compartir y descubrir recetas'; ?>">
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        main {
            flex: 1 0 auto;
        }
        footer {
            flex-shrink: 0;
        }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">
    <!-- Barra de accesibilidad -->
    <div class="accessibility-bar bg-light py-2">
        <div class="container d-flex justify-content-end">
            <?php echo accessibilityControls(); ?>
        </div>
    </div>

    <!-- Cabecera -->
    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand" href="<?php echo url(''); ?>">
                    <?php echo APP_NAME; ?>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMain" aria-controls="navbarMain" aria-expanded="false" aria-label="Alternar navegación">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarMain">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link <?php echo isCurrentUrl('') ? 'active' : ''; ?>" aria-current="page" href="<?php echo url(''); ?>">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link <?php echo isCurrentUrl('recipes') ? 'active' : ''; ?>" href="<?php echo url('recipes'); ?>">Recetas</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Categorías
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <?php 
                                // Obtener categorías para el menú
                                $categoryModel = new Category();
                                $categories = $categoryModel->getAllCategories();
                                foreach($categories as $category) : 
                                ?>
                                <li><a class="dropdown-item" href="<?php echo url('recipes/category/' . $category->id); ?>"><?php echo $category->name; ?></a></li>
                                <?php endforeach; ?>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link <?php echo isCurrentUrl('home/about') ? 'active' : ''; ?>" href="<?php echo url('home/about'); ?>">Acerca de</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link <?php echo isCurrentUrl('home/contact') ? 'active' : ''; ?>" href="<?php echo url('home/contact'); ?>">Contacto</a>
                        </li>
                    </ul>
                    <form class="d-flex me-2" action="<?php echo url('recipes/search'); ?>" method="GET">
                        <input class="form-control me-2" type="search" name="search" placeholder="Buscar recetas..." aria-label="Buscar">
                        <button class="btn btn-outline-success" type="submit">Buscar</button>
                    </form>
                    <ul class="navbar-nav">
                        <?php if(isLoggedIn()) : ?>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownUser" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <img src="<?php echo asset('img/users/' . ($_SESSION['user_image'] ?? 'default.jpg')); ?>" alt="Perfil" class="rounded-circle me-1" width="24" height="24">
                                    <?php echo $_SESSION['user_name']; ?>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownUser">
                                    <li><a class="dropdown-item" href="<?php echo url('users/profile'); ?>"><i class="fas fa-user me-2"></i> Mi Perfil</a></li>
                                    <li><a class="dropdown-item" href="<?php echo url('recipes/add'); ?>"><i class="fas fa-plus-circle me-2"></i> Añadir Receta</a></li>
                                    <li><a class="dropdown-item" href="<?php echo url('users/settings'); ?>"><i class="fas fa-cog me-2"></i> Configuración</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="<?php echo url('users/logout'); ?>"><i class="fas fa-sign-out-alt me-2"></i> Cerrar Sesión</a></li>
                                </ul>
                            </li>
                        <?php else : ?>
                            <li class="nav-item">
                                <a class="nav-link <?php echo $view == 'users/login' ? 'active' : ''; ?>" href="<?php echo url('users/login'); ?>">
                                    <i class="fas fa-sign-in-alt me-1"></i> Iniciar Sesión
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link <?php echo $view == 'users/register' ? 'active' : ''; ?>" href="<?php echo url('users/register'); ?>">
                                    <i class="fas fa-user-plus me-1"></i> Registrarse
                                </a>
                            </li>
                        <?php endif; ?>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <!-- Mensaje de error de base de datos -->
    <?php if(isset($_SESSION['db_error'])): ?>
    <div class="container mt-4">
        <div class="alert alert-danger">
            <h4 class="alert-heading">Error de conexión a la base de datos</h4>
            <p><?php echo $_SESSION['db_error']; ?></p>
            <hr>
            <p class="mb-0">Verifique que el servidor MySQL esté en ejecución y que la configuración de la base de datos sea correcta.</p>
            <p>
                <strong>Host:</strong> <?php echo $_SESSION['db_config']['host']; ?><br>
                <strong>Base de datos:</strong> <?php echo $_SESSION['db_config']['dbname']; ?><br>
                <strong>Usuario:</strong> <?php echo $_SESSION['db_config']['user']; ?>
            </p>
            <p>
                <a href="<?php echo url('test_db.php'); ?>" class="btn btn-sm btn-primary">Ejecutar diagnóstico</a>
            </p>
        </div>
    </div>
    <?php endif; ?>

    <!-- Contenido principal -->
    <main class="container py-4">
        <?php echo getFlash('success'); ?>
        <?php echo getFlash('error'); ?>
        <?php 
        // Mostrar mensajes flash
        echo getFlash('register_success');
        echo getFlash('login_success');
        echo getFlash('profile_success');
        echo getFlash('recipe_success');
        echo getFlash('comment_success');
        echo getFlash('comment_error');
        echo getFlash('contact_success');
        
        // Mostrar el contenido de la vista
        if (isset($viewContent)) {
            echo $viewContent;
        }
        ?>
    </main>

    <!-- Pie de página -->
    <footer class="footer mt-auto py-4 bg-dark text-white">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h5>Manos a la Olla</h5>
                    <p>Tu comunidad de recetas favorita donde puedes compartir y descubrir platos deliciosos de todo el mundo.</p>
                    <div class="social-links mt-3">
                        <a href="#" class="text-white me-2"><i class="fab fa-facebook-f fa-lg"></i></a>
                        <a href="#" class="text-white me-2"><i class="fab fa-twitter fa-lg"></i></a>
                        <a href="#" class="text-white me-2"><i class="fab fa-instagram fa-lg"></i></a>
                        <a href="#" class="text-white me-2"><i class="fab fa-pinterest fa-lg"></i></a>
                        <a href="#" class="text-white"><i class="fab fa-youtube fa-lg"></i></a>
                    </div>
                </div>
                <div class="col-md-2">
                    <h5>Enlaces</h5>
                    <ul class="list-unstyled">
                        <li><a href="<?php echo url(''); ?>" class="text-white"><i class="fas fa-angle-right me-1"></i> Inicio</a></li>
                        <li><a href="<?php echo url('recipes'); ?>" class="text-white"><i class="fas fa-angle-right me-1"></i> Recetas</a></li>
                        <li><a href="<?php echo url('categories'); ?>" class="text-white"><i class="fas fa-angle-right me-1"></i> Categorías</a></li>
                        <li><a href="<?php echo url('about'); ?>" class="text-white"><i class="fas fa-angle-right me-1"></i> Acerca de</a></li>
                    </ul>
                </div>
                <div class="col-md-3">
                    <h5>Categorías populares</h5>
                    <ul class="list-unstyled">
                        <?php 
                        $categoryModel = new Category();
                        $popularCategories = $categoryModel->getPopularCategories(4);
                        foreach($popularCategories as $category) : 
                        ?>
                        <li><a href="<?php echo url('recipes/category/' . $category->id); ?>" class="text-white"><i class="fas fa-angle-right me-1"></i> <?php echo $category->name; ?></a></li>
                        <?php endforeach; ?>
                    </ul>
                </div>
                <div class="col-md-3">
                    <h5>Contacto</h5>
                    <ul class="list-unstyled">
                        <li><i class="fas fa-envelope me-2"></i><a href="mailto:info@manosalaolla.com" class="text-white">info@manosalaolla.com</a></li>
                        <li><i class="fas fa-phone me-2"></i><a href="tel:+123456789" class="text-white">+12 345 6789</a></li>
                        <li class="mt-3"><a href="<?php echo url('privacy'); ?>" class="text-white"><i class="fas fa-shield-alt me-1"></i> Política de Privacidad</a></li>
                        <li><a href="<?php echo url('terms'); ?>" class="text-white"><i class="fas fa-file-contract me-1"></i> Términos de Servicio</a></li>
                    </ul>
                </div>
            </div>
            <hr>
            <div class="text-center">
                <p>&copy; <?php echo date('Y'); ?> Manos a la Olla. Todos los derechos reservados.</p>
            </div>
        </div>
    </footer>

    <!-- Modal de confirmación global -->
    <?php require APPROOT . '/views/components/confirm_modal.php'; ?>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Scripts personalizados -->
    <script src="<?php echo asset('js/accessibility.js'); ?>"></script>
    <script src="<?php echo asset('js/main.js'); ?>"></script>
</body>
</html> 
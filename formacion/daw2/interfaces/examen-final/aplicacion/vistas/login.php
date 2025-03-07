<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
</head>
<body>
    <!-- Contenido principal -->
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body p-4">
                        <h2 class="text-center mb-4">Iniciar sesión</h2>
                        
                        <?php if (isset($_GET['registro']) && $_GET['registro'] === 'exitoso'): ?>
                            <div class="alert alert-success" role="alert">
                                <i class="fas fa-check-circle me-2"></i> ¡Registro completado con éxito! Ahora puedes iniciar sesión.
                            </div>
                        <?php endif; ?>
                        
                        <?php if (isset($error)): ?>
                            <div class="alert alert-danger" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>
                                <?php
                                    switch ($error) {
                                        case 'credenciales_invalidas':
                                            echo 'Email o contraseña incorrectos. Por favor, inténtalo de nuevo.';
                                            break;
                                        case 'campos_vacios':
                                            echo 'Por favor, completa todos los campos.';
                                            break;
                                        case 'datos_incompletos':
                                            echo 'Datos incompletos. Por favor, inténtalo de nuevo.';
                                            break;
                                        default:
                                            echo 'Ha ocurrido un error. Por favor, inténtalo de nuevo.';
                                    }
                                ?>
                            </div>
                        <?php endif; ?>
                        
                        <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/login/auth" method="post" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                                    <input type="email" class="form-control" id="email" name="email" placeholder="tu@email.com" required>
                                    <div class="invalid-feedback">
                                        Por favor, introduce un email válido.
                                    </div>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Tu contraseña" required>
                                    <button class="btn btn-outline-secondary" type="button" id="togglePassword">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                    <div class="invalid-feedback">
                                        Por favor, introduce tu contraseña.
                                    </div>
                                </div>
                            </div>
                            
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="remember" name="remember">
                                <label class="form-check-label" for="remember">Recordarme</label>
                            </div>
                            
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-sign-in-alt me-2"></i> Iniciar sesión
                                </button>
                            </div>
                        </form>
                        
                        <div class="mt-4 text-center">
                            <p>¿No tienes una cuenta? <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/registro">Regístrate</a></p>
                            <p><a href="#">¿Olvidaste tu contraseña?</a></p>
                        </div>
                        
                        <hr class="my-4">
                        
                        <div class="text-center">
                            <p class="text-muted mb-3">O inicia sesión con:</p>
                            <div class="d-flex justify-content-center gap-2">
                                <button class="btn btn-outline-primary">
                                    <i class="fab fa-facebook-f"></i>
                                </button>
                                <button class="btn btn-outline-danger">
                                    <i class="fab fa-google"></i>
                                </button>
                                <button class="btn btn-outline-dark">
                                    <i class="fab fa-apple"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
    
    <script>
        // Script para mostrar/ocultar contraseña
        document.addEventListener('DOMContentLoaded', function() {
            const togglePassword = document.getElementById('togglePassword');
            const password = document.getElementById('password');
            
            togglePassword.addEventListener('click', function() {
                const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
                password.setAttribute('type', type);
                
                // Cambiar el icono
                this.querySelector('i').classList.toggle('fa-eye');
                this.querySelector('i').classList.toggle('fa-eye-slash');
            });
        });
    </script>
</body>
</html> 
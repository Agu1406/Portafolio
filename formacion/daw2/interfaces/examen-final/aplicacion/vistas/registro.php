<!DOCTYPE html>
<html lang="es">
<head>
    <?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>
</head>
<body>
    <!-- Contenido principal -->
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-body p-4">
                        <h2 class="text-center mb-4">Crear una cuenta</h2>
                        
                        <?php if (isset($errores) && !empty($errores)): ?>
                            <div class="alert alert-danger" role="alert">
                                <i class="fas fa-exclamation-circle me-2"></i>
                                <strong>Por favor, corrige los siguientes errores:</strong>
                                <ul class="mb-0 mt-2">
                                    <?php foreach ($errores as $error): ?>
                                        <li><?php echo htmlspecialchars($error); ?></li>
                                    <?php endforeach; ?>
                                </ul>
                            </div>
                        <?php endif; ?>
                        
                        <form action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/registro/crear" method="post" class="needs-validation" novalidate>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="nombre" class="form-label">Nombre completo</label>
                                    <input type="text" class="form-control" id="nombre" name="nombre" value="<?php echo isset($datos['nombre']) ? htmlspecialchars($datos['nombre']) : ''; ?>" required>
                                    <div class="invalid-feedback">
                                        Por favor, introduce tu nombre completo.
                                    </div>
                                </div>
                                
                                <div class="col-md-6 mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" value="<?php echo isset($datos['email']) ? htmlspecialchars($datos['email']) : ''; ?>" required>
                                    <div class="invalid-feedback">
                                        Por favor, introduce un email válido.
                                    </div>
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="password" class="form-label">Contraseña</label>
                                    <div class="input-group">
                                        <input type="password" class="form-control" id="password" name="password" required>
                                        <button class="btn btn-outline-secondary" type="button" id="togglePassword">
                                            <i class="fas fa-eye"></i>
                                        </button>
                                    </div>
                                    <div class="form-text">
                                        La contraseña debe tener al menos 6 caracteres.
                                    </div>
                                </div>
                                
                                <div class="col-md-6 mb-3">
                                    <label for="confirmar_password" class="form-label">Confirmar contraseña</label>
                                    <div class="input-group">
                                        <input type="password" class="form-control" id="confirmar_password" name="confirmar_password" required>
                                        <button class="btn btn-outline-secondary" type="button" id="toggleConfirmPassword">
                                            <i class="fas fa-eye"></i>
                                        </button>
                                    </div>
                                    <div class="invalid-feedback">
                                        Las contraseñas deben coincidir.
                                    </div>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <label for="telefono" class="form-label">Teléfono (opcional)</label>
                                <input type="tel" class="form-control" id="telefono" name="telefono" value="<?php echo isset($datos['telefono']) ? htmlspecialchars($datos['telefono']) : ''; ?>">
                            </div>
                            
                            <div class="mb-3">
                                <label for="direccion" class="form-label">Dirección</label>
                                <input type="text" class="form-control" id="direccion" name="direccion" value="<?php echo isset($datos['direccion']) ? htmlspecialchars($datos['direccion']) : ''; ?>" required>
                                <div class="invalid-feedback">
                                    Por favor, introduce tu dirección.
                                </div>
                            </div>
                            
                            <div class="row">
                                <div class="col-md-4 mb-3">
                                    <label for="codigo_postal" class="form-label">Código postal</label>
                                    <input type="text" class="form-control" id="codigo_postal" name="codigo_postal" value="<?php echo isset($datos['codigo_postal']) ? htmlspecialchars($datos['codigo_postal']) : ''; ?>" required>
                                    <div class="invalid-feedback">
                                        Por favor, introduce tu código postal.
                                    </div>
                                </div>
                                
                                <div class="col-md-4 mb-3">
                                    <label for="ciudad" class="form-label">Ciudad</label>
                                    <input type="text" class="form-control" id="ciudad" name="ciudad" value="<?php echo isset($datos['ciudad']) ? htmlspecialchars($datos['ciudad']) : ''; ?>" required>
                                    <div class="invalid-feedback">
                                        Por favor, introduce tu ciudad.
                                    </div>
                                </div>
                                
                                <div class="col-md-4 mb-3">
                                    <label for="provincia" class="form-label">Provincia</label>
                                    <input type="text" class="form-control" id="provincia" name="provincia" value="<?php echo isset($datos['provincia']) ? htmlspecialchars($datos['provincia']) : ''; ?>" required>
                                    <div class="invalid-feedback">
                                        Por favor, introduce tu provincia.
                                    </div>
                                </div>
                            </div>
                            
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="terminos" name="terminos" required>
                                <label class="form-check-label" for="terminos">
                                    Acepto los <a href="#" data-bs-toggle="modal" data-bs-target="#terminosModal">términos y condiciones</a> y la <a href="#" data-bs-toggle="modal" data-bs-target="#privacidadModal">política de privacidad</a>
                                </label>
                                <div class="invalid-feedback">
                                    Debes aceptar los términos y condiciones para continuar.
                                </div>
                            </div>
                            
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-user-plus me-2"></i> Crear cuenta
                                </button>
                            </div>
                        </form>
                        
                        <div class="mt-4 text-center">
                            <p>¿Ya tienes una cuenta? <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/login">Iniciar sesión</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de Términos y Condiciones -->
    <div class="modal fade" id="terminosModal" tabindex="-1" aria-labelledby="terminosModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="terminosModalLabel">Términos y Condiciones</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h6>1. Aceptación de los términos</h6>
                    <p>Al registrarte en NaturalShop, aceptas los siguientes términos y condiciones de uso. Si no estás de acuerdo con estos términos, no debes registrarte ni utilizar nuestros servicios.</p>
                    
                    <h6>2. Uso del servicio</h6>
                    <p>NaturalShop ofrece una plataforma para la compra de productos naturales, parafarmacia y cosméticos ecológicos. Te comprometes a utilizar el servicio de manera responsable y de acuerdo con todas las leyes aplicables.</p>
                    
                    <h6>3. Cuenta de usuario</h6>
                    <p>Eres responsable de mantener la confidencialidad de tu contraseña y de todas las actividades que ocurran bajo tu cuenta. Debes notificarnos inmediatamente sobre cualquier uso no autorizado de tu cuenta.</p>
                    
                    <h6>4. Compras y pagos</h6>
                    <p>Al realizar una compra, aceptas pagar el precio indicado más los gastos de envío y los impuestos aplicables. Nos reservamos el derecho de cambiar los precios en cualquier momento.</p>
                    
                    <h6>5. Envíos y devoluciones</h6>
                    <p>Los plazos de entrega son orientativos y pueden variar. Puedes devolver los productos en un plazo de 14 días desde la recepción si no estás satisfecho.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de Política de Privacidad -->
    <div class="modal fade" id="privacidadModal" tabindex="-1" aria-labelledby="privacidadModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="privacidadModalLabel">Política de Privacidad</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h6>1. Recopilación de información</h6>
                    <p>Recopilamos información personal como tu nombre, dirección, correo electrónico y número de teléfono cuando te registras en nuestra plataforma o realizas una compra.</p>
                    
                    <h6>2. Uso de la información</h6>
                    <p>Utilizamos tu información para procesar pedidos, personalizar tu experiencia, mejorar nuestro servicio y comunicarnos contigo sobre ofertas y novedades.</p>
                    
                    <h6>3. Protección de datos</h6>
                    <p>Implementamos medidas de seguridad para proteger tu información personal y cumplimos con todas las leyes de protección de datos aplicables.</p>
                    
                    <h6>4. Cookies</h6>
                    <p>Utilizamos cookies para mejorar tu experiencia en nuestro sitio web. Puedes configurar tu navegador para rechazar todas las cookies, pero esto puede afectar a la funcionalidad del sitio.</p>
                    
                    <h6>5. Derechos del usuario</h6>
                    <p>Tienes derecho a acceder, rectificar, limitar y eliminar tus datos personales. Para ejercer estos derechos, ponte en contacto con nosotros a través de info@naturalshop.com.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?>
    
    <script>
        // Script para mostrar/ocultar contraseñas
        document.addEventListener('DOMContentLoaded', function() {
            // Toggle para la contraseña
            const togglePassword = document.getElementById('togglePassword');
            const password = document.getElementById('password');
            
            togglePassword.addEventListener('click', function() {
                const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
                password.setAttribute('type', type);
                
                // Cambiar el icono
                this.querySelector('i').classList.toggle('fa-eye');
                this.querySelector('i').classList.toggle('fa-eye-slash');
            });
            
            // Toggle para confirmar contraseña
            const toggleConfirmPassword = document.getElementById('toggleConfirmPassword');
            const confirmPassword = document.getElementById('confirmar_password');
            
            toggleConfirmPassword.addEventListener('click', function() {
                const type = confirmPassword.getAttribute('type') === 'password' ? 'text' : 'password';
                confirmPassword.setAttribute('type', type);
                
                // Cambiar el icono
                this.querySelector('i').classList.toggle('fa-eye');
                this.querySelector('i').classList.toggle('fa-eye-slash');
            });
            
            // Validación personalizada para confirmar contraseña
            const form = document.querySelector('form');
            
            form.addEventListener('submit', function(event) {
                if (password.value !== confirmPassword.value) {
                    confirmPassword.setCustomValidity('Las contraseñas no coinciden');
                } else {
                    confirmPassword.setCustomValidity('');
                }
            });
            
            confirmPassword.addEventListener('input', function() {
                if (password.value !== confirmPassword.value) {
                    confirmPassword.setCustomValidity('Las contraseñas no coinciden');
                } else {
                    confirmPassword.setCustomValidity('');
                }
            });
        });
    </script>
</body>
</html> 
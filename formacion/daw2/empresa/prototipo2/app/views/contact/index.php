<div class="container py-5">
    <div class="row">
        <div class="col-lg-8 mx-auto">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h1 class="h3 mb-4 text-center">Contacta con nosotros</h1>
                    <p class="text-muted text-center mb-5">¿Tienes alguna pregunta, sugerencia o comentario? Estamos aquí para ayudarte.</p>
                    
                    <div class="row mb-5">
                        <div class="col-md-4 text-center mb-4 mb-md-0">
                            <div class="d-inline-block p-3 bg-light rounded-circle mb-3">
                                <i class="fas fa-envelope fa-2x text-primary"></i>
                            </div>
                            <h3 class="h5">Correo electrónico</h3>
                            <p class="text-muted">info@manosalaolla.com</p>
                        </div>
                        <div class="col-md-4 text-center mb-4 mb-md-0">
                            <div class="d-inline-block p-3 bg-light rounded-circle mb-3">
                                <i class="fas fa-phone fa-2x text-primary"></i>
                            </div>
                            <h3 class="h5">Teléfono</h3>
                            <p class="text-muted">+34 123 456 789</p>
                        </div>
                        <div class="col-md-4 text-center">
                            <div class="d-inline-block p-3 bg-light rounded-circle mb-3">
                                <i class="fas fa-map-marker-alt fa-2x text-primary"></i>
                            </div>
                            <h3 class="h5">Dirección</h3>
                            <p class="text-muted">Calle Ejemplo 123, Madrid</p>
                        </div>
                    </div>
                    
                    <form action="<?php echo url('contact/send'); ?>" method="post" class="needs-validation" novalidate>
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label for="name" class="form-label">Nombre completo</label>
                                <input type="text" class="form-control <?php echo isset($data['name_err']) ? 'is-invalid' : ''; ?>" id="name" name="name" value="<?php echo $data['name'] ?? ''; ?>" required>
                                <div class="invalid-feedback">
                                    <?php echo $data['name_err'] ?? 'Por favor ingresa tu nombre.'; ?>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label for="email" class="form-label">Correo electrónico</label>
                                <input type="email" class="form-control <?php echo isset($data['email_err']) ? 'is-invalid' : ''; ?>" id="email" name="email" value="<?php echo $data['email'] ?? ''; ?>" required>
                                <div class="invalid-feedback">
                                    <?php echo $data['email_err'] ?? 'Por favor ingresa un correo electrónico válido.'; ?>
                                </div>
                            </div>
                            <div class="col-12">
                                <label for="subject" class="form-label">Asunto</label>
                                <input type="text" class="form-control <?php echo isset($data['subject_err']) ? 'is-invalid' : ''; ?>" id="subject" name="subject" value="<?php echo $data['subject'] ?? ''; ?>" required>
                                <div class="invalid-feedback">
                                    <?php echo $data['subject_err'] ?? 'Por favor ingresa el asunto.'; ?>
                                </div>
                            </div>
                            <div class="col-12">
                                <label for="message" class="form-label">Mensaje</label>
                                <textarea class="form-control <?php echo isset($data['message_err']) ? 'is-invalid' : ''; ?>" id="message" name="message" rows="6" required><?php echo $data['message'] ?? ''; ?></textarea>
                                <div class="invalid-feedback">
                                    <?php echo $data['message_err'] ?? 'Por favor ingresa tu mensaje.'; ?>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-check">
                                    <input class="form-check-input <?php echo isset($data['privacy_err']) ? 'is-invalid' : ''; ?>" type="checkbox" id="privacy" name="privacy" required>
                                    <label class="form-check-label" for="privacy">
                                        He leído y acepto la <a href="<?php echo url('privacy'); ?>">política de privacidad</a>
                                    </label>
                                    <div class="invalid-feedback">
                                        <?php echo $data['privacy_err'] ?? 'Debes aceptar la política de privacidad.'; ?>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 mt-4">
                                <button type="submit" class="btn btn-primary w-100">Enviar mensaje</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            
            <div class="card mt-5">
                <div class="card-body">
                    <h2 class="h4 mb-4">Preguntas frecuentes</h2>
                    
                    <div class="accordion" id="faqAccordion">
                        <div class="accordion-item">
                            <h3 class="accordion-header" id="faqHeading1">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faqCollapse1" aria-expanded="false" aria-controls="faqCollapse1">
                                    ¿Cómo puedo crear una cuenta?
                                </button>
                            </h3>
                            <div id="faqCollapse1" class="accordion-collapse collapse" aria-labelledby="faqHeading1" data-bs-parent="#faqAccordion">
                                <div class="accordion-body">
                                    Para crear una cuenta, haz clic en el botón "Registrarse" en la esquina superior derecha de la página. Completa el formulario con tu información y sigue las instrucciones para activar tu cuenta.
                                </div>
                            </div>
                        </div>
                        
                        <div class="accordion-item">
                            <h3 class="accordion-header" id="faqHeading2">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faqCollapse2" aria-expanded="false" aria-controls="faqCollapse2">
                                    ¿Cómo puedo compartir una receta?
                                </button>
                            </h3>
                            <div id="faqCollapse2" class="accordion-collapse collapse" aria-labelledby="faqHeading2" data-bs-parent="#faqAccordion">
                                <div class="accordion-body">
                                    Una vez que hayas iniciado sesión, haz clic en "Añadir Receta" en el menú desplegable de tu perfil. Completa el formulario con los detalles de tu receta, incluyendo ingredientes, instrucciones y una imagen, y luego haz clic en "Publicar receta".
                                </div>
                            </div>
                        </div>
                        
                        <div class="accordion-item">
                            <h3 class="accordion-header" id="faqHeading3">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faqCollapse3" aria-expanded="false" aria-controls="faqCollapse3">
                                    ¿Puedo editar o eliminar mis recetas?
                                </button>
                            </h3>
                            <div id="faqCollapse3" class="accordion-collapse collapse" aria-labelledby="faqHeading3" data-bs-parent="#faqAccordion">
                                <div class="accordion-body">
                                    Sí, puedes editar o eliminar tus propias recetas en cualquier momento. Ve a la página de la receta y encontrarás botones para editar o eliminar si eres el autor de la receta.
                                </div>
                            </div>
                        </div>
                        
                        <div class="accordion-item">
                            <h3 class="accordion-header" id="faqHeading4">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faqCollapse4" aria-expanded="false" aria-controls="faqCollapse4">
                                    ¿Cómo puedo cambiar mi contraseña?
                                </button>
                            </h3>
                            <div id="faqCollapse4" class="accordion-collapse collapse" aria-labelledby="faqHeading4" data-bs-parent="#faqAccordion">
                                <div class="accordion-body">
                                    Para cambiar tu contraseña, ve a la configuración de tu cuenta en el menú desplegable de tu perfil. Allí encontrarás una sección para cambiar tu contraseña.
                                </div>
                            </div>
                        </div>
                        
                        <div class="accordion-item">
                            <h3 class="accordion-header" id="faqHeading5">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#faqCollapse5" aria-expanded="false" aria-controls="faqCollapse5">
                                    ¿Cómo puedo reportar contenido inapropiado?
                                </button>
                            </h3>
                            <div id="faqCollapse5" class="accordion-collapse collapse" aria-labelledby="faqHeading5" data-bs-parent="#faqAccordion">
                                <div class="accordion-body">
                                    Si encuentras contenido que consideras inapropiado, puedes reportarlo haciendo clic en el botón "Reportar" que aparece en cada receta o comentario. Nuestro equipo revisará el contenido y tomará las medidas necesarias.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> 
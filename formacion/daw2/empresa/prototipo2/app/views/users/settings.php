<div class="container py-4">
    <div class="row">
        <div class="col-md-3 mb-4">
            <div class="card">
                <div class="card-header">
                    <h2 class="h5 mb-0">Configuración</h2>
                </div>
                <div class="list-group list-group-flush">
                    <a href="#profile" class="list-group-item list-group-item-action active" data-bs-toggle="list">Perfil</a>
                    <a href="#account" class="list-group-item list-group-item-action" data-bs-toggle="list">Cuenta</a>
                    <a href="#notifications" class="list-group-item list-group-item-action" data-bs-toggle="list">Notificaciones</a>
                    <a href="#privacy" class="list-group-item list-group-item-action" data-bs-toggle="list">Privacidad</a>
                    <a href="#accessibility" class="list-group-item list-group-item-action" data-bs-toggle="list">Accesibilidad</a>
                </div>
            </div>
            
            <div class="card mt-4">
                <div class="card-body">
                    <h3 class="h6 text-danger mb-3">Zona de peligro</h3>
                    <button type="button" class="btn btn-outline-danger btn-sm w-100" data-bs-toggle="modal" data-bs-target="#deleteAccountModal">
                        Eliminar cuenta
                    </button>
                </div>
            </div>
        </div>
        
        <div class="col-md-9">
            <div class="tab-content">
                <!-- Perfil -->
                <div class="tab-pane fade show active" id="profile">
                    <div class="card">
                        <div class="card-header">
                            <h2 class="h5 mb-0">Información de perfil</h2>
                        </div>
                        <div class="card-body">
                            <form action="<?php echo url('users/updateProfile'); ?>" method="post" enctype="multipart/form-data">
                                <div class="mb-3 text-center">
                                    <img src="<?php echo asset('img/users/' . ($data['user']->profile_image ?? 'default.jpg')); ?>" alt="<?php echo $data['user']->name; ?>" class="rounded-circle mb-3" width="150" height="150" style="object-fit: cover;" id="profileImagePreview">
                                    <div>
                                        <label for="profile_image" class="btn btn-outline-primary btn-sm">
                                            Cambiar imagen
                                        </label>
                                        <input type="file" class="form-control d-none image-upload" id="profile_image" name="profile_image" accept="image/*" data-preview="#profileImagePreview">
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label for="name" class="form-label">Nombre completo</label>
                                    <input type="text" class="form-control" id="name" name="name" value="<?php echo $data['user']->name; ?>" required>
                                </div>

                                <div class="mb-3">
                                    <label for="bio" class="form-label">Biografía</label>
                                    <textarea class="form-control" id="bio" name="bio" rows="4"><?php echo $data['user']->bio ?? ''; ?></textarea>
                                    <div class="form-text">Cuéntanos un poco sobre ti y tu pasión por la cocina.</div>
                                </div>

                                <div class="mb-3">
                                    <label for="location" class="form-label">Ubicación</label>
                                    <input type="text" class="form-control" id="location" name="location" value="<?php echo $data['user']->location ?? ''; ?>">
                                </div>

                                <div class="mb-3">
                                    <label for="website" class="form-label">Sitio web</label>
                                    <input type="url" class="form-control" id="website" name="website" value="<?php echo $data['user']->website ?? ''; ?>">
                                </div>

                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                
                <!-- Cuenta -->
                <div class="tab-pane fade" id="account">
                    <div class="card mb-4">
                        <div class="card-header">
                            <h2 class="h5 mb-0">Información de la cuenta</h2>
                        </div>
                        <div class="card-body">
                            <form action="<?php echo url('users/updateAccount'); ?>" method="post">
                                <div class="mb-3">
                                    <label for="email" class="form-label">Correo electrónico</label>
                                    <input type="email" class="form-control" id="email" name="email" value="<?php echo $data['user']->email; ?>" required>
                                </div>

                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-primary">Actualizar correo</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-header">
                            <h2 class="h5 mb-0">Cambiar contraseña</h2>
                        </div>
                        <div class="card-body">
                            <form action="<?php echo url('users/updatePassword'); ?>" method="post">
                                <div class="mb-3">
                                    <label for="current_password" class="form-label">Contraseña actual</label>
                                    <input type="password" class="form-control" id="current_password" name="current_password" required>
                                </div>

                                <div class="mb-3">
                                    <label for="password" class="form-label">Nueva contraseña</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>

                                <div class="mb-3">
                                    <label for="confirm_password" class="form-label">Confirmar nueva contraseña</label>
                                    <input type="password" class="form-control" id="confirm_password" name="confirm_password" required>
                                </div>

                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-primary">Cambiar contraseña</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                
                <!-- Notificaciones -->
                <div class="tab-pane fade" id="notifications">
                    <div class="card">
                        <div class="card-header">
                            <h2 class="h5 mb-0">Preferencias de notificaciones</h2>
                        </div>
                        <div class="card-body">
                            <form action="<?php echo url('users/updateNotifications'); ?>" method="post">
                                <div class="mb-3">
                                    <h3 class="h6">Notificaciones por correo electrónico</h3>
                                    <div class="form-check form-switch mb-2">
                                        <input class="form-check-input" type="checkbox" id="email_comments" name="email_comments" <?php echo $data['user']->email_comments ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="email_comments">Comentarios en mis recetas</label>
                                    </div>
                                    <div class="form-check form-switch mb-2">
                                        <input class="form-check-input" type="checkbox" id="email_likes" name="email_likes" <?php echo $data['user']->email_likes ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="email_likes">Me gusta en mis recetas</label>
                                    </div>
                                    <div class="form-check form-switch mb-2">
                                        <input class="form-check-input" type="checkbox" id="email_mentions" name="email_mentions" <?php echo $data['user']->email_mentions ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="email_mentions">Menciones</label>
                                    </div>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="email_newsletter" name="email_newsletter" <?php echo $data['user']->email_newsletter ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="email_newsletter">Boletín semanal con recetas destacadas</label>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <h3 class="h6">Notificaciones en el sitio</h3>
                                    <div class="form-check form-switch mb-2">
                                        <input class="form-check-input" type="checkbox" id="notify_comments" name="notify_comments" <?php echo $data['user']->notify_comments ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="notify_comments">Comentarios en mis recetas</label>
                                    </div>
                                    <div class="form-check form-switch mb-2">
                                        <input class="form-check-input" type="checkbox" id="notify_likes" name="notify_likes" <?php echo $data['user']->notify_likes ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="notify_likes">Me gusta en mis recetas</label>
                                    </div>
                                    <div class="form-check form-switch mb-2">
                                        <input class="form-check-input" type="checkbox" id="notify_mentions" name="notify_mentions" <?php echo $data['user']->notify_mentions ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="notify_mentions">Menciones</label>
                                    </div>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="notify_follows" name="notify_follows" <?php echo $data['user']->notify_follows ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="notify_follows">Nuevos seguidores</label>
                                    </div>
                                </div>

                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-primary">Guardar preferencias</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                
                <!-- Privacidad -->
                <div class="tab-pane fade" id="privacy">
                    <div class="card">
                        <div class="card-header">
                            <h2 class="h5 mb-0">Configuración de privacidad</h2>
                        </div>
                        <div class="card-body">
                            <form action="<?php echo url('users/updatePrivacy'); ?>" method="post">
                                <div class="mb-3">
                                    <h3 class="h6">Visibilidad del perfil</h3>
                                    <div class="form-check mb-2">
                                        <input class="form-check-input" type="radio" name="profile_visibility" id="profile_public" value="public" <?php echo $data['user']->profile_visibility == 'public' ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="profile_public">
                                            <strong>Público</strong> - Cualquier persona puede ver tu perfil y recetas
                                        </label>
                                    </div>
                                    <div class="form-check mb-2">
                                        <input class="form-check-input" type="radio" name="profile_visibility" id="profile_registered" value="registered" <?php echo $data['user']->profile_visibility == 'registered' ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="profile_registered">
                                            <strong>Solo usuarios registrados</strong> - Solo los usuarios con cuenta pueden ver tu perfil
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="profile_visibility" id="profile_private" value="private" <?php echo $data['user']->profile_visibility == 'private' ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="profile_private">
                                            <strong>Privado</strong> - Solo tú y los administradores pueden ver tu perfil
                                        </label>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <h3 class="h6">Otras opciones de privacidad</h3>
                                    <div class="form-check form-switch mb-2">
                                        <input class="form-check-input" type="checkbox" id="show_email" name="show_email" <?php echo $data['user']->show_email ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="show_email">Mostrar mi correo electrónico en mi perfil</label>
                                    </div>
                                    <div class="form-check form-switch mb-2">
                                        <input class="form-check-input" type="checkbox" id="show_location" name="show_location" <?php echo $data['user']->show_location ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="show_location">Mostrar mi ubicación en mi perfil</label>
                                    </div>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" id="allow_comments" name="allow_comments" <?php echo $data['user']->allow_comments ? 'checked' : ''; ?>>
                                        <label class="form-check-label" for="allow_comments">Permitir comentarios en mis recetas</label>
                                    </div>
                                </div>

                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" class="btn btn-primary">Guardar configuración</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                
                <!-- Accesibilidad -->
                <div class="tab-pane fade" id="accessibility">
                    <div class="card">
                        <div class="card-header">
                            <h2 class="h5 mb-0">Opciones de accesibilidad</h2>
                        </div>
                        <div class="card-body">
                            <div class="mb-3">
                                <h3 class="h6">Tamaño de texto</h3>
                                <div class="btn-group" role="group" aria-label="Tamaño de texto">
                                    <button type="button" class="btn btn-outline-secondary" id="font-size-normal">Normal</button>
                                    <button type="button" class="btn btn-outline-secondary" id="font-size-large">Grande</button>
                                    <button type="button" class="btn btn-outline-secondary" id="font-size-larger">Más grande</button>
                                    <button type="button" class="btn btn-outline-secondary" id="font-size-largest">Máximo</button>
                                </div>
                            </div>

                            <div class="mb-3">
                                <h3 class="h6">Contraste</h3>
                                <button type="button" class="btn btn-outline-dark" id="contrast-toggle">Activar alto contraste</button>
                            </div>

                            <div class="mb-3">
                                <h3 class="h6">Texto a voz</h3>
                                <div class="form-check form-switch">
                                    <input class="form-check-input" type="checkbox" id="enable_tts" name="enable_tts" checked>
                                    <label class="form-check-label" for="enable_tts">Habilitar botones de texto a voz</label>
                                </div>
                                <div class="form-text">Permite escuchar el contenido de recetas y otros textos importantes.</div>
                            </div>

                            <div class="alert alert-info">
                                <i class="fas fa-info-circle me-2"></i> Estas configuraciones se guardan automáticamente en tu navegador.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal para eliminar cuenta -->
<div class="modal fade" id="deleteAccountModal" tabindex="-1" aria-labelledby="deleteAccountModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteAccountModalLabel">Eliminar cuenta</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger">
                    <i class="fas fa-exclamation-triangle me-2"></i> Esta acción es irreversible
                </div>
                <p>Al eliminar tu cuenta:</p>
                <ul>
                    <li>Se eliminarán todas tus recetas y comentarios</li>
                    <li>Tu perfil ya no estará disponible</li>
                    <li>No podrás recuperar ninguna información</li>
                </ul>
                <p>Por favor, confirma que deseas eliminar tu cuenta ingresando tu contraseña:</p>
                <form id="deleteAccountForm" action="<?php echo url('users/delete'); ?>" method="post">
                    <div class="mb-3">
                        <label for="password_confirm" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="password_confirm" name="password_confirm" required>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="submit" form="deleteAccountForm" class="btn btn-danger">Eliminar mi cuenta</button>
            </div>
        </div>
    </div>
</div> 
<div class="container py-4">
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h1 class="h4 mb-0">Editar perfil</h1>
                </div>
                <div class="card-body">
                    <form action="<?php echo url('users/update'); ?>" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                        <div class="mb-3 text-center">
                            <img src="<?php echo asset('img/users/' . ($data['user']->profile_image ?? 'default.jpg')); ?>" alt="<?php echo $data['user']->name; ?>" class="rounded-circle mb-3" width="150" height="150" style="object-fit: cover;" id="profileImagePreview">
                            <div class="mb-3">
                                <label for="profile_image" class="form-label">Imagen de perfil</label>
                                <input type="file" class="form-control image-upload <?php echo isset($data['profile_image_err']) ? 'is-invalid' : ''; ?>" id="profile_image" name="profile_image" accept="image/*" data-preview="#profileImagePreview">
                                <div class="invalid-feedback">
                                    <?php echo $data['profile_image_err'] ?? 'Por favor selecciona una imagen válida.'; ?>
                                </div>
                                <div class="form-text">Deja este campo vacío si no deseas cambiar tu imagen de perfil.</div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="name" class="form-label">Nombre completo</label>
                            <input type="text" class="form-control <?php echo isset($data['name_err']) ? 'is-invalid' : ''; ?>" id="name" name="name" value="<?php echo $data['user']->name; ?>" required>
                            <div class="invalid-feedback">
                                <?php echo $data['name_err'] ?? 'Por favor ingresa tu nombre completo.'; ?>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control <?php echo isset($data['email_err']) ? 'is-invalid' : ''; ?>" id="email" name="email" value="<?php echo $data['user']->email; ?>" required>
                            <div class="invalid-feedback">
                                <?php echo $data['email_err'] ?? 'Por favor ingresa un correo electrónico válido.'; ?>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="bio" class="form-label">Biografía</label>
                            <textarea class="form-control <?php echo isset($data['bio_err']) ? 'is-invalid' : ''; ?>" id="bio" name="bio" rows="4"><?php echo $data['user']->bio ?? ''; ?></textarea>
                            <div class="invalid-feedback">
                                <?php echo $data['bio_err'] ?? 'La biografía no puede exceder los 500 caracteres.'; ?>
                            </div>
                            <div class="form-text">Cuéntanos un poco sobre ti y tu pasión por la cocina (máximo 500 caracteres).</div>
                        </div>

                        <hr class="my-4">

                        <h2 class="h5 mb-3">Cambiar contraseña</h2>
                        <p class="text-muted small mb-3">Deja estos campos en blanco si no deseas cambiar tu contraseña.</p>

                        <div class="mb-3">
                            <label for="current_password" class="form-label">Contraseña actual</label>
                            <input type="password" class="form-control <?php echo isset($data['current_password_err']) ? 'is-invalid' : ''; ?>" id="current_password" name="current_password">
                            <div class="invalid-feedback">
                                <?php echo $data['current_password_err'] ?? 'La contraseña actual es incorrecta.'; ?>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Nueva contraseña</label>
                            <input type="password" class="form-control <?php echo isset($data['password_err']) ? 'is-invalid' : ''; ?>" id="password" name="password">
                            <div class="invalid-feedback">
                                <?php echo $data['password_err'] ?? 'La contraseña debe tener al menos 6 caracteres.'; ?>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="confirm_password" class="form-label">Confirmar nueva contraseña</label>
                            <input type="password" class="form-control <?php echo isset($data['confirm_password_err']) ? 'is-invalid' : ''; ?>" id="confirm_password" name="confirm_password">
                            <div class="invalid-feedback">
                                <?php echo $data['confirm_password_err'] ?? 'Las contraseñas no coinciden.'; ?>
                            </div>
                        </div>

                        <hr class="my-4">

                        <h2 class="h5 mb-3">Preferencias</h2>

                        <div class="mb-3">
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" id="email_notifications" name="email_notifications" <?php echo $data['user']->email_notifications ? 'checked' : ''; ?>>
                                <label class="form-check-label" for="email_notifications">Recibir notificaciones por correo electrónico</label>
                            </div>
                            <div class="form-text">Recibirás correos cuando alguien comente tus recetas o te mencione.</div>
                        </div>

                        <div class="mb-3">
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" id="public_profile" name="public_profile" <?php echo $data['user']->public_profile ? 'checked' : ''; ?>>
                                <label class="form-check-label" for="public_profile">Perfil público</label>
                            </div>
                            <div class="form-text">Si desactivas esta opción, tu perfil solo será visible para usuarios registrados.</div>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a href="<?php echo url('users/profile'); ?>" class="btn btn-secondary me-md-2">Cancelar</a>
                            <button type="submit" class="btn btn-primary">Guardar cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div> 
<div class="row">
    <div class="col-md-6 mx-auto">
        <div class="card card-body bg-light mt-4">
            <h2 class="text-center"><?php echo $data['title']; ?></h2>
            <p class="text-center">Complete este formulario para registrarse en Manos a la Olla</p>
            <form action="<?php echo url('users/register'); ?>" method="post" novalidate>
                <div class="mb-3">
                    <label for="name" class="form-label">Nombre completo <span class="text-danger">*</span></label>
                    <input type="text" name="name" id="name" class="form-control <?php echo (!empty($data['name_err'])) ? 'is-invalid' : ''; ?>" value="<?php echo $data['name']; ?>" required <?php echo ariaDescribedby('nameHelp'); ?>>
                    <div id="nameHelp" class="form-text">Ingrese su nombre completo como desea que aparezca en el sitio.</div>
                    <div class="invalid-feedback">
                        <?php echo $data['name_err']; ?>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Correo electrónico <span class="text-danger">*</span></label>
                    <input type="email" name="email" id="email" class="form-control <?php echo (!empty($data['email_err'])) ? 'is-invalid' : ''; ?>" value="<?php echo $data['email']; ?>" required <?php echo ariaDescribedby('emailHelp'); ?>>
                    <div id="emailHelp" class="form-text">Su correo electrónico no será compartido con terceros.</div>
                    <div class="invalid-feedback">
                        <?php echo $data['email_err']; ?>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña <span class="text-danger">*</span></label>
                    <input type="password" name="password" id="password" class="form-control <?php echo (!empty($data['password_err'])) ? 'is-invalid' : ''; ?>" value="<?php echo $data['password']; ?>" required <?php echo ariaDescribedby('passwordHelp'); ?>>
                    <div id="passwordHelp" class="form-text">La contraseña debe tener al menos 6 caracteres.</div>
                    <div class="invalid-feedback">
                        <?php echo $data['password_err']; ?>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="confirm_password" class="form-label">Confirmar contraseña <span class="text-danger">*</span></label>
                    <input type="password" name="confirm_password" id="confirm_password" class="form-control <?php echo (!empty($data['confirm_password_err'])) ? 'is-invalid' : ''; ?>" value="<?php echo $data['confirm_password']; ?>" required>
                    <div class="invalid-feedback">
                        <?php echo $data['confirm_password_err']; ?>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col">
                        <input type="submit" value="Registrarse" class="btn btn-primary w-100">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col">
                        <p class="text-center">¿Ya tiene una cuenta? <a href="<?php echo url('users/login'); ?>">Iniciar Sesión</a></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div> 
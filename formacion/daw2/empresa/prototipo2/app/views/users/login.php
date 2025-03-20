<div class="row">
    <div class="col-md-6 mx-auto">
        <div class="card card-body bg-light mt-4">
            <h2 class="text-center"><?php echo $data['title']; ?></h2>
            <p class="text-center">Ingrese sus credenciales para iniciar sesión</p>
            <form action="<?php echo url('users/login'); ?>" method="post" novalidate>
                <div class="mb-3">
                    <label for="email" class="form-label">Correo electrónico <span class="text-danger">*</span></label>
                    <input type="email" name="email" id="email" class="form-control <?php echo (!empty($data['email_err'])) ? 'is-invalid' : ''; ?>" value="<?php echo $data['email']; ?>" required>
                    <div class="invalid-feedback">
                        <?php echo $data['email_err']; ?>
                    </div>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña <span class="text-danger">*</span></label>
                    <input type="password" name="password" id="password" class="form-control <?php echo (!empty($data['password_err'])) ? 'is-invalid' : ''; ?>" value="<?php echo $data['password']; ?>" required>
                    <div class="invalid-feedback">
                        <?php echo $data['password_err']; ?>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col">
                        <input type="submit" value="Iniciar Sesión" class="btn btn-primary w-100">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col">
                        <p class="text-center">¿No tiene una cuenta? <a href="<?php echo url('users/register'); ?>">Registrarse</a></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div> 
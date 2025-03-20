<?php
// Incluir el header
include RUTA_APLICACION . '/vistas/componentes/header.php';
?>

<div class="container mt-5">
    <?php if (isset($_SESSION['mensaje_exito'])): ?>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <?php echo $_SESSION['mensaje_exito']; ?>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <?php unset($_SESSION['mensaje_exito']); ?>
    <?php endif; ?>

    <?php if (isset($_SESSION['errores_perfil'])): ?>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <ul class="mb-0">
                <?php foreach ($_SESSION['errores_perfil'] as $error): ?>
                    <li><?php echo $error; ?></li>
                <?php endforeach; ?>
            </ul>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <?php unset($_SESSION['errores_perfil']); ?>
    <?php endif; ?>

    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h1 class="h3 mb-0"><i class="fas fa-user-circle me-2"></i>Mi Perfil</h1>
                </div>
                <div class="card-body">
                    <div class="row mb-4">
                        <div class="col-md-4 text-center">
                            <div class="mb-3">
                                <i class="fas fa-user-circle fa-6x text-primary"></i>
                            </div>
                            <h4 class="mb-0"><?php echo htmlspecialchars($usuario['nombre']); ?></h4>
                            <p class="text-muted"><?php echo $usuario['rol'] === 'admin' ? 'Administrador' : 'Cliente'; ?></p>
                        </div>
                        <div class="col-md-8">
                            <h5 class="mb-3">Información Personal</h5>
                            <div class="mb-3">
                                <label class="text-muted">Email:</label>
                                <p class="mb-2"><?php echo htmlspecialchars($usuario['email']); ?></p>
                            </div>
                            <?php if (isset($usuario['telefono'])): ?>
                            <div class="mb-3">
                                <label class="text-muted">Teléfono:</label>
                                <p class="mb-2"><?php echo htmlspecialchars($usuario['telefono']); ?></p>
                            </div>
                            <?php endif; ?>
                            <?php if (isset($usuario['direccion'])): ?>
                            <div class="mb-3">
                                <label class="text-muted">Dirección:</label>
                                <p class="mb-2"><?php echo htmlspecialchars($usuario['direccion']); ?></p>
                            </div>
                            <?php endif; ?>
                            <?php if (isset($usuario['codigo_postal'])): ?>
                            <div class="mb-3">
                                <label class="text-muted">Código Postal:</label>
                                <p class="mb-2"><?php echo htmlspecialchars($usuario['codigo_postal']); ?></p>
                            </div>
                            <?php endif; ?>
                            <?php if (isset($usuario['ciudad'])): ?>
                            <div class="mb-3">
                                <label class="text-muted">Ciudad:</label>
                                <p class="mb-2"><?php echo htmlspecialchars($usuario['ciudad']); ?></p>
                            </div>
                            <?php endif; ?>
                            <?php if (isset($usuario['provincia'])): ?>
                            <div class="mb-3">
                                <label class="text-muted">Provincia:</label>
                                <p class="mb-2"><?php echo htmlspecialchars($usuario['provincia']); ?></p>
                            </div>
                            <?php endif; ?>
                        </div>
                    </div>
                    <div class="border-top pt-4">
                        <div class="row">
                            <div class="col-md-6">
                                <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/pedidos" class="btn btn-outline-primary w-100 mb-2">
                                    <i class="fas fa-box me-2"></i>Mis Pedidos
                                </a>
                            </div>
                            <div class="col-md-6">
                                <a href="#" class="btn btn-outline-secondary w-100 mb-2" data-bs-toggle="modal" data-bs-target="#editarPerfilModal">
                                    <i class="fas fa-edit me-2"></i>Editar Perfil
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal para editar perfil -->
<div class="modal fade" id="editarPerfilModal" tabindex="-1" aria-labelledby="editarPerfilModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editarPerfilModalLabel">Editar Perfil</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="formEditarPerfil" action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/perfil/actualizar" method="post">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="<?php echo htmlspecialchars($usuario['nombre']); ?>" required>
                    </div>
                    <div class="mb-3">
                        <label for="telefono" class="form-label">Teléfono</label>
                        <input type="tel" class="form-control" id="telefono" name="telefono" value="<?php echo isset($usuario['telefono']) ? htmlspecialchars($usuario['telefono']) : ''; ?>">
                    </div>
                    <div class="mb-3">
                        <label for="direccion" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="direccion" name="direccion" value="<?php echo isset($usuario['direccion']) ? htmlspecialchars($usuario['direccion']) : ''; ?>">
                    </div>
                    <div class="mb-3">
                        <label for="codigo_postal" class="form-label">Código Postal</label>
                        <input type="text" class="form-control" id="codigo_postal" name="codigo_postal" value="<?php echo isset($usuario['codigo_postal']) ? htmlspecialchars($usuario['codigo_postal']) : ''; ?>">
                    </div>
                    <div class="mb-3">
                        <label for="ciudad" class="form-label">Ciudad</label>
                        <input type="text" class="form-control" id="ciudad" name="ciudad" value="<?php echo isset($usuario['ciudad']) ? htmlspecialchars($usuario['ciudad']) : ''; ?>">
                    </div>
                    <div class="mb-3">
                        <label for="provincia" class="form-label">Provincia</label>
                        <input type="text" class="form-control" id="provincia" name="provincia" value="<?php echo isset($usuario['provincia']) ? htmlspecialchars($usuario['provincia']) : ''; ?>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="submit" form="formEditarPerfil" class="btn btn-primary">Guardar Cambios</button>
            </div>
        </div>
    </div>
</div>

<?php
// Incluir el footer
include RUTA_APLICACION . '/vistas/componentes/footer.php';
?> 
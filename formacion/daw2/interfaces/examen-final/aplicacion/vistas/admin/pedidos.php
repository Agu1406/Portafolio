<?php include RUTA_APLICACION . '/vistas/componentes/header.php'; ?>

<div class="container mt-4">
    <h1><?php echo $tituloPagina; ?></h1>
    
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Código</th>
                    <th>Cliente</th>
                    <th>Estado</th>
                    <th>Total</th>
                    <th>Productos</th>
                    <th>Fecha</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <?php if ($pedidos && count($pedidos) > 0): ?>
                    <?php foreach ($pedidos as $pedido): ?>
                        <tr>
                            <td><?php echo $pedido['id']; ?></td>
                            <td><?php echo htmlspecialchars($pedido['codigo']); ?></td>
                            <td>
                                <?php echo htmlspecialchars($pedido['nombre_usuario']); ?><br>
                                <small class="text-muted"><?php echo htmlspecialchars($pedido['email_usuario']); ?></small>
                            </td>
                            <td>
                                <?php
                                $claseEstado = '';
                                switch ($pedido['estado']) {
                                    case 'pendiente':
                                        $claseEstado = 'bg-warning';
                                        break;
                                    case 'procesando':
                                        $claseEstado = 'bg-info';
                                        break;
                                    case 'completado':
                                        $claseEstado = 'bg-success';
                                        break;
                                    case 'cancelado':
                                        $claseEstado = 'bg-danger';
                                        break;
                                }
                                ?>
                                <span class="badge <?php echo $claseEstado; ?>"><?php echo ucfirst($pedido['estado']); ?></span>
                            </td>
                            <td><?php echo number_format($pedido['total'], 2); ?> €</td>
                            <td><?php echo $pedido['total_productos']; ?></td>
                            <td><?php echo date('d/m/Y H:i', strtotime($pedido['fecha_pedido'])); ?></td>
                            <td>
                                <div class="btn-group">
                                    <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/pedidos/ver?id=<?php echo $pedido['id']; ?>" class="btn btn-sm btn-info">
                                        <i class="fas fa-eye"></i>
                                    </a>
                                    <button type="button" class="btn btn-sm btn-primary" onclick="cambiarEstado(<?php echo $pedido['id']; ?>)">
                                        <i class="fas fa-sync-alt"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                <?php else: ?>
                    <tr>
                        <td colspan="8" class="text-center">No hay pedidos disponibles</td>
                    </tr>
                <?php endif; ?>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal para cambiar estado -->
<div class="modal fade" id="modalCambiarEstado" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Cambiar estado del pedido</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="formCambiarEstado" action="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/admin/pedidos/cambiar-estado" method="POST">
                    <input type="hidden" name="pedido_id" id="pedido_id">
                    <div class="mb-3">
                        <label for="estado" class="form-label">Nuevo estado</label>
                        <select class="form-select" id="estado" name="estado" required>
                            <option value="pendiente">Pendiente</option>
                            <option value="procesando">Procesando</option>
                            <option value="completado">Completado</option>
                            <option value="cancelado">Cancelado</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
function cambiarEstado(pedidoId) {
    document.getElementById('pedido_id').value = pedidoId;
    var modal = new bootstrap.Modal(document.getElementById('modalCambiarEstado'));
    modal.show();
}
</script>

<?php include RUTA_APLICACION . '/vistas/componentes/footer.php'; ?> 
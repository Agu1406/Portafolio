<!-- Modal de confirmación genérico -->
<div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmModalLabel">Confirmar acción</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
                <p id="confirmModalMessage">¿Está seguro de que desea realizar esta acción?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <form id="confirmModalForm" method="post">
                    <button type="submit" class="btn btn-danger">Confirmar</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    // Función para configurar el modal de confirmación
    window.configureConfirmModal = function(message, formAction) {
        const modal = document.getElementById('confirmModal');
        const messageElement = document.getElementById('confirmModalMessage');
        const form = document.getElementById('confirmModalForm');
        
        if (messageElement && form) {
            messageElement.textContent = message;
            form.action = formAction;
        }
    };
});
</script> 
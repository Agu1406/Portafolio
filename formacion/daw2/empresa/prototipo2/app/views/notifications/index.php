<div class="container py-4">
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h1 class="h4 mb-0">Notificaciones</h1>
                    <?php if(!empty($data['notifications'])) : ?>
                        <form action="<?php echo url('notifications/markAllAsRead'); ?>" method="post">
                            <button type="submit" class="btn btn-sm btn-outline-primary">Marcar todas como leídas</button>
                        </form>
                    <?php endif; ?>
                </div>
                <div class="card-body p-0">
                    <?php if(empty($data['notifications'])) : ?>
                        <div class="text-center p-4">
                            <div class="mb-3">
                                <i class="fas fa-bell fa-3x text-muted"></i>
                            </div>
                            <p class="text-muted">No tienes notificaciones en este momento.</p>
                        </div>
                    <?php else : ?>
                        <div class="list-group list-group-flush">
                            <?php foreach($data['notifications'] as $notification) : ?>
                                <a href="<?php echo $notification->url; ?>" 
                                   class="list-group-item list-group-item-action notification-item <?php echo $notification->read ? 'text-muted' : 'fw-bold'; ?>"
                                   data-notification-id="<?php echo $notification->id; ?>">
                                    <div class="d-flex w-100 justify-content-between align-items-center">
                                        <div class="d-flex align-items-center">
                                            <div class="me-3">
                                                <i class="<?php echo $notification->icon; ?> fa-lg <?php echo $notification->read ? 'text-muted' : 'text-primary'; ?>"></i>
                                            </div>
                                            <div>
                                                <p class="mb-1"><?php echo $notification->message; ?></p>
                                                <small class="text-muted"><?php echo timeAgo($notification->created_at); ?></small>
                                            </div>
                                        </div>
                                        <?php if(!$notification->read) : ?>
                                            <span class="badge bg-primary rounded-pill">Nueva</span>
                                        <?php endif; ?>
                                    </div>
                                </a>
                            <?php endforeach; ?>
                        </div>
                        
                        <!-- Paginación -->
                        <?php if($data['total_pages'] > 1) : ?>
                            <div class="d-flex justify-content-center p-3">
                                <nav aria-label="Navegación de notificaciones">
                                    <ul class="pagination pagination-sm">
                                        <?php if($data['current_page'] > 1) : ?>
                                            <li class="page-item">
                                                <a class="page-link" href="<?php echo url('notifications?page=' . ($data['current_page'] - 1)); ?>" aria-label="Anterior">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        <?php endif; ?>
                                        
                                        <?php for($i = 1; $i <= $data['total_pages']; $i++) : ?>
                                            <li class="page-item <?php echo $i == $data['current_page'] ? 'active' : ''; ?>">
                                                <a class="page-link" href="<?php echo url('notifications?page=' . $i); ?>"><?php echo $i; ?></a>
                                            </li>
                                        <?php endfor; ?>
                                        
                                        <?php if($data['current_page'] < $data['total_pages']) : ?>
                                            <li class="page-item">
                                                <a class="page-link" href="<?php echo url('notifications?page=' . ($data['current_page'] + 1)); ?>" aria-label="Siguiente">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        <?php endif; ?>
                                    </ul>
                                </nav>
                            </div>
                        <?php endif; ?>
                    <?php endif; ?>
                </div>
            </div>
            
            <div class="card mt-4">
                <div class="card-header">
                    <h2 class="h5 mb-0">Preferencias de notificaciones</h2>
                </div>
                <div class="card-body">
                    <p>Puedes personalizar tus preferencias de notificaciones en la <a href="<?php echo url('users/settings#notifications'); ?>">configuración de tu cuenta</a>.</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    // Marcar notificaciones como leídas al hacer clic
    const notificationItems = document.querySelectorAll('.notification-item');
    
    notificationItems.forEach(item => {
        item.addEventListener('click', function(e) {
            const notificationId = this.getAttribute('data-notification-id');
            
            // Enviar solicitud AJAX para marcar como leída
            fetch(`<?php echo url('notifications/markAsRead'); ?>/${notificationId}`, {
                method: 'POST',
                headers: {
                    'X-Requested-With': 'XMLHttpRequest'
                }
            });
            
            // Actualizar la apariencia
            this.classList.remove('fw-bold');
            this.classList.add('text-muted');
            
            // Ocultar la insignia "Nueva"
            const badge = this.querySelector('.badge');
            if (badge) {
                badge.style.display = 'none';
            }
            
            // Cambiar el color del icono
            const icon = this.querySelector('i');
            if (icon) {
                icon.classList.remove('text-primary');
                icon.classList.add('text-muted');
            }
        });
    });
});
</script> 
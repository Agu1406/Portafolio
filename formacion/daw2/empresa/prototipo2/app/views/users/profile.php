<div class="row">
    <div class="col-md-4 mb-4">
        <div class="card">
            <div class="card-body text-center">
                <img src="<?php echo asset('img/users/' . ($data['user']->profile_image ?? 'default.jpg')); ?>" alt="Foto de perfil" class="rounded-circle img-fluid mb-3" style="max-width: 150px;">
                <h2 class="card-title"><?php echo $data['user']->name; ?></h2>
                <p class="text-muted">Miembro desde <?php echo date('d/m/Y', strtotime($data['user']->created_at)); ?></p>
                
                <?php if(!empty($data['user']->bio)) : ?>
                <div id="user-bio" class="mb-3">
                    <h5>Sobre mí</h5>
                    <p><?php echo $data['user']->bio; ?></p>
                </div>
                <?php echo textToSpeechButton('user-bio'); ?>
                <?php endif; ?>
                
                <div class="d-grid gap-2 mt-3">
                    <a href="<?php echo url('users/edit'); ?>" class="btn btn-primary">Editar Perfil</a>
                    <a href="<?php echo url('recipes/add'); ?>" class="btn btn-success">Añadir Receta</a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-md-8">
        <div class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h3 class="mb-0">Mis Recetas</h3>
                <a href="<?php echo url('recipes/add'); ?>" class="btn btn-sm btn-primary">Añadir Nueva</a>
            </div>
            <div class="card-body">
                <?php if(empty($data['recipes'])) : ?>
                    <p class="text-center">Aún no has compartido ninguna receta.</p>
                    <div class="text-center">
                        <a href="<?php echo url('recipes/add'); ?>" class="btn btn-primary">Compartir mi primera receta</a>
                    </div>
                <?php else : ?>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Título</th>
                                    <th>Categoría</th>
                                    <th>Fecha</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php foreach($data['recipes'] as $recipe) : ?>
                                <tr>
                                    <td>
                                        <a href="<?php echo url('recipes/show/' . $recipe->id); ?>" class="text-decoration-none">
                                            <?php echo $recipe->title; ?>
                                        </a>
                                    </td>
                                    <td><?php echo $recipe->category_name; ?></td>
                                    <td><?php echo date('d/m/Y', strtotime($recipe->created_at)); ?></td>
                                    <td>
                                        <a href="<?php echo url('recipes/edit/' . $recipe->id); ?>" class="btn btn-sm btn-outline-primary" <?php echo ariaLabel('Editar ' . $recipe->title); ?>>
                                            <i class="fas fa-edit"></i>
                                        </a>
                                        <button type="button" class="btn btn-sm btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteModal<?php echo $recipe->id; ?>" <?php echo ariaLabel('Eliminar ' . $recipe->title); ?>>
                                            <i class="fas fa-trash"></i>
                                        </button>
                                        
                                        <!-- Modal de confirmación para eliminar -->
                                        <div class="modal fade" id="deleteModal<?php echo $recipe->id; ?>" tabindex="-1" aria-labelledby="deleteModalLabel<?php echo $recipe->id; ?>" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="deleteModalLabel<?php echo $recipe->id; ?>">Confirmar eliminación</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        ¿Está seguro de que desea eliminar la receta <strong><?php echo $recipe->title; ?></strong>? Esta acción no se puede deshacer.
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                                        <form action="<?php echo url('recipes/delete/' . $recipe->id); ?>" method="post">
                                                            <button type="submit" class="btn btn-danger">Eliminar</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <?php endforeach; ?>
                            </tbody>
                        </table>
                    </div>
                <?php endif; ?>
            </div>
        </div>
        
        <div class="card">
            <div class="card-header">
                <h3 class="mb-0">Estadísticas</h3>
            </div>
            <div class="card-body">
                <div class="row text-center">
                    <div class="col-md-4 mb-3">
                        <div class="p-3 bg-light rounded">
                            <h4 class="h5"><?php echo count($data['recipes']); ?></h4>
                            <p class="mb-0">Recetas compartidas</p>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="p-3 bg-light rounded">
                            <h4 class="h5">
                                <?php 
                                // Contar comentarios recibidos
                                $commentCount = 0;
                                foreach($data['recipes'] as $recipe) {
                                    $commentModel = new Comment();
                                    $comments = $commentModel->getCommentsByRecipe($recipe->id);
                                    $commentCount += count($comments);
                                }
                                echo $commentCount;
                                ?>
                            </h4>
                            <p class="mb-0">Comentarios recibidos</p>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <div class="p-3 bg-light rounded">
                            <h4 class="h5">
                                <?php 
                                // Obtener categorías utilizadas
                                $categories = [];
                                foreach($data['recipes'] as $recipe) {
                                    if(!in_array($recipe->category_name, $categories)) {
                                        $categories[] = $recipe->category_name;
                                    }
                                }
                                echo count($categories);
                                ?>
                            </h4>
                            <p class="mb-0">Categorías utilizadas</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> 
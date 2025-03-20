<?php require APPROOT . '/views/layouts/main.php'; ?>

<div class="row mb-4">
    <div class="col-md-8">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="<?php echo url(''); ?>">Inicio</a></li>
                <li class="breadcrumb-item"><a href="<?php echo url('recipes'); ?>">Recetas</a></li>
                <li class="breadcrumb-item"><a href="<?php echo url('recipes/category/' . $data['recipe']->category_id); ?>"><?php echo $data['recipe']->category_name; ?></a></li>
                <li class="breadcrumb-item active" aria-current="page"><?php echo $data['recipe']->title; ?></li>
            </ol>
        </nav>
    </div>
    <div class="col-md-4 text-md-end">
        <?php if(isLoggedIn() && $_SESSION['user_id'] == $data['recipe']->user_id) : ?>
            <a href="<?php echo url('recipes/edit/' . $data['recipe']->id); ?>" class="btn btn-primary me-2">
                <i class="fas fa-edit"></i> Editar
            </a>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">
                <i class="fas fa-trash"></i> Eliminar
            </button>
            
            <!-- Modal de confirmación para eliminar -->
            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">Confirmar eliminación</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            ¿Está seguro de que desea eliminar la receta <strong><?php echo $data['recipe']->title; ?></strong>? Esta acción no se puede deshacer.
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <form action="<?php echo url('recipes/delete/' . $data['recipe']->id); ?>" method="post">
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        <?php endif; ?>
    </div>
</div>

<div class="row">
    <div class="col-md-8">
        <div class="card mb-4">
            <?php 
            // Verificar si la imagen es una URL completa o un nombre de archivo local
            $imageUrl = (strpos($data['recipe']->image, 'http') === 0) 
                ? $data['recipe']->image 
                : asset('img/recipes/' . $data['recipe']->image);
            ?>
            <img src="<?php echo $imageUrl; ?>" class="card-img-top" alt="<?php echo $data['recipe']->title; ?>">
            <div class="card-body">
                <h1 class="card-title"><?php echo $data['recipe']->title; ?></h1>
                <p class="card-text">
                    <small class="text-muted">
                        <i class="fas fa-user me-1"></i> Por <a href="#"><?php echo $data['recipe']->author; ?></a> |
                        <i class="fas fa-folder me-1"></i> <a href="<?php echo url('recipes/category/' . $data['recipe']->category_id); ?>"><?php echo $data['recipe']->category_name; ?></a> |
                        <i class="fas fa-calendar me-1"></i> <?php echo date('d/m/Y', strtotime($data['recipe']->created_at)); ?>
                    </small>
                </p>
                
                <div class="d-flex flex-wrap mb-4">
                    <span class="badge bg-<?php 
                        echo $data['recipe']->difficulty == 'Fácil' ? 'success' : 
                            ($data['recipe']->difficulty == 'Media' ? 'warning' : 'danger'); 
                    ?> me-2 mb-2">
                        <i class="fas fa-signal me-1"></i> <?php echo $data['recipe']->difficulty; ?>
                    </span>
                    <?php if(!empty($data['recipe']->preparation_time)) : ?>
                        <span class="badge bg-info text-dark me-2 mb-2">
                            <i class="fas fa-clock me-1"></i> Preparación: <?php echo $data['recipe']->preparation_time; ?>
                        </span>
                    <?php endif; ?>
                    <?php if(!empty($data['recipe']->cooking_time)) : ?>
                        <span class="badge bg-info text-dark me-2 mb-2">
                            <i class="fas fa-fire me-1"></i> Cocción: <?php echo $data['recipe']->cooking_time; ?>
                        </span>
                    <?php endif; ?>
                    <?php if(!empty($data['recipe']->servings)) : ?>
                        <span class="badge bg-info text-dark me-2 mb-2">
                            <i class="fas fa-utensils me-1"></i> Porciones: <?php echo $data['recipe']->servings; ?>
                        </span>
                    <?php endif; ?>
                </div>
                
                <div class="mb-4">
                    <h2 class="h4">Ingredientes</h2>
                    <div id="recipe-ingredients" class="p-3 bg-light rounded">
                        <?php 
                        // Convertir texto de ingredientes a lista HTML
                        $ingredients = explode("\n", $data['recipe']->ingredients);
                        echo '<ul class="mb-0">';
                        foreach($ingredients as $ingredient) {
                            if(!empty(trim($ingredient))) {
                                echo '<li>' . htmlspecialchars(trim($ingredient)) . '</li>';
                            }
                        }
                        echo '</ul>';
                        ?>
                    </div>
                    <?php echo textToSpeechButton('recipe-ingredients', 'Escuchar ingredientes'); ?>
                </div>
                
                <div class="mb-4">
                    <h2 class="h4">Instrucciones</h2>
                    <div id="recipe-instructions" class="p-3 bg-light rounded">
                        <?php 
                        // Convertir texto de instrucciones a lista numerada HTML
                        $instructions = explode("\n", $data['recipe']->instructions);
                        echo '<ol class="mb-0">';
                        foreach($instructions as $instruction) {
                            if(!empty(trim($instruction))) {
                                echo '<li class="mb-2">' . htmlspecialchars(trim($instruction)) . '</li>';
                            }
                        }
                        echo '</ol>';
                        ?>
                    </div>
                    <?php echo textToSpeechButton('recipe-instructions', 'Escuchar instrucciones'); ?>
                </div>
                
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <button class="btn btn-outline-primary me-2" onclick="window.print()">
                            <i class="fas fa-print me-1"></i> Imprimir
                        </button>
                        <button class="btn btn-outline-success" onclick="shareRecipe()">
                            <i class="fas fa-share-alt me-1"></i> Compartir
                        </button>
                    </div>
                    <div>
                        <button class="btn btn-outline-danger">
                            <i class="fas fa-heart me-1"></i> Favorito
                        </button>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Sección de comentarios -->
        <div class="card mb-4">
            <div class="card-header">
                <h2 class="h5 mb-0">Comentarios (<?php echo count($data['comments']); ?>)</h2>
            </div>
            <div class="card-body">
                <?php if(isLoggedIn()) : ?>
                    <form action="<?php echo url('recipes/comment/' . $data['recipe']->id); ?>" method="post" class="mb-4">
                        <div class="mb-3">
                            <label for="body" class="form-label">Deja tu comentario</label>
                            <textarea class="form-control" id="body" name="body" rows="3" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar Comentario</button>
                    </form>
                <?php else : ?>
                    <div class="alert alert-info">
                        <a href="<?php echo url('users/login'); ?>">Inicia sesión</a> para dejar un comentario.
                    </div>
                <?php endif; ?>
                
                <hr>
                
                <?php if(empty($data['comments'])) : ?>
                    <p class="text-center">No hay comentarios aún. ¡Sé el primero en comentar!</p>
                <?php else : ?>
                    <?php foreach($data['comments'] as $comment) : ?>
                        <div class="d-flex mb-4">
                            <div class="flex-shrink-0">
                                <img src="<?php echo asset('img/users/default.jpg'); ?>" alt="<?php echo $comment->user_name; ?>" class="rounded-circle" width="50">
                            </div>
                            <div class="ms-3">
                                <div class="d-flex align-items-center mb-1">
                                    <h3 class="h6 mb-0 me-2"><?php echo $comment->user_name; ?></h3>
                                    <small class="text-muted"><?php echo date('d/m/Y H:i', strtotime($comment->created_at)); ?></small>
                                </div>
                                <div id="comment-<?php echo $comment->id; ?>">
                                    <?php echo htmlspecialchars($comment->body); ?>
                                </div>
                                <?php echo textToSpeechButton('comment-' . $comment->id, 'Escuchar'); ?>
                                
                                <?php if(isLoggedIn() && $_SESSION['user_id'] == $comment->user_id) : ?>
                                    <div class="mt-2">
                                        <button class="btn btn-sm btn-outline-primary me-1">Editar</button>
                                        <button class="btn btn-sm btn-outline-danger">Eliminar</button>
                                    </div>
                                <?php endif; ?>
                            </div>
                        </div>
                    <?php endforeach; ?>
                <?php endif; ?>
            </div>
        </div>
    </div>
    
    <div class="col-md-4">
        <!-- Información del autor -->
        <div class="card mb-4">
            <div class="card-header">
                <h3 class="h5 mb-0">Sobre el autor</h3>
            </div>
            <div class="card-body text-center">
                <?php 
                // Verificar si la imagen es una URL completa o un nombre de archivo local
                $userImageUrl = (strpos($data['user']->profile_image, 'http') === 0) 
                    ? $data['user']->profile_image 
                    : asset('img/users/' . ($data['user']->profile_image ?? 'default.jpg'));
                ?>
                <img src="<?php echo $userImageUrl; ?>" alt="<?php echo $data['user']->name; ?>" class="rounded-circle mb-3" width="100">
                <h4 class="h6"><?php echo $data['user']->name; ?></h4>
                <p class="small text-muted">Miembro desde <?php echo date('d/m/Y', strtotime($data['user']->created_at)); ?></p>
                <?php if(!empty($data['user']->bio)) : ?>
                    <p class="small"><?php echo htmlspecialchars($data['user']->bio); ?></p>
                <?php endif; ?>
                <a href="#" class="btn btn-sm btn-outline-primary">Ver perfil</a>
            </div>
        </div>
        
        <!-- Recetas relacionadas -->
        <div class="card mb-4">
            <div class="card-header">
                <h3 class="h5 mb-0">Recetas relacionadas</h3>
            </div>
            <div class="card-body">
                <?php 
                // Obtener recetas de la misma categoría
                $recipeModel = new Recipe();
                $relatedRecipes = $recipeModel->getRecipesByCategory($data['recipe']->category_id);
                
                // Filtrar la receta actual
                $relatedRecipes = array_filter($relatedRecipes, function($recipe) use ($data) {
                    return $recipe->id != $data['recipe']->id;
                });
                
                // Limitar a 3 recetas
                $relatedRecipes = array_slice($relatedRecipes, 0, 3);
                
                if(empty($relatedRecipes)) : 
                ?>
                    <p class="text-center small">No hay recetas relacionadas.</p>
                <?php else : ?>
                    <div class="list-group list-group-flush">
                        <?php foreach($relatedRecipes as $recipe) : ?>
                            <a href="<?php echo url('recipes/show/' . $recipe->id); ?>" class="list-group-item list-group-item-action">
                                <div class="d-flex align-items-center">
                                    <?php 
                                    // Verificar si la imagen es una URL completa o un nombre de archivo local
                                    $relatedImageUrl = (strpos($recipe->image, 'http') === 0) 
                                        ? $recipe->image 
                                        : asset('img/recipes/' . $recipe->image);
                                    ?>
                                    <img src="<?php echo $relatedImageUrl; ?>" alt="<?php echo $recipe->title; ?>" class="me-3" width="50" height="50" style="object-fit: cover;">
                                    <div>
                                        <h4 class="h6 mb-0"><?php echo $recipe->title; ?></h4>
                                        <small class="text-muted">Por <?php echo $recipe->author; ?></small>
                                    </div>
                                </div>
                            </a>
                        <?php endforeach; ?>
                    </div>
                <?php endif; ?>
            </div>
        </div>
        
        <!-- Categorías populares -->
        <div class="card">
            <div class="card-header">
                <h3 class="h5 mb-0">Categorías populares</h3>
            </div>
            <div class="card-body">
                <?php 
                $categoryModel = new Category();
                $categories = $categoryModel->getPopularCategories(5);
                ?>
                <div class="list-group list-group-flush">
                    <?php foreach($categories as $category) : ?>
                        <a href="<?php echo url('recipes/category/' . $category->id); ?>" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                            <?php echo $category->name; ?>
                            <span class="badge bg-primary rounded-pill"><?php echo $category->recipe_count; ?></span>
                        </a>
                    <?php endforeach; ?>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
function shareRecipe() {
    if (navigator.share) {
        navigator.share({
            title: '<?php echo $data['recipe']->title; ?>',
            text: 'Mira esta deliciosa receta de <?php echo $data['recipe']->title; ?> en Manos a la Olla',
            url: window.location.href
        })
        .then(() => console.log('Compartido con éxito'))
        .catch((error) => console.log('Error al compartir', error));
    } else {
        alert('La función de compartir no está disponible en este navegador. Copia el enlace manualmente.');
    }
}
</script> 
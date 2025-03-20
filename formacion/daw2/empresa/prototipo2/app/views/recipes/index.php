<?php require APPROOT . '/views/layouts/main.php'; ?>

<div class="row mb-4">
    <div class="col-md-8">
        <h1><?php echo $data['title']; ?></h1>
        <p class="lead">Descubre deliciosas recetas compartidas por nuestra comunidad</p>
    </div>
    <div class="col-md-4 text-md-end">
        <?php if(isLoggedIn()) : ?>
            <a href="<?php echo url('recipes/add'); ?>" class="btn btn-primary">
                <i class="fas fa-plus me-1"></i> Añadir Receta
            </a>
        <?php endif; ?>
    </div>
</div>

<!-- Filtros -->
<div class="card mb-4">
    <div class="card-body">
        <form action="<?php echo url('recipes/search'); ?>" method="GET" class="row g-3">
            <div class="col-md-4">
                <label for="search" class="form-label">Buscar</label>
                <input type="text" class="form-control" id="search" name="search" placeholder="Buscar recetas...">
            </div>
            <div class="col-md-3">
                <label for="category" class="form-label">Categoría</label>
                <select class="form-select" id="category" name="category">
                    <option value="">Todas las categorías</option>
                    <?php 
                    $categoryModel = new Category();
                    $categories = $categoryModel->getAllCategories();
                    foreach($categories as $category) : 
                    ?>
                    <option value="<?php echo $category->id; ?>"><?php echo $category->name; ?></option>
                    <?php endforeach; ?>
                </select>
            </div>
            <div class="col-md-3">
                <label for="difficulty" class="form-label">Dificultad</label>
                <select class="form-select" id="difficulty" name="difficulty">
                    <option value="">Todas las dificultades</option>
                    <option value="Fácil">Fácil</option>
                    <option value="Media">Media</option>
                    <option value="Difícil">Difícil</option>
                </select>
            </div>
            <div class="col-md-2 d-flex align-items-end">
                <button type="submit" class="btn btn-primary w-100">Filtrar</button>
            </div>
        </form>
    </div>
</div>

<?php if(empty($data['recipes'])) : ?>
    <div class="alert alert-info">
        No se encontraron recetas. ¡Sé el primero en compartir una!
    </div>
<?php else : ?>
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <?php foreach($data['recipes'] as $recipe) : ?>
            <div class="col">
                <div class="card h-100">
                    <img src="<?php echo asset('img/recipes/' . $recipe->image); ?>" class="card-img-top" alt="<?php echo $recipe->title; ?>">
                    <div class="card-body">
                        <h2 class="card-title h5"><?php echo $recipe->title; ?></h2>
                        <p class="card-text">
                            <small class="text-muted">
                                <i class="fas fa-user me-1"></i> <?php echo $recipe->author; ?> |
                                <i class="fas fa-folder me-1"></i> <?php echo $recipe->category_name; ?> |
                                <i class="fas fa-calendar me-1"></i> <?php echo date('d/m/Y', strtotime($recipe->created_at)); ?>
                            </small>
                        </p>
                        <div class="mb-2">
                            <span class="badge bg-<?php 
                                echo $recipe->difficulty == 'Fácil' ? 'success' : 
                                    ($recipe->difficulty == 'Media' ? 'warning' : 'danger'); 
                            ?>">
                                <?php echo $recipe->difficulty; ?>
                            </span>
                            <?php if(!empty($recipe->preparation_time)) : ?>
                                <span class="badge bg-info text-dark">
                                    <i class="fas fa-clock me-1"></i> <?php echo $recipe->preparation_time; ?>
                                </span>
                            <?php endif; ?>
                        </div>
                        <div id="recipe-excerpt-<?php echo $recipe->id; ?>">
                            <?php echo substr(strip_tags($recipe->instructions), 0, 100) . '...'; ?>
                        </div>
                        <?php echo textToSpeechButton('recipe-excerpt-' . $recipe->id); ?>
                    </div>
                    <div class="card-footer bg-transparent">
                        <a href="<?php echo url('recipes/show/' . $recipe->id); ?>" class="btn btn-primary w-100">Ver Receta</a>
                    </div>
                </div>
            </div>
        <?php endforeach; ?>
    </div>
<?php endif; ?> 
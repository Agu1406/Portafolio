<div class="jumbotron bg-light p-5 rounded-3 mb-4">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-6">
                <div id="welcome-section">
                    <h1 class="display-4"><?php echo $data['title']; ?></h1>
                    <p class="lead"><?php echo $data['description']; ?></p>
                    <hr class="my-4">
                    <p>Comparte tus recetas favoritas, descubre nuevas ideas culinarias y conecta con otros amantes de la gastronomía.</p>
                </div>
                <?php echo textToSpeechButton('welcome-section', 'Escuchar bienvenida'); ?>
                <div class="d-grid gap-2 d-md-flex justify-content-md-start mt-3">
                    <?php if(!isLoggedIn()) : ?>
                        <a href="<?php echo url('users/register'); ?>" class="btn btn-primary btn-lg me-md-2" <?php echo role('button'); ?>>Registrarse</a>
                        <a href="<?php echo url('users/login'); ?>" class="btn btn-outline-secondary btn-lg" <?php echo role('button'); ?>>Iniciar Sesión</a>
                    <?php else : ?>
                        <a href="<?php echo url('recipes/add'); ?>" class="btn btn-primary btn-lg me-md-2" <?php echo role('button'); ?>>Añadir Receta</a>
                        <a href="<?php echo url('recipes'); ?>" class="btn btn-outline-secondary btn-lg" <?php echo role('button'); ?>>Explorar Recetas</a>
                    <?php endif; ?>
                </div>
            </div>
            <div class="col-md-6 d-none d-md-block">
                <div id="welcomeCarousel" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="https://erreqerremadrid.com/wp-content/uploads/2021/09/gastronomia-espanola.jpg" alt="Gastronomía Española" class="img-fluid rounded-3">
                        </div>
                        <div class="carousel-item">
                            <img src="https://www.cnature.es/wp-content/uploads/2020/08/gazpacho-gallego-.jpg" alt="Gazpacho" class="img-fluid rounded-3">
                        </div>
                        <div class="carousel-item">
                            <img src="https://www.hola.com/horizon/landscape/9e7aa7251e9f-stockfood00456742hirespaellavalencianawithgreenbeansspain.jpg?im=Resize=(960),type=downsize" alt="Paella" class="img-fluid rounded-3">
                        </div>
                        <div class="carousel-item">
                            <img src="https://s1.elespanol.com/2024/03/08/sevilla/838427159_240585428_1706x960.jpg" alt="Tortilla" class="img-fluid rounded-3">
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#welcomeCarousel" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Anterior</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#welcomeCarousel" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Siguiente</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container mb-5">
    <div id="benefits-section">
        <h2 class="text-center mb-4">¿Por qué unirse a Manos a la Olla?</h2>
        <div class="row g-4">
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-users fa-3x text-primary mb-3"></i>
                        <h3 class="card-title h5">Comunidad Inclusiva</h3>
                        <p class="card-text">Una red social gastronómica diseñada para ser accesible para todos, especialmente para personas de la tercera edad.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-book-open fa-3x text-primary mb-3"></i>
                        <h3 class="card-title h5">Recetas Ilimitadas</h3>
                        <p class="card-text">Accede a miles de recetas compartidas por usuarios de todo el mundo, desde platos tradicionales hasta creaciones innovadoras.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card h-100">
                    <div class="card-body text-center">
                        <i class="fas fa-universal-access fa-3x text-primary mb-3"></i>
                        <h3 class="card-title h5">Accesibilidad Total</h3>
                        <p class="card-text">Disfruta de funciones como lector de texto para personas con discapacidad visual y controles de tamaño de texto.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <?php echo textToSpeechButton('benefits-section', 'Escuchar beneficios'); ?>
</div>

<div class="container mb-5">
    <div id="popular-recipes-section">
        <h2 class="text-center mb-4">Recetas Populares</h2>
        <div class="row g-4">
            <?php 
            // Obtener recetas populares
            $recipeModel = new Recipe();
            $recipes = $recipeModel->getPopularRecipes(3);
            
            foreach($recipes as $recipe) : 
            ?>
            <div class="col-md-4">
                <div class="card h-100">
                    <img src="<?php echo asset('img/recipes/' . $recipe->image); ?>" class="card-img-top" alt="<?php echo $recipe->title; ?>">
                    <div class="card-body">
                        <h3 class="card-title h5"><?php echo $recipe->title; ?></h3>
                        <p class="card-text">
                            <small class="text-muted">
                                <i class="fas fa-user me-1"></i> <?php echo $recipe->author; ?> |
                                <i class="fas fa-folder me-1"></i> <?php echo $recipe->category_name; ?>
                            </small>
                        </p>
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
    </div>
    <?php echo textToSpeechButton('popular-recipes-section', 'Escuchar recetas populares'); ?>
    <div class="text-center mt-4">
        <a href="<?php echo url('recipes'); ?>" class="btn btn-outline-primary">Ver Todas las Recetas</a>
    </div>
</div>

<div class="container mb-5">
    <div class="row align-items-center">
        <div class="col-md-6">
            <h2>Únete a nuestra comunidad</h2>
            <p class="lead">Manos a la Olla es una red social gastronómica diferente, diseñada pensando en la accesibilidad y la experiencia del usuario.</p>
            <ul class="list-unstyled">
                <li><i class="fas fa-check text-success me-2"></i> Sin publicidad intrusiva</li>
                <li><i class="fas fa-check text-success me-2"></i> Interfaz sencilla y accesible</li>
                <li><i class="fas fa-check text-success me-2"></i> Funciones de accesibilidad avanzadas</li>
                <li><i class="fas fa-check text-success me-2"></i> Comunidad amigable y colaborativa</li>
            </ul>
            <?php if(!isLoggedIn()) : ?>
                <a href="<?php echo url('users/register'); ?>" class="btn btn-primary">Registrarse Ahora</a>
            <?php endif; ?>
        </div>
        <div class="col-md-6 d-none d-md-block">
            <img src="<?php echo asset('img/community.jpg'); ?>" alt="Comunidad de cocineros" class="img-fluid rounded-3">
        </div>
    </div>
</div>

<div class="container mb-5">
    <h2 class="text-center mb-4">Categorías Populares</h2>
    <div class="row g-4">
        <?php 
        // Obtener categorías populares
        $categoryModel = new Category();
        $categories = $categoryModel->getPopularCategories(6);
        
        foreach($categories as $category) : 
        ?>
        <div class="col-md-4 col-lg-2">
            <a href="<?php echo url('recipes/category/' . $category->id); ?>" class="text-decoration-none">
                <div class="card h-100 text-center">
                    <div class="card-body">
                        <h3 class="card-title h5"><?php echo $category->name; ?></h3>
                        <p class="card-text text-muted"><?php echo $category->recipe_count; ?> recetas</p>
                    </div>
                </div>
            </a>
        </div>
        <?php endforeach; ?>
    </div>
</div>

<div class="container">
    <div class="card bg-light">
        <div class="card-body">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h2>¿Tienes una receta para compartir?</h2>
                    <p class="mb-md-0">Comparte tus creaciones culinarias con nuestra comunidad y recibe comentarios de otros amantes de la gastronomía.</p>
                </div>
                <div class="col-md-4 text-md-end">
                    <?php if(isLoggedIn()) : ?>
                        <a href="<?php echo url('recipes/add'); ?>" class="btn btn-primary btn-lg">Añadir Receta</a>
                    <?php else : ?>
                        <a href="<?php echo url('users/login'); ?>" class="btn btn-primary btn-lg">Iniciar Sesión para Compartir</a>
                    <?php endif; ?>
                </div>
            </div>
        </div>
    </div>
</div> 
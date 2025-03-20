<div class="container py-5">
    <div class="row mb-5">
        <div class="col-md-8 mx-auto text-center">
            <h1 class="display-4 mb-4">Acerca de Manos a la Olla</h1>
            <p class="lead">Una comunidad donde los amantes de la cocina pueden compartir y descubrir recetas deliciosas.</p>
            <div class="d-flex justify-content-center mt-4">
                <a href="<?php echo url('recipes'); ?>" class="btn btn-primary me-2">Explorar recetas</a>
                <?php if(!isLoggedIn()) : ?>
                    <a href="<?php echo url('users/register'); ?>" class="btn btn-outline-primary">Unirse ahora</a>
                <?php endif; ?>
            </div>
        </div>
    </div>

    <div class="row mb-5">
        <div class="col-md-6">
            <img src="<?php echo asset('img/about-cooking.jpg'); ?>" alt="Cocinando juntos" class="img-fluid rounded shadow">
        </div>
        <div class="col-md-6">
            <h2 class="h3 mb-4">Nuestra misión</h2>
            <p>En Manos a la Olla, creemos que la cocina es una forma de arte que une a las personas. Nuestra misión es crear un espacio donde los cocineros de todos los niveles puedan compartir sus creaciones, aprender nuevas técnicas y descubrir sabores de todo el mundo.</p>
            <p>Queremos democratizar la gastronomía, haciendo que recetas de calidad sean accesibles para todos, independientemente de su experiencia en la cocina o sus necesidades dietéticas.</p>
            <h3 class="h5 mt-4">Nuestros valores</h3>
            <ul>
                <li><strong>Comunidad:</strong> Fomentamos un ambiente acogedor y colaborativo.</li>
                <li><strong>Accesibilidad:</strong> Nos esforzamos por hacer que la cocina sea accesible para todos.</li>
                <li><strong>Diversidad:</strong> Celebramos la riqueza de las tradiciones culinarias de todo el mundo.</li>
                <li><strong>Sostenibilidad:</strong> Promovemos prácticas de cocina respetuosas con el medio ambiente.</li>
            </ul>
        </div>
    </div>

    <div class="row mb-5">
        <div class="col-12 text-center mb-4">
            <h2 class="h3">¿Cómo funciona?</h2>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body text-center">
                    <div class="display-4 text-primary mb-3">
                        <i class="fas fa-user-plus"></i>
                    </div>
                    <h3 class="h5 card-title">1. Crea una cuenta</h3>
                    <p class="card-text">Regístrate gratis para unirte a nuestra comunidad de amantes de la cocina.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body text-center">
                    <div class="display-4 text-primary mb-3">
                        <i class="fas fa-utensils"></i>
                    </div>
                    <h3 class="h5 card-title">2. Comparte tus recetas</h3>
                    <p class="card-text">Sube tus creaciones culinarias favoritas con fotos, ingredientes e instrucciones detalladas.</p>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body text-center">
                    <div class="display-4 text-primary mb-3">
                        <i class="fas fa-comments"></i>
                    </div>
                    <h3 class="h5 card-title">3. Conecta con otros</h3>
                    <p class="card-text">Descubre recetas, deja comentarios y forma parte de una comunidad apasionada por la cocina.</p>
                </div>
            </div>
        </div>
    </div>

    <div class="row mb-5">
        <div class="col-md-6 order-md-2">
            <img src="<?php echo asset('img/about-team.jpg'); ?>" alt="Nuestro equipo" class="img-fluid rounded shadow">
        </div>
        <div class="col-md-6 order-md-1">
            <h2 class="h3 mb-4">Nuestro equipo</h2>
            <p>Somos un grupo diverso de amantes de la comida, desarrolladores y diseñadores unidos por nuestra pasión por la gastronomía. Trabajamos incansablemente para crear la mejor plataforma posible para nuestra comunidad.</p>
            <p>Nuestro equipo está comprometido con la mejora continua de Manos a la Olla, añadiendo nuevas funcionalidades y asegurando que la plataforma sea accesible para todos los usuarios.</p>
            <div id="team-description">
                <p>Creemos en el poder de la comida para unir a las personas y crear momentos inolvidables. Por eso, nos esforzamos por hacer que Manos a la Olla sea más que una simple colección de recetas: queremos que sea un lugar donde se formen conexiones significativas a través del amor compartido por la buena comida.</p>
            </div>
            <?php echo textToSpeechButton('team-description', 'Escuchar sobre nuestro equipo'); ?>
        </div>
    </div>

    <div class="row mb-5">
        <div class="col-12 text-center mb-4">
            <h2 class="h3">Estadísticas de la comunidad</h2>
        </div>
        <div class="col-md-3 mb-4 text-center">
            <div class="display-4 text-primary mb-2">
                <?php 
                // Número de usuarios (valor estático por ahora)
                echo '500';
                ?>+
            </div>
            <p class="text-muted">Usuarios registrados</p>
        </div>
        <div class="col-md-3 mb-4 text-center">
            <div class="display-4 text-primary mb-2">
                <?php 
                // Número de recetas (valor estático por ahora)
                echo '1200';
                ?>+
            </div>
            <p class="text-muted">Recetas compartidas</p>
        </div>
        <div class="col-md-3 mb-4 text-center">
            <div class="display-4 text-primary mb-2">
                <?php 
                // Número de comentarios (valor estático por ahora)
                echo '3500';
                ?>+
            </div>
            <p class="text-muted">Comentarios</p>
        </div>
        <div class="col-md-3 mb-4 text-center">
            <div class="display-4 text-primary mb-2">
                <?php 
                // Número de categorías (valor estático por ahora)
                echo '25';
                ?>+
            </div>
            <p class="text-muted">Categorías</p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8 mx-auto text-center">
            <h2 class="h3 mb-4">¿Tienes preguntas?</h2>
            <p>Si tienes alguna pregunta, sugerencia o comentario, no dudes en ponerte en contacto con nosotros. Estamos aquí para ayudarte.</p>
            <a href="<?php echo url('contact'); ?>" class="btn btn-primary mt-3">Contáctanos</a>
        </div>
    </div>
</div> 
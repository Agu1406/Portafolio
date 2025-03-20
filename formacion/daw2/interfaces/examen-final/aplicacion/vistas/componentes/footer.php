<!-- Footer -->
<footer class="mt-5 footer-container">
    <div class="container">
        <div class="row">
            <div class="col-md-4 mb-4">
                <h5><i class="fas fa-leaf"></i> NaturalShop</h5>
                <p>Tu tienda online de productos naturales, parafarmacia y cosméticos ecológicos.</p>
                <p>
                    <i class="fas fa-map-marker-alt me-2"></i> Calle Ejemplo, 123<br>
                    <i class="fas fa-phone me-2"></i> +34 600 123 456<br>
                    <i class="fas fa-envelope me-2"></i> info@naturalshop.com
                </p>
            </div>
            <div class="col-md-2 mb-4">
                <h5>Enlaces</h5>
                <ul class="list-unstyled">
                    <li><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/">Inicio</a></li>
                    <li><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos">Productos</a></li>
                    <li><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/categorias">Categorías</a></li>
                    <li><a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/carrito">Carrito</a></li>
                </ul>
            </div>
            <div class="col-md-2 mb-4">
                <h5>Categorías</h5>
                <ul class="list-unstyled">
                    <?php if (isset($categorias) && !empty($categorias)): ?>
                        <?php $contador = 0; ?>
                        <?php foreach ($categorias as $categoria): ?>
                            <?php if ($contador < 5): ?>
                                <li>
                                    <a href="<?php echo $GLOBALS['configuracion']['rutaBase']; ?>/publico/productos?categoria=<?php echo $categoria['id']; ?>">
                                        <?php echo htmlspecialchars($categoria['nombre']); ?>
                                    </a>
                                </li>
                                <?php $contador++; ?>
                            <?php endif; ?>
                        <?php endforeach; ?>
                    <?php endif; ?>
                </ul>
            </div>
            <div class="col-md-4 mb-4">
                <h5>Suscríbete a nuestro boletín</h5>
                <p>Recibe las últimas novedades y ofertas exclusivas.</p>
                <form class="mb-3">
                    <div class="input-group">
                        <input type="email" class="form-control" placeholder="Tu email" aria-label="Email" aria-describedby="button-addon2">
                        <button class="btn btn-primary" type="button" id="button-addon2">Suscribirse</button>
                    </div>
                </form>
                <h5>Síguenos</h5>
                <div class="social-icons">
                    <a href="#" class="me-2"><i class="fab fa-facebook-f"></i></a>
                    <a href="#" class="me-2"><i class="fab fa-twitter"></i></a>
                    <a href="#" class="me-2"><i class="fab fa-instagram"></i></a>
                    <a href="#"><i class="fab fa-youtube"></i></a>
                </div>
            </div>
        </div>
        <div class="footer-bottom text-center">
            <p>&copy; <?php echo date('Y'); ?> NaturalShop. Todos los derechos reservados.</p>
        </div>
    </div>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<!-- JavaScript personalizado -->
<script src="<?php echo $GLOBALS['configuracion']['rutaPublica']; ?>/js/main.js"></script> 
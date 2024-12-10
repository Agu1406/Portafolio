<!-- Se carga dinámicamente en el PHP donde se llame, si no, se agrega manualmente al HTML. -->
<header>
    <p style="font-size: large; font-weight: bolder;">TuMercado.com</p>
    <nav>
        <ul>
            <li><a href="formulario_login.php">Inicio</a></li>
            <li><a href="catalogo.php">Catalogo</a></li>
            <li><a href="carrito.php">Carrito</a></li>
            <?php if(isset($_SESSION['usuario'])): ?>
                <?php if(isset($_SESSION['rol']) && $_SESSION['rol'] === "1"): ?>
                    <li><a href="formulario_registro.php">Registrar Usuario</a></li>
                    <li><a href="formulario_producto.php">Registrar Producto</a></li>
                    <li><a href="formulario_borrar_usuario.php">Borrar Usuario</a></li>
                <?php endif; ?>
                <li><a href="../controladores/validar_login.php?accion=cerrar">Cerrar Sesión</a></li>
            <?php endif; ?>
        </ul>
    </nav>
</header>

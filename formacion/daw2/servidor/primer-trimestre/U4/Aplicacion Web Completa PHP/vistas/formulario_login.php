<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link rel="stylesheet" href="../archivos/estilos.css">
</head>
<body>
    <?php include_once "header.php"; ?>
    <main>
        <div class="contenedor-principal">
            <h1>Bienvenido a tumercado.com</h1>
            <div class="seccion">
                <!-- Div de la izquierda con H1 y párrafo -->
                <div class="izquierda">
                    <h1>Los mejores productos al mejor precio</h1>
                    <p>&emsp; Accede a tu cuenta para poder gestionar tus compras, tus pedidos
                        y acceder a nuestras ofertas y mucho más.</p>
                </div>
                <!-- Div de la derecha con el formulario -->
                <div class="derecha">
                    <form class="login-form" action="../controladores/validar_login.php" method="post">
                        <label for="usuario">Usuario</label>
                        <input type="text" name="usuario" id="usuario" placeholder="Ingresa tu usuario">
                        <label for="contrasena">Contraseña</label>
                        <input type="password" name="contrasena" id="contrasena" placeholder="Ingresa tu contraseña">
                        <input type="submit" value="Iniciar sesión">
                    </form>
                </div>
            </div>
        </div>
    </main>
    <?php include_once "footer.php"; ?>
</body>
</html>

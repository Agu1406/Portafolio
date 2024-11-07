<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="./styles/logout.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>BIENVENIDO<?php 
    session_start();
    $_SESSION["user"]?></h1>

    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"])?>" method="post">
        <input type="submit" value="Cerrar sesión">
    </form>
</body>
</html>

<?php 
//redirige al login
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    require "sesiones1_login.php";

    session_destroy();
    redirigir("");
}
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<style>
    body {
        background-color: paleturquoise;
    }
    h1 {
        text-align: center;
        font-size: 80px;
        color: darkgoldenrod;
    }
    p{
        text-align: center;
        font-size: 30px;
    }
    p.counter {

        color: cornflowerblue
    }
    div.counter {
        border: 1px black solid;
        border-radius: 20px;
        background-color: azure;
        margin-left: 46.5%;
        margin-right: 46.5%;
        padding: 10px;
    }
    div.container {
        display: flex;
        justify-content: center;
    }
    input[type="submit"] {
            margin-top: 10px;       
            padding: 10px 20px;        
            border: none;              
            border-radius: 5px;        
            cursor: pointer;           
            font-size: 16px;           
            transition: background-color 0.3s;
            width: 183px;
            font-weight: bold;
        }
    input[type="submit"]:hover {
        background-color: gray;
        color: white;
    }
</style>
<body>
    <h1>Contador de visitas</h1>
    <p>Veces que se ha abierto esta página</p>
    <div class="counter">
        <p class="counter">
            <?php
            if (isset($_POST["restart"])) { //si el botón se ha pulsado borra la cookie
                $contador = 0;
                setcookie("contadorSesion", 0, time() - 3600, "./", "", false, false);
            } else {
                if (!isset($_COOKIE["contadorSesion"])) { //si la cookie no existe la inicializa
                    $contador = 1;
                    setcookie("contadorSesion", $contador, time() + 3600, "./", "", false, false);
                } else { //si la cookie existe incrementa su valor en 1
                    $contador = $_COOKIE["contadorSesion"] + 1;
                    setcookie("contadorSesion", $contador, time() + 3600, "./", "", false, false);
                }
            }
            echo $contador;
            ?>
        </p>
    </div>
    <p>¿Reiniciamos?</p>
    <div class="container">
        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="post">
            <input type="submit" value="Reiniciar" name="restart">
        </form>
    </div>

</body>
</html>
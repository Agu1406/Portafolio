<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }

        fieldset {
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 20px;
        }


        label {
            display: inline-block;
            margin-bottom: 10px;
        }
        .label-titulo{
            text-align: center;
            font-size: 1.5em;
            font-weight: bold;
            margin-bottom: 10px;
            display: block;
        }
        
    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de login</title>
</head>
<body>
    <?php
    if($_SERVER["REQUEST_METHOD"] == "POST"){
        if($_POST['usuario']=="Carlos" and $_POST['pw']=="1234"){
            header("Location: bienvenido.html");
        }else{
            $err = true;
        }
    }
    if(isset($err)){
        echo "<p>Revise su usuario y contraseña</p>";
    }
    ?>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
        <fieldset> 
            <label for="titulo" class="label-titulo">Formulario básico</label><br><br>

            <!-- Entrada de texto -->
            <label for="usuario">Usuario: </label>
            <input type="text" name="usuario" value=" <?php if(isset($usuario)) echo $usuario; ?>"><br><br>

            <!-- Entrada de contraseña -->
            <label for="pw">Contraseña: </label>
            <input name="pw" type="password"><br><br>

            <!-- Entrada de sumbit -->
            <input type="submit">
        </fieldset>
    </form>
</body>
</html>


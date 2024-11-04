<!DOCTYPE html>
<!--Aitor Pascual Jiménez-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>

        h1 {
            font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
            text-align: center;
            color: aquamarine;
            letter-spacing: 5px;
        }
        body {
            background: url(https://img.freepik.com/vector-gratis/fondo-conexion-red-degradado_23-2148881320.jpg);
            background-repeat: no-repeat;
            background-size: cover;
        }
        div#container {
            border: 2px solid;
            border-radius: 40px;
            display: flex;
            justify-content: center;
            padding: 5%;
            padding-bottom: 8%;
            margin-left: 25%;
            margin-right: 25%;
            background: rgba(0, 0, 0, 0.2);
            text-align: center;
            margin-bottom: 20px;
        }
        div#error {
            justify-content: center;
            text-align: center;
            margin-top: 10px;
        }

        div label {
            font-size: 20px;
            font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
            display: inline-block;
            margin: 10px;
            color: aquamarine;
        }
        div input[type="text"], div input[type="password"] {
            outline: none;
            padding: 10px;
            border-radius: 10px;
            background-color: rgba(0, 100, 0, 0.2);
            border-color: skyblue;
            transition: all 0.3s;
            color: aqua;
        }
        div input[type="text"]:hover, div input[type="text"]:focus, div input[type="password"]:hover, div input[type="password"]:focus {
            border-color: blueviolet;
        }
        h3 {
            text-align: center;
            text-decoration: underline;
            color: gray;
        }

        input[type="submit"] {
            margin-top: 50px;
            background-color: aqua;
            color: gray;             
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
            background-color: blueviolet;
            color: aqua;
        }


    </style>
</head>
<body>
    <h1>LOGIN</h1>
    <div id="container">
        <form action="Aitor_validar.php" method="post">
            <label for="user">usuario</label><br>
            <input type="text" name="user" id="user" placeholder="" required><br>
            <label for="pass">contraseña</label><br>
            <input type="password" name="pass" id="pass" value="" required><br>
            <input type="submit" value="Login">

            <div id="error">
                <h2 style="color: red;">
                    <?php 
                        if (isset($err)) {
                            echo"El usuario o la contraseña no son correctos";
                        }
                    ?>
                </h2>
            </div>
            
        </form>
    </div>

    <h3>Aitor Pascual Jiménez</h3>
    <h3>Ningún derecho reservado</h3>
</body>
</html>
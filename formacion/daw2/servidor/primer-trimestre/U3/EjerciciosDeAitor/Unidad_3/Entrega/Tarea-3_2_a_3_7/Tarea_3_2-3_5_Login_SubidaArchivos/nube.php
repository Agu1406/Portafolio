<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Audiowide&display=swap" rel="stylesheet">
</head>
<style>
    html, body {
        padding: 0;
        margin: 0;
        overflow: hidden;
    }

    /* plantilla de: https://bg.ibelick.com/ convertido de tailwind a css puro */
    .background-gradient {
        position: absolute;
        inset: 0;
        z-index: -10;
        height: 100%;
        width: 100%;
        display: flex;
        align-items: center;
        padding: 24px 5px;
        background: radial-gradient(125% 125% at 50% 10%, #000 40%, #63e 100%);
    }

    .audiowide-regular {
        font-family: "Audiowide", sans-serif;
        font-weight: 400;
        font-style: normal;
    }

    h1 {
        color: white;
        font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
        text-align: center;
        margin-top: 10%;
        font-size: 400%;
    }
    div#form-container {
        text-align: center;
        display: flex;
        justify-content: center;
    }
    input[type="file"] {
        padding: 10px;
        color: silver;
    }
    div.container {
        padding: 10px;
        border: gray 1px ;
        padding-top: 10px;
        padding-bottom: 10px;
        padding-left: 40px;
        padding-right: 40px;
        border-radius: 50px;
        background-color: rgba(255, 255, 255, 0.1);
    }

    input[type="submit"] {
            margin-top: 50px;
            background-color: darkgray;
            color: black;             
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
        color: white;
    }

    p {
        display: block;
        text-align: center;
        font-size: 20px;
        color: red;
    }



</style>
<body>
    <div class="background-gradient">
    </div>
    <h1 class="audiowide-regular" id="titulo">sube tus archivos</h1>

    <div id="form-container">
        <form action="Aitor_upload.php" enctype="multipart/form-data" method="post">
            <div class="container">
                <input type="file" name="archivo" id="arch">
            </div><br>
            <input type="submit" value="subir">
            <p>
                <?php
                    if (isset($error)) {
                        echo "Ha habido un error";
                    }
                ?>
            </p>
        </form>
    </div>

</body>
</html>


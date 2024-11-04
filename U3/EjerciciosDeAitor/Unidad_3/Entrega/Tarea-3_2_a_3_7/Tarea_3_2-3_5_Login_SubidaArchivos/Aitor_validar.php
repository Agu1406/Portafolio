<?php
$err = false;
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if ($_POST['user'] === "aitor" && $_POST['pass'] === "hola123")
        header("Location: nube.php");
    else {
        $err = true;
        include 'Aitor_Login.php';
    }
}
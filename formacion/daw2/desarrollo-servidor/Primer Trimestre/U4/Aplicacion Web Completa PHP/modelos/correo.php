<?php
/*
 * Alias del espacio de nombres de PHPMailer
 * La directiva "use" al principio del script crea un alias para las clases
 * PHPMailer y PHPException
 */ 
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;
use PHPMailer\PHPMailer\SMTP;

/*
* Incluir el fichero autoload.php de Composer que 
* se encarga de incluir las librerías PHPMailer
*/
require "c:/xampp/composer/vendor/autoload.php";

class Correo {
    public static function enviarEmail($productos){
        $correo = new PHPMailer(TRUE);    
    
        try{
        //Configuración del servidor SMTP
        $correo -> isSMTP();
        $correo -> Host = 'smtp.gmail.com';             //Servidor SMTP
        $correo -> SMTPAuth = true;                     //Habilitar autenticación SMTP
        $correo -> Username = 'dawiesventura@gmail.com'; //Correo del emisor
        $correo -> Password = 'nnli oyoa game qmoc';     //Contraseña del emisor
        $correo -> SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS; //Cifrado TLS
        $correo -> Port = 587;                          //Puerto SMTP 
    
        //Configuración del remitente y destinatario
        $emailComprador = $_SESSION['usuario'];//Obtener correo del usuario
        $correo -> setFrom('dawiesventura@gmail.com', 'Grafo'); //Correo del remitente
        $correo -> addAddress($emailComprador);  //Correo del destinatario
    
        //Contenido del correo
        $correo -> isHTML(true); //Habilitar HTML en el correo
        $correo -> Subject = 'Detalles de tu compra en TuMercado.com';
        $correo -> Body = "
        <h1>¡Gracias por tu compra!</h1>
        <p>Estos son los detalles de tu pedido:</p>
        <ul>
        ";
    
        //Añadir detalles del pedido
        foreach ($productos as $producto) {
        $correo -> Body .= "
            <li>
                <strong>Producto:</strong> {$producto['codigo_producto']}<br>
                <strong>Cantidad:</strong> {$producto['cantidad_producto']}<br>
                <strong>Precio:</strong> " . number_format($producto['precio_producto'], 2) . " €
            </li>
        ";
        }
    
        $correo -> Body .= "
        </ul>
        <p>Gracias por confiar en nosotros.</p>
        ";
        
        //Enviar el correo
        $correo-> send();
        echo "Correo enviado correctamente al comprador.";
    } catch (Exception $e) {
        echo "No se pudo enviar el correo. Error: {$correo -> ErrorInfo}";
    }
    }
}

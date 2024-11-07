1 <?php 
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
 	require 'c:/xampp/composer/vendor/phpmailer/phpmailer/src/Exception.php';
 	require 'c:/xampp/composer/vendor/phpmailer/phpmailer/src/PHPMailer.php';
 	require 'c:/xampp/composer/vendor/phpmailer/phpmailer/src/SMTP.php';
 	require "c:/xampp/composer/vendor/autoload.php";
 	$mail = new PHPMailer(TRUE);
 	$mail->IsSMTP();
	
 	// cambiar a 0 para no ver mensajes de error
 	$mail->SMTPDebug  = 2; 							
 	$mail->SMTPAuth   = true;
 	$mail->SMTPSecure = "tls"; //ssl                
	$mail->Host       = "smtp.gmail.com";    
	$mail->Port       = 587; //465                
	// introducir usuario de google
	$mail->Username   = "dawiesventura@gmail.com"; 
	// introducir clave
	$mail->Password   = "nnli oyoa game qmoc";   	
	$mail->SetFrom('dawiesventura@gmail.com', 'Mercedes Profe');
	// asunto
	$mail->Subject    = "Mensaje de prueba";
	// cuerpo
	$mail->MsgHTML('Prueba Tarea 3_9');
	// adjuntos
	//$mail->addAttachment("empleado.xsd");
	$mail->addAttachment("error.html");
	// destinatario
	$address = "tudireccion@gmail.com";
	$mail->AddAddress($address, "Prueba");
	//$mail->IsHTML(true);
	// enviar
	$resul = $mail->Send();
	if(!$resul) {
	  echo "Error" . $mail->ErrorInfo;
	} else {
	  echo "Enviado";
	}
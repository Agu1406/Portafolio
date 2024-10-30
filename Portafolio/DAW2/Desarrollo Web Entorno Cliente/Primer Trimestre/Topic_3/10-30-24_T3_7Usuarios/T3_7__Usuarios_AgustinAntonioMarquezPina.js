/**
 * Realizar un programa que reciba una cadena con el siguiente formato: “nombre:apellidos:dni:telefono:email:codigopostal”
 * 
 * Tras recibir la cadena, se debe desglosar la información. Crear una estructura de datos que recoja la información extraída
 * de la cadena de caracteres.
 * 
 * Validar si la letra del DNI introducido es correcta. (Para la validación usar aproximación sin utilizar Expresiones 
 * regulares). Añadir el campo de tipo boolean “dniValido” cuyo valor será true en caso de DNI correcto o false en caso
 * de que la letra del DNI del usuario no coincida.
 * 
 * Suponiendo un formato de email “direccion@servidor”, se debe crear un campo llamado “servidor” para asignarle el nombre
 * del servidor asociado (sin la @).
 * 
 * Generar un nombre de usuario con la primera letra del nombre, las tres últimas del apellido separadas por un carácter “_”.
 * 
 * Guardar el nombre de usuario generado en el campo “username” de la estructura creada, asociado a cada usuario.
 * 
 * Añadir un campo a la estructura llamado “país” y asignar como valor una cadena de caracteres que represente la bandera
 * de su país (elegir cualquiera). *Dependiendo del navegador, en algunos casos se puede visualizar un icono de la
 * bandera o en otros casos la combinación de los caracteres únicos para cada país. (Ejemplo: España se podría
 * visualizar: Bandera de España o 🇪🇸).
 * 
 * Mostrar los nombres de usuario y las banderas correspondientes a su país en la página del lado del cliente.
 * 
 * Por último, si la inicial del nombre y los dos primeros caracteres del apellido forman la palabra “oso” (sin distinguir
 * mayúsculas o minúsculas), la página lanzará una ventana de dialogo de tipo alert: “¡Cuidado, hay # osos cerca!”, 
 * indicando en # el número de usuarios con la cadena.
 * 
 * Unos usuarios de ejemplo serían:
 * 
 * Rocio:Osorno:01101315Y:612345649:rocio@outlook.com:28001
 * Sonia: Sastre:15340807Z:765430987:ssastre@gmail.com:43071
 * Olivia:Soriano:46216401X:654567098:osor01@educa.madrid.org:28660
 * Elena:Costaguta:70068005T:7123098654:ecostag@madrid.org:45230
 * Orestes:Sosa:52243028T::sosaorestes2000@yahoo.com:28001
 * 
 * */
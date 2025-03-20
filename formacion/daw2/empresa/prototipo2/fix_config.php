<?php
// Este archivo permite verificar y corregir la configuración de la base de datos

// Mostrar todos los errores
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

// Establecer un tiempo límite para evitar que la página se quede cargando indefinidamente
set_time_limit(10); // 10 segundos máximo

// Cargar configuración actual
$configFile = 'app/config/config.php';
$configExists = file_exists($configFile);
$configContent = $configExists ? file_get_contents($configFile) : '';

// Valores actuales
$currentHost = '';
$currentUser = '';
$currentPass = '';
$currentDbName = '';
$currentBaseUrl = '';

// Extraer valores actuales
if ($configExists) {
    preg_match("/define\('DB_HOST', '(.*)'\);/", $configContent, $hostMatches);
    preg_match("/define\('DB_USER', '(.*)'\);/", $configContent, $userMatches);
    preg_match("/define\('DB_PASS', '(.*)'\);/", $configContent, $passMatches);
    preg_match("/define\('DB_NAME', '(.*)'\);/", $configContent, $dbNameMatches);
    preg_match("/define\('BASE_URL', '(.*)'\);/", $configContent, $baseUrlMatches);
    
    $currentHost = isset($hostMatches[1]) ? $hostMatches[1] : '';
    $currentUser = isset($userMatches[1]) ? $userMatches[1] : '';
    $currentPass = isset($passMatches[1]) ? $passMatches[1] : '';
    $currentDbName = isset($dbNameMatches[1]) ? $dbNameMatches[1] : '';
    $currentBaseUrl = isset($baseUrlMatches[1]) ? $baseUrlMatches[1] : '';
}

// Procesar formulario
$message = '';
$messageType = '';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $newHost = $_POST['db_host'] ?? $currentHost;
    $newUser = $_POST['db_user'] ?? $currentUser;
    $newPass = $_POST['db_pass'] ?? $currentPass;
    $newDbName = $_POST['db_name'] ?? $currentDbName;
    $newBaseUrl = $_POST['base_url'] ?? $currentBaseUrl;
    
    // Probar conexión
    try {
        $conn = new PDO("mysql:host=$newHost", $newUser, $newPass, [
            PDO::ATTR_TIMEOUT => 3,
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
        ]);
        
        // Verificar si existe la base de datos
        $stmt = $conn->query("SHOW DATABASES LIKE '$newDbName'");
        $dbExists = $stmt->rowCount() > 0;
        
        if (!$dbExists) {
            // Crear base de datos si no existe
            if (isset($_POST['create_db']) && $_POST['create_db'] === 'yes') {
                $conn->exec("CREATE DATABASE `$newDbName` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci");
                $message .= "Base de datos '$newDbName' creada correctamente.<br>";
                $dbExists = true;
            } else {
                $message .= "La base de datos '$newDbName' no existe.<br>";
                $messageType = 'warning';
            }
        }
        
        // Actualizar configuración
        if ($dbExists && isset($_POST['update_config']) && $_POST['update_config'] === 'yes') {
            $newConfig = $configContent;
            $newConfig = preg_replace("/define\('DB_HOST', '(.*)'\);/", "define('DB_HOST', '$newHost');", $newConfig);
            $newConfig = preg_replace("/define\('DB_USER', '(.*)'\);/", "define('DB_USER', '$newUser');", $newConfig);
            $newConfig = preg_replace("/define\('DB_PASS', '(.*)'\);/", "define('DB_PASS', '$newPass');", $newConfig);
            $newConfig = preg_replace("/define\('DB_NAME', '(.*)'\);/", "define('DB_NAME', '$newDbName');", $newConfig);
            $newConfig = preg_replace("/define\('BASE_URL', '(.*)'\);/", "define('BASE_URL', '$newBaseUrl');", $newConfig);
            
            // Guardar configuración
            if (file_put_contents($configFile, $newConfig)) {
                $message .= "Configuración actualizada correctamente.<br>";
                $messageType = 'success';
            } else {
                $message .= "Error al guardar la configuración. Verifica los permisos de escritura.<br>";
                $messageType = 'error';
            }
        }
        
        // Importar estructura de base de datos
        if ($dbExists && isset($_POST['import_structure']) && $_POST['import_structure'] === 'yes') {
            // Conectar a la base de datos
            $dbConn = new PDO("mysql:host=$newHost;dbname=$newDbName", $newUser, $newPass, [
                PDO::ATTR_TIMEOUT => 3,
                PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
            ]);
            
            // Verificar si existe el archivo database.sql
            if (file_exists('database.sql')) {
                // Leer archivo SQL
                $sql = file_get_contents('database.sql');
                
                // Dividir en consultas individuales
                $queries = explode(';', $sql);
                
                // Ejecutar cada consulta
                $success = true;
                foreach ($queries as $query) {
                    $query = trim($query);
                    if (!empty($query)) {
                        try {
                            $dbConn->exec($query);
                        } catch (PDOException $e) {
                            $message .= "Error al ejecutar consulta: " . $e->getMessage() . "<br>";
                            $success = false;
                        }
                    }
                }
                
                if ($success) {
                    $message .= "Estructura de base de datos importada correctamente.<br>";
                    $messageType = 'success';
                } else {
                    $message .= "Hubo errores al importar la estructura de la base de datos.<br>";
                    $messageType = 'warning';
                }
            } else {
                $message .= "No se encontró el archivo database.sql<br>";
                $messageType = 'error';
            }
        }
    } catch (PDOException $e) {
        $message = "Error de conexión a MySQL: " . $e->getMessage();
        $messageType = 'error';
    }
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verificar y corregir configuración</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        h1, h2 {
            color: #333;
        }
        .success {
            color: green;
            font-weight: bold;
        }
        .warning {
            color: orange;
            font-weight: bold;
        }
        .error {
            color: red;
            font-weight: bold;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .checkbox-group {
            margin: 15px 0;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Verificar y corregir configuración</h1>
    
    <?php if (!empty($message)): ?>
        <div class="<?php echo $messageType; ?>">
            <?php echo $message; ?>
        </div>
    <?php endif; ?>
    
    <h2>Configuración actual</h2>
    <?php if ($configExists): ?>
        <p>Archivo de configuración: <span class="success">✓ Existe</span></p>
        <ul>
            <li>Host: <?php echo htmlspecialchars($currentHost); ?></li>
            <li>Usuario: <?php echo htmlspecialchars($currentUser); ?></li>
            <li>Contraseña: <?php echo !empty($currentPass) ? '********' : '<em>vacía</em>'; ?></li>
            <li>Base de datos: <?php echo htmlspecialchars($currentDbName); ?></li>
            <li>URL base: <?php echo htmlspecialchars($currentBaseUrl); ?></li>
        </ul>
    <?php else: ?>
        <p>Archivo de configuración: <span class="error">✗ No existe</span></p>
    <?php endif; ?>
    
    <h2>Actualizar configuración</h2>
    <form method="post" action="">
        <div class="form-group">
            <label for="db_host">Host de MySQL:</label>
            <input type="text" id="db_host" name="db_host" value="<?php echo htmlspecialchars($currentHost); ?>" placeholder="localhost o 127.0.0.1">
        </div>
        
        <div class="form-group">
            <label for="db_user">Usuario de MySQL:</label>
            <input type="text" id="db_user" name="db_user" value="<?php echo htmlspecialchars($currentUser); ?>" placeholder="root">
        </div>
        
        <div class="form-group">
            <label for="db_pass">Contraseña de MySQL:</label>
            <input type="password" id="db_pass" name="db_pass" value="<?php echo htmlspecialchars($currentPass); ?>" placeholder="Dejar en blanco si no hay contraseña">
        </div>
        
        <div class="form-group">
            <label for="db_name">Nombre de la base de datos:</label>
            <input type="text" id="db_name" name="db_name" value="<?php echo htmlspecialchars($currentDbName); ?>" placeholder="manosalaolla">
        </div>
        
        <div class="form-group">
            <label for="base_url">URL base:</label>
            <input type="text" id="base_url" name="base_url" value="<?php echo htmlspecialchars($currentBaseUrl); ?>" placeholder="http://localhost/formacion/daw2/empresa/prototipo2">
        </div>
        
        <div class="checkbox-group">
            <label>
                <input type="checkbox" name="create_db" value="yes"> Crear base de datos si no existe
            </label>
        </div>
        
        <div class="checkbox-group">
            <label>
                <input type="checkbox" name="import_structure" value="yes"> Importar estructura de base de datos (database.sql)
            </label>
        </div>
        
        <div class="checkbox-group">
            <label>
                <input type="checkbox" name="update_config" value="yes"> Actualizar archivo de configuración
            </label>
        </div>
        
        <button type="submit">Verificar y actualizar</button>
    </form>
    
    <p><a href="test.html">Volver a la página de prueba</a></p>
</body>
</html> 
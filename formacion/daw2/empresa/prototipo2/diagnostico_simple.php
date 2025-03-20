<?php
// Mostrar todos los errores
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

echo "<h1>Diagnóstico Simple - Manos a la Olla</h1>";

// Verificar PHP
echo "<h2>Verificación de PHP</h2>";
echo "<p>PHP está funcionando correctamente. Versión: " . phpversion() . "</p>";

// Verificar información del servidor
echo "<h2>Información del servidor</h2>";
echo "<ul>";
echo "<li>Sistema operativo: " . PHP_OS . "</li>";
echo "<li>Servidor web: " . $_SERVER['SERVER_SOFTWARE'] . "</li>";
echo "<li>Document Root: " . $_SERVER['DOCUMENT_ROOT'] . "</li>";
echo "<li>Script Path: " . $_SERVER['SCRIPT_FILENAME'] . "</li>";
echo "</ul>";

// Verificar módulos de Apache
echo "<h2>Módulos de Apache</h2>";
if (function_exists('apache_get_modules')) {
    $modules = apache_get_modules();
    echo "<p>mod_rewrite: " . (in_array('mod_rewrite', $modules) ? '<span style="color:green">✓ Habilitado</span>' : '<span style="color:red">✗ No habilitado</span>') . "</p>";
} else {
    echo "<p>No se puede verificar los módulos de Apache</p>";
}

// Verificar archivos críticos
echo "<h2>Archivos críticos</h2>";
$files = [
    '.htaccess',
    'public/.htaccess',
    'app/config/config.php',
    'app/bootstrap.php',
    'public/index.php'
];

foreach ($files as $file) {
    if (file_exists($file)) {
        echo "<p style='color:green'>✓ $file existe</p>";
        if (strpos($file, '.htaccess') !== false) {
            echo "<p>Contenido de $file:</p>";
            echo "<pre>" . htmlspecialchars(file_get_contents($file)) . "</pre>";
        }
    } else {
        echo "<p style='color:red'>✗ $file no existe</p>";
    }
}

// Verificar MySQL
echo "<h2>Verificación de MySQL</h2>";
try {
    $conn = new PDO('mysql:host=localhost', 'root', '', [
        PDO::ATTR_TIMEOUT => 3,
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
    ]);
    echo "<p style='color:green'>✓ Conexión a MySQL exitosa</p>";
    
    // Listar bases de datos
    echo "<h3>Bases de datos disponibles:</h3>";
    echo "<ul>";
    $stmt = $conn->query("SHOW DATABASES");
    $databases = $stmt->fetchAll(PDO::FETCH_COLUMN);
    foreach ($databases as $database) {
        if ($database == 'manosalaolla') {
            echo "<li style='color:green'><strong>$database</strong> (Esta es la base de datos de la aplicación)</li>";
        } else {
            echo "<li>$database</li>";
        }
    }
    echo "</ul>";
    
    // Verificar si existe la base de datos manosalaolla
    $manosalaollaExists = in_array('manosalaolla', $databases);
    if ($manosalaollaExists) {
        echo "<p style='color:green'>✓ La base de datos 'manosalaolla' existe</p>";
    } else {
        echo "<p style='color:red'>✗ La base de datos 'manosalaolla' NO existe</p>";
        echo "<p>Necesitas crear la base de datos. Puedes usar el siguiente comando SQL:</p>";
        echo "<pre>CREATE DATABASE `manosalaolla` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;</pre>";
    }
} catch (PDOException $e) {
    echo "<p style='color:red'>✗ Error de conexión a MySQL: " . $e->getMessage() . "</p>";
    echo "<p>Posibles causas:</p>";
    echo "<ul>";
    echo "<li>El servidor MySQL no está en ejecución</li>";
    echo "<li>Las credenciales son incorrectas</li>";
    echo "<li>El host no es accesible</li>";
    echo "</ul>";
}

// Sugerencias
echo "<h2>Sugerencias</h2>";
echo "<ol>";
echo "<li>Verifica que el servidor MySQL esté en ejecución</li>";
echo "<li>Si la base de datos 'manosalaolla' no existe, créala</li>";
echo "<li>Asegúrate de que el usuario 'root' tenga acceso a la base de datos</li>";
echo "<li>Verifica que el módulo mod_rewrite de Apache esté habilitado</li>";
echo "<li>Intenta acceder directamente a <a href='public/index.php'>public/index.php</a></li>";
echo "</ol>";

// Verificar si config.php existe y es legible
echo "<h2>Verificación de archivos de configuración</h2>";
$configFile = 'app/config/config.php';
if (file_exists($configFile)) {
    echo "<p style='color:green'>✓ config.php existe</p>";
    $configContent = file_get_contents($configFile);
    if ($configContent !== false) {
        echo "<p style='color:green'>✓ config.php es legible</p>";
        
        // Extraer y mostrar la configuración (sin mostrar contraseñas)
        preg_match("/define\('DB_HOST', '(.*)'\);/", $configContent, $hostMatches);
        preg_match("/define\('DB_USER', '(.*)'\);/", $configContent, $userMatches);
        preg_match("/define\('DB_NAME', '(.*)'\);/", $configContent, $dbNameMatches);
        preg_match("/define\('BASE_URL', '(.*)'\);/", $configContent, $baseUrlMatches);
        
        echo "<h3>Configuración actual:</h3>";
        echo "<ul>";
        echo "<li>DB_HOST: " . (isset($hostMatches[1]) ? $hostMatches[1] : 'No definido') . "</li>";
        echo "<li>DB_USER: " . (isset($userMatches[1]) ? $userMatches[1] : 'No definido') . "</li>";
        echo "<li>DB_NAME: " . (isset($dbNameMatches[1]) ? $dbNameMatches[1] : 'No definido') . "</li>";
        echo "<li>BASE_URL: " . (isset($baseUrlMatches[1]) ? $baseUrlMatches[1] : 'No definido') . "</li>";
        echo "</ul>";
    } else {
        echo "<p style='color:red'>✗ No se puede leer config.php</p>";
    }
} else {
    echo "<p style='color:red'>✗ config.php no existe</p>";
}

// Enlace de regreso
echo "<p><a href='test.html'>Volver a la página de prueba</a></p>"; 
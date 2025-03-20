<?php
/**
 * Diagnóstico rápido para problemas de carga
 */

// Mostrar todos los errores
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

echo "<h1>Diagnóstico Rápido</h1>";

// Información de PHP
echo "<h2>Información de PHP</h2>";
echo "<p>Versión de PHP: " . phpversion() . "</p>";
echo "<p>Extensiones cargadas: </p><ul>";
$extensions = get_loaded_extensions();
foreach ($extensions as $extension) {
    if ($extension == 'pdo_mysql' || $extension == 'mysqli' || $extension == 'mysql') {
        echo "<li><strong>$extension</strong></li>";
    }
}
echo "</ul>";

// Verificar conexión a MySQL
echo "<h2>Prueba de conexión a MySQL</h2>";
try {
    // Cargar configuración
    require_once 'app/config/config.php';
    
    echo "<p>Intentando conectar a MySQL en " . DB_HOST . " con usuario " . DB_USER . "...</p>";
    
    // Intentar conexión sin base de datos
    $conn = new PDO('mysql:host=' . DB_HOST, DB_USER, DB_PASS, [
        PDO::ATTR_TIMEOUT => 3,
        PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
    ]);
    
    echo "<p style='color:green'>✓ Conexión a MySQL exitosa</p>";
    
    // Verificar si existe la base de datos
    $stmt = $conn->query("SHOW DATABASES LIKE '" . DB_NAME . "'");
    if ($stmt->rowCount() > 0) {
        echo "<p style='color:green'>✓ Base de datos '" . DB_NAME . "' existe</p>";
        
        // Intentar conexión con base de datos
        try {
            $dbConn = new PDO('mysql:host=' . DB_HOST . ';dbname=' . DB_NAME, DB_USER, DB_PASS, [
                PDO::ATTR_TIMEOUT => 3,
                PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
            ]);
            
            echo "<p style='color:green'>✓ Conexión a la base de datos exitosa</p>";
            
            // Verificar tablas
            $tables = ['users', 'categories', 'recipes', 'comments', 'favorites'];
            echo "<h3>Verificación de tablas:</h3><ul>";
            
            foreach ($tables as $table) {
                try {
                    $tableStmt = $dbConn->query("SHOW TABLES LIKE '$table'");
                    if ($tableStmt->rowCount() > 0) {
                        $count = $dbConn->query("SELECT COUNT(*) FROM $table")->fetchColumn();
                        echo "<li style='color:green'>✓ Tabla '$table' existe ($count registros)</li>";
                    } else {
                        echo "<li style='color:red'>✗ Tabla '$table' no existe</li>";
                    }
                } catch (PDOException $e) {
                    echo "<li style='color:red'>✗ Error al verificar tabla '$table': " . $e->getMessage() . "</li>";
                }
            }
            
            echo "</ul>";
            
        } catch (PDOException $e) {
            echo "<p style='color:red'>✗ Error al conectar a la base de datos: " . $e->getMessage() . "</p>";
        }
    } else {
        echo "<p style='color:red'>✗ Base de datos '" . DB_NAME . "' no existe</p>";
        echo "<p>Necesitas crear la base de datos. Puedes usar el siguiente comando SQL:</p>";
        echo "<pre>CREATE DATABASE `" . DB_NAME . "` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;</pre>";
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

// Verificar rutas y archivos
echo "<h2>Verificación de rutas y archivos</h2>";
echo "<p>Document Root: " . $_SERVER['DOCUMENT_ROOT'] . "</p>";
echo "<p>Script Filename: " . $_SERVER['SCRIPT_FILENAME'] . "</p>";
echo "<p>Request URI: " . $_SERVER['REQUEST_URI'] . "</p>";

// Verificar archivos críticos
$criticalFiles = [
    'index.php',
    'public/index.php',
    'app/bootstrap.php',
    'app/config/config.php',
    'app/config/Database.php'
];

echo "<h3>Archivos críticos:</h3><ul>";
foreach ($criticalFiles as $file) {
    if (file_exists($file)) {
        echo "<li style='color:green'>✓ $file existe</li>";
    } else {
        echo "<li style='color:red'>✗ $file no existe</li>";
    }
}
echo "</ul>";

// Verificar permisos de .htaccess
echo "<h3>Verificación de .htaccess:</h3>";
if (file_exists('.htaccess')) {
    echo "<p style='color:green'>✓ .htaccess existe en la raíz</p>";
    echo "<p>Contenido:</p>";
    echo "<pre>" . htmlspecialchars(file_get_contents('.htaccess')) . "</pre>";
} else {
    echo "<p style='color:red'>✗ .htaccess no existe en la raíz</p>";
}

if (file_exists('public/.htaccess')) {
    echo "<p style='color:green'>✓ .htaccess existe en public/</p>";
    echo "<p>Contenido:</p>";
    echo "<pre>" . htmlspecialchars(file_get_contents('public/.htaccess')) . "</pre>";
} else {
    echo "<p style='color:red'>✗ .htaccess no existe en public/</p>";
}

// Sugerencias
echo "<h2>Sugerencias</h2>";
echo "<ol>";
echo "<li>Verifica que el servidor MySQL esté en ejecución</li>";
echo "<li>Asegúrate de que la base de datos 'manosalaolla' exista</li>";
echo "<li>Comprueba que el módulo mod_rewrite de Apache esté habilitado</li>";
echo "<li>Intenta acceder directamente a <a href='public/index.php'>public/index.php</a></li>";
echo "<li>Verifica que los archivos .htaccess tengan los permisos correctos</li>";
echo "</ol>"; 
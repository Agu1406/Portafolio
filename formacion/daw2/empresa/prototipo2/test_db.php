<?php
/**
 * Script de diagnóstico para la base de datos
 */

// Cargar configuración
require_once 'app/config/config.php';

// Mostrar errores
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

echo "<h1>Prueba de Conexión a Base de Datos</h1>";

// Configuración de la base de datos
$host = '127.0.0.1';  // o 'localhost'
$dbname = 'manosalaolla';
$user = 'root';
$pass = '';

echo "<h2>Configuración actual:</h2>";
echo "<ul>";
echo "<li>Host: $host</li>";
echo "<li>Base de datos: $dbname</li>";
echo "<li>Usuario: $user</li>";
echo "<li>Contraseña: " . (empty($pass) ? "vacía" : "configurada") . "</li>";
echo "</ul>";

try {
    // Primero intentar conectar solo al servidor MySQL
    echo "<h2>Paso 1: Conectar al servidor MySQL</h2>";
    $conn = new PDO("mysql:host=$host", $user, $pass);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "<p style='color:green'>✓ Conexión al servidor MySQL exitosa</p>";
    
    // Verificar si la base de datos existe
    echo "<h2>Paso 2: Verificar base de datos</h2>";
    $stmt = $conn->query("SHOW DATABASES");
    $databases = $stmt->fetchAll(PDO::FETCH_COLUMN);
    
    if (in_array($dbname, $databases)) {
        echo "<p style='color:green'>✓ La base de datos '$dbname' existe</p>";
        
        // Intentar conectar a la base de datos específica
        echo "<h2>Paso 3: Conectar a la base de datos</h2>";
        $dbConn = new PDO("mysql:host=$host;dbname=$dbname", $user, $pass);
        $dbConn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        echo "<p style='color:green'>✓ Conexión a la base de datos exitosa</p>";
        
        // Verificar tablas
        echo "<h2>Paso 4: Verificar tablas</h2>";
        $tables = $dbConn->query("SHOW TABLES")->fetchAll(PDO::FETCH_COLUMN);
        
        if (empty($tables)) {
            echo "<p style='color:orange'>⚠ La base de datos está vacía. No hay tablas.</p>";
            echo "<p>Necesitas importar la estructura de la base de datos.</p>";
        } else {
            echo "<p>Tablas encontradas:</p><ul>";
            foreach ($tables as $table) {
                $count = $dbConn->query("SELECT COUNT(*) FROM `$table`")->fetchColumn();
                echo "<li>$table ($count registros)</li>";
            }
            echo "</ul>";
        }
    } else {
        echo "<p style='color:red'>✗ La base de datos '$dbname' no existe</p>";
        echo "<p>Necesitas crear la base de datos. Puedes usar este comando SQL:</p>";
        echo "<pre>CREATE DATABASE `$dbname` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;</pre>";
    }
    
} catch(PDOException $e) {
    echo "<p style='color:red'>✗ Error de conexión: " . $e->getMessage() . "</p>";
    
    // Sugerencias basadas en el error
    echo "<h2>Sugerencias:</h2>";
    echo "<ul>";
    if (strpos($e->getMessage(), "Access denied") !== false) {
        echo "<li>Verifica que el usuario y contraseña sean correctos</li>";
        echo "<li>Asegúrate de que el usuario tenga los permisos necesarios</li>";
    }
    if (strpos($e->getMessage(), "Unknown database") !== false) {
        echo "<li>La base de datos no existe, necesitas crearla</li>";
    }
    if (strpos($e->getMessage(), "Connection refused") !== false) {
        echo "<li>Verifica que el servidor MySQL esté en ejecución</li>";
        echo "<li>Comprueba que el host sea correcto</li>";
    }
    echo "</ul>";
}

// Enlace para volver
echo "<p><a href='test_basico.html'>Volver a la prueba básica</a></p>";

// Función para verificar la conexión a MySQL
function checkMySQLConnection($host, $user, $pass) {
    try {
        $connection = @new PDO("mysql:host=$host", $user, $pass);
        $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return [true, "Conexión exitosa al servidor MySQL"];
    } catch (PDOException $e) {
        return [false, "Error de conexión: " . $e->getMessage()];
    }
}

// Función para verificar la existencia de la base de datos
function checkDatabaseExists($host, $user, $pass, $dbname) {
    try {
        $connection = new PDO("mysql:host=$host", $user, $pass);
        $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        
        $stmt = $connection->query("SHOW DATABASES LIKE '$dbname'");
        if ($stmt->rowCount() > 0) {
            return [true, "La base de datos '$dbname' existe"];
        } else {
            return [false, "La base de datos '$dbname' no existe"];
        }
    } catch (PDOException $e) {
        return [false, "Error al verificar la base de datos: " . $e->getMessage()];
    }
}

// Función para verificar la conexión a la base de datos
function checkDatabaseConnection($host, $user, $pass, $dbname) {
    try {
        $connection = new PDO("mysql:host=$host;dbname=$dbname", $user, $pass);
        $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return [true, "Conexión exitosa a la base de datos '$dbname'"];
    } catch (PDOException $e) {
        return [false, "Error de conexión a la base de datos: " . $e->getMessage()];
    }
}

// Función para verificar la existencia de tablas
function checkTablesExist($host, $user, $pass, $dbname, $tables) {
    try {
        $connection = new PDO("mysql:host=$host;dbname=$dbname", $user, $pass);
        $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        
        $results = [];
        foreach ($tables as $table) {
            $stmt = $connection->query("SHOW TABLES LIKE '$table'");
            if ($stmt->rowCount() > 0) {
                $count = $connection->query("SELECT COUNT(*) FROM $table")->fetchColumn();
                $results[$table] = [true, "La tabla '$table' existe ($count registros)"];
            } else {
                $results[$table] = [false, "La tabla '$table' no existe"];
            }
        }
        
        return $results;
    } catch (PDOException $e) {
        return ["error" => "Error al verificar las tablas: " . $e->getMessage()];
    }
}

// Configuración de la base de datos
$host = DB_HOST;
$user = DB_USER;
$pass = DB_PASS;
$dbname = DB_NAME;

// Tablas a verificar
$tables = ['users', 'categories', 'recipes', 'comments', 'favorites'];

// Realizar verificaciones
$mysqlConnection = checkMySQLConnection($host, $user, $pass);
$databaseExists = $mysqlConnection[0] ? checkDatabaseExists($host, $user, $pass, $dbname) : [false, "No se pudo verificar la base de datos"];
$databaseConnection = $databaseExists[0] ? checkDatabaseConnection($host, $user, $pass, $dbname) : [false, "No se pudo conectar a la base de datos"];
$tablesExist = $databaseConnection[0] ? checkTablesExist($host, $user, $pass, $dbname, $tables) : ["error" => "No se pudieron verificar las tablas"];

// Mostrar resultados
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Diagnóstico de Base de Datos - <?php echo APP_NAME; ?></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .status-success { color: #28a745; }
        .status-error { color: #dc3545; }
    </style>
</head>
<body>
    <div class="container py-5">
        <h1 class="mb-4">Diagnóstico de Base de Datos - <?php echo APP_NAME; ?></h1>
        
        <div class="card mb-4">
            <div class="card-header">
                <h2 class="h5 mb-0">Configuración</h2>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span>Host</span>
                        <span><?php echo $host; ?></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span>Usuario</span>
                        <span><?php echo $user; ?></span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span>Base de datos</span>
                        <span><?php echo $dbname; ?></span>
                    </li>
                </ul>
            </div>
        </div>
        
        <div class="card mb-4">
            <div class="card-header">
                <h2 class="h5 mb-0">Resultados</h2>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <span>Conexión a MySQL</span>
                            <span class="<?php echo $mysqlConnection[0] ? 'status-success' : 'status-error'; ?>">
                                <?php echo $mysqlConnection[0] ? 'OK' : 'Error'; ?>
                            </span>
                        </div>
                        <small class="text-muted"><?php echo $mysqlConnection[1]; ?></small>
                    </li>
                    <li class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <span>Existencia de la base de datos</span>
                            <span class="<?php echo $databaseExists[0] ? 'status-success' : 'status-error'; ?>">
                                <?php echo $databaseExists[0] ? 'OK' : 'Error'; ?>
                            </span>
                        </div>
                        <small class="text-muted"><?php echo $databaseExists[1]; ?></small>
                    </li>
                    <li class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <span>Conexión a la base de datos</span>
                            <span class="<?php echo $databaseConnection[0] ? 'status-success' : 'status-error'; ?>">
                                <?php echo $databaseConnection[0] ? 'OK' : 'Error'; ?>
                            </span>
                        </div>
                        <small class="text-muted"><?php echo $databaseConnection[1]; ?></small>
                    </li>
                </ul>
            </div>
        </div>
        
        <?php if ($databaseConnection[0]): ?>
        <div class="card mb-4">
            <div class="card-header">
                <h2 class="h5 mb-0">Tablas</h2>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <?php foreach ($tablesExist as $table => $result): ?>
                        <?php if ($table !== 'error'): ?>
                            <li class="list-group-item">
                                <div class="d-flex justify-content-between align-items-center">
                                    <span>Tabla: <?php echo $table; ?></span>
                                    <span class="<?php echo $result[0] ? 'status-success' : 'status-error'; ?>">
                                        <?php echo $result[0] ? 'OK' : 'Error'; ?>
                                    </span>
                                </div>
                                <small class="text-muted"><?php echo $result[1]; ?></small>
                            </li>
                        <?php endif; ?>
                    <?php endforeach; ?>
                    
                    <?php if (isset($tablesExist['error'])): ?>
                        <li class="list-group-item">
                            <div class="alert alert-danger mb-0">
                                <?php echo $tablesExist['error']; ?>
                            </div>
                        </li>
                    <?php endif; ?>
                </ul>
            </div>
        </div>
        <?php endif; ?>
        
        <div class="card">
            <div class="card-header">
                <h2 class="h5 mb-0">Soluciones</h2>
            </div>
            <div class="card-body">
                <?php if (!$mysqlConnection[0]): ?>
                    <div class="alert alert-danger">
                        <h3 class="h6">Problema: No se puede conectar al servidor MySQL</h3>
                        <p>Posibles soluciones:</p>
                        <ol>
                            <li>Asegúrese de que el servidor MySQL esté en ejecución.</li>
                            <li>Verifique que las credenciales de acceso sean correctas.</li>
                            <li>Compruebe que el host sea accesible (pruebe con 'localhost' o '127.0.0.1').</li>
                        </ol>
                    </div>
                <?php elseif (!$databaseExists[0]): ?>
                    <div class="alert alert-danger">
                        <h3 class="h6">Problema: La base de datos no existe</h3>
                        <p>Posibles soluciones:</p>
                        <ol>
                            <li>Cree la base de datos manualmente usando phpMyAdmin u otra herramienta.</li>
                            <li>Importe el archivo database.sql para crear la estructura de la base de datos.</li>
                        </ol>
                        <p>Comando SQL para crear la base de datos:</p>
                        <pre><code>CREATE DATABASE `<?php echo $dbname; ?>` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;</code></pre>
                    </div>
                <?php elseif (!$databaseConnection[0]): ?>
                    <div class="alert alert-danger">
                        <h3 class="h6">Problema: No se puede conectar a la base de datos</h3>
                        <p>Posibles soluciones:</p>
                        <ol>
                            <li>Verifique que el usuario tenga permisos para acceder a la base de datos.</li>
                            <li>Compruebe que la base de datos esté correctamente configurada.</li>
                        </ol>
                    </div>
                <?php elseif (isset($tablesExist['error']) || count(array_filter($tablesExist, function($result) { return $result[0] === false; })) > 0): ?>
                    <div class="alert alert-danger">
                        <h3 class="h6">Problema: Faltan tablas en la base de datos</h3>
                        <p>Posibles soluciones:</p>
                        <ol>
                            <li>Importe el archivo database.sql para crear la estructura de la base de datos.</li>
                            <li>Ejecute los scripts SQL necesarios para crear las tablas faltantes.</li>
                        </ol>
                    </div>
                <?php else: ?>
                    <div class="alert alert-success">
                        <h3 class="h6">¡Todo está correcto!</h3>
                        <p>La base de datos está configurada correctamente y todas las tablas existen.</p>
                    </div>
                <?php endif; ?>
                
                <div class="mt-4">
                    <a href="<?php echo BASE_URL; ?>" class="btn btn-primary">Volver a la página principal</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html> 
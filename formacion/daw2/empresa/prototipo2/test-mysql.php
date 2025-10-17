<?php
// Mostrar errores
error_reporting(E_ALL);
ini_set('display_errors', 1);

echo "<h1>Prueba Simple de MySQL</h1>";

try {
    // Intentar conectar a MySQL
    $conn = new PDO('mysql:host=localhost', 'root', '');
    echo "<p style='color:green'>✓ Conexión exitosa a MySQL</p>";
    
    // Listar bases de datos
    $stmt = $conn->query("SHOW DATABASES");
    echo "<h2>Bases de datos disponibles:</h2><ul>";
    while ($row = $stmt->fetch()) {
        echo "<li>" . $row[0] . "</li>";
    }
    echo "</ul>";
    
} catch (PDOException $e) {
    echo "<p style='color:red'>✗ Error de conexión: " . $e->getMessage() . "</p>";
}
?> 
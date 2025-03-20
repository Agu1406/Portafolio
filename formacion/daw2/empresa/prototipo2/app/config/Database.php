<?php
/**
 * Clase Database
 * Maneja la conexión a la base de datos y las consultas
 */
class Database {
    private $host = DB_HOST;
    private $user = DB_USER;
    private $pass = DB_PASS;
    private $dbname = DB_NAME;

    private $dbh; // Database handler
    private $stmt;
    private $error;

    /**
     * Constructor - Establece la conexión a la base de datos
     */
    public function __construct() {
        // Configurar DSN
        $dsn = 'mysql:host=' . $this->host . ';dbname=' . $this->dbname . ';charset=utf8mb4';
        
        // Configurar opciones de PDO
        $options = array(
            PDO::ATTR_PERSISTENT => true,
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_OBJ,
            PDO::ATTR_EMULATE_PREPARES => false,
            // Añadir timeout para evitar que la página se quede cargando indefinidamente
            PDO::ATTR_TIMEOUT => 3
        );

        // Crear instancia de PDO
        try {
            $this->dbh = new PDO($dsn, $this->user, $this->pass, $options);
        } catch(PDOException $e) {
            $this->error = $e->getMessage();
            // Almacenar el error en lugar de mostrarlo inmediatamente
            // Esto permitirá que la página siga cargando con sus estilos
            $_SESSION['db_error'] = 'Error de conexión a la base de datos: ' . $this->error;
            $_SESSION['db_config'] = [
                'host' => $this->host,
                'dbname' => $this->dbname,
                'user' => $this->user
            ];
            // No interrumpir la ejecución con die()
        }
    }

    /**
     * Prepara una consulta SQL
     * @param string $sql Consulta SQL
     * @return boolean Indica si la consulta se preparó correctamente
     */
    public function query($sql) {
        // Verificar si la conexión se estableció correctamente
        if(!$this->dbh) {
            return false;
        }
        
        try {
            $this->stmt = $this->dbh->prepare($sql);
            return true;
        } catch(PDOException $e) {
            $this->error = $e->getMessage();
            return false;
        }
    }

    /**
     * Vincula un valor a un parámetro
     * @param mixed $param Parámetro
     * @param mixed $value Valor
     * @param mixed $type Tipo de dato (opcional)
     */
    public function bind($param, $value, $type = null) {
        if(is_null($type)) {
            switch(true) {
                case is_int($value):
                    $type = PDO::PARAM_INT;
                    break;
                case is_bool($value):
                    $type = PDO::PARAM_BOOL;
                    break;
                case is_null($value):
                    $type = PDO::PARAM_NULL;
                    break;
                default:
                    $type = PDO::PARAM_STR;
            }
        }

        $this->stmt->bindValue($param, $value, $type);
    }

    /**
     * Ejecuta la consulta preparada
     * @return boolean
     */
    public function execute() {
        // Verificar si la consulta se preparó correctamente
        if(!$this->stmt) {
            return false;
        }
        
        return $this->stmt->execute();
    }

    /**
     * Obtiene un conjunto de resultados como un array de objetos
     * @return array
     */
    public function resultSet() {
        // Verificar si la consulta se preparó correctamente
        if(!$this->stmt) {
            return [];
        }
        
        $this->execute();
        return $this->stmt->fetchAll();
    }

    /**
     * Obtiene un solo registro como objeto
     * @return object
     */
    public function single() {
        // Verificar si la consulta se preparó correctamente
        if(!$this->stmt) {
            return null;
        }
        
        $this->execute();
        return $this->stmt->fetch();
    }

    /**
     * Obtiene el número de filas afectadas
     * @return int
     */
    public function rowCount() {
        return $this->stmt->rowCount();
    }

    /**
     * Obtiene el último ID insertado
     * @return int
     */
    public function lastInsertId() {
        return $this->dbh->lastInsertId();
    }
} 
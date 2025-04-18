<?php
/**
 * Clase para gestionar la conexión y operaciones con la base de datos
 * 
 * Esta clase extiende la funcionalidad básica de ConexionBD para proporcionar
 * métodos específicos para interactuar con la base de datos de NaturalShop.
 */
class BaseDatos {
    /**
     * Instancia de la conexión PDO
     * @var PDO
     */
    private $conexion;
    
    /**
     * Constructor que inicializa la conexión a la base de datos
     */
    public function __construct() {
        try {
            $this->conexion = ConexionBD::obtenerInstancia();
        } catch (Exception $e) {
            // Registrar el error y relanzar la excepción
            error_log('Error al obtener la conexión a la base de datos: ' . $e->getMessage());
            throw $e;
        }
    }
    
    /**
     * Ejecuta el script SQL para crear o actualizar la estructura de la base de datos
     * 
     * @return bool True si se ejecutó correctamente, false en caso contrario
     */
    public function ejecutarScriptSQL() {
        try {
            $rutaScript = RUTA_APLICACION . '/configuracion/naturalshop_bd.sql';
            
            if (!file_exists($rutaScript)) {
                throw new Exception('El archivo de script SQL no existe');
            }
            
            // Leer el contenido del script
            $sql = file_get_contents($rutaScript);
            
            // Dividir el script en sentencias individuales
            $sentencias = $this->dividirSentenciasSQL($sql);
            
            // Ejecutar cada sentencia
            foreach ($sentencias as $sentencia) {
                if (trim($sentencia) !== '') {
                    $this->conexion->exec($sentencia);
                }
            }
            
            return true;
        } catch (Exception $e) {
            error_log('Error al ejecutar el script SQL: ' . $e->getMessage());
            return false;
        }
    }
    
    /**
     * Divide un script SQL en sentencias individuales
     * 
     * @param string $sql Script SQL completo
     * @return array Array de sentencias SQL
     */
    private function dividirSentenciasSQL($sql) {
        // Eliminar comentarios
        $sql = preg_replace('!/\*.*?\*/!s', '', $sql);
        $sql = preg_replace('!--.*?[\r\n]!', '', $sql);
        
        // Dividir por punto y coma, pero respetando los que están dentro de comillas
        $tokens = preg_split('/(\'[^\']*\')|(\;)/', $sql, -1, PREG_SPLIT_NO_EMPTY | PREG_SPLIT_DELIM_CAPTURE);
        
        $sentencias = [];
        $sentenciaActual = '';
        
        foreach ($tokens as $token) {
            if ($token === ';') {
                $sentencias[] = $sentenciaActual . ';';
                $sentenciaActual = '';
            } else {
                $sentenciaActual .= $token;
            }
        }
        
        // Añadir la última sentencia si no termina con punto y coma
        if (trim($sentenciaActual) !== '') {
            $sentencias[] = $sentenciaActual;
        }
        
        return $sentencias;
    }
    
    /**
     * Verifica si la base de datos está configurada correctamente
     * 
     * @return bool True si la base de datos está configurada, false en caso contrario
     */
    public function verificarBaseDatos() {
        try {
            // Intentar consultar la tabla de usuarios
            $stmt = $this->conexion->query("SHOW TABLES LIKE 'usuarios'");
            return $stmt->rowCount() > 0;
        } catch (Exception $e) {
            return false;
        }
    }
    
    /**
     * Obtiene todas las categorías activas
     * 
     * @return array Array de categorías
     */
    public function obtenerCategorias() {
        try {
            $stmt = $this->conexion->query("SELECT * FROM categorias WHERE activa = 1 ORDER BY orden ASC");
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            error_log('Error al obtener categorías: ' . $e->getMessage());
            return [];
        }
    }
    
    /**
     * Obtiene los productos destacados
     * 
     * @param int $limite Número máximo de productos a obtener
     * @return array Array de productos destacados
     */
    public function obtenerProductosDestacados($limite = 4) {
        try {
            $stmt = $this->conexion->prepare("
                SELECT p.*, c.nombre as categoria_nombre 
                FROM productos p
                JOIN categorias c ON p.categoria_id = c.id
                WHERE p.destacado = 1 AND p.activo = 1
                ORDER BY p.fecha_creacion DESC
                LIMIT :limite
            ");
            $stmt->bindParam(':limite', $limite, PDO::PARAM_INT);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            error_log('Error al obtener productos destacados: ' . $e->getMessage());
            return [];
        }
    }
    
    /**
     * Obtiene todos los productos activos
     * 
     * @return array Array de productos
     */
    public function obtenerProductos() {
        try {
            $stmt = $this->conexion->query("
                SELECT p.*, c.nombre as categoria_nombre 
                FROM productos p
                JOIN categorias c ON p.categoria_id = c.id
                WHERE p.activo = 1
                ORDER BY p.nombre ASC
            ");
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            error_log('Error al obtener productos: ' . $e->getMessage());
            return [];
        }
    }
    
    /**
     * Obtiene productos por categoría
     * 
     * @param int $categoriaId ID de la categoría
     * @return array Array de productos de la categoría
     */
    public function obtenerProductosPorCategoria($categoriaId) {
        try {
            $stmt = $this->conexion->prepare("
                SELECT p.*, c.nombre as categoria_nombre 
                FROM productos p
                JOIN categorias c ON p.categoria_id = c.id
                WHERE p.categoria_id = :categoria_id AND p.activo = 1
                ORDER BY p.nombre ASC
            ");
            $stmt->bindParam(':categoria_id', $categoriaId, PDO::PARAM_INT);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            error_log('Error al obtener productos por categoría: ' . $e->getMessage());
            return [];
        }
    }
    
    /**
     * Obtiene un producto por su ID
     * 
     * @param int $id ID del producto
     * @return array|null Datos del producto o null si no existe
     */
    public function obtenerProductoPorId($id) {
        try {
            $stmt = $this->conexion->prepare("
                SELECT p.*, c.nombre as categoria_nombre 
                FROM productos p
                JOIN categorias c ON p.categoria_id = c.id
                WHERE p.id = :id AND p.activo = 1
            ");
            $stmt->bindParam(':id', $id, PDO::PARAM_INT);
            $stmt->execute();
            return $stmt->fetch(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            error_log('Error al obtener producto: ' . $e->getMessage());
            return null;
        }
    }
    
    /**
     * Obtiene productos relacionados (de la misma categoría)
     * 
     * @param int $categoriaId ID de la categoría
     * @param int $productoId ID del producto actual (para excluirlo)
     * @param int $limite Número máximo de productos a obtener
     * @return array Array de productos relacionados
     */
    public function obtenerProductosRelacionados($categoriaId, $productoId, $limite = 3) {
        try {
            $stmt = $this->conexion->prepare("
                SELECT p.*, c.nombre as categoria_nombre 
                FROM productos p
                JOIN categorias c ON p.categoria_id = c.id
                WHERE p.categoria_id = :categoria_id AND p.id != :producto_id AND p.activo = 1
                ORDER BY RAND()
                LIMIT :limite
            ");
            $stmt->bindParam(':categoria_id', $categoriaId, PDO::PARAM_INT);
            $stmt->bindParam(':producto_id', $productoId, PDO::PARAM_INT);
            $stmt->bindParam(':limite', $limite, PDO::PARAM_INT);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            error_log('Error al obtener productos relacionados: ' . $e->getMessage());
            return [];
        }
    }
    
    /**
     * Verifica las credenciales de un usuario
     * 
     * @param string $email Email del usuario
     * @param string $password Contraseña sin hashear
     * @return array|null Datos del usuario o null si las credenciales son incorrectas
     */
    public function verificarCredenciales($email, $password) {
        try {
            $stmt = $this->conexion->prepare("SELECT * FROM usuarios WHERE email = :email AND activo = 1");
            $stmt->bindParam(':email', $email, PDO::PARAM_STR);
            $stmt->execute();
            $usuario = $stmt->fetch(PDO::FETCH_ASSOC);
            
            if ($usuario && password_verify($password, $usuario['password'])) {
                // Actualizar última sesión
                $stmt = $this->conexion->prepare("UPDATE usuarios SET ultima_sesion = NOW() WHERE id = :id");
                $stmt->bindParam(':id', $usuario['id'], PDO::PARAM_INT);
                $stmt->execute();
                
                // Eliminar la contraseña del array antes de devolverlo
                unset($usuario['password']);
                
                // Asegurarnos de que el rol esté presente
                if (!isset($usuario['rol'])) {
                    $usuario['rol'] = 'cliente'; // Por defecto es cliente si no tiene rol asignado
                }
                
                return $usuario;
            }
            
            return null;
        } catch (Exception $e) {
            error_log('Error al verificar credenciales: ' . $e->getMessage());
            return null;
        }
    }
    
    /**
     * Verifica si un email ya existe en la base de datos
     * 
     * @param string $email Email a verificar
     * @return bool True si el email existe, false en caso contrario
     */
    public function verificarEmailExiste($email) {
        try {
            $stmt = $this->conexion->prepare("SELECT COUNT(*) FROM usuarios WHERE email = :email");
            $stmt->bindParam(':email', $email, PDO::PARAM_STR);
            $stmt->execute();
            return $stmt->fetchColumn() > 0;
        } catch (Exception $e) {
            error_log('Error al verificar email: ' . $e->getMessage());
            return false;
        }
    }
    
    /**
     * Registra un nuevo usuario
     * 
     * @param string $nombre Nombre completo
     * @param string $email Email
     * @param string $password Contraseña hasheada
     * @param string $telefono Teléfono (opcional)
     * @param string $direccion Dirección
     * @param string $codigoPostal Código postal
     * @param string $ciudad Ciudad
     * @param string $provincia Provincia
     * @return bool True si el registro fue exitoso, false en caso contrario
     */
    public function registrarUsuario($nombre, $email, $password, $telefono, $direccion, $codigoPostal, $ciudad, $provincia) {
        try {
            $stmt = $this->conexion->prepare("
                INSERT INTO usuarios (
                    nombre, email, password, telefono, direccion, 
                    codigo_postal, ciudad, provincia, fecha_registro
                ) VALUES (
                    :nombre, :email, :password, :telefono, :direccion, 
                    :codigo_postal, :ciudad, :provincia, NOW()
                )
            ");
            
            $stmt->bindParam(':nombre', $nombre, PDO::PARAM_STR);
            $stmt->bindParam(':email', $email, PDO::PARAM_STR);
            $stmt->bindParam(':password', $password, PDO::PARAM_STR);
            $stmt->bindParam(':telefono', $telefono, PDO::PARAM_STR);
            $stmt->bindParam(':direccion', $direccion, PDO::PARAM_STR);
            $stmt->bindParam(':codigo_postal', $codigoPostal, PDO::PARAM_STR);
            $stmt->bindParam(':ciudad', $ciudad, PDO::PARAM_STR);
            $stmt->bindParam(':provincia', $provincia, PDO::PARAM_STR);
            
            return $stmt->execute();
        } catch (Exception $e) {
            error_log('Error al registrar usuario: ' . $e->getMessage());
            return false;
        }
    }
    
    /**
     * Actualiza los datos de un usuario
     * 
     * @param int $id ID del usuario
     * @param string $nombre Nombre completo
     * @param string $telefono Teléfono (opcional)
     * @param string $direccion Dirección
     * @param string $codigoPostal Código postal
     * @param string $ciudad Ciudad
     * @param string $provincia Provincia
     * @return bool True si la actualización fue exitosa, false en caso contrario
     */
    public function actualizarUsuario($id, $nombre, $telefono, $direccion, $codigoPostal, $ciudad, $provincia) {
        try {
            $stmt = $this->conexion->prepare("
                UPDATE usuarios SET 
                    nombre = :nombre,
                    telefono = :telefono,
                    direccion = :direccion,
                    codigo_postal = :codigo_postal,
                    ciudad = :ciudad,
                    provincia = :provincia,
                    fecha_actualizacion = NOW()
                WHERE id = :id
            ");
            
            $stmt->bindParam(':id', $id, PDO::PARAM_INT);
            $stmt->bindParam(':nombre', $nombre, PDO::PARAM_STR);
            $stmt->bindParam(':telefono', $telefono, PDO::PARAM_STR);
            $stmt->bindParam(':direccion', $direccion, PDO::PARAM_STR);
            $stmt->bindParam(':codigo_postal', $codigoPostal, PDO::PARAM_STR);
            $stmt->bindParam(':ciudad', $ciudad, PDO::PARAM_STR);
            $stmt->bindParam(':provincia', $provincia, PDO::PARAM_STR);
            
            return $stmt->execute();
        } catch (Exception $e) {
            error_log('Error al actualizar usuario: ' . $e->getMessage());
            return false;
        }
    }
    
    /**
     * Inserta un nuevo pedido en la base de datos
     */
    public function insertarPedido($datos) {
        try {
            $sql = "INSERT INTO pedidos (codigo, usuario_id, estado, metodo_pago, subtotal, impuestos, 
                    gastos_envio, descuento, total, nombre_envio, direccion_envio, codigo_postal_envio, 
                    ciudad_envio, provincia_envio, telefono_envio, notas, fecha_pedido) 
                    VALUES (:codigo, :usuario_id, :estado, :metodo_pago, :subtotal, :impuestos, 
                    :gastos_envio, :descuento, :total, :nombre_envio, :direccion_envio, :codigo_postal_envio, 
                    :ciudad_envio, :provincia_envio, :telefono_envio, :notas, NOW())";
            
            $stmt = $this->conexion->prepare($sql);
            $stmt->execute($datos);
            
            return $this->conexion->lastInsertId();
        } catch (PDOException $e) {
            throw new Exception("Error al insertar el pedido: " . $e->getMessage());
        }
    }
    
    /**
     * Inserta un detalle de pedido en la base de datos
     */
    public function insertarDetallePedido($datos) {
        try {
            $sql = "INSERT INTO detalles_pedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) 
                    VALUES (:pedido_id, :producto_id, :cantidad, :precio_unitario, :subtotal)";
            
            $stmt = $this->conexion->prepare($sql);
            $stmt->execute($datos);
            
            return true;
        } catch (PDOException $e) {
            throw new Exception("Error al insertar el detalle del pedido: " . $e->getMessage());
        }
    }
    
    /**
     * Obtiene los pedidos de un usuario
     */
    public function obtenerPedidosUsuario($usuarioId) {
        try {
            $sql = "SELECT p.*, 
                    (SELECT COUNT(*) FROM detalles_pedido dp WHERE dp.pedido_id = p.id) as total_productos
                    FROM pedidos p 
                    WHERE p.usuario_id = :usuario_id 
                    ORDER BY p.fecha_pedido DESC";
            
            $stmt = $this->conexion->prepare($sql);
            $stmt->bindParam(':usuario_id', $usuarioId, PDO::PARAM_INT);
            $stmt->execute();
            
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (PDOException $e) {
            throw new Exception("Error al obtener los pedidos del usuario: " . $e->getMessage());
        }
    }
    
    /**
     * Obtiene los detalles de un pedido específico
     */
    public function obtenerDetallesPedido($pedidoId) {
        try {
            $sql = "SELECT dp.*, p.nombre as nombre_producto, p.imagen_principal 
                    FROM detalles_pedido dp 
                    JOIN productos p ON dp.producto_id = p.id 
                    WHERE dp.pedido_id = :pedido_id";
            
            $stmt = $this->conexion->prepare($sql);
            $stmt->bindParam(':pedido_id', $pedidoId, PDO::PARAM_INT);
            $stmt->execute();
            
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (PDOException $e) {
            throw new Exception("Error al obtener los detalles del pedido: " . $e->getMessage());
        }
    }
    
    /**
     * Ejecuta una consulta SQL y devuelve los resultados
     * 
     * @param string $sql Consulta SQL a ejecutar
     * @param array $params Parámetros para la consulta (opcional)
     * @return array|false Resultados de la consulta o false en caso de error
     */
    public function query($sql, $params = []) {
        try {
            $stmt = $this->conexion->prepare($sql);
            $stmt->execute($params);
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $e) {
            error_log('Error al ejecutar la consulta: ' . $e->getMessage());
            return false;
        }
    }
} 
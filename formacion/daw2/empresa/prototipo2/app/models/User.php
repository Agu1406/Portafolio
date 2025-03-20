<?php
/**
 * Modelo User
 * Maneja las operaciones de la base de datos relacionadas con usuarios
 */
class User {
    private $db;

    /**
     * Constructor
     */
    public function __construct() {
        $this->db = new Database;
    }

    /**
     * Registrar usuario
     * @param array $data Datos del usuario
     * @return boolean
     */
    public function register($data) {
        // Preparar consulta
        $this->db->query('INSERT INTO users (name, email, password, created_at) VALUES (:name, :email, :password, NOW())');
        
        // Vincular valores
        $this->db->bind(':name', $data['name']);
        $this->db->bind(':email', $data['email']);
        $this->db->bind(':password', $data['password']);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Login de usuario
     * @param string $email Email del usuario
     * @param string $password Contraseña del usuario
     * @return object|boolean
     */
    public function login($email, $password) {
        // Buscar usuario por email
        $this->db->query('SELECT * FROM users WHERE email = :email');
        $this->db->bind(':email', $email);

        $row = $this->db->single();

        if($row) {
            $hashed_password = $row->password;
            
            // Verificar contraseña
            if(password_verify($password, $hashed_password)) {
                return $row;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Buscar usuario por email
     * @param string $email Email del usuario
     * @return boolean
     */
    public function findUserByEmail($email) {
        $this->db->query('SELECT * FROM users WHERE email = :email');
        $this->db->bind(':email', $email);

        $row = $this->db->single();

        // Verificar si se encontró el usuario
        if($this->db->rowCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Buscar usuario por email excepto ID
     * @param string $email Email del usuario
     * @param int $id ID del usuario a excluir
     * @return boolean
     */
    public function findUserByEmailExceptId($email, $id) {
        $this->db->query('SELECT * FROM users WHERE email = :email AND id != :id');
        $this->db->bind(':email', $email);
        $this->db->bind(':id', $id);

        $row = $this->db->single();

        // Verificar si se encontró el usuario
        if($this->db->rowCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtener usuario por ID
     * @param int $id ID del usuario
     * @return object
     */
    public function getUserById($id) {
        $this->db->query('SELECT * FROM users WHERE id = :id');
        $this->db->bind(':id', $id);

        return $this->db->single();
    }

    /**
     * Actualizar usuario
     * @param array $data Datos del usuario
     * @return boolean
     */
    public function updateUser($data) {
        // Preparar consulta
        $this->db->query('UPDATE users SET name = :name, email = :email, bio = :bio, updated_at = NOW() WHERE id = :id');
        
        // Vincular valores
        $this->db->bind(':id', $data['id']);
        $this->db->bind(':name', $data['name']);
        $this->db->bind(':email', $data['email']);
        $this->db->bind(':bio', $data['bio']);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Actualizar contraseña
     * @param int $id ID del usuario
     * @param string $password Nueva contraseña
     * @return boolean
     */
    public function updatePassword($id, $password) {
        // Preparar consulta
        $this->db->query('UPDATE users SET password = :password, updated_at = NOW() WHERE id = :id');
        
        // Vincular valores
        $this->db->bind(':id', $id);
        $this->db->bind(':password', $password);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtener todos los usuarios
     * @return array
     */
    public function getAllUsers() {
        $this->db->query('SELECT * FROM users ORDER BY created_at DESC');
        return $this->db->resultSet();
    }
} 
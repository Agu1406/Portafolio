<?php
/**
 * Modelo Category
 * Maneja las operaciones de la base de datos relacionadas con categorías
 */
class Category {
    private $db;

    /**
     * Constructor
     */
    public function __construct() {
        $this->db = new Database;
    }

    /**
     * Obtener todas las categorías
     * @return array
     */
    public function getAllCategories() {
        $this->db->query('SELECT * FROM categories ORDER BY name ASC');
        return $this->db->resultSet();
    }

    /**
     * Obtener categoría por ID
     * @param int $id ID de la categoría
     * @return object
     */
    public function getCategoryById($id) {
        $this->db->query('SELECT * FROM categories WHERE id = :id');
        $this->db->bind(':id', $id);
        return $this->db->single();
    }

    /**
     * Agregar categoría
     * @param array $data Datos de la categoría
     * @return boolean
     */
    public function addCategory($data) {
        // Preparar consulta
        $this->db->query('INSERT INTO categories (name, description) VALUES (:name, :description)');
        
        // Vincular valores
        $this->db->bind(':name', $data['name']);
        $this->db->bind(':description', $data['description']);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Actualizar categoría
     * @param array $data Datos de la categoría
     * @return boolean
     */
    public function updateCategory($data) {
        // Preparar consulta
        $this->db->query('UPDATE categories SET name = :name, description = :description WHERE id = :id');
        
        // Vincular valores
        $this->db->bind(':id', $data['id']);
        $this->db->bind(':name', $data['name']);
        $this->db->bind(':description', $data['description']);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Eliminar categoría
     * @param int $id ID de la categoría
     * @return boolean
     */
    public function deleteCategory($id) {
        // Preparar consulta
        $this->db->query('DELETE FROM categories WHERE id = :id');
        
        // Vincular valores
        $this->db->bind(':id', $id);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtener categorías populares
     * @param int $limit Límite de categorías a obtener
     * @return array
     */
    public function getPopularCategories($limit = 5) {
        $this->db->query('SELECT c.*, COUNT(r.id) as recipe_count 
                          FROM categories c 
                          LEFT JOIN recipes r ON c.id = r.category_id 
                          GROUP BY c.id 
                          ORDER BY recipe_count DESC 
                          LIMIT :limit');
        
        $this->db->bind(':limit', $limit);
        
        return $this->db->resultSet();
    }
} 
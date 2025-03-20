<?php
/**
 * Modelo Comment
 * Maneja las operaciones de la base de datos relacionadas con comentarios
 */
class Comment {
    private $db;

    /**
     * Constructor
     */
    public function __construct() {
        $this->db = new Database;
    }

    /**
     * Obtener comentarios por receta
     * @param int $recipeId ID de la receta
     * @return array
     */
    public function getCommentsByRecipe($recipeId) {
        $this->db->query('SELECT c.*, u.name as user_name 
                          FROM comments c 
                          JOIN users u ON c.user_id = u.id 
                          WHERE c.recipe_id = :recipe_id 
                          ORDER BY c.created_at DESC');
        
        $this->db->bind(':recipe_id', $recipeId);
        
        return $this->db->resultSet();
    }

    /**
     * Obtener comentario por ID
     * @param int $id ID del comentario
     * @return object
     */
    public function getCommentById($id) {
        $this->db->query('SELECT c.*, u.name as user_name 
                          FROM comments c 
                          JOIN users u ON c.user_id = u.id 
                          WHERE c.id = :id');
        
        $this->db->bind(':id', $id);
        
        return $this->db->single();
    }

    /**
     * Agregar comentario
     * @param array $data Datos del comentario
     * @return boolean
     */
    public function addComment($data) {
        // Preparar consulta
        $this->db->query('INSERT INTO comments (recipe_id, user_id, body, created_at) 
                          VALUES (:recipe_id, :user_id, :body, NOW())');
        
        // Vincular valores
        $this->db->bind(':recipe_id', $data['recipe_id']);
        $this->db->bind(':user_id', $data['user_id']);
        $this->db->bind(':body', $data['body']);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Actualizar comentario
     * @param array $data Datos del comentario
     * @return boolean
     */
    public function updateComment($data) {
        // Preparar consulta
        $this->db->query('UPDATE comments SET body = :body, updated_at = NOW() 
                          WHERE id = :id AND user_id = :user_id');
        
        // Vincular valores
        $this->db->bind(':id', $data['id']);
        $this->db->bind(':user_id', $data['user_id']);
        $this->db->bind(':body', $data['body']);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Eliminar comentario
     * @param int $id ID del comentario
     * @param int $userId ID del usuario (para verificar propiedad)
     * @return boolean
     */
    public function deleteComment($id, $userId) {
        // Preparar consulta
        $this->db->query('DELETE FROM comments WHERE id = :id AND user_id = :user_id');
        
        // Vincular valores
        $this->db->bind(':id', $id);
        $this->db->bind(':user_id', $userId);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtener comentarios recientes
     * @param int $limit Límite de comentarios a obtener
     * @return array
     */
    public function getRecentComments($limit = 5) {
        $this->db->query('SELECT c.*, u.name as user_name, r.title as recipe_title 
                          FROM comments c 
                          JOIN users u ON c.user_id = u.id 
                          JOIN recipes r ON c.recipe_id = r.id 
                          ORDER BY c.created_at DESC 
                          LIMIT :limit');
        
        $this->db->bind(':limit', $limit);
        
        return $this->db->resultSet();
    }
} 
<?php
/**
 * Modelo Recipe
 * Maneja las operaciones de la base de datos relacionadas con recetas
 */
class Recipe {
    private $db;

    /**
     * Constructor
     */
    public function __construct() {
        $this->db = new Database;
    }

    /**
     * Obtener todas las recetas
     * @return array
     */
    public function getAllRecipes() {
        $this->db->query('SELECT r.*, u.name as author, c.name as category_name 
                          FROM recipes r 
                          JOIN users u ON r.user_id = u.id 
                          JOIN categories c ON r.category_id = c.id 
                          ORDER BY r.created_at DESC');
        
        return $this->db->resultSet();
    }

    /**
     * Obtener receta por ID
     * @param int $id ID de la receta
     * @return object
     */
    public function getRecipeById($id) {
        $this->db->query('SELECT r.*, u.name as author, c.name as category_name 
                          FROM recipes r 
                          JOIN users u ON r.user_id = u.id 
                          JOIN categories c ON r.category_id = c.id 
                          WHERE r.id = :id');
        
        $this->db->bind(':id', $id);
        
        return $this->db->single();
    }

    /**
     * Obtener recetas por usuario
     * @param int $userId ID del usuario
     * @return array
     */
    public function getRecipesByUser($userId) {
        $this->db->query('SELECT r.*, c.name as category_name 
                          FROM recipes r 
                          JOIN categories c ON r.category_id = c.id 
                          WHERE r.user_id = :user_id 
                          ORDER BY r.created_at DESC');
        
        $this->db->bind(':user_id', $userId);
        
        return $this->db->resultSet();
    }

    /**
     * Obtener recetas por categoría
     * @param int $categoryId ID de la categoría
     * @return array
     */
    public function getRecipesByCategory($categoryId) {
        $this->db->query('SELECT r.*, u.name as author 
                          FROM recipes r 
                          JOIN users u ON r.user_id = u.id 
                          WHERE r.category_id = :category_id 
                          ORDER BY r.created_at DESC');
        
        $this->db->bind(':category_id', $categoryId);
        
        return $this->db->resultSet();
    }

    /**
     * Agregar receta
     * @param array $data Datos de la receta
     * @return boolean
     */
    public function addRecipe($data) {
        // Preparar consulta
        $this->db->query('INSERT INTO recipes (title, category_id, ingredients, instructions, preparation_time, cooking_time, servings, difficulty, user_id, created_at) 
                          VALUES (:title, :category_id, :ingredients, :instructions, :preparation_time, :cooking_time, :servings, :difficulty, :user_id, NOW())');
        
        // Vincular valores
        $this->db->bind(':title', $data['recipe_title']);
        $this->db->bind(':category_id', $data['category_id']);
        $this->db->bind(':ingredients', $data['ingredients']);
        $this->db->bind(':instructions', $data['instructions']);
        $this->db->bind(':preparation_time', $data['preparation_time']);
        $this->db->bind(':cooking_time', $data['cooking_time']);
        $this->db->bind(':servings', $data['servings']);
        $this->db->bind(':difficulty', $data['difficulty']);
        $this->db->bind(':user_id', $data['user_id']);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Actualizar receta
     * @param array $data Datos de la receta
     * @return boolean
     */
    public function updateRecipe($data) {
        // Preparar consulta
        $this->db->query('UPDATE recipes 
                          SET title = :title, category_id = :category_id, ingredients = :ingredients, 
                              instructions = :instructions, preparation_time = :preparation_time, 
                              cooking_time = :cooking_time, servings = :servings, difficulty = :difficulty, 
                              updated_at = NOW() 
                          WHERE id = :id AND user_id = :user_id');
        
        // Vincular valores
        $this->db->bind(':id', $data['id']);
        $this->db->bind(':title', $data['recipe_title']);
        $this->db->bind(':category_id', $data['category_id']);
        $this->db->bind(':ingredients', $data['ingredients']);
        $this->db->bind(':instructions', $data['instructions']);
        $this->db->bind(':preparation_time', $data['preparation_time']);
        $this->db->bind(':cooking_time', $data['cooking_time']);
        $this->db->bind(':servings', $data['servings']);
        $this->db->bind(':difficulty', $data['difficulty']);
        $this->db->bind(':user_id', $data['user_id']);

        // Ejecutar
        if($this->db->execute()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Eliminar receta
     * @param int $id ID de la receta
     * @return boolean
     */
    public function deleteRecipe($id) {
        // Preparar consulta
        $this->db->query('DELETE FROM recipes WHERE id = :id');
        
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
     * Buscar recetas
     * @param string $search Término de búsqueda
     * @return array
     */
    public function searchRecipes($search) {
        // Preparar consulta
        $this->db->query('SELECT r.*, u.name as author, c.name as category_name 
                          FROM recipes r 
                          JOIN users u ON r.user_id = u.id 
                          JOIN categories c ON r.category_id = c.id 
                          WHERE r.title LIKE :search 
                          OR r.ingredients LIKE :search 
                          OR r.instructions LIKE :search 
                          ORDER BY r.created_at DESC');
        
        // Vincular valores
        $this->db->bind(':search', '%' . $search . '%');

        return $this->db->resultSet();
    }

    /**
     * Obtener recetas populares
     * @param int $limit Límite de recetas a obtener
     * @return array
     */
    public function getPopularRecipes($limit = 5) {
        try {
            // Verificar si la conexión a la base de datos se estableció correctamente
            if (!$this->db->query('SELECT r.*, u.name as author, c.name as category_name, 
                            (SELECT COUNT(*) FROM comments WHERE recipe_id = r.id) as comment_count 
                            FROM recipes r 
                            JOIN users u ON r.user_id = u.id 
                            JOIN categories c ON r.category_id = c.id 
                            ORDER BY comment_count DESC, r.created_at DESC 
                            LIMIT :limit')) {
                // Si la consulta falló, devolver un array vacío
                return [];
            }
            
            $this->db->bind(':limit', $limit);
            
            return $this->db->resultSet();
        } catch (Exception $e) {
            // En caso de error, devolver un array vacío
            return [];
        }
    }

    /**
     * Obtener recetas recientes
     * @param int $limit Límite de recetas a obtener
     * @return array
     */
    public function getRecentRecipes($limit = 5) {
        $this->db->query('SELECT r.*, u.name as author, c.name as category_name 
                          FROM recipes r 
                          JOIN users u ON r.user_id = u.id 
                          JOIN categories c ON r.category_id = c.id 
                          ORDER BY r.created_at DESC 
                          LIMIT :limit');
        
        $this->db->bind(':limit', $limit);
        
        return $this->db->resultSet();
    }
} 
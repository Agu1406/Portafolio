<?php
/**
 * Controlador Recipes
 * Maneja las recetas de la aplicación
 */
class RecipesController extends Controller {
    /**
     * Constructor
     */
    public function __construct() {
        // Verificar si el usuario está logueado para ciertas acciones
        if(!isset($_SESSION['user_id']) && 
           in_array($_GET['url'] ?? '', ['recipes/add', 'recipes/edit', 'recipes/delete'])) {
            redirect('users/login');
        }

        // Cargar modelos
        $this->recipeModel = $this->model('Recipe');
        $this->userModel = $this->model('User');
        $this->categoryModel = $this->model('Category');
        $this->commentModel = $this->model('Comment');
    }

    /**
     * Mostrar todas las recetas
     */
    public function index() {
        // Obtener todas las recetas
        $recipes = $this->recipeModel->getAllRecipes();

        // Datos para la vista
        $data = [
            'title' => 'Recetas',
            'recipes' => $recipes
        ];

        // Cargar vista
        $this->view('recipes/index', $data);
    }

    /**
     * Mostrar una receta específica
     * @param int $id ID de la receta
     */
    public function show($id) {
        // Obtener receta
        $recipe = $this->recipeModel->getRecipeById($id);

        // Verificar si la receta existe
        if(!$recipe) {
            // Mensaje flash
            setFlash('recipe_error', 'La receta solicitada no existe o ha sido eliminada.');
            // Redireccionar
            $this->redirect('recipes');
            return;
        }

        // Obtener usuario que creó la receta
        $user = $this->userModel->getUserById($recipe->user_id);

        // Obtener comentarios de la receta
        $comments = $this->commentModel->getCommentsByRecipe($id);

        // Datos para la vista
        $data = [
            'title' => $recipe->title,
            'recipe' => $recipe,
            'user' => $user,
            'comments' => $comments
        ];

        // Cargar vista
        $this->view('recipes/show', $data);
    }

    /**
     * Agregar una nueva receta
     */
    public function add() {
        // Obtener categorías para el formulario
        $categories = $this->categoryModel->getAllCategories();

        // Procesar formulario si se envió
        if($_SERVER['REQUEST_METHOD'] == 'POST') {
            // Sanitizar datos POST
            $_POST = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING);

            // Datos del formulario
            $data = [
                'title' => 'Agregar Receta',
                'recipe_title' => trim($_POST['title']),
                'category_id' => $_POST['category_id'],
                'ingredients' => trim($_POST['ingredients']),
                'instructions' => trim($_POST['instructions']),
                'preparation_time' => trim($_POST['preparation_time']),
                'cooking_time' => trim($_POST['cooking_time']),
                'servings' => trim($_POST['servings']),
                'difficulty' => $_POST['difficulty'],
                'user_id' => $_SESSION['user_id'],
                'categories' => $categories,
                'title_err' => '',
                'category_id_err' => '',
                'ingredients_err' => '',
                'instructions_err' => ''
            ];

            // Validar título
            if(empty($data['recipe_title'])) {
                $data['title_err'] = 'Por favor ingrese un título';
            }

            // Validar categoría
            if(empty($data['category_id'])) {
                $data['category_id_err'] = 'Por favor seleccione una categoría';
            }

            // Validar ingredientes
            if(empty($data['ingredients'])) {
                $data['ingredients_err'] = 'Por favor ingrese los ingredientes';
            }

            // Validar instrucciones
            if(empty($data['instructions'])) {
                $data['instructions_err'] = 'Por favor ingrese las instrucciones';
            }

            // Si no hay errores, agregar receta
            if(empty($data['title_err']) && empty($data['category_id_err']) && 
               empty($data['ingredients_err']) && empty($data['instructions_err'])) {
                
                // Agregar receta
                if($this->recipeModel->addRecipe($data)) {
                    // Mensaje flash
                    setFlash('recipe_success', '¡Receta agregada correctamente!');
                    
                    // Redireccionar
                    $this->redirect('recipes');
                } else {
                    die('Algo salió mal');
                }
            } else {
                // Cargar vista con errores
                $this->view('recipes/add', $data);
            }
        } else {
            // Datos para la vista
            $data = [
                'title' => 'Agregar Receta',
                'recipe_title' => '',
                'category_id' => '',
                'ingredients' => '',
                'instructions' => '',
                'preparation_time' => '',
                'cooking_time' => '',
                'servings' => '',
                'difficulty' => 'Fácil',
                'categories' => $categories,
                'title_err' => '',
                'category_id_err' => '',
                'ingredients_err' => '',
                'instructions_err' => ''
            ];

            // Cargar vista
            $this->view('recipes/add', $data);
        }
    }

    /**
     * Editar una receta
     * @param int $id ID de la receta
     */
    public function edit($id) {
        // Obtener receta
        $recipe = $this->recipeModel->getRecipeById($id);

        // Verificar si la receta existe
        if(!$recipe) {
            $this->redirect('recipes');
        }

        // Verificar si el usuario es el propietario
        if($recipe->user_id != $_SESSION['user_id']) {
            $this->redirect('recipes');
        }

        // Obtener categorías para el formulario
        $categories = $this->categoryModel->getAllCategories();

        // Procesar formulario si se envió
        if($_SERVER['REQUEST_METHOD'] == 'POST') {
            // Sanitizar datos POST
            $_POST = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING);

            // Datos del formulario
            $data = [
                'title' => 'Editar Receta',
                'id' => $id,
                'recipe_title' => trim($_POST['title']),
                'category_id' => $_POST['category_id'],
                'ingredients' => trim($_POST['ingredients']),
                'instructions' => trim($_POST['instructions']),
                'preparation_time' => trim($_POST['preparation_time']),
                'cooking_time' => trim($_POST['cooking_time']),
                'servings' => trim($_POST['servings']),
                'difficulty' => $_POST['difficulty'],
                'user_id' => $_SESSION['user_id'],
                'categories' => $categories,
                'title_err' => '',
                'category_id_err' => '',
                'ingredients_err' => '',
                'instructions_err' => ''
            ];

            // Validar título
            if(empty($data['recipe_title'])) {
                $data['title_err'] = 'Por favor ingrese un título';
            }

            // Validar categoría
            if(empty($data['category_id'])) {
                $data['category_id_err'] = 'Por favor seleccione una categoría';
            }

            // Validar ingredientes
            if(empty($data['ingredients'])) {
                $data['ingredients_err'] = 'Por favor ingrese los ingredientes';
            }

            // Validar instrucciones
            if(empty($data['instructions'])) {
                $data['instructions_err'] = 'Por favor ingrese las instrucciones';
            }

            // Si no hay errores, actualizar receta
            if(empty($data['title_err']) && empty($data['category_id_err']) && 
               empty($data['ingredients_err']) && empty($data['instructions_err'])) {
                
                // Actualizar receta
                if($this->recipeModel->updateRecipe($data)) {
                    // Mensaje flash
                    setFlash('recipe_success', '¡Receta actualizada correctamente!');
                    
                    // Redireccionar
                    $this->redirect('recipes/show/' . $id);
                } else {
                    die('Algo salió mal');
                }
            } else {
                // Cargar vista con errores
                $this->view('recipes/edit', $data);
            }
        } else {
            // Datos para la vista
            $data = [
                'title' => 'Editar Receta',
                'id' => $id,
                'recipe_title' => $recipe->title,
                'category_id' => $recipe->category_id,
                'ingredients' => $recipe->ingredients,
                'instructions' => $recipe->instructions,
                'preparation_time' => $recipe->preparation_time,
                'cooking_time' => $recipe->cooking_time,
                'servings' => $recipe->servings,
                'difficulty' => $recipe->difficulty,
                'categories' => $categories,
                'title_err' => '',
                'category_id_err' => '',
                'ingredients_err' => '',
                'instructions_err' => ''
            ];

            // Cargar vista
            $this->view('recipes/edit', $data);
        }
    }

    /**
     * Eliminar una receta
     * @param int $id ID de la receta
     */
    public function delete($id) {
        // Verificar si se envió por POST
        if($_SERVER['REQUEST_METHOD'] == 'POST') {
            // Obtener receta
            $recipe = $this->recipeModel->getRecipeById($id);

            // Verificar si la receta existe
            if(!$recipe) {
                $this->redirect('recipes');
            }

            // Verificar si el usuario es el propietario
            if($recipe->user_id != $_SESSION['user_id']) {
                $this->redirect('recipes');
            }

            // Eliminar receta
            if($this->recipeModel->deleteRecipe($id)) {
                // Mensaje flash
                setFlash('recipe_success', '¡Receta eliminada correctamente!');
            } else {
                die('Algo salió mal');
            }
        }

        // Redireccionar
        $this->redirect('recipes');
    }

    /**
     * Buscar recetas
     */
    public function search() {
        // Verificar si se envió por GET
        if(isset($_GET['search'])) {
            $search = trim($_GET['search']);
            
            // Buscar recetas
            $recipes = $this->recipeModel->searchRecipes($search);

            // Datos para la vista
            $data = [
                'title' => 'Resultados de búsqueda: ' . $search,
                'recipes' => $recipes,
                'search' => $search
            ];

            // Cargar vista
            $this->view('recipes/search', $data);
        } else {
            $this->redirect('recipes');
        }
    }

    /**
     * Filtrar recetas por categoría
     * @param int $categoryId ID de la categoría
     */
    public function category($categoryId) {
        // Obtener categoría
        $category = $this->categoryModel->getCategoryById($categoryId);

        // Verificar si la categoría existe
        if(!$category) {
            $this->redirect('recipes');
        }

        // Obtener recetas por categoría
        $recipes = $this->recipeModel->getRecipesByCategory($categoryId);

        // Datos para la vista
        $data = [
            'title' => 'Recetas de ' . $category->name,
            'recipes' => $recipes,
            'category' => $category
        ];

        // Cargar vista
        $this->view('recipes/category', $data);
    }

    /**
     * Agregar comentario a una receta
     * @param int $recipeId ID de la receta
     */
    public function comment($recipeId) {
        // Verificar si el usuario está logueado
        if(!$this->isLoggedIn()) {
            $this->redirect('users/login');
        }

        // Verificar si se envió por POST
        if($_SERVER['REQUEST_METHOD'] == 'POST') {
            // Sanitizar datos POST
            $_POST = filter_input_array(INPUT_POST, FILTER_SANITIZE_STRING);

            // Datos del comentario
            $data = [
                'recipe_id' => $recipeId,
                'user_id' => $_SESSION['user_id'],
                'body' => trim($_POST['body']),
                'body_err' => ''
            ];

            // Validar comentario
            if(empty($data['body'])) {
                $data['body_err'] = 'Por favor ingrese un comentario';
            }

            // Si no hay errores, agregar comentario
            if(empty($data['body_err'])) {
                // Agregar comentario
                if($this->commentModel->addComment($data)) {
                    // Mensaje flash
                    setFlash('comment_success', '¡Comentario agregado correctamente!');
                } else {
                    die('Algo salió mal');
                }
            } else {
                // Mensaje flash
                setFlash('comment_error', $data['body_err'], 'alert alert-danger');
            }
        }

        // Redireccionar
        $this->redirect('recipes/show/' . $recipeId);
    }
} 
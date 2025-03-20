<?php require APPROOT . '/views/layouts/main.php'; ?>

<div class="container py-4">
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h1 class="h4 mb-0">Añadir nueva receta</h1>
                </div>
                <div class="card-body">
                    <form action="<?php echo url('recipes/add'); ?>" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                        <div class="mb-3">
                            <label for="title" class="form-label">Título de la receta</label>
                            <input type="text" class="form-control <?php echo isset($data['title_err']) ? 'is-invalid' : ''; ?>" id="title" name="title" value="<?php echo $data['title'] ?? ''; ?>" required>
                            <div class="invalid-feedback">
                                <?php echo $data['title_err'] ?? 'Por favor ingresa un título para tu receta.'; ?>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="category_id" class="form-label">Categoría</label>
                                <select class="form-select <?php echo isset($data['category_id_err']) ? 'is-invalid' : ''; ?>" id="category_id" name="category_id" required>
                                    <option value="" selected disabled>Selecciona una categoría</option>
                                    <?php foreach($data['categories'] as $category) : ?>
                                        <option value="<?php echo $category->id; ?>" <?php echo isset($data['category_id']) && $data['category_id'] == $category->id ? 'selected' : ''; ?>>
                                            <?php echo $category->name; ?>
                                        </option>
                                    <?php endforeach; ?>
                                </select>
                                <div class="invalid-feedback">
                                    <?php echo $data['category_id_err'] ?? 'Por favor selecciona una categoría.'; ?>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label for="difficulty" class="form-label">Dificultad</label>
                                <select class="form-select <?php echo isset($data['difficulty_err']) ? 'is-invalid' : ''; ?>" id="difficulty" name="difficulty" required>
                                    <option value="" selected disabled>Selecciona la dificultad</option>
                                    <option value="Fácil" <?php echo isset($data['difficulty']) && $data['difficulty'] == 'Fácil' ? 'selected' : ''; ?>>Fácil</option>
                                    <option value="Media" <?php echo isset($data['difficulty']) && $data['difficulty'] == 'Media' ? 'selected' : ''; ?>>Media</option>
                                    <option value="Difícil" <?php echo isset($data['difficulty']) && $data['difficulty'] == 'Difícil' ? 'selected' : ''; ?>>Difícil</option>
                                </select>
                                <div class="invalid-feedback">
                                    <?php echo $data['difficulty_err'] ?? 'Por favor selecciona la dificultad.'; ?>
                                </div>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label for="preparation_time" class="form-label">Tiempo de preparación</label>
                                <input type="text" class="form-control <?php echo isset($data['preparation_time_err']) ? 'is-invalid' : ''; ?>" id="preparation_time" name="preparation_time" value="<?php echo $data['preparation_time'] ?? ''; ?>" placeholder="Ej: 30 minutos">
                                <div class="invalid-feedback">
                                    <?php echo $data['preparation_time_err'] ?? 'Formato inválido.'; ?>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label for="cooking_time" class="form-label">Tiempo de cocción</label>
                                <input type="text" class="form-control <?php echo isset($data['cooking_time_err']) ? 'is-invalid' : ''; ?>" id="cooking_time" name="cooking_time" value="<?php echo $data['cooking_time'] ?? ''; ?>" placeholder="Ej: 45 minutos">
                                <div class="invalid-feedback">
                                    <?php echo $data['cooking_time_err'] ?? 'Formato inválido.'; ?>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label for="servings" class="form-label">Porciones</label>
                                <input type="number" class="form-control <?php echo isset($data['servings_err']) ? 'is-invalid' : ''; ?>" id="servings" name="servings" value="<?php echo $data['servings'] ?? ''; ?>" min="1" placeholder="Ej: 4">
                                <div class="invalid-feedback">
                                    <?php echo $data['servings_err'] ?? 'Por favor ingresa un número válido.'; ?>
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="ingredients" class="form-label">Ingredientes</label>
                            <textarea class="form-control <?php echo isset($data['ingredients_err']) ? 'is-invalid' : ''; ?>" id="ingredients" name="ingredients" rows="6" required placeholder="Escribe cada ingrediente en una línea nueva"><?php echo $data['ingredients'] ?? ''; ?></textarea>
                            <div class="invalid-feedback">
                                <?php echo $data['ingredients_err'] ?? 'Por favor ingresa los ingredientes.'; ?>
                            </div>
                            <div class="form-text">Escribe cada ingrediente en una línea nueva.</div>
                        </div>

                        <div class="mb-3">
                            <label for="instructions" class="form-label">Instrucciones</label>
                            <textarea class="form-control <?php echo isset($data['instructions_err']) ? 'is-invalid' : ''; ?>" id="instructions" name="instructions" rows="8" required placeholder="Escribe cada paso en una línea nueva"><?php echo $data['instructions'] ?? ''; ?></textarea>
                            <div class="invalid-feedback">
                                <?php echo $data['instructions_err'] ?? 'Por favor ingresa las instrucciones.'; ?>
                            </div>
                            <div class="form-text">Escribe cada paso en una línea nueva.</div>
                        </div>

                        <div class="mb-3">
                            <label for="image" class="form-label">Imagen de la receta</label>
                            <input type="file" class="form-control image-upload <?php echo isset($data['image_err']) ? 'is-invalid' : ''; ?>" id="image" name="image" accept="image/*" required data-preview="#imagePreview">
                            <div class="invalid-feedback">
                                <?php echo $data['image_err'] ?? 'Por favor selecciona una imagen.'; ?>
                            </div>
                            <div class="mt-2">
                                <img id="imagePreview" src="#" alt="Vista previa" class="img-thumbnail" style="display: none; max-width: 200px;">
                            </div>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a href="<?php echo url('recipes'); ?>" class="btn btn-secondary me-md-2">Cancelar</a>
                            <button type="submit" class="btn btn-primary">Publicar receta</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div> 
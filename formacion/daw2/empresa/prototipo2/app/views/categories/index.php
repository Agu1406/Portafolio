<div class="container py-4">
    <div class="row mb-4">
        <div class="col-md-8">
            <h1 class="mb-3">Categorías de recetas</h1>
            <p class="lead">Explora nuestras categorías para encontrar recetas que se adapten a tus gustos y necesidades.</p>
        </div>
        <?php if(isAdmin()) : ?>
        <div class="col-md-4 text-md-end">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCategoryModal">
                <i class="fas fa-plus-circle me-1"></i> Añadir categoría
            </button>
        </div>
        <?php endif; ?>
    </div>

    <div class="row">
        <?php if(empty($data['categories'])) : ?>
            <div class="col-12">
                <div class="alert alert-info">
                    No hay categorías disponibles en este momento.
                </div>
            </div>
        <?php else : ?>
            <?php foreach($data['categories'] as $category) : ?>
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <img src="<?php echo asset('img/categories/' . ($category->image ?? 'default.jpg')); ?>" class="card-img-top" alt="<?php echo $category->name; ?>">
                        <div class="card-body">
                            <h2 class="card-title h5"><?php echo $category->name; ?></h2>
                            <p class="card-text small text-muted"><?php echo $category->recipe_count; ?> recetas</p>
                            <p class="card-text"><?php echo $category->description; ?></p>
                        </div>
                        <div class="card-footer bg-white">
                            <a href="<?php echo url('recipes/category/' . $category->id); ?>" class="btn btn-outline-primary">Ver recetas</a>
                            <?php if(isAdmin()) : ?>
                                <div class="float-end">
                                    <button type="button" class="btn btn-sm btn-outline-secondary me-1" data-bs-toggle="modal" data-bs-target="#editCategoryModal<?php echo $category->id; ?>">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button type="button" class="btn btn-sm btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteCategoryModal<?php echo $category->id; ?>">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </div>
                            <?php endif; ?>
                        </div>
                    </div>
                </div>

                <?php if(isAdmin()) : ?>
                <!-- Modal para editar categoría -->
                <div class="modal fade" id="editCategoryModal<?php echo $category->id; ?>" tabindex="-1" aria-labelledby="editCategoryModalLabel<?php echo $category->id; ?>" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="<?php echo url('categories/update/' . $category->id); ?>" method="post" enctype="multipart/form-data">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editCategoryModalLabel<?php echo $category->id; ?>">Editar categoría</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="name<?php echo $category->id; ?>" class="form-label">Nombre</label>
                                        <input type="text" class="form-control" id="name<?php echo $category->id; ?>" name="name" value="<?php echo $category->name; ?>" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="description<?php echo $category->id; ?>" class="form-label">Descripción</label>
                                        <textarea class="form-control" id="description<?php echo $category->id; ?>" name="description" rows="3"><?php echo $category->description; ?></textarea>
                                    </div>
                                    <div class="mb-3">
                                        <label for="image<?php echo $category->id; ?>" class="form-label">Imagen</label>
                                        <input type="file" class="form-control" id="image<?php echo $category->id; ?>" name="image" accept="image/*">
                                        <div class="form-text">Deja este campo vacío si no deseas cambiar la imagen actual.</div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Modal para eliminar categoría -->
                <div class="modal fade" id="deleteCategoryModal<?php echo $category->id; ?>" tabindex="-1" aria-labelledby="deleteCategoryModalLabel<?php echo $category->id; ?>" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteCategoryModalLabel<?php echo $category->id; ?>">Confirmar eliminación</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p>¿Estás seguro de que deseas eliminar la categoría <strong><?php echo $category->name; ?></strong>?</p>
                                <p class="text-danger">Esta acción no se puede deshacer y eliminará todas las recetas asociadas a esta categoría.</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <form action="<?php echo url('categories/delete/' . $category->id); ?>" method="post">
                                    <button type="submit" class="btn btn-danger">Eliminar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <?php endif; ?>
            <?php endforeach; ?>
        <?php endif; ?>
    </div>
</div>

<?php if(isAdmin()) : ?>
<!-- Modal para añadir categoría -->
<div class="modal fade" id="addCategoryModal" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="<?php echo url('categories/add'); ?>" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <h5 class="modal-title" id="addCategoryModalLabel">Añadir nueva categoría</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="name" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Descripción</label>
                        <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">Imagen</label>
                        <input type="file" class="form-control" id="image" name="image" accept="image/*">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Añadir categoría</button>
                </div>
            </form>
        </div>
    </div>
</div>
<?php endif; ?> 
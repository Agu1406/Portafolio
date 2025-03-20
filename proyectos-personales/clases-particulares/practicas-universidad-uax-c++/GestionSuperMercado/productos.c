#include "supermercado.h"

/**
 * @brief Valida los datos de un producto
 * @param nombre Nombre del producto
 * @param codigo Código del producto
 * @param precio Precio del producto
 * @param stock Stock inicial
 * @return 1 si los datos son válidos, 0 en caso contrario
 */
static int validarDatosProducto(const char* nombre, int codigo, float precio, int stock) {
    if (nombre == NULL || strlen(nombre) == 0) {
        printf("Error: El nombre no puede estar vacío\n");
        return 0;
    }
    if (codigo <= 0) {
        printf("Error: El código debe ser positivo\n");
        return 0;
    }
    if (precio <= 0) {
        printf("Error: El precio debe ser positivo\n");
        return 0;
    }
    if (stock < 0) {
        printf("Error: El stock no puede ser negativo\n");
        return 0;
    }
    return 1;
}

Producto inicializarProducto(const char* nombre, int codigo, float precio, int stock) {
    Producto p;
    strncpy(p.nombre, nombre, MAX_NOMBRE - 1);
    p.nombre[MAX_NOMBRE - 1] = '\0';
    p.codigo = codigo;
    p.precio = precio;
    p.stock = stock;
    return p;
}

int agregarProducto(Supermercado* super, Producto producto) {
    // Verificar si el código ya existe
    for (int i = 0; i < super->totalProductos; i++) {
        if (super->productos[i].codigo == producto.codigo) {
            printf("Error: Ya existe un producto con ese código\n");
            return 0;
        }
    }

    Producto* temp = (Producto*)realloc(super->productos, 
                                     (super->totalProductos + 1) * sizeof(Producto));
    if (temp == NULL) {
        printf("Error: No se pudo asignar memoria para el nuevo producto\n");
        return 0;
    }

    super->productos = temp;
    super->productos[super->totalProductos] = producto;
    super->totalProductos++;
    return 1;
}

int eliminarProducto(Supermercado* super, int codigo) {
    int encontrado = -1;
    
    // Buscar el producto
    for (int i = 0; i < super->totalProductos; i++) {
        if (super->productos[i].codigo == codigo) {
            encontrado = i;
            break;
        }
    }

    if (encontrado == -1) {
        printf("Error: No se encontró el producto con código %d\n", codigo);
        return 0;
    }

    // Mover los elementos restantes
    for (int i = encontrado; i < super->totalProductos - 1; i++) {
        super->productos[i] = super->productos[i + 1];
    }

    Producto* temp = (Producto*)realloc(super->productos, 
                                     (super->totalProductos - 1) * sizeof(Producto));
    
    if (super->totalProductos > 1 && temp == NULL) {
        printf("Error: No se pudo redimensionar el array\n");
        return 0;
    }

    super->productos = temp;
    super->totalProductos--;
    return 1;
}

int actualizarStock(Supermercado* super, int codigo, int nuevoStock) {
    if (nuevoStock < 0) {
        printf("Error: El stock no puede ser negativo\n");
        return 0;
    }

    for (int i = 0; i < super->totalProductos; i++) {
        if (super->productos[i].codigo == codigo) {
            super->productos[i].stock = nuevoStock;
            return 1;
        }
    }
    printf("Error: No se encontró el producto con código %d\n", codigo);
    return 0;
}

int actualizarPrecio(Supermercado* super, int codigo, float nuevoPrecio) {
    if (nuevoPrecio <= 0) {
        printf("Error: El precio debe ser mayor que 0\n");
        return 0;
    }

    for (int i = 0; i < super->totalProductos; i++) {
        if (super->productos[i].codigo == codigo) {
            super->productos[i].precio = nuevoPrecio;
            return 1;
        }
    }
    printf("Error: No se encontró el producto con código %d\n", codigo);
    return 0;
} 
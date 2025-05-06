#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_NOMBRE 100

// Estructuras básicas
typedef struct {
    char nombre[MAX_NOMBRE];
    int codigo;
    float precio;
    int stock;
} Producto;

typedef struct {
    float totalVenta;
    int codigoProducto;
    int cantidadVendida;
} Venta;

typedef struct {
    Producto* productos;
    Venta* ventas;
    int totalProductos;
    int totalVentas;
} Supermercado;

// Funciones de inicialización
Supermercado* inicializarSupermercado() {
    Supermercado* super = (Supermercado*)malloc(sizeof(Supermercado));
    if (super == NULL) {
        printf("Error: No se pudo asignar memoria para el supermercado\n");
        exit(1);
    }
    super->productos = NULL;
    super->ventas = NULL;
    super->totalProductos = 0;
    super->totalVentas = 0;
    return super;
}

// Función para inicializar un producto
Producto inicializarProducto(const char* nombre, int codigo, float precio, int stock) {
    Producto p;
    strncpy(p.nombre, nombre, MAX_NOMBRE - 1);
    p.nombre[MAX_NOMBRE - 1] = '\0';
    p.codigo = codigo;
    p.precio = precio;
    p.stock = stock;
    return p;
}

// Función para agregar un producto
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

// Función para eliminar un producto
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

// Función principal para liberar memoria
void liberarMemoria(Supermercado* super) {
    if (super != NULL) {
        if (super->productos != NULL) {
            free(super->productos);
        }
        if (super->ventas != NULL) {
            free(super->ventas);
        }
        free(super);
    }
}

// Funciones de actualización de stock y precios
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

// Funciones de registro de ventas
int registrarVenta(Supermercado* super, int codigo, int cantidad) {
    // Buscar el producto
    int indiceProducto = -1;
    for (int i = 0; i < super->totalProductos; i++) {
        if (super->productos[i].codigo == codigo) {
            indiceProducto = i;
            break;
        }
    }

    if (indiceProducto == -1) {
        printf("Error: Producto no encontrado\n");
        return 0;
    }

    // Verificar stock suficiente
    if (super->productos[indiceProducto].stock < cantidad) {
        printf("Error: Stock insuficiente\n");
        return 0;
    }

    // Crear nueva venta
    Venta* temp = (Venta*)realloc(super->ventas, 
                                 (super->totalVentas + 1) * sizeof(Venta));
    if (temp == NULL) {
        printf("Error: No se pudo registrar la venta\n");
        return 0;
    }

    super->ventas = temp;
    super->ventas[super->totalVentas].codigoProducto = codigo;
    super->ventas[super->totalVentas].cantidadVendida = cantidad;
    super->ventas[super->totalVentas].totalVenta = 
        cantidad * super->productos[indiceProducto].precio;

    // Actualizar stock
    super->productos[indiceProducto].stock -= cantidad;
    super->totalVentas++;

    printf("Venta registrada con éxito\n");
    return 1;
}

// Funciones de búsqueda
void buscarPorNombre(Supermercado* super, const char* nombre) {
    int encontrados = 0;
    printf("\nResultados de búsqueda para '%s':\n", nombre);
    
    for (int i = 0; i < super->totalProductos; i++) {
        if (strstr(super->productos[i].nombre, nombre) != NULL) {
            printf("Código: %d, Nombre: %s, Precio: %.2f, Stock: %d\n",
                   super->productos[i].codigo,
                   super->productos[i].nombre,
                   super->productos[i].precio,
                   super->productos[i].stock);
            encontrados++;
        }
    }
    
    if (encontrados == 0) {
        printf("No se encontraron productos que coincidan con la búsqueda\n");
    }
}

void buscarPorRangoPrecio(Supermercado* super, float minPrecio, float maxPrecio) {
    int encontrados = 0;
    printf("\nProductos entre %.2f y %.2f:\n", minPrecio, maxPrecio);
    
    for (int i = 0; i < super->totalProductos; i++) {
        if (super->productos[i].precio >= minPrecio && 
            super->productos[i].precio <= maxPrecio) {
            printf("Código: %d, Nombre: %s, Precio: %.2f, Stock: %d\n",
                   super->productos[i].codigo,
                   super->productos[i].nombre,
                   super->productos[i].precio,
                   super->productos[i].stock);
            encontrados++;
        }
    }
    
    if (encontrados == 0) {
        printf("No se encontraron productos en ese rango de precios\n");
    }
}

// Funciones de generación de reportes
void generarReporteProductos(Supermercado* super) {
    float valorTotalInventario = 0;
    printf("\n=== REPORTE DE PRODUCTOS ===\n");
    printf("Total de productos: %d\n\n", super->totalProductos);
    
    for (int i = 0; i < super->totalProductos; i++) {
        float valorStock = super->productos[i].precio * super->productos[i].stock;
        printf("Producto #%d:\n", i + 1);
        printf("  Código: %d\n", super->productos[i].codigo);
        printf("  Nombre: %s\n", super->productos[i].nombre);
        printf("  Precio: %.2f\n", super->productos[i].precio);
        printf("  Stock: %d\n", super->productos[i].stock);
        printf("  Valor total en stock: %.2f\n\n", valorStock);
        
        valorTotalInventario += valorStock;
    }
    
    printf("Valor total del inventario: %.2f\n", valorTotalInventario);
    printf("============================\n");
}

void generarReporteVentas(Supermercado* super) {
    float totalVentas = 0;
    printf("\n=== REPORTE DE VENTAS ===\n");
    printf("Total de ventas realizadas: %d\n\n", super->totalVentas);
    
    for (int i = 0; i < super->totalVentas; i++) {
        printf("Venta #%d:\n", i + 1);
        printf("  Código de producto: %d\n", super->ventas[i].codigoProducto);
        printf("  Cantidad vendida: %d\n", super->ventas[i].cantidadVendida);
        printf("  Total de venta: %.2f\n\n", super->ventas[i].totalVenta);
        
        totalVentas += super->ventas[i].totalVenta;
    }
    
    printf("Total general de ventas: %.2f\n", totalVentas);
    printf("=======================\n");
}

// Funciones de manejo de archivos
int guardarInventario(Supermercado* super, const char* nombreArchivo) {
    FILE* archivo = fopen(nombreArchivo, "w");
    if (archivo == NULL) {
        printf("Error: No se pudo abrir el archivo para escritura\n");
        return 0;
    }
    
    // Guardar cantidad total de productos
    fprintf(archivo, "%d\n", super->totalProductos);
    
    // Guardar información de cada producto
    for (int i = 0; i < super->totalProductos; i++) {
        fprintf(archivo, "%d;%s;%.2f;%d\n",
                super->productos[i].codigo,
                super->productos[i].nombre,
                super->productos[i].precio,
                super->productos[i].stock);
    }
    
    fclose(archivo);
    printf("Inventario guardado exitosamente en '%s'\n", nombreArchivo);
    return 1;
}

int cargarInventario(Supermercado* super, const char* nombreArchivo) {
    FILE* archivo = fopen(nombreArchivo, "r");
    if (archivo == NULL) {
        printf("Error: No se pudo abrir el archivo para lectura\n");
        return 0;
    }
    
    // Liberar memoria existente
    if (super->productos != NULL) {
        free(super->productos);
        super->productos = NULL;
        super->totalProductos = 0;
    }
    
    // Leer cantidad total de productos
    int cantidadProductos;
    if (fscanf(archivo, "%d\n", &cantidadProductos) != 1) {
        printf("Error: Formato de archivo inválido\n");
        fclose(archivo);
        return 0;
    }
    
    // Reservar memoria para los productos
    super->productos = (Producto*)malloc(cantidadProductos * sizeof(Producto));
    if (super->productos == NULL) {
        printf("Error: No se pudo asignar memoria para los productos\n");
        fclose(archivo);
        return 0;
    }
    
    // Leer productos
    char linea[256];
    int i = 0;
    while (i < cantidadProductos && fgets(linea, sizeof(linea), archivo)) {
        char nombre[MAX_NOMBRE];
        if (sscanf(linea, "%d;%[^;];%f;%d\n",
                   &super->productos[i].codigo,
                   nombre,
                   &super->productos[i].precio,
                   &super->productos[i].stock) == 4) {
            strncpy(super->productos[i].nombre, nombre, MAX_NOMBRE - 1);
            super->productos[i].nombre[MAX_NOMBRE - 1] = '\0';
            i++;
        }
    }
    
    super->totalProductos = i;
    fclose(archivo);
    
    printf("Inventario cargado exitosamente desde '%s'\n", nombreArchivo);
    printf("Se cargaron %d productos\n", super->totalProductos);
    return 1;
}

void limpiarBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}

void mostrarMenu() {
    printf("\n=== SISTEMA DE GESTIÓN DE SUPERMERCADO ===\n");
    printf("1. Agregar producto\n");
    printf("2. Eliminar producto\n");
    printf("3. Actualizar stock\n");
    printf("4. Actualizar precio\n");
    printf("5. Registrar venta\n");
    printf("6. Buscar por nombre\n");
    printf("7. Buscar por rango de precio\n");
    printf("8. Generar reporte de productos\n");
    printf("9. Generar reporte de ventas\n");
    printf("10. Guardar inventario\n");
    printf("11. Cargar inventario\n");
    printf("0. Salir\n");
    printf("Seleccione una opción: ");
}

int main() {
    Supermercado* super = inicializarSupermercado();
    int opcion;
    char nombreProducto[MAX_NOMBRE];
    int codigo, stock;
    float precio, minPrecio, maxPrecio;
    char nombreArchivo[100];

    do {
        mostrarMenu();
        if (scanf("%d", &opcion) != 1) {
            printf("Error: Entrada inválida\n");
            limpiarBuffer();
            continue;
        }
        limpiarBuffer();

        switch (opcion) {
            case 1: // Agregar producto
                printf("Ingrese nombre del producto: ");
                fgets(nombreProducto, MAX_NOMBRE, stdin);
                nombreProducto[strcspn(nombreProducto, "\n")] = 0;
                
                printf("Ingrese código: ");
                scanf("%d", &codigo);
                printf("Ingrese precio: ");
                scanf("%f", &precio);
                printf("Ingrese stock inicial: ");
                scanf("%d", &stock);
                
                Producto nuevoProducto = inicializarProducto(nombreProducto, codigo, precio, stock);
                if (agregarProducto(super, nuevoProducto)) {
                    printf("Producto agregado exitosamente\n");
                }
                break;

            case 2: // Eliminar producto
                printf("Ingrese código del producto a eliminar: ");
                scanf("%d", &codigo);
                if (eliminarProducto(super, codigo)) {
                    printf("Producto eliminado exitosamente\n");
                }
                break;

            case 3: // Actualizar stock
                printf("Ingrese código del producto: ");
                scanf("%d", &codigo);
                printf("Ingrese nuevo stock: ");
                scanf("%d", &stock);
                actualizarStock(super, codigo, stock);
                break;

            case 4: // Actualizar precio
                printf("Ingrese código del producto: ");
                scanf("%d", &codigo);
                printf("Ingrese nuevo precio: ");
                scanf("%f", &precio);
                actualizarPrecio(super, codigo, precio);
                break;

            case 5: // Registrar venta
                printf("Ingrese código del producto: ");
                scanf("%d", &codigo);
                printf("Ingrese cantidad a vender: ");
                scanf("%d", &stock);
                registrarVenta(super, codigo, stock);
                break;

            case 6: // Buscar por nombre
                printf("Ingrese nombre a buscar: ");
                fgets(nombreProducto, MAX_NOMBRE, stdin);
                nombreProducto[strcspn(nombreProducto, "\n")] = 0;
                buscarPorNombre(super, nombreProducto);
                break;

            case 7: // Buscar por rango de precio
                printf("Ingrese precio mínimo: ");
                scanf("%f", &minPrecio);
                printf("Ingrese precio máximo: ");
                scanf("%f", &maxPrecio);
                buscarPorRangoPrecio(super, minPrecio, maxPrecio);
                break;

            case 8: // Reporte de productos
                generarReporteProductos(super);
                break;

            case 9: // Reporte de ventas
                generarReporteVentas(super);
                break;

            case 10: // Guardar inventario
                printf("Ingrese nombre del archivo para guardar: ");
                scanf("%s", nombreArchivo);
                guardarInventario(super, nombreArchivo);
                break;

            case 11: // Cargar inventario
                printf("Ingrese nombre del archivo para cargar: ");
                scanf("%s", nombreArchivo);
                cargarInventario(super, nombreArchivo);
                break;

            case 0: // Salir
                printf("Gracias por usar el sistema\n");
                break;

            default:
                printf("Opción no válida\n");
        }
    } while (opcion != 0);

    // Liberar memoria antes de salir
    liberarMemoria(super);
    return 0;
}

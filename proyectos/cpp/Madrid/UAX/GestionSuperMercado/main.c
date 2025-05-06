#include "supermercado.h"

/**
 * @brief Maneja los errores de entrada del usuario
 * @param opcion Puntero a la variable donde se almacenará la opción
 * @return 1 si la entrada es válida, 0 en caso contrario
 */
static int manejarErrorEntrada(int* opcion) {
    if (scanf("%d", opcion) != 1) {
        printf("Error: Entrada inválida\n");
        limpiarBuffer();
        return 0;
    }
    limpiarBuffer(); // Limpiar el buffer después de cada scanf
    return 1;
}

/**
 * @brief Función principal del programa
 * @return 0 si el programa finaliza correctamente
 */
int main() {
    Supermercado* nuevoSupermercado = inicializarSupermercado();
    int opcion;
    char nombreProducto[MAX_NOMBRE];
    int codigo, stock;
    float precio, minPrecio, maxPrecio;
    char nombreArchivo[100];

    do {
        mostrarMenu();
        if (!manejarErrorEntrada(&opcion)) {
            continue;
        }

        switch (opcion) {
            case 1: // Agregar producto
                printf("Ingrese nombre del producto: ");
                fgets(nombreProducto, MAX_NOMBRE, stdin);
                nombreProducto[strcspn(nombreProducto, "\n")] = 0;
                
                printf("Ingrese código: ");
                scanf("%d", &codigo);
                limpiarBuffer();
                
                printf("Ingrese precio: ");
                scanf("%f", &precio);
                limpiarBuffer();
                
                printf("Ingrese stock inicial: ");
                scanf("%d", &stock);
                limpiarBuffer();
                
                Producto nuevoProducto = inicializarProducto(nombreProducto, codigo, precio, stock);
                if (agregarProducto(nuevoSupermercado, nuevoProducto)) {
                    printf("Producto agregado exitosamente\n");
                }
                break;

            case 2: // Eliminar producto
                printf("Ingrese código del producto a eliminar: ");
                scanf("%d", &codigo);
                limpiarBuffer();
                if (eliminarProducto(nuevoSupermercado, codigo)) {
                    printf("Producto eliminado exitosamente\n");
                }
                break;

            case 3: // Actualizar stock
                printf("Ingrese código del producto: ");
                scanf("%d", &codigo);
                limpiarBuffer();
                printf("Ingrese nuevo stock: ");
                scanf("%d", &stock);
                limpiarBuffer();
                actualizarStock(nuevoSupermercado, codigo, stock);
                break;

            case 4: // Actualizar precio
                printf("Ingrese código del producto: ");
                scanf("%d", &codigo);
                limpiarBuffer();
                printf("Ingrese nuevo precio: ");
                scanf("%f", &precio);
                limpiarBuffer();
                actualizarPrecio(nuevoSupermercado, codigo, precio);
                break;

            case 5: // Registrar venta
                printf("Ingrese código del producto: ");
                scanf("%d", &codigo);
                limpiarBuffer();
                printf("Ingrese cantidad a vender: ");
                scanf("%d", &stock);
                limpiarBuffer();
                registrarVenta(nuevoSupermercado, codigo, stock);
                break;

            case 6: // Buscar por nombre
                printf("Ingrese nombre a buscar: ");
                fgets(nombreProducto, MAX_NOMBRE, stdin);
                nombreProducto[strcspn(nombreProducto, "\n")] = 0;
                buscarPorNombre(nuevoSupermercado, nombreProducto);
                break;

            case 7: // Buscar por rango de precio
                printf("Ingrese precio mínimo: ");
                scanf("%f", &minPrecio);
                limpiarBuffer();
                printf("Ingrese precio máximo: ");
                scanf("%f", &maxPrecio);
                limpiarBuffer();
                buscarPorRangoPrecio(nuevoSupermercado, minPrecio, maxPrecio);
                break;

            case 8: // Reporte de productos
                generarReporteProductos(nuevoSupermercado);
                break;

            case 9: // Reporte de ventas
                generarReporteVentas(nuevoSupermercado);
                break;

            case 10: // Guardar inventario
                printf("Ingrese nombre del archivo para guardar: ");
                scanf("%s", nombreArchivo);
                limpiarBuffer();
                guardarInventario(nuevoSupermercado, nombreArchivo);
                break;

            case 11: // Cargar inventario
                printf("Ingrese nombre del archivo para cargar: ");
                scanf("%s", nombreArchivo);
                limpiarBuffer();
                cargarInventario(nuevoSupermercado, nombreArchivo);
                break;

            case 0: // Salir
                printf("Gracias por usar el sistema\n");
                break;

            default:
                printf("Opción no válida\n");
        }
    } while (opcion != 0);

    // Liberar memoria antes de salir
    liberarMemoria(nuevoSupermercado);
    return 0;
} 
#include "supermercado.h"

void limpiarBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}

/**
 * @brief Muestra una línea separadora en el menú
 * @param caracter Carácter a utilizar para la línea
 * @param longitud Longitud de la línea
 */
static void mostrarSeparador(char caracter, int longitud) {
    for (int i = 0; i < longitud; i++) {
        printf("%c", caracter);
    }
    printf("\n");
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
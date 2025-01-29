#include "supermercado.h"

/**
 * @brief Valida los datos de una venta
 * @param codigo Código del producto
 * @param cantidad Cantidad a vender
 * @return 1 si los datos son válidos, 0 en caso contrario
 */
static int validarDatosVenta(int codigo, int cantidad) {
    if (codigo <= 0) {
        printf("Error: Código de producto inválido\n");
        return 0;
    }
    if (cantidad <= 0) {
        printf("Error: La cantidad debe ser positiva\n");
        return 0;
    }
    return 1;
}

/**
 * @brief Registra una nueva venta en el sistema
 * @param super Puntero al supermercado
 * @param codigo Código del producto a vender
 * @param cantidad Cantidad de unidades a vender
 * @return 1 si la venta se registró exitosamente, 0 en caso contrario
 */
int registrarVenta(Supermercado* super, int codigo, int cantidad) {
    if (!validarDatosVenta(codigo, cantidad)) {
        return 0;
    }

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
        printf("Error: Stock insuficiente. Stock actual: %d\n", 
               super->productos[indiceProducto].stock);
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
    printf("Total de la venta: %.2f\n", 
           super->ventas[super->totalVentas - 1].totalVenta);
    return 1;
}

/**
 * @brief Genera un reporte detallado de todas las ventas realizadas
 * @param super Puntero al supermercado
 */
void generarReporteVentas(Supermercado* super) {
    if (super->totalVentas == 0) {
        printf("\n=== REPORTE DE VENTAS ===\n");
        printf("No hay ventas registradas\n");
        printf("=======================\n");
        return;
    }

    float totalVentas = 0;
    printf("\n=== REPORTE DE VENTAS ===\n");
    printf("Total de ventas realizadas: %d\n\n", super->totalVentas);
    
    // Mostrar cada venta con detalles del producto
    for (int i = 0; i < super->totalVentas; i++) {
        printf("Venta #%d:\n", i + 1);
        
        // Buscar información del producto
        for (int j = 0; j < super->totalProductos; j++) {
            if (super->productos[j].codigo == super->ventas[i].codigoProducto) {
                printf("  Producto: %s\n", super->productos[j].nombre);
                printf("  Precio unitario: %.2f\n", super->productos[j].precio);
                break;
            }
        }
        
        printf("  Código de producto: %d\n", super->ventas[i].codigoProducto);
        printf("  Cantidad vendida: %d\n", super->ventas[i].cantidadVendida);
        printf("  Total de venta: %.2f\n\n", super->ventas[i].totalVenta);
        
        totalVentas += super->ventas[i].totalVenta;
    }
    
    // Mostrar estadísticas generales
    printf("=== RESUMEN ===\n");
    printf("Total general de ventas: %.2f\n", totalVentas);
    printf("Promedio por venta: %.2f\n", totalVentas / super->totalVentas);
    printf("=======================\n");
} 
#include "supermercado.h"

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
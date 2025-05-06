#include "supermercado.h"

/**
 * @brief Valida el nombre del archivo
 * @param nombreArchivo Nombre del archivo a validar
 * @return 1 si es válido, 0 en caso contrario
 */
static int validarNombreArchivo(const char* nombreArchivo) {
    if (nombreArchivo == NULL || strlen(nombreArchivo) == 0) {
        printf("Error: Nombre de archivo inválido\n");
        return 0;
    }
    return 1;
}

int guardarInventario(Supermercado* super, const char* nombreArchivo) {
    if (!validarNombreArchivo(nombreArchivo)) {
        return 0;
    }
    
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
    if (!validarNombreArchivo(nombreArchivo)) {
        return 0;
    }
    
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
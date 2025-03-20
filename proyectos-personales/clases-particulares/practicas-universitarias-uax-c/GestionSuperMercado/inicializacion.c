#include "supermercado.h"

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
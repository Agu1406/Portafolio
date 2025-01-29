#ifndef SUPERMERCADO_H
#define SUPERMERCADO_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/**
 * @brief Tamaño máximo para el nombre de los productos
 */
#define MAX_NOMBRE 100

/**
 * @brief Estructura que representa un producto en el supermercado
 */
typedef struct {
    char nombre[MAX_NOMBRE];  ///< Nombre del producto
    int codigo;              ///< Código único del producto
    float precio;           ///< Precio unitario del producto
    int stock;             ///< Cantidad disponible en inventario
} Producto;

/**
 * @brief Estructura que representa una venta realizada
 */
typedef struct {
    float totalVenta;      ///< Monto total de la venta
    int codigoProducto;    ///< Código del producto vendido
    int cantidadVendida;   ///< Cantidad de unidades vendidas
} Venta;

/**
 * @brief Estructura principal que gestiona el supermercado
 */
typedef struct {
    Producto* productos;    ///< Array dinámico de productos
    Venta* ventas;         ///< Array dinámico de ventas
    int totalProductos;    ///< Cantidad total de productos
    int totalVentas;       ///< Cantidad total de ventas
} Supermercado;

// Prototipos de funciones con documentación

/**
 * @brief Inicializa una nueva instancia de Supermercado
 * @return Puntero a la nueva instancia de Supermercado
 */
Supermercado* inicializarSupermercado();

/**
 * @brief Libera la memoria utilizada por el supermercado
 * @param super Puntero al supermercado a liberar
 */
void liberarMemoria(Supermercado* super);

// Gestión de productos
Producto inicializarProducto(const char* nombre, int codigo, float precio, int stock);
int agregarProducto(Supermercado* super, Producto producto);
int eliminarProducto(Supermercado* super, int codigo);
int actualizarStock(Supermercado* super, int codigo, int nuevoStock);
int actualizarPrecio(Supermercado* super, int codigo, float nuevoPrecio);

// Gestión de ventas
int registrarVenta(Supermercado* super, int codigo, int cantidad);

// Búsquedas
void buscarPorNombre(Supermercado* super, const char* nombre);
void buscarPorRangoPrecio(Supermercado* super, float minPrecio, float maxPrecio);

// Reportes
void generarReporteProductos(Supermercado* super);
void generarReporteVentas(Supermercado* super);

// Manejo de archivos
int guardarInventario(Supermercado* super, const char* nombreArchivo);
int cargarInventario(Supermercado* super, const char* nombreArchivo);

// Utilidades
void limpiarBuffer();
void mostrarMenu();

#endif 
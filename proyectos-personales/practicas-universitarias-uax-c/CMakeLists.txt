cmake_minimum_required(VERSION 3.26)
project(practicas_universitarias_uax_c C)

set(CMAKE_C_STANDARD 11)

# Ejecutable para el Juego de Conquistas
add_executable(JuegoDeConquistas
        JuegoDeConquistas/main.c)

# Ejecutable para la Gestión de Supermercado
add_executable(GestionSuperMercado
        GestionSuperMercado/main.c
        GestionSuperMercado/inicializacion.c
        GestionSuperMercado/productos.c
        GestionSuperMercado/ventas.c
        GestionSuperMercado/reportes.c
        GestionSuperMercado/archivos.c
        GestionSuperMercado/interfaz.c)

# Ejecutable para la Gestión de Supermercado all in one.
add_executable(GestionSuperMercadoV2
        GestionSuperMercado/GestionSuperMercado.c)
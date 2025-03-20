#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define VACIO 'V'
#define JUGADOR1 '1'
#define JUGADOR2 '2'

/*
 * [V][V][V][V][V][V]
 * [1][2][1][2][2][2]
 * [1][1][2][1][2][1}
 */

// LLAMADA DE LAS FUNCIONES DEL PROGRAMA

// Función que permite generar un mapa bidimensional.
char** crearMapa(int filas, int columnas);
// Función que permite crear los ejercitos.
void crearEjercitos(int** ejercito, int filas, int columnas);
// Función que permite desplazar los ejercitos
void moverEjercito(int* ejercito, char** mapa, int origen, int destino, int cantidad, int jugador, int columnas, int filas);
// Función que permite que dos ejercitos peleen.
void combatir (int* ejercito1, int* ejercito2);
// Función que permite ampliar el mapa.
void ampliarMapa (char*** mapa, int* filas, int* columnas);
// Función que cada vez que es invocada imprime el mapa
void mostrarMapa (char** mapa, int filas, int columnas);
// Función "jugar" que permite iniciar el juego.
void jugar(int* ejercito1, int* ejercito2, char** mapa, int filas, int columnas);
// Función para liberar memoria
void liberarMemoria(char** mapa, int* ejercito1, int* ejercito2, int filas);

// MAIN DEL PROGRAMA (JUEGO)

int main(void) {
    int filas, columnas;

    printf("De cuantas filas el tablero?: ");
    if (scanf_s("%d", &filas) != 1) return 1;
    printf("De cuantas columnas el tablero?: ");
    if (scanf_s("%d", &columnas) != 1) return 1;

    char** mapa = crearMapa(filas, columnas);
    if (!mapa) {
        printf("Error: No se pudo crear el mapa\n");
        return 1;
    }

    int* ejercito1 = (int*)calloc(filas*columnas, sizeof(int));
    int* ejercito2 = (int*)calloc(filas*columnas, sizeof(int));

    if (!ejercito1 || !ejercito2) {
        liberarMemoria(mapa, ejercito1, ejercito2, filas);
        printf("Error: No se pudieron crear los ejércitos\n");
        return 1;
    }

    // Establecer posiciones iniciales de los jugadores con 5 soldados
    ejercito1[0] = 5;  // Jugador 1 inicia en la posición 0 con 5 soldados
    ejercito2[(filas * columnas) - 1] = 5;  // Jugador 2 inicia en la última posición con 5 soldados

    // También necesitamos marcar estas posiciones en el mapa
    mapa[0][0] = JUGADOR1;  // Marca la posición inicial del jugador 1
    mapa[filas-1][columnas-1] = JUGADOR2;  // Marca la posición inicial del jugador 2

    // Empezamos a jugar el juego
    jugar(ejercito1, ejercito2, mapa, filas, columnas);

    // Liberamos la memoria al finalizar
    liberarMemoria(mapa, ejercito1, ejercito2, filas);

    return 0;
}

// CÓDIGO DE LAS FUNCIONES

void mostrarPosicionesJugador(int* ejercito, int turno, int totalCasillas) {
    printf("\nPosiciones del Jugador %d:\n", turno);
    for (int i = 0; i < totalCasillas; i++) {
        if (ejercito[i] > 0) {
            printf("Posicion %d: %d soldados\n", i, ejercito[i]);
        }
    }
}

void jugar(int* ejercito1, int* ejercito2, char** mapa, int filas, int columnas) {
    if (!ejercito1 || !ejercito2 || !mapa) {
        printf("Error: Punteros invalidos\n");
        return;
    }

    int turno = 1;
    int totalCasillas = filas * columnas;

    while (1) {
        mostrarMapa(mapa, filas, columnas);
        printf("\n=== Turno del jugador N.%d ===\n", turno);

        int* ejercitoActual = (turno == 1) ? ejercito1 : ejercito2;
        mostrarPosicionesJugador(ejercitoActual, turno, totalCasillas);

        // Mostrar movimientos posibles
        printf("\nMovimientos posibles:\n");
        for (int pos = 0; pos < totalCasillas; pos++) {
            if (ejercitoActual[pos] > 0) {
                int fila = pos / columnas;
                int col = pos % columnas;

                printf("Desde posicion %d (%d soldados) puedes mover a:\n", pos, ejercitoActual[pos]);

                if (fila > 0)
                    printf("- Posicion %d (arriba)\n", pos - columnas);
                if (fila < filas - 1)
                    printf("- Posicion %d (abajo)\n", pos + columnas);
                if (col > 0)
                    printf("- Posicion %d (izquierda)\n", pos - 1);
                if (col < columnas - 1)
                    printf("- Posicion %d (derecha)\n", pos + 1);
            }
        }

        int origen = -1, destino = -1, cantidad = -1;
        int entradaValida = 0;

        while (!entradaValida) {
            printf("\nIngrese origen, destino y cantidad de soldados (ejemplo: 0 1 5)\n");
            printf("O ingrese -1 para terminar: ");

            if (scanf_s("%d %d %d", &origen, &destino, &cantidad) != 3) {
                // Limpiar el buffer de entrada
                while (getchar() != '\n');
                printf("Error: Entrada invalida. Por favor ingrese tres numeros.\n");
                continue;
            }

            // Verificar si el usuario quiere terminar
            if (origen == -1 || destino == -1) {
                return;
            }

            // Validar origen
            if (origen < 0 || origen >= totalCasillas || ejercitoActual[origen] <= 0) {
                printf("Error: Posicion de origen invalida o sin soldados.\n");
                continue;
            }

            // Validar destino
            int filaOrigen = origen / columnas;
            int colOrigen = origen % columnas;
            int filaDestino = destino / columnas;
            int colDestino = destino % columnas;

            if (destino < 0 || destino >= totalCasillas ||
                (abs(filaOrigen - filaDestino) + abs(colOrigen - colDestino) != 1)) {
                printf("Error: Solo puede moverse a casillas adyacentes (arriba, abajo, izquierda o derecha).\n");
                continue;
            }

            // Validar cantidad
            if (cantidad <= 0 || cantidad > ejercitoActual[origen]) {
                printf("Error: Cantidad invalida. Debe ser entre 1 y %d soldados.\n", ejercitoActual[origen]);
                continue;
            }

            entradaValida = 1;
        }

        if (mapa[destino / columnas][destino % columnas] != VACIO) {
            char territorioOcupado = mapa[destino / columnas][destino % columnas];
            char jugadorActual = (turno == 1) ? JUGADOR1 : JUGADOR2;

            if (territorioOcupado == jugadorActual) {
                // Si el territorio pertenece al mismo jugador, simplemente movemos las tropas
                moverEjercito(ejercitoActual, mapa, origen, destino, cantidad, turno, columnas, filas);
            } else {
                printf("¡Territorio enemigo! Hora de pelear.\n");
                int* ejercitoAtacante = (turno == 1) ? ejercito1 : ejercito2;
                int* ejercitoDefensor = (turno == 1) ? ejercito2 : ejercito1;

                combatir(ejercitoAtacante, ejercitoDefensor);

                if (*ejercitoDefensor == 0) {
                    printf("El jugador %d ha conquistado el territorio!\n", turno);
                    moverEjercito(ejercitoActual, mapa, origen, destino, cantidad, turno, columnas, filas);
                }
            }
        } else {
            moverEjercito(ejercitoActual, mapa, origen, destino, cantidad, turno, columnas, filas);
        }

        turno = (turno == 1) ? 2 : 1;

        char respuesta;
        do {
            printf("Quereis ampliar el mapa? ('s' de si o 'n' de no): ");
            while (getchar() != '\n'); // Limpiar buffer antes de leer
            if (scanf_s(" %c", &respuesta, 1) != 1) {
                printf("Error: Entrada invalida.\n");
                continue;
            }

            if (respuesta != 's' && respuesta != 'n') {
                printf("Error: Solo se acepta 's' o 'n' como respuesta.\n");
            }
        } while (respuesta != 's' && respuesta != 'n');

        if (respuesta == 's') {
            int viejoTamano = filas * columnas;
            int* viejoEjercito1 = ejercito1;
            int* viejoEjercito2 = ejercito2;

            ampliarMapa(&mapa, &filas, &columnas);
            if (!mapa) {
                printf("Error: Fallo al ampliar el mapa\n");
                return;
            }

            int nuevoTamano = filas * columnas;
            int* nuevoEjercito1 = (int*)calloc(nuevoTamano, sizeof(int));
            int* nuevoEjercito2 = (int*)calloc(nuevoTamano, sizeof(int));

            if (!nuevoEjercito1 || !nuevoEjercito2) {
                free(nuevoEjercito1); // Es seguro llamar free con NULL
                free(nuevoEjercito2);
                printf("Error: No se pudo ampliar los ejércitos\n");
                return;
            }

            memcpy(nuevoEjercito1, viejoEjercito1, viejoTamano * sizeof(int));
            memcpy(nuevoEjercito2, viejoEjercito2, viejoTamano * sizeof(int));

            free(viejoEjercito1);
            free(viejoEjercito2);
            ejercito1 = nuevoEjercito1;
            ejercito2 = nuevoEjercito2;
            totalCasillas = nuevoTamano;
        }

        // Recibir soldados solo si no se ha alcanzado el límite
        int* ejercitoSiguiente = (turno == 1) ? ejercito1 : ejercito2;
        int totalSoldados = 0;
        for (int i = 0; i < totalCasillas; i++) {
            totalSoldados += ejercitoSiguiente[i];
        }

        if (totalSoldados < totalCasillas) {
            if (turno == 1) {
                ejercito1[0] += 2;
            } else {
                ejercito2[(filas * columnas) - 1] += 2;
            }
        }
    }
}

void mostrarMapa (char** mapa, int filas, int columnas) {
    printf("Mapa actual del juego: \n");
    // Recorre una por una todas las filas del tablero.
    for (int fila = 0; fila < filas; fila++) {
        // Recorre una por una todas las columnas de "X" fila.
        for (int columna = 0; columna < columnas; columna++) {
            // Imprime una por una todas las casillas de "X" fila.
            printf("[%c] ", mapa[fila][columna]);
        }
        // Salto de linea entre la impresión de cada fila.
        printf("\n");
    }
}

/**
 * Función que permite modificar el tamaño actual del mapa haciendo
 * el mismo más grande, preservando el contenido anterior y añadiendo
 * nuevas casillas vacías.
 * @param mapa Puntero triple para modificar el mapa original
 * @param filas Puntero a las filas actuales
 * @param columnas Puntero a las columnas actuales
 */
void ampliarMapa(char*** mapa, int* filas, int* columnas) {
    int nuevasFilas, nuevasColumnas;
    printf("¿Cuantas filas totales desea tener? (Actual: %d): ", *filas);
    if (scanf("%d", &nuevasFilas) != 1) {
        printf("Error: Entrada inválida\n");
        return;
    }
    printf("¿Cuantas columnas totales desea tener? (Actual: %d): ", *columnas);
    if (scanf("%d", &nuevasColumnas) != 1) {
        printf("Error: Entrada inválida\n");
        return;
    }

    if (nuevasFilas < *filas || nuevasColumnas < *columnas) {
        printf("Error: Las nuevas dimensiones deben ser mayores que las actuales.\n");
        return;
    }

    // Crear nuevo mapa con las dimensiones ampliadas
    char** nuevoMapa = (char**)malloc(nuevasFilas * sizeof(char*));
    if (!nuevoMapa) {
        printf("Error: No se pudo asignar memoria para el nuevo mapa\n");
        return;
    }

    for (int i = 0; i < nuevasFilas; i++) {
        nuevoMapa[i] = (char*)malloc(nuevasColumnas * sizeof(char));
        if (!nuevoMapa[i]) {
            // Liberar memoria ya asignada si hay error
            for (int j = 0; j < i; j++) {
                free(nuevoMapa[j]);
            }
            free(nuevoMapa);
            printf("Error: No se pudo asignar memoria para las filas\n");
            return;
        }
        // Inicializar todas las posiciones como VACIO
        for (int j = 0; j < nuevasColumnas; j++) {
            nuevoMapa[i][j] = VACIO;
        }
    }

    // Copiar el contenido existente manteniendo las posiciones originales
    for (int i = 0; i < *filas; i++) {
        for (int j = 0; j < *columnas; j++) {
            nuevoMapa[i][j] = (*mapa)[i][j];
        }
    }

    // Liberar el mapa antiguo
    for (int i = 0; i < *filas; i++) {
        free((*mapa)[i]);
    }
    free(*mapa);

    // Actualizar el mapa y dimensiones
    *mapa = nuevoMapa;
    *filas = nuevasFilas;
    *columnas = nuevasColumnas;

    printf("¡Mapa ampliado con éxito!\n");
    // Mostrar el mapa actualizado
    mostrarMapa(*mapa, *filas, *columnas);
}

/**
 * Función que permite simular el combate de dos ejercitos
 * cuando colisionan en un mismo territorio, gana el
 * ejercito que tenga más soldados asuminedo como perdida
 * el tamaño del ejercito enemigo.
 * @param ejercito1
 * @param ejercito2
 */
void combatir (int* ejercito1, int* ejercito2) {
    // Si el ejercito del jugador 1 es más grande ocurre esto.
    if (*ejercito1 > *ejercito2) {
        printf("¡Ha ganado el jugador 1!");
        *ejercito1 -= *ejercito2;
        *ejercito2 = 0;
    }
    // Si en su lugar, el ejercito 2 es más grande, ocurre esto.
    else {
        printf("¡Ha ganado el jugador 2!");
        *ejercito2 -= *ejercito1;
        *ejercito1 = 0;
    }
}

/**
 * Esta función permite desplzar el ejercito de un jugador
 * de forma horizontal o vertical validando que el movimiento
 * sea posible calculando los indices de filas y columnas
 * usando el tamaño real del mapa como base y las posiciones
 * iniciales y destino del movimiento.
 * @param ejercito del jugador que se intenta desplazar.
 * @param mapa actualizado del juego.
 * @param origen del ejercito que se quiere mover.
 * @param destino hacía donde se quiere mover.
 * @param cantidad de soldados que desea mover.
 * @param jugador que está intentando mover.
 * @param columnas que reflejan el tamaño del mapa.
 * @param filas que reflejan el tamaño del mapa.
 */
void moverEjercito(int* ejercito, char** mapa, int origen, int destino, int cantidad, int jugador, int columnas, int filas) {
    /**
     * La primera parte del movimiento es calcular los indíces que permiten
     * saber en que posición exacta colocar los ejercitos, para ello usamos
     * divisiones entre la fila y columna de origen del ejercito y la fila
     * y columna de destino del ejercito.
     */
    int filaOrigen = origen / columnas;
    int columnaOrigen = origen % columnas;
    int filaDestino = destino / columnas;
    int columnaDestino = destino % columnas;

    // Controla si el movimiento es horizontal o vertical (y si es valido)
    if ((filaOrigen == filaDestino && abs(columnaOrigen - columnaDestino) == 1) ||
        columnaOrigen == columnaDestino && abs(filaOrigen - filaDestino) == 1) {
        // Verificar si la cantidad que se desaa mover es posible
        if (ejercito[origen] >= cantidad) {
            // Restar del origen la cantidad movida
            ejercito[origen] -= cantidad;
            // Sumar al destino el ejercito movido.
            ejercito[destino] += cantidad;
            // Llenar con "1" o "2" s dependiendo de que jugador está moviendo el ejercito.
            mapa[filaDestino][columnaDestino] = (jugador == 1) ? JUGADOR1 : JUGADOR2;
            // Avisar por pantalla del movimiento exitoso de ejercito.
            printf("Se ha movido %d de soldados de %d a %d.\n", cantidad, origen, destino);
        }
        // Si la cantidad que se deseba mover no era posible ocurre el "else".
        else {
            printf("No hay suficientes soldados para ser movidos al destino");
        }
    // Si el movimiento no era horizontal o vertical (o valido) corrue el "else".
    } else {
        printf("Movimiento no valido, solo se puede movir a espacios adyacentes (horizontales o verticales)");
    }
}


/**
 * Función que permite inicializar los ejercitos de los jugadores
 * con el mismo tamaño del mapa (sin excederse) guardando en
 * cada una de las posiciones del ejercito el valor "0"
 * inicialmente.
 * @param ejercito puntero doble que almacena un ejercito.
 * @param filas la cantidad de filas que tiene el mapa.
 * @param columnas la cantidad de columnas que tiene el mapa.
 */
void crearEjercitos(int** ejercito, int filas, int columnas) {
    for (int posicion = 0; posicion < filas * columnas; posicion++) {
        (*ejercito)[posicion] = 0;
    }
}

/**
 * Función que permite generar un mapa bidimensional
 * de cáracteres que tienen como valor inicial "V"
 * de vacio.
 * @param filas que deberá tener el mapa.
 * @param columnas que deberá tener el mapa.
 * @return el mapa lleno de filas y columnas vacias.
 */
char** crearMapa(int filas, int columnas) {
    // Generamos tantas filas como el usuario indique en el mapa
    char** mapa = (char**) malloc(filas * sizeof(char*));
    /**
     * Recorremos una por una todas las filas del mapa llenado
     * cada una de ella de la cantidad de columnas que se nos
     * hayan dicho.
     */
    for (int fila = 0; fila < filas; fila++) {
        // Va llenando de columnas la "fila" en la que el bucle se encuentre.
        mapa[fila] = (char*) malloc (columnas * sizeof(char));
        // Va llenando el mapa de filas y columnas con el valor VACIO "V".
        for (int columna = 0; columna < columnas; columna++) {
            mapa[fila][columna] = VACIO;
        }
    }
    // Devuelve el mapa lleno de filas y columnas vacias ("V").
    return mapa;
}

void liberarMemoria(char** mapa, int* ejercito1, int* ejercito2, int filas) {
    if (mapa) {
        for (int i = 0; i < filas; i++) {
            free(mapa[i]);
        }
        free(mapa);
    }
    free(ejercito1);
    free(ejercito2);
}
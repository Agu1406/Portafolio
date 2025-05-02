package sistema_juego_ajedrez;

import sistema_juego_ajedrez.ejercicio3.PartidaAjedrez;
import sistema_juego_ajedrez.ejercicio3.Peon;
import sistema_juego_ajedrez.ejercicio3.Pieza;

import java.util.Scanner;

public class Main {
    private static PartidaAjedrez partida;
    private static Scanner scanner;

    public static void main(String[] args) {
        partida = new PartidaAjedrez();
        scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    iniciarNuevaPartida();
                    break;
                case 2:
                    realizarMovimiento();
                    break;
                case 3:
                    mostrarTablero();
                    break;
                case 4:
                    mostrarMovimientosRealizados();
                    break;
                case 5:
                    consultarPosicion();
                    break;
                case 6:
                    mostrarEstadoPartida();
                    break;
                case 0:
                    salir = true;
                    System.out.println("¡Gracias por jugar!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ DEL JUEGO DE AJEDREZ ===");
        System.out.println("1. Iniciar nueva partida");
        System.out.println("2. Realizar movimiento");
        System.out.println("3. Mostrar tablero");
        System.out.println("4. Mostrar movimientos realizados");
        System.out.println("5. Consultar posición");
        System.out.println("6. Mostrar estado de la partida");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void iniciarNuevaPartida() {
        System.out.print("Nombre del jugador con piezas blancas: ");
        String jugadorBlancas = scanner.nextLine();
        System.out.print("Nombre del jugador con piezas negras: ");
        String jugadorNegras = scanner.nextLine();
        
        partida.iniciarPartida(jugadorBlancas, jugadorNegras);
        System.out.println("\nPartida iniciada. Estado inicial del tablero:");
        partida.mostrarTablero();
    }

    private static void realizarMovimiento() {
        System.out.println("Ingrese el movimiento en formato 'e2-e4': ");
        String movimiento = scanner.nextLine();
        
        if (partida.realizarMovimiento(movimiento)) {
            System.out.println("Movimiento realizado correctamente.");
            partida.mostrarTablero();
        } else {
            System.out.println("Movimiento no válido. Intente de nuevo.");
        }
    }

    private static void mostrarTablero() {
        partida.mostrarTablero();
    }

    private static void mostrarMovimientosRealizados() {
        System.out.println("\nMovimientos realizados en la partida:");
        for (String movimiento : partida.obtenerMovimientos()) {
            System.out.println(movimiento);
        }
    }

    private static void consultarPosicion() {
        System.out.print("Ingrese la posición a consultar (ejemplo: e4): ");
        String posicion = scanner.nextLine();
        Pieza pieza = partida.obtenerPiezaEnPosicion(posicion);
        
        if (pieza != null) {
            System.out.println("Pieza en " + posicion + ": " + 
                (pieza instanceof Peon ? "Peón" : "Torre") + " " + pieza.getColor());
            System.out.println("Movimientos posibles desde esta posición:");
            for (String mov : partida.obtenerMovimientosPosibles(posicion)) {
                System.out.println("- " + mov);
            }
        } else {
            System.out.println("No hay ninguna pieza en esa posición.");
        }
    }

    private static void mostrarEstadoPartida() {
        if (partida == null || partida.obtenerMovimientos().isEmpty()) {
            System.out.println("\nNo hay ninguna partida en curso. Por favor, inicie una nueva partida.");
            return;
        }
        
        System.out.println("\nEstado actual de la partida:");
        System.out.println("¿Jaque mate? " + partida.esJaqueMate());
        System.out.println("¿Empate? " + partida.esEmpate());
    }
} 
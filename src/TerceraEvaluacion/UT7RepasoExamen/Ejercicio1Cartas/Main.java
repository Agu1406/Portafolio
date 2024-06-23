package TerceraEvaluacion.UT7RepasoExamen.Ejercicio1Cartas;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // En este ArrayList están guardadas todas las cartas.
        ArrayList <Cartas> cartasIniciales = new ArrayList<>();

        // En este ArrayList están las cartas elegidas por el jugador.
        ArrayList <Cartas> cartasElegidas = new ArrayList<>();

        // Método que crea todas las cartas y las guarda.
        crearCartas(cartasIniciales);

        // Método que permite elegir una carta de cartasIniciales, borrarla de ahí y guardarla en cartasElegidas
        elegirCarta(cartasIniciales, cartasElegidas);
    }

    private static void elegirCarta(ArrayList<Cartas> cartasIniciales, ArrayList<Cartas> cartasElegidas) {
        Scanner teclado = new Scanner(System.in);
        int numElegido;
        int paloElegido;
        try {
            System.out.println
                    ("Dime el palo (Opciones validas: [1] Diamantes [2] Picas [3] Corazones [4] Tréboles");

            paloElegido = teclado.nextInt();

            // Resto uno porque en las posiciones son "0, 1, 2 y 3" y no "1, 2, 3 y 4".
            paloElegido--;

            System.out.println("Dime el número de la carta (Opciones validas: números del 1 al 13");

            numElegido = teclado.nextInt();

            Cartas cartaElegida = new Cartas(paloElegido, numElegido);

            boolean encontrada = false; // Si encuentra la carta, se vuelve true.

            // Clona una por una todas las cartas Iniciales y la compara con la elegida.
            for (Cartas cartaEspejo : cartasIniciales) {
                // Si la elegida está en iniciales, la borra de ahí y la guarda en "elegidas".
                if (cartaEspejo.equals(cartaElegida)) {
                    cartasIniciales.remove(cartaEspejo);
                    cartasElegidas.add(cartaEspejo);
                    encontrada = true;
                }
            }

            if (!encontrada) {
                System.out.println("¡Lo siento! Esa carta no está en las cartas iniciales.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            teclado.next();
        }
    }
    private static void crearCartas(ArrayList<Cartas> cartasIniciales) {
        int num; // Controla que "número" tienen las cartas.
        int palo = 0; // Controla que "palo" de cartas estamos creando.

        // Es el primer bucle, controla que "palo" de cartas se está creando en el momento.
        while (palo < 4) {

            num = 1; // Controla cuál es la primera carta que se crea.
            // Crea 13 cartas del mismo palo, sale del bucle, pasa al siguiente palo.
            while (num < 14) {
                // Crea una nueva carta con el número, el palo y el color correcto.
                Cartas nuevaCarta = new Cartas(num, palo);
                // Incrementa la variable para crear la siguiente carta.
                num++;
                // Agrega la carta al ArrayList
                cartasIniciales.add(nuevaCarta);
            }
            // Creadas las 13 cartas de un mismo "palo", pasa al siguiente "palo".
            palo++;
        }
    }
}

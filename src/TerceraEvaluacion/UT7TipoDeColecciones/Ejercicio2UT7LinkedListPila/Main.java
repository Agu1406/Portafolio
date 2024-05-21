package TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio2UT7LinkedListPila;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * <h1 style="text-align: center;">Main</h1>
 * <br>
 * <p style="text-align: justify;">
 * Esta clase representa el programa principal que gestiona una cola de clientes utilizando una LinkedList.
 * Permite añadir clientes a la cola, consultar quién es el próximo en ser atendido, remover clientes de la cola y salir del programa.
 * </p>
 *
 * @since 21/10/23
 * @version 1.0
 * @author Agu1406 (Agustín)
 */
public class Main {

    /**
     * <h2 style="text-align: center;">main</h2>
     * <br>
     * <p style="text-align: justify;">
     * Método principal que inicia el programa.
     * </p>
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        LinkedList<Persona> colaDeClientes = new LinkedList<>();
        int opcionMenu;

        // Bucle do-while para el menú de opciones disponibles.
        do {
            mostrarMenu(); // Método que imprime en pantalla las opciones del menú.
            System.out.print("Opción: "); // Mensaje estético para introducir la opción.
            opcionMenu = teclado.nextInt(); // Solicitud de la opción por teclado.
            elegirOpcion(opcionMenu, colaDeClientes);

        } while (opcionMenu != 4);
    }

    /**
     * <h2 style="text-align: center;">elegirOpcion</h2>
     * <br>
     * <p style="text-align: justify;">
     * Método que ejecuta la opción seleccionada por el usuario.
     * </p>
     *
     * @param opcionMenu     La opción seleccionada por el usuario.
     * @param colaDeClientes La cola de clientes.
     */
    private static void elegirOpcion(int opcionMenu, LinkedList<Persona> colaDeClientes) {
        boolean clienteEstaEnCola;
        switch (opcionMenu) {
            case 1:
                addPersonaCola(colaDeClientes);
                break;
            case 2:
                Persona clienteSacado = removePersonaCola(colaDeClientes);
                System.out.println("El cliente N.º" + clienteSacado.getCODCLIENTE() + "ya ha sido atendido.");
                break;
            case 3:
                clienteEstaEnCola = buscarCliente(colaDeClientes);
                String respuestaPersonalizada = clienteEstaEnCola ? "Si, esta en cola" : "No esta en cola";
                System.out.println("El cliente" + respuestaPersonalizada);
                break;
            case 4:
                aQueClienteLeToca(colaDeClientes);
                break;
            case 5:
                System.out.println("¡Gracias! Hasta luego.");
                break;
            default:
                System.out.println("¡¿Pero cómo has llegado aquí?!");
                break;
        }
    }

    private static boolean buscarCliente(LinkedList<Persona> colaDeClientes) {
        Scanner teclado = new Scanner(System.in);
        boolean encontrado = false;
        int numCola;

        System.out.println("Actualmente hay: " + colaDeClientes.size() + " personas en la cola");
        System.out.println("¿Que posición de la cola quieres consultar?");
        numCola = teclado.nextInt();

        Persona personaQueBusco = new Persona(numCola);


        Iterator <Persona> buscador = colaDeClientes.iterator();
        while (buscador.hasNext()) {
            encontrado = buscador.next().equals(personaQueBusco);
        }

        return  encontrado;
    }

    /**
     * <h2 style="text-align: center;">mostrarMenu</h2>
     * <br>
     * <p style="text-align: justify;">
     * Método que muestra el menú de opciones disponibles.
     * </p>
     */
    private static void mostrarMenu() {
        System.out.printf(
                "¡Opciones del menú disponibles! %n" +
                        "1.º - Añadir persona a la cola. %n" +
                        "2.º - Remover persona de la cola. %n" +
                        "3.º - Buscar un cliente en la cola. %n" +
                        "4.º - Consultar a quién le toca el turno. %n" +
                        "5.º - Salir del programa.%n");
    }

    /**
     * <h2 style="text-align: center;">aQueClienteLeToca</h2>
     * <br>
     * <p style="text-align: justify;">
     * Método que consulta a quién le toca ser atendido.
     * </p>
     *
     * @param colaDeClientes La cola de clientes.
     */
    private static void aQueClienteLeToca(LinkedList <Persona> colaDeClientes) {
        Persona clienteTurno = colaDeClientes.peek();
        if (clienteTurno != null) {
            System.out.println("Es el turno del cliente N.º" + clienteTurno.getCODCLIENTE());
        }
    }

    /**
     * <h2 style="text-align: center;">removePersonaCola</h2>
     * <br>
     * <p style="text-align: justify;">
     * Método que remueve a una persona de la cola.
     * </p>
     *
     * @param colaDeClientes La cola de clientes.
     * @return La persona removida de la cola.
     */
    private static Persona removePersonaCola(LinkedList<Persona> colaDeClientes) {
        /* La diferencia entre cola y pila es que una cola es de frente hacia atrás o
        * de lado a lado, ejemplo: [0][1][2][3][4], pero una pila se entiende mejor
        * si se visualiza como apilar los platos uno encima de otro, ejemplo:
        * [3]
        * [2]
        * [1]
        * [0]
        *
        * Por eso cambiamos "poll" por "pop", poll quitaría en una fila al último,
        * ejemplo: [0][1][2][3][4], hago un "poll" y él [4] es retirado de la cola.
        *
        * En una pila uso "pop" y quita al que está más arriba en la pila.
         */
        return colaDeClientes.pop();
    }

    /**
     * <h2 style="text-align: center;">addPersonaCola</h2>
     * <br>
     * <p style="text-align: justify;">
     * Método que añade una persona a la cola.
     * </p>
     *
     * @param colaDeClientes La cola de clientes.
     */
    private static void addPersonaCola(LinkedList<Persona> colaDeClientes) {
        Persona nuevoCliente = new Persona();
        // colaDeClientes.push(nuevoCliente);

        /* Para entender mejor "pila" y "cola" mira el comentario que deje en el
        * método de removerPersonaCola, pero básicamente, "push" coloca ese
        * objeto en la cima de la pila, desplazando a todos los demás en la
        * pila hacia abajo.*/
        colaDeClientes.push(nuevoCliente);
    }
}

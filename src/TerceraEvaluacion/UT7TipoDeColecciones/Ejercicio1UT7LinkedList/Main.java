package TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio1UT7LinkedList;

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
        switch (opcionMenu) {
            case 1:
                addPersonaCola(colaDeClientes);
                break;
            case 2:
                Persona clienteSacado = removePersonaCola(colaDeClientes);
                System.out.println("El cliente N.º" + clienteSacado.getCODCLIENTE() + "ya ha sido atendido.");
                break;
            case 3:
                aQueClienteLeToca(colaDeClientes);
                break;
            case 4:
                System.out.println("¡Gracias! Hasta luego.");
                break;
            default:
                System.out.println("¡¿Pero cómo has llegado aquí?!");
                break;
        }
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
                        "3.º - Consultar a quién le toca el turno. %n" +
                        "4.º - Salir del programa.%n");
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
        return colaDeClientes.poll();
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

        // Anteriormente, use push, pero estaba mal, yo deseo añadirlo al final de la fila, no al inicio.
        colaDeClientes.offer(nuevoCliente);
    }
}

package UT7ObjetosAvanzados.Ejercicio7UT7GestionEmpleados;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h1 style="text-align: center">Clase MainEmpleado para la gestión de empleados en una empresa de productos químicos</h1>
 * <br>
 * <p style="text-align: justify">La segunda fase consiste en generar una lista con los empleados.
 * Se debe generar un ArrayList con empleados, para que no haya límite de personal. Como
 * inicialmente no tenemos ningún empleado, se nos proporciona un listado de los
 * empleados actuales, en forma de array de String (todos los datos de un
 * empleado vienen en un solo String).</p>
 * <br>
 * <p style="text-align: justify">El primer paso, será grabar los datos iniciales en objetos de
 * tipo Empleado y almacenarlos en el ArrayList, está clase cuenta con los siguiente métodos:
 * </p>
 * <ul>
 *      <li>cargarDatosIniciales - Carga todos los empleados del String Array en el ArrayList del Main</li>
 *      <li>inicialMayúscula - Desconocido, no está hecho a causa de no saber para que debía hacerlo</li>
 *      <li>mostrarMenu - Imprime en pantalla todas las opciones del programa ejecutables.</li>
 *      <li>pedirOperaciónMenu - Solicita que opción del menu desea ejecutar.</li>
 *      <li>mostrarEmpleados - Imprime una lista númerada de todos los empleados existentes.</li>
 *      <li>altaNuevoEmpleado - Permite crear un nuevo objeto / estancia empleado ("darle de alta")</li>
 *      <li>deseaContinuar - Permite controlar cuando el usuario desea o no seguir utilizando el programa.</li>
 *      <li>buscarPorDNI - Permite buscar un empleado en especifico atráves se su clave primaria (el DNI).</li>
 *      <li>buscarPorDepartamento - Permite buscar empleados que pertenezcan a un departamento específico.</li>
 *      <li>buscarPorSalario - Permite localizar uno o varios empleados que tengan el mismo sueldo.</li>
 *      <li>borrarEmpleado - Permite borrar un empleado del ArrayList y por lo tanto "darlo de baja".</li>
 *      <li>subirSueldo - Permite aumentar el sueldo de un empleado un "x" porcentaje deseado por el usuario.</li>
 * </ul>
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 31/01/2024
 */
public class MainEmpleado {
    public static void main(String[] args) {
        // Scanner teclado = new Scanner(System.in); // Permite introducir datos usando el teclado.
        int opcionMenu; // Variable que determina que método/opción ejecutamos.

        /* Hace falta una mención especial a que se refiere el "Empleado" dentro de "< >" y
         * es nuestra forma de indicarle a Java que aquello que este ArrayList va a almacenar
         * son estancias la clase Empleado, no permitiendo guardar otro tipo de datos y/u
         * objetos en él. */
        ArrayList<Empleado> empleados = new ArrayList<>(); // ArrayList que aloja los empleados.


        /* Un String del tipo Array que contiene los primeros empleados de la empresa.
         * Por petición del jefe (profesor) todos los nombres, apellidos y nombres
         * de los departamentos deben empezar por mayúscula. */
        String[] primerosEmpleados = {
                "11111111A Aitor Tilla Informática 60000",
                "22222222B Ester Colero Logística 34000",
                "33333333C Andrés Trozado Informática 56000",
                "44444444D Armando Ruido Logística 39000",
                "55555555E Dolores Fuertes Comercial 45000",
                "66666666F Enrique Cide Comercial 48000",
                "77777777F Estela Gartija Logística 52000",
                "88888888G Josechu Leton Informática 49000",
                "99999999H Lola Mento Informática 51000"};

        // Utilizamos el método de cargarEmpleados para cargar todos los empleados del String Array
        cargarDatosIniciales(empleados, primerosEmpleados);

        /* Creamos un menú usando un do-while que permite que método / opción del
         * programa deseamos ejecutar, hasta que no se elija la opción de "salir"
         * se sigue ejecutando.*/
        do {
            // Muestra el menú con todas sus opciones por pantalla.
            mostrarMenu();
            // Solicita la opción del menu que se desea ejecutar.
            opcionMenu = pedirOperacionMenu();
            // Dependiendo de la opción seleccionada, ejecuta un case u otro.
            switch (opcionMenu) {
                case 1:
                    // Imprime todos los empleados actuales en una lista númerada.
                    mostrarEmpleados(empleados);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("¡Hasta luego! Gracias por usar nuestro programa.");
                    break;
                default:
                    System.out.println("¡Opción no valida! intentalo de nuevo.");
                    break;
            }
        } while (opcionMenu != 8);
    }
    /**
     * <h2 style="text-align: center">Método N.º1 - Separa, ordena y carga los datos iniciales del
     * programa.</h2>
     * <br>
     * <p style="text-align: justify">Permite extraer los datos de los empleados de una cadena de
     * texto a "trozos" más manipulables para posteriormente estanciarlos en nuevos objetos de la
     * clase "Empleado" y añadirlos al ArrayList de está clase que contiene a los empleados.</p>
     *
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    public static void cargarDatosIniciales(ArrayList<Empleado> empleados, String[] primerosEmpleados) {
        /* Bucle que recorre cada String en el array primerosEmpleados, cada "String" contiene del
         * "String" Array primeros empleado contiene en una cadena completa los atributos y/o
         * elementos de un empleado (DNI, nombre, apellido, departamento, sueldo), por lo que
         * en él String "datosEmpleado" se guarda esa cadena completa con toda la información. */
        for (String datosEmpleado : primerosEmpleados) {
            /* en "datosEmpleado" tengo la cadena completa, la separo por sus espacios en blanco
             * y guardo cada trozo / recorte en una posición diferente de un nuevo String Array
             * que he llamado "partes".*/
            String[] partes = datosEmpleado.split(" ");

            /* Ahora en "partes" tengo la información del empleado en diferentes posiciones, así
             * guardo cada trozo de una variable del tipo String que permite manipular de forma
             * individual cada uno de los datos del empleado. */
            String DNIExtraido = partes[0];
            String nombreExtraido = partes[1];
            String apellidoExtraido = partes[2];
            String departamentoExtraido = partes[3];
            /* El último trozo es el sueldo, pero como es un "String", no solo debo extraerlo
             * para poder manipularlo, sino convertirlo / parsearlo a un tipo de variable
             * float para evitar problemas y hacerlo compatible con el constructor. */
            float sueldoExtraido = Float.parseFloat(partes[4]);

            /* Ahora que tengo los datos de forma individual, estancio la clase "Empleado" con
             * estós, creando un nuevo objeto / estancia "empleado". */
            Empleado nuevoEmpleado = new Empleado(DNIExtraido, nombreExtraido, apellidoExtraido, departamentoExtraido, sueldoExtraido);

            // Ya creado el empleado lo agrego al ArrayList de empleados.
            empleados.add(nuevoEmpleado);
        }
    }
    /**
     * <h2 style="text-align: center">Método N.º2 - Sin información.</h2>
     * <br>
     * <p style="text-align: justify">Este método no lo realice porque el día que se
     * asigno la realización de métodos me perdí de la explicación de para que
     * servía y cual era su función, por lo que queda inconcluso hasta que
     * pueda dar con cual era su propósito.</p>
     *
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    @Deprecated
    public static void inicialMayuscula () {

    }

    /**
     * <h2 style="text-align: center">Método N.º3 - Imprime el menú de opciones disponibles.</h2>
     * <br>
     * <p style="text-align: justify">Contiene todas las posibles opciones del menú y el
     * número correspondiente a cada una, para poder hacer el código más legible.</p>
     *
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    private static void mostrarMenu() {
        System.out.println("¡Elige una opción del menú!");
        System.out.println("1- Mostrar todos los empleados.");
        System.out.println("2- Dar de alta un nuevo empleado.");
        System.out.println("3- Buscar al empleado por Dni.");
        System.out.println("4- Buscar empleados por departamento");
        System.out.println("5- Borrar empleado por Dni");
        System.out.println("6- Buscar empleados por rango de sueldo");
        System.out.println("7- Subir el sueldo del empleado");
        System.out.println("8- Salir del programa.");
    }
    /**
     * <h2 style="text-align: center">Método N.º4 - Permite introducir por teclado la opción del menu
     * que deseamos ejecutar.</h2>
     * <br>
     * <p style="text-align: justify">Contiene un filtro para permitir que la opción seleccionada
     * sea valida y en caso de no serlo, enviar un mensaje de error y volver a solicitar una
     * opción valida.</p>
     *
     * @author Agu1406 (Agustín)
     * @since 31/01/2024
     */
    public static int pedirOperacionMenu () {
        Scanner teclado = new Scanner(System.in); // Escáner para introducir la opción por teclado.
        int opcionMenu = 0; // Variable que determina que método/opción ejecutamos.

        // bucle do-while que sigue pidiendo una opción de menu valida.
        do {
            try {
                // Permite introducir la opción por teclado.
                opcionMenu = teclado.nextInt();
                if (opcionMenu < 1 || opcionMenu > 8) {
                    System.out.println("¡Lo siento! Has introducido: "+opcionMenu+" y las opciones" +
                            "validas van del 1 al 8, ni más, ni menos, intentalo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Lo siento! Debes introducir números enteros, intentalo de nuevo.");
                teclado.next(); // Consume la linea para evitar que causa un error infinito.
            }
        } while (opcionMenu < 1 || opcionMenu > 8); // Mientras no sea una opción válida, no sale del bucle.

        // Devuelve una opción de menu valida.
        return opcionMenu;
    }
    /**
     * <h2 style="text-align: center">Método N.º5 - imprime la información reducida de todos
     * los empleados.</h2>
     * <br>
     * <p style="text-align: justify">Utiliza un método de la clase "Empleado" que permite
     * visualizar en forma de una lina de texto el DNI, nombre y sueldo de la estancia
     * desde la que se invoca.</p>
     *
     * @param empleados ArraList llamado "empleados" que contiene las estancias de la clase "Empleado".
     * @author Agu1406 (Agustín)
     * @see Empleado Para entender mejor como funciona el método "toStringReducido" de la clase
     * "Empleado" deberias consultar la misma.
     * @since 31/01/2024
     */
    public static void mostrarEmpleados(ArrayList<Empleado> empleados) {
        int orden = 1; // Sirve para el añadido estético de una numeración en la lista.

        /* Bucle que recorre el ArrayList de empleados completo imprimiendo la información
         * de las estancias, funciona como un bucle "for". */
        for (Empleado empleado : empleados) {
            // Imprime por pantalla la información reducida de cada empleado en forma de lista númerada.
            System.out.println("Numero N.º" + orden + ": " + empleado.toStringReducido());
            // Añade "1" para que la númeracion de la lista sea coherente.
            orden++;
        }
    }
    public static void altaNuevoEmpleado (ArrayList<Empleado> empleados) {

    }
    public static int deseaContinuar () {
        int continuar = 0;
        return continuar;
    }
    public static void buscarPorDNI (ArrayList<Empleado> empleados) {

    }
    public static void buscarPorDepartamento (ArrayList<Empleado> empleados) {

    }
    public static void buscarPorSueldo (ArrayList<Empleado> empleados) {

    }
    public static void borrarEmpleado (ArrayList<Empleado> empleados) {

    }
    public static void subirSueldo (ArrayList<Empleado> empleados) {

    }
}

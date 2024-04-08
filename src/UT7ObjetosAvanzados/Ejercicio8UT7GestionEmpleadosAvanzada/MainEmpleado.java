package UT7ObjetosAvanzados.Ejercicio8UT7GestionEmpleadosAvanzada;

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
 *
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
                    altaNuevoEmpleado(empleados);
                    break;
                case 3:
                    buscarPorDNI(empleados);
                    break;
                case 4:
                    buscarPorDepartamento(empleados);
                    break;
                case 5:
                    borrarEmpleado(empleados);
                    break;
                case 6:
                    buscarPorSueldo(empleados);
                    break;
                case 7:
                    subirSueldo(empleados);
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
    public static void inicialMayuscula() {

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
    public static int pedirOperacionMenu() {
        Scanner teclado = new Scanner(System.in); // Escáner para introducir la opción por teclado.
        int opcionMenu = 0; // Variable que determina que método/opción ejecutamos.

        // bucle do-while que sigue pidiendo una opción de menu valida.
        do {
            try {
                // Permite introducir la opción por teclado.
                opcionMenu = teclado.nextInt();
                if (opcionMenu < 1 || opcionMenu > 8) {
                    System.out.println("¡Lo siento! Has introducido: " + opcionMenu + " y las opciones" +
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

    /**
     * <h2 style="text-align: center">Método N.º6 - Dar de alta a un nuevo empleado.</h2>
     * <br>
     * <p style="text-align: justify">Este método permite añadir un nuevo empleado al
     * ArrayList de empleados. Antes de añadir a un nuevo empleado, verifica que el DNI
     * proporcionado no exista ya entre los empleados registrados. En caso de que el DNI
     * ya esté asociado a un empleado existente, se notifica al usuario y se le pide que
     * introduzca un DNI diferente. Este proceso se repite hasta que se ingrese un DNI único.
     * Una vez validado el DNI, se solicita al usuario que ingrese el resto de la información
     * necesaria para crear el nuevo empleado (nombre, apellido, departamento, sueldo), la cual
     * se utiliza para instanciar un nuevo objeto {@link Empleado} y añadirlo al ArrayList.</p>
     *
     * @param empleados ArrayList que contiene todos los empleados actuales. Este parámetro es
     *                  modificado por el método al añadir el nuevo empleado.
     */
    public static void altaNuevoEmpleado(ArrayList<Empleado> empleados) {
        // Creamos una instancia de Scanner para introducción de datos.
        Scanner teclado = new Scanner(System.in);
        // Creamos todas las variables que requieren nuestro constructor.
        String DNI, nombre, apellido, departamento;
        // El sueldo es el único de los atributos que no es un String.
        float sueldo = 0f;
        // Booleano que verifica si el DNI ya existe o no en el ArrayList
        boolean existeDNI = true, sueldoValido = false;

        /* Algo que debo tomar en cuenta es que podrían intentar dar de alta a
         * un empleado ya existente en el programa / aplicación, para controlar
         * esto puedo crear una instancia temporal falsa que revise una por una
         * todas las instancias del ArrayList y copie sus atributos, lo que me
         * permite usar el getDNI de la clase empleado para comparar el DNI en
         * el empleado temporal con el que ingresa el usuario, esto mediante
         * el equals, si ocurre que encuentra alguna concordancia es que ese
         * empleado ya existe y avisa del error y no continúa solicitando los
         * datos, pero si no existe ningún empleado con ese DNI entonces el
         * método sigue adelante y solicita el resto de los datos/atributos.*/
        System.out.println("¡Vamos a dar de alta al empleado!");

        // Bucle do-while que pide un DNI hasta que se verifique que no existe dentro del ArrayList.
        do {
            // Solicitamos el DNI por pantalla y teclado.
            System.out.print("Introduce el DNI: ");
            DNI = teclado.nextLine();
            System.out.println(" ");
            /* Bucle for que revisa una por una todas las instancias del ArrayList de
             * empleados, en cada vuelta hace un espejo de la instancia a la que está
             * apuntando, perfecto para poder compararlo por ejemplo con el método
             * "equals" con un DNI proporcionado por el usuario y así verificar si
             * ya existe una instancia de "Empleado" con el mismo DNI o no.*/
            for (Empleado empleadoEspejo : empleados) {
                // Si consigue el DNI en cualquier instancia del ArrayList, avisa de que ya existe.
                if (empleadoEspejo.getDNI().equals(DNI)) {
                    System.out.println("¡Lo siento! Ese DNI ya existe, no" +
                            "es posible darle de alta, intentalo de nuevo" +
                            "con un DNI no existente en el programa.");
                } else {
                    // Si no encuentra el DNI en el Array, para el bucle y continua el proceso de alta.
                    existeDNI = false;
                }
            } // Al llegar aquí ya ha recorrido el ArrayList en su totalidad.
        } while (existeDNI); // Al llegar aquí, dependiendo del booleano, repite o finaliza.

        // Solicitamos el Nombre por pantalla y teclado.
        System.out.print("Introduce el Nombre: ");
        nombre = teclado.nextLine();
        System.out.println(" ");

        // Solicitamos el Apellido por pantalla y teclado.
        System.out.print("Introduce el Apellido: ");
        apellido = teclado.nextLine();
        System.out.println(" ");

        // Solicitamos el Departamento por pantalla y teclado.
        System.out.print("Introduce el Departamento: ");
        departamento = teclado.nextLine();
        System.out.println(" ");

        /* Como el sueldo es un número y puede dar lugar a errores, hacemos un bucle do-while
        con try-catch para solicitar sueldo. */
        do {
            try {
                // Solicitamos el Sueldo por pantalla y teclado.
                System.out.print("Introduce el Sueldo: ");
                sueldo = teclado.nextFloat();
                System.out.println(" "); // Espacio adicional para la legibilidad.
                sueldoValido = true; // Si se llega a este punto, el sueldo es válido.
            } catch (InputMismatchException e) {
                System.out.println("¡Error! Debes introducir un número válido para el sueldo.");
                teclado.next(); // Limpiamos el buffer del scanner.
            }
        } while (!sueldoValido);

        // Con todos los datos válidos y correctos, creo una nueva instancia de "Empleado".
        Empleado nuevoEmpleado = new Empleado(DNI, nombre, apellido, departamento, sueldo);
        // Creada la instancia la agrego al ArrayList / lista de empleados existentes.
        empleados.add(nuevoEmpleado);
    }

    @Deprecated
    public static void deseaContinuar() {
    // En teoría debería ser del tipo "int" con un return.
    }

    /**
     * <h2 style="text-align: center">Método N.º6 - Buscar empleado por DNI.</h2>
     * <br>
     * <p style="text-align: justify">
     * Este método busca en la lista de empleados a aquel que tenga el DNI especificado
     * por el usuario. Si el empleado existe, muestra su información; de lo contrario,
     * notifica que no se encontró ningún empleado con ese DNI.
     * </p>
     *
     * @param empleados La lista de empleados donde se realizará la búsqueda.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public static void buscarPorDNI(ArrayList<Empleado> empleados) {
        Scanner teclado = new Scanner(System.in); // instancia de "Scanner" para introducir el DNI.
        boolean encontrado = false; // Boolean para indicar si se ha encontrado el empleado o no (no existe).

        // Solicita por pantalla y por teclado el DNI del empleado que desean buscar.
        System.out.println("Introduce el DNI del empleado que deseas buscar: ");
        String DNI = teclado.nextLine(); //

        for (Empleado empleado : empleados) {
            if (empleado.getDNI().equals(DNI)) {
                System.out.println("Empleado encontrado: " + empleado);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró un empleado con el DNI proporcionado.");
        }
    }

    /**
     * <h2 style="text-align: center">Método N.º7 - Buscar empleados por departamento.</h2>
     * <br>
     * <p style="text-align: justify">
     * Este método busca y muestra todos los empleados pertenecientes a un departamento específico.
     * Solicita al usuario el nombre del departamento y luego recorre la lista de empleados,
     * mostrando aquellos cuyo departamento coincide con el ingresado. Si no encuentra empleados
     * en ese departamento, notifica que no hay empleados en el departamento especificado.
     * </p>
     *
     * @param empleados La lista de empleados donde se realizará la búsqueda.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public static void buscarPorDepartamento(ArrayList<Empleado> empleados) {
        Scanner teclado = new Scanner(System.in); // Crear instancia de Scanner para entrada de datos.
        boolean encontrado = false; // Booleano para verificar si se encontraron empleados en el departamento.

        // Solicita por pantalla el nombre del departamento y se introduce por teclado.
        System.out.print("Por favor, introduce el nombre del departamento a buscar: ");
        String departamentoBuscado = teclado.nextLine();

        // Bucle for que recorre el ArrayList por completo buscando todos los empleados en ese departamento.
        for (Empleado empleado : empleados) {
            // Si existe un empleado cuyo atributo departamento concuerde con el de búsqueda, lo imprime.
            if (empleado.getDepartamento().equals(departamentoBuscado)) {
                System.out.println(empleado); // Muestra información del empleado encontrado.
                encontrado = true; // Marca que se ha encontrado al menos un empleado en el departamento.
            }
        }
        // Vale con que encuentre 1 solo empleado en el departamento que se busca para que este if no ocurra.
        if (!encontrado) {
            // Notifica al usuario si no se encontraron empleados en el departamento especificado.
            System.out.println("No se encontraron empleados en el departamento " + departamentoBuscado + ".");
        }
    }

    /**
     * <h2 style="text-align: center">Método N.º8 - Buscar empleados por rango de sueldo.</h2>
     * <br>
     * <p style="text-align: justify">
     * Este método permite al usuario buscar empleados cuyo sueldo esté dentro de un rango específico,
     * introducido por el mismo usuario. Solicita al usuario que ingrese el sueldo mínimo y máximo
     * del rango de búsqueda y muestra la información de los empleados cuyo sueldo se encuentre
     * dentro de dicho rango. Si no se encuentran empleados dentro del rango, informa al usuario.
     * </p>
     *
     * @param empleados La lista de empleados donde se realizará la búsqueda.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public static void buscarPorSueldo(ArrayList<Empleado> empleados) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el sueldo mínimo a buscar: ");
        float sueldoMin = teclado.nextFloat(); // Lee el sueldo mínimo del rango.
        System.out.print("Introduce el sueldo máximo a buscar: ");
        float sueldoMax = teclado.nextFloat(); // Lee el sueldo máximo del rango.
        boolean encontrado = false; // Para verificar si se encuentran empleados dentro del rango.

        for (Empleado empleado : empleados) {
            // Verifica si existe algún empleado dentro del sueldo definido por el usuario.
            if (empleado.getSueldo() >= sueldoMin && empleado.getSueldo() <= sueldoMax) {
                System.out.println(empleado); // Muestra información del empleado que cumple con el rango.
                encontrado = true;
            }
        }
        // Vale con que encuentre 1 solo empleado en el rango de sueldo que se busca para que este if no ocurra.
        if (!encontrado) {
            // Notifica al usuario si no se encontraron empleados dentro del rango especificado.
            System.out.println("No se encontraron empleados con sueldos entre " + sueldoMin + " y " + sueldoMax + ".");
        }
    }

    /**
     * <h2 style="text-align: center">Método N.º9 - Borrar empleado por DNI.</h2>
     * <br>
     * <p style="text-align: justify">
     * Este método permite al usuario eliminar un empleado específico del sistema utilizando su DNI
     * como identificador único. Solicita al usuario que ingrese el DNI del empleado a eliminar y,
     * si se encuentra en la lista de empleados, lo elimina. Si no se encuentra el empleado con el
     * DNI especificado, notifica al usuario que no se puede realizar la eliminación.
     * </p>
     *
     * @param empleados La lista de empleados de donde se eliminará el empleado especificado.
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public static void borrarEmpleado(ArrayList<Empleado> empleados) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduce el DNI del empleado a eliminar: ");
        String dniABorrar = teclado.nextLine();
        boolean encontrado = false;

        /* Tuve que cambiar a un for-i para poder abordar mejor el problema, recorre el
         * ArrayList "empleado" por completo, va revisando cada instancia dentro del mismo
         * una por una, extrayendo de cada una el DNI y comparándolo con el DNI que ha
         * introducido el usuario, si concuerdan, procede a borrar la instancia en la
         * posición de "i" del ArrayList y avisa por pantalla / mensaje que ha sido
         * eliminado. */
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getDNI().equals(dniABorrar)) {
                empleados.remove(i); // Elimina el empleado que coincide con el DNI.
                i--; /* Si elimina un objeto ArrayList, vuelve a la posición anterior para manejar
                la reducción en el tamaño del ArrayList y que no se salte posiciones, en este
                ejercicio no parece afectar en nada, pero es buena práctica dejarlo.*/
                System.out.println("Empleado con DNI " + dniABorrar + " ha sido eliminado.");
                encontrado = true;
            }
        }
        // Solo llegará a ejecutar este "if" si el booleano no cambia de valor porque no encuentra ese "empleado".
        if (!encontrado) {
            // Si después de buscar en toda la lista no se encuentra el DNI, notifica al usuario.
            System.out.println("No se encontró un empleado con el DNI " + dniABorrar + ", por lo" +
                    "que no se puede borrar ese empleado.");
        }
    }
    /**
     * <h2 style="text-align: center">Método N.º10 - Subir Sueldo de un Empleado</h2>
     * <br>
     * <p style="text-align: justify">Este método permite aumentar el sueldo de un empleado específico, identificado por su DNI, en un porcentaje indicado por el usuario. Si el empleado con el DNI proporcionado existe en la lista, su sueldo se actualizará según el porcentaje de aumento. El método muestra el sueldo del empleado antes y después del aumento para proporcionar un feedback claro al usuario. Si no se encuentra un empleado con el DNI especificado, se notificará al usuario.</p>
     * <br>
     * @param empleados La lista de empleados donde se realizará la búsqueda y actualización del sueldo.
     * <br>
     * <h3 style="text-align: center">Ejemplo de uso:</h3>
     * <br>
     * <code>subirSueldo(listaEmpleados);</code>
     * <p style="text-align: justify">Este método interactúa directamente con el usuario pidiendo el DNI del empleado a modificar y el porcentaje de aumento. Luego procede a buscar el empleado en la lista y, si lo encuentra, aplica el aumento de sueldo indicado.</p>
     * <br>
     * <p style="text-align: justify"><b>Nota:</b> Es fundamental que el DNI proporcionado por el usuario exista en la lista de empleados para que el método funcione correctamente. De lo contrario, el sistema informará que no se ha encontrado ningún empleado con dicho DNI.</p>
     *
     * @author Agu1406 (Agustín)
     * @since 04/04/2024
     */
    public static void subirSueldo(ArrayList<Empleado> empleados) {
        Scanner teclado = new Scanner(System.in); // instancia "Scanner" para recoger datos.
        boolean encontrado = false; // Booleano que dice si encontró alguien con ese DNI.

        // Solicita por pantalla y por teclado el DNI del empleado al que desea subir el sueldo.
        System.out.print("Introduce el DNI del empleado al que desea subir el sueldo: ");
        String dniEmpleado = teclado.nextLine();

        // Solicita por pantalla y por teclado el porcentaje de aumento de sueldo que desea.
        System.out.print("Introduce el porcentaje de aumento: ");
        float porcentajeAumento = teclado.nextFloat();

        /* El bucle recorre toda la lista de empleados, si encuentra alguno cuyo DNI
         * concuerde, sube su sueldo.*/
        for (Empleado empleado : empleados) {
            // Si encuentra el empleado con ese DNI, llama al método subirSalario de la clase "Empleado".
            if (empleado.getDNI().equals(dniEmpleado)) {
                // Imprime por pantalla el sueldo del empleado antes del incremento.
                System.out.println("El sueldo del empleado con DNI " + dniEmpleado + " antes era: " + empleado.getSueldo());
                empleado.subirSalario(porcentajeAumento);

                // Avisa por pantalla que el sueldo de ese empleado ha sido incrementado.
                System.out.println("El sueldo del empleado con DNI " + dniEmpleado + " ha sido actualizado.");

                // Imprime por pantalla el sueldo del empleado después del incremento.
                System.out.println("El sueldo del empleado con DNI " + dniEmpleado + " ahora es: " + empleado.getSueldo());

                // Booleano que indica que ese empleado si existe y fue encontrado.
                encontrado = true;
            }
        }
        // Solo llegará a ejecutar este "if" si el booleano no cambia de valor porque no encuentra ese "empleado".
        if (!encontrado) {
            System.out.println("No se encontró un empleado con el DNI " + dniEmpleado + ".");
        }
    }
}

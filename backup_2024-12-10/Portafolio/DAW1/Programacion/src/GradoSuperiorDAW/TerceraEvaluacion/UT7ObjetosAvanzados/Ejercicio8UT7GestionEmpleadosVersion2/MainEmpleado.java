package GradoSuperiorDAW.TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio8UT7GestionEmpleadosVersion2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
 * <h2 style="text-align: center;">Métodos del Main 1.0</h2>
 * <ul>
 *      <li><b>cargarDatosIniciales: </b>Carga todos los empleados del String Array en el ArrayList del Main</li>
 *      <li><b>inicialMayúscula: </b>Desconocido, no está hecho a causa de no saber para que debía hacerlo</li>
 *      <li><b>mostrarMenu: </b>Imprime en pantalla todas las opciones del programa ejecutables.</li>
 *      <li><b>pedirOperaciónMenu: </b>Solicita que opción del menu desea ejecutar.</li>
 *      <li><b>mostrarEmpleados: </b>Imprime una lista númerada de todos los empleados existentes.</li>
 *      <li><b>altaNuevoEmpleado: </b>Permite crear un nuevo objeto / estancia empleado ("darle de alta")</li>
 *      <li><b>deseaContinuar: </b>Permite controlar cuando el usuario desea o no seguir utilizando el programa.</li>
 *      <li><b>buscarPorDNI: </b>Permite buscar un empleado en especifico atráves se su clave primaria (el DNI).</li>
 *      <li><b>buscarPorDepartamento: </b>Permite buscar empleados que pertenezcan a un departamento específico.</li>
 *      <li><b>buscarPorSalario: </b>Permite localizar uno o varios empleados que tengan el mismo sueldo.</li>
 *      <li><b>borrarEmpleado: </b>Permite borrar un empleado del ArrayList y por lo tanto "darlo de baja".</li>
 *      <li><b>subirSueldo: </b>Permite aumentar el sueldo de un empleado un "x" porcentaje deseado por el usuario.</li>
 * </ul>
 * <h2 style="text-align: center;">Métodos del Main 2.0</h2>
 * <br>
 * <ul style="text-align: justify">
 *     <li><b>Mostrar aniversario:</b> Muestra cuando será el próximo aniversario del empleado en la
 * empresa y cuantos años completos, cumplirá en esa fecha. La fecha del aniversario se
 * mostrará en formato largo (ej: miércoles, 24 de marzo de 2021).</li>
 *     <li><b>Mostrar datos del empleado: </b>Mostrar DNI (añadiendo ceros para tener 8 dígitos),
 * nombre, apellido, departamento, edad actual y fecha de contrato en formato largo (ej:
 * miércoles, 24 de marzo de 2021). Si el DNI tiene menos de 8 dígitos, se deberán mostrar
 * ceros por delante, hasta completar 8 dígitos.</li>
 *     <li><b>altaNuevoEmpleado: </b>Se solicitarán los datos de un nuevo empleado, cumpliendo
 * todas las indicaciones anteriores. Además:
 * <ol><b>A) </b>Cuando la fecha de contrato, sea mayor al día de hoy, debe volver a solicitar esta fecha.</ol>
 * <ol><b>B) </b>En la fecha de contrato, el empleado debe tener al menos 18 años. En caso contrario, se
 * mostrará este error.</ol></li>
 *      <li><b>calcularSueldoMensual: </b>Para esta operación, deberá crearse un numero
 * método en la clase de los empleados. Para calcular el sueldo mensual de un empleado, se
 * deberán sumar los siguientes conceptos:
 * <ol><b>A) </b>Sueldo del empleado / 12 </ol>
 * <ol><b>B) </b>Antigüedad *30 (por cada año de antigüedad en la empresa, se añaden 30€)</ol>
 * <ol><b>C) </b>Además, si el mes actual coincide con el mes del cumpleaños del empleado, se
 * añadirán 50€ extras.</ol></li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @version 1.0
 * @since 31/01/2024
 */
public class MainEmpleado {
    final static String LETRAS_VALIDAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE"; // Juego de letras válidas de un DNI.
    public static void main(String[] args) {
        // Scanner teclado = new Scanner(System.in); // Permite introducir datos usando el teclado.
        int opcionMenu; // Variable que determina que método/opción ejecutamos.
        ArrayList<Empleado> empleados = new ArrayList<>(); // ArrayList que aloja los empleados.

        /* Un String del tipo Array que contiene los primeros empleados de la empresa.
         * Por petición del jefe (profesor) todos los nombres, apellidos y nombres
         * de los departamentos deben empezar por mayúscula. */
        String[] primerosEmpleados = {
                "11111111A Aitor Tilla Informática 60000 01/03/2022 15/04/1985",
                "22222222B Ester Colero Logística 34000 15/05/2021 22/08/1980",
                "33333333C Andrés Trozado Informática 56000 10/06/2020 03/11/1982",
                "44444444D Armando Ruido Logística 39000 20/07/2019 09/02/1979",
                "55555555E Dolores Fuertes Comercial 45000 25/08/2018 17/05/1990",
                "66666666F Enrique Cide Comercial 48000 30/09/2022 28/07/1988",
                "77777777F Estela Gartija Logística 52000 05/10/2021 04/12/1984",
                "88888888G Josechu Leton Informática 49000 11/11/2020 15/03/1987",
                "99999999H Lola Mento Informática 51000 16/12/2019 06/01/1983"
        };

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (String datosEmpleado : primerosEmpleados) {
            String[] partes = datosEmpleado.split(" ");

            String DNIExtraido = partes[0];
            String nombreExtraido = partes[1];
            String apellidoExtraido = partes[2];
            String departamentoExtraido = partes[3];
            float sueldoExtraido = Float.parseFloat(partes[4]);

            LocalDate fechaContrato, fechaNacimiento;

            try {
                fechaContrato = LocalDate.parse(partes[5], formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error al parsear la fecha de contrato para " + nombreExtraido + ". Usando fecha actual como predeterminada.");
                fechaContrato = LocalDate.now();
            }

            try {
                fechaNacimiento = LocalDate.parse(partes[6], formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error al parsear la fecha de nacimiento para " + nombreExtraido + ". Usando fecha actual como predeterminada.");
                fechaNacimiento = LocalDate.now();
            }

            Empleado nuevoEmpleado = new Empleado(DNIExtraido, nombreExtraido, apellidoExtraido, departamentoExtraido, sueldoExtraido, fechaContrato, fechaNacimiento);
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
        Scanner teclado = new Scanner(System.in); // Teclado para introducir datos.
        String DNI, nombre, apellido, departamento; // los atributos tipo "String" de un empleado.
        LocalDate fechaContrato; // La fecha en la que he contratado al empleado.
        LocalDate fechaNacimiento; // La fecha en la que ha nacido el empleado.
        float sueldo = 0f; // El sueldo del empleado, inicializado en "0".
        boolean sueldoValido = false; // Boolean que verifica que el sueldo ingresado sea válido.
        boolean existeDNI; // Booleano que verifica si el DNI ya existe en la empresa.

        System.out.println("¡Vamos a dar de alta al empleado!");
        do {
            System.out.print("Introduce el DNI: ");
            DNI = teclado.nextLine();
            // Damos por hecho de momento que no existe ya en la empresa, por eso, "false".
            existeDNI = false;

            // Primera comprobación que revisa si el DNI ya existe en la empresa (Ya esta dado de alta)
            for (Empleado empleado : empleados) {
                // Si lo encuentra dado de alta, avisa del error.
                if (empleado.getDNI().equals(DNI)) {
                    existeDNI = true;
                    System.out.println("¡Lo siento! Ese DNI ya existe, intenta con un DNI diferente.");
                }
            }

            // Comprobado que no existe, separamos los números de la letra.
            if (!existeDNI) {
                try {
                    // Guardamos todos los números de la posición "0" hasta la "-1" del tamaño.
                    int numeroDNI = Integer.parseInt(DNI.substring(0, DNI.length() - 1));
                    // Guardamos la letra, está en la posicion final de la longitud "-1".
                    char letraDNI = DNI.charAt(DNI.length() - 1);
                    // El número entero del DNI % 23 = a la posición de su letra válida.
                    int indice = numeroDNI % 23;

                    // Si la letra previamente extraida no concuerda con la letra válida calculada, da error.
                    if (LETRAS_VALIDAS_DNI.charAt(indice) != letraDNI) {
                        existeDNI = true;  // Usamos existeDNI para indicar un error de validación también
                        System.out.println("DNI no válido. Introduce un DNI correcto.");
                    }
                // Si cualquiera de los parseos da error, el try-catch los atrapa.
                } catch (NumberFormatException ex) {
                    existeDNI = true;
                    System.out.println("Formato de DNI incorrecto. Asegúrate de introducir bien el número y la letra.");
                }
            }
        } while (existeDNI);

        System.out.print("Introduce el Nombre: ");
        nombre = teclado.nextLine();

        System.out.print("Introduce el Apellido: ");
        apellido = teclado.nextLine();

        System.out.print("Introduce el Departamento: ");
        departamento = teclado.nextLine();

        while (!sueldoValido) {
            try {
                System.out.print("Introduce el Sueldo: ");
                sueldo = teclado.nextFloat();
                sueldoValido = true;
            } catch (InputMismatchException e) {
                System.out.println("¡Error! Debes introducir un número válido para el sueldo.");
                teclado.next();  // Limpiar el buffer del scanner
            }
        }

        fechaContrato = solicitarFecha("Fecha de contrato");
        
        fechaNacimiento = solicitarFecha("Fecha de nacimiento");

        Empleado nuevoEmpleado = new Empleado(DNI, nombre, apellido, departamento, sueldo, fechaContrato, fechaNacimiento);
        empleados.add(nuevoEmpleado);
    }

    private static LocalDate solicitarFecha(String tipoFecha) {
        Scanner teclado = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha;
        while (true) {
            System.out.print("Introduce la " + tipoFecha + " (dd/MM/yyyy): ");
            String fechaStr = teclado.next();
            try {
                fecha = LocalDate.parse(fechaStr, formatter);
                return fecha;  // Retornar la fecha si es válida
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida, intenta nuevamente.");
            }
        }
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
     *
     * @param empleados La lista de empleados donde se realizará la búsqueda y actualización del sueldo.
     *                  <br>
     *                  <h3 style="text-align: center">Ejemplo de uso:</h3>
     *                  <br>
     *                  <code>subirSueldo(listaEmpleados);</code>
     *                  <p style="text-align: justify">Este método interactúa directamente con el usuario pidiendo el DNI del empleado a modificar y el porcentaje de aumento. Luego procede a buscar el empleado en la lista y, si lo encuentra, aplica el aumento de sueldo indicado.</p>
     *                  <br>
     *                  <p style="text-align: justify"><b>Nota:</b> Es fundamental que el DNI proporcionado por el usuario exista en la lista de empleados para que el método funcione correctamente. De lo contrario, el sistema informará que no se ha encontrado ningún empleado con dicho DNI.</p>
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

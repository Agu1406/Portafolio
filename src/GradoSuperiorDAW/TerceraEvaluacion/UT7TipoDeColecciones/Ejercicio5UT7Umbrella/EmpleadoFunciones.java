package GradoSuperiorDAW.TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio5UT7Umbrella;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 */
public class EmpleadoFunciones {

    public static void anadirDatos(ArrayList<Empleado> listaEmpleados) {

        Empleado empleado1 = new Empleado(11111111,"Aitor","Tilla","Informatica", 60000, LocalDate.of(2002,4,30),LocalDate.of(2018,9,1));
        Empleado empleado2 = new Empleado(22222222, "María", "López", "Contabilidad", 55000, LocalDate.of(2005, 8, 15), LocalDate.of(2019, 6, 12));
        Empleado empleado3 = new Empleado(33333333, "Juan", "Gómez", "Recursos Humanos", 58000, LocalDate.of(2008, 3, 20), LocalDate.of(2020, 2, 28));
        Empleado empleado4 = new Empleado(44444444, "Laura", "Martínez", "Ventas", 62000, LocalDate.of(2003, 11, 10), LocalDate.of(2017, 7, 5));
        Empleado empleado5 = new Empleado(55555555, "Carlos", "Rodríguez", "Ventas", 60000, LocalDate.of(2006, 7, 25), LocalDate.of(2015, 9, 10));

        listaEmpleados.add(empleado1);
        listaEmpleados.add(empleado2);
        listaEmpleados.add(empleado3);
        listaEmpleados.add(empleado4);
        listaEmpleados.add(empleado5);

    }

    public static void mostrarLista(ArrayList<Empleado> listaEmpleados) {
        for (Empleado empleado : listaEmpleados) {
            empleado.mostrarReducido();
        }
    }

    public static void menu() {
        System.out.println("UMBRELLA CORP\n" +
                "1. MOSTRAR TODOS LOS EMPLEADOS\n" +
                "2. DAR DE ALTA UN NUEVO EMPLEADO\n" +
                "3. BUSCAR UN EMPLEADO\n" +
                "4. BUSCAR TODOS LOS EMPLEADOS DE UN DEPARTAMENTO\n" +
                "5. BORRAR UN EMPLEADO\n" +
                "6. SUBIR EL SUELDO A UN EMPLEADO\n" +
                "7. MOSTRAR EL SALARIO DEL MES ACTUAL DE UN EMPLEADO");
    }

    public static void altaEmpleado(ArrayList<Empleado> listaEmpleados) {
        Scanner teclado = new Scanner(System.in);
        int dni = 0;
        double sueldo=0;
        LocalDate fechaNacimiento=LocalDate.now();

        boolean dniValido=false;
        boolean sueldoValido=false;
        boolean fechaNacimientoValido=false;

        //Bucle que se repite en caso de que el formato de dni sea incorrecto
        while(!dniValido) {
            try {
                System.out.println("Introduce el DNI de la persona(sin letra): ");
                dni = teclado.nextInt();
                dniValido = true; //Se marca como true si no se lanza ninguna excepcion
            } catch (InputMismatchException e) {
                System.out.println("DNI Incorrecto!");
                teclado.nextLine();
            }
        }
        //Creamos un empleado auxiliar con ese dni.
        Empleado empleadoAux = new Empleado(dni);

        //Comprobamos si el usuario existe.
        int posicion = listaEmpleados.indexOf(empleadoAux);

        //Si la posición es distinta de -1 significa que ya existe
        if (posicion != -1) {
            System.out.println("El usuario ya existe");
        } else {
            System.out.println("Introduce el nombre del empleado: ");
            String nombre = solicitarDatos();
            System.out.println("Introduce el apellido del empleado: ");
            String apellido = solicitarDatos();
            System.out.println("Introduce el departamento del empleado: ");
            String departamento = solicitarDatos();

            while (!sueldoValido || sueldo>60000) {
                try {
                    System.out.println("Introduce el sueldo del empleado: ");
                    sueldo = teclado.nextInt();
                    sueldoValido=true; //Se marca como true si el formato de sueldo es valido
                } catch (InputMismatchException e) {
                    System.out.println("Formato incorrecto!");
                    teclado.nextLine();
                }
                teclado.nextLine();
            }

            while (!fechaNacimientoValido || fechaNacimiento.until(LocalDate.now(), ChronoUnit.YEARS)<18) {
                try {
                    System.out.println("Introduce la fecha de nacimiento del empleado: ");
                    fechaNacimiento = LocalDate.parse(teclado.nextLine(),
                            DateTimeFormatter.ofPattern("d/M/yyyy"));

                    fechaNacimientoValido =true; //Se marca como true si el formato de la fecha es correcto
                } catch (DateTimeParseException | MenorEdadException e ) {
                    if (e instanceof DateTimeParseException){
                        System.out.println("Formato de fecha Incorrecto!");
                }else {
                        System.out.println("El empleado debe ser mayor de edad");
                    }
                }

            }
            //Creamos un nuevo empleado con los datos introducidos.
            Empleado nuevoEmpleado = new Empleado(dni, nombre, apellido, departamento, sueldo, fechaNacimiento, LocalDate.now());
            listaEmpleados.add(nuevoEmpleado);
            System.out.println("Se ha añadido el empleado con dni: " + dni);

            empleadoAux.mostrarTodo();
        }
    }

    public static String solicitarDatos() {
        Scanner teclado = new Scanner(System.in);

        String introducirDatosRegex = "[A-Z]{1,15}";
        String datos;
        do {
            datos = teclado.nextLine().toUpperCase();
        } while (!Pattern.matches(introducirDatosRegex, datos));
        return datos;
    }

    public static void buscarEmpleado(ArrayList<Empleado> listaEmpleados) {
        Scanner teclado = new Scanner(System.in);

        String codigoEmpleado = "";
        boolean codigoEmpleadoValido = false;
        boolean empleadoEncontrado = false;

        //Bucle que se repite en caso de que el formato del codigo empleado sea incorrecto
        while (!codigoEmpleadoValido) {
            try {
                System.out.println("Introduce el codigo de empleado de la persona: ");
                codigoEmpleado = teclado.nextLine().toUpperCase();
                codigoEmpleadoValido = true; //Se marca como true si no se lanza ninguna excepcion
            } catch (InputMismatchException e) {
                System.out.println("Codigo de empleado Incorrecto!");
                teclado.nextLine();
            }
            for (Empleado empleado : listaEmpleados) {
                if (empleado.getCodigoEmpleado().equals(codigoEmpleado)) {
                    empleado.mostrarTodo();
                    empleadoEncontrado = true;
                }
            }
            if (!empleadoEncontrado) {
                System.out.println("El usuario no existe");
            }
        }
    }
    public static void buscarDepartamento(ArrayList<Empleado> listaEmpleados) {     //BUSCAMOS EMPLEADO POR DEPARTAMENTO
        Scanner teclado = new Scanner(System.in);

        String departamento;
        System.out.println("Dame un departamento para filtrar por el: ");
        departamento = teclado.next();
        int countEmpleados = 0;
        ArrayList<Empleado> empleadosFiltrados = new ArrayList<>();

        for (Empleado empleado : listaEmpleados) {
            if (empleado.getDepartamento().equals(departamento)) {
                empleadosFiltrados.add(empleado);
                countEmpleados++;
            }
        }

        System.out.println("FILTRANDO " + countEmpleados + " RESULTADO(S)...");
        for (Empleado empleado : empleadosFiltrados) {
            empleado.mostrarReducido();
        }
    }

    public static void borrarEmpleado(ArrayList<Empleado> listaEmpleados) {     //BORRAMOS EMPLEADO SEGÚN SU CODIGO DE EMPLEADO

        Empleado codigoEmpleadoProv = empleadoAuxYConfirmarEmpleado(listaEmpleados);

        listaEmpleados.removeIf(empleado -> empleado.equals(codigoEmpleadoProv));
    }

    public static void subirSueldo(ArrayList<Empleado> listaEmpleados) {        //SUBIMOS EL SUELDO PORCENTUALMENTE
        Scanner teclado = new Scanner(System.in);

        Empleado codigoEmpleadoProv = empleadoAuxYConfirmarEmpleado(listaEmpleados);

        boolean esSueldoCorrecto = false;
        double sueldoPorcentaje;

        do {
            System.out.println("Dame un porcentaje para subir el sueldo ");
            sueldoPorcentaje = teclado.nextInt();
            if (sueldoPorcentaje > 0) {
                esSueldoCorrecto = true;
            } else {
                System.out.println("la subida debe ser mayor que 0 ");
            }
        } while (!esSueldoCorrecto);

        for (Empleado empleado : listaEmpleados) {
            if (empleado.equals(codigoEmpleadoProv)) {
                double variacion = empleado.getSueldo();
                double variacionHecha = variacion * ((sueldoPorcentaje / 100) + 1);
                empleado.setSueldo(variacionHecha);

                System.out.println("Sueldo actualizado para " + empleado.getNombre() + ": " + empleado.getSueldo());
            }
        }
    }
    public static void mostrarSalarioMesActual(ArrayList<Empleado> listaEmpleados) {        //MOSTRAR SALARIO DEL MES ACTUAL DE EL EMPLEADO SELECCIONADO POR SU CODIGO DE EMPLEADO

        Empleado codigoEmpleadoProv = empleadoAuxYConfirmarEmpleado(listaEmpleados);

        for (Empleado empleado : listaEmpleados){
            if (empleado.equals(codigoEmpleadoProv)){
                empleado.sueldoMensual();
            }
        }
    }

    public static Empleado empleadoAuxYConfirmarEmpleado (ArrayList<Empleado> listaEmpleados) {     //FUNCION ASISTENTE PARA PEDIR Y VERIFICAR EL CODIGO DE EMPLEADO, DEVUELVE UN EMPLEADO AUXILIAR
        Scanner teclado = new Scanner(System.in);

        Empleado codigoEmpleadoProv;
        boolean contieneCodigoEmpleado = false;
        String codigoEmpleado;

        do {
            System.out.println("Dame el Codigo Empleado");
            codigoEmpleado = teclado.nextLine();
            codigoEmpleadoProv = new Empleado(codigoEmpleado);
            if (listaEmpleados.contains(codigoEmpleadoProv)) {
                System.out.println("El Empleado buscado se encuentra en la lista");
                contieneCodigoEmpleado = true;
            } else {
                System.out.println("El empleado no se encuentra en la lista\nVuelva a intentar");
            }
        } while (!contieneCodigoEmpleado);
        return codigoEmpleadoProv;
    }
}

package GradoSuperiorDAW.TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio8UT7GestionEmpleadosVersion3.Coorporacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainEmpleado {
    final static String LETRAS_VALIDAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcionMenu;
        ArrayList<Empleado> empleados = new ArrayList<>();

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

        cargarDatosIniciales(empleados, primerosEmpleados);

        do {
            mostrarMenu();
            opcionMenu = pedirOperacionMenu(teclado);
            switch (opcionMenu) {
                case 1:
                    mostrarTodosLosEmpleados(empleados);
                    break;
                case 2:
                    darDeAltaEmpleado(empleados, teclado);
                    break;
                case 3:
                    buscarEmpleadoPorCodigo(empleados, teclado);
                    break;
                case 4:
                    mostrarEmpleadosPorDepartamento(empleados, teclado);
                    break;
                case 5:
                    borrarEmpleadoPorCodigo(empleados, teclado);
                    break;
                case 6:
                    subirSueldoEmpleado(empleados, teclado);
                    break;
                case 7:
                    mostrarSalarioActualEmpleado(empleados, teclado);
                    break;
                case 8:
                    System.out.println("¡Hasta luego! Gracias por usar nuestro programa.");
                    break;
                default:
                    System.out.println("¡Opción no válida! Inténtalo de nuevo.");
                    break;
            }
        } while (opcionMenu != 8);
    }

    public static void cargarDatosIniciales(ArrayList<Empleado> empleados, String[] primerosEmpleados) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (String datosEmpleado : primerosEmpleados) {
            String[] partes = datosEmpleado.split(" ");
            String DNI = partes[0];
            String nombre = partes[1];
            String apellido = partes[2];
            String departamento = partes[3];
            float sueldo = Float.parseFloat(partes[4]);
            LocalDate fechaContrato = LocalDate.parse(partes[5], formatter);
            LocalDate fechaNacimiento = LocalDate.parse(partes[6], formatter);
            Empleado empleado = new Empleado(DNI, nombre, apellido, departamento, sueldo, fechaContrato, fechaNacimiento);
            empleados.add(empleado);
        }
    }

    private static void mostrarMenu() {
        System.out.println("¡Elige una opción del menú!");
        System.out.println("1- Mostrar todos los empleados (en formato reducido).");
        System.out.println("2- Dar de alta un nuevo empleado.");
        System.out.println("3- Buscar un empleado por su código.");
        System.out.println("4- Buscar todos los empleados de un departamento (en formato reducido).");
        System.out.println("5- Borrar un empleado por su código.");
        System.out.println("6- Subir el sueldo a un empleado.");
        System.out.println("7- Mostrar el salario del mes actual de un empleado.");
        System.out.println("8- Salir del programa.");
    }


    public static int pedirOperacionMenu(Scanner teclado) {
        int opcionMenu = 0;
        do {
            try {
                opcionMenu = teclado.nextInt();
                if (opcionMenu < 1 || opcionMenu > 8) {
                    System.out.println("¡Opción no válida! Por favor, elige una opción entre 1 y 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Error! Debes ingresar un número. Inténtalo de nuevo.");
                teclado.next();
            }
        } while (opcionMenu < 1 || opcionMenu > 8);
        return opcionMenu;
    }

    public static void mostrarTodosLosEmpleados(ArrayList<Empleado> empleados) {
        for (int i = 0; i < empleados.size(); i++) {
            System.out.println((i + 1) + ". " + empleados.get(i).toStringReducido());
        }
    }

    public static void darDeAltaEmpleado(ArrayList<Empleado> empleados, Scanner teclado) {
        System.out.println("Introduzca los datos del nuevo empleado:");
        System.out.print("DNI: ");
        String DNI = teclado.next();
        System.out.print("Nombre: ");
        String nombre = teclado.next();
        System.out.print("Apellido: ");
        String apellido = teclado.next();
        System.out.print("Departamento: ");
        String departamento = teclado.next();
        System.out.print("Sueldo: ");
        float sueldo = teclado.nextFloat();
        System.out.print("Fecha de Contrato (dd/MM/yyyy): ");
        String fechaContratoStr = teclado.next();
        LocalDate fechaContrato = LocalDate.parse(fechaContratoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Fecha de Nacimiento (dd/MM/yyyy): ");
        String fechaNacimientoStr = teclado.next();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Empleado nuevoEmpleado = new Empleado(DNI, nombre, apellido, departamento, sueldo, fechaContrato, fechaNacimiento);
        empleados.add(nuevoEmpleado);
        System.out.println("Empleado añadido exitosamente.");
    }
    public static void buscarEmpleadoPorCodigo (ArrayList<Empleado> empleados, Scanner teclado) {
        System.out.print("Introduce el Código del empleado a buscar: ");
        String codigo = teclado.next();
        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getCodigoEmpleado().equals(codigo)) {
                System.out.println("Empleado encontrado: " + empleado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún empleado con el Código proporcionado.");
        }
    }

    public static void buscarPorDNI(ArrayList<Empleado> empleados, Scanner teclado) {
        System.out.print("Introduce el DNI del empleado a buscar: ");
        String DNI = teclado.next();
        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getDNI().equals(DNI)) {
                System.out.println("Empleado encontrado: " + empleado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún empleado con el DNI proporcionado.");
        }
    }

    public static void mostrarEmpleadosPorDepartamento(ArrayList<Empleado> empleados, Scanner teclado) {
        System.out.print("Introduce el departamento a buscar: ");
        String departamento = teclado.next();
        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getDepartamento().equals(departamento)) {
                System.out.println("Empleado en departamento " + departamento + ": " + empleado);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron empleados en el departamento " + departamento + ".");
        }
    }

    public static void buscarPorSueldo(ArrayList<Empleado> empleados, Scanner teclado) {
        System.out.print("Introduce el sueldo mínimo: ");
        float minSueldo = teclado.nextFloat();
        System.out.print("Introduce el sueldo máximo: ");
        float maxSueldo = teclado.nextFloat();
        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getSueldo() >= minSueldo && empleado.getSueldo() <= maxSueldo) {
                System.out.println("Empleado con sueldo en el rango: " + empleado);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron empleados con sueldo en el rango especificado.");
        }
    }

    public static void borrarEmpleadoPorCodigo(ArrayList<Empleado> empleados, Scanner teclado) {
        System.out.print("Introduce el codigo del empleado a eliminar: ");
        String codigo = teclado.next();
        boolean encontrado = false;
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getCodigoEmpleado().equals(codigo)) {
                empleados.remove(i);
                System.out.println("Empleado eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún empleado con el DNI proporcionado para eliminar.");
        }
    }

    public static void subirSueldoEmpleado(ArrayList<Empleado> empleados, Scanner teclado) {
        System.out.print("Introduce el DNI del empleado al que deseas subir el sueldo: ");
        String DNI = teclado.next();
        System.out.print("Introduce el porcentaje de aumento: ");
        float porcentaje = teclado.nextFloat();
        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getDNI().equals(DNI)) {
                float nuevoSueldo = empleado.getSueldo() * (1 + porcentaje / 100);
                empleado.setSueldo(nuevoSueldo);
                System.out.println("El sueldo ha sido actualizado a: " + nuevoSueldo);
                encontrado = true;
                break;
            }
        }
    }

    public static void mostrarSalarioActualEmpleado (ArrayList<Empleado> empleados, Scanner teclado) {
        System.out.print("Introduce el código del empleado a buscar: ");
        String codigo = teclado.next();
        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getCodigoEmpleado().equals(codigo)) {
                System.out.println("Empleado encontrado: " + empleado);
                System.out.println("Su sueldo es: " + empleado.getSueldo());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún empleado con el DNI proporcionado.");
        }
    }
}
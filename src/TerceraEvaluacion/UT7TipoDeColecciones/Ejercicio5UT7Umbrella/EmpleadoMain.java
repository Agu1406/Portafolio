package TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio5UT7Umbrella;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 */
public class EmpleadoMain {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        EmpleadoFunciones.anadirDatos(listaEmpleados);

        int opcion = 0;
        do {
            EmpleadoFunciones.menu();
            try {
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        EmpleadoFunciones.mostrarLista(listaEmpleados);
                        break;
                    case 2:
                        EmpleadoFunciones.altaEmpleado(listaEmpleados);
                        break;
                    case 3:
                        EmpleadoFunciones.buscarEmpleado(listaEmpleados);
                        break;
                    case 4:
                        EmpleadoFunciones.buscarDepartamento(listaEmpleados);
                        break;

                    case 5:
                        EmpleadoFunciones.borrarEmpleado(listaEmpleados);
                        break;

                    case 6:
                        EmpleadoFunciones.subirSueldo(listaEmpleados);
                        break;

                    case 7:
                        EmpleadoFunciones.mostrarSalarioMesActual(listaEmpleados);
                        break;
                } }catch(InputMismatchException e) {
                System.out.println("¡Error! Solo se permiten números enteros.");
                teclado.next();
            }
        }while (opcion!=8);
    }
}
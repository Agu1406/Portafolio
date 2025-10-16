package PrimeraEvaluacion.EjercicioEstacionConcepcion;

import java.util.Scanner;

public class EstacionConcepcion {
    // Creamos un enum para guardar las estaciones del año.
    private enum Estacion {
        PRIMAVERA, VERANO, OTONO, INVIERNO
    }

    public static void main(String[] args) {
        // Creamos un Scanner llamado "teclado" para escribir datos.
        Scanner teclado = new Scanner(System.in);

        int diaNacimiento, mesNacimiento, diaMaximo, mesConcepcion, diaConcepcion ,diaMaxConcepcion;
        String mensaje;

        System.out.println("¡Introduce el día de nacimiento!");
        diaNacimiento = teclado.nextInt();

        System.out.println("¡Introduce el mes de nacimiento! Opciones validas:\n" +
                "1 => Enero. \n" +
                "2 => Febrero. \n" +
                "3 => Marzo. \n" +
                "4 => Abril. \n" +
                "5 => Mayo. \n" +
                "6 => Junio. \n" +
                "7 => Julio. \n" +
                "8 => Agosto. \n" +
                "9 => Septiembre. \n" +
                "10 => Octubre. \n" +
                "11 => Noviembre. \n" +
                "12 => Diciembre.\n ");
        mesNacimiento = teclado.nextInt();

        // Valida que el mes de nacimiento introducido sea valido.
        if (mesNacimiento < 1 || mesNacimiento > 12 ) {
            System.out.println("¡Error! Introduce un mes valido (del 1 al 12).");
        }

        /*
         * Switch case que dependiendo del mes introducido define el día maximmo valido
         * de nacimiento de la persona, hipeticamente hablando, febrero siempre tiene
         * 28 días.
         */
        switch (mesNacimiento) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                diaMaximo = 31;
                break;
            case 2:
                diaMaximo = 28;
                break;
            case 4: case 6: case 9: case 11:
                diaMaximo = 30;
                break;
            default:
                diaMaximo = 0;
                System.out.println("¡Error! Mes no valido, intentalo de nuevo");
                break;
        }

        // Valida que el día de nacimiento introducido sea valido.
        if (diaNacimiento < 1 || diaNacimiento > diaMaximo) {
            System.out.println("¡Error! Introduce un día valido de nacimiento.");
        }

        /*
         * Explicación de porque sumamos "12 a números iguales o
         * menores a "0".
         *
         * 12 - 9 =  3.
         * 11 - 9 =  2.
         * 10 - 9 =  1.
         * 09 - 9 =  0 + 12 (12).
         *
         * 08 - 9 = -1 + 12 (11).
         * 07 - 9 = -2 + 12 (10).
         * 06 - 9 = -3 + 12 (09).
         *
         * */
        mesConcepcion = mesNacimiento - 9;
        if(mesConcepcion <= 0) {
            mesConcepcion += 12;
        }

        switch (mesConcepcion) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                diaMaxConcepcion = 31;
                break;
            case 2:
                diaMaxConcepcion = 28;
                break;
            case 4: case 6: case 9: case 11:
                diaMaxConcepcion = 30;
                break;
            default:
                diaMaxConcepcion = 0;
                System.out.println("¡Error! Mes no valido, intentalo de nuevo");
                break;
        }

        if (diaNacimiento >= diaMaxConcepcion) {
            diaConcepcion = diaMaxConcepcion;
        } else {
            diaConcepcion = diaNacimiento;
        }

        Estacion estacion;

        if ((mesConcepcion == 1) || (mesConcepcion == 2) || (mesConcepcion == 3 && diaConcepcion <=20)) {
            estacion = Estacion.INVIERNO;
        }
        else if ((mesConcepcion == 3 && diaConcepcion >= 21) || (mesConcepcion == 4) || (mesConcepcion == 5) || (mesConcepcion == 6 && diaConcepcion <=20)) {
            estacion = Estacion.PRIMAVERA;
        }
        else if ((mesConcepcion == 6 && diaConcepcion >= 21) || (mesConcepcion == 7) || (mesConcepcion == 8) || (mesConcepcion == 9 && diaConcepcion <=20)) {
            estacion = Estacion.VERANO;
        }
        else if ((mesConcepcion == 9 && diaConcepcion >= 21) || (mesConcepcion == 10) || (mesConcepcion == 11) || (mesConcepcion == 12 && diaConcepcion <=20)) {
            estacion = Estacion.OTONO;
        } else {
            estacion = Estacion.INVIERNO;
        }

        switch (estacion) {
            case PRIMAVERA:
                mensaje = "¡Naciste en primavera!";
                break;
            case INVIERNO:
                mensaje = "¡Naciste en inverino!";
                break;
            case OTONO:
                mensaje = "¡Naciste en otoño!";
                break;
            case VERANO:
                mensaje = "¡Naciste en verano!";
                break;
            default:
                mensaje = "¡Error! No has nacido.";
        }

        System.out.println("Calculando estación de concepción... " + mensaje);

        System.out.println("Programa terminado.");
    }
}

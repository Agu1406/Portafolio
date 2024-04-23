package UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto;

import UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto.Entradas.Entrada;
import UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto.Entradas.Grada;
import UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto.Entradas.Pista;
import UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto.Entradas.VIP;

import java.util.ArrayList;
import java.util.Scanner;

public class MainEntradas {
    public static void main(String[] args) {
        /*
         * ArrayList genérico donde alojo todas las entradas sin importar de que tipo son
         * y además 3 ArrayList donde guardo de forma clasificada las entradas según el
         * tipo de entrada que sean. */
        ArrayList<Entrada> todasLasEntradas = new ArrayList<>();
        ArrayList<Grada> entradasGrada = new ArrayList<>();
        ArrayList<Pista> entradasPista = new ArrayList<>();
        ArrayList<VIP> entradasVIP = new ArrayList<>();

        // Bucles que genera todas las entradas
        int maxEntradasGrada = Grada.getLimiteOeste() + Grada.getLimiteEste() + Grada.getLimiteSur();
        int maxEntradasPista = Pista.getLimitePista();
        int maxEntradasVIP = VIP.getLimiteVip();

        while (maxEntradasGrada > 0) {
            Grada entradaGrada = definirGrada(maxEntradasGrada);
            todasLasEntradas.add(entradaGrada);
            entradasGrada.add(entradaGrada);
            maxEntradasGrada--;
        }

        while (maxEntradasPista > 0) {
            Pista entradaPista = new Pista();
            todasLasEntradas.add(entradaPista);
            entradasPista.add(entradaPista);
            maxEntradasPista--;
        }
        while (maxEntradasVIP > 0) {
            VIP entradaVIP = new VIP();
            todasLasEntradas.add(entradaVIP);
            entradasVIP.add(entradaVIP);
            maxEntradasVIP--;
        }

        comprarEntrada();
    }

    private static void comprarEntrada() {
        Scanner teclado = new Scanner(System.in);
        String correoElectronico;
        String tipoEntrada;
        boolean tipoValido;
        int cantidadDeseada;
        int opcionEntrada;
        String expRegCorreo = ".+@.+\\..+";
        System.out.println("¡Hola! Bienvenido al Wiz-ink.");
        do {
            System.out.println("Introduce tú correo electronico. ");
            System.out.print("Correo: ");
            correoElectronico = teclado.nextLine();
        } while (!correoElectronico.matches(expRegCorreo));
        // Bucle do-while para solicitar la cantidad de entradas que desea comprar.
        do {
            System.out.println("¿Que cantidad de entradas quieres comprar? (Max. 20)");
            System.out.print("Cantidad: ");
            cantidadDeseada = teclado.nextInt();
        } while (cantidadDeseada > 20 || cantidadDeseada < 1);

        do {
            System.out.println("¿Que tipo de entrada quieres comprar?");
            menuEntradasValidas(); // Muestra el menu de opciones para comprar.
            opcionEntrada = teclado.nextInt();
            tipoEntrada = definirTipoEntrada(opcionEntrada);
            tipoValido = verificarTipoValido(tipoEntrada);

        } while (!tipoValido);
        // Me falta pedir la cantidad de entradas que desea comprar, de que tipo, etc....
    }

    private static boolean verificarTipoValido(String tipoEntrada) {
        boolean tipoValido;

        tipoValido = tipoEntrada.equals("GRADA") || tipoEntrada.equals("PISTA") || tipoEntrada.equals("VIP");

        return tipoValido;
    }

    private static Grada definirGrada(int maxEntradasGrada) {
        String zonaActual; // Controla a que "zona" pertenece el examén.

        // Dependiendo del número de entrada creada es una zona u otra.
        if (maxEntradasGrada > 2000 && maxEntradasGrada <= 4000) {
            zonaActual = "OESTE";
        } else if (maxEntradasGrada <= 2000) {
            zonaActual = "ESTE";
        } else {
            zonaActual = "SUR";
        }
        // Crea una nueva entrada del tipo "Grada" en su respectiva "zona".
        return new Grada(zonaActual);
    }

    public static String definirTipoEntrada(int opcionEntrada) {
        String tipoEntrada;
        switch (opcionEntrada) {
            case 1:
                tipoEntrada = "GRADA";
                break;
            case 2:
                tipoEntrada = "PISTA";
                break;
            case 3:
                tipoEntrada = "VIP";
                break;
            default:
                System.out.println("¡La opción elegida no es valida, intentalo de nuevo!");
                tipoEntrada = null;
                break;

        }
        return tipoEntrada;
    }

    public static void menuEntradasValidas () {
        System.out.printf("Elige una entrada del siguiente menú. %n" +
                "debes utilizar el número correspondiente a la " +
                "opción para elegirla: %n" +
                "[1] Grada. %n" +
                "[2] Pista. %n" +
                "[3] V.I.P. %n");
    }
}

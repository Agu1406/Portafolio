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
        ArrayList <Entrada> todasLasEntradas = new ArrayList<>();
        ArrayList <Grada> entradasGrada = new ArrayList<>();
        ArrayList <Pista> entradasPista = new ArrayList<>();
        ArrayList <VIP> entradasVIP = new ArrayList<>();

        // Bucles que genera todas las entradas
        int maxEntradasGrada = Grada.getLimiteOeste() + Grada.getLimiteEste() + Grada.getLimiteSur();
        int maxEntradasPista = Pista.getLimitePista();
        int maxEntradasVIP = VIP.getLimiteVip();

        while (maxEntradasGrada > 0) {
            Grada entradaGrada = definirGrada(maxEntradasGrada);
            todasLasEntradas.add(entradaGrada);
            entradasGrada.add(entradaGrada);
        }

        while (maxEntradasPista > 0) {
            Pista entradaPista = new Pista();
            todasLasEntradas.add(entradaPista);
            entradasPista.add(entradaPista);
        }
        while (maxEntradasVIP > 0) {
            VIP entradaVIP = new VIP();
            todasLasEntradas.add(entradaVIP);
            entradasVIP.add(entradaVIP);
        }

        comprarEntrada();
    }

    private static void comprarEntrada() {
        Scanner teclado = new Scanner(System.in);
        String correoElectronico;
        System.out.println("¡Hola! Bienvenido al Wiz-ink.");
        System.out.println("Introduce tú correo electronico. ");
        System.out.print("Correo: "); correoElectronico = teclado.nextLine();

        // Me falta pedir la cantidad de entradas que desea comprar, de que tipo, etc....
    }

    private static Grada definirGrada(int maxEntradasGrada) {
        String zonaActual; // Controla a que "zona" pertenece el examén.

        // Dependiendo del número de entrada creada es una zona u otra.
        if (maxEntradasGrada > 2000 && maxEntradasGrada <= 4000) {
            zonaActual = "OESTE";
        } else
        if (maxEntradasGrada <= 2000) {
            zonaActual = "ESTE";
        } else {
            zonaActual = "SUR";
        }
        // Crea una nueva entrada del tipo "Grada" en su respectiva "zona".
        Grada entradaGrada = new Grada(zonaActual);
        return entradaGrada;
    }
}

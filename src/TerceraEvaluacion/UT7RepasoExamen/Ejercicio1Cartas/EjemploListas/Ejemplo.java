package TerceraEvaluacion.UT7RepasoExamen.Ejercicio1Cartas.EjemploListas;

import java.util.ArrayList;
import java.util.Iterator;

public class Ejemplo {
    public static void main(String[] args) {
        ArrayList<String> ciudades = new ArrayList();
        // LinkedList<String> ciudades = new LinkedList();
        // Vector<String> ciudades = new Vector();

        ciudades.add("Madrid");
        ciudades.add("Barcelona");
        ciudades.add("Malaga");
        ciudades.add("Vigo");
        ciudades.add(1, "Sevilla"); //Lo insertamos en la posicion 1
        ciudades.add("Madrid");


        //Varias formas de recorrerlo
        Iterator<String> it = ciudades.iterator();
        while (it.hasNext()) {
            System.out.println("Ciudad: " + it.next());
        }
        System.out.println("**********");
        for (String ciudad : ciudades) {
            System.out.println("Ciudad: " + ciudad);
        }
        System.out.println("**********");
        for (int i = 0; i < ciudades.size(); i++) {
            System.out.println("Ciudad " + i + " es " + ciudades.get(i));
        }

    }
}


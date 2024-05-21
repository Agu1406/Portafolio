package TerceraEvaluacion.UT7TipoDeColecciones.Ejercicio4UT7SetList;

import java.util.ArrayList;
import java.util.Arrays;

public class CiudadesVisitadas {
    public static void main(String[] args) {
        // Creamos las ciudades que hemos visitado en el ordén de las visitas.
        String ciudad1 = "Caracas", ciudad2 = "Barcelona", ciudad3 = "Madrid";
        String ciudad4 = "Miamia", ciudad5 = "Cartagena", ciudad6 = "Caracas";

        // Creamos un ArrayList donde guardar las ciudades que visitamos (admite repetidas)
        ArrayList<String> ciudades = new ArrayList<>(Arrays.asList(ciudad1, ciudad2, ciudad3, ciudad4, ciudad5, ciudad6));

        // Creamos un ArrayList que usaremos como guía para no contar dos veces una misma ciudad.
        ArrayList<String> ciudadesContadas = new ArrayList<>();

        /*
         * Este bucle foreach en cada vuelta toma la ciudad de "X" posición del ArrayList y
         * guarda una copia en el comparador, luego el bucle interno usa esa copia que se
         * llama "comparador" para contar cuantas veces aparece esa ciudad en el Array. */
        for (String comparador : ciudades) {

            if (!ciudadesContadas.contains(comparador)) {

                // Este contador lo usará el bucle interno para contar la cantidad de visitas.
                int visitas = 0;

                // Contamos cuántas veces aparece la ciudad en el ArrayList
                for (String ciudad : ciudades) {
                    if (comparador.equals(ciudad)) {
                        visitas++;
                    }
                }

                System.out.println("Has visitado " + comparador + " un total de " + visitas + " veces.");

                ciudadesContadas.add(comparador);
            }
        }
    }
}

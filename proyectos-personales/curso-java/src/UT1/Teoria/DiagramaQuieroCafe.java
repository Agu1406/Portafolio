package UT1.CodigoDiagramas.Ejemplos;

public class QuieroCafe {
    public static void main(String[] args) {
        // Variable que avisa si hay cafe hecho, "true" es sí, "false" es no.
        boolean hayCafeHecho = true;
        // Variable que nos avisa si se calentó el café.
        boolean calentarCafe = false;
        // Variable que nos avisa si se hizo café nuevo.
        boolean hacerCafe = false;
        // Variable que avisa si el cafe fue servido.
        boolean servirCafe = false;
        // Variable que nos dice si el café está dulce.
        boolean estaDulce = false;
        // Variable que controla cuanta azucar añadi al café.
        int azucarEchada = 0;


        /*
        * Condicional que pregunta "¿Hay café hecho?", si la
        * respuesta es "sí" (true) entonces calienta el café.
        */
        if (hayCafeHecho) {
            // Define el valor en "true", el café fue calentado.
            calentarCafe = true;
            // Da igual si se "calienta" o se "hace", se sirve en taza.
            servirCafe = true;
        }
        // Si la respuesta es "no" (false) entonces hace café.
        else {
            // Define el valor en "true", se hizo café nuevo.
            hacerCafe = true;
            // Da igual si se "calienta" o se "hace", se sirve en taza.
            servirCafe = true;
        }

        // Bucle que se repite hasta que el café este dulce.
        while (estaDulce == false) {
            // En cada iteración añade una cucharada de azucar.
            azucarEchada++;
            // Si ya ha añadido 3 cucharas, es suficiente, está dulce.
            if (azucarEchada == 3) {
                // Modifica el valor a "true".
                estaDulce = true;
            }
        }
    }
}

package GradoSuperiorDAW.SegundaEvaluacion.UT5EjerciciosConArrays;

public class MPAAEjercicio4Chivato {
    public static void main(String[] args) {
        //Programa que me avisa cuando ocurre algo.

        /*
        * Queremos saber si hay algún número par y mostrar el mensaje al
        * terminar de recorrer el array
        * */
        int[] numeros = {1, 3, 4, 6, 7, 3, 2, 5, 8, 9, 8};

        /*
        * Solo hay dos posibles resultados "true" o "false", luego usamos un boolean.
        * Suponemos que no hay ninguno
        * */
        boolean hayPares = false;
        // Bucle que recorre uno por uno todos los números del array.
        for(int numero:numeros) {
            // Si algún número es par convierte el "false" a "true".
            if(numero % 2 == 0){
                hayPares = true;
            }
        }
        System.out.println("La respuesta a si había algún numero par es : " + hayPares);
    }
}

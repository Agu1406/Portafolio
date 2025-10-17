package GradoSuperiorDAW.SegundaEvaluacion.UT5EjerciciosConArrays;

public class MPAAEjercicio2Acumulador {
    public static void main(String[] args) {
        //Queremos sumar y multiplicar los valores de un array
        int[] numeros = {1, 3, 4, 6, 7, 3, 2, 5, 8, 9, 8};

        // Creamos una variable que guarda las sumas parciales hasta ese momento. Empieza en cero.
        int sumaAcumulada = 0;

        /*
        * Lleva las multiplicaciones acumuladas hasta ese momento.
        * Empieza en uno, porque si empezara en cero, todas serían cero.
        * */
        int productoAcumulado=1;
        // Con un bucle recorremos uno por uno los números dentro del array "números".
        for(int numero : numeros) {
            sumaAcumulada = sumaAcumulada + numero;
            productoAcumulado = productoAcumulado * numero;
        }
        // Imprimimos por consola los resultados del programa.
        System.out.println("La suma total es: " + sumaAcumulada);
        System.out.println("La multiplicacion total es: " + productoAcumulado);
    }
}

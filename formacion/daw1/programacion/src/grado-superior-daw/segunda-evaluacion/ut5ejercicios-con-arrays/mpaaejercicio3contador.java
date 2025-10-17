package GradoSuperiorDAW.SegundaEvaluacion.UT5EjerciciosConArrays;

public class MPAAEjercicio3Contador {
    public static void main(String[] args) {
        //Programa que cuenta cuantos pares hay en un array
        int[] numeros = {1, 3, 4, 6, 7, 3, 2, 5, 8, 9, 8};

        //Contador que contará cuantos pares hay. Empieza en cero
        int cuentaPares = 0;

        // Recorre uno por uno todos los números del array que hemos creado.
        for(int numero : numeros) {
            // Si el número en la posición actual del for es par el contador incrementa.
            if(numero % 2 == 0){
                cuentaPares = cuentaPares + 1;
            }
        }
        // Fuera del bucle imprime cuantos pares hemos contado.
        System.out.println("El numero total de pares es: " + cuentaPares);
    }
}

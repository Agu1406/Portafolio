package GradoSuperiorDAW.SegundaEvaluacion.UT5EjerciciosConArrays;

public class MPAAEjercicio1IntercambioValores {
    public static void main(String[] args) {
        int[] numeros = {1, 3, 4, 6, 7, 3, 2, 5, 8, 9, 8};
        // Queremos intercambiar las posiciones 2 y 7 del array.
        int numeroAuxiliar; // Usamos una variable auxiliar para hacer el intercambio
        numeroAuxiliar = numeros[2];
        numeros[2] = numeros[7];
        numeros[7] = numeroAuxiliar;
        // Hemos intercambiado los valores y los mostramos todos para visualizarlo
        for (int numero : numeros) {
            System.out.println(numero);
        }
    }
}

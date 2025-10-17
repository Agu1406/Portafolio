package GradoSuperiorDAW.SegundaEvaluacion.UT5EjerciciosConArrays;

public class MPAAEjercicio5Aleatorios {
    public static void main(String[] args) {
        /*
         * Crea un programa que genere 10 números aleatorios entre 0 y 100
         * y luego los muestre por pantalla. A continuación, vuelve a
         * mostrarlos en sentido contrario.
         * */
        int[] aleatorios = llenarArray();

        /*
         * Usamos las dos funciones que hemos creado para imprimir los números
         * del array al derecho y al revés.
         * */
        imprimirArray(aleatorios);
        imprimirArrayInverso(aleatorios);
    }

    private static void imprimirArrayInverso(int[] aleatorios) {
        /*
         * Bucle que empieza desde la última posición del array hasta la posición
         * "0" imprimiendo todos los números en el orden inverso del array.
         * */
        for (int posicion = aleatorios.length - 1; posicion >= 0; posicion--) {
            System.out.println(aleatorios[posicion]);
        }
    }

    /**
     * Función que imprime todos los números de un array
     * en el mismo orden en el que están escritos.
     *
     * @param aleatorios (Array primitivo lleno de números enteros).
     */
    private static void imprimirArray(int[] aleatorios) {
        // Usamos un bucle que recorra uno por uno los números del array
        for (int numero : aleatorios) {
            // Imprime el número directamente
            System.out.println(numero);
        }
    }

    /**
     * Función que genera aleatorios "X" cantidad de veces y los
     * mete (push) dentro de un Array para al final devolver el
     * array lleno de Aleatorios.
     *
     * @return un Array lleno de "X" números aleatorios.
     */
    private static int[] llenarArray() {
        /*
         * Creamos un nuevo array primitivo de números enteros
         * llamado "arrayLleno" que inicializamos con su tamaño
         * ya que conocemos el tamaño maximo que tendra el cual
         * es "10".
         * */
        int[] arrayLleno = new int[10];

        // Bucle que genera 10 aleatorios y los guarda en el array.
        for (int posicion = 0; posicion < arrayLleno.length; posicion++) {
            // Generamos un número "aleatorio" entre "0" y "100".
            int aleatorio = (int) (Math.random() * 100);
            /*
             * Si el bucle está en la posición "0", coge el número
             * generado aleatoriamente en esa posición y en la siguiente
             * iteración en la "1", "2", etc. Hasta llenar las diez
             * posiciones del array.
             * */
            arrayLleno[posicion] = aleatorio;
        }
        // Retornamos el array ya lleno de los números generados.
        return arrayLleno;
    }
}

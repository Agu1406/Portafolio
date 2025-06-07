package Valencia.Alicante.UA.visorImagenesL04.menuFicheros;


import Valencia.Alicante.UA.visorImagenesL04.interfaz.ImageViewer;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Instancia del ImageView");

        ImageViewer nuevaInterfaz = new ImageViewer();

        int resultado = calculadoraQueSoloSuma(10, 20);
    }

    /**
     * Esto es una función llamada "calculadoraQueSoloSuma" la cual solo
     * suma dos números recibifos como argumentos.
     * @param unNumero - Es un número entero (int) para ser sumado.
     * @param otroNumero - Es otro número entero (int) para ser sumado.
     * @return devuelve un número entero (int) con el resultado de la suma.
     * @author Agustín.
     *
     */
    public static int calculadoraQueSoloSuma (int unNumero, int otroNumero) {
        int resultado = unNumero + otroNumero;

        return resultado;
    }
}

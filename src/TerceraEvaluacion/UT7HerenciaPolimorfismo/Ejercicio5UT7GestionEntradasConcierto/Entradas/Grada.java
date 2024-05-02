package TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto.Entradas;

import TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto.Excepciones.EntradasEnEsaZonaAgotadasException;

public class Grada extends Entrada {
    private static final double suplementoGrada = 10; // Suplemento por el tipo de entrada "grada".
    private static int numEntradaGrada = 1; // Controla la cantidad de instancias creadas.
    private static int disponiblesOeste = 2000;
    private static final int LIMITE_OESTE = 2000; // Maxima cantidad de posibles en zona "Oeste".
    private static int disponiblesEste = 2000;
    private static final int LIMITE_ESTE = 2000; // Maxima cantidad de posibles en zona "Este".
    private static int disponiblesSur = 2000;
    private static final int LIMITE_SUR = 3000; // Maxima cantidad de posibles en zona "Sur".
    private final String numAsiento; // El número de asiento asignado a la entrada.
    private final String zona; // Puede ser OESTE, ESTE o SUR.
    public Grada(String zona) {
        super("Grada"); // Llamo al constructor del padre e introduzco el tipo de entrada.
        this.numAsiento = definirAsiento();
        this.zona = zona;


        // Si en esa zona no quedan más entradas disponibles, arroja una excepción.
        if (disponiblesOeste == 0 || disponiblesEste == 0 || disponiblesSur == 0)
            throw new EntradasEnEsaZonaAgotadasException("¡Lo iento! " +
                    "No quedan más entradas en la zona " + this.zona);
        // Controla que la cantidad de entradas creadas no supere los límites.
        switch (this.zona) {
            case "OESTE":
                disponiblesOeste--;
                break;
            case "ESTE":
                disponiblesEste--;
                break;
            case "SUR":
                disponiblesSur--;
                break;
        }
    }

    @Override
    public double calcularPrecioFinal() {
        return super.getPrecio() + suplementoGrada;
    }

    private static String definirAsiento () {
        String inicioCod = "Asiento: ";
        String finalCod = String.format("%04d", numEntradaGrada);
        return inicioCod + finalCod;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Suplemento grada: " + suplementoGrada + "\n" +
                "Precio total: " + calcularPrecioFinal() + "\n" +
                "Asiento: " + numAsiento + "\n" +
                "Zona: " + zona;

    }

    public static int getLimiteEste() {
        return LIMITE_ESTE;
    }

    public static int getLimiteOeste() {
        return LIMITE_OESTE;
    }

    public static int getLimiteSur() {
        return LIMITE_SUR;
    }
}

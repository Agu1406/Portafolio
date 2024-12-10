package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto.Entradas;

public class VIP extends Entrada {
    private static final int LIMITE_VIP = 100;
    private static int contadorVIP = 1;
    private static final double suplementoVIP = 100;
    private final String CODVIP;

    public VIP() {
        super("VIP");
        this.CODVIP = generarCodVIP();
    }


    @Override
    public double calcularPrecioFinal() {
        return super.getPrecio() + suplementoVIP;
    }
@Override
    public String toString() {
        return super.toString() +
                "Suplemento VIP: " + suplementoVIP + "\n" +
                "Precio total: " + calcularPrecioFinal() + "\n" +
                "Código VIP: " + CODVIP + "\n";
    }

    private static String generarCodVIP () {
        String inicioCod = "VIP";
        String finalCod = String.format("%03d",contadorVIP);
        contadorVIP++;

        return  inicioCod + finalCod;
    }

    public static int getLimiteVip() {
        return LIMITE_VIP;
    }
}

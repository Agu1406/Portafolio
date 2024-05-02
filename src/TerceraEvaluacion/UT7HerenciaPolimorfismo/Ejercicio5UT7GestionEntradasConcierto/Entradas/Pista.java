package TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConcierto.Entradas;

public class Pista extends Entrada {
    private static final int LIMITE_PISTA = 2900;
    public Pista() {
        super("PISTA");
    }

    @Override
    public String toString() {
        return super.toString() +
                "Precio total: " + calcularPrecioFinal() + "\n";
    }

    @Override
    public double calcularPrecioFinal() {
        return super.getPrecio();
    }

    public static int getLimitePista() {
        return LIMITE_PISTA;
    }
}

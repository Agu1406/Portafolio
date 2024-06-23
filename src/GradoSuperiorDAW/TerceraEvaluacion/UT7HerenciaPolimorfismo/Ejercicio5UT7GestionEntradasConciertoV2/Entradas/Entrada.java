package GradoSuperiorDAW.TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio5UT7GestionEntradasConciertoV2.Entradas;

import java.time.LocalDateTime;

/**
 * <h1 style="text-align: center">Clase Entrada</h1><br>
 * <p style="text-align: justify">
 * Queremos generar una aplicación que me permita vender entradas para un concierto en un
 * gran pabellón como el Wiz-ink. La numeración de las entradas es única. En cada entrada
 * aparece el grupo que toca, la fecha y la hora de inicio. El precio inicial de cada
 * entrada es de 50€. Para las entradas vendidas, también se almacena un correo
 * electrónico, y fecha/hora de la compra.
 * </p><br>
 * <h2 style="text-align: center">Tenemos 3 tipos de entrada:</h2>
 * <ul style="text-align: justify">
 *     <li>Pista: tiene un LIMITE_PISTA que define el limite máximo de entradas de este tipo.</li>
 *     <li>Grada: Debe contener la zona y el asiento asignado. Tiene un suplemento de 10€.
 *     Hay 3 zonas ESTE (2000 entradas), SUR (3000 entradas) y OESTE (2000 entradas)</li>
 *     <li>VIP: Solo hay 100 entradas de este tipo, y es necesario distinguirlas. Estas
 *     tienen un suplemento de 100€.</li>
 * </ul>
 * <p style="text-align: justify">
 * Queremos generar un aplicación que gestiona las 10.000 entradas para el Wiz-ink.
 * De ellas, 100 son VIP, 7000 son de grada y 2900 de pista.</p>
 * <ul style="text-align: justify">
 *     <li>Nuestra aplicación permite comprar entradas en paquetes de máximo 20 entradas.</li>
 *     <li>Se debe controlar las entradas que han sido vendidas en cada momento, para
 *     poder mostrar en cada momento las entradas disponibles (en el caso de Grada,
 *     mostrar las disponibles en cada zona).</li>
 *     <li>Nunca se podrán vender más entradas de las disponibles.</li>
 *     <li>Queremos poder calcular cuanto dinero se ha recaudado por tipo de entrada y en total.</li>
 * </ul>
 */
public abstract class Entrada {
    /* El artista que toca en este concierto en específico no va a cambiar, es el que es y punto,
     * por eso es "final", porque no debería de cambiar en ningún momento, si viene "Shakira", el
     * concierto es de "Shakira" y nada más, además es "static" porque todas las entradas son de
     * su concierto, es un atributo global que toda la clase puede ver, leer y utilizar en
     * cualquier momento. */
    private static final String ARTISTA = "Shakira ft. Eminen";
    private static final double precioBase = 50; // Precio base de las entradas, sin suplementos.
    private static int entradasCreadas = 1; // Cantidad de entradas creadas para vender.
    /* El código de cada entrada es único, es una combinación de las letras "ENTR" que abrevian la
     * palabra "entrada" seguido de un número de 4 dígitos, si por ejemplo, la entrada actual que
     * se está creando es la 192, el código de esa entrada sería "ENTR0192", además es final, la
     * entrada ENTR0192 es única, su código no debería de cambiar jamás y no debería de poder
     * modificarse.*/
    private final String COD_ENTRADA;
    /* El tipo de entrada también es final, pero no es estático, hay diferentes tipos de entrada
     * y una entrada por ejemplo "VIP" no se puede convertir en "Grada" ni viceversa, por lo que
     * una vez creada la entrada, su "tipo" de entrada no debería de ser posible de cambiar. */
    private final String tipoEntrada; // En las clases hijo defino que tipo de entrada es realmente.
    // Atributos sin modificadores, son comunes.
    private static LocalDateTime fechaHoraConcierto = LocalDateTime.of(2024, 4, 25, 22, 15); // Fecha y hora de inicio del concierto.
    private LocalDateTime fechaHoraCompra; // Fecha y hora de la compra de la entrada.
    private String correoElectronico; // Correo electrónico del comprador.
    private boolean vendida; // Si la entrada se vende, se cambia a true.

    // CREACIÓN DE LOS GETTERS Y SETTERS

    public Entrada(String tipoEntrada) {
        this.COD_ENTRADA = crearCodEntrada();
        this.tipoEntrada = tipoEntrada;
        this.correoElectronico = null;
        this.fechaHoraCompra = null;

        String artistaActual = ARTISTA;
        double precioGeneral = precioBase;

        this.vendida = false;
    }

    private static String crearCodEntrada() {
        /* Toma el N.º de "entradas" creadas y le agrega tantos
         * ceros hagas falta a la izquierda hasta ser un número
         * de 5 dígitos. */
        String finalCod = String.format("%05d", entradasCreadas);
        // Es la parte principal del código, "ENTR"
        String incioCod = "ENTR";
        // La siguiente sera "1" número más de la creada anteriormente.
        entradasCreadas++;
        // Devuelve la union de "ENTR" + "elNúmeroDe5Dígitos".
        return incioCod + finalCod;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public LocalDateTime getFechaHoraConcierto() {
        return fechaHoraConcierto;
    }

    public void setFechaHoraConcierto(LocalDateTime fechaHoraConcierto) {
        this.fechaHoraConcierto = fechaHoraConcierto;
    }

    public static String getARTISTA() {
        return ARTISTA;
    }

    public LocalDateTime getFechaHoraCompra() {
        return fechaHoraCompra;
    }

    public void setFechaHoraCompra(LocalDateTime fechaHoraCompra) {
        this.fechaHoraCompra = fechaHoraCompra;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getEntradasCreadas() {
        return entradasCreadas;
    }

    public boolean isVendida() {
        return vendida;
    }

    public void setVendida(boolean vendida) {
        this.vendida = vendida;
    }

    public double getPrecio() {
        return precioBase;
    }

    public abstract double calcularPrecioFinal();

    public String toString() {
        return "Información General: \n " +
                "Fecha y hora de inicio: " + fechaHoraConcierto + "\n" +
                "Artista/s y/o grupo/s: " + ARTISTA + "\n" +
                "Tipo de entrada: " + tipoEntrada + "\n" +
                "N.º de entrada: " + COD_ENTRADA + "\n" +
                "Precio general: " + precioBase + "\n" +
                "INFORMACIÓN ADICIONAL SEGÚN TIPO DE ENTRADA: " + "\n";
    }

    public void comprarEntrada (String correoElectronico, LocalDateTime fechaHoraCompra) {
        this.correoElectronico = correoElectronico;
        this.fechaHoraCompra = fechaHoraCompra;
        this.vendida = true;
    }
}

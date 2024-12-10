package GradoSuperiorDAW.TerceraEvaluacion.UT7ObjetosAvanzados.Ejercicio3UT7ClaseRectangulo3Avance;

import java.util.Scanner;

/**
 * <h1>Ejercicio N.º3 del UT7 - Clase Rectángulo Avanzada - Parte 2</h1>
 * Esta clase extiende la funcionalidad de la Clase Rectángulo de los ejercicios N.º1 y
 * N.º2 del UT7, en este ejercício se buscan los siguientes objetivos:
 * <ul>
 *     <li>Un constructor sin parámetros, que haga que los valores
 * iniciales de las esquinas del rectángulo sean (0,0) y (1,1);</li>
 *     <li>Un constructor con cuatro parámetros: x1, y1, x2, y2, que
 * rellene los valores iniciales de los atributos del rectángulo con
 * los valores proporcionados a través de los parámetros.</li>
 *     <li>Un constructor con dos parámetros, base y altura, que cree
 * un rectángulo donde el vértice inferior derecho esté ubicado
 * en la posición (0,0) y que tenga una base y una altura tal y
 * como indican los dos parámetros proporcionados.</li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @since 09/03/2024
 */
class MPAAEjercicio3UT7 { // Sin modificador "public" para que solo sea accesible desde el paquete
    private static final String nombreFigura = "Rectángulo"; // Nombre genérico que tienen todos los rectángulos.
    private static final double valorDePI = 3.1416; // Valor final, estático y real del número PI.
    private static int numRectangulo = 0; // Cantidad de rectángulos creados hasta el momento.
    private String nombre; // Es el nombre personalizado que le daremos a cada rectángulo.
    private int x1, x2, y1, y2; // Todas las coordenadas "X" e "Y" de un posible rectángulo.
    private int numeroEstanciaCreada; // Número de creación único de cada estancia.

    /**
     * <h2>Este constructor corresponde al apartado N.º2 del tercer ejercicio.</h2>
     * <p>
     * Constructor de la clase "Rectángulo" que permite estanciar /crear rectángulos con valores
     * personalizados al mismo tiempo que comparten valores estáticos tales como el nombre
     * genérico "Rectángulo" o el valor con los primeros cuatro decimales del número PI.
     *</p>
     * @param nombre (String que recibe el constructor y es el nombre personalizado de la figura)
     * @param x1     (Int que indica la posición horizontal inferior izquierda de la figura)
     * @param x2     (Int que indica la posición horizontal inferior derecha de la figura)
     * @param y1     (Int que indica la posición vertical superior izquierda de la figura)
     * @param y2     (Int que indica la posición vertical superior derecha de la figura)
     */
    MPAAEjercicio3UT7(String nombre, int x1, int x2, int y1, int y2) {
        this.numeroEstanciaCreada = numRectangulo; // Asigna a la estancia el número que le corresponde por creación.
        double PI = valorDePI; // Asigna a la estancia el valor de PI.

        this.nombre = nombre; // nombre personalizado que se asigna al rectángulo / figura.
        this.x1 = x1; // Indica la posición horizontal inferior izquierda de la figura.
        this.x2 = x2; // Indica la posición horizontal inferior derecha de la figura.
        this.y1 = y1; // Indica la posición vertical superior izquierda de la figura.
        this.y2 = y2; // Indica la posición vertical superior derecha de la figura.

        // Para que la siguiente estancia sea el número siguiente después de esta se suma "1".
        numRectangulo++;
    }

    /**
     * <h2>Este constructor corresponde al apartado N.º1 del tercer ejercicio.</h2>
     * <p>Constructor sin parametros que estable por defecto los valores del
     * rectángulo en (0,0) y (1,1), también el nombre por defecto del
     * rectángulo y le asgina su número de estancia creada y el valor
     * de PI.</p>
     */
    MPAAEjercicio3UT7 () {
        this.x1 = 0; // Valores por defecto del constructor.
        this.x2 = 1; // Valores por defecto del constructor.
        this.y1 = 0; // Valores por defecto del constructor.
        this.y2 = 1; // Valores por defecto del constructor.

        double PI = valorDePI; // Valor final a cuatro decimales del número PI.
        numeroEstanciaCreada = numRectangulo; // Número de creación de la estancia.
        this.nombre = "Rectángulo N.º" + numRectangulo; // Nombre "default" del rectángulo.
        numRectangulo++; // Incrementa la variable para llevar registro de las estancias creadas.
    }
    /**
     * <h2>Este constructor corresponde al apartado N.º3 del tercer ejercicio.</h2>
     * <p>Constructor cuyos parámetros son "altura" y "base", crea una nueva
     * estancia, donde dibuja desde 0 hasta el valor ingresad al constructor
     * , el resto de valores como el nombre del rectángulo, su número de
     * estancia creada y el valor de PI son asignados automáticamente.</p>
     */
    MPAAEjercicio3UT7(int base, int altura) {
        this.x1 = 0; // Establece su primera "X" coordenada en 0;
        this.x2 = base; // partiendo desde 0, "dibuja" el rectángulo.
        this.y1 = 0; // Establece su primera "Y" coordenada en 0;
        this.y2 = altura; // partiendo desde 0, "dibuja" el rectángulo.


        double PI = valorDePI; // Valor final a cuatro decimales del número PI.
        numeroEstanciaCreada = numRectangulo; // Número de creación de la estancia.
        this.nombre = "Rectángulo N.º" + numRectangulo; // Nombre "default" del rectángulo.
        numRectangulo++; // Incrementa la variable para llevar registro de las estancias creadas.
    }
    /**
     * Método toString personalizado que me permite visualizar la información de una estancia
     * Rectángulo en un formato de fácil comprensión.
     *
     * @return (String con saltos de línea que refleja toda la información de la estancia)
     */
    @Override
    public String toString() {
        return "Datos del rectángulo: " + '\n' +
                "1. Coordenada X1: " + this.x1 + '\n' +
                "2. Coordenada Y1: " + this.y1 + '\n' +
                "3. Coordenada X2: " + this.x2 + '\n' +
                "4. Coordenada Y2: " + this.y2 + '\n' +
                "Nombre personalizado: " + this.nombre + '\n' +
                "Número de instancia: " + this.numeroEstanciaCreada + '\n' +
                "Nombre de la figura: " + nombreFigura + '\n' +
                "Valor de PI: " + valorDePI;
    }

    /**
     * Método de la clase "Rectángulo" que permite obtener / visualizar el valor
     * del atributo "nombre".
     *
     * @return nombre (String que contiene el nombre personalizado de cada rectángulo)
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método de la clase "Rectángulo" que permite modificar el valor del atributo
     * "nombre" incluso después de que ya ha sido definido / creado.
     *
     * @param nombre (String que recibe y sustituye/define el valor de "nombre" de esa estancia)
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Calcula la superficie (área) de un rectángulo dado por sus coordenadas.
     * -
     * Este método utiliza el valor absoluto de las diferencias entre las coordenadas
     * para asegurar que la altura y la anchura sean valores positivos. Esto permite
     * calcular correctamente la superficie del rectángulo independientemente de
     * las posiciones relativas de sus coordenadas.
     *
     * @return La superficie (área) del rectángulo, que es el producto de su altura y anchura.
     */
    protected int calcularSuperficie() {
        int altura, anchura, superficie; // Variables usadas internamente en el método.
        /*
         * Un escenário que se me vino a la mente creando este método es, ¿Qué pasaría
         * si mi usuário introduce un x1 > x2? ¿Tendría que usar un if-else para poder
         * calcúlar correctamente el ancho y alto del rectángulo? Fue entonces cuando
         * se me enseño la existencia de Math.abs que permite el calcúlo de valores
         * absolutos y me ahorra algunas líneas de código, por lo tanto: */
        altura = Math.abs(this.y1 - this.y2);
        anchura = Math.abs(this.x1 - this.x2);

        // Luego, la superficie de un rectángulo es su anchura multiplicada por su altúra, por lo tanto:
        superficie = altura * anchura;

        // Por último, devuelvo la superficie de esa estancia en concreto.
        return superficie; // Devuelve la superficie ocupada por esa estancia "rectángulo".
    }

    /**
     * Calcula el perímetro de un rectángulo dado por sus coordenadas.
     * -
     * Este método utiliza el valor absoluto de las diferencias entre las coordenadas
     * para asegurar que la altura y la anchura sean valores positivos. Esto permite
     * calcular correctamente el perímetro del rectángulo independientemente de
     * las posiciones relativas de sus coordenadas.
     *
     * @return La superficie (área) del rectángulo, que es el producto de su altura y anchura.
     */
    protected int calcularPerimetro() {
        int altura, anchura, perimetro; // Variables usadas internamente en el método.
        /*
         * Para calcular el perímetro de un rectángulo, sabiendo que todos los rectángulos
         * tienen dos pares de lados iguales, por ejemplo, el lateral izquierdo ha de ser
         * igual que el lateral derécha y de la misma forma, el borde superior ha de ser
         * igual que el borde inferior, sabiendo esto, podemos calcúlar el perímetro de
         * un rectángulo con la fórmula: Perimetro = 2 * (altura + anchura) y si
         * reciclamos el método anterior, podemos copiar-pegar la forma de
         * calcúlar la anchura y a altura: */
        altura = Math.abs(this.y1 - this.y2);
        anchura = Math.abs(this.x1 - this.x2);

        // Ya con los datos internos necesarios, calcúlamos el perímetro ( P = 2 * [A + L] )
        perimetro = 2 * (anchura + altura);

        // Ya calcúlado el perímetro, hacemos su debido return.
        return perimetro;
    }

    /**
     * Desplaza un rectángulo añadiendo a sus coordenadas el desplazamiento deseado (positivo o negativo).
     * -
     * Este método utiliza una cantidad "x" e "y" deseada de desplazamiento proporcionada por el usuario
     * mediante pantalla y teclado y modifica los atributos de una estancia permitiendo que este
     * desplazamiento deseado sea añadido a sus coordenadas originales.
     */
    protected void desplazarRectangulo() {
        Scanner teclado = new Scanner(System.in); // Scanner para la solicitud de datos por teclado.
        double desplazamientoX, desplazamientoY; // Variables usadas a nivel interno en el método.
        /*
         * Desplazar una estancia es bastante sencillo, para realizar semejante acción solo necesitamos
         * preguntar al usuario cuanto desea desplazar dicho rectángulo y sumar a sus coordenadas
         * actuales dicha cifra, sea positiva o negativa, no afectara las dimensiones y permite
         * desplazarlo sin mayor complicación, por lo tanto, solicitamos estos datos: */

        // Solicito por pantalla y teclado el desplazamiento del eje "x" deseado.
        System.out.println("Introduce el desplazamiento del eje \"x\" deseado: ");
        desplazamientoX = teclado.nextDouble();

        // Solicito por pantalla y teclado el desplazamiento del eje "y" deseado.
        System.out.println("Introduce el desplazamiento del eje \"y\" deseado: ");
        desplazamientoY = teclado.nextDouble();

        // Por último, a la estancia, le sumo a sus coordenadas el desplazamiento deseado.
        this.x1 += desplazamientoX; // Modifica la coordenada "x1" del rectángulo desde el que se invoca el método.
        this.x2 += desplazamientoX; // Modifica la coordenada "x2" del rectángulo desde el que se invoca el método.
        this.y1 += desplazamientoY; // Modifica la coordenada "y1" del rectángulo desde el que se invoca el método.
        this.y2 += desplazamientoY; // Modifica la coordenada "y2" del rectángulo desde el que se invoca el método.
    }

    /**
     * Consulta el número de estancias / rectángulos creados hasta el momento y se los devuelvel al usuario.
     *
     * @return numRectángulo (Int con la cantidad exactas de rectángulos creados hasta el momento)
     */
    protected int numeroDeRectangulosCreados() {
        return numRectangulo;
    }
}

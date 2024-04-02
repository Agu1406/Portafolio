package Ejercicio2UT7ClaseRectangulo2Avance;

import java.util.Scanner;

/**
 * <h1>Ejercicio N.º2 del UT7 - Clase Rectángulo Avanzada</h1>
 * Esta clase extiende la funcionalidad de la Clase Rectángulo del Ejercicio N.º1 del UT7,
 * introduciendo nuevos métodos y capacidades, incluyendo:
 * <ul>
 *     <li>Método getNombre - permite el acceso al nombre de un rectángulo.</li>
 *     <li>Método setNombre - permite la modificación del nombre de un rectángulo.</li>
 *     <li>Método calcularSuperficie - calcula el área encerrada por el rectángulo.</li>
 *     <li>Método calcularPerímetro - calcula la longitud del perímetro del rectángulo.</li>
 *     <li>Método desplazar - Mueve la ubicación del rectángulo en el plano en una cantidad
 *     X (para el eje X) y otra cantidad Y (para el eje Y). Se sumará X a las coordenadas x1
 *     y x2, e Y a las coordenadas y1 e y2. Los parámetros de entrada de este método serán, por
 *     lo tanto, "X" e "Y", de tipo double solicitados al usuario por pantalla para que sean utilizados.</li>
 *     <li>Método obtenerNumRectángulos - Devuelve el número exacto de rectángulos creados hasta el momento.</li>
 * </ul>
 *
 * @author Agu1406 (Agustín)
 * @since 08/03/2024
 */
class MPAAEjercicio2UT7 { // Sin modificador "public" para que solo sea accesible desde el paquete
    /*
     * En el primer ejercicio creamos los siguientes atributos utilizando y siguiendo las pautas
     * expuestas por el profesor es nuestra introducción a la Unidad Teórica 7 de programación en Java:
     *
     * ⚫ Atributos x1, y1 que almacenan el vértice inferior izdo.
     * ⚫ Atributos x2, y2 que almacenan el vértice superior dcho.
     * ⚫ Atributo nombre - Almacena el nombre que se le quiera dar a cada rectángulo.
     * ⚫ Atributo nombreFigura - Almacena el nombre de la clase, es decir, "Rectángulo".
     * ⚫ Atributo numRectángulos - Almacena el número de objetos de tipo rectángulo creados.
     * ⚫ Atributo PI - Contiene el valor de PI con una precisión de cuatro cifras decimales.
     *
     * Especificaciones: No se desea que los atributos nombre y numRectángulos puedan ser visibles desde
     * fuera de la clase. Y además se desea que la clase sea accesible solamente desde su propio paquete.
     * -------------------------------------------------------------------------------------------------
     * En la segunda parte del ejercício, se nos asignó la misión de añadir los siguientes metodos a la
     * clase "rectángulo" previamente creada siguiendo el mismo tipo de pauta y especificaciones:
     *
     * ⚫ Método getNombre - permite el acceso al nombre de un rectángulo.
     * ⚫ Método setNombre - permite la modificación del nombre de un rectángulo.
     * ⚫ Método calcularSuperficie - calcula el área encerrada por el rectángulo.
     * ⚫ Método calcularPerímetro - calcula la longitud del perímetro del rectángulo.
     * ⚫ Método desplazar - Mueve la ubicación del rectángulo en el plano en una cantidad
     * X (para el eje X) y otra cantidad Y (para el eje Y). Se sumará X a las coordenadas x1
     * y x2, e Y a las coordenadas y1 e y2. Los parámetros de entrada de este método serán, por
     * lo tanto, "X" e "Y", de tipo double solicitados al usuario por pantalla para que sean utilizados.
     * ⚫ Método obtenerNumRectángulos - Devuelve el número exacto de rectángulos creados hasta el momento.
     *
     * */
    private static final String nombreFigura = "Rectángulo"; // Nombre genérico que tienen todos los rectángulos.
    private static final double valorDePI = 3.1416; // Valor final, estático y real del número PI.
    private static int numRectangulo = 0; // Cantidad de rectángulos creados hasta el momento.
    private String nombre; // Es el nombre personalizado que le daremos a cada rectángulo.
    private int x1, x2, y1, y2; // Todas las coordenadas "X" e "Y" de un posible rectángulo.
    private int numeroEstanciaCreada; // Número de creación único de cada estancia.

    /**
     * Constructor de la clase "Rectángulo" que permite estanciar /crear rectángulos con valores
     * personalizados al mismo tiempo que comparten valores estáticos tales como el nombre
     * genérico "Rectángulo" o el valor con los primeros cuatro decimales del número PI.
     *
     * @param nombre (String que recibe el constructor y es el nombre personalizado de la figura)
     * @param x1     (Int que indica la posición horizontal inferior izquierda de la figura)
     * @param x2     (Int que indica la posición horizontal inferior derecha de la figura)
     * @param y1     (Int que indica la posición vertical superior izquierda de la figura)
     * @param y2     (Int que indica la posición vertical superior derecha de la figura)
     */
    MPAAEjercicio2UT7(String nombre, int x1, int x2, int y1, int y2) {
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

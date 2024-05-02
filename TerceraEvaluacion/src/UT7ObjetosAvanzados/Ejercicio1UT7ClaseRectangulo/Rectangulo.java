package UT7ObjetosAvanzados.Ejercicio1UT7ClaseRectangulo;

/**
 * Ejercício N.º1 del UT7 - Creación de una clase Rectángulo, con su Main, introducción
 * al mundo del polimorfismo, herencia, abstracción, en este ejercício particularmente
 * aprendimos bastante sobre los modificadores de acceso y los atributos de las
 * variables en nuestros programas/clases Java.
 *
 * @author Agu1406 (Agustín)
 * @since 07/03/2024
 */
class Rectangulo {
/*
* Crear una clase Rectángulo, con:
* ⚫ Atributos x1, y1 que almacenan el vértice inferior izdo.
*
* ⚫ Atributos x2, y2 que almacenan el vértice superior dcho.
*
* ⚫ Atributo numRectángulos, que almacena el número de objetos de tipo rectángulo creados hasta el momento.
*
* ⚫ Atributo nombre, que almacena el nombre que se le quiera dar a cada rectángulo.
*
* ⚫ Atributo nombreFigura, que almacena el nombre de la clase, es decir, "Rectángulo".
*
* ⚫ Atributo PI, que contiene el nombre de la constante PI con una precisión de cuatro cifras decimales.
*
* ⚫ No se desea que los atributos nombre y numRectángulos puedan ser visibles desde fuera de la clase. Y además se
* desea que la clase sea accesible solamente desde su propio paquete.
* */

    /*
    *  La primera parte del ejercicio es definir los atributos de los que se hara uso, ahora explico sus
    * declaraciones:
    *
    * - Privates (Privados) cuando un atributo es privado significa que solo puede ser modificado o solo
    * se puede interactuar con el dentro de la propia clase donde son declarados / creados, fuera de la
    * clase se deben usar Getters y Setters para manipularlos, de caso contrario, no es posible.
    *
    * - Static (Estáticos) En el contexto de este programa significa que son "universales", por ejemplo
    * el atributo "X1, X2, Y1, Y2" será diferente en cada estancia del objeto/clase Rectángulo, pero
    * todos los rectángulos comparten el mismo nombre genérico, "Rectángulo", por eso es "Estático",
    * ya que todas las estancias tendrían dentro de sí mismas exactamente el mismo nombre y este no
    * será una copia de sí mismo varias veces, todas tendrán el mismo nombre del mismo String en
    * el mismo espacio de la memoria donde se aloje inicialmente.
    *
    * - Final (Final) son aquellos cuyo valor es final y no debería ser cambiado por ética y diseño,
    * por ejemplo, el valor de PI es final, es el que es y no cambia con el paso del tiempo y todos
    * los rectángulos son "rectángulos", por lo que el nombre también es final.
    * */
    private static final String nombreFigura = "Rectángulo"; // Nombre genérico que tienen todos los rectángulos.
    private static final double valorDePI = 3.1416; // Valor final, estático y real del número PI.
    private static int numRectangulo = 0; // Cantidad de rectángulos creados hasta el momento.
    private String nombre; // Es el nombre personalizado que le daremos a cada rectángulo.
    private int x1, x2, y1, y2; // Todas las coordenadas X y Y de un posible rectángulo.
    private int numeroEstanciaCreada;

    /*
    * Lo siguiente para que funcione nuestra clase es crear los constructores, estos nos
    * permiten "estanciar" objetos de la clase "Rectángulo", es decir, nos permite crear
    * rectángulos, en nuestro constructor usaremos todos los atributos, introduciendo en
    * las estancias el valor de PI, el nombre genérico de la figura y el número que nos
    * indica cuantas estancias se han creado hasta el momento.
    * */

    /**
     * Constructor de la clase "Rectángulo" que permite estanciar /crear rectángulos con valores
     * personalizados al mismo tiempo que comparten valores estáticos tales como el nombre
     * genérico "Rectángulo" o el valor con los primeros cuatro decimales del número PI.
     *
     * @param nombre (String que recibe el constructor y es el nombre personalizado de la figura)
     * @param x1 (Int que indica la posición horizontal inferior izquierda de la figura)
     * @param x2 (Int que indica la posición horizontal inferior derecha de la figura)
     * @param y1 (Int que indica la posición vertical superior izquierda de la figura)
     * @param y2 (Int que indica la posición vertical superior derecha de la figura)
     */
    public Rectangulo(String nombre, int x1, int x2, int y1, int y2) {
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
     * Metodo toString personalizado que me permite visualizar la información de una estancia
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
}

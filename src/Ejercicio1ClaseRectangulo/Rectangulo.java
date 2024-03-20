package Ejercicio1ClaseRectangulo;

class Rectangulo {
/*
* Crear una clase Rectángulo, con:
* ⚫ Atributos x1, y1 que almacenan el vértice inferior izdo.
*
* ⚫ Atributos x2, y2 que almacenan el vértice superior dcho.
*
* ⚫ Atributo numRectangulos, que almacena el número de objetos de tipo rectángulo creados hasta el momento.
*
* ⚫ Atributo nombre, que almacena el nombre que se le quiera dar a cada rectángulo.
*
* ⚫ Atributo nombreFigura, que almacena el nombre de la clase, es decir, "Rectángulo".
*
* ⚫ Atributo PI, que contiene el nombre de la constante PI con una precisión de cuatro cifras decimales.
*
* ⚫ No se desea que los atributos nombre y numRectangulos puedan ser visibles desde fuera de la clase. Y además se
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
    * - Static (Estaticos) En el contexto de este programa significa que son "universales", por ejemplo
    * el atributo "X1, X2, Y1, Y2" sera diferente en cada estancia del objeto/clase Rectangulo, pero
    * todos los rectangulos comparten el mismo nombre generico, "Rectangulo", por eso es "Estatico"
    * ya que todas las estancias tendran dentro de si mismas exactamente el mismo nombre y este no
    * sera una copia de si mismo varias veces, todas tendran el mismo nombre del mismo String en
    * el mismo espacio de la memoria donde se aloje inicialmente.
    *
    * - Final (Final) son aquellos cuyo valor es final y no deberia ser cambiado por etica y diseño,
    * por ejemplo, el valor de PI es final, es el que es y no cambia con el paso de ltiempo y todos
    * los rectangulos son "rectangulos", por lo que el nombre tambien es final.
    * */
    private static final String nombreFigura = "Rectangulo"; // Nombre generico que tienen todos los rectangulos.
    private static final double valorDePI = 3.1416; // Valor final, estatico  y real del numero PI.
    private static int numRectangulo = 0; // Cantidad de rectangulos creados hasta el momento.
    private String nombre; // Es el nombre personalizado que le daremos a cada rectangulo.
    private int x1, x2, y1, y2; // Todas las coordenadas X y Y de un posible rectangulo.

}

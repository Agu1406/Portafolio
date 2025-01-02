package GradoSuperiorDAW.SegundaEvaluacion.UT5ClaseCuenta;

import java.util.Objects;

public class Persona {
    // Creamos todos los atributos típicos de una persona, dos de ellos un String y uno un Integer
    private String nombre;
    private String DNI;
    private Integer edad;

    // Creamos el constructor de la clase Persona que permite instanciar "personas".
    public Persona(String nombre, String DNI, Integer edad) {
        /*
        * El nombre de ESTA (THIS) instancia será el "nombre" recibido como argumento,
        * a eso se refiere "this" en el contexto de los constructores
        * */
        this.nombre = nombre;
        this.DNI = DNI;
        this.edad = edad;
    }

    /*
    * En el contexto de los métodos "get" y "set" tenemos que preguntarnos que atributos
    * deseamos que sean visibles y/o modificables.
    *
    * ¿El nombre de una persona podemos verlo o modificarlo? Si, tiene ambos, get y set.
    * ¿El DNI de una persona podemos verlo o modificarlo? No, solo verlo, tiene get.
    * ¿La edad de una persona cambia? Si, con el tiempo, podemos verla también, get y set.
    * */
    public String getNombre() {
        return nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /*
    * Ahora viene la creación de los métodos especificos de la clase
    * propuestos por el profesor los cuales son:
    *
    * - toString(): Hace override al nativo de Java y usando Stringbuilder
    * este va haciendo "appends" de los diferentes atributos de una persona
    * siempre que estos no sean nulos.
    *
    * - mostrar(): Imprime todos los atributos de una instancia de la clase
    * persona sin importar sus valores (pueden ser nulos).
    *
    * - equals(): Sustituye al nativo de Java, es booleano, devuelve true
    * si dos instancias son iguales en todos sus aspectos o false si no
    * lo son.
    *
    * - esMayorDeEdad(): También es un boolean, devuelve "true" si la edad
    * es mayor o igual a "18" o false si no lo es o el valor es null.
    * */

    @Override
    public String toString() {
        // Creamos el StringBuilder con valor inicial de DNI, el unico que nunca es null.
        StringBuilder respuesta = new StringBuilder("DNI: " + DNI);
        // Si el valor de "nombre" es diferente de null ("!=null") se agrega a la respuesta.
        if (nombre != null) {
            respuesta.append(", Nombre: ").append(nombre);
        }
        // Si el valor de "edad" es diferente de null ("!=edad") se agrega a la respuesta.
        if (edad != null) {
            respuesta.append(", Edad: ").append(edad);
        }
        // Transformamos el StringBuilder a String para que sea un return valido.
        return respuesta.toString();
    }

    public void mostrar() {
        System.out.println("DNI: " + DNI + ", nombre: " + nombre + ", edad: " + edad + ".");
    }

    @Override
    public boolean equals(Object instancia) {
        // Si la instancia es "null" o no es la misma clase ("Persona") devuelve false.
        if (instancia == null || getClass() != instancia.getClass()) return false;
        /*
        * Convierte la "instancia" a un objeto de la clase persona para poder de
        * esa forma usar el "Objects.equals" con la clave que confirma si dos
        * instancias son el mismo objeto o no, en nuestro caso, el DNI.
        * */
        Persona persona = (Persona) instancia;
        return Objects.equals(DNI, persona.DNI);
    }

    /*
    * Se genera automáticamente, aún en la UT5 no teníamos ni idea de que era HashCode
    * o para que se usaba.
    * */
    @Override
    public int hashCode() {
        return Objects.hashCode(DNI);
    }

    /**
     * Función que verifica de entrada si el argumento recibido
     * es un Interger valido y no un de valor null, si fuese
     * null avisa del error sin returnan nada, si es un
     * número valido returna "true" si ">=18" y false si
     * no.
     *
     * @return "true" si es mayor de 18 años, "false" si no.
     */
    public boolean esMayorDeEdad () {
        if (this.edad == null) {
            System.out.println("¡Error! La edad debe ser un número entero valido.");
        } else {
            return this.edad >= 18;
        }
        return false;
    }
}

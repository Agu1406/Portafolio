package GradoSuperiorDAW.SegundaEvaluacion.UT5ClasePersona;

public class Persona {
    // Creamos todos los atributos típicos de una persona, dos de ellos un String y uno un Integer
    private String nombre;
    private String DNI;
    private Integer edad;

    // Creamos el constructor de la clase Persona que permite instanciar "personas".
    public Persona(String nombre, String DNI, Integer edad) {
        /*
        El nombre de ESTA (THIS) instancia será el "nombre" recibido como argumento,
        a eso se refiere "this" en el contexto de los constructores
        */
        this.nombre = nombre;
        this.DNI = DNI;
        this.edad = edad;
    }

    /*
    En el contexto de los métodos "get" y "set" tenemos que preguntarnos que atributos
    deseamos que sean visibles y/o modificables.

    ¿El nombre de una persona podemos verlo o modificarlo? Si, tiene ambos, get y set.
    ¿El DNI de una persona podemos verlo o modificarlo? No, solo verlo, tiene get.
    ¿La edad de una persona cambia? Si, con el tiempo, podemos verla también, get y set.
     */
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
}

package GradoSuperiorDAW.SegundaEvaluacion.UT5ClasePersona;

public class Main {
    public static void main(String[] args) {
        // Instanciamos tres personas con diferentes atributos de prueba.
        Persona persona1= new Persona("Jaime","111111A",25);
        Persona persona2= new Persona("Fernando","222222B",53);
        Persona persona3= new Persona("Paco","333333E",5);
        // Probamos las funciones de la clase persona ("mostrar", "toString" y "esMayorDeEdad").
        persona1.mostrar();
        System.out.println(persona2);
        System.out.println(persona3.esMayorDeEdad());
        // Probamos más funciones (el "set" de edad y "mostrar" con "toString").
        persona3.setEdad(null);
        persona3.mostrar();
        System.out.println(persona3.esMayorDeEdad());
        // Intentamos setear un nombre a null y lo mostramos a ver que ocurre.
        persona3.setNombre(null);
        persona3.mostrar();
        System.out.println(persona3);
    }
}

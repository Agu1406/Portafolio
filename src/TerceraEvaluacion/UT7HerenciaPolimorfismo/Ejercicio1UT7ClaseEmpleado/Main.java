package TerceraEvaluacion.UT7HerenciaPolimorfismo.Ejercicio1UT7ClaseEmpleado;

public class Main {
    public static void main(String[] args) {
        // Estancio un nuevo objeto "Empleado", usando el constructor, introduciendo "nombre" y "sueldo".
        Empleado nuevoEmpleado = new Empleado("Eduardo", 1200);
        // Utilizo el método "mostrar" de la clase "Empleado".
        nuevoEmpleado.mostrar();
        // Utilizo el método de la clase "empleado" para subir el sueldo.
        nuevoEmpleado.subirSueldo(500);

        // Estancio un nuevo objeto "Jefe", usando el constructor que hereda de "Empleado", con "presupuesto".
        Jefe nuevoJefe = new Jefe("Agustín", 1900, 20000);
        // Utilizo el método "mostrar" de la clase "Jefe".
        nuevoJefe.mostrar();
        // Asigno un presupuesto al jefe usando el método asignarPresupuesto de la clase "Jefe".
        nuevoJefe.asignarPresupuesto(25000);

        // Estancio un nuevo objeto "Currito" usando el constructor que hereda de "Empleado", con "jefe"
        Currito nuevoCurrito = new Currito("Victor", 200, "Agustín");
        // Utilizo el método "mostrar" de la clase "Currito".
        nuevoCurrito.mostrar();
        // Asigno un jefe al "Currito" usando el método AsignarJefe de la clase "Currito".
        nuevoCurrito.asignarJefe("Paula");
    }
}

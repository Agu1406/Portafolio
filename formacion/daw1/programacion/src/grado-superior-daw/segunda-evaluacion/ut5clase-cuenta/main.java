package GradoSuperiorDAW.SegundaEvaluacion.UT5ClaseCuenta;

public class Main {
    public static void main(String[] args) {

        //Necesitamos varias personas para crear las cuentas
        Persona persona1= new Persona("Jaime","111111A",25);
        Persona persona2= new Persona("Fernando","222222B",53);
        Persona persona3= new Persona("Paco","333333E",5);

        //Creamos varias cuentas
        Cuenta cuenta1 = new Cuenta(11111, persona1,25000f);
        Cuenta cuenta2 = new Cuenta(22222, persona2,-25000f);

        //Hacemos pruebas
        System.out.println(cuenta1);
        System.out.println(cuenta2);

        System.out.println("Sacamos dinero:");
        cuenta1.retirar(12);
        System.out.println(cuenta1);

        System.out.println("Ingresamos dinero:");
        cuenta2.ingresar(785);
        System.out.println(cuenta2);


        //Comparamos cuentas
        System.out.println("Comparamos cuentas");
        System.out.println(cuenta1.equals(cuenta2));
        System.out.println(cuenta1.equals(cuenta1));
    }
}

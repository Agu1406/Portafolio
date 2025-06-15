package Madrid.entornos_desarrolllo.Usuarios;

/**
 *
 * @author Agu1406
 */
public class Principal {
    /**
     * Método principal que crea varios objetos Usuario y valida sus datos.
     * 
     * @param args Argumentos del programa.
     */
    public class Main {
        public static void main(String[] args) {
            // A la hora de depurar el metodo validarDatos puedes modificar los datos de este usuario para hacer
            // que se pase por todos los puntos pedidos en el enunciado. O crear tantos usuarios como consideres
            // necesarios para poder comprobar todos los datos de forma correcta. 
            Usuario[] usuarios = new Usuario[1];
            usuarios[0] = new Usuario("Marta", "Montes", "mmontes@email.es", "Passwr0d", 25);
            for (Usuario usuario : usuarios) {
                if (usuario.validarDatos()) {
                    System.out.println("Los datos de " + usuario.getNombre() + " " + usuario.getApellido() + " son válidos.");
                } else {
                    System.out.println("Los datos de " + usuario.getNombre() + " " + usuario.getApellido() + " no son válidos.");
                }
            }
        }
    }
}
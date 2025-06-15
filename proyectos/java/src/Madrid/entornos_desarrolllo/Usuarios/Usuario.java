package Madrid.entornos_desarrolllo.Usuarios;

/**
 * Clase que representa a un usuario con nombre, apellido, email, pass y edad.
 *
 * @author agu1406
 */
public class Usuario {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private int edad;

    /**
     * Constructor de la clase Usuario.
     *
     * @param nombre   Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param email    Email del usuario.
     * @param password Contraseña del usuario.
     * @param edad     Edad del usuario.
     */
    public Usuario(String nombre, String apellido, String email, String password, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.edad = edad;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre el nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return el apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param apellido el apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el email del usuario.
     *
     * @return el email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     *
     * @param email el email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password la contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return la edad del usuario.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     *
     * @param edad la edad del usuario.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Este método nos permite comprobar si los datos de un usuario
     * cumplen con los siguientes requisitos:
     *  Nombre y apellido no pueden estar vacíos, y ambos empiezan por una letra mayúscula.
     *  Email debe contener un '@' pero no puede empezar por '@', además deberá acabar con un '.' seguido de 2 o 3 letras.
     *  La contraseña debe tener al menos 8 caracteres, contener al menos un número y una letra mayúscula.
     *  Debe ser mayor de edad y no tener más de 100 años.
     * @return informa si cumple o no los requisitos
     */
    public boolean validarDatos() {
        
        if (this.getNombre() == null || this.getNombre().isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío");
            return false;
        }

        if (!Character.isUpperCase(this.getNombre().charAt(0))) {
            System.out.println("Error: El nombre debe empezar por mayúscula");
            return false;
        }

        if (this.getApellido() == null || this.getApellido().isEmpty()) {
            System.out.println("Error: El apellido no puede estar vacío");
            return false;
        }

        if (!Character.isUpperCase(this.getApellido().charAt(0))) {
            System.out.println("Error: El apellido debe empezar por mayúscula");
            return false;
        }

        if (!this.getEmail().contains("@") || !this.getEmail().contains(".")) {
            System.out.println("Error: El email debe contener '@' y '.'");
            return false;
        } else { 
            if (this.getEmail().startsWith("@")) {
                System.out.println("Error: El email no puede empezar por '@'");
                return false;
            }
            if (this.getEmail().substring(this.getEmail().lastIndexOf(".") + 1).length() < 2 ||
                this.getEmail().substring(this.getEmail().lastIndexOf(".") + 1).length() > 3) {
                System.out.println("Error: El email debe terminar con '.' seguido de 2 o 3 letras");
                return false;
            }
        }

        if (this.getPassword().length() < 8) {
            System.out.println("Error: Contraseña muy corta");
            return false;
        } else if (!password.matches(".*[0-9].*")) {
            System.out.println("Error: La contraseña debe contener números");
            return false;
        } else if (password.equals(password.toLowerCase())) {
            System.out.println("Error: La contraseña debe contener mayúsculas");
            return false;
        }

        if (edad < 18 || edad > 100) {
            System.out.println("Error: Edad fuera del rango permitido (18-100 años)");
            return false;
        }

        System.out.println("Todos los datos son válidos");
        return true;
    }
}
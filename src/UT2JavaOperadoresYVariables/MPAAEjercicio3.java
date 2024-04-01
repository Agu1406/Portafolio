package UT2JavaOperadoresYVariables;

/**
 * Ejercicio N.º 3 ¿Qué crees que mostrará por pantalla el siguiente código?.
 *
 * @author Agu1406 (Agustín)
 * @since 06/10/2023
 */
public class MPAAEjercicio3 {
    public static void main(String[] args) {
        int i;
        /* Idéntico al ejercicio anterior, en el primer "println" se imprime el "1" y
         * después de que ha sido impreso, se le suma "1", por lo que la siguiente vez
         * que es impreso al haberse sumado anteriormente "1" ahora es un "2". */
        i = 1;
        System.out.println(i++);
        System.out.println(i);

        /*  Idéntico al ejercicio anterior, en el primer "println" se imprime el "2" ya
         * qué se le suma "1" antes de imprimirlo, por lo tanto, imprime el "2" y en el
         * siguiente println como "i" no sufre cambios, imprime nuevamente el "2". */
        i = 1;
        System.out.println(++i);
        System.out.println(i);
    }
}


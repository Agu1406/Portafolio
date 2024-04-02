package Ejercicio6UT7JuegoTresEnRayaGrupal;

public class Turno {
    private int turno;

    /**
     * Consctructor que permite definir de quien es el turno.
     * @param turno (numero entero que empieza en 0)
     */
    Turno (int turno) {
        this.turno = 0;
    }

    @Override
    public String toString() {
        return "turno = " + turno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno1 = (Turno) o;
        return turno == turno1.turno;
    }

    // Expresión Regular que controla que el turno sea valido (0 o 1)
    String regexTurno = "^[0-1]$";

    /**
     * Verifica que el turno es valido (un 0 o un 1)
     * @return el turno actual (0 o 1)
     * @throws excepcionTurnoNoValido
     */
    public int quienToca() throws excepcionTurnoNoValido {
        if (turno == 0 || turno == 1)
            return turno;
        else {
            throw new excepcionTurnoNoValido(); }
    }

    /**
     *
     * @return
     * @throws excepcionTurnoNoValido
     */
    public int quienNoToca() throws excepcionTurnoNoValido {
        if (turno < 0 || turno > 1) {
            throw new excepcionTurnoNoValido();
            }
        else {
            // Si el turno es 0, me devuelve "1" y viceversa.
            turno = (turno == 0) ? 1 : 0;
            return turno;
        }
    }

    /**
     * Permite cambiar el turno, si actualmente es 0, cambia al opuesto
     * y viceversa.
     * @throws excepcionTurnoNoValido
     */
    public void cambiar() throws excepcionTurnoNoValido {
        if (turno == 0 || turno == 1) {
            turno = (turno == 0) ? 1 : 0;
        } else {
            throw new excepcionTurnoNoValido();
        }
    }
}

class excepcionTurnoNoValido extends Exception {
    public excepcionTurnoNoValido () {
        super("Turno no válido, solo es posible encontrarse en el turno 0 o 1");
    }
}
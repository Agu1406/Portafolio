package sistema_gestion_banco;

public class CuentaBancaria {
    // ATRBITUOS DE LA CLASE //

    // Creamos un atributo double que guarda el saldo de la cuenta (puede tener decimales)
    private double saldo;
    // Creamos un String que guarda el número de cuenta.
    private String numeroCuenta;

    // CONSTRUCTORES DE LA CLASE //

    /**
     * Constructor de la clase "CuentaBancaria" que permite
     * abrir (instanciar) cuentas de banco con saldo en
     * "-1", recibe como argumentos el número de cuenta
     * del cliente y un saldo.
     */
    public CuentaBancaria(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = -1;
    }

    /**
     * Constructor de la clase "CuentaBancaria" que permite
     * abrir (instanciar) cuentas de banco con saldo en
     * "0", recibe como argumento el número de cuenta
     * del cliente.
     */
    public CuentaBancaria(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0;
    }

    // MÉTODOS DE LA CLASE //

    public void ingresarDinero(double cantidad) {
        /*
         * assert se lee como lo siguiente "¿Esto es verdadero o falso?" Si es verdadero sigue
         * adelante, si es falso, envia un mensaje de error personalizado.
         * */
        assert cantidad > 0 : "¡Error! La cantidad de dinero a ingresar debe ser un número positivo";
        /*
         * Si saldo actualmente fuese "200" y el efectivo a ingresar fuese "50" entonces
         * saldo = 200 + 50;
         * */
        saldo = saldo + cantidad;
    }

    public void retirarDinero(double cantidad) throws SaldoInsufienteException {
        // Primer control de errores, evita que el usuario intente retirar "0" euros o menos.
        assert cantidad > 0 : "¡Error! La cantidad de dinero que desea retirar debe ser mayor a cero";
        // Segundo control de errores, evita que el usuario retire más dinero del que tiene actualmente.
        if (cantidad > saldo) {
            throw new SaldoInsufienteException ("¡Error! Saldo insuficiente, intente una cantida más pequeña");
        }
        // Actualiza el saldo actual de la cuenta restando la cantidad retirada.
        saldo = saldo - cantidad;
    }

    // MÉTODOS GETTERS Y SETTERS //

    public double getSaldo() {
        return saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }
}

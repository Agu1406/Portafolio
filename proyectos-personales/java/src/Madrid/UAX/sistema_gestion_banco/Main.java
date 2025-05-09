package Madrid.UAX.sistema_gestion_banco;

public class Main {
    public static void main(String[] args) throws SaldoInsufienteException {
        // Creamos las dos cuentas de banco, una con saldo "0", otra con saldo "-1".
        CuentaBancaria cuentaBancariaSaldo0 = new CuentaBancaria("ES0100000000000000000001");
        CuentaBancaria cuentaBancariaSaldoN = new CuentaBancaria("ES0100000000000000000002", -1);

        // PRIMERA PARTE - EJERCICIOS CON LA CUENTA CON SALDO "0" //

        // En la primera cuenta depositamos 1000€ y retiramos 500€
        cuentaBancariaSaldo0.ingresarDinero(1000);
        cuentaBancariaSaldo0.retirarDinero(500);
        // Immprimo por pantalla ambos, el saldo de la cuenta y el número de cuenta.
        System.out.println("Saldo de la cuenta: " + cuentaBancariaSaldo0.getSaldo());
        System.out.println("Número de la cuenta: " + cuentaBancariaSaldo0.getNumeroCuenta());

        // SEGUNDA PARTE - EJERCICIOS CON LA CUENTA CON SALDO "-1". //

        // Intentamos depositar 1000, retirar 500, depositar 200, retirar 800, si ocurre un error lo atrapmos.
        try {
            cuentaBancariaSaldoN.ingresarDinero(1000);
            cuentaBancariaSaldoN.retirarDinero(500);
            cuentaBancariaSaldoN.ingresarDinero(200);
            cuentaBancariaSaldoN.retirarDinero(800);
            // Probamos las aserciones
            cuentaBancariaSaldoN.ingresarDinero(0);
            cuentaBancariaSaldoN.retirarDinero(-1);
        } catch (SaldoInsufienteException exception) {
            System.out.println("¡Lo siento! Su saldo actual es insuficiente para realizar está operación.");
        }
        // Impprimo por pantalla ambos, el saldo de la cuenta y el número
        System.out.println("Saldo de la cuenta: " + cuentaBancariaSaldoN.getSaldo());
        System.out.println("Número de la cuenta: " + cuentaBancariaSaldoN.getNumeroCuenta());

    }
}

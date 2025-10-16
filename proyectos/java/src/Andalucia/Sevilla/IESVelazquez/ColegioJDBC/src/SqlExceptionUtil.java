package Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src;

import java.sql.SQLException;

// Utilidad para imprimir detalles de SQLException (SQLState y vendorCode)
public final class SqlExceptionUtil {
    private SqlExceptionUtil() {}

    public static void printSQLException(SQLException e) {
        SQLException ex = e;
        while (ex != null) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("  SQLState: " + ex.getSQLState());
            System.err.println("  ErrorCode: " + ex.getErrorCode());
            ex = ex.getNextException();
        }
    }
}



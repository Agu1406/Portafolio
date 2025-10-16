package Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src;

import java.sql.SQLException;
import java.util.List;

// Programa de demostración: CRUD, ResultSet y transacciones
public class ColegioDemo {
    public static void main(String[] args) throws Exception {
        AlumnosDAO dao = new AlumnosDAO();

        // Inserción simple
        Alumno a = new Alumno(null, "Ana Pérez", "ana.perez@example.com", "600000001");
        int idGenerado = dao.insertar(a);
        System.out.println("Insertado alumno con id = " + idGenerado);

        // Obtener por ID
        Alumno obtenido = dao.obtenerPorId(idGenerado);
        System.out.println("Obtenido: " + obtenido);

        // Listado
        List<Alumno> todos = dao.listarTodos();
        System.out.println("Listado total: " + todos);

        // Actualización
        obtenido.setTelefono("600000999");
        boolean actualizado = dao.actualizar(obtenido);
        System.out.println("Actualizado? " + actualizado);

        // Ejemplo transaccional (intenta insertar dos a la vez)
        Alumno t1 = new Alumno(null, "Trans A", "trans.a@example.com", "611111111");
        Alumno t2 = new Alumno(null, "Trans B", "trans.b@example.com", "622222222");
        try {
            dao.insertarDosAlumnosEnTransaccion(t1, t2);
            System.out.println("Transacción OK: ids " + t1.getIdAlumno() + ", " + t2.getIdAlumno());
        } catch (SQLException ex) {
            SqlExceptionUtil.printSQLException(ex);
        }

        // Eliminación (para no dejar datos de prueba)
        boolean eliminado = dao.eliminar(idGenerado);
        System.out.println("Eliminado? " + eliminado);

        // (Demostración de filtros/paginación eliminada para ceñirnos al PDF)
    }
}



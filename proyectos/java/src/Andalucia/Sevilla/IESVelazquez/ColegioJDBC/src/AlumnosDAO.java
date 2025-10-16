package Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO con operaciones CRUD usando PreparedStatement y ResultSet
public class AlumnosDAO {

    // Inserta un alumno y devuelve el ID generado
    public int insertar(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO Alumnos (nombre, correo, telefono) VALUES (?, ?, ?)";
        try (Connection cn = ColegioConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getCorreo());
            ps.setString(3, alumno.getTelefono());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    alumno.setIdAlumno(id);
                    return id;
                }
            }
        }
        return -1;
    }

    // Obtiene un alumno por ID
    public Alumno obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id_alumno, nombre, correo, telefono FROM Alumnos WHERE id_alumno = ?";
        try (Connection cn = ColegioConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapAlumno(rs);
                }
            }
        }
        return null;
    }

    // Lista todos los alumnos
    public List<Alumno> listarTodos() throws SQLException {
        String sql = "SELECT id_alumno, nombre, correo, telefono FROM Alumnos ORDER BY id_alumno";
        List<Alumno> lista = new ArrayList<>();
        try (Connection cn = ColegioConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapAlumno(rs));
            }
        }
        return lista;
    }

    // (Método de búsqueda con filtros eliminado para ceñirnos al PDF)

    // Actualiza datos de un alumno
    public boolean actualizar(Alumno alumno) throws SQLException {
        String sql = "UPDATE Alumnos SET nombre = ?, correo = ?, telefono = ? WHERE id_alumno = ?";
        try (Connection cn = ColegioConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getCorreo());
            ps.setString(3, alumno.getTelefono());
            ps.setInt(4, alumno.getIdAlumno());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    // Elimina por ID
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Alumnos WHERE id_alumno = ?";
        try (Connection cn = ColegioConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    // Ejemplo transaccional: insertar dos alumnos como una unidad atómica
    public void insertarDosAlumnosEnTransaccion(Alumno a1, Alumno a2) throws SQLException {
        String sql = "INSERT INTO Alumnos (nombre, correo, telefono) VALUES (?, ?, ?)";
        try (Connection cn = ColegioConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            boolean oldAutoCommit = cn.getAutoCommit();
            try {
                cn.setAutoCommit(false); // inicio de transacción

                // Alumno 1
                ps.setString(1, a1.getNombre());
                ps.setString(2, a1.getCorreo());
                ps.setString(3, a1.getTelefono());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) a1.setIdAlumno(rs.getInt(1));
                }

                // Alumno 2
                ps.clearParameters();
                ps.setString(1, a2.getNombre());
                ps.setString(2, a2.getCorreo());
                ps.setString(3, a2.getTelefono());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) a2.setIdAlumno(rs.getInt(1));
                }

                cn.commit(); // fin correcto
            } catch (SQLException ex) {
                cn.rollback(); // deshacer todo si algo falla
                throw ex;
            } finally {
                cn.setAutoCommit(oldAutoCommit);
            }
        }
    }

    private Alumno mapAlumno(ResultSet rs) throws SQLException {
        Alumno a = new Alumno();
        a.setIdAlumno(rs.getInt("id_alumno"));
        a.setNombre(rs.getString("nombre"));
        a.setCorreo(rs.getString("correo"));
        a.setTelefono(rs.getString("telefono"));
        return a;
    }
}



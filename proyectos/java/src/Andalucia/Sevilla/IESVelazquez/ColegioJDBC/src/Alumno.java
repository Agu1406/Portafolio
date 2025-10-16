package Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src;

// Modelo simple (POJO) que representa un alumno
public class Alumno {
    private Integer idAlumno; // puede ser null antes de insertar (AUTO_INCREMENT)
    private String nombre;
    private String correo;
    private String telefono;

    public Alumno() {}

    public Alumno(Integer idAlumno, String nombre, String correo, String telefono) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}



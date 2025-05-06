package Madrid.UAX.sistema_gestion_biblioteca;

import java.util.Date;

public class Usuario implements Clonable, Imprimible {
    private String nombre;
    private Date fechaInicioReserva;
    private Date fechaFinReserva;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFechaInicioReserva() { return fechaInicioReserva; }
    public void setFechaInicioReserva(Date fecha) { this.fechaInicioReserva = fecha; }

    public Date getFechaFinReserva() { return fechaFinReserva; }
    public void setFechaFinReserva(Date fecha) { this.fechaFinReserva = fecha; }

    @Override
    public Usuario clonar() {
        Usuario clon = new Usuario(this.nombre);
        clon.fechaInicioReserva = this.fechaInicioReserva;
        clon.fechaFinReserva = this.fechaFinReserva;
        return clon;
    }

    @Override
    public void imprimir() {
        System.out.printf("sistema_gestion_biblioteca.Usuario: %s%n", nombre);
        if (fechaInicioReserva != null && fechaFinReserva != null) {
            System.out.printf("sistema_gestion_biblioteca.Reserva: desde %s hasta %s%n",
                fechaInicioReserva, fechaFinReserva);
        }
    }
} 
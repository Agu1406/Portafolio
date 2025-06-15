package Madrid.UAX.sistema_gestion_biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Biblioteca {
    private String nombre;
    private List<Libro> catalogo;
    private List<Usuario> usuarios;
    private List<Reserva> reservas;
    private final int maxLibros;
    private final int maxUsuarios;

    public Biblioteca(String nombre, int maxLibros, int maxUsuarios) {
        this.nombre = nombre;
        this.maxLibros = maxLibros;
        this.maxUsuarios = maxUsuarios;
        this.catalogo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        if (catalogo.size() >= maxLibros) {
            throw new IllegalStateException("Se ha alcanzado el límite máximo de libros");
        }
        catalogo.add(libro);
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuarios.size() >= maxUsuarios) {
            throw new IllegalStateException("Se ha alcanzado el límite máximo de usuarios");
        }
        usuarios.add(usuario);
    }

    public boolean existeLibro(Libro libro) {
        return catalogo.contains(libro);
    }

    public boolean existeUsuario(Usuario usuario) {
        return usuarios.contains(usuario);
    }

    public void reservarLibro(Usuario usuario, Libro libro, Date fechaInicio, Date fechaFin) {
        if (!existeUsuario(usuario)) {
            throw new IllegalArgumentException("El usuario no está registrado en la biblioteca");
        }
        if (!existeLibro(libro)) {
            throw new IllegalArgumentException("El libro no está en el catálogo");
        }

        Reserva nuevaReserva = new Reserva(usuario, libro, fechaInicio, fechaFin);
        reservas.add(nuevaReserva);
    }

    public void mostrarReservasActivas() {
        System.out.println("=== Reservas Activas ===");
        for (Reserva reserva : reservas) {
            System.out.printf("sistema_gestion_biblioteca.Usuario: %s, sistema_gestion_biblioteca.Libro: %s, Desde: %s, Hasta: %s%n",
                reserva.getUsuario().getNombre(),
                reserva.getLibro().getTitulo(),
                reserva.getFechaInicio(),
                reserva.getFechaFin());
        }
    }

    public void imprimirCatalogo() {
        System.out.println("=== Catálogo de la sistema_gestion_biblioteca.Biblioteca ===");
        for (Libro libro : catalogo) {
            libro.imprimir();
        }
    }
} 
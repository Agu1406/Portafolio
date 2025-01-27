package sistema_gestion_agricola.interfaces;

public interface IteradorActividades<T> {
    boolean hasNext();
    T next();
    IteradorActividades<T> getIterator();
} 
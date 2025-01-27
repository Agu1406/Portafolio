package sistema_gestion_agricola.modelo;
import sistema_gestion_agricola.interfaces.ActividadAgricola;

public abstract class ActividadParcela implements ActividadAgricola {
    protected Parcela parcela;

    public ActividadParcela(Parcela parcela) {
        this.parcela = parcela;
    }
} 
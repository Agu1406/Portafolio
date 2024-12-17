package modelo;

import interfaces.ActividadAgricola;

public abstract class ActividadParcela implements ActividadAgricola {
    protected Parcela parcela;

    public ActividadParcela(Parcela parcela) {
        this.parcela = parcela;
    }
} 
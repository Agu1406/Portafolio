package modelo;

import excepciones.CultivoSinAradoException;
import excepciones.SiembraSinAradoException;

public class Arado extends ActividadParcela {
    private RegistroActividades<Arado> registro;

    public Arado(RegistroActividades<Arado> registro, Parcela parcela) {
        super(parcela);
        this.registro = registro;
    }

    @Override
    public void realizarArado() {
        parcela.arar();
        registro.agregarActividad(this);
    }

    @Override
    public void realizarSiembra() throws SiembraSinAradoException {

    }

    @Override
    public void realizarCultivo() throws CultivoSinAradoException {

    }
} 
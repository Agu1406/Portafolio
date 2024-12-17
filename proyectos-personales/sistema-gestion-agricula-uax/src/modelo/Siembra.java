package modelo;

import excepciones.CultivoSinAradoException;
import excepciones.SiembraSinAradoException;

public class Siembra extends ActividadParcela {
    private RegistroActividades<Siembra> registro;

    public Siembra(RegistroActividades<Siembra> registro, Parcela parcela) {
        super(parcela);
        this.registro = registro;
    }

    @Override
    public void realizarArado() {

    }

    @Override
    public void realizarSiembra() throws SiembraSinAradoException {
        if (!parcela.getEstado().equals("arada")) {
            throw new SiembraSinAradoException();
        }
        parcela.sembrar();
        registro.agregarActividad(this);
    }

    @Override
    public void realizarCultivo() throws CultivoSinAradoException {

    }
} 
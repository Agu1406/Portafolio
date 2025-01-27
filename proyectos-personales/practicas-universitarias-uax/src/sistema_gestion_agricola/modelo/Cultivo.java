package modelo;

import excepciones.CultivoSinAradoException;
import excepciones.SiembraSinAradoException;

public class Cultivo extends ActividadParcela {
    private RegistroActividades<Cultivo> registro;

    public Cultivo(RegistroActividades<Cultivo> registro, Parcela parcela) {
        super(parcela);
        this.registro = registro;
    }

    @Override
    public void realizarArado() {

    }

    @Override
    public void realizarSiembra() throws SiembraSinAradoException {

    }

    @Override
    public void realizarCultivo() throws CultivoSinAradoException {
        if (!parcela.getEstado().equals("arada")) {
            throw new CultivoSinAradoException();
        }
        parcela.cultivar();
        registro.agregarActividad(this);
    }
} 
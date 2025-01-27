package sistema_gestion_agricola.modelo;

import sistema_gestion_agricola.excepciones.CultivoSinAradoException;
import sistema_gestion_agricola.excepciones.SiembraSinAradoException;
import sistema_gestion_agricola.interfaces.ActividadAgricola;

public class Siembra extends ActividadParcela implements ActividadAgricola {
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
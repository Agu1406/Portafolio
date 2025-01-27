package sistema_gestion_agricola.modelo;
import sistema_gestion_agricola.excepciones.CultivoSinAradoException;
import sistema_gestion_agricola.excepciones.SiembraSinAradoException;
import sistema_gestion_agricola.interfaces.ActividadAgricola;

public class Arado extends ActividadParcela implements ActividadAgricola {
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
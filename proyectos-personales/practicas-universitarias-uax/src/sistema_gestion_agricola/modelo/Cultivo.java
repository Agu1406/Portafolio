package sistema_gestion_agricola.modelo;
import sistema_gestion_agricola.excepciones.CultivoSinAradoException;
import sistema_gestion_agricola.excepciones.SiembraSinAradoException;
import sistema_gestion_agricola.interfaces.ActividadAgricola;

public class Cultivo extends ActividadParcela implements ActividadAgricola {
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
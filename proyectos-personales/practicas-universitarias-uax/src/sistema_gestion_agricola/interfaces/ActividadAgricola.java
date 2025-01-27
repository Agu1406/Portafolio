package sistema_gestion_agricola.interfaces;

import sistema_gestion_agricola.excepciones.CultivoSinAradoException;
import sistema_gestion_agricola.excepciones.SiembraSinAradoException;

public interface ActividadAgricola {
    void realizarArado();
    void realizarSiembra() throws SiembraSinAradoException;
    void realizarCultivo() throws CultivoSinAradoException;
} 
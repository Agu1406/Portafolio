package interfaces;

import excepciones.CultivoSinAradoException;
import excepciones.SiembraSinAradoException;

public interface ActividadAgricola {
    void realizarArado();
    void realizarSiembra() throws SiembraSinAradoException;
    void realizarCultivo() throws CultivoSinAradoException;
} 
import modelo.*;
import excepciones.SiembraSinAradoException;
import excepciones.CultivoSinAradoException;
import excepciones.HistorialVacioException;

public class Main {
    public static void main(String[] args) {
        Parcela parcela1 = new Parcela("La Carrehuela", 10000);
        Parcela parcela2 = new Parcela("La Puchera", 6000);
        Parcela parcela3 = new Parcela("Las Laderas", 3000);

        RegistroActividades<Arado> registroArado = new RegistroActividades<>();
        RegistroActividades<Siembra> registroSiembra = new RegistroActividades<>();
        RegistroActividades<Cultivo> registroCultivo = new RegistroActividades<>();

        // Mostrar información inicial
        parcela1.mostrarInformacion();
        parcela2.mostrarInformacion();
        parcela3.mostrarInformacion();

        // Realizar arado
        Arado arado1 = new Arado(registroArado, parcela1);
        arado1.realizarArado();
        Arado arado2 = new Arado(registroArado, parcela2);
        arado2.realizarArado();
        Arado arado3 = new Arado(registroArado, parcela3);
        arado3.realizarArado();

        // Realizar siembra
        try {
            Siembra siembra1 = new Siembra(registroSiembra, parcela1);
            siembra1.realizarSiembra();
            Siembra siembra2 = new Siembra(registroSiembra, parcela2);
            siembra2.realizarSiembra();
        } catch (SiembraSinAradoException e) {
            System.out.println(e.getMessage());
        }

        // Mostrar estado de las parcelas
        parcela1.mostrarInformacion();
        parcela2.mostrarInformacion();
        parcela3.mostrarInformacion();

        // Realizar cultivo
        try {
            Cultivo cultivo1 = new Cultivo(registroCultivo, parcela1);
            cultivo1.realizarCultivo();
            Cultivo cultivo2 = new Cultivo(registroCultivo, parcela2);
            cultivo2.realizarCultivo();
            Cultivo cultivo3 = new Cultivo(registroCultivo, parcela3);
            cultivo3.realizarCultivo();
        } catch (CultivoSinAradoException e) {
            System.out.println(e.getMessage());
        }

        // Mostrar historial de actividades
        try {
            registroArado.mostrarHistorial();
            registroSiembra.mostrarHistorial();
            registroCultivo.mostrarHistorial();
        } catch (HistorialVacioException e) {
            System.out.println(e.getMessage());
        }
    }
} 
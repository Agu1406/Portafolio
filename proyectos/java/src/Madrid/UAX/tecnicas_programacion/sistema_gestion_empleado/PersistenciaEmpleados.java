package Madrid.UAX.sistema_gestion_empleado;

import java.io.*;

public class PersistenciaEmpleados {
    private static final String ARCHIVO = "empresa.dat";

    public static void guardarEmpresa(Empresa empresa) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO))) {
            oos.writeObject(empresa);
        }
    }

    public static Empresa cargarEmpresa() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO))) {
            return (Empresa) ois.readObject();
        }
    }
} 
# ColegioJDBC (XAMPP/MySQL con MySQL Connector/J)

Pasos según el PDF de JDBC (carga de driver, `DriverManager.getConnection`, `Statement`, cierre de recursos) usando el conector de MySQL. Puedes usar MySQL Server o el servicio MySQL/MariaDB de XAMPP.

1. Instalar XAMPP (inicia el servicio MySQL) o MySQL Server. Usuario por defecto en XAMPP `root`, contraseña vacía.
2. Descargar MySQL Connector/J 8.0.33:
   - Archivo `mysql-connector-j-8.0.33.jar`.
3. Colocar el `.jar` en alguna ruta local, por ejemplo `C:\libs\mysql-connector-j-8.0.33.jar` (ajusta versión/ruta).
4. Ajustar usuario/contraseña en `src/ColegioConnection.java` (`USER`, `PASSWORD`).

## Compilación y ejecución en Windows (PowerShell)

```powershell
# Ir al directorio del proyecto
cd src\Andalucia\Sevilla\IESVelazquez\ColegioJDBC

# Compilar con package (genera carpetas del paquete en el directorio actual)
javac -d . -cp .;C:\libs\mysql-connector-j-8.0.33.jar src\ColegioConnection.java src\ColegioFactoryForServer.java src\ColegioSetup.java src\Alumno.java src\AlumnosDAO.java src\ColegioDemo.java

# Crear BD y tabla desde Java (usar nombre cualificado por el package)
java -cp .;C:\libs\mysql-connector-j-8.0.33.jar Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src.ColegioSetup

# Probar conexión a la BD
java -cp .;C:\libs\mysql-connector-j-8.0.33.jar Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src.ColegioConnection

# Ejecutar la demo CRUD/ResultSet/Transacción
java -cp .;C:\libs\mysql-connector-j-8.0.33.jar Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src.ColegioDemo
```

## Script SQL alternativo

También puedes ejecutar manualmente `sql/colegio.sql` en MySQL Workbench o consola:

```sql
SOURCE sql/colegio.sql;
```

## Notas

- Driver utilizado: `com.mysql.cj.jdbc.Driver` (carga explícita con `Class.forName` para seguir la teoría del PDF).
- URL JDBC: `jdbc:mysql://HOST:PORT/Colegio?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`.
- Gestión de recursos: try-with-resources para `Connection`, `Statement`, `PreparedStatement`, `ResultSet`.
- Seguridad: uso de `PreparedStatement` en CRUD para evitar inyecciones SQL.
- Transacciones: ejemplo de `setAutoCommit(false)`, `commit`, `rollback` en `AlumnosDAO#insertarDosAlumnosEnTransaccion`.
 - Validaciones: `Validacion` comprueba campos obligatorios y formato básico de email.
 - Excepciones SQL: `SqlExceptionUtil` imprime `SQLState` y `ErrorCode`.
 

## Pasos desde cero (resumen)
1. Instala XAMPP (inicia MySQL) o MySQL Server y anota usuario/contraseña (en XAMPP suele ser root/sin contraseña).
2. Descarga MySQL Connector/J y coloca el `.jar` (ej.: `C:\libs\mysql-connector-j-8.0.33.jar`).
3. Crea estructura de carpetas como en este proyecto y copia los `.java` y `sql/colegio.sql`.
4. Edita `ColegioConnection` para poner tu `USER` y `PASSWORD`.
5. Compila con `javac -d . -cp .;C:\libs\mysql-connector-j-*.jar ...` (ver comando arriba).
6. Ejecuta `ColegioSetup` para crear BD y tabla.
7. Ejecuta `ColegioConnection` para probar conexión.
8. Ejecuta `ColegioDemo` para ver CRUD, ResultSet y transacciones.

### Pasos desde cero (detalle)
1) Instalar y preparar entorno
- Instala XAMPP y abre el Panel de Control (o instala MySQL Server).
- Arranca MySQL y comprueba el puerto (normalmente 3306).
- Descarga MySQL Connector/J (`mysql-connector-j-8.0.33.jar`) y colócalo en `C:\libs\`.

2) Estructura del proyecto (carpetas)
- `IESVelazquez/ColegioJDBC/sql/colegio.sql`
- `IESVelazquez/ColegioJDBC/src/*.java`

3) Configurar credenciales
- Edita `ColegioConnection` y pon `USER`/`PASSWORD`.

4) Compilar
```powershell
cd src\Andalucia\Sevilla\IESVelazquez\ColegioJDBC
javac -d . -cp .;C:\libs\mysql-connector-j-8.0.33.jar src\ColegioConnection.java src\ColegioFactoryForServer.java src\ColegioSetup.java src\Alumno.java src\AlumnosDAO.java src\ColegioDemo.java src\SqlExceptionUtil.java
```

5) Crear BD/tabla y probar
```powershell
java -cp .;C:\libs\mysql-connector-j-8.0.33.jar Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src.ColegioSetup
java -cp .;C:\libs\mysql-connector-j-8.0.33.jar Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src.ColegioConnection
java -cp .;C:\libs\mysql-connector-j-8.0.33.jar Andalucia.Sevilla.IESVelazquez.ColegioJDBC.src.ColegioDemo
```
 
 
6) Alternativa con SQL directo
- Abre `sql/colegio.sql` en Workbench y ejecútalo.

## Guía de creación desde cero (paso a paso)
Sigue estos pasos en este orden para crear los archivos y que todo compile a la primera:

Paso 1: Crea la clase `ColegioConnection`
- Ruta: `IESVelazquez/ColegioJDBC/src/ColegioConnection.java`
- Contiene: carga del driver `com.mysql.cj.jdbc.Driver`, construcción de la URL `jdbc:mysql://...`, lectura de `USER`/`PASSWORD`, y método `getConnection()` que devuelve `Connection` vía `DriverManager`.
- Incluye un `main` que prueba la conexión.

Paso 2: Crea la clase `ColegioFactoryForServer`
- Ruta: `IESVelazquez/ColegioJDBC/src/ColegioFactoryForServer.java`
- Contiene: carga del driver y método `getConnectionWithoutDatabase()` que se conecta al servidor MariaDB sin especificar base de datos (lo usaremos para crear la BD `Colegio`).

Paso 3: Crea la clase `ColegioSetup`
- Ruta: `IESVelazquez/ColegioJDBC/src/ColegioSetup.java`
- Contiene: uso de `Statement` para ejecutar `CREATE DATABASE IF NOT EXISTS Colegio` y, después, `CREATE TABLE IF NOT EXISTS Alumnos (...)` dentro de la BD.
- Usa `ColegioFactoryForServer.getConnectionWithoutDatabase()` para crear la BD y `ColegioConnection.getConnection()` para crear la tabla.

Paso 4: Crea el modelo `Alumno`
- Ruta: `IESVelazquez/ColegioJDBC/src/Alumno.java`
- Contiene: POJO con campos `idAlumno`, `nombre`, `correo`, `telefono`, getters/setters y `toString()`.

Paso 5: Crea el DAO `AlumnosDAO`
- Ruta: `IESVelazquez/ColegioJDBC/src/AlumnosDAO.java`
- Contiene: operaciones CRUD con `PreparedStatement`/`ResultSet`:
  - `insertar(Alumno)` (devuelve ID generado)
  - `obtenerPorId(int)`
  - `listarTodos()`
  - `actualizar(Alumno)`
  - `eliminar(int)`
  - Ejemplo transaccional `insertarDosAlumnosEnTransaccion(Alumno, Alumno)` con `setAutoCommit(false)`, `commit`, `rollback`.

Paso 6: Crea la utilidad `SqlExceptionUtil`
- Ruta: `IESVelazquez/ColegioJDBC/src/SqlExceptionUtil.java`
- Contiene: método `printSQLException(SQLException)` que muestra `message`, `SQLState` y `ErrorCode` (útil para depurar según la teoría del PDF).

Paso 7: Crea la clase de demo `ColegioDemo`
- Ruta: `IESVelazquez/ColegioJDBC/src/ColegioDemo.java`
- Contiene: un `main` que usa `AlumnosDAO` para ejecutar `insertar`, `obtenerPorId`, `listarTodos`, `actualizar`, transacción y `eliminar`.

Paso 8 (opcional): Crea el script SQL `sql/colegio.sql`
- Ruta: `IESVelazquez/ColegioJDBC/sql/colegio.sql`
- Contiene: `CREATE DATABASE` y `CREATE TABLE` equivalentes por si prefieres crear la estructura desde Workbench o consola.

Paso 9: Compila y ejecuta
- Compila con `javac -d . -cp .;C:\libs\mariadb-java-client-3.3.3.jar ...` tal como se indica arriba (asegúrate de incluir todas las clases creadas).
- Ejecuta en este orden:
  1) `ColegioSetup` (crea BD/tabla)
  2) `ColegioConnection` (prueba conexión)
  3) `ColegioDemo` (CRUD y transacción)



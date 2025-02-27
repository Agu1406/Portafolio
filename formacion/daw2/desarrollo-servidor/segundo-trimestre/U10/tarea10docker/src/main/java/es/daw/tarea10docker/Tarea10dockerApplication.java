package es.daw.tarea10docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Esta clase contiene el método main que inicia la aplicación Spring Boot.
 * La anotación @SpringBootApplication combina tres anotaciones:
 * - @Configuration: Indica que esta clase define beans de Spring
 * - @EnableAutoConfiguration: Habilita la configuración automática de Spring Boot
 * - @ComponentScan: Escanea componentes en el paquete actual y sus subpaquetes
 *
 * @author Agustín (Agu1406)
 * @version 1.0
 */
@SpringBootApplication
public class Tarea10dockerApplication {

	/**
	 * Método principal que inicia la aplicación Spring Boot.
	 * 
	 * @param args Argumentos de línea de comandos (no utilizados en esta aplicación)
	 */
	public static void main(String[] args) {
		SpringApplication.run(Tarea10dockerApplication.class, args);
	}

}

package es.daw2.tarea86.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Tarea 85")
                        .description("API REST de la Tarea 85 de la asignatura de Diseño de Aplicaciones Web II")
                        .version("1.0"));
    }
}

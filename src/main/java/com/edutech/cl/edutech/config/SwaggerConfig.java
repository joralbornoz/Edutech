package com.edutech.cl.edutech.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Compra Curso Edutech")
                        .version("1.0")
                        .description("Documentacion de las API para el sistema de compra Curso"));
    }

}

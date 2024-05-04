package com.lucasmoraist.jornadamilhas.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI docSwagger(){
        return new OpenAPI()
                .info(new Info()
                        .title("Jornada Milhas API")
                        .description("Esta é uma API que apresentará para seus clientes alguns destinos de viagens possíveis para eles. Além disso contará com depoimentos sobre o destino de antigos turistas que foram para lá.")
                        .summary("Está API servirá para o usuário encontrar o seu próximo melhor destino para se visitar, contando com sistema avançado de recomendações de destinos.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Lucas de Morais Nascimento Taguchi")
                                .url("https://lucasmoraist.github.io/Portfolio/")
                                .email("luksmnt1101@gmail.com")
                        )
                        .license(new License()
                                .name("")
                                .identifier("")
                                .url("")
                        )
                );
    }

}

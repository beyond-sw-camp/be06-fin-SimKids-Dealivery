package org.example.orders.global.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private Info info() {
        String description = "<h3>필독!</h3>  \n" +
                "Dealivery Swagger";
        return new Info().title("Dealivery").description(description).version("1.0.0");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info());
    }

}

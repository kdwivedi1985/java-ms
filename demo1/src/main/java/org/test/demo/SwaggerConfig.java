package org.test.demo;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Customer Service Rest Api")
                                .description("Rest Api for Customer Data Service")
                                .version("1.0"));
    }
}

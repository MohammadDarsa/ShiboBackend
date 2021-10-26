package com.example.shibo.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    private static final Contact SUPPORTED_CONTACTS = new Contact("Jawad/Darsa", "https://Shibo.com", "support@mail.com");
    @Bean
    public Docket newApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Shibo")
                .description("Shibo")
                .termsOfServiceUrl("https://Shibo.com")
                .license("Shibo License v1.0")
                .licenseUrl("https://Shibo.com")
                .version("3.0")
                .contact(SUPPORTED_CONTACTS)
                .build();
    }
}

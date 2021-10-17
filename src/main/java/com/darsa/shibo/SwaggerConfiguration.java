package com.darsa.shibo;


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
    public Docket newApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Shibo")
                .description("Shibo with Swagger documentation")
                .termsOfServiceUrl("https://Shibo.com")
                .license("Shibo License v1.0")
                .licenseUrl("https://Shibo.com")
                .version("3.0")
                .contact(new Contact("Darsa", "https://darsatest.netlify.com", "support@mail.com"))
                .build();
    }
}

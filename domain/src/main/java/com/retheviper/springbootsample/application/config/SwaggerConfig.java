package com.retheviper.springbootsample.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Congifuration class for API documentation with Swagger.
 *
 * @author retheviper
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    /**
     * Create document for this application with Swagger.
     *
     * @return Created API document
     */
    @Bean
    public Docket swaggerDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(springSampleInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.retheviper.springbootsample"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    /**
     * Create API information for this application.
     *
     * @return Created API information
     */
    private ApiInfo springSampleInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Sample")
                .description("A simple sample of Spring Boot REST API.")
                .license("retheviper")
                .licenseUrl("https://github.com/retheviper")
                .version("0.0.1")
                .build();
    }
}

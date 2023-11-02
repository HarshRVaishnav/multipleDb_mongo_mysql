package com.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration                                                 //swagger ui implementation for exposure to application apis
@OpenAPIDefinition(
        info = @Info(
                title = "SpringBootMultipleDatabase_Application API",
                version = "1.0",
                contact = @Contact(
                        name = "MultipleDBSpringBoot", url = "https://www.mongodb.com/"
                ),
                license = @License(
                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                termsOfService = "tos.uri",
                description = "api.description"
        )

//        , servers = @Server(
//                url = "${api.server.url}",
//                description = "Production"
//        )
)
public class OpenApiConfiguration {
    @Bean
    GroupedOpenApi customerApis() {
        return GroupedOpenApi.builder().group("customer").pathsToMatch("/**/customer/**").build();
    }

    @Bean
    GroupedOpenApi productApis() {
        return GroupedOpenApi.builder().group("product").pathsToMatch("/**/product/**").build();
    }

    @Bean
    GroupedOpenApi orderApis() {
        return GroupedOpenApi.builder().group("order").pathsToMatch("/**/order/**").build();
    }

    @Bean
    GroupedOpenApi orderdetailseApis() {
        return GroupedOpenApi.builder().group("orderDetails").pathsToMatch("/**/orderDetails/**").build();
    }
}


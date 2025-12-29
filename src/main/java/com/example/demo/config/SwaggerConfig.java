// // package com.example.demo.config;

// // import io.swagger.v3.oas.models.OpenAPI;
// // import io.swagger.v3.oas.models.servers.Server;
// // import io.swagger.v3.oas.models.security.SecurityScheme;  // <-- important
// // import org.springframework.context.annotation.Bean;
// // import org.springframework.context.annotation.Configuration;

// // import java.util.List;

// // @Configuration
// // public class SwaggerConfig {

// //     @Bean
// //     public OpenAPI customOpenAPI() {

// //         SecurityScheme bearerAuth = new SecurityScheme()
// //                 .type(SecurityScheme.Type.HTTP)
// //                 .scheme("bearer")
// //                 .bearerFormat("JWT");

// //         return new OpenAPI()
// //                 .servers(List.of(
// //                         new Server().url("https://9133.pro604cr.amypo.ai/")
// //                 ));
// //     }
// // }

// package com.example.demo.config;

// import io.swagger.v3.oas.models.Components;
// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         final String securitySchemeName = "bearerAuth";
        
//         return new OpenAPI()
//                 // 1. Adds the global Authorize button and applies it to all endpoints
//                 .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                
//                 // 2. Defines the JWT Bearer token security scheme
//                 .components(new Components()
//                         .addSecuritySchemes(securitySchemeName,
//                                 new SecurityScheme()
//                                         .name(securitySchemeName)
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")))
                
//                 // 3. ONLY ONE SERVER: Using the /api prefix required by your proxy
//                 .servers(List.of(
//                         new Server().url("https://9133.pro604cr.amypo.ai/api")
//                 ));
//     }
// }
package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                // 1. Metadata for the UI
                .info(new Info()
                        .title("Demo API")
                        .version("1.0")
                        .description("Spring Boot 3 JWT Authentication API"))
                
                // 2. Global "Authorize" button
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                
                // 3. Define the Security Scheme
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                
                // 4. Server Configuration
                // IMPORTANT: If your proxy handles the "/api" prefix, keep it. 
                // If not, use the base URL.
                .servers(List.of(
                        new Server().url("https://9133.pro604cr.amypo.ai/").description("Default Server")
                ));
    }
}
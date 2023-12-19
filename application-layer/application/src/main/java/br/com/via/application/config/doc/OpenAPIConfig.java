package br.com.via.application.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Configuration
@RestController
public class OpenAPIConfig {

    @Value("${springdoc.swagger-ui.path:/swagger-ui}")
    public String swaggerPath;

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Spring Microservice Template")
                        .description("A basic Spring microservice template")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @RequestMapping("/")
    public void redirect(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(swaggerPath);
    }

}

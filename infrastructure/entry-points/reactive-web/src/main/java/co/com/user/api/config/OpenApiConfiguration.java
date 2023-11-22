package co.com.user.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    private static final String TITLE_APP = "Documentacion de la API del microservicio usuarios";
    private static final String TERMS_OF_SERVICE = "https://swagger.io/terms/";
    private static final String NAME_LICENSE = "Apache 2.0";
    private static final String LICENSE_URL = "https://springdoc.org";

    @Bean
    public OpenAPI customOpenApi(@Value("${app.description}") String appDescription,
                                 @Value("${app.version}") String appVersion){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(TITLE_APP)
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService(TERMS_OF_SERVICE)
                        .license(new License().name(NAME_LICENSE).url(LICENSE_URL))
                );
    }

}

package uos.capstone.backend;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2Config {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("제주 어디 갈래")
                .version(springdocVersion)
                .description("API에 대한 설명 부분");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}

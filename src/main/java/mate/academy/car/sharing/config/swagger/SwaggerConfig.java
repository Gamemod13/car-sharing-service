package mate.academy.car.sharing.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//TODO:Impl/fix
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket scrumAllyApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Scrum_ally API")
                .description("Scrum_ally is a web application designed for project management")
                .license("MIT License")
                .licenseUrl("https://opensource.org/licenses/MIT")
                .build();
    }
}

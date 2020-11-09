package org.clxmm.service.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author clxmm
 * @version 1.0
 * @date 2020/11/4 10:23 下午
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                //
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build()
                ;

        return docket;
    }




    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("Api")
                .description("this is description")
                .version("1.0")
                .contact(new Contact("clxmm","http:","@qq.com"))
                .build();

    }

    @Bean
    public Docket adminApiConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build()
                ;
    }



    private ApiInfo adminApiInfo() {

        return new ApiInfoBuilder()
                .title("Api")
                .description("this is admin description")
                .version("1.0")
                .contact(new Contact("clxmm","http:","@qq.com"))
                .build();

    }

}

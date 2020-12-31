package com.chengxiaoxiao.lizhiedu.swagger;

import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.swagger.config.SwaggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Swagger配置类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/9 17:40
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "lizhi.swagger.enabled", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    /**
     * 默认的排除路径，排除Spring Boot默认的错误处理路径和端点
     */
    private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error", "/actuator/**");

    private static final String BASE_PATH = "/**";

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }


    @Bean
    public Docket api(SwaggerProperties swaggerProperties) {

        // base-path处理
        if (swaggerProperties.getBasePath().isEmpty()) {
            swaggerProperties.getBasePath().add(BASE_PATH);
        }

        // exclude-path处理
        if (swaggerProperties.getExcludePath().isEmpty()) {
            swaggerProperties.getExcludePath().addAll(DEFAULT_EXCLUDE_PATH);
        }

        //添加全局HEADER参数
        List<RequestParameter> pars = getGlobalHeaderParams(swaggerProperties);


        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getHost())
                .apiInfo(apiInfo(swaggerProperties)).globalRequestParameters(pars).select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()));

        swaggerProperties.getBasePath().forEach(p -> builder.paths(PathSelectors.ant(p)));
        swaggerProperties.getExcludePath().forEach(p -> builder.paths(PathSelectors.ant(p).negate()));

        return builder.build().ignoredParameterTypes(LoginUser.class);
    }

    /**
     * 添加全局HEADER参数
     *
     * @param swaggerProperties
     * @return 参数集合
     */
    private List<RequestParameter> getGlobalHeaderParams(SwaggerProperties swaggerProperties) {
        List<RequestParameter> pars = new ArrayList<>();

        RequestParameterBuilder versionPar = new RequestParameterBuilder().description("请求API版本号")
                .in(ParameterType.HEADER).name("VERSION").required(false)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));

        pars.add(versionPar.build());

        if (swaggerProperties.getEnabledAuth()) {
            RequestParameterBuilder authPar = new RequestParameterBuilder().description("请求TOKEN")
                    .in(ParameterType.HEADER).name(swaggerProperties.getTokenHeader()).required(true)
                    .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));

            pars.add(authPar.build());
        }

        return pars;
    }

    private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContact().getName(), swaggerProperties.getContact().getUrl(), swaggerProperties.getContact().getEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }

}

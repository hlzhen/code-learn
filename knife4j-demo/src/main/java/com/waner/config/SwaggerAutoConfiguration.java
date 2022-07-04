package com.waner.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Swagger配置
 * Created by Ale on 2022/7/2
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
@EnableSwagger2
@ConditionalOnProperty(name = "pd.swagger.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerAutoConfiguration implements BeanFactoryAware {

    @Resource
    private SwaggerProperties swaggerProperties;

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    @Bean
    @ConditionalOnMissingBean
    public List<Docket> createRstApi() {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        List<Docket> docketList = new LinkedList<>();
        Map<String, SwaggerProperties.DocketInfo> docketMap = swaggerProperties.getDocket();
        if (docketMap.isEmpty()) {
            Docket docket = createDocket(swaggerProperties);
            configurableBeanFactory.registerSingleton(swaggerProperties.getTitle(), docket);
            docketList.add(docket);
        } else {
            Set<String> keySet = docketMap.keySet();
            for (String key : keySet) {
                SwaggerProperties.DocketInfo docketInfo = swaggerProperties.getDocket().get(key);
                SwaggerProperties.Contact contact = docketInfo.getContact();
                ApiInfo apiInfo = createApiInfo(
                        docketInfo.getTitle(),
                        contact.getName(),
                        contact.getUrl(),
                        contact.getEmail(),
                        docketInfo.getVersion(),
                        docketInfo.getDescription()
                );
                Docket docket = getDocket(
                        docketInfo.getBasePath(),
                        docketInfo.getExcludePath(),
                        apiInfo,
                        docketInfo.getGroup(),
                        docketInfo.getBasePackage()
                );
                configurableBeanFactory.registerSingleton(key, docket);
                docketList.add(docket);
            }
        }
        return docketList;
    }

    private Docket createDocket(SwaggerProperties swaggerProperties) {
        SwaggerProperties.Contact contact = swaggerProperties.getContact();
        ApiInfo apiInfo = createApiInfo(swaggerProperties.getTitle(), contact.getName(), contact.getUrl(), contact.getEmail(), swaggerProperties.getVersion(), swaggerProperties.getDescription());
        return getDocket(
                swaggerProperties.getBasePath(),
                swaggerProperties.getExcludePath(),
                apiInfo,
                swaggerProperties.getGroup(),
                swaggerProperties.getBasePackage()
        );
    }

    private Docket getDocket(List<String> basePathList, List<String> excludePathList, ApiInfo apiInfo, String groupName, String basePackage) {
        if (basePathList.isEmpty()) {
            basePathList.add("/**");
        }

        List<Predicate<String>> basePath = new ArrayList<>();
        for (String path : basePathList) {
            basePath.add(PathSelectors.ant(path));
        }

        List<Predicate<String>> excludePath = new ArrayList<>();
        for (String path : excludePathList) {
            excludePath.add(PathSelectors.ant(path));
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath)))
                .build();
    }


    private ApiInfo createApiInfo(String title,
                                  String contactName,
                                  String contactUrl,
                                  String contactEmail,
                                  String version,
                                  String Description) {
        return new ApiInfoBuilder()
                .title(title)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .version(version)
                .description(Description)
                .build();
    }
}

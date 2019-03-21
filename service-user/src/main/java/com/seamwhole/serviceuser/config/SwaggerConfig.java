package com.seamwhole.serviceuser.config;

import com.google.common.collect.Sets;
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

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .useDefaultResponseMessages(false)
                .select()
// 指定controller存放的目录路径
                .apis(RequestHandlerSelectors.basePackage("com.seamwhole.serviceuser.controller"))
// .paths(PathSelectors.ant("/api/v1/*"))
                .paths(PathSelectors.any())
                .build();

    }
    //构建api文档的详细信息函数
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //页面标题
                .title("【天字一号】----基础项目")
                //创建人
                .contact(new Contact("richer_huang","http://www.seamwhole.com","richer_huang@seamwhole.com"))
                //版本号
                .version("1.0")
                //描述
                .description("用户服务API")
                .build();
    }
}


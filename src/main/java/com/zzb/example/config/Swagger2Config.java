package com.zzb.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.configuration.SwaggerCommonConfiguration;

import java.util.ArrayList;

/**
 * @author XBird
 * @date 2021/12/28
 **/
@Configuration
public class Swagger2Config {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }


    public ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("郑炳元", "https://juejin.cn/", "1551907870@qq.com");
        return new ApiInfo("郑炳元的Swagger文档", "mdc实战使用项目", "v1.0", "https://juejin.cn/",
                contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    }
}

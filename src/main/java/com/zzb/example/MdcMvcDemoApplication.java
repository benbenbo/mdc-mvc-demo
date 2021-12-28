package com.zzb.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MdcMvcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdcMvcDemoApplication.class, args);
    }

}

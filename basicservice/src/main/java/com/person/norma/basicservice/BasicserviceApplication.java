package com.person.norma.basicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.person.norma")
@SpringBootApplication
public class BasicserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicserviceApplication.class, args);
    }

}

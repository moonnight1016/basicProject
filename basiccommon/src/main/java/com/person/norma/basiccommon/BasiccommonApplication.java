package com.person.norma.basiccommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.person.norma")
@SpringBootApplication
public class BasiccommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasiccommonApplication.class, args);
    }

}

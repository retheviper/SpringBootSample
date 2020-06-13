package com.retheviper.springbootsample.api.v1.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.retheviper.springbootsample")
public class SpringBootSampleWebTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleWebTestApplication.class, args);
    }
}

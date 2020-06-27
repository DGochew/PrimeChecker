package com.test.primechecker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Collections;

@SpringBootApplication
@EnableCaching
public class PrimeCheckerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PrimeCheckerApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "5580"));
        app.run(args);
    }

}

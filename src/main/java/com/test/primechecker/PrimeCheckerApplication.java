package com.test.primechecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class PrimeCheckerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PrimeCheckerApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "5580"));
        app.run(args);
    }

}

package com.example.sp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Sp3Application {

    public static void main(String[] args) {
        SpringApplication.run(Sp3Application.class, args);
    }

}

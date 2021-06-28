package com.project02.hanghaelog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeLogApplication.class, args);
    }

}

package com.dawn.ebms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.dawn.ebms.repository")
@SpringBootApplication
public class EbmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EbmsApplication.class, args);
    }

}

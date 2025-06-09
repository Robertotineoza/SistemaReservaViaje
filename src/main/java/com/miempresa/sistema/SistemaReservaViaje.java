
package com.miempresa.sistema;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.miempresa.sistema.repository")
public class SistemaReservaViaje {
    public static void main(String[] args) {
        SpringApplication.run(SistemaReservaViaje.class, args);
    }
}
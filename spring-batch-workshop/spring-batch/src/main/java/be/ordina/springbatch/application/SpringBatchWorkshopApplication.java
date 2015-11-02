package be.ordina.springbatch.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBatchWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchWorkshopApplication.class, args);
    }
}

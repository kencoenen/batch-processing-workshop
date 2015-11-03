package be.ordina.springbatch;

import org.springframework.batch.admin.annotation.EnableBatchAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;

@SpringBootApplication(exclude = {HypermediaAutoConfiguration.class, MultipartAutoConfiguration.class})
@EnableBatchAdmin
public class SpringBatchWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchWorkshopApplication.class, args);
    }
}

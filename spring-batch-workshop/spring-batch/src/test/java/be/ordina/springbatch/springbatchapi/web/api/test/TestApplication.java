package be.ordina.springbatch.springbatchapi.web.api.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class TestApplication {

	@Bean
	public Job sampleJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory steps) {
		return jobBuilderFactory.get("sampleJob")
			.flow(steps.get("sampleStep").tasklet(new NoopTasklet()).build())
			.end().build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	
}

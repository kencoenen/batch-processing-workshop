package be.ordina.springbatch.application;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.ordina.springbatch.domain.TrajectInformation;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Bean
    public ItemReader<TrajectInformation> reader() {
		return new TrajectInformationReader();
    }

    @Bean
    public ItemProcessor<TrajectInformation, TrajectInformation> processor() {
    	return new TrajectInformationProcessor();
    }

    @Bean
    public ItemWriter<TrajectInformation> writer() {
    	return new TrajectInformationWriter();
    }
    
    @Bean
    public JobExecutionListener trajectInformationListener() {
    	return new TrajectInformationJobExecutionListener();
    }
    

    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step processTrajectInformationStep, JobExecutionListener listener) {
        return jobs.get("importTrajectInformation")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(processTrajectInformationStep)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<TrajectInformation> reader,
            ItemWriter<TrajectInformation> writer, ItemProcessor<TrajectInformation, TrajectInformation> processor) {
        return stepBuilderFactory.get("processTrajectInformationStep")
                .<TrajectInformation, TrajectInformation> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
	
	

}

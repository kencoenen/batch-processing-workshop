package be.ordina.springbatch.application;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.step.NoWorkFoundStepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import be.ordina.springbatch.application.batch.FineInformationWriter;
import be.ordina.springbatch.application.batch.TrajectInformationJobExecutionListener;
import be.ordina.springbatch.application.batch.TrajectInformationProcessor;
import be.ordina.springbatch.application.batch.TrajectInformationReader;
import be.ordina.springbatch.application.batch.TrajectInformationTokenizer;
import be.ordina.springbatch.domain.Fine;
import be.ordina.springbatch.domain.LicensePlateType;
import be.ordina.springbatch.domain.TrajectInformation;

@Configuration
@EnableBatchProcessing
@ComponentScan
public class BatchConfiguration {
	
	@Bean
	public TrajectInformationTokenizer tokenizer() {
		return new TrajectInformationTokenizer();
	}
	
	@Bean
    public ItemReader<TrajectInformation> reader() {
		return new TrajectInformationReader(tokenizer());
    }

    @Bean
    public ItemProcessor<TrajectInformation, Fine> processor() {
    	return new TrajectInformationProcessor();
    }

    @Bean
    public ItemWriter<Fine> writer() {
    	return new FineInformationWriter();
    }
    
    @Bean
    public JobExecutionListener trajectInformationListener() {
    	return new TrajectInformationJobExecutionListener();
    }
    
    @Bean
    public NoWorkFoundStepExecutionListener noWorkFoundStepExecutionListener() {
    	return new NoWorkFoundStepExecutionListener();
    }
    
    @Bean
    public ExecutionContextPromotionListener statisticsListener() {
    	ExecutionContextPromotionListener executionContextPromotionListener = new ExecutionContextPromotionListener();
    	executionContextPromotionListener.setKeys(LicensePlateType.names());
		return executionContextPromotionListener;
    }
    

    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step processTrajectInformationStep) {
        return jobs.get("importTrajectInformation")
                .incrementer(new RunIdIncrementer())
                .listener(trajectInformationListener())
                .flow(processTrajectInformationStep)
                .end()
                .build();
    }

    @Bean
    public Step processTrajectInformationStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("processTrajectInformationStep")
                .<TrajectInformation, Fine> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(noWorkFoundStepExecutionListener())
                .listener(statisticsListener())
                .build();
    }
	
	

}

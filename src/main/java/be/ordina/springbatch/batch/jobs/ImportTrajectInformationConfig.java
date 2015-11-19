package be.ordina.springbatch.batch.jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.step.NoWorkFoundStepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.ordina.springbatch.batch.listener.SpringBatchWorkshopChunkListener;
import be.ordina.springbatch.batch.listener.TrajectInformationJobExecutionListener;
import be.ordina.springbatch.batch.processor.TrajectInformationProcessor;
import be.ordina.springbatch.batch.reader.TrajectInformationReader;
import be.ordina.springbatch.batch.reader.TrajectInformationTokenizer;
import be.ordina.springbatch.batch.writer.FineInformationWriter;
import be.ordina.springbatch.domain.Fine;
import be.ordina.springbatch.domain.LicensePlateType;
import be.ordina.springbatch.domain.TrajectInformation;

@Configuration
public class ImportTrajectInformationConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
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
    	List<String> keys = new ArrayList<>(Arrays.asList("graveErrors", "total"));
    	keys.addAll(Arrays.asList(LicensePlateType.names()));
		executionContextPromotionListener.setKeys(keys.toArray(new String[keys.size()]));
		return executionContextPromotionListener;
    }
    
    @Bean
    public ChunkListener chunkListener() {
    	return new SpringBatchWorkshopChunkListener();
    }
    

    @Bean
    public Job importTrajectInformationJob(Step processTrajectInformationStep) {
        return jobBuilderFactory.get("importTrajectInformation")
                .incrementer(new RunIdIncrementer())
                .listener(trajectInformationListener())
                .flow(processTrajectInformationStep)
                .end()
                .build();
    }

    @Bean
    public Step processTrajectInformationStep() {
        return stepBuilderFactory.get("processTrajectInformationStep")
                .<TrajectInformation, Fine> chunk(100000)
                .reader(reader())
                .faultTolerant()
                .skipLimit(5)
                .skip(FlatFileParseException.class)
                .processor(processor())
                .writer(writer())
                .listener(noWorkFoundStepExecutionListener())
                .listener(statisticsListener())
                .listener(chunkListener())
                .build();
    }
}

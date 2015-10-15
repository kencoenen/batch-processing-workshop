package be.ordina.springbatch.application.batch;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.Fine;

@Component
public class FineInformationWriter extends FlatFileItemWriter<Fine> {

	private StepExecution stepExecution;
	
	public FineInformationWriter(){
		this.setResource(new FileSystemResource("src/main/resources/output.csv"));
		BeanWrapperFieldExtractor<Fine> fieldExtractor = new BeanWrapperFieldExtractor<>();
		        fieldExtractor.setNames(new String[] { "licensePlate", "speed", "amountToPay", "graveError" });
		        DelimitedLineAggregator<Fine> delLineAgg = new DelimitedLineAggregator<>();
		        delLineAgg.setDelimiter(",");
		        delLineAgg.setFieldExtractor(fieldExtractor);
		        this.setLineAggregator(delLineAgg);
	}
	
	@Override
	public void write(List<? extends Fine> fines) throws Exception {
		super.write(fines);
		for (Fine fine : fines) {
			upTheTally(fine);
//			System.out.println(fine);
		}
	}


	private void upTheTally(Fine fine) {
		ExecutionContext executionContext = stepExecution.getExecutionContext();
		executionContext.put(fine.getLicensePlate().getType().name(), executionContext.getInt(fine.getLicensePlate().getType().name(), 0) + 1);
		executionContext.put("total", executionContext.getDouble("total", 0L) + fine.getAmountToPay());
		if(fine.isGraveError()) {
			executionContext.put("graveErrors", executionContext.getInt("graveErrors", 0) + 1);
		}
	}
	
	
    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}

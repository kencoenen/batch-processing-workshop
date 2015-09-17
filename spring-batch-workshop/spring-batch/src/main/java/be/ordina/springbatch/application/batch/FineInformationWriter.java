package be.ordina.springbatch.application.batch;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.Fine;

@Component
public class FineInformationWriter implements ItemWriter<Fine> {

	private StepExecution stepExecution;
	
	@Override
	public void write(List<? extends Fine> fines) throws Exception {
		for (Fine fine : fines) {
			upTheTally(fine);
			System.out.println(fine);
		}
	}


	private void upTheTally(Fine fine) {
		ExecutionContext executionContext = stepExecution.getExecutionContext();
		executionContext.put(fine.getLicensePlate().getType().name(), executionContext.getInt(fine.getLicensePlate().getType().name(), 0) + 1);
	}
	
	
    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}

package be.ordina.springbatch.application.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import be.ordina.springbatch.domain.LicensePlateType;

public class TrajectInformationJobExecutionListener implements JobExecutionListener{

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
			int reads = jobExecution.getStepExecutions().stream().mapToInt(se -> se.getReadCount()).sum();
			int writes = jobExecution.getStepExecutions().stream().mapToInt(se -> se.getWriteCount()).sum();
			int skips = jobExecution.getStepExecutions().stream().mapToInt(se -> se.getSkipCount()).sum();
			System.out.println("BATCH COMPLETED WITH GREAT SUCCESS reads = [ " + reads + " ] writes = [ " + writes + " ] skips = [" + skips +"]" );
			for (LicensePlateType licensePlateType : LicensePlateType.values()) {
				System.out.println(licensePlateType.name().toLowerCase() + " - " + jobExecution.getExecutionContext().getInt(licensePlateType.name(), 0));
			}
			System.out.println("Total grave errors this batch : " + jobExecution.getExecutionContext().getInt("graveErrors"));
			System.out.println("Total money stolen from the honest speeder : " + jobExecution.getExecutionContext().getDouble("total") +"€");
		} else {
			System.err.println("BATCH COMPLETED WITH STATUS " + jobExecution.getExitStatus().getExitCode());
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("STARTING THE BATCH");
		
	}

}

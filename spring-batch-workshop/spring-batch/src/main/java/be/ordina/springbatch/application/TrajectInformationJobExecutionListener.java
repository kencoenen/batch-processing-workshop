package be.ordina.springbatch.application;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class TrajectInformationJobExecutionListener implements JobExecutionListener{

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
			System.out.println("BATCH COMPLETED WITH GREAT SUCCESS");
		} else {
			System.err.println("BATCH COMPLETED WITH STATUS " + jobExecution.getExitStatus().getExitCode());
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("STARTING THE BATCH");
		
	}

}

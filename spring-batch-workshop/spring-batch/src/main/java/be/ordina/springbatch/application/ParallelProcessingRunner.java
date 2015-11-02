package be.ordina.springbatch.application;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ParallelProcessingRunner {

	public static void main(String[] args) {
		String[] configurationLocation = {
				"META-INF/spring/context-config.xml",
				"META-INF/spring/job-config.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				configurationLocation);
		Job job = (Job) ctx.getBean("myJob");
		JobLauncher jobLauncher = (JobLauncher) ctx.getBean("jobLauncher");
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Execution Status: " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

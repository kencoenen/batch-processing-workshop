package be.ordina.springbatch.application.batch;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.test.MetaDataInstanceFactory;

import be.ordina.springbatch.application.batch.listener.TrajectInformationJobExecutionListener;

public class TrajectInformationJobExecutionListenerTest {

	
	TrajectInformationJobExecutionListener listener = new TrajectInformationJobExecutionListener();
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	private static PrintStream stdout;

	@BeforeClass
	public static void saveStdOut() {
		stdout = System.out;
	}
	
	@AfterClass
	public static void resetOut() {
		System.setOut(stdout);   
	}
	
	@Test
	public void testBeforeJob() {
		JobExecution jobExecution = MetaDataInstanceFactory.createJobExecution(1L);
		listener.beforeJob(jobExecution);
		assertEquals("STARTING THE BATCH", outContent.toString().trim());
	}
	
	@Test
	public void testAfterJob() {
		JobExecution jobExecution = MetaDataInstanceFactory.createJobExecution(1L);
		jobExecution.setExitStatus(ExitStatus.COMPLETED);
		StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution();
		stepExecution.setReadCount(10);
		stepExecution.setWriteCount(2);
		stepExecution.setCommitCount(1);
		jobExecution.addStepExecutions(Arrays.asList(stepExecution));
		ExecutionContext executionContext = new ExecutionContext();
		executionContext.put("graveErrors", 1);
		executionContext.put("total", 2D);
		jobExecution.setExecutionContext(executionContext);
		listener.afterJob(jobExecution);
		Assert.assertTrue(outContent.toString().contains("Total grave errors this batch : 1"));
		Assert.assertTrue(outContent.toString().contains("2.0€"));
		Assert.assertTrue(outContent.toString().contains("reads = [ 10 ]"));
		Assert.assertTrue(outContent.toString().contains("writes = [ 2 ]"));
	}
	
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

}

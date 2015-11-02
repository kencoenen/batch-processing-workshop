package be.ordina.springbatch.springbatchapi.web.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;

import be.ordina.springbatch.springbatchapi.infrastructure.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JobExecutionResource {

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:mm:ss", timezone="CET")
	private Date creationDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:mm:ss", timezone="CET")
	private Date endDate;
	private ExitStatus exitStatus;
	private Long id;
	private String status;
	private List<String> exceptions = new ArrayList();
	private List<StepExecutionResource> steps = new ArrayList<>();
	private Map<String,Object> context = new HashMap<String, Object>();
	private Map<String, JobParameter> parameters = new HashMap<>();
	
	
	public static List<JobExecutionResource> fromList(List<JobExecution> executions) {
		List<JobExecutionResource> result = new ArrayList<JobExecutionResource>();
		
		for (JobExecution jobExecution: executions) {
			result.add(new JobExecutionResource(jobExecution));
		}
		
		return result;
	}
	
	public JobExecutionResource(JobExecution jobExecution) {
		this.creationDate = jobExecution.getCreateTime();
		this.endDate = jobExecution.getEndTime();
		this.id = jobExecution.getId();
		this.exitStatus = jobExecution.getExitStatus();
		this.status = jobExecution.getStatus().getBatchStatus().toString();
		this.parameters = jobExecution.getJobParameters().getParameters();
		
		for (Throwable throwable : jobExecution.getAllFailureExceptions()) {
			this.exceptions.add(ExceptionUtils.toString(throwable));
		}
		
		for (StepExecution step : jobExecution.getStepExecutions()) {
			this.steps.add(new StepExecutionResource(step));
		}
		
		for (Entry<String, Object> entry : jobExecution.getExecutionContext().entrySet()) {
			this.context.put(entry.getKey(), entry.getValue());
		}
	}
	
	public Map<String, JobParameter> getParameters() {
		return parameters;
	}
	
	public List<StepExecutionResource> getSteps() {
		return steps;
	}
	
	public Map<String, Object> getContext() {
		return context;
	}
	
	public List<String> getExceptions() {
		return exceptions;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public ExitStatus getExitStatus() {
		return exitStatus;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getStatus() {
		return status;
	}
	
}

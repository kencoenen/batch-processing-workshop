package be.ordina.springbatch.springbatchapi.web.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;

import com.fasterxml.jackson.annotation.JsonFormat;

import be.ordina.springbatch.springbatchapi.infrastructure.ExceptionUtils;

public class StepExecutionResource {
	
	private String name;
	private long id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:mm:ss", timezone="CET")
	private Date startTime;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:mm:ss", timezone="CET")
	private Date endTime;
	private BatchStatus status;
	private ExitStatus exitStatus;
	private int commitCount;
	private List<String> exceptions = new ArrayList<>();
	private Map<String,Object> context = new HashMap<String, Object>();
	private int readCount;
	private int writeCount;
	private int skipCount;
	private int readSkipCount;
	private int processSkipCount;
	private int writeSkipCount;
	private int filterCount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:mm:ss", timezone="CET")
	private Date lastUpdated;
	private int rollbackCount;
	private String summary;
	
	public static List<StepExecutionResource> fromList(List<StepExecution> executions) {
		List<StepExecutionResource> result = new ArrayList<StepExecutionResource>();
		
		for (StepExecution jobExecution: executions) {
			result.add(new StepExecutionResource(jobExecution));
		}
		
		return result;
	}
	
	public StepExecutionResource(StepExecution stepExecution) {
		this.id = stepExecution.getId();
		this.name = stepExecution.getStepName();
		this.startTime = stepExecution.getStartTime();
		this.endTime = stepExecution.getEndTime();
		this.exitStatus = stepExecution.getExitStatus();
		this.status = stepExecution.getStatus();
		this.commitCount = stepExecution.getCommitCount();
		
		for (Entry<String, Object> entry : stepExecution.getExecutionContext().entrySet()) {
			this.context.put(entry.getKey(), entry.getValue());
		}
		
		this.readCount = stepExecution.getReadCount();
		this.writeCount = stepExecution.getWriteCount();
		this.readSkipCount = stepExecution.getReadSkipCount();
		this.processSkipCount = stepExecution.getProcessSkipCount();
		this.writeSkipCount = stepExecution.getWriteSkipCount();
		this.summary = stepExecution.getSummary();
		this.filterCount = stepExecution.getFilterCount();
		this.rollbackCount = stepExecution.getRollbackCount();
		this.lastUpdated = stepExecution.getLastUpdated();
		
		for (Throwable throwable : stepExecution.getFailureExceptions()) {
			this.exceptions.add(ExceptionUtils.toString(throwable));
		}
		
	}
	
	public List<String> getExceptions() {
		return exceptions;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public BatchStatus getStatus() {
		return status;
	}

	public ExitStatus getExitStatus() {
		return exitStatus;
	}

	public int getCommitCount() {
		return commitCount;
	}

	public Map<String,Object> getContext() {
		return context;
	}

	public int getReadCount() {
		return readCount;
	}

	public int getWriteCount() {
		return writeCount;
	}

	public int getSkipCount() {
		return skipCount;
	}

	public int getReadSkipCount() {
		return readSkipCount;
	}

	public int getProcessSkipCount() {
		return processSkipCount;
	}

	public int getWriteSkipCount() {
		return writeSkipCount;
	}

	public int getFilterCount() {
		return filterCount;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public int getRollbackCount() {
		return rollbackCount;
	}

	public String getSummary() {
		return summary;
	}
	
	
}

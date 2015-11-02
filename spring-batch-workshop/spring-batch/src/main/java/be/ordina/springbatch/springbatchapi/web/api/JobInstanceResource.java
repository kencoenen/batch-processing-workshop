package be.ordina.springbatch.springbatchapi.web.api;

import static be.ordina.springbatch.springbatchapi.web.api.JobExecutionResource.fromList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class JobInstanceResource extends ResourceSupport {

	private Long instanceId;

	private List<JobExecutionResource> executions = new ArrayList<>();
	
	
	
	public JobInstanceResource (JobInstance instance, List<JobExecution> jobExecutions) {
		this.instanceId = instance.getInstanceId();
		this.executions = fromList(jobExecutions);
	}
	
	public Long getInstanceId() {
		return instanceId;
	}
	
	public List<JobExecutionResource> getExecutions() {
		return executions;
	}
	
}

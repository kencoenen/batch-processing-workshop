package be.ordina.springbatch.springbatchapi.web.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/batch/executions")
public class ExecutionsController {

	private JobExplorer jobExplorer;
	
	public ExecutionsController(JobExplorer jobExplorer) {
		super();
		this.jobExplorer = jobExplorer;
	}

	@RequestMapping(method = GET, value="/{executionId}")
	public JobExecutionResource getOne(@PathVariable Long executionId) {
		return new JobExecutionResource(jobExplorer.getJobExecution(executionId));
	}
	
}

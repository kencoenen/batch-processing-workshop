package be.ordina.springbatch.springbatchapi.web.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@RequestMapping(value = "/api/batch/jobs")
public class JobsController {

	private JobExplorer jobExplorer;
	
	private List<Job> jobs;
	
	private JobLauncher launcher;
	
	public JobsController(JobExplorer jobExplorer, List<Job> jobs, JobLauncher launcher) {
		this.jobExplorer = jobExplorer;
		this.jobs = jobs;
		this.launcher = launcher;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<JobResource> get() {
		List<JobResource> jobResources = new ArrayList<>();
		JobResourceAssembler resourceAssembler = new JobResourceAssembler(jobExplorer);
		for (Job job : jobs) {
			jobResources.add(resourceAssembler.toResource(job));
		}
		return jobResources;
	}
	
	
	@RequestMapping(value="/{jobName}", method = RequestMethod.GET)
	public List<JobInstanceResource> getInstances(@PathVariable String jobName) {
		List<JobInstanceResource> resources = new ArrayList<>();
		
		List<JobInstance> instances = jobExplorer.getJobInstances(jobName, 0, Integer.MAX_VALUE);

		for (JobInstance jobInstance : instances) {
			resources.add(new JobInstanceResource(jobInstance, jobExplorer.getJobExecutions(jobInstance)));
		}
		
		return resources;
	}

	@RequestMapping(value = "/{jobName}/{instanceId}", method = RequestMethod.GET)
	public JobInstanceResource getInstance(@PathVariable String jobName, @PathVariable long instanceId) {
		JobInstance jobInstance = jobExplorer.getJobInstance(instanceId);
		return new JobInstanceResource(jobInstance, jobExplorer.getJobExecutions(jobInstance));
	}
	
	@RequestMapping(value="/{jobName}/execution/{executionId}", method = RequestMethod.GET)
	public List<JobInstanceResource> getExecution(@PathVariable String jobName) {
		List<JobInstanceResource> resources = new ArrayList<>();
		
		List<JobInstance> instances = jobExplorer.getJobInstances(jobName, 0, Integer.MAX_VALUE);

		for (JobInstance jobInstance : instances) {
			resources.add(new JobInstanceResource(jobInstance, jobExplorer.getJobExecutions(jobInstance)));
		}
		
		return resources;
	}
	
	
	@RequestMapping(value="/{jobName}", method = RequestMethod.POST)
	public ResponseEntity<Void> createInstance(@PathVariable String jobName, @RequestParam Map<String, Object> parameters) {
		
		Optional<Job> job = jobs.stream().filter(j -> j.getName().equals(jobName)).findFirst();
		Assert.isTrue(job.isPresent(), "Unknown job " + jobName);
		
		try {
			JobParameters convertedParameters = convertParameters(parameters);
			if (job.get().getJobParametersIncrementer() != null) {
				convertedParameters = job.get().getJobParametersIncrementer().getNext(convertedParameters);
			}
			JobExecution execution = launcher.run(job.get(), convertedParameters);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(linkTo(methodOn(ExecutionsController.class).getOne(execution.getId())).toUri());
			return new ResponseEntity<>(headers, HttpStatus.OK);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	protected JobParameters convertParameters(Map<String, Object> parameters) {
		Map<String, JobParameter> convertedParams = new HashMap<>();
		
		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			
			if (value instanceof Date) {
				convertedParams.put(key, new JobParameter( (Date) value));
			} else if (value instanceof Long) {
				convertedParams.put(key, new JobParameter( (Long) value));
			} else if (value instanceof Double) {
				convertedParams.put(key, new JobParameter( (Double) value));
			} else {
				convertedParams.put(key, new JobParameter(value.toString()));
			}
		}
		
		return new JobParameters(convertedParams);
	}
	
	
	
}

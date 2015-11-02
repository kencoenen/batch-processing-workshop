package be.ordina.springbatch.springbatchapi.web.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * @author jherfurth
 */
public class JobResourceAssembler extends ResourceAssemblerSupport<Job, JobResource> {

    private JobExplorer jobExplorer;

    public JobResourceAssembler(JobExplorer jobExplorer) {
        super(JobsController.class, JobResource.class);
        this.jobExplorer = jobExplorer;
    }

    @Override
    public JobResource toResource(Job job) {
        JobResource resource = new JobResource(job);
        List<JobInstance> instances = jobExplorer.getJobInstances(job.getName(), 0, Integer.MAX_VALUE);
        for (JobInstance ji : instances) {
            resource.add(linkTo(methodOn(JobsController.class, ji.getId()).getInstance(job.getName(), ji.getInstanceId())).withRel("instance"));
        }
        return resource;
    }
}

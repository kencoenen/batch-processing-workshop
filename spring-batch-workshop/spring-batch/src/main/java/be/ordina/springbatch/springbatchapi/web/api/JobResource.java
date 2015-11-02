package be.ordina.springbatch.springbatchapi.web.api;

import org.springframework.batch.core.Job;
import org.springframework.hateoas.ResourceSupport;

/**
 * The collection of jobs in the batch application.
 *
 * @author jherfurth
 */
public class JobResource extends ResourceSupport {

    private String name;

    public JobResource(Job job) {
        this.name = job.getName();
    }

    public String getName() {
        return name;
    }
}

package be.ordina.springbatch.springbatchapi.infrastructure;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.ordina.springbatch.springbatchapi.web.api.ExecutionsController;
import be.ordina.springbatch.springbatchapi.web.api.JobsController;
import be.ordina.springbatch.springbatchapi.web.api.RootController;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnBean({JobExplorer.class, JobLauncher.class, Job.class})
@AutoConfigureAfter({BatchAutoConfiguration.class, EmbeddedServletContainerAutoConfiguration.class})
public class SpringBatchApiAutoconfiguration {
	
	@Bean
	public ExecutionsController executionsController(JobExplorer jobExplorer) {
		return new ExecutionsController(jobExplorer);
	}
	
	@Bean
	public JobsController jobsController(JobExplorer explorer, List<Job> jobs, JobLauncher launcher) {
		return new JobsController(explorer, jobs, launcher);
	}
	
	@Bean
	public RootController batchRootController() {
		return new RootController();
	}
}

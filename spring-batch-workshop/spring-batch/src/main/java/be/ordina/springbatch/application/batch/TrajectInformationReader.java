package be.ordina.springbatch.application.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.TrajectInformation;

@Component
public class TrajectInformationReader extends FlatFileItemReader<TrajectInformation> {

	@Autowired
	private TrajectInformationLineMapper lineMapper;
	
	public TrajectInformationReader() {
		super();
	}

	public TrajectInformationReader(LineTokenizer trajectInformationTokenizer) {
		super();
		this.setResource(new ClassPathResource("input.txt", getClass().getClassLoader()));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setLineMapper(lineMapper);
		super.afterPropertiesSet();
	}
	
	
}

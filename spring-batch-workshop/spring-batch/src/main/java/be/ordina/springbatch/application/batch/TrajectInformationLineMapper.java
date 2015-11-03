package be.ordina.springbatch.application.batch;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.TrajectInformation;

@Component
public class TrajectInformationLineMapper extends DefaultLineMapper<TrajectInformation> implements InitializingBean{

	@Autowired
	private TrajectInformationTokenizer tokenizer;
	
	@Autowired
	private TrajectInformationFieldSetMapper fieldSetMapper; 
	
	@Override
	public void afterPropertiesSet() {
		this.setLineTokenizer(tokenizer);
		this.setFieldSetMapper(fieldSetMapper);
		super.afterPropertiesSet();
	}
	
	

	
	
}

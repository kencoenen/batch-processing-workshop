package be.ordina.springbatch.application;

import java.beans.PropertyEditor;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.LicensePlate;
import be.ordina.springbatch.domain.TrajectInformation;
import be.ordina.springbatch.domain.propertyeditor.LicensePlatePropertyEditor;
import be.ordina.springbatch.domain.propertyeditor.LocalDateTimePropertyEditor;

public class TrajectInformationReader extends FlatFileItemReader<TrajectInformation> {

	public TrajectInformationReader() {
		super();
	}

	public TrajectInformationReader(LineTokenizer trajectInformationTokenizer) {
		super();
		this.setResource(new ClassPathResource("input.txt", getClass().getClassLoader()));
		this.setLineMapper(new DefaultLineMapper<TrajectInformation>() {
			
			{
				setLineTokenizer(trajectInformationTokenizer);
				setFieldSetMapper(new BeanWrapperFieldSetMapper<TrajectInformation>() {
					{
						setTargetType(TrajectInformation.class);
						Map<String, PropertyEditor> editorMap = new HashMap<String, PropertyEditor>();
						editorMap.put(LocalDateTime.class.getName(), new LocalDateTimePropertyEditor());
						editorMap.put(LicensePlate.class.getName(), new LicensePlatePropertyEditor());
						setCustomEditors(editorMap);
					}
				});
			}
		});
	}
}

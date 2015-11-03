package be.ordina.springbatch.batch.reader;

import java.beans.PropertyEditor;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.batch.propertyeditor.LicensePlatePropertyEditor;
import be.ordina.springbatch.batch.propertyeditor.LocalDateTimePropertyEditor;
import be.ordina.springbatch.domain.LicensePlate;
import be.ordina.springbatch.domain.TrajectInformation;

@Component
public class TrajectInformationFieldSetMapper extends BeanWrapperFieldSetMapper<TrajectInformation>{

	public TrajectInformationFieldSetMapper() {
		super();
		this.setTargetType(TrajectInformation.class);
		Map<String, PropertyEditor> editorMap = new HashMap<String, PropertyEditor>();
		editorMap.put(LocalDateTime.class.getName(), new LocalDateTimePropertyEditor());
		editorMap.put(LicensePlate.class.getName(), new LicensePlatePropertyEditor());
		this.setCustomEditors(editorMap);
	}
	
	

}

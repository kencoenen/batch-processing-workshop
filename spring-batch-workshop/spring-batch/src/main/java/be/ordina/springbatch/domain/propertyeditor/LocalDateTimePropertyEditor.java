package be.ordina.springbatch.domain.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimePropertyEditor extends PropertyEditorSupport {

	private DateTimeFormatter formatter;

	public LocalDateTimePropertyEditor() {
		super();
		formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(LocalDateTime.parse(text, formatter));
	}
}

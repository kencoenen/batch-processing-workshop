package be.ordina.springbatch.propertyeditor;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

import be.ordina.springbatch.batch.propertyeditor.LocalDateTimePropertyEditor;

public class LocalDateTimePropertyEditorTest {

	@Test
	public void testSetAsText() {
		LocalDateTimePropertyEditor localDateTimePropertyEditor = new LocalDateTimePropertyEditor();
		localDateTimePropertyEditor.setAsText("20150110201015");
		assertEquals(LocalDateTime.of(2015, Month.JANUARY, 10, 20, 10, 15), localDateTimePropertyEditor.getValue());
	}

}

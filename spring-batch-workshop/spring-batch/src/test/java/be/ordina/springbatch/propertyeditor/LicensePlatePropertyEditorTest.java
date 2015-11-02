package be.ordina.springbatch.propertyeditor;

import static org.junit.Assert.*;

import org.junit.Test;

import be.ordina.springbatch.domain.LicensePlate;

public class LicensePlatePropertyEditorTest {

	private static final String LICENSE_PLATE_RAW_INPUT = "1-DVL-977";

	@Test
	public void testSetAsText() {
		LicensePlatePropertyEditor licensePlatePropertyEditor = new LicensePlatePropertyEditor();
		licensePlatePropertyEditor.setAsText(LICENSE_PLATE_RAW_INPUT);
		assertEquals(new LicensePlate(LICENSE_PLATE_RAW_INPUT), licensePlatePropertyEditor.getValue());
	}
}

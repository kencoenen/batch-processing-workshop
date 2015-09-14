package be.ordina.springbatch.domain.propertyeditor;

import java.beans.PropertyEditorSupport;

import be.ordina.springbatch.domain.LicensePlate;

public class LicensePlatePropertyEditor extends PropertyEditorSupport {

	public LicensePlatePropertyEditor() {
		super();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(new LicensePlate(text));
	}
}

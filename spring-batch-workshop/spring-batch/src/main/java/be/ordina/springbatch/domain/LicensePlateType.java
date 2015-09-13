package be.ordina.springbatch.domain;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum LicensePlateType {
	//1-AAA-000
	STANDARD(Pattern.compile("^[1-9]-[A-Z]{3}-[0-9]{3}$")),
	//CD-AAOOO
	CORPS_DIPLOMATIQUE(Pattern.compile("^CD-[A-Z0-9]{3,6}$")),
	//AAA-000
	OLD(Pattern.compile("^[A-Z]{3}-[0-9]{3}$")),
	//catchall
	CUSTOM(Pattern.compile("^[0-9A-Z]{1,9}$"));
	
	private Pattern pattern;
	
	private LicensePlateType(Pattern pattern) {
		this.pattern = pattern;
	}

	public static LicensePlateType fromRawInput(String rawInput) {
		return Arrays.stream(LicensePlateType.values()).filter(plateType -> plateType.pattern.matcher(rawInput).matches()).findFirst().get();
	}

}

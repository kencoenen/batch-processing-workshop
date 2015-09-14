package be.ordina.springbatch.domain;

public class LicensePlate {
	
	private String rawInput;
	private LicensePlateType type;
	
	public LicensePlate(String rawInput) {
		super();
		this.rawInput = rawInput;
		this.type = LicensePlateType.fromRawInput(rawInput);
	}
	
	public String getRawInput() {
		return rawInput;
	}
	public LicensePlateType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "LicensePlate [rawInput=" + rawInput + ", type=" + type + "]";
	}
	
}

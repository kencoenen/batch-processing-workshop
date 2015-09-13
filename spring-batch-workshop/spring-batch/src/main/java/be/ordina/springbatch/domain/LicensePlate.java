package be.ordina.springbatch.domain;

public class LicensePlate {
	
	private String rawInput;
	private LicensePlateType type;
	
	public LicensePlate(String rawInput, LicensePlateType type) {
		super();
		this.rawInput = rawInput;
		this.type = type;
	}
	
	public String getRawInput() {
		return rawInput;
	}
	public void setRawInput(String rawInput) {
		this.rawInput = rawInput;
	}
	public LicensePlateType getType() {
		return type;
	}
	public void setType(LicensePlateType type) {
		this.type = type;
	}
	

}

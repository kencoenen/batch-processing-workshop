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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rawInput == null) ? 0 : rawInput.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LicensePlate other = (LicensePlate) obj;
		if (rawInput == null) {
			if (other.rawInput != null)
				return false;
		} else if (!rawInput.equals(other.rawInput))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return rawInput + "," + type;
	}

}

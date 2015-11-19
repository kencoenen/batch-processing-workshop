package be.ordina.springbatch.domain;

import java.time.LocalDateTime;

public class TrajectInformation {
	
	private LocalDateTime incomingTime;
	private LocalDateTime outgoingTime;
	private LicensePlate licensePlate;
	
	
	
	public TrajectInformation() {
		super();
	}
	
	public TrajectInformation(LocalDateTime incomingTime,
			LocalDateTime outgoingTime, LicensePlate licensePlate) {
		super();
		this.incomingTime = incomingTime;
		this.outgoingTime = outgoingTime;
		this.licensePlate = licensePlate;
	}
	public LocalDateTime getIncomingTime() {
		return incomingTime;
	}
	public LocalDateTime getOutgoingTime() {
		return outgoingTime;
	}
	public LicensePlate getLicensePlate() {
		return licensePlate;
	}

	public void setIncomingTime(LocalDateTime incomingTime) {
		this.incomingTime = incomingTime;
	}

	public void setOutgoingTime(LocalDateTime outgoingTime) {
		this.outgoingTime = outgoingTime;
	}

	public void setLicensePlate(LicensePlate licensePlate) {
		this.licensePlate = licensePlate;
	}
	
}

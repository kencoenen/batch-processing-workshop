package be.ordina.springbatch.domain;

public class Fine {
	
	private LicensePlate licensePlate;
	private int speed;
	private long amountToPay;
	private boolean graveError;
	
	public Fine(LicensePlate licensePlate, int speed, long amountToPay, boolean graveError) {
		super();
		this.licensePlate = licensePlate;
		this.speed = speed;
		this.amountToPay = amountToPay;
		this.graveError = graveError;
	}
	public int getSpeed() {
		return speed;
	}
	public long getAmountToPay() {
		return amountToPay;
	}
	public boolean isGraveError() {
		return graveError;
	}
	public LicensePlate getLicensePlate() {
		return licensePlate;
	}
	@Override
	public String toString() {
		return "Fine [licensePlate=" + licensePlate + ", speed=" + speed
				+ ", amountToPay=" + amountToPay + ", graveError=" + graveError
				+ "]";
	}
	
	
}

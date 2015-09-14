package be.ordina.springbatch.domain;

public class Fine {
	
	public static final int MAX_SPEED = 130;
	private LicensePlate licensePlate;
	private int speed;
	private long amountToPay;
	private boolean graveError;
	
	public Fine(LicensePlate licensePlate, int speed) {
		super();
		this.licensePlate = licensePlate;
		this.speed = speed;
		calculateAmountToPay(speed);
	}
	private void calculateAmountToPay(int speed) {
		if(speed > MAX_SPEED) {
			this.amountToPay = 50l + ((speed - MAX_SPEED)/10 * 10);
		}
		this.graveError = speed - MAX_SPEED > 30;
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

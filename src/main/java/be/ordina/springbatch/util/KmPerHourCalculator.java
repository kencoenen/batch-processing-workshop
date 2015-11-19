package be.ordina.springbatch.util;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class KmPerHourCalculator {
	
	private BigDecimal distance;
	
	public KmPerHourCalculator(BigDecimal distance) {
		this.distance = distance;
	}

	public BigDecimal calculateKmPerHour(LocalDateTime incomingTime, LocalDateTime outgoingTime) {
		Long timeSpent = incomingTime.until(outgoingTime, SECONDS);
		BigDecimal hour = new BigDecimal(timeSpent).divide(new BigDecimal(3600),5,RoundingMode.UP);
		BigDecimal coefficient = new BigDecimal(1).divide(hour, 5, RoundingMode.UP);
		BigDecimal kmPerHour = distance.multiply(coefficient);
		return kmPerHour;
	}

}

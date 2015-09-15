package be.ordina.springbatch.application;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.Fine;
import be.ordina.springbatch.domain.TrajectInformation;

@Component
public class TrajectInformationProcessor implements ItemProcessor<TrajectInformation, Fine> {
	
	//Distance in Km
	private static final BigDecimal DISTANCE = new BigDecimal(10);

	@Override
	public Fine process(TrajectInformation trajectInformation) throws Exception {
		BigDecimal kmPerHour = calculateKmPerHour(trajectInformation);
		if(kmPerHour.intValue() > Fine.MAX_SPEED) {
			return new Fine(trajectInformation.getLicensePlate(), kmPerHour.intValue());
		} else {
			return null;
		}
	}

	private BigDecimal calculateKmPerHour(TrajectInformation trajectInformation) {
		Long timeSpent = trajectInformation.getIncomingTime().until(trajectInformation.getOutgoingTime(), SECONDS);
		BigDecimal hour = new BigDecimal(timeSpent).divide(new BigDecimal(3600),5,RoundingMode.UP);
		BigDecimal coefficient = new BigDecimal(1).divide(hour, 5, RoundingMode.UP);
		BigDecimal kmPerHour = DISTANCE.multiply(coefficient);
		return kmPerHour;
	}
	
}

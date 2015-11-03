package be.ordina.springbatch.application.batch.processor;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.Fine;
import be.ordina.springbatch.domain.TrajectInformation;
import be.ordina.springbatch.util.KmPerHourCalculator;

@Component
public class TrajectInformationProcessor implements ItemProcessor<TrajectInformation, Fine> {
	
	//Distance in Km
	private static final BigDecimal DISTANCE = new BigDecimal(10);
	
	private KmPerHourCalculator calculator;
	

	public TrajectInformationProcessor() {
		super();
		calculator = new KmPerHourCalculator(DISTANCE);
	}

	@Override
	public Fine process(TrajectInformation trajectInformation) throws Exception {
		BigDecimal kmPerHour = calculator.calculateKmPerHour(trajectInformation.getIncomingTime(), trajectInformation.getOutgoingTime());
		if(kmPerHour.intValue() > Fine.MAX_SPEED) {
			return new Fine(trajectInformation.getLicensePlate(), kmPerHour.intValue());
		} else {
			return null;
		}
	}
}

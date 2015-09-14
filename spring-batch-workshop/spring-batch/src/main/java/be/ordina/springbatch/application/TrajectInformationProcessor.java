package be.ordina.springbatch.application;

import static java.time.temporal.ChronoUnit.SECONDS;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.Fine;
import be.ordina.springbatch.domain.TrajectInformation;

@Component
public class TrajectInformationProcessor implements ItemProcessor<TrajectInformation, Fine>{

	@Override
	public Fine process(TrajectInformation trajectInformation) throws Exception {
		Long timeSpent = trajectInformation.getIncomingTime().until(trajectInformation.getOutgoingTime(), SECONDS);
		return new Fine(trajectInformation.getLicensePlate(), timeSpent.intValue(), 2l, false);
	}

}

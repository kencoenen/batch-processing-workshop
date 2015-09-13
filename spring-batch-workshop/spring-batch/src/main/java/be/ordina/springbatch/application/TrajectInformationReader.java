package be.ordina.springbatch.application;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import be.ordina.springbatch.domain.TrajectInformation;

public class TrajectInformationReader implements ItemReader<TrajectInformation> {

	@Override
	public TrajectInformation read() throws Exception,
			UnexpectedInputException, ParseException,
			NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}

}

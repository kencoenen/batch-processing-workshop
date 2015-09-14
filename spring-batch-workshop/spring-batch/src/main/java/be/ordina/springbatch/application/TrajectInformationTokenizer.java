package be.ordina.springbatch.application;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.stereotype.Component;

@Component
public class TrajectInformationTokenizer extends FixedLengthTokenizer {

	public TrajectInformationTokenizer() {
		super();
		setNames(new String[]{"incomingTime","outgoingTime","licensePlate"});
		setColumns(new Range[]{new Range(1,14), new Range(15,28), new Range(29)});
	}
}

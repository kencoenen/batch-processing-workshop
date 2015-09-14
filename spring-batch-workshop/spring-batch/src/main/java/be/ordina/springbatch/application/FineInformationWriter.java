package be.ordina.springbatch.application;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import be.ordina.springbatch.domain.Fine;

@Component
public class FineInformationWriter implements ItemWriter<Fine> {

	@Override
	public void write(List<? extends Fine> fines) throws Exception {
		fines.forEach(System.out::println);
	}

}

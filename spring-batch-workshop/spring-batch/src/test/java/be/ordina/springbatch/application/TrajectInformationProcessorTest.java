package be.ordina.springbatch.application;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.junit.Test;

import be.ordina.springbatch.batch.processor.TrajectInformationProcessor;
import be.ordina.springbatch.domain.Fine;
import be.ordina.springbatch.domain.LicensePlate;
import be.ordina.springbatch.domain.LicensePlateType;
import be.ordina.springbatch.domain.TrajectInformation;

public class TrajectInformationProcessorTest {
	
	private TrajectInformationProcessor processor = new TrajectInformationProcessor();
	
	@Test
	public void testHappyPath() {
		TrajectInformation information = new TrajectInformation();
		information.setIncomingTime(LocalDateTime.now());
		information.setOutgoingTime(LocalDateTime.now().plusMinutes(3));
		information.setLicensePlate(new LicensePlate("1-AAA-000"));
		try {
			Fine fine = processor.process(information);
			assertThat(fine.getAmountToPay(), equalTo(120L));
			assertThat(fine.getSpeed(), equalTo(200));
			assertThat(fine.getLicensePlate().getRawInput(), equalTo("1-AAA-000"));
			assertThat(fine.getLicensePlate().getType(), equalTo(LicensePlateType.STANDARD));
			assertThat(fine.isGraveError(), equalTo(true));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Shouldn't throw an exception");
		}
	}
	
	@Test
	public void testUnderSpeedLimit() {
		TrajectInformation information = new TrajectInformation();
		information.setIncomingTime(LocalDateTime.now());
		information.setOutgoingTime(LocalDateTime.now().plusMinutes(4).plusSeconds(35L));
		information.setLicensePlate(new LicensePlate("1-AAA-000"));
		try {
			Fine fine = processor.process(information);
			assertThat(fine, is(nullValue()));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Shouldn't throw an exception");
		}
	}

}

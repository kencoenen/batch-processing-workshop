package be.ordina.springbatch.domain;

import static be.ordina.springbatch.domain.LicensePlateType.CORPS_DIPLOMATIQUE;
import static be.ordina.springbatch.domain.LicensePlateType.CUSTOM;
import static be.ordina.springbatch.domain.LicensePlateType.OLD;
import static be.ordina.springbatch.domain.LicensePlateType.STANDARD;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Test;

public class LicensePlateTypeTest {

	@Test
	public void testStandard() {
		assertThat(LicensePlateType.fromRawInput("1-AAA-000"), is(STANDARD));
	}
	
	@Test
	public void testCorpsDiplomatique() {
		assertThat(LicensePlateType.fromRawInput("CD-AE466"), is(CORPS_DIPLOMATIQUE));
	}
	
	@Test
	public void testOld() {
		assertThat(LicensePlateType.fromRawInput("AAA-000"), is(OLD));
	}
	
	@Test
	public void testCustom() {
		assertThat(LicensePlateType.fromRawInput("SSHHH"), is(CUSTOM));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testErroneous() {
		LicensePlateType.fromRawInput("WAYTOOLONGLICENSEPLATE");
	}

}

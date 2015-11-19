package be.ordina.springbatch.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;

public class KmPerHourCalculatorTest {

	@Test
	public void testCalculate() {
		KmPerHourCalculator calculator = new KmPerHourCalculator(new BigDecimal(10));
		BigDecimal calculateKmPerHour = calculator.calculateKmPerHour(LocalDateTime.now(), LocalDateTime.now().plusMinutes(3));
		assertEquals(200, calculateKmPerHour.intValue());
	}
}

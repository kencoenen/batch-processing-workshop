package be.ordina.springbatch.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class InputGeneratorTest {
	
	private static final DateTimeFormatter DEFAULT_DATE_PATTERN = DateTimeFormatter.ofPattern("YYYYMMddHHmmss");
	private Random random = new Random();
	private static List<String> licensePlates = Arrays.asList("ABC-123","1-XYZ-999","SHHHHH","9-JOS-999","CD-554BDE","1-FRG-020","1-DVL-977","1-EKP-240","PORSHJ911","LOLOIL","1-POP-030","1-CVJ-564","CD-200HJI","AZE-390","CD-45XJC","1-JKL-008","JOVALLLY");
	
	@Ignore("unIgnore this test to generate data")
	@Test
	public void generateInput() {
		System.out.println("generating input file, this can take up to a minute or 2, please be patient...");
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./src/main/resources/input.txt"), "utf-8"))) {
			for (int i = 0; i < 5000000; i++) {
				Double chance = random.nextDouble() * 10;
				Integer minute;
				if(chance < 0.05) {
					minute = 3;
				} else if (chance < 0.3) {
					minute = 4;
				} else {
					minute = 5;
				}
				
				Integer seconds = random.nextInt(61) + 1;
				LocalDateTime startTime = LocalDateTime.now();
				LocalDateTime endTime = startTime.plusMinutes(minute).plusSeconds(seconds);
				String licensePlate = licensePlates.get(random.nextInt(licensePlates.size()));
				writer.write(startTime.format(DEFAULT_DATE_PATTERN)+endTime.format(DEFAULT_DATE_PATTERN)+licensePlate);
				writer.newLine();
			}
			writer.write("THISLINESHOULDCAUSEASKIPINTHEEND");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println("DONE");
	}

}

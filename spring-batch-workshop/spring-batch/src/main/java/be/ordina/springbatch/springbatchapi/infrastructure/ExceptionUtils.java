package be.ordina.springbatch.springbatchapi.infrastructure;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	public static String toString(Throwable t) {
		StringWriter writeToString = new StringWriter();
		PrintWriter writer = new PrintWriter(writeToString);
		t.printStackTrace(writer);
		return writer.toString();
	}
	
}

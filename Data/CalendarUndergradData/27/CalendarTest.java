import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class CalendarTest {

	//This function wraps Calendar.main so that it can be tested as if it's a single unit. 
	//The OBSERVABILITY is really bad because the only way for the tests to get the output is if
	//it's captured in a separate output stream, then compared as a string to the expected output. It would
	//be much easier to observe output if it was returned from individual units of code rather than spit out
	//by the program as a whole.
	
	//The CONTROLLABILITY is really bad as well because rather than allowing me to enter input as parameters 
	//to a function (the way unit testing is supposed to work), I have to run the entire program and
	//simulate an input stream from the user, then capture the output in a separate stream and test
	//that it matches the expected output. In addition, main is the only function that's public 
	//so it's the only one that I can test, even though most of the variability is coming from isLeapYear().
	//It would be much easier to test if I could run unit tests on isLeapYear() separately.
	private String runCalendarMain(String userInput) {
		//need to create an artificial input stream to simulate user input since it can't be passed to main
		//as arguments. Otherwise tests can't be automated.
		InputStream stream = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
		System.setIn(stream);
		
		//need to capture output in a print stream since it isn't returned from main, otherwise
		//I can't check if it's correct.
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream stdOut = System.out;
		System.setOut(ps);
		
		//run the program using input from userInput and capture output in ps
		Calendar.main(null);
		
		//dump ps into baos
		System.out.flush();
		System.setOut(stdOut);
		return baos.toString();
	}
	
	@Test
	void testNonLeapYear() {
		//This string addresses the observability aspect of the test because it is the fully
		//formatted output that I expect to receive after running main. Failures can only be 
		//revealed in the output of main.
		String sept2019 = 
				"Enter month: Enter year:    September 2019\r\n" +
				" S  M Tu  W Th  F  S\r\n" +
				" 1  2  3  4  5  6  7 \r\n" +
				" 8  9 10 11 12 13 14 \r\n" +
				"15 16 17 18 19 20 21 \r\n" +
				"22 23 24 25 26 27 28 \r\n" +
				"29 30 \r\n";
		
		//The input to runCalendarMain addresses the controllability aspect of the test, since
		//input directly to main from an input stream is the only way to communicate with the
		//program.
		assertEquals(sept2019, runCalendarMain("9\n2019\n"));
	}
	
	@Test
	void testLeapYear() {
		//This string addresses the observability aspect of the test because it is the fully
		//formatted output that I expect to receive after running main. Failures can only be 
		//revealed in the output of main.
		String feb2016 = 
				"Enter month: Enter year:    February 2016\r\n" +
				" S  M Tu  W Th  F  S\r\n" +
				"    1  2  3  4  5  6 \r\n" +
				" 7  8  9 10 11 12 13 \r\n" +
				"14 15 16 17 18 19 20 \r\n" +
				"21 22 23 24 25 26 27 \r\n" +
				"28 29 \r\n";
		
		//The input to runCalendarMain addresses the controllability aspect of the test, since
		//input directly to main from an input stream is the only way to communicate with the
		//program.
		assertEquals(feb2016, runCalendarMain("2\n2016\n"));
	}
	
	@Test
	void test100LeapYear() {
		//This string addresses the observability aspect of the test because it is the fully
		//formatted output that I expect to receive after running main. Failures can only be 
		//revealed in the output of main.
		String feb1900 = 
				"Enter month: Enter year:    February 1900\r\n" +
				" S  M Tu  W Th  F  S\r\n" +
				"             1  2  3 \r\n" +
				" 4  5  6  7  8  9 10 \r\n" +
				"11 12 13 14 15 16 17 \r\n" +
				"18 19 20 21 22 23 24 \r\n" +
				"25 26 27 28 \r\n";
		
		//The input to runCalendarMain addresses the controllability aspect of the test, since
		//input directly to main from an input stream is the only way to communicate with the
		//program.
		assertEquals(feb1900, runCalendarMain("2\n1900\n"));
	}
	
	@Test
	void test400LeapYear() {
		//This string addresses the observability aspect of the test because it is the fully
		//formatted output that I expect to receive after running main. Failures can only be 
		//revealed in the output of main.
		String feb2000 = 
				"Enter month: Enter year:    February 2000\r\n" +
				" S  M Tu  W Th  F  S\r\n" +
				"       1  2  3  4  5 \r\n" +
				" 6  7  8  9 10 11 12 \r\n" +
				"13 14 15 16 17 18 19 \r\n" +
				"20 21 22 23 24 25 26 \r\n" +
				"27 28 29 \r\n";
		
		//The input to runCalendarMain addresses the controllability aspect of the test, since
		//input directly to main from an input stream is the only way to communicate with the
		//program.
		assertEquals(feb2000, runCalendarMain("2\n2000\n"));
	}
	
	@Test
	void testBadInput() {
		//The observability isn't as bad for this test because the expected output is an error.
		//I don't need to do anything special to see the output since Java already throws the exception.
		
		//The input to runCalendarMain addresses the controllability aspect of the test, since
		//input directly to main from an input stream is the only way to communicate with the
		//program.		
		try {
			runCalendarMain("February\n2019\n");
		} catch(NumberFormatException e) {
			return;
		}
		fail("NumberFormatException expected");
	}
	
	@Test
	void testMonthOutOfBounds() {
		//The observability isn't as bad for this test because the expected output is an error.
		//I don't need to do anything special to see the output since Java already throws the exception.
		
		//The input to runCalendarMain addresses the controllability aspect of the test, since
		//input directly to main from an input stream is the only way to communicate with the
		//program.		
		try {
			runCalendarMain("13\n2016\n");
		} catch(ArrayIndexOutOfBoundsException e) {
			return;
		}	
		fail("ArrayIndexOutOfBoundsException expected");
	}
}

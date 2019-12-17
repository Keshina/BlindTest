import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

public class test {
	
	private ByteArrayOutputStream testOutput;
	
	@Before
    public void setUpOutput() {
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }
	
	// tests correct output for leap year
	@Test	
	public void testLeepYear() {
		// Difficult to provide needed input, affecting controllability.
		// Difficult to test because of data abstraction (private methods)
		String data = "2" +"\n2020";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		Calendar.main(new String[0]);
		
		// this test observes the correct output only when a leap year is entered from the user
		assertEquals("Enter month: Enter year:    February 2020\r\n" + 
				" S  M Tu  W Th  F  S\r\n" + 
				"                   1 \r\n" + 
				" 2  3  4  5  6  7  8 \r\n" + 
				" 9 10 11 12 13 14 15 \r\n" + 
				"16 17 18 19 20 21 22 \r\n" + 
				"23 24 25 26 27 28 29 \r\n" + 
				"30 \r\n", testOutput.toString());
	}

	// tests for invalid month, month should be between 1-12 inclusive
	@Test
	public void testInvalidMonth()
	{	
		// user input -> controllability
		String data = "0" +"\n2018";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		try {
	        Calendar.main(new String[0]);
	        // addresses observability
	        fail("Should throw an exception, Month should be between 1-12 inclusive");
	    } 
	    catch (Exception e) {
	    	// addresses observablity
	        assertEquals( "Invalid month, try again", e.getMessage());
	    }        
	}
	
	// Tests for invalid year, year should be greater than 0 
	@Test 
	public void testInvalidYear()
	{
		// Controllability -> input entered in from user
		String data = "4" +"\n-4";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		try {
	        Calendar.main(new String[0]);
	        // observability -> exception for invalid year
	        fail("Should throw an exception, year should be > 0");
	    } 
	    catch (Exception e) {
	    	// observability
	        assertEquals( "Invalid year, try again", e.getMessage());
	    }       
	}
	
	// for non integer input
	@Test 
	public void testInvalidInput()
	{
		// Controllability -> input entered in from user
		String data = "q" +"\n2018";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		try {
			// observability -> exception for non integer input
	        Calendar.main(new String[0]);
	        fail("Should throw exception");
	    } 
	    catch (Exception e) {
	    	// observablity
	        assertEquals( "For input string: \"q\"", e.getMessage());
	    }        
	}
	
	// for working input, happy path test
	@Test
	public void TestCalendar()
	{
		// Controllability -> input entered in from user
		String data = "1" +"\n2018";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		// observablity, correct output from happy path test
		Calendar.main(new String[0]);
		assertEquals("Enter month: Enter year:    January 2018\r\n" + 
				" S  M Tu  W Th  F  S\r\n" + 
				"    1  2  3  4  5  6 \r\n" + 
				" 7  8  9 10 11 12 13 \r\n" + 
				"14 15 16 17 18 19 20 \r\n" + 
				"21 22 23 24 25 26 27 \r\n" + 
				"28 29 30 31 \r\n", testOutput.toString());
	}

}

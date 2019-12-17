import static org.junit.Assert.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarTests {

	private final InputStream systemIn  = System.in;
	private final PrintStream systemOut = System.out;
	private ByteArrayOutputStream mockOutput;

	@BeforeEach
	public void setUpOutput() {
		mockOutput = new ByteArrayOutputStream();
		System.setOut(new PrintStream(mockOutput));
	}

	@AfterEach
	public void restoreSystemInputOutput() {
		System.setIn(systemIn);
		System.setOut(systemOut);
	}

	/**
	 * tests is Calendar correctly handles a leap year
	 * (February should have 29 days)
	 * Observability: Used to PrintStream and ByteOutputStream to capture output. Checking to see for the correct
	 * output was difficult. Had to manipulate output string to check if the last day was 29 
	 * Controllability: Used a ByteArrayInputStream and System.setIn to feed in input
	 */
	@Test
	void testMainLeapYear() {
		String data = "2" +"\n2000";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);
		Calendar.main(null);
		String output = mockOutput.toString();
		String[] str = output.toString().trim().split("\n");
		String lastStr = str[str.length - 1];
		assertTrue(lastStr.substring(lastStr.length() - 2).equals("29"));
	}

	/**
	 * tests is Calendar correctly handles a non-leap year 
	 * (February should have 28 days)
	 * Observability: Used to PrintStream and ByteOutputStream to capture output. Checking to see for the correct
	 * output was difficult. Had to manipulate output string to check if the last day was 28 
	 * Controllability: Used a ByteArrayInputStream and System.setIn to feed in input
	 */
	@Test
	void testMainNonLeapYear() {
		String data = "2" +"\n2001";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);
		Calendar.main(null);
		String output = mockOutput.toString();
		String[] str = output.toString().trim().split("\n");
		String lastStr = str[str.length - 1];
		assertTrue(lastStr.substring(lastStr.length() - 2).equals("28"));
	}

	/**
	 * Happy path test
	 * Tests that September has the correct number of days (30)
	 * Observability: Used to PrintStream and ByteOutputStream to capture output. Checking to see for the correct
	 * output was difficult. Had to manipulate output string to check if the last day was 30 and contained September 2019 
	 * Controllability: Used a ByteArrayInputStream and System.setIn to feed in input
	 */
	@Test
	void testMainRegularMonth() {
		String data = "9" +"\n2019";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);
		Calendar.main(new String[0]);
		String output = mockOutput.toString();
		String[] str = output.toString().trim().split("\n");
		String lastStr = str[str.length - 1];
		assertTrue(lastStr.substring(lastStr.length() - 2).equals("30") && output.contains("September 2019"));
	}

	/**
	 * Tests that March starts on the correct day of the week (Friday)
	 * Observability: Used to PrintStream and ByteOutputStream to capture output. Checking to see for the correct
	 * output was difficult. Had to specifically hardcode string index to check for correct starting day 
	 * Controllability: Used a ByteArrayInputStream and System.setIn to feed in input
	 */
	@Test
	void testMainMonthMarch() {
		String data = "3" +"\n2019";
		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);
		Calendar.main(null);
		String output = mockOutput.toString();
		String[] str = output.toString().trim().split("\n");
		assertTrue(str[2].charAt(16) == '1');
	}

	/*
	 * Tests for negative input 
	 * Program should throw an exception.
	 * Observability: Have a try/catch statement to see if the test throws an exception 
	 * Controllability: Used a ByteArrayInputStream and System.setIn to feed in input
	 */
	@Test
	void testMainNegativeInput() {
		Throwable e = null;
		try {
			String data = "3" +"\n-2019";
			ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
			System.setIn(mockInput);
			Calendar.main(null);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof IllegalArgumentException);
		
	}

	/*
	 * Tests for negative input 
	 * Program should throw an exception.
	 * Observability: Have a try/catch statement to see if the test throws an exception 
	 * Controllability: Used a ByteArrayInputStream and System.setIn to feed in input
	 */
	@Test
	void testMainInvalidMonth() {
		Throwable e = null;
		try {
			String data = "13" +"\n2019";
			ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
			System.setIn(mockInput);
			Calendar.main(null);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof ArrayIndexOutOfBoundsException);
	}
}

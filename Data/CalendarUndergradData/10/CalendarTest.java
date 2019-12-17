import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.io.*;


public class CalendarTest {

	private final InputStream systemIn  = System.in;
	private final PrintStream systemOut = System.out;

	private ByteArrayOutputStream mockOutput;
	private StringWriter output = new StringWriter();
	private PrintWriter printWriter = new PrintWriter(output);
	private String prompt = "Enter month: Enter year: ";

	@BeforeEach
	public void setUpOutput()
	{
		mockOutput = new ByteArrayOutputStream();
		System.setOut(new PrintStream(mockOutput));
	}

	@AfterEach
	public void restoreSystemInputOutput()
	{
		System.setIn(systemIn);
		System.setOut(systemOut);
	}

	@Test
	public void testInput()
	{
		final String data = "9\n2019"; //\n makes sure that 9 and 2019 are considered as two separate inputs

		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);

		Calendar.main(new String[0]);

		assertTrue(mockOutput.toString().contains("September 2019"));
	}

	@Test
	public void testLeap()
	{
		
		printWriter.println(prompt + "   February 2008");
		printWriter.println(" S  M Tu  W Th  F  S");
		printWriter.println("             1  2  3 ");
		printWriter.println(" 4  5  6  7  8  9 10 ");
		printWriter.println("11 12 13 14 15 16 17 ");
		printWriter.println("18 19 20 21 22 23 24 ");
		printWriter.println("25 26 27 28 29 30 ");
		printWriter.close();
		
		final String data = "2\n2008";

		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);

		Calendar.main(new String[0]);

		assertTrue(mockOutput.toString().equals(output.toString()), "testNotLeap failed");
	}
	
	@Test
	public void testMarch()
	{
		printWriter.println(prompt + "   March 2000");
		printWriter.println(" S  M Tu  W Th  F  S");
		printWriter.println("          1  2  3  4 ");
		printWriter.println(" 5  6  7  8  9 10 11 ");
		printWriter.println("12 13 14 15 16 17 18 ");
		printWriter.println("19 20 21 22 23 24 25 ");
		printWriter.println("26 27 28 29 30 31 ");
		printWriter.close();
		
		final String data = "3\n2000";

		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);

		Calendar.main(new String[0]);

		assertTrue(mockOutput.toString().equals(output.toString()), "testMarch failed");
	}
	
	@Test
	public void testLeap_div400()
	{
		printWriter.println(prompt + "   February 1600");
		printWriter.println(" S  M Tu  W Th  F  S");
		printWriter.println("                1  2 ");
		printWriter.println(" 3  4  5  6  7  8  9 ");
		printWriter.println("10 11 12 13 14 15 16 ");
		printWriter.println("17 18 19 20 21 22 23 ");
		printWriter.println("24 25 26 27 28 29 ");
		printWriter.close();
		
		final String data = "2\n1600";

		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);

		Calendar.main(new String[0]);

		assertTrue(mockOutput.toString().equals(output.toString()), "testLeap_div400 failed");
	}
	
	@Test
	public void testWeekDay()
	{
		printWriter.println(prompt + "   September 1644");
		printWriter.println(" S  M Tu  W Th  F  S");
		printWriter.println(" 1  2  3  4  5  6  7 ");
		printWriter.println(" 8  9 10 11 12 13 14 ");
		printWriter.println("15 16 17 18 19 20 21 ");
		printWriter.println("22 23 24 25 26 27 28 ");
		printWriter.println("29 30 ");
		printWriter.close();
		
		final String data = "9\n1644";

		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);

		Calendar.main(new String[0]);

		assertTrue(mockOutput.toString().equals(output.toString()), "testLeap_div400 failed");
	}
	
	@Test
	public void testNotLeap()
	{
		printWriter.println(prompt + "   February 2015");
		printWriter.println(" S  M Tu  W Th  F  S");
		printWriter.println(" 1  2  3  4  5  6  7 ");
		printWriter.println(" 8  9 10 11 12 13 14 ");
		printWriter.println("15 16 17 18 19 20 21 ");
		printWriter.println("22 23 24 25 26 27 28 ");
		printWriter.close();
		
		final String data = "2\n2015";

		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);

		Calendar.main(new String[0]);

		assertTrue(mockOutput.toString().equals(output.toString()), "testNotLeap failed");
	}
	
	@Test
	public void test()
	{
		printWriter.println(prompt + "   June 2019");
		printWriter.println(" S  M Tu  W Th  F  S");
		printWriter.println(" 1  2  3  4  5  6  7 ");
		printWriter.println(" 8  9 10 11 12 13 14 ");
		printWriter.println("15 16 17 18 19 20 21 ");
		printWriter.println("22 23 24 25 26 27 28 ");
		printWriter.close();
		
		final String data = "2\n2015";

		ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
		System.setIn(mockInput);

		Calendar.main(new String[0]);

		assertTrue(mockOutput.toString().equals(output.toString()), "testNotLeap failed");
	}
	
	@Test
	public void testExceptionMessage()
	{
		try {
			final String data = "12\n2015";
			ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
			System.setIn(mockInput);
		}
		catch (IndexOutOfBoundsException Exception)
		{
			return;
		}
		
		fail("IndexOutOfBoundsException expected");
	}
	
}

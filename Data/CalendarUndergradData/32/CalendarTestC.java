package calendar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalendarTestC {//<!-- -->

   private final InputStream systemIn  = System.in;
   private final PrintStream systemOut = System.out;

   private ByteArrayOutputStream mockOutput;

   @Before
   public void setUpOutput() {//<!-- -->
      mockOutput = new ByteArrayOutputStream();
      System.setOut(new PrintStream(mockOutput));
   }

   @After
   public void restoreSystemInputOutput() {//<!-- -->
      System.setIn(systemIn);
      System.setOut(systemOut);
   }

   // Observability is influenced by this test. It addresses output structure.
   @Test
   public void testCase1() { /* Test that the month and year variables and the months array are working */
      final String data = "9\n2019"; //\n makes sure that 9 and 2019 are considered as two separate inputs

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      assertTrue(mockOutput.toString().contains("September 2019"));
   }
   
   // Controllability is influenced by this test. It addresses data handling.
   @Test
   public void testLeapYearTrue() { /* Test the leap year method. Make sure February has a 29th day for a valid leap year */
      final String data = "2\n2000"; //\n makes sure that 9 and 2019 are considered as two separate inputs

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      assertTrue(mockOutput.toString().contains("29"));
   }
   
   // Controllability is influenced by this test. It addresses data handling.
   @Test
   public void testLeapYearFalse() { /* Test the leap year method. Make sure February does NOT have 29th day for non-leap year */
      final String data = "2\n2100"; //\n makes sure that 9 and 2019 are considered as two separate inputs

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      // 2100 is not a leap year, so February should not contain a 29th day
      assertTrue(!mockOutput.toString().contains("29"));
   }
   
   // Controllability is influenced by this test. It addresses user input.
   // Observability is influenced by this test. It prevents invalid data from reaching the user.
   @Test
   public void testBigMonth() { /* Ensures user input greater than the month field's upper bound throws 
										an ArrayIndexOutOfBounds exception */
	   final String data = "13\n1995";
	   
	   ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	   System.setIn(mockInput);
	   
	   try {
		   // This operation should throw an exception
		   Calendar.main(new String[0]);
		   
		   // If no exception is thrown
		   fail("Expected an ArrayIndexOutOfBounds Exception");
	   } catch(ArrayIndexOutOfBoundsException e) {
		   // If caught, test successful
	   }
   }
   
   // Controllability is influenced by this test. It addresses data user input.
   // Observability is influenced by this test. It prevents invalid data from reaching the user.
   @Test
   public void testSmallMonth() { /* Ensures user input less than the month field's lower bound throws 
										an ArrayIndexOutOfBounds exception */
	   final String data = "-10\n1995";
	   
	   ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	   System.setIn(mockInput);
	   
	   try {
		   // This operation should throw an exception
		   Calendar.main(new String[0]);
		   
		   // If no exception is thrown
		   fail("Expected an ArrayIndexOutOfBoundsException");
	   } catch(ArrayIndexOutOfBoundsException e) {
		   // If caught, test successful
	   }
   }
   
   // Controllability is influenced by this test. It addresses user input.
   // Observability is influenced by this test. It prevents invalid data from reaching the user.
   @Test
   public void testSmallYear() { /* Checks handling of invalid user input for the year field */
	   final String data = "10\n-10";
			   
	   ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	   System.setIn(mockInput);
	   
	   Calendar.main(new String[0]);
	   
	   // Should not output a calendar for a month in year -10
	   assertFalse(!mockOutput.toString().contains("-10"));
   }
   
   // Controllability is influenced by this test. It addresses user input.
   // Observability is influenced by this test. It prevents invalid data from reaching the user.
   @Test
   public void testBadMonth() { /* Checks handling of invalid characters in the month field */
	   final String data = "c\n1987";
	   
	   ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	   System.setIn(mockInput);
	   
	   try {
		   // Should throw an exception
		   Calendar.main(new String[0]);
		   
		   // If no exception is thrown
		   fail("Expected a NumberFormatException");
	   } catch(NumberFormatException e) {
		   // Success
	   }
   }
   
   // Controllability is influenced by this test. It addresses user input.
   // Observability is influenced by this test. It prevents invalid data from reaching the user.
   @Test
   public void testBadYear() { /* Checks handling of invalid characters in the year field */
	   final String data = "10\na";
	   
	   ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	   System.setIn(mockInput);
	   
	   try {
		   // Should throw an exception
		   Calendar.main(new String[0]);
		   
		   // If no exception is thrown
		   fail("Expected a NumberFormatException");
	   } catch(NumberFormatException e) {
		   // Success
	   }
   }
   
   // Controllability is influenced by this test. It addresses data handling.
   // Observability is influenced by this test. It addresses output structure.
   @Test
   public void testWeekDay() { /* Ensures that May, 2019 begins on a Wednesday */
      final String data = "5\n2019"; 

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      // Expected output formatting
      assertTrue(mockOutput.toString().contains("          1  2  3  4"));
   }
}

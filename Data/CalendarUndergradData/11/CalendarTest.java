import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalendarTest {

   private final InputStream systemIn  = System.in;
   private final PrintStream systemOut = System.out;

   private ByteArrayOutputStream mockOutput;

   @Before
   public void setUpOutput() {
      mockOutput = new ByteArrayOutputStream();
      System.setOut(new PrintStream(mockOutput));
   }

   @After
   public void restoreSystemInputOutput() {
      System.setIn(systemIn);
      System.setOut(systemOut);
   }

   /**
    * Tests a happy path.
    */
   @Test
   public void testCase1() {
      final String data = "3\n1998";

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      assertTrue(mockOutput.toString().contains("March 1998"));
   }

   /**
    * Tests leap year special case.
    * Addresses obersvability.
    */
    @Test
    public void testForLeapYear4() {
       final String data = "2\n2012";
 
       ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
       System.setIn(mockInput);
 
       Calendar.main(new String[0]);
 
       assertTrue(mockOutput.toString().contains("29"));
    }
   
   /**
    * Tests non-leap year special case.
    * Addresses obersvability.
    */
   @Test
   public void testForLeapYear100() {
      final String data = "2\n2100";

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      assertTrue(!(mockOutput.toString().contains("29")));
   }

   /**
    * Tests leap year special case.
    * Addresses observality.
    */
   @Test
   public void testForLeapYear400() {
      final String data = "2\n2000";

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      assertTrue(mockOutput.toString().contains("29"));
   }

   /**
    * Tests invalid user inputs.
    * Tests controlability.
    */
   @Test
   (expected = NumberFormatException.class) public void testForLetterInput(){
      final String data = "he\nll0";

         ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
         System.setIn(mockInput);

         Calendar.main(new String[0]);

         assertTrue(false);
   }
   
   /**
    * Tests invalid user inputs that start with valid numbers.
    * Tests controlability.
    */
    @Test
    (expected = NumberFormatException.class) public void testForPartialNumberInput(){
       final String data = "3HI\n2019HI";
 
          ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
          System.setIn(mockInput);
 
          Calendar.main(new String[0]);
 
          assertTrue(false);
    }

    /**
    * Tests invalid month/year special case.
    * Addresses controlability.
    */
   @Test
   public void testForExceptionalNumbers() {
      final String data = "13\n-1";

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      assertTrue(!(mockOutput.toString().contains("-1")));
   }
}

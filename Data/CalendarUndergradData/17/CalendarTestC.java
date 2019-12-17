import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CalendarTestC {

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

   //Happy path test
   @Test
   public void testCase1() {
      final String data = "9\n2019"; //\n makes sure that 9 and 2019 are considered as two separate inputs
                                    //Controllability

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      System.out.println(mockOutput.toString()); //Observability
      assertTrue(mockOutput.toString().contains("September 2019"));
   }

   //Tests the isLeapYear()
   @Test
   public void testCase2()
   {
      final String data = "2\n2019"; //Controllability

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      System.out.println(mockOutput.toString()); //Observability
      assertTrue(mockOutput.toString().contains("Feburary 2019"));
   }

   //Test whether the class can handle string input
   @Test
   public void testCase3()
   {
      final String data = "May\n2019"; //Controllability

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      System.out.println(mockOutput.toString()); //Observability
      assertTrue(mockOutput.toString().contains("May 2019"));
   }
}

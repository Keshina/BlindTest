/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalendarTest {

   private final InputStream systemIn  = System.in;
   private final PrintStream systemOut = System.out;

   private ByteArrayOutputStream mockOutput;

   @Before
   public void setUpOutput() 
   {
      mockOutput = new ByteArrayOutputStream();
      System.setOut(new PrintStream(mockOutput));
   }

   @After
   public void restoreSystemInputOutput() 
   {
      System.setIn(systemIn);
      System.setOut(systemOut);
   }

   @Test
   public void testCase1() 
   {
      //observable input
      final String data = "9\n2019"; //\n makes sure that 9 and 2019 are considered as two separate inputs

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);
      
      //controllable output
      assertTrue(mockOutput.toString().contains("September 2019"));
   }
   
   //test to see if it accepts non-numerical input
   //controllable output
   @Test(expected = NumberFormatException.class)
   public void testReadInput()
   {
       //observable input
       String data = "df\n2019";
       
       ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
       System.setIn(mockInput);
       
       Calendar.main(new String[0]);
       
   }
   
   //test to see if leap year properly takes off day 31
   @Test
   public void testLeapYear()
   {
       //observable input
       String data = "2\n2020";
       ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
       System.setIn(mockInput);
       Calendar.main(new String[0]);
       
       //controllable output
       assertFalse(mockOutput.toString().contains("31"));
   }
}

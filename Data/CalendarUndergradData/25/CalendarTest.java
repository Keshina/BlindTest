 
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import org.apache.commons.lang3.StringUtils;


public class CalendarTest {

   /** Fixture initialization (common initialization
    *  for all tests). **/
    private String[] arguments;
    ByteArrayOutputStream output;
    PrintStream old;
    private String[] months = {
            "",                               // leave empty so that months[1] = "January"
            "January", "February", "March",
            "April",   "May",      "June",
            "July",    "August",   "September",
            "October", "November", "December"
      };

   @Before public void setUp() {
     
      arguments = new String[]{};
      output = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(output);
      // Save the old system out so it can be restored later
      old = System.out;
      // Set out to the new stream
      System.setOut(ps);
   }

   @Test 
   public void happyPath() {
      
      // Set the test case arguments to be passed to main in Calendar
      arguments = new String[]{"1", "2019"};
      // Output printed in main goes into stream and can be accessed by calling "output.toString()"
      Calendar.main(arguments);
      //CONTROLLABILITY - controlling inputs via arguments array
      
      // Restore the streams to normal
      System.out.flush();
      System.setOut(old);
      
      //Modify these 4 lines based on the expected output of the test case
    //----------------------------
      String month = months[1];
      int year = 2019;
      int wd = 2; // get this value by printing wd when running calendar class
      int daysMonth = 31;
    //----------------------------
      String expectedOutput = "   " + month + " " + year + "\r\n S  M Tu  W Th  F  S" + "\r\n" + "      ";
      
      for(int i = 1; i <= daysMonth; i++) {
         String piece = String.format("%2d ", i);
         expectedOutput += piece;
         if (((i + wd) % 7 == 0) || (i == daysMonth) )
            expectedOutput += "\r\n";
      }

      assertEquals(expectedOutput, output.toString());
      //OBSERVABILITY - string comparison of the expected and actual months
      
   }
   
   //THIS IS THE ONLY TEST THAT SHOULD FAIL SINCE PROGRAM HAS A FAULT
      //WHEN IT COMES TO LEAP YEARS
   @Test 
   public void leapTest() {
      
      // Set the test case arguments to be passed to main in Calendar
      arguments = new String[]{"2", "2016"};
      
      // Output printed in main goes into stream and can be accessed by calling "output.toString()"
      Calendar.main(arguments);
      //CONTROLLABILITY 
      
      // Restore the streams to normal
      System.out.flush();
      System.setOut(old);
      
      //Modify these 4 lines based on the expected output of the test case
    //----------------------------
      String month = months[2];
      int year = 2016;
      int wd = 2; // get this value by printing wd when running calendar class
      int daysMonth = 28;
    //----------------------------
      String expectedOutput = "   " + month + " " + year + "\r\n S  M Tu  W Th  F  S" + "\r\n" + "      ";
      
      for(int i = 1; i <= daysMonth; i++) {
         String piece = String.format("%2d ", i);
         expectedOutput += piece;
         if (((i + wd) % 7 == 0) || (i == daysMonth) )
            expectedOutput += "\r\n";
      }
      
      assertEquals(expectedOutput, output.toString());
      //OBSERVABILITY
   }
   
   //EXCEPTION TESTS
   
   //test will pass if a NumberFormatException is thrown
   @Test(expected = NumberFormatException.class)
   public void wordTest() {
      
      // Set the test case arguments to be passed to main in Calendar
      arguments = new String[]{"February", "2016"};
      
      // Output printed in main goes into stream and can be accessed by calling "output.toString()"
      Calendar.main(arguments);
      //CONTROLLABILITY - send inputs
      //OBSERVABILITY - a NumberFormatException will be thrown if it works
            
      // Restore the streams to normal
      System.out.flush();
      System.setOut(old);
      
      //Modify these 4 lines based on the expected output of the test case
    //----------------------------
      String month = months[2];
      int year = 2016;
      int wd = 2; // get this value by printing wd when running calendar class
      int daysMonth = 28;
    //----------------------------
      String expectedOutput = "   " + month + " " + year + "\r\n S  M Tu  W Th  F  S" + "\r\n" + "      ";
      
      for(int i = 1; i <= daysMonth; i++) {
         String piece = String.format("%2d ", i);
         expectedOutput += piece;
         if (((i + wd) % 7 == 0) || (i == daysMonth) )
            expectedOutput += "\r\n";
      } 
   }   
   
   //test passes if ArrayIndexOutOfBoundsException is thrown
   @Test(expected = ArrayIndexOutOfBoundsException.class)
   public void outOfBoundTest() {
      
      // Set the test case arguments to be passed to main in Calendar
      arguments = new String[]{"-2", "2016"};
      
      // Output printed in main goes into stream and can be accessed by calling "output.toString()"
      Calendar.main(arguments);
      //CONTROLLABILITY - send inputs
      //OBSERVABILITY - an ArrayIndexOutOfBoundsException is thrown if it works
      
      // Restore the streams to normal
      System.out.flush();
      System.setOut(old);
      
      //Modify these 4 lines based on the expected output of the test case
    //----------------------------
      String month = months[2];
      int year = 2016;
      int wd = 2; // get this value by printing wd when running calendar class
      int daysMonth = 28;
    //----------------------------
      String expectedOutput = "   " + month + " " + year + "\r\n S  M Tu  W Th  F  S" + "\r\n" + "      ";
      
      for(int i = 1; i <= daysMonth; i++) {
         String piece = String.format("%2d ", i);
         expectedOutput += piece;
         if (((i + wd) % 7 == 0) || (i == daysMonth) )
            expectedOutput += "\r\n";
      }
   }
}

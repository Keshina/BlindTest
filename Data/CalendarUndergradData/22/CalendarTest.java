import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.*;

public class CalendarTest {
   
   Calendar c;
   private OutputStream printOutput = new ByteArrayOutputStream();
   
   private final InputStream systemIn  = System.in;
   private final PrintStream systemOut = System.out;
   
   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUpVariable() {
      c = new Calendar(); //controllability
   }
   
   @Before public void setUpOutputPrintStream(){ 
      System.setOut(new PrintStream(printOutput)); //controllability
   }
   
   @After public void resetOutputPrintStream(){
      System.setOut(systemOut); //controllability
      System.setIn(systemIn); //controllability
   }
   
   @Test (expected = Exception.class)
   public void nonIntegerInputTest() {
      //controllability
      String input = "June\n" + 
                     "Nine";
      System.setIn(new ByteArrayInputStream(input.getBytes()));
      
      //observability
      c.main(new String[] {""});    
   }
   @Test
   public void februaryNonLeapYearTest() {
      //controllability
      String input = "2\n" + 
                     "2019";
      System.setIn(new ByteArrayInputStream(input.getBytes()));     
      c.main(new String[] {""});
      
      //observability
      String s = printOutput.toString();
      Assert.assertEquals("Found February has " +  s.substring(s.lastIndexOf(" ")-2) + "days in year " + input.substring(input.lastIndexOf("\n")+1),
                           "28",s.substring(s.lastIndexOf(" ")-2, s.lastIndexOf(" "))    ); 
   }
   
   @Test
   public void februaryLeapYearTest() {
      //controllability
      String input = "2\n" + 
                     "2020";
      System.setIn(new ByteArrayInputStream(input.getBytes()));     
      c.main(new String[] {""});
      
      //observability
      String s = printOutput.toString();
      Assert.assertEquals("Found February has " +  s.substring(s.lastIndexOf(" ")-2) + "days in year " + input.substring(input.lastIndexOf("\n")+1),
                           "29",s.substring(s.lastIndexOf(" ")-2, s.lastIndexOf(" "))    ); 
   }
   
   @Test
   public void specialNonLeapYearTest() {
      //controllability
      String input = "2\n" + 
                     "1900";
      System.setIn(new ByteArrayInputStream(input.getBytes()));     
      c.main(new String[] {""});
      
      //observability
      String s = printOutput.toString();
      Assert.assertEquals("Found February has " +  s.substring(s.lastIndexOf(" ")-2) + "days in year " + input.substring(input.lastIndexOf("\n")+1) ,
                           "28",s.substring(s.lastIndexOf(" ")-2, s.lastIndexOf(" "))    ); 
   }
   
   @Test
   public void calendarMonthYearHeaderTest() {
      //controllability
      String input = "9\n" + 
                     "2019";
      System.setIn(new ByteArrayInputStream(input.getBytes()));     
      c.main(new String[] {""});
      
      //observability
      String s = printOutput.toString();
      Assert.assertTrue("Calendar does not have the month name printed",s.contains("September") );
      Assert.assertTrue("Calendar does not have the year value printed",s.contains("2019") );
   }
   
   @Test
   public void calendarWeekdayHeaderTest() {
      //controllability
      String input = "9\n" + 
                     "2019";
      System.setIn(new ByteArrayInputStream(input.getBytes()));     
      c.main(new String[] {""});
      
      //observability.
      String s = printOutput.toString();
      Assert.assertTrue("Calendar does not have weekday header", s.contains(" S  M Tu  W Th  F  S") );
   }
   
   @Test
   public void weekdayTest() {
      //controllability
      String input = "9\n" + 
                     "2019";
      System.setIn(new ByteArrayInputStream(input.getBytes()));     
      c.main(new String[] {""});
      
      //observability.
      String s = printOutput.toString().split("\n")[2];
      Assert.assertEquals("Starting weekday is incorrect", s.indexOf("1"), 1 );
   }
   
}

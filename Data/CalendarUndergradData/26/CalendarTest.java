import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class CalendarTest {

   private Calendar Cal;
   private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
   private final PrintStream originalOut = System.out;
   private final InputStream originalIn = System.in;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
      Cal = new Calendar();
      System.setOut(new PrintStream(outContent));
   }

   @After public void tearDown() 
   {     
      System.setOut(originalOut);
      System.setIn(originalIn);
   }

   /** Leap Year test. **/
   @Test public void testLeapYear() {//year div by 4 and not 100,400 conrollability, month = 2 controllability
      String data = "2" +
         "\n2016";
      System.setIn(new ByteArrayInputStream(data.getBytes()));
      Cal.main(new String[] {});
      assertEquals(outContent.toString().trim(), "Enter month: Enter year:    February 2016\n" + " S  M Tu  W Th  F  S\n" + "    1  2  3  4  5  6 \n" + " 7  8  9 10 11 12 13 \n" + "14 15 16 17 18 19 20 \n" + "21 22 23 24 25 26 27 \n" + "28 29\n");
   }
   
   @Test public void testLeapYear2() {//year not div by 4 conrollability, month = 2 controllability
      String data = "2" +
         "\n2017";
      System.setIn(new ByteArrayInputStream(data.getBytes()));
      Cal.main(new String[] {});
      assertEquals(outContent.toString().trim(), "Enter month: Enter year:    February 2017\n" + " S  M Tu  W Th  F  S\n" + "          1  2  3  4 \n" + " 5  6  7 8 9 10 11 \n" + "12 13 14 15 16 17 18 \n" + "19 20 21 22 23 24 25 \n" + "26 27 28\n");
   }
   
  
   
   @Test public void testLeapYear3() {//year div by 400 conrollability, month = 2 controllability
      String data = "2" +
         "\n2000";
      System.setIn(new ByteArrayInputStream(data.getBytes()));
      Cal.main(new String[] {});
      assertEquals(outContent.toString().trim(), "Enter month: Enter year:    February 2000\n" + " S  M Tu  W Th  F  S\n" + "       1  2  3  4  5 \n" + " 6  7  8  9  10 11 12 \n" + "13 14 15 16 17 18 19 \n" + "20 21 22 23 24 25 26 \n" + "27 28\n");
   }
}

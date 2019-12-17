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

   @Test
   public void sepTest() {
      final String data = "9\n2019"; // Test September 9th 2019

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);
      
      Calendar.main(new String[0]);

      String week1 = " 1  2  3  4  5  6  7 ";
      String week2 = "\n 8  9 10 11 12 13 14 ";
      String week3 = "\n15 16 17 18 19 20 21 ";
      String week4 = "\n22 23 24 25 26 27 28 ";
      String week5 = "\n29 30 ";
      
      assertTrue(mockOutput.toString().contains("September 2019"));
      assertTrue(mockOutput.toString().contains(week1));
      assertTrue(mockOutput.toString().contains(week2));
      assertTrue(mockOutput.toString().contains(week3));
      assertTrue(mockOutput.toString().contains(week4));
      assertTrue(mockOutput.toString().contains(week5));
   }
   
   @Test
   public void febNonLeapYear() {
      final String data = "2\n2019"; 

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);
      
      Calendar.main(new String[0]);
      
      String week1 = "                1  2 ";
      String week2 = "\n 3  4  5  6  7  8  9 ";
      String week3 = "\n10 11 12 13 14 15 16 ";
      String week4 = "\n17 18 19 20 21 22 23 ";
      String week5 = "\n24 25 26 27 28 ";
      
      assertTrue(mockOutput.toString().contains("February 2019"));
      assertTrue(mockOutput.toString().contains(week1));
      assertTrue(mockOutput.toString().contains(week2));
      assertTrue(mockOutput.toString().contains(week3));
      assertTrue(mockOutput.toString().contains(week4));
      assertTrue(mockOutput.toString().contains(week5));

   }
   
   /* Kept in for reference, not valid test
   @Test
   public void febNonLeapYear1() {
      final String data = "2\n2019"; 

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);
      
      Calendar.main(new String[0]);
      
      String week1 = "                1  2 ";
      String week2 = "\n 3  4  5  6  7  8  9 ";
      String week3 = "\n10 11 12 13 14 15 16 ";
      String week4 = "\n10 11 12 13 14 15 16 ";
      String week5 = "\n17 18 19 20 21 22 23 ";
     // String week5 = "\n24 25 26 27 28       ";
      
      String f = "Enter month: Enter year:    February 2019\n" + " S  M Tu  W Th  F  S\n" +
    		  					"                1  2 " + 
    		  					"\n 3  4  5  6  7  8  9 " +
    		  					"\n10 11 12 13 14 15 16 " +
    		  					"\n17 18 19 20 21 22 23 " + 
    		  					"\n24 25 26 27 28 29 30 \n";
      
      //assertEquals(f, mockOutput.toString());
      //assertTrue(mockOutput.toString().contains(f);

   }*/
   
   @Test
   public void febLeapYear() {
      final String data = "2\n2020"; 

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);
      
      Calendar.main(new String[0]);
      
      String week1 = "                   1 ";
      String week2 = "\n 2  3  4  5  6  7  8 ";
      String week3 = "\n 9 10 11 12 13 14 15 ";
      String week4 = "\n16 17 18 19 20 21 22 ";
      String week5 = "\n23 24 25 26 27 28 29 ";
      
      assertTrue(mockOutput.toString().contains("February 2020"));
      assertTrue(mockOutput.toString().contains(week1));
      assertTrue(mockOutput.toString().contains(week2));
      assertTrue(mockOutput.toString().contains(week3));
      assertTrue(mockOutput.toString().contains(week4));
      assertTrue(mockOutput.toString().contains(week5));
      assertTrue(mockOutput.toString().contains("29")); // leap year should be have 29 days
      
      // Test fails because of flaw in Calendar.java with calculating days in a leap year
      assertFalse(mockOutput.toString().contains("30")); // Feb 2020 does not have a 30th day
   }
   
   @Test
   public void invalidInputTest1() {
	   final String data = "May\n2019"; // if user enters name of month rather than number
	   
	   try {
	      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	      System.setIn(mockInput);
	      
	      Calendar.main(new String[0]);
	   } 
	   catch (NumberFormatException e) {
		   return;
	   }
	    fail("NumberFormatException was expected \n");
   }
   
   @Test
   public void invalidInputTest2() {
	   final String data = "July\n2019"; // if user enters name of month rather than number
	   
	   try {
	      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	      System.setIn(mockInput);
	      
	      Calendar.main(new String[0]);
	   } 
	   catch (NumberFormatException e) {
		   return;
	   }
	    fail("NumberFormatException was expected \n");
   }
   
   @Test
   public void invalidInputTest3() {
	   final String data = "3\nTwo-Thousand"; // if user enters name of year rather than number
	   
	   try {
	      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	      System.setIn(mockInput);
	      
	      Calendar.main(new String[0]);
	   } 
	   catch (NumberFormatException e) {
		   return;
	   }
	    fail("NumberFormatException was expected \n");
   }
   
   @Test
   public void NegativeInputTest() {
	   final String data = "-9\n2019"; // if user enters a negative month
	   
	   try {
	      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	      System.setIn(mockInput);
	      
	      Calendar.main(new String[0]);
	   } 
	   catch (ArrayIndexOutOfBoundsException e) {
		   return;
	   }
	    fail("ArrayIndexOutOfBoundsException was expected \n");
   }
   
   @Test
   public void OutOfBoundsInputTest() {
	   final String data = "13\n2019"; // if user enters a month not 1 <= n <= 12
	   
	   try {
	      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
	      System.setIn(mockInput);
	      
	      Calendar.main(new String[0]);
	   } 
	   catch (ArrayIndexOutOfBoundsException e) {
		   return;
	   }
	    fail("ArrayIndexOutOfBoundsException was expected \n");
   }
}

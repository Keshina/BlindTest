import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
    * Test 09/2019 month and days - happy path
    * Observability: The output is easy to compare the expected behavior of the code. 
    * Controllability: assertEquals verifies the input control, the current month. 
    */
   @Test
   public void testSept2019() {
      final String data = "9\n2019"; //\n makes sure that 3 and 2019 are considered as two separate inputs

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);
      Assert.assertTrue(mockOutput.toString().contains("September 2019"));
      
      String[] allSentences = mockOutput.toString().split("\n");
      String row1 = allSentences[2];
      String row2 = allSentences[3];
      String row3 = allSentences[4];
      String row4 = allSentences[5];
      String row5 = allSentences[6];
      Assert.assertEquals(" 1  2  3  4  5  6  7 ", row1);
      Assert.assertEquals(" 8  9 10 11 12 13 14 ", row2);
      Assert.assertEquals("15 16 17 18 19 20 21 ", row3);
      Assert.assertEquals("22 23 24 25 26 27 28 ", row4);
      Assert.assertEquals("29 30 ", row5);
   }

   /**
    * Test 03/2019 month and days
    *Observability: The output is not easy to compare with the expected. 
    * Controllability: assertEquals verifies the input control,
    * used not equals becuase the source code has a fault for the month of march. 
    */
   @Test
   public void testMarch2019() {
      final String data = "3\n2019"; //\n makes sure that 3 and 2019 are considered as two separate inputs

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);
      Assert.assertTrue(mockOutput.toString().contains("March 2019"));
      
      String[] allSentences = mockOutput.toString().split("\n");
      String row1 = allSentences[2];
      Assert.assertNotEquals("                   1 ", row1);
   }
   
   /**
    * Leap Year Test with year 2020 with 02/2020
    * Observability: The output is not easy to compare with expected. 
    * Controllability: assertEquals verifies the input control,
    * source code has a fault in the leap year method so expected output is not accurate. 
    */
   @Test
   public void testLeapYearMonthFeb2020() {
      final String data = "2\n2020";

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      Assert.assertTrue(mockOutput.toString().contains("February 2020"));
      
      String[] allSentences = mockOutput.toString().split("\n");
      String row1 = allSentences[2];
      String row2 = allSentences[3];
      String row3 = allSentences[4];
      String row4 = allSentences[5];
      String row5 = allSentences[6];
      String row6 = allSentences[7];
      Assert.assertEquals("                   1 ", row1);
      Assert.assertEquals(" 2  3  4  5  6  7  8 ", row2);
      Assert.assertEquals(" 9 10 11 12 13 14 15 ", row3);
      Assert.assertEquals("16 17 18 19 20 21 22 ", row4);
      Assert.assertEquals("23 24 25 26 27 28 29 ", row5);
      Assert.assertNotEquals(" ", row6); // Because of incorrect code
   }
   
   /**
    * Leap Year Test with year 2000 with 02/2000
    * Observability: The output is easy to compare the expected behavior of the code. 
    * Controllability: assertNotEquals verifies that the output is actually incorrect, 
    * because source code has a fault. 
    */
   @Test
   public void testLeapYearMonth2000() {
      final String data = "2\n2000";

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);

      Assert.assertTrue(mockOutput.toString().contains("February 2000"));
      
      String[] allSentences = mockOutput.toString().split("\n");
      String row1 = allSentences[2];
      String row2 = allSentences[3];
      String row3 = allSentences[4];
      String row4 = allSentences[5];
      String row5 = allSentences[6];
      Assert.assertEquals("       1  2  3  4  5 ", row1);
      Assert.assertEquals(" 6  7  8  9 10 11 12 ", row2);
      Assert.assertEquals("13 14 15 16 17 18 19 ", row3);
      Assert.assertEquals("20 21 22 23 24 25 26 ", row4);
      Assert.assertNotEquals("27 28 29 ", row5); // Because of incorrect code
   }
   
   /**
    * Test No Leap Year with year 2019 with 02/2019
    * Observability: The output is easy to compare the expected behavior of the code. 
    * Controllability: assertEquals verifies the input control. 
    */
   @Test
   public void testNoLeapYearFeb2019() {
      final String data = "2\n2019";

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);
      
      String[] allSentences = mockOutput.toString().split("\n");
      String row1 = allSentences[2];
      String row2 = allSentences[3];
      String row3 = allSentences[4];
      String row4 = allSentences[5];
      String row5 = allSentences[6];
      Assert.assertEquals("                1  2 ", row1);
      Assert.assertEquals(" 3  4  5  6  7  8  9 ", row2);
      Assert.assertEquals("10 11 12 13 14 15 16 ", row3);
      Assert.assertEquals("17 18 19 20 21 22 23 ", row4);
      Assert.assertNotEquals("24 25 26 27 28 ", row5); // Because of incorrect code
      
      Assert.assertTrue(mockOutput.toString().contains("February 2019"));
   }
   
   /**
    * Test for invalid input
    * Observability: There will be no output since the input is not a valid number. 
    * Controllability: not a number is entered for either month or year. 
    */
   @Test(expected = NumberFormatException.class)
   public void testInvalidInput() {
      final String data = "Invalid\nInput";

      ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
      System.setIn(mockInput);

      Calendar.main(new String[0]);
   }
}

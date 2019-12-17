
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalendarTest
{
  
  private final InputStream systemIn  = System.in;
  private final PrintStream systemOut = System.out;
  
  private ByteArrayOutputStream outContent;
  
  @Before
  public void setUpOutput() {
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }
  
  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }
  
  
  //March 2020 starts on a sunday, output makes March start on a Friday
  /*Part of this test that addresses observability is is when we look at the the input of 03 2020 this is because
   * we are simply looking at the calendar month and this is easily able to be verified for correctness when looking
   * at the calendar. The behavior based off this test gives a good way to see the behavior of the program and it is fairly
   * easy to see what we expect as the output with the given input in this test.
   * Part of this test that addresses controllability because in this test we want to test if the month starts on the right day,
   * so we gave the Calendar.java program a test to see if the month starts on the right day to make sure we have good
   * control of the program and it accomplishes the goal it is designed for (an accurate calendar).
   */
  @Test
  public void testMarch2020(){
    String data = "3" +
      "\n2020";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    String march2020 = "\n 1  2  3  4  5  6  7 " ;
    String args[] = null;
    Calendar.main(args);
    String calendar = outContent.toString();
    assertTrue(calendar.toString().contains(march2020));
  }
  
  
  //April 2019 starts on a sunday, output makes April start on a monday
   /*Part of this test that addresses observability is is when we look at the the input of 04 2019 this is because
   * we are simply looking at the calendar month and this is easily able to be verified for correctness when looking
   * at the calendar. The behavior based off this test gives a good way to see the behavior of the program and it is fairly
   * easy to see what we expect as the output with the given input in this test.
   * Part of this test that addresses controllability because in this test we want to test if the month starts on the right day,
   * so we gave the Calendar.java program a test to see if the month starts on the right day to make sure we have good
   * control of the program and it accomplishes the goal it is designed for (an accurate calendar).
   */
  @Test
  public void testApril2019(){
    String data = "4" +
      "\n2019";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    String april2019 = "\n    1  2  3  4  5  6 " ;
    String args[] = null;
    Calendar.main(args);
    String calendar = outContent.toString();
    assertTrue(calendar.toString().contains(april2019));
  }
  
  
  //Feb 2020 should contain 29 days because it is a leap year
   /*Part of this test that addresses observability is is when we look at the the input of 02 2020 this is because
   * we are simply looking at the calendar month and year inputted and veriifying the leap year.
   * This is easily able to be verified for correctness when looking
   * at the calendar. The behavior based off this test gives a good way to see the behavior of the program and it is fairly
   * easy to see what we expect as the output with the given input in this test.
   * Part of this test that addresses controllability because in this test we want to test if the month and year entered 
   * correlates correctly to a leap year (29 days in the month).
   * So we gave the Calendar.java program a test to see if the month and year correlate to a leap year and therefore have 29 days.
   * Here we control of the program and it accomplishes the goal it is designed for (an accurate Leap Year calendar).
   */
  @Test
  public void checkLeapYear(){
    String data = "2" +
      "\n2020";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    String args[] = null;
    Calendar.main(args);
    String calendar = outContent.toString();
    assertTrue(calendar.toString().contains("29"));
  }
  
  //Feb 2020(Leap year) should contain only 29 days and not more
   /*Part of this test that addresses observability is is when we look at the the input of 02 2020 this is because
   * we are simply looking at the calendar month and year inputted and veriifying the leap year.
   * This is easily able to be verified for correctness when looking
   * at the calendar. The behavior based off this test gives a good way to see the behavior of the program and it is fairly
   * easy to see what we expect as the output with the given input in this test.
   * Part of this test that addresses controllability because in this test we want to test if the month and year entered 
   * correlates correctly to a leap year (29 days in the month).
   * So we gave the Calendar.java program a test to see if the month and year correlate to a leap year and therefore have 29 days.
   * Here we control of the program and it accomplishes the goal it is designed for (an accurate Leap Year calendar). In this test
   * we have also incorporated another type of control by making sure that it is exactly 29 days and not a day over (30).
   */
  @Test
  public void checkLeapYear2(){
    String data = "2" +
      "\n2020";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    String args[] = null;
    Calendar.main(args);
    String calendar = outContent.toString();
    assertTrue(calendar.toString().contains("29") && !(calendar.toString().contains("30")));
  }
  
  
  //Feb 2019(not a leap year) should not contain 29 days
   /*Part of this test that addresses observability is is when we look at the the input of 02 2019 this is because
   * we are simply looking at the calendar month and year inputted and veriifying the that its a non-leap year.
   * This is easily able to be verified for correctness when looking
   * at the calendar. The behavior based off this test gives a good way to see the behavior of the program and it is fairly
   * easy to see what we expect as the output with the given input in this test.
   * Part of this test that addresses controllability because in this test we want to test if the month and year entered 
   * correlates correctly to a non-leap year (28,30, or 31 days in the month).
   * So we gave the Calendar.java program a test to see if the month and year correlate to a leap year and therefore should not have 29 days.
   * Here we control of the program and it accomplishes the goal it is designed for (an accurate non-Leap Year calendar).
   */
  @Test
  public void checkNotALeapYear(){
    String data = "2" +
      "\n2019";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    String args[] = null;
    Calendar.main(args);
    String calendar = outContent.toString();
    assertFalse(calendar.toString().contains("29"));
  }
  
  //August is supposed to have 31 days
     /*Part of this test that addresses observability is is when we look at the the input of 08 2019 this is because
   * we are simply looking at the calendar month and year inputted and making sure the days of the month are correct.
   * This is easily able to be verified for correctness when looking
   * at the calendar. The behavior based off this test gives a good way to see the behavior of the program and it is fairly
   * easy to see what we expect as the output with the given input in this test.
   * Part of this test that addresses controllability because in this test we want to test if the month and year  
   * correlates correctly to the right number of days(31 days in the month). When looking at August 2019
   * So we gave the Calendar.java program a test to see if the month and year correlate to a correct number of days
   * and therefore have 31 days in this test.
   * Here we control of the program and it accomplishes the goal it is designed for (correct number of days in input).
   */
  @Test
  public void testAugust() {
    String data = "8" +
      "\n2019";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    String args[] = null;
    Calendar.main(args);
    String calendar = outContent.toString();//the string of the calendar
    assertTrue(calendar.toString().contains("31"));
  }
  
  
  //checking december 2019
   /*Part of this test that addresses observability is is when we look at the the input of 12 2019 this is because
   * we are simply looking at the calendar month and this is easily able to be verified for correctness when looking
   * at the calendar. The behavior based off this test gives a good way to see the behavior of the program and it is fairly
   * easy to see what we expect as the output with the given input in this test.
   * Part of this test that addresses controllability because in this test we want to test if the month starts on the right day,
   * and has the right number of days. This checks to make sure the entire month matches up as expected for this case (December 2019)
   * so we gave the Calendar.java program a test to see if the month starts on the right day to make sure we have good
   * control of the program and it accomplishes the goal it is designed for (an accurate calendar).
   */
  @Test
  public void testDec2019(){
    String data = "12" +
      "\n2019";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    
    String decFirstWeek = "\n 1  2  3  4  5  6  7 " ;
    String decSecWeek = "\n 8  9 10 11 12 13 14 ";
    String decThirdWeek = "\n15 16 17 18 19 20 21 ";
    String decFourthWeek = "\n22 23 24 25 26 27 28 ";
    String decLastWeek = "\n29 30 31";
    
    String args[] = null;
    Calendar.main(args);
    String calendar = outContent.toString();
    assertTrue(calendar.toString().contains(decFirstWeek));
    assertTrue(calendar.toString().contains(decSecWeek));
    assertTrue(calendar.toString().contains(decThirdWeek));
    assertTrue(calendar.toString().contains(decFourthWeek));
    assertTrue(calendar.toString().contains(decLastWeek));
  }
  
  //checking september 2019
  /*Part of this test that addresses observability is is when we look at the the input of 09 2019 this is because
   * we are simply looking at the calendar month and this is easily able to be verified for correctness when looking
   * at the calendar. The behavior based off this test gives a good way to see the behavior of the program and it is fairly
   * easy to see what we expect as the output with the given input in this test.
   * Part of this test that addresses controllability because in this test we want to test if the month starts on the right day,
   * and has the right number of days. This checks to make sure the entire month matches up as expected for this case (September 2019)
   * so we gave the Calendar.java program a test to see if the month starts on the right day to make sure we have good
   * control of the program and it accomplishes the goal it is designed for (an accurate calendar).
   */
  @Test
  public void testSep2019(){
    String data = "9" +
      "\n2019";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    
    String sepFirstWeek = "\n 1  2  3  4  5  6  7 " ;
    String sepSecWeek = "\n 8  9 10 11 12 13 14 ";
    String sepThirdWeek = "\n15 16 17 18 19 20 21 ";
    String sepFourthWeek = "\n22 23 24 25 26 27 28 ";
    String sepLastWeek = "\n29 30";
    
    String args[] = null;
    Calendar.main(args);
    String calendar = outContent.toString();
    assertTrue(calendar.toString().contains(sepFirstWeek));
    assertTrue(calendar.toString().contains(sepSecWeek));
    assertTrue(calendar.toString().contains(sepThirdWeek));
    assertTrue(calendar.toString().contains(sepFourthWeek));
    assertTrue(calendar.toString().contains(sepLastWeek));
  }
    
}

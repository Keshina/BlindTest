
import org.junit.*; 
import static org.junit.Assert.*;

import java.io.*;  
import java.util.Scanner;


public class CalendarTest {
  public static void main(String args[]) throws Exception{
    org.junit.runner.JUnitCore.main("CalendarTest");
  }
  
  @Before
  public void setUp(){
    ByteArrayOutputStream baos = null;
    PrintStream ps = null;
  }
  
  @After
  public void end() {
    ByteArrayOutputStream baos = null;
    PrintStream ps = null;
  }
  
  @Test public void testActualLeapYears(){
    assertTrue(Calendar.isLeapYear(2016)); 
    assertTrue(Calendar.isLeapYear(2020));
    assertTrue(Calendar.isLeapYear(2000));
    assertTrue(Calendar.isLeapYear(1600));
    //observability: this functionality is only observed by the user when entering a february month in the main function
    //controllability: user controls what year input they want, may not be a valid integer however. For this reason, we hardcoded to have more control.
  }
  
  @Test public void testNotLeapYears1(){
    assertFalse(Calendar.isLeapYear(2015));
    //observability: this functionality is only observed by the user when entering a february month in the main function
    //controllability: user controls what year input they want, may not be a valid integer however. For this reason, we hardcoded to have more control.
  }
  
  @Test public void testNotLeapYears2(){
    assertFalse(Calendar.isLeapYear(1900));
    //observability: this functionality is only observed by the user when entering a february month in the main function
    //controllability: user controls what year input they want, may not be a valid integer however. For this reason, we hardcoded to have more control.
  }
  
  @Test public void testNotLeapYears3(){
    assertFalse(Calendar.isLeapYear(2300));
    //observability: this functionality is only observed by the user when entering a february month in the main function
    //controllability: user controls what year input they want, may not be a valid integer however. For this reason, we hardcoded to have more control.
  }
  
  @Test public void testNotLeapYears4(){
    assertFalse(Calendar.isLeapYear(1999));
    //observability: this functionality is only observed by the user when entering a february month in the main function
    //controllability: user controls what year input they want, may not be a valid integer however. For this reason, we hardcoded to have more control.
  }
  
  @Test public void testWeekDayJan2019(){
    assertEquals(Calendar.weekDay(1,1,2019), 2); //Tuesday
    //observability: this functionality is only observed once the calendar is printed out (see what day Jan 2019 starts)
    //controllability: user controls what year and month inputs they want, may not be a valid integer however. month... 
    //...input might not be between 1 and 12 (inclusive) as well
  }
  
  @Test public void testWeekDayFeb2019(){
    assertEquals(Calendar.weekDay(2,1,2019), 5); //Friday
    //observability: this functionality is only observed once the calendar is printed out (see what day a Feb 2019 starts)
    //controllability: user controls what year and month inputs they want, may not be a valid integer however. month... 
    //...input might not be between 1 and 12 (inclusive) as well
  }
  
  @Test public void testWeekDayMarch2019(){
    assertEquals(Calendar.weekDay(3,1,2019), 5); //Friday
    //observability: this functionality is only observed once the calendar is printed out (see what day a March 2019 starts)
    //controllability: user controls what year and month inputs they want, may not be a valid integer however. month... 
    //...input might not be between 1 and 12 (inclusive) as well
  }
  
  @Test public void testWeekDayApril2019(){
    assertEquals(Calendar.weekDay(4,1,2019), 1); //Monday
    //observability: this functionality is only observed once the calendar is printed out (see what day a April 2019 starts)
    //controllability: user controls what year and month inputs they want, may not be a valid integer however. month... 
    //...input might not be between 1 and 12 (inclusive) as well
  }
  
  @Test public void testReadInput(){
    String inputString = "4";
    InputStream input = new ByteArrayInputStream(inputString.getBytes());
    System.setIn(input);
    
    assertEquals("4", Calendar.readInput(new Scanner(System.in), ""));
    //observability: what the user inputs for month and day can be seen as the user inputs 
    //controllability: again, a valid integer input might not be provided by the user, possibly leading to unwanted exceptions
  }
  
  @Test public void testMainFeb2019LeapYear() throws Exception{
    // Create stream to hold the printing output that is executed in main
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    
    // Save the old System.out to reset later
    PrintStream old = System.out;
    
    // print to our stream
    System.setOut(ps);
    
    // call main, which has print commands, stored into baos
    runMain(2, 2019); 
    //observability: user can see what Feb 2019 calendar looks like and whether it has a 29th day (it shouldn't)
    //controllability: here, we hardcoded the month and year inputs so we do not rely on improper user input for testing...
    //...a specific case we want to test. We thus have control over what is outputted. 
    
    // reset
    System.out.flush();
    System.setOut(old);
    
    assertTrue(baos.toString().contains("February 2019"));
    assertFalse(baos.toString().contains("29")); //is not a leap year
  }
  
  @Test public void testMainFeb2020LeapYear() throws Exception{
    // Create stream to hold the printing output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    
    // Save the old System.out to reset later
    PrintStream old = System.out;
    
    // print to our stream
    System.setOut(ps);
    
    // call main, which has print commands, stored in baos
    runMain(2, 2020); 
    //observability: user can see what Feb 2020 calendar looks like and whether it has a 29th day
    //controllability: here, we hardcoded the month and year inputs so we do not rely on improper user input for testing...
    //...a specific case we want to test. We thus have control over what is outputted. 
    
    // reset
    System.out.flush();
    System.setOut(old);
    
    assertTrue(baos.toString().contains("February 2020"));
    assertTrue(baos.toString().contains("29")); //is a leap year
  }
  
  @Test public void testMainFeb2000LeapYear() throws Exception{
    // Create stream to hold the printing output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    
    // Save the old System.out to reset later
    PrintStream old = System.out;
    
    // print to our stream
    System.setOut(ps);
    
    // call main, which has print commands, stored in baos
    runMain(2, 2000); 
    //observability: user can see what Feb 2000 calendar looks like and whether it has a 29th day
    //controllability: here, we hardcoded the month and year inputs so we do not rely on improper user input for testing...
    //...a specific case we want to test. We thus have control over what is outputted. 
    
    // reset
    System.out.flush();
    System.setOut(old);
    
    assertTrue(baos.toString().contains("February 2000"));
    assertTrue(baos.toString().contains("29")); //is a leap year
  }
  
  @Test public void testMainFeb1900LeapYear() throws Exception{
    // Create stream to hold the printing output
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    
    // Save the old System.out to reset later
    PrintStream old = System.out;
    
    // print to our stream
    System.setOut(ps);
    
    // call main, which has print commands, stored in baos
    runMain(2, 1900); 
    //observability: user can see what Feb 1900 calendar looks like and whether it has a 29th day (it shouldn't)
    //controllability: here, we hardcoded the month and year inputs so we do not rely on improper user input for testing...
    //...a specific case we want to test. We thus have control over what is outputted. 
    
    // reset
    System.out.flush();
    System.setOut(old);
    
    assertTrue(baos.toString().contains("February 1900"));
    assertFalse(baos.toString().contains("29")); //is not a leap year
  }
  
  @Test public void testMainAugust2019NumDays() throws Exception{
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);

    PrintStream old = System.out;

    System.setOut(ps);

    runMain(8, 2019);
    //observability: user can see what August 2019 calendar looks like and whether it has a 31st day (it should)
    //controllability: here, we hardcoded the month and year inputs so we do not rely on improper user input for testing...
    //...a specific case we want to test. We thus have control over what is outputted. 
    
    System.out.flush();
    System.setOut(old);
    assertTrue(baos.toString().contains("31")); //August has 31 days
  }
  
  @Test public void testMainMarch2020StartDay() throws Exception{
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);

    PrintStream old = System.out;

    System.setOut(ps);

    runMain(3, 2020);
    //observability: user can see what March 2020 calendar looks like and whether it starts on a Sunday (it should)
    //controllability: here, we hardcoded the month and year inputs so we do not rely on improper user input for testing...
    //...a specific case we want to test. We thus have control over what is outputted. 
    
    System.out.flush();
    System.setOut(old);
    String thirdLine = baos.toString().split("\n")[2]; //get third line of output --> begins days of month here
    //System.err.println(thirdLine);
    assertTrue(thirdLine.startsWith("1")); //starts on Sunday
  }
  
  @Test public void testMainFeb2019StartDay() throws Exception{
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);

    PrintStream old = System.out;

    System.setOut(ps);

    runMain(2, 2019);
    //observability: user can see what Feb 2019 calendar looks like and whether it starts on a Friday (it should)
    //controllability: here, we hardcoded the month and year inputs so we do not rely on improper user input for testing...
    //...a specific case we want to test. We thus have control over what is outputted. 
    
    System.out.flush();
    System.setOut(old);
    String thirdLine = baos.toString().split("\n")[2];
    //System.err.println(thirdLine);
    String expected = String.format("%" + 17 + "s", "1"); //starts on Friday ('1' printed after 16th space) 
    assertTrue(thirdLine.startsWith(expected)); 
  }
  
  

  /*Brought same functionality of Calendar.java below to allow for testing of private functions,
  Also can hardcode values of month,year without Scanner use (easier for testing, since does not rely on user input)*/
  private static void runMain(int num1, int num2){

      // Get month and year from user
      int month = num1;
      int year  = num2;

      // months[i] = name of month i
      String[] months = {
            "",                               // leave empty so that months[1] = "January"
            "January", "February", "March",
            "April",   "May",      "June",
            "July",    "August",   "September",
            "October", "November", "December"
      };

      // days[i] = number of days in month i (skip month 0)
      int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 30, 30, 31, 30, 31 };

      // check for leap year
      if (month == 2 && isLeapYear(year))
         days[month] = 30;

      // print calendar header
      System.out.println("   " + months[month] + " " + year);
      System.out.println(" S  M Tu  W Th  F  S");

      // starting day of the week
      int wd = weekDay(month, 1, year);
      // print the calendar
      // space over for the first day
      for (int i = 0; i < wd; i++)
         System.out.print("   ");
      for (int i = 1; i <= days[month]; i++) {
         System.out.printf("%2d ", i);
         if (((i + wd) % 7 == 0) || (i == days[month]))
            System.out.println();
      }
   } 
  
  private static int weekDay(int month, int day, int year) {
    int y = year - (14 - month) / 10;
    int x = y + y/4 - y/100 + y/400;
    int m = month + 12 * ((14 - month) / 12) - 2;
    int wd = (day + x + (31*m)/12) % 7;
    return wd;
  }
  
  private static boolean isLeapYear(int year) {
    if ((year % 4 == 0) || (year % 100 != 0))
      return true;
    if (year % 400 == 0)
      return true;
    return false;
  }
  
  private static String readInput(Scanner sc, String arg) {
    System.out.print("Enter "+arg+": "); /* CLI */
    return(sc.next()); /* CLI */
  }
}

import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;

public class CalendarTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;
  
  @Before
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }
  
  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }
  
  private String getOutput() {
    return testOut.toString();
  }
  
  @After
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }
  
  @Test
  public void testLeapYear1() {
    String testString = "2" + "\n2000";
    provideInput(testString);    
    Calendar.main(new String[0]);
    assertEquals("Entermonth:Enteryear:February2000SMTuWThFS123456789101112131415161718192021222324252627282930", getOutput().replaceAll("\\s+",""));
 //Observability: Its very easy to see what the output will be and what it should look like
 //Controllability: We can immediately take input from testString and use it as part of our tests
  }
  
  @Test
  public void testLeapYear2() {
    String testString = "2" + "\n2001";
    provideInput(testString);    
    Calendar.main(new String[0]);
    assertEquals("Entermonth:Enteryear:February2001SMTuWThFS12345678910111213141516171819202122232425262728", getOutput().replaceAll("\\s+",""));
 //Observability: Again, we have our comparison for what it should look like versus what the code will output
 //Controllability: Again, we can just take input from testString and use it for our test again
  }
  
  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testMonth1() {
    String testString = "-1" + "\n1";
    provideInput(testString);    
    Calendar.main(new String[0]);
 //Observability: It is harder to look at this test, as we can see the input, but output would not be possible to see
 //Controllability: we can just take input from testString and use it for our test again
  }
  
  @Test(expected = NumberFormatException.class)
  public void testMonth2() {
    String testString = "a" + "\n1";
    provideInput(testString);    
    Calendar.main(new String[0]);
 //Observability: It is harder to observe this test, but we can see from the input, that it will throw and exception, as a is not a valid month
 //Controllability: we can just take input from testString and use it for our test
  }
  
  @Test(expected = NumberFormatException.class)
  public void testYear() {
    String testString = "1" + "\na";
    provideInput(testString);    
    Calendar.main(new String[0]);
 //Observability: It is harder to observe this test, but we can see from the input, that it will throw and exception, as a is not a valid year
 //Controllability: as said before, we can just take input from testString to test our functionality
  }
}

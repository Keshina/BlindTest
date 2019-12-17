import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class CalendarTest 
{

   private final InputStream originalIn = System.in;
   private final PrintStream originalOut = System.out;
   
   private ByteArrayOutputStream outContent;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before 
   public void setUp() 
   {
      outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));
   }

   @After 
   public void tearDown() 
   {
      System.setIn(originalIn);     
      System.setOut(originalOut);
   }
   
   // Observability: Rather easy to see the output for this, though the inner workings a tad cumbersome since had to find a way to check main.
   // Controllability: It is rather easy to provide inputs, both correct and incorrect.
   @Test 
   public void testCorrectMonthAndYear() 
   {
      final String data = "2\n2016";
      ByteArrayInputStream inContent = new ByteArrayInputStream(data.getBytes());
      System.setIn(inContent);
      Calendar.main(new String[0]);
      assertTrue(outContent.toString().contains("February 2016"));
   }
   
   // Observability: Even easier to see the output for this, since exceptions are thrown when given wrong input, still cumbersome to check via main however.
   // Controllability: Same as above, since there is no error checking for input for this program.
   @Test(expected = ArrayIndexOutOfBoundsException.class)
   public void testMonth1() 
   {
      final String data = "13\n1240";
      ByteArrayInputStream inContent = new ByteArrayInputStream(data.getBytes());
      System.setIn(inContent);
      Calendar.main(new String[0]);
   }
   
   // Observability: Same as the test above, just checking the opposite edge case, so fault clearly noticeable upon wrong input.
   // Controllability: Ibid, same as the rest of the test in terms of ease for inputs.
   @Test(expected = ArrayIndexOutOfBoundsException.class)
   public void testMonth2() 
   {
      final String data = "-1\n1396";
      ByteArrayInputStream inContent = new ByteArrayInputStream(data.getBytes());
      System.setIn(inContent);
      Calendar.main(new String[0]);
   }
   
   // Observability: Still easy to see its (incorrect) output since program only allows numeric keys, otherwise it throws exception.
   // Controllability: While still easy to mess up, only way this would happen is if someone mistyped while typing in their number.
   @Test(expected = NumberFormatException.class)
   public void testMonth3() 
   {
      final String data = "[a-zA-Z]+\n2019";
      ByteArrayInputStream inContent = new ByteArrayInputStream(data.getBytes());
      System.setIn(inContent);
      Calendar.main(new String[0]);
   }
   
   // Observability: Same as above, except testing for it where the year is instead of month. Just like always only difficult part was observing it through main initially.
   // Controllability: Same as above, still easy to mess up, but definitely someone can mistype while typing in their number.
   @Test(expected = NumberFormatException.class)
   public void testYear1() 
   {
      final String data = "3\n[a-zA-Z]+";
      ByteArrayInputStream inContent = new ByteArrayInputStream(data.getBytes());
      System.setIn(inContent);
      Calendar.main(new String[0]);
   }
}

/** A JUnit Test class for testing the Calendar class.
 *
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

/*List all the test inputs to Calendar.java. This defines the input domain of the method.
 */

//LIST DOMAIN OF VALID INPUTS
//month: integers (0 - 12)
//year: integers

/*Test class that tests Calendar.java
 **/
public class CalendarTest2{

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


    /**
     * Controlibility and observability were two issues we had with every function.  Since all functions were private
     * we could not invoke them directly.  This is a problem with only a few solutions, based on the requirements the
     * best being testing the public interface directly.  All of our tests do the same idea, and test the main method
     * directly.  This still allows us to cover every line of code while still respecting information hiding
     */
    @Test
    public void testCase1() {
        String data = "2" +
                "\n2019";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        //System.setIn(s1);
        String args[] = null;
        Calendar.main(args);

        final String testString = "Enter month: Enter year:    February 2019" +
                "\n S  M Tu  W Th  F  S" +
                "\n                1  2 " +
                "\n 3  4  5  6  7  8  9 " +
                "\n10 11 12 13 14 15 16 " +
                "\n17 18 19 20 21 22 23 " +
                "\n24 25 26 27 28 29 30 " +
                "\n";
        provideInput(testString);
        assertEquals(testString, getOutput());
    }

    /**
     * Controlibility and observability were two issues we had with every function.  Since all functions were private
     * we could not invoke them directly.  This is a problem with only a few solutions, based on the requirements the
     * best being testing the public interface directly.  All of our tests do the same idea, and test the main method
     * directly.  This still allows us to cover every line of code while still respecting information hiding
     */
    @Test
    public void testCase2() {
        String data = "9" +
                "\n2019";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        //System.setIn(s1);
        String args[] = null;
        Calendar.main(args);

        final String testString = "Enter month: Enter year:    September 2019" +
                "\n S  M Tu  W Th  F  S" +
                "\n 1  2  3  4  5  6  7 " +
                "\n 8  9 10 11 12 13 14 " +
                "\n15 16 17 18 19 20 21 " +
                "\n22 23 24 25 26 27 28 " +
                "\n29 30 " +
                "\n";
        provideInput(testString);
        assertEquals(testString, getOutput());
    }

    /**
     * Controlibility and observability were two issues we had with every function.  Since all functions were private
     * we could not invoke them directly.  This is a problem with only a few solutions, based on the requirements the
     * best being testing the public interface directly.  All of our tests do the same idea, and test the main method
     * directly.  This still allows us to cover every line of code while still respecting information hiding
     */
    @Test
    public void testCase3() {
        String data = "1" +
                "\n2020";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        //System.setIn(s1);
        String args[] = null;
        Calendar.main(args);

        final String testString = "Enter month: Enter year:    January 2020" +
                "\n S  M Tu  W Th  F  S" +
                "\n          1  2  3  4 " +
                "\n 5  6  7  8  9 10 11 " +
                "\n12 13 14 15 16 17 18 " +
                "\n19 20 21 22 23 24 25 " +
                "\n26 27 28 29 30 31 " +
                "\n";
        provideInput(testString);
        assertEquals(testString, getOutput());
    }

    /**
     * Controlibility and observability were two issues we had with every function.  Since all functions were private
     * we could not invoke them directly.  This is a problem with only a few solutions, based on the requirements the
     * best being testing the public interface directly.  All of our tests do the same idea, and test the main method
     * directly.  This still allows us to cover every line of code while still respecting information hiding
     */
    @Test
    public void testCase4() {
        String data = "12" +
                "\n2020";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        //System.setIn(s1);
        String args[] = null;
        Calendar.main(args);

        final String testString = "Enter month: Enter year:    December 2020" +
                "\n S  M Tu  W Th  F  S" +
                "\n       1  2  3  4  5 " +
                "\n 6  7  8  9 10 11 12 " +
                "\n13 14 15 16 17 18 19 " +
                "\n20 21 22 23 24 25 26 " +
                "\n27 28 29 30 31 " +
                "\n";
        provideInput(testString);
        assertEquals(testString, getOutput());
    }
}

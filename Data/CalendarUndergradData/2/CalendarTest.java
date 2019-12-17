import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class CalendarTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput(){
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    //Helper method
    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    //Tests for if calendar is printed when input is 0 for both month and year
    // Tests controllability with user inputs 0,0
    // Tests observability when output is printed and checked for correct information
    @Test
    public void calendarPrintTest(){
        final String data = "0\n0";
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);

        Calendar.main(new String[0]);

        assertFalse(getOutput().contains("1"));
    }

    //Tests if given inputs print the correct month and year
    // Tests controllability with user inputs 1, 2001
    // Tests observability when output is printed and checked for true information

    @Test
    public void calendarPrintTest2(){
        final String data = "1\n2001";
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);

        Calendar.main(new String[0]);

        assertTrue(getOutput().contains("January 2001"));
    }

    //Tests if given inputs print the correct month and year
    // Tests controllability with user inputs 12, 2001
    // Tests observability when output is printed and checked for true information
    @Test
    public void calendarPrintTest3(){
        final String data = "12\n2001";
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);

        Calendar.main(new String[0]);

        assertTrue(getOutput().contains("December 2001"));
    }

    //Tests if given inputs print the correct month and year
    // Tests controllability with user inputs 2, 2019
    // Tests observability when output is printed and checked for false information
    @Test
    public void calendarPrintTest4(){
        final String data = "2\n2019";
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);

        Calendar.main(new String[0]);

        assertFalse(getOutput().contains("March 2019"));
    }

    //Tests if given inputs print the correct month and year
    // Tests controllability with user inputs 12 , 2001
    // observability when output is printed and checked for false information
    @Test
    public void calendarPrintTest5(){
        final String data = "12\n2001";
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);

        Calendar.main(new String[0]);

        assertFalse(getOutput().contains("December 2000"));
    }

    //Tests if given inputs print the correct month and year
    // Tests controllability with user inputs -1 , 2001
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void calendarPrintTest6(){
        final String data = "-1\n2001";
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);

        Calendar.main(new String[0]);

    }

    //Tests if calendar correctly prints a leap year,
    // Tests controllability with user inputs 2, 2000
    // Tests observability when output is printed and checked for correct information
    @Test
    public void calendarLeapYearTest(){
        final String data = "2\n2000";
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);

        Calendar.main(new String[0]);

        assertTrue(getOutput().contains("29"));
    }

    //Tests leap year function by giving a non leap year.
    // Tests controllability with user inputs 2, 2001
    // observability when output is printed and checked for correct information
    @Test
    public void calendarLeapYearTest2(){
        final String data = "2\n2001";
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);

        Calendar.main(new String[0]);

        assertFalse(getOutput().contains("29"));
    }
}

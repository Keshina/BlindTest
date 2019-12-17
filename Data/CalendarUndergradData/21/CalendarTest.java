
import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;
import java.util.*;
public class CalendarTest
{

    private final InputStream inputStream = System.in;
    public static void main(String args[]){
        org.junit.runner.JUnitCore.main("CalendarTest");
    }
    private ByteArrayInputStream input;
    private ByteArrayOutputStream output;
    private PrintStream outputStream = System.out;
    @Before
    public void setUpOutput() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }
    @After
    public void restoreSystemInputOutput() {
        System.setIn(inputStream);
        System.setOut(outputStream);
    }

    /**
     * This test case checks for a happy path: testing if an expected input behaves correctly.
     */
    @Test
    public void HappyPathTest() {
        final String inputString = "9\n2019";
        input = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(input);
        String expectedResult = "Enter month: Enter year:    September 2019\n"+" S  M Tu  W Th  F  S\n"+" 1  2  3  4  5  6  7 \n"+
                " 8  9 10 11 12 13 14 \n"+"15 16 17 18 19 20 21 \n"+"22 23 24 25 26 27 28 \n"+
                "29 30 \n";

        Calendar.main(new String[0]);
        String actualResult = output.toString();
        assertTrue("Can't pass the Happy Path test: possible mistakes in the design of the algorithm.\n",expectedResult.equals(actualResult));
    }
    /**
     * This test case checks for a leap year: testing if a leap year is identified.
     */
    @Test
    public void leapYearTest1() {
        final String inputString = "2\n2020";
        input = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(input);
        String expectedResult = "Enter month: Enter year:    February 2020\n"+" S  M Tu  W Th  F  S\n"+"                   1 \n"+" 2  3  4  5  6  7  8 \n"+
                " 9  10 11 12 13 14 15 \n"+"16 17 18 19 20 21 22 \n"+"23 24 25 26 27 28 29 \n";
        Calendar.main(new String[0]);
        String actualResult = output.toString();
        assertTrue("The leap year calculation is incorrect.\n",expectedResult.equals(actualResult));
    }
    /**
     * This test case checks for a non-leap year: testing if the leap year algorithm is correct.
     */
    @Test
    public void leapYearTest2() {
        final String inputString = "2\n2019";
        input = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(input);
        String expectedResult = "Enter month: Enter year:    February 2019\n"+" S  M Tu  W Th  F  S\n"+"                1  2 \n"+" 3  4  5  6  7  8  9 \n"+
                "10 11 12 13 14 15 16 \n"+"17 18 19 20 21 21 23 \n"+
                "24 25 26 27 28 \n";
        Calendar.main(new String[0]);
        String actualResult = output.toString();
        assertTrue("The leap year algorithm is incorrect.\n",expectedResult.equals(actualResult));
    }
    /**
     * This test case checks for a invalid inputs: testing if an invalid month such as 0 is handled correctly.
     */
    @Test
    public void ZeroMonthTest() {
        final String inputString = "0\n2019";
        input = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(input);
        String expectedResult = "Invalid Input: The value for month may not be 0.";
        Calendar.main(new String[0]);
        String actualResult = output.toString();
        assertTrue("The exception paths are not tested thoroughly: month=0\n",expectedResult.equals(actualResult));
    }
    /**
     * This test case checks for a invalid inputs: testing if an invalid year such as a negative year is handled correctly.
     */
    @Test
    public void NegativeYearTest() {
        final String inputString = "5\n-3";
        input = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(input);
        String expectedResult = "Invalid Input: The value for year may not be a negative value.";
        Calendar.main(new String[0]);
        String actualResult = output.toString();
        assertTrue("The exception paths are not tested thoroughly: year<0\n",expectedResult.equals(actualResult));
    }
    /**
     * This test case checks for a invalid inputs: testing if an invalid month such as a negative month is handled correctly.
     */
    @Test
    public void NegativeMonthTest() {
        final String inputString = "-5\n2019";
        input = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(input);
        String expectedResult = "Invalid Input: The value for month may not be a negative value.";
        Calendar.main(new String[0]);
        String actualResult = output.toString();
        assertTrue("The exception paths are not tested thoroughly: month<0\n",expectedResult.equals(actualResult));
    }
    /**
     * This test case checks for a invalid inputs: testing if an invalid input type such as a string is handled correctly.
     */
    @Test
    public void StringInputTest() {
        final String inputString = "January\n2019";
        input = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(input);
        String expectedResult = "Invalid Input: The value for month may only be of type int.";
        Calendar.main(new String[0]);
        String actualResult = output.toString();
        assertTrue("The exception paths are not tested thoroughly: Unexpected Types\n",expectedResult.equals(actualResult));
    }
}

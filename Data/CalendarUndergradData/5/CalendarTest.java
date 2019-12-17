import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalendarTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream mockOutput;

    // taken from Piazza
    @Before
    public void setUpOutput() {
        mockOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockOutput));
    }

    // taken from Piazza
    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testSept2019() {
        final String input = "9\n2019"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);

        // this tests observability
        assertEquals("September 2019 Failed", "Enter month: Enter year:    September 2019\n" +
                " S  M Tu  W Th  F  S\n" +
                " 1  2  3  4  5  6  7 \n" +
                " 8  9 10 11 12 13 14 \n" +
                "15 16 17 18 19 20 21 \n" +
                "22 23 24 25 26 27 28 \n" +
                "29 30 \n", mockOutput.toString());
    }


    @Test
    public void testLeapYearDivisibleByFourHundred() {
        final String input = "2\n2000"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);

        // this tests observability, should fail b/c leapYear is wrong
        assertEquals("February 2000 Failed", "Enter month: Enter year:    February 2000\n" +
                " S  M Tu  W Th  F  S\n" +
                "       1  2  3  4  5 \n" +
                " 6  7  8  9 10 11 12 \n" +
                "13 14 15 16 17 18 19 \n" +
                "20 21 22 23 24 25 26 \n" +
                "27 28 29 \n", mockOutput.toString());
    }

    @Test
    public void testLeapYearNotDivisbleBy4Or100() {
        final String input = "2\n1999"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);

        // this tests observability, should fail b/c leapYear is wrong
        assertEquals("February 1999 Failed", "Enter month: Enter year:    February 1999\n" +
                " S  M Tu  W Th  F  S\n" +
                "    1  2  3  4  5  6 \n" +
                " 7  8  9 10 11 12 13 \n" +
                "14 15 16 17 18 19 20 \n" +
                "21 22 23 24 25 26 27 \n" +
                "28 \n", mockOutput.toString());
    }

    @Test
    public void testLeapYear2016() {
        final String input = "2\n2016"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);

        // this tests observability, should fail b/c leapYear is wrong
        // Feb only has 29 days in a leap yr not 30
        assertEquals("February 2016 Failed", "Enter month: Enter year:    February 2016\n" +
                " S  M Tu  W Th  F  S\n" +
                "    1  2  3  4  5  6 \n" +
                " 7  8  9 10 11 12 13 \n" +
                "14 15 16 17 18 19 20 \n" +
                "21 22 23 24 25 26 27 \n" +
                "28 29 \n", mockOutput.toString());
    }

    @Test(expected = NumberFormatException.class) // observability tested here
    public void testNegativeYear() {
        final String input = "5\n-12"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class) // observability tested here
    public void testOutOfBoundsMonth() {
        final String input = "-5\n2018"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class) // observability tested here
    public void testZeroMonth() {
        final String input = "0\n2018"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
    }

    @Test(expected = NumberFormatException.class) // observability tested here
    public void testBadInput() {
        final String input = "abc\n2012"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
    }

    @Test(expected = NumberFormatException.class) // observability tested here
    public void testDoubleInput() {
        final String input = "5.5\n2012"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
    }

    @Test
    public void testMonthAfterLeapYear() {
        final String input = "3\n1999"; // controllability being tested here

        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);

        // this tests observability, should fail b/c leapYear is wrong
        assertEquals("March 1999 Failed", "Enter month: Enter year:       March 1999\n" +
                " S  M Tu  W Th  F  S\n" +
                "    1  2  3  4  5  6 \n" +
                " 7  8  9 10 11 12 13 \n" +
                "14 15 16 17 18 19 20 \n" +
                "21 22 23 24 25 26 27 \n" +
                "28 29 30 31 \n", mockOutput.toString());
    }


}

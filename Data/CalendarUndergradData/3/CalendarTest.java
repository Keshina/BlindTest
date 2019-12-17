import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
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

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    /**
     * Observability:
     * We have a test augustTest() to see if August prints 31 days.
     * This is easily observable by checking the output. If it prints
     * 31 days then it is correct. However, because the original
     * program prints 30 days we it fails our test.
     *
     * Controllability:
     * In the augustTest(), we have a good controllability.
     * The first line “String input = "8\n2019";” has the string
     * that we are feeding into standard input. We are also separating
     * different inputs using the “\n” char. It allows us to provide
     * any needed input we wish to test the method.
     */
    @Test
    void augustTest() {
        String input = "8\n2019";
        provideInput(input);
        Calendar.main(new String[]{});
        String output = getOutput();
        System.out.println(output);

        //TEST FOR AUGUST :D
        String testOut = "Enter month: Enter year:    August 2019\n" +
                " S  M Tu  W Th  F  S\n" +
                "             1  2  3 \n" +
                " 4  5  6  7  8  9 10 \n" +
                "11 12 13 14 15 16 17 \n" +
                "18 19 20 21 22 23 24 \n" +
                "25 26 27 28 29 30 31 \n";
        assertEquals(testOut,output,"AUGUST TEST FAILED");
    }

    /**
     * Observability:
     * In the LeapYearIsFalseTest, we have a high level of observability.
     * We input the month: 2 and the year that is a non-leap year: 2019.
     * We can observe the output on the console and see that the month
     * printed has more than 28 days. We also have a predefined string
     *that contains the expected output which we can compare to the actual output.
     *
     * Controllability:
     * In the leapYearIsFalseTest(), we have a good controllability.
     * The first line String input = "2\n2019";” has the string that
     * we are feeding into standard input. We are also separating
     * different inputs using the “\n” char. It allows us to provide
     * any needed input we wish to test the method. We can also easily
     * just change the input type if we wish to test the method using various types.
     *
     */
    @Test
    void leapYearIsFalseTest(){
        String input = "2\n2019";
        provideInput(input);
        Calendar.main(new String[]{});
        String output = getOutput();
        System.out.println(output);

        //TEST FOR LEAP YEAR, LEAP YEAR SHOULD BE FALSE
        String testOut = "Enter month: Enter year:    February 2019\n" +
                " S  M Tu  W Th  F  S\n" +
                "                1  2 \n" +
                " 3  4  5  6  7  8  9 \n" +
                "10 11 12 13 14 15 16 \n" +
                "17 18 19 20 21 22 23 \n" +
                "24 25 26 27 28 \n";
        assertEquals(testOut,output,"LEAP YEAR IS FALSE TEST FAILED");
    }

    /**
     * Obeservability:
     *
     * We also have a test LeapYearIsTrueTest which is highly observable.
     * We input the month: 2 and the year: 2020 which is a leap year. We
     * can test the actual output of the program against the expected
     * string and see if the actual output has 29 days.
     *
     * Controllability:
     *
     * In the leapYearIsTrueTest(), we have a good controllability.
     * The first line String input = "2\n2020"; ” has the string that
     * we are feeding into standard input. We are also separating
     * different inputs using the “\n” char. It allows us to provide
     * any needed input we wish to test the method. We can also easily
     * just change the input type if we wish to test the method using various types.
     *
     */
    @Test
    void leapYearIsTrueTest(){
        String input = "2\n2020";
        provideInput(input);
        Calendar.main(new String[]{});
        String output = getOutput();
        System.out.println(output);

        //TEST FOR LEAP YEAR, LEAP YEAR SHOULD BE TRUE
        String testOut = "Enter month: Enter year:    February 2020\n" +
                " S  M Tu  W Th  F  S\n" +
                "                   1 \n" +
                " 2  3  4  5  6  7  8 \n" +
                " 9 10 11 12 13 14 15 \n" +
                "16 17 18 19 20 21 22 \n" +
                "23 24 25 26 27 28 29 \n";
        assertEquals(testOut,output,"LEAP YEAR IS TRUE TEST FAILED");
    }

    /**
     * Observability:
     *
     * We also have a test zerothMonthWrongOutput() which is highly observable.
     * We input the month: 0  and an obritatry year. The month 0 should output
     * an observable error message that 0 is not a valid input. Our test checks
     * if an error message is displayed.
     *
     * Controllability:
     *
     * In the zerothMonthWrongOutput(), we have a good controllability.
     * The first line “String input = "0\n2020";” has the string that we
     * are feeding into standard input. We are also separating different
     * inputs using the “\n” char. It allows us to provide any needed i
     * nput we wish to test the method. We can also easily just change
     * the input type if we wish to test the method using various types.
     */
    @Test
    void zerothMonthWrongOutput(){
        String input = "0\n2020";
        provideInput(input);
        Calendar.main(new String[]{});
        String output = getOutput();
        System.out.println(output);

        //TEST FOR MONTH ZERO, SHOULD INFORM USER OF WRONG INPUT
        String testOut = "Enter month: Enter year: Invalid month\n";
        assertEquals(testOut,output,"ZERO MONTH TEST FAILED");
    }

    @Test
    /**
     * Observability:
     *
     * In the OutOfBoundsMonthTest, we have a high level of observability.
     * We can observe if the program behavior is correct by checking the
     * console output. If we enter a month outside the range of 12 we can
     * observe the environment and catch an arrayOutOfBoundsExcepetion which
     * is easy to observe.
     *
     * Controllability:
     *
     * In the outOfBoundsMonthTest(), we have good controllability. The first
     * line “String input = "13\n2020";” has the string that we are feeding
     * into standard input. We are also separating different inputs using the
     * “\n” char. It allows us to provide any needed input we wish to test the
     * method. We can also easily just change the input type if we wish to test
     * the method using various types.
     *
     */
    void outOfBoundsMonthTest(){
        String input = "13\n2020";
        provideInput(input);
        try {
            Calendar.main(new String[]{});
        }catch (ArrayIndexOutOfBoundsException e){
            fail("OUT OF BOUND MONTH FAILED");
        }
        String output = getOutput();
        System.out.println(output);
        String testOut = "Enter month: Enter year: Invalid month\n";
        assertEquals(testOut,output,"OUT OF BOUNDS MONTH FAILED");
    }

    @Test
    /**
     * Observability:
     *
     * We included a happy test which has a high level of observability. We provided
     * inputs of month:3 and year: 2019 which the program printed correctly and
     * passed our test.
     *
     * Controllability:
     *
     * In the happyHappyTest(), we have a good controllability. The first line
     * “String input = "3\n2019";” has the string that we are feeding into standard
     * input. We are also separating different inputs using the “\n” char. It allows
     * us to provide any needed input we wish to test the method. We can also easily
     * just change the input type if we wish to test the method using various types.
     */
    void happyHappyTest(){
        String input = "3\n2019";
        provideInput(input);
        Calendar.main(new String[]{});
        String output = getOutput();
        System.out.println(output);

        //THIS IS EASY, YOU BETTER PASS DIS
        String testOut = "Enter month: Enter year:    March 2019\n" +
                " S  M Tu  W Th  F  S\n" +
                "             1  2  3 \n" +
                " 4  5  6  7  8  9 10 \n" +
                "11 12 13 14 15 16 17 \n" +
                "18 19 20 21 22 23 24 \n" +
                "25 26 27 28 29 30 31 \n";
        assertEquals(testOut,output,"YOU FAILED THE HAPPY TEST D:");
    }
}
package Assignment4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class CalendarTest {

    private final InputStream systemIn = System.in;
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

    @Test
    public void testCase1() {
        /****observability*****/
        final String data = "12\n2019"; //\n makes sure that 9 and 2019 are considered as two separate inputs

        ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
        /****controllability*****/
        assertTrue(mockOutput.toString().contains("December 2019"));
    }
    @Test
    public void testCase2() {
        /****observability*****/
        final String data = "13\n2019"; //\n makes sure that 9 and 2019 are considered as two separate inputs

        ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
        /****controllability*****/
        assertTrue(mockOutput.toString().contains("Error"));
    }
    @Test
    public void testCase3() {
        /****observability*****/
        final String data = "0\n0"; //\n makes sure that 9 and 2019 are considered as two separate inputs

        ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
        /****controllability*****/
        assertTrue(mockOutput.toString().contains("0"));
    }
    @Test
    public void testCase4() {
        /****observability*****/
        final String data = "8\n2019"; //\n makes sure that 9 and 2019 are considered as two separate inputs

        ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
        /****controllability*****/
        assertTrue(mockOutput.toString().contains("30"));
    }
    @Test
    public void testCase5() {
        /****observability*****/
        final String data = "2\n2016"; //\n makes sure that 9 and 2019 are considered as two separate inputs

        ByteArrayInputStream mockInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(mockInput);

        Calendar.main(new String[0]);
        /****controllability*****/
        assertTrue((mockOutput.toString().contains("30")));
    }
}

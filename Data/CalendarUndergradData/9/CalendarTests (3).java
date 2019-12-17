import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

//import java.io.*;

public class CalendarTests {

    // it holds the stolen output
    private ByteArrayOutputStream output;
    // These just hold on to the standard in/out so we can reset them later.
    private final InputStream stdIn = System.in;
    private final PrintStream stdOut = System.out;

    //@Before
    public void setup() {
        // handy-dandy thing to snatch the printed output from Calendar.java
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @After
    public void restoreIO() {
        // restores standard input/output
        System.setIn(stdIn);
        System.setOut(stdOut);
    }

    // This does all the heavy work.
    private String testHelpr(String input){

        // pass input to input stream...
        ByteArrayInputStream passIn = new ByteArrayInputStream(input.getBytes());

        System.setIn(passIn);

        Calendar.main(new String[0]);

        String thing2 = output.toString();

        restoreIO();

        // trims out the "Enter month: Enter year: "
        String thing3 = thing2.substring(25);
        return thing3;
    }

    /*
   Observability: assertEquals returns the actual state of the code ran
   Controllability: testHelpr takes the input and passes it to the code were testing
   */
    @Test
    public void testApril2012() {

        setup();

        //UNCOMMENT this version if running on windows / the other version doesn't work
       /* String expected = "   April 2012\r\n S  M Tu  W Th  F  S\r\n" +
                "                1  2 \r\n 3  4  5  6  7  8  9 \r\n10 11 12 13 14 15 16 \r\n" +
                "17 18 19 20 21 22 23 \r\n24 25 26 27 28 29 30 \r\n";*/

        String expected = "   April 2012\n S  M Tu  W Th  F  S\n" +
                "                1  2 \n 3  4  5  6  7  8  9 \n10 11 12 13 14 15 16 \n" +
                "17 18 19 20 21 22 23 \n24 25 26 27 28 29 30 \n";

        assertEquals(expected, testHelpr("4\n2012"));
    }

    /*
   Observability: assertEquals returns the actual state of the code ran
   Controllability: testHelpr takes the input and passes it to the code were testing
   */
    // returns the last seven days in the calendar
    private String leapyearhelpr(String result){

        String[] febArray = result.split("\n");

        String thing = "";

        if(febArray.length==8){

            thing = febArray[6] + febArray[7];

        }
        else {
            thing = febArray[5] + febArray[6];
        }

        String[] stringi = thing.split(" ");

        List<Integer> daylist = new ArrayList<>();

        for (int i = 0; i < stringi.length; i++) {
            String nustr = stringi[i];

            if(!nustr.isEmpty()) {
                int day = Integer.parseInt(nustr);
                daylist.add(day);
            }
        }

        //last seven days of february...
        List<Integer> dayl = daylist.subList(daylist.size()-7, daylist.size());

        StringBuilder nuStrb = new StringBuilder();

        for (Integer str : dayl) {

            String blah = str + " ";

            nuStrb.append(blah);

        }

        return nuStrb.toString();
    }


    /*
   Observability: assertEquals returns the actual state of the code ran
   Controllability: testHelpr takes the input and passes it to the code were testing
   */
    @Test
    public void testFebruary_leapyear(){
        //February during a leap year should have 29 days.
        // Because Calendar's isLeapYear method is private, this is a little workaround to test
        // if it's working properly by using February.

        //A whole bunch of tests in one!
        int[] leapyears = {1808, 1812, 1816, 1840, 1852, 1880, 1904, 1932, 1960, 1980, 2000,
                2012, 2060, 2108, 2172, 2224, 2332, 2396, 2400};

        for(int i =0; i<leapyears.length; i++){

            setup();

            String input = "2\n"+leapyears[i];

            String res = testHelpr(input);

            restoreIO();

            String lastDays = leapyearhelpr(res);

            String expected = "23 24 25 26 27 28 29 ";

            if(!lastDays.endsWith("29 ")){


                String fmsg = "The year "+leapyears[i]+" is a leap year. February "+leapyears[i]+" should have 29 days.\n" +
                        "The last week of February "+leapyears[i]+":";

                assertEquals(fmsg, expected,lastDays);
            }
        }
    }

    /*
   Observability: assertEquals returns the actual state of the code ran
   Controllability: testHelpr takes the input and passes it to the code were testing
   */
    @Test
    public void testFebruary_notleapyear(){
        //February should have 28 days.
        // Because Calendar's isLeapYear method is private, this is a little workaround to test
        // if it's working properly by using February.

        //A whole bunch of tests in one!
        int[] leapyears = {1808, 1812, 1816, 1840, 1852, 1880, 1904, 1932, 1960, 1980, 2000,
                2012, 2060, 2108, 2172, 2224, 2332, 2396, 2400};

        for(int i =0; i<leapyears.length; i++){

            setup();

            int notleapyear = leapyears[i]-1;

            String input = "2\n"+notleapyear;

            String res = testHelpr(input);

            restoreIO();

            String lastDays = leapyearhelpr(res);

            String expected = "22 23 24 25 26 27 28 ";

            if(!lastDays.endsWith("28 ")){


                String fmsg = "The year "+notleapyear+" is not a leap year. February "+notleapyear+" should have 28 days.\n" +
                        "The last week of February "+notleapyear+":";

                assertEquals(fmsg, expected,lastDays);
            }
        }
    }

    /*
      Observability: observability is lower here as all we have is exception handling
      Controllability: testHelpr takes the input and passes it to the code were testing
      */
    @Test
    public void testBadMonth(){

        //Considering that we can't modify Calendar.java, this will always fail.

        String input = "May\n";

        setup();

        try {

            testHelpr(input);

            Calendar.main(new String[0]);


        } catch (NumberFormatException e){

            restoreIO();

            String msg = "Do you have input checks?";
            fail(msg);
        }
    }

    /*
      Observability: observability is lower here as all we have is exception handling
      Controllability: testHelpr takes the input and passes it to the code were testing
      */
    @Test
    public void testBadYear(){

        //Considering that we can't modify Calendar.java, this will always fail.

        String input = "5\nideklol";

        setup();

        try {

            testHelpr(input);

            Calendar.main(new String[0]);

        } catch (NumberFormatException e){

            restoreIO();
            String msg = "Do you have input checks?";
            fail(msg);
        }
    }
}

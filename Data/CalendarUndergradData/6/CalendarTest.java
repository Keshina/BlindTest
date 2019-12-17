


import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class CalendarTest {
    // output buffer for System.out
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    private int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private String[] monthNames = { "", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

    /**
     * Simulates System.in
     * @param month
     * @param year
     */
    private void input(String month, String year){
        String input = month + System.lineSeparator() + year;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    /**
     * Capture output
     */
    @Before
    public void captureOutput(){
        // Start capturing
        System.setOut(new PrintStream(buffer));
    }

    /**
     * Reset capture
     */
    @After
    public void resetOutputCapture(){
        buffer.reset();
    }

    /**
     * Tests bad inputs for month. Not an integer and numbers our of month domain.
     * Controllability: how easy is it to pass inputs.
     */
    @Test
    public void testBadMonthInput() {
        String month[] = new String[]{"a", "-1", "0", "13"};

        for(int i = 0; i < month.length; i++){
            input(month[i], "2019");
            try{
                Calendar.main(null);
                Assert.fail("Expected exception thrown on month input: " + month[i]);
            }catch (Exception e){ }
        }
    }

    /**
     * Tests bad inputs for year. Not an integer, zero or negative values.
     * Controllability: how easy is it to pass inputs.
     */
    @Test
    public void testBadYearInput() {
        String year[] = new String[]{"a", "-1", "0"};

        for(int i = 0; i < year.length; i++){
            input("1", year[i]);
            try{
                Calendar.main(null);
                Assert.fail("Expected exception thrown on year input: " + year[i]);
            }catch (Exception e){ }
        }
    }

    /**
     * Tests that program accepts month input domain.
     * Observability: observe that the program outputs correct calendar for each month.
     * Not very good observability, we have to capture outputs and check if string contains the right days
     */
    @Test
    public void testAllMonthsExceptFebruary(){
        for(int month = 1; month <= 12; month++){
            if(month == 2){
                continue;
            }

            // Pass inputs to System.in
            input(Integer.toString(month), "2019");
            try {
                Calendar.main(null);
                // cast output buffer to a string
                String content = buffer.toString();
                Assert.assertTrue(monthNames[month] + " should have " + days[month] + " days", content.contains(Integer.toString(days[month])));
                Assert.assertFalse(monthNames[month] + " should only have " + days[month] + " days", content.contains(Integer.toString(days[month] + 1)));
                resetOutputCapture(); // need to reset now because we are looping, otherwise it gets called automatically
            }catch (Exception e){
                Assert.fail("Exception not expected");
            }
        }
    }

    /**
     * Tests February for a leap year to have exactly 29 days.
     * Observabilty: verify that the calendar for february in a leap year has 29 days.
     * Not easy to do, need to capture output bad observability.
     */
    @Test
    public void testFebruaryLeapYear(){
    	input("2", "2020");
    	try {
            Calendar.main(null);
            String content = buffer.toString();
            Assert.assertTrue("Leap year February should have 29 days", content.contains("29"));
            Assert.assertFalse("Leap year February should only have 29 days", content.contains("30"));
        }catch (Exception e){
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Tests February for a non leap year to have exactly 28 days.
     * Observabilty: verify that the calendar for february in a non leap year has 28 days.
     * Not easy to do, need to capture output bad observability.
     */
    @Test
    public void testFebruaryNonLeapYear(){
    	input("2", "2019");
    	try {
            Calendar.main(null);
            String content = buffer.toString();
            Assert.assertTrue("Non leap year February should have 28 days", content.contains("28"));
            Assert.assertFalse("Non leap year February should only have 28 days", content.contains("29"));
        }catch (Exception e){
            Assert.fail("Exception not expected");
        }
    }


}

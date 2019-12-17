package quizretakes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class quizschedTest {

    public quizzes quizList;
    public retakes retakesList;
    public courseBean course;
    public String sample;
    public String header;
    public ByteArrayOutputStream out = new ByteArrayOutputStream();

    //Set up for functions.  This header is hard coded for software testing
    @Before
    public void setUp() throws Exception
    {
        LocalDate start = LocalDate.of(2019, 2, 21);
        LocalDate end = LocalDate.of(2010, 2, 25);
        course = new courseBean("swe437", "Software testing", "14", start, end, "/var/www/CS/webapps/offutt/WEB-INF/data/");

        header = "\n\n******************************************************************************\n";
        header += "GMU quiz retake scheduler for class " + course.getCourseTitle() + "\n";
        header += "******************************************************************************\n\n\n";
        header += "You can sign up for quiz retakes within the next two weeks. \n";
        header += "Enter your name (as it appears on the class roster), \n";
        header += "then select which date, time, and quiz you wish to retake from the following list.\n\n";

        System.setOut(new PrintStream(out));


        quizList = new quizzes(1, 2, 25, 15, 00);
        retakesList = new retakes(1, "Engineering", 2, 26, 00, 00);

    }

    //Clean up
    @After
    public void tearDown() throws Exception
    {
        quizList = null;
        retakesList = null;
        course = null;
        sample = null;
    }

    //Test that tests most of the lines of code. Original test.
    //Controllibility:  This test code allows for testing of most lines of code. This controls the code by
    //                  providing input that allows it is execute correctly.
    //Observability:    This is observable because the print outs to the user are standard and expected.
    @Test
    public void quizTestMost()
    {

        //Basic header and output catch
        sample = header;
        out.reset();


        //Large range of dates to ensure +7 days to test window
        LocalDate start = LocalDate.of(2000, 1, 21);
        LocalDate end = LocalDate.of(2999, 1, 25);
        course = new courseBean("swe437", "Software testing", "14", start, end, "/var/www/CS/webapps/offutt/WEB-INF/data/");
        quizretakes.quizsched.printQuizScheduleForm(quizList, retakesList, course);

        LocalDate today  = LocalDate.now();
        LocalDate endDay = today.plusDays(new Long(21));
        sample += "Today is " + today.getDayOfWeek() + ", " + today.getMonth() + " " + today.getDayOfMonth() + "\n";
        sample += "Currently scheduling quizzes for the next two weeks, until ";
        sample += endDay.getDayOfWeek() + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth() + "\n";
        sample += "RETAKE: TUESDAY, FEBRUARY 26, at 00:00 in Engineering\n";
        sample += "    1) Quiz 1 from MONDAY, FEBRUARY 25\n\n";

        assertEquals(sample, out.toString());
    }

    //Test that tests for the ability for program to have skip week condition.
    //Controllability: this manipulates the code to allow for the execution of the skip week.
    //Observability:  With the printing out of the Skipping a week prompt, the execution of the code
    //                segment can be observed
    @Test
    public void quizTestSkipWeek()
    {

        //basic set up for header and output catch
        sample = header;
        out.reset();

        //large range of dates to ensure +7 days for condition
        LocalDate start = LocalDate.of(2000, 1, 21);
        LocalDate end = LocalDate.of(2999, 1, 25);
        course = new courseBean("swe437", "Software testing", "14", start, end, "/var/www/CS/webapps/offutt/WEB-INF/data/");
        int number = LocalDate.now().getDayOfMonth() - 13;

        //Day to allow for skip week to fall within the given window
        retakesList = new retakes(1, "Engineering", 3, number, 00, 00);

        quizretakes.quizsched.printQuizScheduleForm(quizList, retakesList, course);

        LocalDate today  = LocalDate.now();
        LocalDate endDay = today.plusDays(new Long(21));
        sample += "Today is " + today.getDayOfWeek() + ", " + today.getMonth() + " " + today.getDayOfMonth() + "\n";
        sample += "Currently scheduling quizzes for the next two weeks, until ";
        sample += endDay.getDayOfWeek() + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth() + "\n";
        sample += "      Skipping a week, no quiz or retakes.\n";
        sample += "RETAKE: " + LocalDate.now().minusDays(13).getDayOfWeek() +", MARCH " + number + ", at 00:00 in Engineering\n";
        sample += "\n";
        assertEquals(sample, out.toString());
    }

    //Test for No skip condition. only 14 day window
    //Controllability: this manipulates the code to allow for the execution of no skip week if statement
    //Observability:  With the hard coding of 14 days vs 21, this skipping of the code is observable by passing the
    //                Test.
    @Test
    public void quizTestNoSkip()
    {

        //Basic set up for header and output catch
        sample = header;
        out.reset();

        quizretakes.quizsched.printQuizScheduleForm(quizList, retakesList, course);

        LocalDate today  = LocalDate.now();
        LocalDate endDay = today.plusDays(new Long(14));  //no skip
        sample += "Today is " + today.getDayOfWeek() + ", " + today.getMonth() + " " + today.getDayOfMonth() + "\n";
        sample += "Currently scheduling quizzes for the next two weeks, until ";
        sample += endDay.getDayOfWeek() + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth() + "\n";
        sample += "RETAKE: TUESDAY, FEBRUARY 26, at 00:00 in Engineering\n";
        sample += "    1) Quiz 1 from MONDAY, FEBRUARY 25\n\n";

        assertEquals(sample, out.toString());
    }

    //Test for no retake or quiz days. Testing false condition of loops
    //Controllibility:  This code allows for the testing of no execution of the two loops for the quizzes/retakes.
    //                  This controls the execution path.
    //Observability:    This can be observed by seeing no print out from the test days.  Slightly observable.
    @Test
    public void quizTestNoDays()
    {
        //Basic set up for header/output catch
        sample = header;
        out.reset();

        LocalDate today  = LocalDate.now();
        LocalDate end = today.plusDays(new Long(14)); //Has skip.


        //Empty lists
        quizList = new quizzes();
        retakesList = new retakes();
        quizretakes.quizsched.printQuizScheduleForm(quizList, retakesList, course);

        sample += "Today is " + today.getDayOfWeek() + ", " + today.getMonth() + " " + today.getDayOfMonth() + "\n";
        sample += "Currently scheduling quizzes for the next two weeks, until ";
        sample += end.getDayOfWeek() + ", " + end.getMonth() + " " + end.getDayOfMonth() + "\n\n";
        assertEquals(sample, out.toString());
    }

    //Test for triple null input.  Should throw null pointer exception.
    //Controllibility:   This could should crash the code because there is a null pointer exception.
    //Observability:     Slightly observable. If one null crashes, the others cannot be observed because error is
    //                   Thrown.
    @Test
    public void testTripleNull()
    {
        try
        {
            quizretakes.quizsched.printQuizScheduleForm(null, null, null);
        }
        catch(NullPointerException e)
        {
            return;
        }

        fail("Fail Triple Null");
    }

}
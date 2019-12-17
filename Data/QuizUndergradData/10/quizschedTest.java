package quizretakes;

import jdk.internal.org.xml.sax.SAXException;
import org.junit.*;

import javax.xml.parsers.ParserConfigurationException;

import static java.util.stream.IntStream.of;
import static org.junit.Assert.*;
import static quizretakes.quizsched.*;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;


import java.io.IOException;
import java.time.*;
import java.lang.Long;
import java.lang.String;

import java.io.*;

import java.util.Properties;



/**
 * this test file tests the printQuizScheduleForm() method
 * assumes that the original xml files that were given to us by Dr. Offutt
 * and his source code are placed in .../src/quizretakes/
 */
public class quizschedTest {

    public quizsched schedTest = new quizsched();

    //paths for the xml files
    Path currentRelativePath = Paths.get("");
    String s = currentRelativePath.toAbsolutePath().toString() + "/src/quizretakes/"; //added src/quizretakes since this was done in IntelliJ
    String courseFile = s + "course-swe437.xml";
    public String quizFile = s + "quiz-orig-swe437.xml";
    public String retakeFile = s + "quiz-retakes-swe437.xml";

    int daysAvailable;

    //variables that will hold our test values for the courseBean object
    courseBean courseTest;
    courseReader courseReader;

    //variables that will hold our test values for the quizzes object
    quizzes quizzesT;
    quizReader quizReader;

    //variables that will hold our test values for the retakes object
    retakes retakesTest;
    retakesReader retakesReader;

    LocalDate newStartTest = LocalDate.of(2019, 1, 29); // LocalDate variable for testing
    LocalDate newStartTest2 = LocalDate.of(2019, 2, 5); // LocalDate variable for testing
    LocalDate newStartTest3 = LocalDate.of(2019, 1, 30); // LocalDate variable for testing
    LocalDate newStartTest4 = LocalDate.of(2019, 2, 5); // LocalDate variable for testing


    /**
     * initializes our retakes class with the values from the appropriate xml files
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public quizschedTest() throws IOException, ParserConfigurationException, SAXException {
        try {
            quizReader = new quizReader();// create quiz reader instance
            quizzesT = quizReader.read(quizFile);// read quiz file -> new quizzes(1, 1, 29, 10, 30); /* CLI */


            retakesReader = new retakesReader();
            retakesTest = retakesReader.read(retakeFile); //read retake -> new retakes(1, "EB 4430", 1, 30, 15, 30); /* CLI */

            courseReader = new courseReader(); // create courseReader instance
            courseTest = courseReader.read(courseFile); // read course file
            daysAvailable = Integer.parseInt(courseTest.getRetakeDuration()); // get # of days available

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUp() {
        System.out.println("FINISHED TEST");
    }

    /**
     * tests our getters used throughout the printQuizScheduleForm() method
     * This test addresses overall controllability since it checks if the courseBean object inputs
     * are being read correctly and in turn will make sure that the input for the
     * printQuizScheduleForm method will be correct.
     *
     */
    @Test
    public void gettersCourseBeanTest() {
        //all variable initializations address Controllability
        LocalDate newStart = LocalDate.of(2019, 1, 21);
        LocalDate newEnd = LocalDate.of(2019, 1, 25);

        courseBean course = new courseBean("swe437", "Software testing", "14",
                newStart, newEnd, "/var/www/CS/webapps/offutt/WEB-INF/data/");

        quizzes quizList = new quizzes(1, 1, 29, 10, 30); /* CLI */
        retakes retakesList = new retakes(1, "EB 4430", 1, 30, 15, 30); /* CLI */

        //controllability tested here
        assertEquals("courseBean CourseTitle not equivalent", courseTest.getCourseTitle(), course.getCourseTitle());
        assertEquals("courseBean CourseID not equivalent", courseTest.getCourseID(), course.getCourseID());
        assertEquals("courseBean RetakeDuration not equivalent", courseTest.getRetakeDuration(), course.getRetakeDuration());
        assertEquals("courseBean DataLocation not equivalent", courseTest.getDataLocation(), course.getDataLocation());
        assertEquals("courseBean StartSkip not equivalent", courseTest.getStartSkip(), course.getStartSkip());
        assertEquals("courseBean EndSkip not equivalent", courseTest.getEndSkip(), course.getEndSkip());



    }

    /**
     * tests the quizzes object to see if it is reading correctly
     * Overall controllability is observed here since we are making sure the inputs that are stored in a quiz work
     * and that in turn will provide correct input for the printQuizScheduleForm method
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    @Test
    public void quizzesTest() throws  IOException, ParserConfigurationException, SAXException{
        //all variable initializations address Controllability
        LocalDate newStart1 = LocalDate.of(2019, 1, 29);
        LocalDate newStart2 = LocalDate.of(2019, 2, 5);

        quizzes quizList; /* CLI */
        quizReader qr = new quizReader();

        try{
            //all variable initializations address Controllability
            quizList = qr.read(quizFile);// reads quizFile
            Iterator<quizBean> iter = quizList.iterator();//iterator to iterate through quizList

            //both of these two assertion blocks address Observability
            // tests quiz 1
            quizBean quiz1 = iter.next();
            assertEquals("TEST ID 1 WRONG", 1, quiz1.getID());
            assertEquals("TEST DATE 1 WRONG", newStart1, quiz1.getDate());
            assertEquals("TEST TIME 1 WRONG", "10:30", quiz1.timeAsString());

            // tests quiz 2
            quizBean quiz2 = iter.next();
            assertEquals("TEST ID 2 WRONG", 2, quiz2.getID());
            assertEquals("TEST DATE 2 WRONG", newStart2, quiz2.getDate());
            assertEquals("TEST TIME 2 WRONG", "10:30", quiz2.timeAsString());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * tests that quizschedul initialization is not null
     * tests Observability since we are observing whether or not the initialization is not null
     */
    @Test
    public void testNotNull()
    {
        assertNotNull(schedTest);
    }

    /**
     * tests the retakes object
     * this test addresses Controllability since it tests the inputs of a retakes object and
     * in turn makes sure that correct input is put when the printQuizScheduleForm method is called
     * @throws Exception
     */
    @Test
    public void quizRetakeTest() throws IOException, ParserConfigurationException, SAXException {
        //all variable initializations address Controllability
        retakes retakes;
        retakesReader retakesR = new retakesReader();

        try
        {
            //all variable initializations address Controllability
            retakes = retakesR.read(retakeFile);
            Iterator<retakeBean> iter = retakes.iterator();

            //All assertions address Observability
            // tests for first retake
            retakeBean rb1 = iter.next();
            assertEquals("wrong testID1",1, rb1.getID());
            assertEquals("wrong test date 1", newStartTest3, rb1.getDate());
            assertEquals("wrong test time 1", "15:30", rb1.timeAsString());
            assertEquals("wrong test location 1", "EB 4430", rb1.getLocation());

            // tests for second retake
            retakeBean rb2 = iter.next();
            assertEquals("wrong testID2", 2, rb2.getID());
            assertEquals("wrong test date 2", newStartTest2, rb2.getDate());
            assertEquals("wrong test time 2", "16:00", rb2.timeAsString());
            assertEquals("wrong test location 2", "???", rb2.getLocation());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * tests the printQuizScheduleForm method
     * Addresses Observability since we are checking the output of the printQuizScheduleForm method as a whole
     * @throws Exception
     */
    @Test
    public void testPrintQuizScheduleForm() throws Exception
    {
        //all variable initializations address Controllability
        LocalDate endD = LocalDate.of(2019, 02, 5);

        LocalDate startSkip = courseTest.getStartSkip();
        LocalDate endSkip   = courseTest.getEndSkip();

        boolean skip = false;

        LocalDate today  = LocalDate.of(2019, 1, 22);
        LocalDate endDay = today.plusDays(new Long(daysAvailable));
        LocalDate origEndDay = endDay;

        // if endDay is between startSkip and endSkip, add 7 to endDay
        if(!endDay.isBefore(startSkip) && !endDay.isAfter(endSkip))
        {  // endDay is in a skip week, add 7 to endDay
            //Controllability
            endDay = endDay.plusDays(new Long(7));
            skip = true;
        }


        // Unique integer for each retake and quiz pair
        int quizRetakeCount = 0; /* CLI */

        for(retakeBean r: retakesTest)
        {
            LocalDate retakeDay = r.getDate();
            if(!(retakeDay.isBefore(today)) && !(retakeDay.isAfter(endDay)))
            {
                // if skip && retakeDay is after the skip week
                if(skip && retakeDay.isAfter(origEndDay))
                {  // A "skip" week such as spring break.
                    skip = false; //Controllability
                }

                for(quizBean q: quizzesT)
                {
                    //Controllability
                    LocalDate quizDay = q.getDate();
                    LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));

                    if(!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay) &&
                            !today.isAfter(retakeDay) && !retakeDay.isAfter(endDay))
                    {
                        quizRetakeCount++; //Controllability
                    }
                }
            }
        }

        //these assertions test Observability since we are checking the output of various variables used in the method
        assertEquals("end day for 01-22-2019 should be 02-05-2019", endDay, endD);
        assertEquals("skip should be false", false, skip);
        assertEquals("number of quizzes is not 3", 3, quizRetakeCount);

    }


    @After
    public void tearDown() throws Exception {

    }

}

package com.csbuddies.quizretakes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

// Every test faces and solves observability and controllablity in the same way
public class quizschedTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final LocalDate today = LocalDate.now();
    private quizzes quizzes;
    private retakes retakes;
    private courseBean course;

    /**
     * addresses observability
     */
    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * addresses observability
     */
    @After
    public void cleanup() throws IOException {
        System.setOut(originalOut);
        System.setErr(originalErr);
        outContent.close();
        errContent.close();
    }


    /**
     * Address observability
     * @param template the template to parse
     * @return the formatted template
     */
    private String replaceTemplateDates(String template) {
        Pattern pattern = Pattern.compile("\\{date:(-?\\d+)}");
        Matcher matcher = pattern.matcher(template);
        HashSet<Integer> dates = new HashSet<>();
        while (matcher.find()) {
            dates.add(Integer.valueOf(matcher.group(1)));
        }
        LocalDate currDate = today;
        String subsitute = "";
        for (int offsetDays : dates) {
            currDate = today.plusDays(offsetDays);
            subsitute = currDate.getDayOfWeek() + ", " + currDate.getMonth() + " " + currDate.getDayOfMonth();
            template = template.replace("{date:" + offsetDays + "}", subsitute);
        }
        return template;
    }

    /**
     * Observability is a challenge because the method under test uses System.out.println()
     * - Reading the console output is not straight forward, so we redirect it to an outputStream we can interact with.
     * - An issue with observability is the output of the method is reliant on the current date of the system. We fix this by having the inputs and expected output be relative to the current date.
     * Controllability is fairly straight forward by skipping the xml documents for the data and creating objects manually.
     * - The method under test is not responsible for reading or parsing xml and therefore does not need to have that in it's tests.
     *
     * - This test is a simple baseline test.
     */
    @Test
    public void testPrintQuizScheduleOneQuiz() {
        LocalDate tomorrow = today.plusDays(1);
        String expectedOutput;
        quizzes = new quizzes();
        retakes = new retakes();
        course = new courseBean("cs484", "Data Mining", "14", LocalDate.of(2018, 12, 24), LocalDate.of(2018, 12, 31),
                                null);

        quizzes.addQuiz(new quizBean(0, today.getMonthValue(), today.getDayOfMonth(), 12, 01));
        retakes.addRetake(
                new retakeBean(0, "Merten Hall 1200", tomorrow.getMonthValue(), tomorrow.getDayOfMonth(), 12, 01));
        quizsched.printQuizScheduleForm(quizzes, retakes, course);
        expectedOutput = replaceTemplateDates("\n" +
                                                      "\n" +
                                                      "******************************************************************************\n" +
                                                      "GMU quiz retake scheduler for class Data Mining\n" +
                                                      "******************************************************************************\n" +
                                                      "\n" +
                                                      "\n" +
                                                      "You can sign up for quiz retakes within the next two weeks. \n" +
                                                      "Enter your name (as it appears on the class roster), \n" +
                                                      "then select which date, time, and quiz you wish to retake from the following list.\n" +
                                                      "\n" +
                                                      "Today is {date:0}\n" +
                                                      "Currently scheduling quizzes for the next two weeks, until {date:14}\n" +
                                                      "RETAKE: {date:1}, at 12:01 in Merten Hall 1200\n" +
                                                      "    1) Quiz 0 from {date:0}\n" +
                                                      "\n");
        assertEquals("Output of printQuizScheduleForm is incorrect", expectedOutput,
                     outContent.toString().replace("\r", ""));
    }

    /**
     * Observability is a challenge because the method under test uses System.out.println()
     * - Reading the console output is not straight forward, so we redirect it to an outputStream we can interact with.
     * - An issue with observability is the output of the method is reliant on the current date of the system. We fix this by having the inputs and expected output be relative to the current date.
     * Controllability is fairly straight forward by skipping the xml documents for the data and creating objects manually.
     * - The method under test is not responsible for reading or parsing xml and therefore does not need to have that in it's tests.
     *
     * - This test focuses on lots of data, and the skip-week
     */
    @Test
    public void testPrintQuizScheduleSkipWeek() {

        LocalDate tempDate = today;

        quizzes = new quizzes();
        LocalDate lastWeek = today.minusWeeks(1);
        LocalDate twoWeeksAgo = today.minusWeeks(2);
        LocalDate threeWeeksAgo = today.minusWeeks(3);
        LocalDate fourWeeksAgo = today.minusWeeks(4);
        // Add Quizzes
        quizzes.addQuiz(new quizBean(0, lastWeek.getMonthValue(), lastWeek.getDayOfMonth(), 10, 30));
        quizzes.addQuiz(new quizBean(1, lastWeek.getMonthValue(), lastWeek.getDayOfMonth(), 10, 45));
        quizzes.addQuiz(new quizBean(2, twoWeeksAgo.getMonthValue(), twoWeeksAgo.getDayOfMonth(), 11, 00));
        quizzes.addQuiz(new quizBean(3, threeWeeksAgo.getMonthValue(), threeWeeksAgo.getDayOfMonth(), 11, 00));
        quizzes.addQuiz(new quizBean(4, fourWeeksAgo.getMonthValue(), fourWeeksAgo.getDayOfMonth(), 11, 00));

        // Add retake opporutnites
        retakes = new retakes();
        retakes.addRetake(new retakeBean(0, "ENGR 101", today.getMonthValue(), today.getDayOfMonth(), 10, 15));
        retakes.addRetake(new retakeBean(1, "ENGR 102", today.getMonthValue(), today.getDayOfMonth(), 16, 30));

        tempDate = today.plusDays(4);
        retakes.addRetake(new retakeBean(2, "B101", tempDate.getMonthValue(), tempDate.getDayOfMonth(), 10, 15));
        tempDate = tempDate.plusDays(4);
        retakes.addRetake(new retakeBean(3, "B102", tempDate.getMonthValue(), tempDate.getDayOfMonth(), 10, 15));
        tempDate = tempDate.plusDays(4);
        retakes.addRetake(new retakeBean(4, "B103", tempDate.getMonthValue(), tempDate.getDayOfMonth(), 10, 15));
        tempDate = tempDate.plusDays(4);
        retakes.addRetake(new retakeBean(5, "B104", tempDate.getMonthValue(), tempDate.getDayOfMonth(), 10, 15));
        tempDate = tempDate.plusDays(4);
        retakes.addRetake(new retakeBean(6, "B105", tempDate.getMonthValue(), tempDate.getDayOfMonth(), 10, 15));

        course = new courseBean("swe437", "Software Testing", "14", today, today.plusWeeks(4), null);

        String expectedResult = replaceTemplateDates("\n" +
                                                             "\n" +
                                                             "******************************************************************************\n" +
                                                             "GMU quiz retake scheduler for class Software Testing\n" +
                                                             "******************************************************************************\n" +
                                                             "\n" +
                                                             "\n" +
                                                             "You can sign up for quiz retakes within the next two weeks. \n" +
                                                             "Enter your name (as it appears on the class roster), \n" +
                                                             "then select which date, time, and quiz you wish to retake from the following list.\n" +
                                                             "\n" +
                                                             "Today is {date:0}\n" +
                                                             "Currently scheduling quizzes for the next two weeks, until {date:21}\n" +
                                                             "RETAKE: {date:0}, at 10:15 in ENGR 101\n" +
                                                             "    1) Quiz 0 from {date:-7}\n" +
                                                             "    2) Quiz 1 from {date:-7}\n" +
                                                             "    3) Quiz 2 from {date:-14}\n" +
                                                             "RETAKE: {date:0}, at 16:30 in ENGR 102\n" +
                                                             "    4) Quiz 0 from {date:-7}\n" +
                                                             "    5) Quiz 1 from {date:-7}\n" +
                                                             "    6) Quiz 2 from {date:-14}\n" +
                                                             "RETAKE: {date:4}, at 10:15 in B101\n" +
                                                             "    7) Quiz 0 from {date:-7}\n" +
                                                             "    8) Quiz 1 from {date:-7}\n" +
                                                             "RETAKE: {date:8}, at 10:15 in B102\n" +
                                                             "RETAKE: {date:12}, at 10:15 in B103\n" +
                                                             "      Skipping a week, no quiz or retakes.\n" +
                                                             "RETAKE: {date:16}, at 10:15 in B104\n" +
                                                             "RETAKE: {date:20}, at 10:15 in B105\n\n");

        quizsched.printQuizScheduleForm(quizzes, retakes, course);
        assertEquals("Output of printQuizScheduleForm is incorrect", expectedResult,
                     outContent.toString().replace("\r", ""));
    }

    /**
     * Observability is a challenge because the method under test uses System.out.println()
     * - Reading the console output is not straight forward, so we redirect it to an outputStream we can interact with.
     * - An issue with observability is the output of the method is reliant on the current date of the system. We fix this by having the inputs and expected output be relative to the current date.
     * Controllability is fairly straight forward by skipping the xml documents for the data and creating objects manually.
     * - The method under test is not responsible for reading or parsing xml and therefore does not need to have that in it's tests.
     *
     * - This test focuses on the lack of data.
     */
    @Test
    public void fullEmptyTest() {
        quizzes = new quizzes();
        retakes = new retakes();
        course = new courseBean("", " ", "", today, today, null);
        String expectedResult = replaceTemplateDates("\n" +
                                                             "\n" +
                                                             "******************************************************************************\n" +
                                                             "GMU quiz retake scheduler for class  \n" +
                                                             "******************************************************************************\n" +
                                                             "\n" +
                                                             "\n" +
                                                             "You can sign up for quiz retakes within the next two weeks. \n" +
                                                             "Enter your name (as it appears on the class roster), \n" +
                                                             "then select which date, time, and quiz you wish to retake from the following list.\n" +
                                                             "\n" +
                                                             "Today is {date:0}\n" +
                                                             "Currently scheduling quizzes for the next two weeks, until {date:14}\n\n");
        quizsched.printQuizScheduleForm(quizzes, retakes, course);
        assertEquals("Output of emptyTest is incorrect", expectedResult, outContent.toString().replace("\r", ""));
    }

}

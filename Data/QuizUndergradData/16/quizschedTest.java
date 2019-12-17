package quizretakes;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class quizschedTest {

    private quizsched mquizsched;
    private Method quizschedPrintQuizScheduleForm;
    private LocalDate today;

    @Before
    public void setUp() {
        mquizsched = new quizsched();
        today = LocalDate.now(); // used for relative quiz and retake dates
        try {
            quizschedPrintQuizScheduleForm = mquizsched
                    .getClass()
                    .getDeclaredMethod("printQuizScheduleForm",
                            quizzes.class,
                            retakes.class,
                            courseBean.class);
            quizschedPrintQuizScheduleForm.setAccessible(true); // so that we can test the private method
        } catch (NoSuchMethodException e) {
            fail("quizsched does not have method 'printQuizScheduleForm'");
        }
    }

    @After
    public void tearDown() {
        mquizsched = null;
        quizschedPrintQuizScheduleForm = null;
    }

    /**
     * Runs the printQuizScheduleForm method with the specified arguments and redirects stdout to an output
     * stream
     * @param quizList
     * @param retakesList
     * @param course
     * @return Returns the output stream as a String, or an empty String if the method failed to run.
     */
    private String runPrintQuizScheduleForm(quizzes quizList, retakes retakesList, courseBean course) {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(outputStream));
            quizschedPrintQuizScheduleForm.invoke(mquizsched, quizList, retakesList, course);
            System.setOut(oldOut);
            return outputStream.toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail("Could not run quizsched.printQuizScheduleForm()");
        }

        return "";

    }

    /**
     * Happy path.
     * Tests whether the method prints out today's date correctly.
     */
    @Test
    public void printQuizScheduleFormTest1() {


        // Controllability:
        //  - the arguments must be instantiated relative to today's date
        LocalDate quizDay = today.minusDays(7);
        quizzes quizList = new quizzes(1,
                quizDay.getMonthValue(),
                quizDay.getDayOfMonth(),
                10,
                30);

        LocalDate retakeDay = today.plusDays(7);
        retakes retakesList = new retakes(1,
                "ENGR",
                retakeDay.getMonthValue(),
                retakeDay.getDayOfMonth(),
                10,
                0);

        courseBean course = new courseBean(
                "swe437",
                "Software testing",
                "14",
                LocalDate.of(2019, 1, 21),
                LocalDate.of(2019, 1, 25),
                "/Data/Location"
        );
        //



        // Observability:
        //  - redirects the printout from stdout to the outputStream variable.
        //  - uses the contains() method to check if the correct date is displayed.
        String outputStream = runPrintQuizScheduleForm(quizList, retakesList, course);

        String todayString = String.format("Today is %s, %s %d",
                today.getDayOfWeek(),
                today.getMonth(),
                today.getDayOfMonth());

        assertTrue(String.format("Method did not print \"%s\"", todayString),
                outputStream.contains(todayString));

    }

    /**
     * Happy path.
     * Tests whether the method prints out the retake string correctly
     */
    @Test
    public void printQuizScheduleFormTest2() {

        // Controllability:
        //  - the arguments must be instantiated relative to today's date
        //  - the retake day must be between today and two weeks ahead
        LocalDate quizDay = today.minusDays(7);
        quizzes quizList = new quizzes(1,
                quizDay.getMonthValue(),
                quizDay.getDayOfMonth(),
                10,
                30);

        LocalDate retakeDay = today.plusDays(7);
        retakes retakesList = new retakes(1,
                "ENGR",
                retakeDay.getMonthValue(),
                retakeDay.getDayOfMonth(),
                10,
                0);

        courseBean course = new courseBean(
                "swe437",
                "Software testing",
                "14",
                LocalDate.of(2019, 1, 21),
                LocalDate.of(2019, 1, 25),
                "/Data/Location"
        );
        //


        // Observability:
        //  - redirects the output into a variable
        //  - What is printed out must exactly contain the following string. This demonstrates the low observability
        //  - The retake string must be in the demonstrated format

        String outputStream = runPrintQuizScheduleForm(quizList, retakesList, course);

        String retakeString = String.format("RETAKE: %s, %s %d, at 10:00 in ENGR",
                retakeDay.getDayOfWeek(),
                retakeDay.getMonth(),
                retakeDay.getDayOfMonth());

        assertTrue(String.format("Method did not print \"%s\"", retakeString),
                outputStream.contains(retakeString));

    }

    /**
     * Happy path.
     * Tests whether a quiz string prints out correctly
     */
    @Test
    public void printQuizScheduleFormTest3() {


        // Controllability:
        //  - the arguments must be instantiated relative to today's date
        //  - the quiz day must be before the retake day and within two weeks
        LocalDate quizDay = today.minusDays(7);
        quizzes quizList = new quizzes(1,
                quizDay.getMonthValue(),
                quizDay.getDayOfMonth(),
                10,
                30);

        LocalDate retakeDay = today.plusDays(7);
        retakes retakesList = new retakes(1,
                "ENGR",
                retakeDay.getMonthValue(),
                retakeDay.getDayOfMonth(),
                10,
                0);

        courseBean course = new courseBean(
                "swe437",
                "Software testing",
                "14",
                LocalDate.of(2019, 1, 21),
                LocalDate.of(2019, 1, 25),
                "/Data/Location"
        );

        // Observability:
        //  - output is redirected
        //  - The quiz string must be in the correct format
        String outputStream = runPrintQuizScheduleForm(quizList, retakesList, course);

        String quizString1 = String.format("Quiz 1 from %s, %s %d",
                quizDay.getDayOfWeek(),
                quizDay.getMonth(),
                quizDay.getDayOfMonth());

        assertTrue(String.format("Method did not print \"%s\"", quizString1),
                outputStream.contains(quizString1));

    }

    /**
     * Tests whether the quiz string does NOT print
     */
    @Test
    public void printQuizScheduleFormTest4() {

        // Controllability
        //  - in order for the quiz to not print, the quiz day is set to be
        //    two weeks before today
        LocalDate quizDay = today.minusDays(14);
        quizzes quizList = new quizzes(1,
                quizDay.getMonthValue(),
                quizDay.getDayOfMonth(),
                10,
                30);

        LocalDate retakeDay = today.plusDays(7);
        retakes retakesList = new retakes(1,
                "ENGR",
                retakeDay.getMonthValue(),
                retakeDay.getDayOfMonth(),
                10,
                0);

        courseBean course = new courseBean(
                "swe437",
                "Software testing",
                "14",
                LocalDate.of(2019, 1, 21),
                LocalDate.of(2019, 1, 25),
                "/Data/Location"
        );

        // Observability
        //   - The quiz string with the correct format should NOT be printed, so the contains() method is used
        //   - with assertFalse
        String outputStream = runPrintQuizScheduleForm(quizList, retakesList, course);

        String quizString1 = String.format("Quiz 1 from %s, %s %d",
                quizDay.getDayOfWeek(),
                quizDay.getMonth(),
                quizDay.getDayOfMonth());

        assertFalse(String.format("Method should not had printed \"%s\"", quizString1),
                outputStream.contains(quizString1));

    }

    /**
     * Tests whether the retake string does NOT print
     */
    @Test
    public void printQuizScheduleFormTest5() {

        LocalDate quizDay = today.minusDays(7);
        quizzes quizList = new quizzes(1,
                quizDay.getMonthValue(),
                quizDay.getDayOfMonth(),
                10,
                30);

        // Controllability
        //  - The retake day is set to be a day before today, so it should not print.
        LocalDate retakeDay = today.minusDays(1);
        retakes retakesList = new retakes(1,
                "ENGR",
                retakeDay.getMonthValue(),
                retakeDay.getDayOfMonth(),
                10,
                0);

        courseBean course = new courseBean(
                "swe437",
                "Software testing",
                "14",
                LocalDate.of(2019, 1, 21),
                LocalDate.of(2019, 1, 25),
                "/Data/Location"
        );

        // Observability:
        //  - redirects the output into a variable
        //  - Makes sure the retake string with the correct format is not in the string
        String outputStream = runPrintQuizScheduleForm(quizList, retakesList, course);

        String retakeString = String.format("RETAKE: %s, %s %d, at 10:00 in ENGR",
                retakeDay.getDayOfWeek(),
                retakeDay.getMonth(),
                retakeDay.getDayOfMonth());

        assertFalse(String.format("Method should NOT had printed \"%s\"", retakeString),
                outputStream.contains(retakeString));

    }


    /**
     * Tests whether both retake days print
     */
    @Test
    public void printQuizScheduleFormTest6() {

        LocalDate quizDay = today.minusDays(7);
        quizzes quizList = new quizzes(1,
                quizDay.getMonthValue(),
                quizDay.getDayOfMonth(),
                10,
                30);

        // Controllability:
        //  - The first retake day is set to be yesterday in order for it to not print
        LocalDate retakeDay = today.plusDays(1);
        retakes retakesList = new retakes(1,
                "ENGR",
                retakeDay.getMonthValue(),
                retakeDay.getDayOfMonth(),
                10,
                0);

        LocalDate retakeDay2 = today.plusDays(14);

        retakesList.addRetake(
                new retakeBean(
                        2,
                        "Walmart",
                        retakeDay2.getMonthValue(),
                        retakeDay2.getDayOfMonth(),
                        9,
                        0
                )
        );

        courseBean course = new courseBean(
                "swe437",
                "Software testing",
                "14",
                LocalDate.of(2019, 1, 21),
                LocalDate.of(2019, 1, 25),
                "/Data/Location"
        );

        // Observability:
        //  - Makes sure only one of the retake strings are contained in the output stream
        String outputStream = runPrintQuizScheduleForm(quizList, retakesList, course);

        String retakeString = String.format("RETAKE: %s, %s %d, at 10:00 in ENGR",
                retakeDay.getDayOfWeek(),
                retakeDay.getMonth(),
                retakeDay.getDayOfMonth());

        String retakeString2 = String.format("RETAKE: %s, %s %d, at 09:00 in Walmart",
                retakeDay2.getDayOfWeek(),
                retakeDay2.getMonth(),
                retakeDay2.getDayOfMonth());

        assertTrue(
                String.format("Method should had printed \"%s\"", retakeString),
                outputStream.contains(retakeString));

        assertTrue(
                String.format("Method should had printed \"%s\"", retakeString2),
                outputStream.contains(retakeString2));

    }


    /**
     * Tests whether the first retake day does not print
     * Tests whether the author iterates through the retakes
     */
    @Test
    public void printQuizScheduleFormTest7() {

        LocalDate quizDay = today.minusDays(7);
        quizzes quizList = new quizzes(1,
                quizDay.getMonthValue(),
                quizDay.getDayOfMonth(),
                10,
                30);

        // Controllability
        //  - The retake day is set to be a day before today, so it should not print.
        LocalDate retakeDay = today.minusDays(1);
        retakes retakesList = new retakes(1,
                "ENGR",
                retakeDay.getMonthValue(),
                retakeDay.getDayOfMonth(),
                10,
                0);

        LocalDate retakeDay2 = today.plusDays(14);
        retakesList.addRetake(
                new retakeBean(
                        2,
                        "Walmart",
                        retakeDay2.getMonthValue(),
                        retakeDay2.getDayOfMonth(),
                        9,
                        0
                )
        );

        courseBean course = new courseBean(
                "swe437",
                "Software testing",
                "14",
                LocalDate.of(2019, 1, 21),
                LocalDate.of(2019, 1, 25),
                "/Data/Location"
        );

        // Observability:
        //  - redirects the output into a variable
        //  - Makes sure the retake string with the correct format is not in the output stream
        String outputStream = runPrintQuizScheduleForm(quizList, retakesList, course);

        String retakeString = String.format("RETAKE: %s, %s %d, at 10:00 in ENGR",
                retakeDay.getDayOfWeek(),
                retakeDay.getMonth(),
                retakeDay.getDayOfMonth());

        String retakeString2 = String.format("RETAKE: %s, %s %d, at 09:00 in Walmart",
                retakeDay2.getDayOfWeek(),
                retakeDay2.getMonth(),
                retakeDay2.getDayOfMonth());


        assertFalse(
                String.format("Method should NOT had printed \"%s\"", retakeString),
                outputStream.contains(retakeString));

        assertTrue(
                String.format("Method should had printed \"%s\"", retakeString2),
                outputStream.contains(retakeString2));

    }


    /**
     * Edge case.
     * Tests whether quiz 1 prints under the first retake, and only quiz 2 prints under
     * the second retake.
     */
    @Test
    public void printQuizScheduleFormTest8() {

        LocalDate quizDay = today.minusDays(7);

        quizzes quizList = new quizzes(1,
                quizDay.getMonthValue(),
                quizDay.getDayOfMonth(),
                10,
                30);

        // Controllability:
        //  - Added a second quiz by instantiating a new quiz bean
        //  - set the date of the second quiz to be after the first retake day
        //    and before the second retake day. This is to control the fact that
        //    quiz 1 prints under the first retake, and quiz 2 prints under the second retake.
        LocalDate quizDay2 = today.plusDays(8);
        quizList.addQuiz(
                new quizBean(
                        2,
                        quizDay2.getMonthValue(),
                        quizDay2.getDayOfMonth(),
                        9,
                        30
                )
        );

        LocalDate retakeDay = today.plusDays(7);
        retakes retakesList = new retakes(1,
                "ENGR",
                retakeDay.getMonthValue(),
                retakeDay.getDayOfMonth(),
                10,
                0);

        LocalDate retakeDay2 = today.plusDays(14);
        retakesList.addRetake(
                new retakeBean(
                        2,
                        "Walmart",
                        retakeDay2.getMonthValue(),
                        retakeDay2.getDayOfMonth(),
                        9,
                        0
                )
        );

        courseBean course = new courseBean(
                "swe437",
                "Software testing",
                "14",
                LocalDate.of(2019, 1, 21),
                LocalDate.of(2019, 1, 25),
                "/Data/Location"
        );

        String outputStream = runPrintQuizScheduleForm(quizList, retakesList, course);

        String quizString1 = String.format("Quiz 1 from %s, %s %d",
                quizDay.getDayOfWeek(),
                quizDay.getMonth(),
                quizDay.getDayOfMonth());

        String quizString2 = String.format("Quiz 2 from %s, %s %d",
                quizDay2.getDayOfWeek(),
                quizDay2.getMonth(),
                quizDay2.getDayOfMonth());


        // Observability:
        //  - Splits the output stream by the "RETAKE: " delimiter.
        //    This should result in three parts: the first is the irrelevant string
        //    before the first retake. The second part contains the first retake and the first quiz
        //    the third part contains the second retake and the second quiz. This helps the test oracle
        //    determine under which retake is a given quiz

        String retakeDelimiter = "RETAKE: ";
        String[] retakesStringList = outputStream.split(retakeDelimiter);

        assertTrue(
                String.format("The string \"%s\" should be under the first retake.", quizString1),
                retakesStringList[1].contains(quizString1)
        );

        assertTrue(
                String.format("The string \"%s\" should be under the second retake.", quizString2),
                retakesStringList[2].contains(quizString2)
        );

    }

}
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class quizschedTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPrintQuizScheduleForm() {
        LocalDate today = LocalDate.now();
        quizsched q = new quizsched();
        quizzes quizList = new quizzes();
        retakes retakesList = new retakes();
        courseBean course = new courseBean("1", "test", "test", today, today, null);
        q.printQuizScheduleForm(quizList, retakesList, course);

        assertEquals(System.getProperty("line.separator") + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + "GMU quiz retake scheduler for class test"
                + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + System.getProperty("line.separator") + "You can sign up for quiz retakes within the next two weeks. "
                + System.getProperty("line.separator") + "Enter your name (as it appears on the class roster), "
                + System.getProperty("line.separator")
                + "then select which date, time, and quiz you wish to retake from the following list."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "Today is TUESDAY, JULY 30" + System.getProperty("line.separator")
                + "Currently scheduling quizzes for the next two weeks, until " + today.plusDays(14).getDayOfWeek()
                + ", " + today.plusDays(14).getMonth() + " " + today.plusDays(14).getDayOfMonth()
                + System.getProperty("line.separator") + System.getProperty("line.separator"),
                outContent.toString());
    }

    @Test
    public void testPrintQuizScheduleForm2() {
        LocalDate today = LocalDate.now();
        quizsched q = new quizsched();
        quizzes quizList = new quizzes();
        retakes retakesList = new retakes();
        courseBean course = new courseBean("1", "test", "test", today, today.plusDays(30), null);
        q.printQuizScheduleForm(quizList, retakesList, course);

        assertEquals(System.getProperty("line.separator") + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + "GMU quiz retake scheduler for class test"
                + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + System.getProperty("line.separator") + "You can sign up for quiz retakes within the next two weeks. "
                + System.getProperty("line.separator") + "Enter your name (as it appears on the class roster), "
                + System.getProperty("line.separator")
                + "then select which date, time, and quiz you wish to retake from the following list."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "Today is TUESDAY, JULY 30" + System.getProperty("line.separator")
                + "Currently scheduling quizzes for the next two weeks, until " + today.plusDays(14).getDayOfWeek()
                + ", " + today.plusDays(21).getMonth() + " " + today.plusDays(21).getDayOfMonth()
                + System.getProperty("line.separator") + System.getProperty("line.separator"), outContent.toString());
    }

    @Test
    public void testPrintQuizScheduleForm3() {
        LocalDate today = LocalDate.now();
        quizsched q = new quizsched();
        quizzes quizList = new quizzes();
        retakes retakesList = new retakes();

        ArrayList<retakeBean> qrs = new ArrayList<retakeBean>();
        qrs.add(new retakeBean(1, "nowhere", 1, 1, 1, 1));
        retakesList.addRetake(qrs.get(0));
        courseBean course = new courseBean("1", "test", "test", today, today.plusDays(30), null);
        q.printQuizScheduleForm(quizList, retakesList, course);

        assertEquals(System.getProperty("line.separator") + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + "GMU quiz retake scheduler for class test"
                + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + System.getProperty("line.separator") + "You can sign up for quiz retakes within the next two weeks. "
                + System.getProperty("line.separator") + "Enter your name (as it appears on the class roster), "
                + System.getProperty("line.separator")
                + "then select which date, time, and quiz you wish to retake from the following list."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "Today is TUESDAY, JULY 30" + System.getProperty("line.separator")
                + "Currently scheduling quizzes for the next two weeks, until " + today.plusDays(14).getDayOfWeek()
                + ", " + today.plusDays(21).getMonth() + " " + today.plusDays(21).getDayOfMonth()
                + System.getProperty("line.separator") + System.getProperty("line.separator"), outContent.toString());
    }

}

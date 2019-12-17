package quizretakes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Random;

public class QuizSchedTests {

    quizsched qsched;
    retakes r;
    quizzes q;
    courseReader cr;
    courseBean cb;
    final String courseID = "quizretakes/course-swe437.xml";
    final String quizFile = "quizretakes/quiz-orig-swe437.xml";
    final String retakeFile = "quizretakes/quiz-retakes-swe437.xml";
    Method quizschedPrintQuizScheduleForm;
    PrintStream out;
    LocalDate cur;
    String expectedIntro;
    String expectedDateLine;
    String schedulingUntil;
    String name;
    ByteArrayOutputStream bt;

    @Before
    public void init(){
        cur = LocalDate.now();
        bt = new ByteArrayOutputStream();
        out = new PrintStream(bt);
        qsched = new quizsched();
        r = new retakes();
        q = new quizzes();
        cr = new courseReader();
        quizsched mquizsched;
        try {
            cb = cr.read(courseID);
            quizschedPrintQuizScheduleForm = qsched
                    .getClass()
                    .getDeclaredMethod("printQuizScheduleForm",
                            quizzes.class,
                            retakes.class,
                            courseBean.class);
            quizschedPrintQuizScheduleForm.setAccessible(true);
        }
        catch(Exception e){
            System.out.println("Something went wrong when it should not have!");
        }
        System.setOut(out);
        expectedIntro =
                "\n" +
                "\n" +
                "******************************************************************************\n" +
                "GMU quiz retake scheduler for class Software testing\n" +
                "******************************************************************************\n" +
                "\n" +
                "\n" +
                "You can sign up for quiz retakes within the next two weeks. \n" +
                "Enter your name (as it appears on the class roster), \n" +
                "then select which date, time, and quiz you wish to retake from the following list.\n" +
                "\n";
        expectedDateLine = "Today is " + cur.getDayOfWeek() + ", " + cur.getMonth() + " " + cur.getDayOfMonth() +"\n";
        schedulingUntil = "Currently scheduling quizzes for the next two weeks, until " +
                cur.plusWeeks(2).getDayOfWeek() + ", " + cur.plusWeeks(2).getMonth() + " " + cur.plusWeeks(2).getDayOfMonth();
    }

    //Observability: standard out is redirected into a variable in the @Before method, the captured output is
    //               compared to a constructed string of the expected output
    //Controllability:
    @Test
    public void validQuizRetakeTest(){
        quizBean qb;
        retakeBean rb;
        qb = new quizBean(4,2,19,10,30);
        rb = new retakeBean(4,"EB 4430",2,27,16,0);
        q.addQuiz(qb);
        r.addRetake(rb);
        String retakeString = "RETAKE: " + rb.getDayOfWeek() + ", " +rb.getMonth() + " " +rb.getDate().getDayOfMonth() + ", at " + rb.timeAsString() + " in " + rb.getLocation() + "\n";
        String quizString = "    1) Quiz " + qb.getID() + " from " + qb.getDayOfWeek() + ", " + qb.getMonth() + " " + qb.getDate().getDayOfMonth() + "\n";
        try {
            quizschedPrintQuizScheduleForm.invoke(qsched, q, r, cb);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        String completeExpected = expectedIntro + expectedDateLine + schedulingUntil + "\n" + retakeString + quizString + "\n";
        Assert.assertEquals(completeExpected, bt.toString());
    }

    //Observability: standard out is redirected into a variable in the @Before method, the captured output is
    //               compared to a constructed string of the expected output
    //Controllability: method behavior is controlled through the quizzes and retakes that are passed into it, which
    //                 in this case are too old to be shown as retakes that students can register for
    @Test
    public void expiredQuizRetakeTest(){
        Random rand = new Random();
        quizBean qb;
        retakeBean rb;
        qb = new quizBean(1,1,29,10,30);
        rb = new retakeBean(1,"EB 4430",1,30,15,30);
        q.addQuiz(qb);
        r.addRetake(rb);
        try {
            quizschedPrintQuizScheduleForm.invoke(qsched, q, r, cb);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        String completeExpected = expectedIntro + expectedDateLine + schedulingUntil + "\n\n";
        Assert.assertEquals(completeExpected, bt.toString());
    }

    //Observability: observes the method's behavior through a boolean variable that is initialized to false and set
    //               to true when an InvocationTargetException occurs that is caused by a NullPointerException
    //Controllability: Method behavior is controlled through the arguments passed in, with the primary argument
    //                 determining the behavior of this method being the list of quizzes, which is set to null
    @Test
    public void nullQuizListTest(){
        q = null;
        Random rand = new Random();
//        quizBean qb;
        retakeBean rb;
//        qb = new quizBean(1,1,29,10,30);
        LocalDate retakeDate = cur.plusWeeks(1);
        rb = new retakeBean(1,"EB 4430", retakeDate.getMonthValue(),retakeDate.getDayOfMonth(),15,30);
//        q.addQuiz(qb);
        r.addRetake(rb);
        boolean NPE = false;
        try {
            quizschedPrintQuizScheduleForm.invoke(qsched, q, r, cb);
        }
        catch (InvocationTargetException e){
            if(e.getCause() instanceof NullPointerException) {
                NPE = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        String completeExpected = expectedIntro + expectedDateLine + schedulingUntil + "\n\n";
        Assert.assertTrue("Expected NullPointerException, NPE was not thrown", NPE);
    }

    //Observability: observes the method's behavior through a boolean variable that is initialized to false and set
    //               to true when an InvocationTargetException occurs that is caused by a NullPointerException
    //Controllability: Method behavior is controlled through the arguments passed in, with the primary argument
    //                 determining the behavior of this method being the list of retakes, which is set to null
    @Test
    public void nullRetakeListTest(){
        r = null;
        LocalDate retakeDate = cur.plusWeeks(1);
        Random rand = new Random();
        quizBean qb;
        retakeBean rb;
        qb = new quizBean(1,retakeDate.getMonthValue(),retakeDate.getDayOfMonth(),10,30);
//        rb = new retakeBean(1,"EB 4430", retakeDate.getMonthValue(),retakeDate.getDayOfMonth(),15,30);
        q.addQuiz(qb);
//        r.addRetake(rb);
        boolean NPE = false;
        try {
            quizschedPrintQuizScheduleForm.invoke(qsched, q, r, cb);
        }
        catch (InvocationTargetException e){
            if(e.getCause() instanceof NullPointerException) {
                NPE = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        String completeExpected = expectedIntro + expectedDateLine + schedulingUntil + "\n\n";
        Assert.assertTrue("Expected NullPointerException, NPE was not thrown", NPE);
    }

    //Observability: observes the resulting state through a boolean variable that is initialized to false and set
    //               to true when an InvocationTargetException occurs that is caused by a NullPointerException
    //Controllability: Method behavior is controlled through the arguments passed in, with the primary argument
    //                 determining the behavior of this method being the course, which is set to null
    @Test
    public void nullCourseTest(){
        cb = null;
        LocalDate retakeDate = cur.plusWeeks(1);
        Random rand = new Random();
        quizBean qb;
        retakeBean rb;
        qb = new quizBean(1,cur.getMonthValue(),cur.getDayOfMonth(),10,30);
        rb = new retakeBean(1,"EB 4430", retakeDate.getMonthValue(),retakeDate.getDayOfMonth(),15,30);
        q.addQuiz(qb);
        r.addRetake(rb);
        boolean NPE = false;
        try {
            quizschedPrintQuizScheduleForm.invoke(qsched, q, r, cb);
        }
        catch (InvocationTargetException e){
            if(e.getCause() instanceof NullPointerException) {
                NPE = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        String completeExpected = expectedIntro + expectedDateLine + schedulingUntil + "\n\n";
        Assert.assertTrue("Expected NullPointerException, NPE was not thrown", NPE);
    }

    @After
    public void clean(){
        out.close();
    }

}

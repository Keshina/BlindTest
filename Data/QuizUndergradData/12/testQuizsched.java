//REVISED 27-Feb-2019


package quizretakes;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.io.*;




public class testQuizsched {
    quizsched mquizsched;
    Method quizschedPrintQuizScheduleForm;
    Method readQuizzes;
    Method readRetakes;
    Method readCourseFile;
    Method buildFileNames;
//    quizsched qSched = new quizsched();
//    private final LocalDate date = new LocalDate;
//    private String startS = "2019-01-21";
//    private String endS = "2019-01-25";
//    private LocalDate startSkip = LocalDate.parse(startS);
//    private LocalDate endSkip = LocalDate.parse(endS);
    private courseBean c1;
    private courseBean c2;
    private courseBean course_null = null;
    private retakes r1;
    private quizzes q1;
    private quizzes q_null = null;
    private retakes r_null = null;
    private String expected_message;
    private LocalDate today  = LocalDate.now();
    ByteArrayOutputStream outContent;
    PrintStream old;

    /**------------------------**/
    private static final String courseID = "swe437";
    private static final String courseID2 = "cs463";
    private static final String dataLocation = System.getProperty("user.dir") +"/" + "quizretakes/src/";

    private static final String separator    = ",";
    private static final String courseBase  = "course";
    private static final String quizzesBase  = "quiz-orig";
    private static final String retakesBase  = "quiz-retakes";
    private static final String apptsBase    = "quiz-appts";

// Filenames to be built from above and the c1

    private String courseFileName = dataLocation + courseBase  + "-" + courseID + ".xml";
    private String courseFileName2 = dataLocation + courseBase  + "-" + courseID2 + ".xml";
    private String quizzesFileName = dataLocation + quizzesBase + "-" + courseID + ".xml";
    private String quizzesFileName2 = dataLocation + quizzesBase + "-" + courseID2 + ".xml";
    private String retakesFileName = dataLocation + retakesBase + "-" + courseID + ".xml";
    private String retakesFileName2 = dataLocation + retakesBase + "-" + courseID2 + ".xml";
//public String apptsFileName = dataLocation + apptsBase   + "-" + courseID + ".txt";

    /**---------------------------------------------------*/
// Test values for retakeBeans, courseBeans, quizBeans


    /**--------------------------*/
//    //retakes data
    retakeBean rb_null = null;
    retakeBean rb_valid = new retakeBean(3,"Innovation 112",2,28,16,00);
    retakeBean rb_past = new retakeBean(3, "EB 5421", 2,01,16,00);
    retakeBean rb_skip = new retakeBean(3,"Buchanan 1101",3,17,11,00);
//    retakeBean rb_before_quiz_date = new retakeBean();
    retakeBean rb_after_last_day = new retakeBean(3, "Buchanan 1110", 3,25,11,00);


    /**--------------------------*/
    //quizzes data
    quizBean qb_null = null;
    quizBean qb_valid = new quizBean(6,2,22,16,00);
    quizBean qb_after_retakes = new quizBean(7,3,10,11,45);
    quizBean qb_before_retakes = new quizBean(6,2,01,11,00);
//    quizBean qb_skip = new quizBean();
//
    /**--------------------------*/
//    courses data
    courseBean cb_null = null;
//    courseBean cb_valid = new courseBean();
//    courseBean cb_no_quizzes = new courseBean();
//    courseBean cb_no_retakes = new courseBean();
//



    /**--------------------------*/


    public static void main(String args[]) throws IllegalAccessException, IOException, InvocationTargetException {

        org.junit.runner.JUnitCore.main("testQuizsched");
    }


    @Before
    public void setup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        mquizsched=new quizsched();

        quizschedPrintQuizScheduleForm = mquizsched
                .getClass()
                .getDeclaredMethod("printQuizScheduleForm",
                        quizzes.class,
                        retakes.class,
                        courseBean.class);
        quizschedPrintQuizScheduleForm.setAccessible(true);

        readQuizzes = mquizsched
                .getClass()
                .getDeclaredMethod("readQuizzes",
                        String.class);
        readQuizzes.setAccessible(true);

        readRetakes = mquizsched
                .getClass()
                .getDeclaredMethod("readRetakes",
                        String.class);
        readRetakes.setAccessible(true);

        readCourseFile = mquizsched
                .getClass()
                .getDeclaredMethod("readCourseFile",
                        String.class);
        readCourseFile.setAccessible(true);

        buildFileNames  = mquizsched
                .getClass()
                .getDeclaredMethod("buildFileNames",
                        String.class);
        buildFileNames.setAccessible(true);


        /**----------------------------------_*/
        buildFileNames.invoke(mquizsched,courseID);
        c1 = (courseBean) readCourseFile.invoke(mquizsched,courseID);
        c2 = (courseBean) readCourseFile.invoke(mquizsched,courseID2);
        q1 = (quizzes) readQuizzes.invoke(mquizsched,quizzesFileName);
        r1 = (retakes) readRetakes.invoke(mquizsched,retakesFileName);
        int daysAvailable = Integer.parseInt(c1.getRetakeDuration());
        LocalDate endDay = today.plusDays((daysAvailable));
        expected_message ="\n" + "\n" +
                "******************************************************************************\n" +
                "GMU quiz retake scheduler for class " + c1.getCourseTitle() + "\n" +
                "******************************************************************************\n" +
                "\n" +
                "\n" +
                "You can sign up for quiz retakes within the next two weeks. \n" +
                "Enter your name (as it appears on the class roster), \n" +
                "then select which date, time, and quiz you wish to retake from the following list.\n" +
                "\n" +
                "Today is "+ (today.getDayOfWeek()) + ", " + today.getMonth() + " " + today.getDayOfMonth()+"\n" +
                "Currently scheduling quizzes for the next two weeks, until "+endDay.getDayOfWeek()+", " +endDay.getMonth() +" " + endDay.plusDays(7).getDayOfMonth() +"\n";
    }


    @After
    public void after__each_test() {
        c1 =null;
        r1=null;
        q1=null;
    }

    @Test
    public void check_class_name() throws InvocationTargetException, IllegalAccessException, IOException {
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString(); //observability
        assertTrue("Class name not valid",contains_class(output_from_function,c1)); //observability
    }

    @Test
    public void test_quiz() throws NullPointerException, InvocationTargetException, IllegalAccessException {
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString(); //observability
        String expected = expected_message +
                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" +
                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
                "    3) Quiz 5 from TUESDAY, MARCH 5\n\n";
        assertTrue("Initial data for retakes incorrect from XML",expected.equals(output_from_function)); //observability
    }


    @Test
    public void test_null_quizzes() throws NullPointerException {
        outputStream();
        try {
            quizschedPrintQuizScheduleForm.invoke(mquizsched, q_null, r1, c1); //controllability
        }catch (Throwable e){
            assertEquals(InvocationTargetException.class,e.getClass());
        }
    }

    @Test
    public void test_null_retakes() {
        outputStream();
        try {
            quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r_null, c1); //controllability
        }catch (Throwable e){
            assertEquals(InvocationTargetException.class,e.getClass()); //observability
        }
    }

    @Test
    public void test_null_course() {
        outputStream();
        try {
            quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, course_null); //controllability
        }catch (Throwable e){
            assertEquals(InvocationTargetException.class,e.getClass()); //observability
        }
    }

    @Test
    public void test_valid_retake() throws InvocationTargetException, IllegalAccessException {
        r1.addRetake(rb_valid); //controllability
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString(); //observability
        String expected = expected_message + //observability
                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" +
                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
                "    3) Quiz 5 from TUESDAY, MARCH 5\n" +
                "RETAKE: THURSDAY, FEBRUARY 28, at 16:00 in Innovation 112\n" +
                "    4) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
                "    5) Quiz 4 from TUESDAY, FEBRUARY 26\n\n";
        assertEquals(expected,output_from_function); //observability
    }

    @Test
    public void test_past_retake() throws InvocationTargetException, IllegalAccessException {
        r1.addRetake(rb_past); //controllability
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString();
        String expected = expected_message + //observability
                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" +
                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
                "    3) Quiz 5 from TUESDAY, MARCH 5\n\n";
        assertEquals(expected,output_from_function); //observability
    }

    /******--------------------------------------*/

    @Test
    public void test_future_retake() throws InvocationTargetException, IllegalAccessException {
        r1.addRetake(rb_after_last_day); //controllability
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString();
        String expected = expected_message +
                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" + //observability
                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
                "    3) Quiz 5 from TUESDAY, MARCH 5\n\n";
        assertEquals(expected,output_from_function); //observability
    }

    @Test
    public void test_past_quiz() throws InvocationTargetException, IllegalAccessException {
        q1.addQuiz(qb_before_retakes); //controllability
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString();
        String expected = expected_message +
                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" + //observability
                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
                "    3) Quiz 5 from TUESDAY, MARCH 5\n\n";
        assertEquals(expected,output_from_function); //observability
    }

    @Test
    public void test_skip_week_retake() throws InvocationTargetException, IllegalAccessException {
        r1.addRetake(rb_skip); //controllability
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString(); //observability
        assertFalse("Offered a quiz during a skip week!",printed_retake(rb_skip,output_from_function)); //observability
    }
    @Test
    public void test_valid_quiz() throws InvocationTargetException, IllegalAccessException {
        q1.addQuiz(qb_valid); //controllability
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString(); //observability
        assertTrue(contains_quiz(qb_valid,output_from_function)); //observability
    }

    @Test
    public void test_future_quiz() throws InvocationTargetException, IllegalAccessException {
        q1.addQuiz(qb_after_retakes); //controllability
        outputStream();
        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1); //controllability
        System.setOut(old);
        String output_from_function=outContent.toString(); //observability
        assertFalse(contains_quiz(qb_after_retakes,output_from_function)); //observability
    }
//
//    public void test_valid_retake_to_empty_course() throws InvocationTargetException, IllegalAccessException {
//        outputStream();
//        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1);
//        System.setOut(old);
//        String output_from_function=outContent.toString();
//        String expected = expected_message +
//                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" +
//                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
//                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
//                "    3) Quiz 5 from TUESDAY, MARCH 5\n\n";
//        assertEquals(expected,output_from_function);
//        r1.addRetake(rb_valid);
//        outputStream();
//        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1);
//        System.setOut(old);
//        output_from_function=outContent.toString();
//        expected = expected_message +
//                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" +
//                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
//                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
//                "    3) Quiz 5 from TUESDAY, MARCH 5\n" +
//                "RETAKE: THURSDAY, FEBRUARY 28, at 16:00 in Innovation 112\n" +
//                "    4) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
//                "    5) Quiz 4 from TUESDAY, FEBRUARY 26\n\n";
//        assertEquals(expected,output_from_function);
//    }
//
//    public void test_valid_quiz_to_empty_course() throws InvocationTargetException, IllegalAccessException {
//        outputStream();
//        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1);
//        System.setOut(old);
//        String output_from_function=outContent.toString();
//        String expected = expected_message +
//                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" +
//                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
//                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
//                "    3) Quiz 5 from TUESDAY, MARCH 5\n\n";
//        assertEquals(expected,output_from_function);
//        r1.addRetake(rb_valid);
//        outputStream();
//        quizschedPrintQuizScheduleForm.invoke(mquizsched, q1, r1, c1);
//        System.setOut(old);
//        output_from_function=outContent.toString();
//        expected = expected_message +
//                "RETAKE: TUESDAY, MARCH 5, at 16:00 in Innovation 112\n" +
//                "    1) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
//                "    2) Quiz 4 from TUESDAY, FEBRUARY 26\n" +
//                "    3) Quiz 5 from TUESDAY, MARCH 5\n" +
//                "RETAKE: THURSDAY, FEBRUARY 28, at 16:00 in Innovation 112\n" +
//                "    4) Quiz 3 from TUESDAY, FEBRUARY 19\n" +
//                "    5) Quiz 4 from TUESDAY, FEBRUARY 26\n\n";
//        assertEquals(expected,output_from_function);
//    }


    /**-------------------------------------------------*/

//
//    public boolean seq(String a, String b) {
//        return a.equals(b);
//
//    }

    /*reads the output from the screen
     */
    private void outputStream(){
        outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        old = System.out;
        System.setOut(ps);
    }

    /*
    checks to see if the passed class was printed to the screen
    */
    private boolean contains_class(String screen_output, courseBean c)
    {
        return (screen_output.contains(c.getCourseTitle()));

    }

    /*
    checks to see if the expected quiz was actually printed to the screen
     */
    private boolean printed_quiz(quizBean q, String screen_output)
    {
        return screen_output.contains(Integer.toString(q.getID()) );
    }

    /*
    checks to see if the passed retake was printed to the screen
     */
    private boolean printed_retake(retakeBean r,String screen_output)
    {
        return screen_output.contains(r.getLocation()) && screen_output.contains(r.getDayOfWeek().toString())
                &&screen_output.contains(r.timeAsString());
    }

    /*
   checks to see if the passed quiz was printed to the screen
    */
    private boolean contains_quiz(quizBean q, String screen_output)
    {
        return screen_output.contains(Integer.toString(q.getID()))&& screen_output.contains(q.timeAsString());
    }


}//end of class

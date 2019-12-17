import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class quizschedTest {
    
    ByteArrayOutputStream sys;
    PrintStream ps;
    
    
    quizsched quizsched;
    
    quizzes quizList;
    
    retakes retakesList;
    
    
    
    courseBean course;
    String courseID = "Tst101";
    String courseTitle = "Testing Software";
    String retakeDuration = "2 weeks";
    LocalDate startSkip = LocalDate.of(2019,01,21);
    LocalDate endSkip = LocalDate.of(2019,01,25); 
    String dataLocation = "Dont worry about it";
    
    
    quizBean quizbean1 =  new quizBean(1, 2, 26, 1, 0);
    quizBean quizbean2 =  new quizBean(2, 2, 27, 1, 0);
    quizBean quizbean3 =  new quizBean(3, 2, 28, 1, 0);
    
    

    retakeBean retake1 = new retakeBean(1, "EB 100", 2, 27, 1, 0);
    retakeBean retake2 = new retakeBean(2, "EB 200", 2, 28, 1, 0);
    retakeBean retake3 = new retakeBean(3, "EB 300", 3, 1, 1, 0);
    
    /**
     * @throws java.lang.Exception
     */
     
    public String readFile(String filename) {
    try {
      Scanner sc = new Scanner(new File(filename));
      StringBuilder s = new StringBuilder();
      while (sc.hasNextLine()){
        s.append(sc.nextLine()+"\n");
      }
      return s.toString();
    }
    catch (FileNotFoundException e){
      throw new AssertionError("can't read the missing file '"+filename+"'"); 
    }
    }
 
    /**
     * Clears all objects into a new state before each test.
     * Each test must add quizzes or retakes
     */
    @Before
    public void setUp()  {
                      //sets up a quizlist with no quizes in it must be added later
        quizList = new quizzes();
        //creates a new course 
        course   = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
        //creates a new re-take with no re-takes inside must be added in later
        retakesList = new retakes();
        //creates a new and quizesched 
        quizsched = new quizsched();
        
        //used to read system.out or put in
        sys = new ByteArrayOutputStream();
        ps = new PrintStream(sys);
        System.setOut(ps);
        
    }
   

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        quizList = null;
        course   = null;
        retakesList = null;
        quizsched = null;
    }
    
	@Test public void testAllNull(){
		try{
		  quizsched.printQuizScheduleForm(null, null, null);
		  fail("Expected a NullPointerException");
		} 
		catch (Exception e){
		  assertTrue("", true);
		}
	} 
    
    @Test //observability
    public void TestAll(){
        
        quizList.addQuiz(quizbean1);
        quizList.addQuiz(quizbean2);
        quizList.addQuiz(quizbean3);
        
        
        retakesList.addRetake(retake1);
        retakesList.addRetake(retake2);
        retakesList.addRetake(retake3);
        
        quizsched.printQuizScheduleForm(quizList, retakesList, course);
	    System.out.flush();
	    assertEquals("******************************************************************************\r\n" + 
	    		"GMU quiz retake scheduler for class Testing Software\r\n" + 
	    		"******************************************************************************\r\n" + 
	    		"\r\n" + 
	    		"\r\n" + 
	    		"You can sign up for quiz retakes within the next two weeks. \r\n" + 
	    		"Enter your name (as it appears on the class roster), \r\n" + 
	    		"then select which date, time, and quiz you wish to retake from the following list.\r\n" + 
	    		"\r\n" + 
	    		"Today is WEDNESDAY, FEBRUARY 27\r\n" + 
	    		"Currently scheduling quizzes for the next two weeks, until WEDNESDAY, MARCH 13\r\n" + 
	    		"RETAKE: WEDNESDAY, FEBRUARY 27, at 01:00 in EB 100\r\n" + 
	    		"    1) Quiz 1 from TUESDAY, FEBRUARY 26\r\n" + 
	    		"    2) Quiz 2 from WEDNESDAY, FEBRUARY 27\r\n" + 
	    		"RETAKE: THURSDAY, FEBRUARY 28, at 01:00 in EB 200\r\n" + 
	    		"    3) Quiz 1 from TUESDAY, FEBRUARY 26\r\n" + 
	    		"    4) Quiz 2 from WEDNESDAY, FEBRUARY 27\r\n" + 
	    		"    5) Quiz 3 from THURSDAY, FEBRUARY 28\r\n" + 
	    		"RETAKE: FRIDAY, MARCH 1, at 01:00 in EB 300\r\n" + 
	    		"    6) Quiz 1 from TUESDAY, FEBRUARY 26\r\n" + 
	    		"    7) Quiz 2 from WEDNESDAY, FEBRUARY 27\r\n" + 
	    		"    8) Quiz 3 from THURSDAY, FEBRUARY 28",sys.toString().trim());
    }
    
    	
    
    
    @Test //observability
    public void TestRetakesList(){
    	
        retakesList.addRetake(retake1);
        retakesList.addRetake(retake2);
        retakesList.addRetake(retake3);
        
		quizsched.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		assertEquals("******************************************************************************\r\n" + 
				"GMU quiz retake scheduler for class Testing Software\r\n" + 
				"******************************************************************************\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"You can sign up for quiz retakes within the next two weeks. \r\n" + 
				"Enter your name (as it appears on the class roster), \r\n" + 
				"then select which date, time, and quiz you wish to retake from the following list.\r\n" + 
				"\r\n" + 
				"Today is WEDNESDAY, FEBRUARY 27\r\n" + 
				"Currently scheduling quizzes for the next two weeks, until WEDNESDAY, MARCH 13\r\n" + 
				"RETAKE: WEDNESDAY, FEBRUARY 27, at 01:00 in EB 100\r\n" + 
				"RETAKE: THURSDAY, FEBRUARY 28, at 01:00 in EB 200\r\n" + 
				"RETAKE: FRIDAY, MARCH 1, at 01:00 in EB 300", sys.toString().trim());
    }
    
    @Test public void TestPrintPastTwoWeeks() {
    	quizList.addQuiz(quizbean1);
        quizList.addQuiz(quizbean2);
        quizList.addQuiz(quizbean3);
        
        
        retakesList.addRetake(retake1);
        retakesList.addRetake(retake2);
        retakesList.addRetake(retake3);
        
        quizBean quizbean3 =  new quizBean(3, 9, 28, 1, 0);
        
        quizsched.printQuizScheduleForm(quizList, retakesList, course);
	    System.out.flush();
	    assertEquals("******************************************************************************\r\n" + 
	    		"GMU quiz retake scheduler for class Testing Software\r\n" + 
	    		"******************************************************************************\r\n" + 
	    		"\r\n" + 
	    		"\r\n" + 
	    		"You can sign up for quiz retakes within the next two weeks. \r\n" + 
	    		"Enter your name (as it appears on the class roster), \r\n" + 
	    		"then select which date, time, and quiz you wish to retake from the following list.\r\n" + 
	    		"\r\n" + 
	    		"Today is WEDNESDAY, FEBRUARY 27\r\n" + 
	    		"Currently scheduling quizzes for the next two weeks, until WEDNESDAY, MARCH 13\r\n" + 
	    		"RETAKE: WEDNESDAY, FEBRUARY 27, at 01:00 in EB 100\r\n" + 
	    		"    1) Quiz 1 from TUESDAY, FEBRUARY 26\r\n" + 
	    		"    2) Quiz 2 from WEDNESDAY, FEBRUARY 27\r\n" + 
	    		"RETAKE: THURSDAY, FEBRUARY 28, at 01:00 in EB 200\r\n" + 
	    		"    3) Quiz 1 from TUESDAY, FEBRUARY 26\r\n" + 
	    		"    4) Quiz 2 from WEDNESDAY, FEBRUARY 27\r\n" + 
	    		"    5) Quiz 3 from THURSDAY, FEBRUARY 28\r\n" + 
	    		"RETAKE: FRIDAY, MARCH 1, at 01:00 in EB 300\r\n" + 
	    		"    6) Quiz 1 from TUESDAY, FEBRUARY 26\r\n" + 
	    		"    7) Quiz 2 from WEDNESDAY, FEBRUARY 27\r\n" + 
	    		"    8) Quiz 3 from THURSDAY, FEBRUARY 28",sys.toString().trim());
        
    }
    
    @Test //observability
    public void TestNoQuizzes() {

        quizsched.printQuizScheduleForm(quizList, retakesList, course);
        System.out.flush();
        
        assertEquals("******************************************************************************\r\n" + 
                "GMU quiz retake scheduler for class Testing Software\r\n" + 
                "******************************************************************************\r\n" + 
                "\r\n" + 
                "\r\n" + 
                "You can sign up for quiz retakes within the next two weeks. \r\n" + 
                "Enter your name (as it appears on the class roster), \r\n" + 
                "then select which date, time, and quiz you wish to retake from the following list.\r\n" + 
                "\r\n" + 
                "Today is WEDNESDAY, FEBRUARY 27\r\n" + 
                "Currently scheduling quizzes for the next two weeks, until WEDNESDAY, MARCH 13",sys.toString().trim());
    }
    
    

    

}

import org.junit.*;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;



public class quizScheduleTest
{
	quizschedule mquizsched;
	quizzes quizList;
	retakes retakesList;
	courseBean course;
	LocalDate today  = LocalDate.now();
	
	
	@Before 
	public void initialize() throws NoSuchMethodException, SecurityException 
	{
		 mquizsched = new quizschedule();
	}
	
	@After 
	public void after()
	{
		mquizsched = null;
		quizList = null;
		retakesList = null;
		course = null;
	}
	
	private String getLog(quizzes quizList, retakes retakesList, courseBean course)
	{
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(outContent);
		PrintStream old = System.out;
		System.setOut(ps);
		mquizsched.printQuizScheduleForm(quizList, retakesList, course);
		System.setOut(old);
		return outContent.toString();
		
	}
	
	// Checks if all the inputs are NULL
	// This method addresses observability because the inputs cannot be entered by the user.
	// The user can only observe the behavior of the program.
	@Test (expected = NullPointerException.class)
	public void firstTest() 
	{   
		
        mquizsched.printQuizScheduleForm(null, null, null);

	}
		
	// Checks if quizList is NULL
	// This is also addressing observability.
	// In the test, the retakes, quizzes, and courseBean objects are being created with
	// inputs already entered.
	@Test (expected = NullPointerException.class)
	public void twoTest() 
	{ 
		retakesList = new retakes(1, "JC", 3 , 1, 2, 1);
		course = new courseBean("swe437", "Testing", "2 Weeks", LocalDate.of(2019, 05, 16), LocalDate.of(2019, 05, 17), "");			
		mquizsched.printQuizScheduleForm(null, retakesList, course);

	}
		
	// Checks if retakeList is NULL
	// Also observability. The tests that are checking to see if the objects are NULL are
	// tests that the user cannot do anything about.
	@Test (expected = NullPointerException.class)
	public void thirdTest() 
	{   
		quizList = new quizzes(10, 2, 27, 5, 1);
		course = new courseBean("swe437", "Testing", "2 Weeks", LocalDate.of(2019, 05, 16), LocalDate.of(2019, 05, 17), "");	
		mquizsched.printQuizScheduleForm(quizList, null, course);
				
	}
	
	// Checks if course is NULL
	// This is also observability. Final check for NULL,
	// users cannot do anything about inputs.
	@Test (expected = NullPointerException.class)
	public void fourthTest() 
	{   
		quizList = new quizzes(10, 2, 27, 5, 1);
		retakesList = new retakes(1, "JC", 3 , 1, 2, 1);
		mquizsched.printQuizScheduleForm(quizList, retakesList, null);
				
	}
	
	// checks if everyone add the right info with no break
	// The observability portion of this test are the quizzes, retakes, courseBean objects being created.
	// The controllabilty is when the user has to enter info that matches the criteria of this test.
	@Test 
	public void fifthTest() 
	{
		quizList = new quizzes(10, 2, 27, 5, 1);
		retakesList = new retakes(1, "JC", 3 , 1, 2, 1);
		course = new courseBean("swe437", "Testing", "2 Weeks", LocalDate.of(2019, 05, 16), LocalDate.of(2019, 05, 17), "");
		String res = getLog(quizList, retakesList, course);
		assertTrue(res.contains("Today is " + today.getDayOfWeek()));
		assertTrue(res.contains("RETAKE: FRIDAY"));
		assertFalse(res.contains("Skipping a week, no quiz or retakes."));
		assertTrue(res.contains("Currently scheduling quizzes for the next two weeks, until "+ (today.plusDays(14).getDayOfWeek() + "," )));
	}
		
	// checks if everyone add the right info with break and retake is after 14 days
	// Similarly, observability are the objects that are manually being made in the test
	// and the controllability is making sure the user fits the criteria of retakes can occur
	// up to 2 weeks and to not schedule a retake on a week where it is being skipped.
	@Test
	public void sixthTest() 
	{
		quizList = new quizzes(10, 2, 27, 5, 1);
		retakesList = new retakes(1, "JC", 3 , 20, 2, 1);
		course = new courseBean("swe437", "Testing", "2 Weeks", LocalDate.of(2019, 03, 11), LocalDate.of(2019, 03, 15), "");
		String res = getLog(quizList, retakesList, course);
		assertTrue(res.contains("Skipping a week, no quiz or retakes."));
		assertTrue(res.contains("RETAKE: WEDNESDAY"));
		assertTrue(res.contains("Currently scheduling quizzes for the next two weeks, until "+ (today.plusDays(21).getDayOfWeek() + "," )));
	}
	
	// checks if everyone add the right info with break and retake is not after 14 days
	// Observability are the objects manually being entered while the controllability
	// is checking that the user's retake falls within the criteria and skipped weeks.
	@Test
	public void seventhTest() 
	{
		quizList = new quizzes(10, 2, 27, 5, 1);
		retakesList = new retakes(1, "JC", 3 , 1, 2, 1);
		course = new courseBean("swe437", "Testing", "2 Weeks", LocalDate.of(2019, 03, 11), LocalDate.of(2019, 03, 15), "");
		String res = getLog(quizList, retakesList, course);
		assertFalse(res.contains("Skipping a week, no quiz or retakes."));
		assertTrue(res.contains("RETAKE: FRIDAY"));
		assertTrue(res.contains("Currently scheduling quizzes for the next two weeks, until "+ (today.plusDays(14).getDayOfWeek() + "," )));
	}
	
	// checks retake is before orgin date
	@Test 
	public void eigthTest() 
	{
		quizList = new quizzes(10, 2, 27, 5, 1);
		retakesList = new retakes(1, "JC", 2 , 25, 2, 1);
		course = new courseBean("swe437", "Testing", "2 Weeks", LocalDate.of(2019, 05, 16), LocalDate.of(2019, 05, 17), "");
		String res = getLog(quizList, retakesList, course);
		assertTrue(res.contains("Today is " + today.getDayOfWeek()));
		assertFalse(res.contains("RETAKE: MONDAY"));
		assertFalse(res.contains("Skipping a week, no quiz or retakes."));
	}
}
	
	

package quizretakes;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * |-----------------------------------------------------------------|
 * | printQuizScheduleForm WAS CHANGED TO PUBLIC FOR EASE OF TESTING |
 * |-----------------------------------------------------------------|
 */

public class quizschedTest {

	quizzes quiz;
	retakes retake;
	courseBean course;
	
	int quizCounter, retakeCounter;
	final int hour = 10, minute = 0;
	LocalDate date;
	java.io.ByteArrayOutputStream out;
	PrintStream stdout = System.out;
	
	//setting up quizzes, retakes, and a course to be used in each test.
	//this is controllability
	@Before
	public void setup() {
		quizCounter = 1;
		retakeCounter = 1;
		date = LocalDate.now().minusDays(1);
		
		//set up core quizzes
		quiz = new quizzes();
		//yesterday
		quiz.addQuiz(new quizBean(quizCounter++, date.getMonthValue(), date.getDayOfMonth(), hour, minute));
		date = date.minusDays(7);
		//8 days ago
		quiz.addQuiz(new quizBean(quizCounter++, date.getMonthValue(), date.getDayOfMonth(), hour, minute));
		date = date.minusDays(7);
		//15 days ago, should never be valid
		quiz.addQuiz(new quizBean(quizCounter++, date.getMonthValue(), date.getDayOfMonth(), hour, minute));
		
		//set up core retakes
		retake = new retakes();
		date = date.plusDays(14); //yesterday, should not show up.
		retake.addRetake(new retakeBean(retakeCounter++, "ENGR 5358", date.getMonthValue(), date.getDayOfMonth(), hour, minute));
		date = date.plusDays(2); //tomorrow
		retake.addRetake(new retakeBean(retakeCounter++, "ENGR 5358", date.getMonthValue(), date.getDayOfMonth(), hour, minute));
		date = date.plusDays(7); //8 days from now
		retake.addRetake(new retakeBean(retakeCounter++, "ENGR 5358", date.getMonthValue(), date.getDayOfMonth(), hour, minute));
		
		//set up course info
		course = new courseBean("swe437", "Software Testing and Maintentance", "14",
				LocalDate.now().plusDays(7), LocalDate.now().plusDays(14), "no/where");
		
		//redirect standard out to a readable stream.
		out = new java.io.ByteArrayOutputStream();
	    System.setOut(new java.io.PrintStream(out));
	    out.reset();
		
		date = LocalDate.now();
	}
	
	@Test //test date extension when skip is set
	public void testSkipExtension() {
		date = date.plusDays(21); //end date with skip set
		//controllability
		String endDateString = (date.getDayOfWeek()) + ", " + date.getMonth() + " " + date.getDayOfMonth();
		
		quizsched.printQuizScheduleForm(quiz, retake, course);
		//observability
		assertTrue("Skip boolean not thrown.",out.toString().contains(endDateString));
	}
	
	@Test //test that the date is not extended unnecessarily
	public void testNoSkipExtension() {
		date = date.plusDays(14); //end date with skip set
		//controllability
		course = new courseBean("swe437", "Software Testing and Maintentance", "14",
				LocalDate.now().plusDays(21), LocalDate.now().plusDays(28), "no/where");
		String endDateString = (date.getDayOfWeek()) + ", " + date.getMonth() + " " + date.getDayOfMonth();
		
		quizsched.printQuizScheduleForm(quiz, retake, course);
		//observability
		assertTrue("Skip boolean not thrown.",out.toString().contains(endDateString));
	}
	
	@Test //test printing retakes when in range
	public void testRetakesAppearing() {
		date = date.plusDays(1);
		//controllability
		String retakeString = ("RETAKE: " + date.getDayOfWeek() + ", " +
				date.getMonth() + " " +
				date.getDayOfMonth() + ", at " +
				LocalTime.of (hour, minute) + " in " +
                "ENGR 5358");
		
		quizsched.printQuizScheduleForm(quiz, retake, course);
		//observability
		assertTrue("Retakes did not appear.", out.toString().contains(retakeString));
	}
	
	@Test //test that retakes out of range are NOT printed
	public void testRetakesNotAppearing() {
		date = date.minusDays(1);
		//controllability
		String retakeString = ("RETAKE: " + date.getDayOfWeek() + ", " +
				date.getMonth() + " " +
				date.getDayOfMonth() + ", at " +
				LocalTime.of (hour, minute) + " in " +
                "ENGR 5358");
		
		quizsched.printQuizScheduleForm(quiz, retake, course);
		//observability
		assertTrue("Retakes appeared incorrectly.", !out.toString().contains(retakeString));
	}
	
	@Test //test that quizzes in range are printed
	public void testQuizzesAppearing() {
		date = date.minusDays(1);
		//controllability
		String quizString = ("Quiz " + 1 + " from " + date.getDayOfWeek() +
				", " + date.getMonth() + " " + date.getDayOfMonth() );
		
		quizsched.printQuizScheduleForm(quiz, retake, course);
		//observability
		int occurances = out.toString().split(quizString).length - 1;
		assertTrue("Quizzes did not appear properly.", occurances == 2);
	}
	
	@Test //test that quizzes out of range are NOT printed
	public void testQuizzesNotAppearing() {
		date = date.minusDays(1);
		//controllability
		String retakeString = ("RETAKE: " + date.getDayOfWeek() + ", " +
				date.getMonth() + " " +
				date.getDayOfMonth() + ", at " +
				LocalTime.of (hour, minute) + " in " +
                "ENGR 5358");
		
		quizsched.printQuizScheduleForm(quiz, retake, course);
		//observability
		assertTrue("Retakes did not appear.", !out.toString().contains(retakeString));
	}
}

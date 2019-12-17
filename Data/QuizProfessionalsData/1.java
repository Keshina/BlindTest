

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.time.LocalDate;

public class quizschedtest {
	
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	quizsched q;
	
	
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
	public void printQuizScheduleFormTestNoSkip(){
	
		q  = new quizsched();
		quizzes quizList = new quizzes();
		retakes retakeList = new retakes();
		courseBean course = new courseBean("Bio", "Bio", "Bio", LocalDate.now(), LocalDate.now().plusDays(10), "fairfax");
		
		q.printQuizScheduleForm(quizList, retakeList, course);
		
		assertEquals(
                System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + "GMU quiz retake scheduler for class Bio"
                + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "You can sign up for quiz retakes within the next two weeks. "
                + System.getProperty("line.separator") + "Enter your name (as it appears on the class roster), "
                + System.getProperty("line.separator")
                + "then select which date, time, and quiz you wish to retake from the following list."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "Today is " + LocalDate.now().getDayOfWeek() + ", " + LocalDate.now().getMonth() + " " + LocalDate.now().getDayOfMonth() + System.getProperty("line.separator") 
                + "Currently scheduling quizzes for the next two weeks, until " + (LocalDate.now().plusDays(14).getDayOfWeek()) + ", " + LocalDate.now().plusDays(14).getMonth() + " " + LocalDate.now().plusDays(14).getDayOfMonth() + System.getProperty("line.separator") 
                + System.getProperty("line.separator"), outContent.toString());
	}
	
	@Test
	public void printQuizScheduleFormTestFullCoverage(){
	
		q  = new quizsched();
		quizzes quizList = new quizzes(123, LocalDate.now().plusDays(8).getMonthValue(), LocalDate.now().plusDays(8).getDayOfMonth(), 12, 0);
		retakes retakeList = new retakes(123, "fairfax", LocalDate.now().plusDays(19).getMonthValue(), LocalDate.now().plusDays(19).getDayOfMonth(), 12, 0);
		courseBean course = new courseBean("Bio", "Bio", "Bio", LocalDate.now(), LocalDate.now().plusDays(30), "fairfax");
		
		q.printQuizScheduleForm(quizList, retakeList, course);
		
		assertEquals(
                System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + "GMU quiz retake scheduler for class Bio"
                + System.getProperty("line.separator")
                + "******************************************************************************"
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "You can sign up for quiz retakes within the next two weeks. "
                + System.getProperty("line.separator") + "Enter your name (as it appears on the class roster), "
                + System.getProperty("line.separator")
                + "then select which date, time, and quiz you wish to retake from the following list."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "Today is " + LocalDate.now().getDayOfWeek() + ", " + LocalDate.now().getMonth() + " " + LocalDate.now().getDayOfMonth() + System.getProperty("line.separator") 
                + "Currently scheduling quizzes for the next two weeks, until " + (LocalDate.now().plusDays(21).getDayOfWeek()) + ", " + LocalDate.now().plusDays(21).getMonth() + " " + LocalDate.now().plusDays(21).getDayOfMonth() + System.getProperty("line.separator") 
                + "      Skipping a week, no quiz or retakes." + System.getProperty("line.separator")
                + "RETAKE: " + (LocalDate.now().plusDays(19).getDayOfWeek()) + ", " + LocalDate.now().plusDays(19).getMonth() + " " + LocalDate.now().plusDays(19).getDayOfMonth()  + ", at 12:00 in fairfax" + System.getProperty("line.separator")
                + "    1) Quiz 123 from " + (LocalDate.now().plusDays(8).getDayOfWeek()) + ", " + LocalDate.now().plusDays(8).getMonth() + " " + LocalDate.now().plusDays(8).getDayOfMonth()+ System.getProperty("line.separator") + System.getProperty("line.separator"), 
                outContent.toString());
	}
	

}

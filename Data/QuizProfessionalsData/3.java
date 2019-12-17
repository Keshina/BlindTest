

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class quizSchedTest {

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
	public void printQuizScheduleFormEmptyRetake() {
		quizsched q = new quizsched();
		quizzes quiz = new quizzes();
		retakes retake = new retakes();
		courseBean course = new courseBean(null, null, null, LocalDate.now().plusDays(16), LocalDate.now().plusDays(16),
				null);
		q.printQuizScheduleForm(quiz, retake, course);
		assertEquals(
				System.getProperty("line.separator") + System.getProperty("line.separator")
						+ "******************************************************************************"
						+ System.getProperty("line.separator") + "GMU quiz retake scheduler for class null"
						+ System.getProperty("line.separator")
						+ "******************************************************************************"
						+ System.getProperty("line.separator") + System.getProperty("line.separator")
						+ System.getProperty("line.separator")
						+ "You can sign up for quiz retakes within the next two weeks. "
						+ System.getProperty("line.separator") + "Enter your name (as it appears on the class roster), "
						+ System.getProperty("line.separator")
						+ "then select which date, time, and quiz you wish to retake from the following list."
						+ System.getProperty("line.separator") + System.getProperty("line.separator")
						+ "Today is " + LocalDate.now().getDayOfWeek() + ", " + LocalDate.now().getMonth() + " "
						+ LocalDate.now().getDayOfMonth() + System.getProperty("line.separator")
						+ "Currently scheduling quizzes for the next two weeks, until "
						+ LocalDate.now().plusDays(14).getDayOfWeek() + ", " + LocalDate.now().plusDays(14).getMonth()
						+ " " + LocalDate.now().plusDays(14).getDayOfMonth()
						+ System.getProperty("line.separator") + System.getProperty("line.separator"),
				outContent.toString());
	}

	@Test
	public void printQuizScheduleFormOneQuiz() {
		quizsched q = new quizsched();
		String str = "2015-03-15";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateTime = LocalDate.parse(str, formatter);
		courseBean course = new courseBean(null, null, null, dateTime, LocalDate.now().plusDays(16), null);
		retakes retake = new retakes(3, "test", LocalDate.now().plusDays(20).getMonthValue(),
				LocalDate.now().plusDays(20).getDayOfMonth(), 20, 20);
		quizzes quiz = new quizzes(10, LocalDate.now().plusDays(8).getMonthValue(),
				LocalDate.now().plusDays(8).getDayOfMonth(), 10, 10);
		q.printQuizScheduleForm(quiz, retake, course);
		assertEquals(System.getProperty("line.separator") + System.getProperty("line.separator")
				+ "******************************************************************************"
				+ System.getProperty("line.separator") + "GMU quiz retake scheduler for class null"
				+ System.getProperty("line.separator")
				+ "******************************************************************************"
				+ System.getProperty("line.separator") + System.getProperty("line.separator")
				+ System.getProperty("line.separator") + "You can sign up for quiz retakes within the next two weeks. "
				+ System.getProperty("line.separator") + "Enter your name (as it appears on the class roster), "
				+ System.getProperty("line.separator")
				+ "then select which date, time, and quiz you wish to retake from the following list."
				+ System.getProperty("line.separator") + System.getProperty("line.separator")
				+ "Today is" + " " + LocalDate.now().getDayOfWeek() + ", " + LocalDate.now().getMonth() + " "
				+ LocalDate.now().getDayOfMonth() + System.getProperty("line.separator")
				+ "Currently scheduling quizzes for the next two weeks, until "
				+ LocalDate.now().plusDays(21).getDayOfWeek() + ", " + LocalDate.now().plusDays(21).getMonth() + " "
				+ LocalDate.now().plusDays(21).getDayOfMonth()
				+ System.getProperty("line.separator") + "      Skipping a week, no quiz or retakes."
				+ System.getProperty("line.separator") + "RETAKE: " + LocalDate.now().plusDays(20).getDayOfWeek() + ", "
				+ LocalDate.now().plusDays(20).getMonth() + " " + LocalDate.now().plusDays(20).getDayOfMonth()
				+ ", at 20:20 in test"
				+ System.getProperty("line.separator") + "    1) Quiz 10 from "
				+ LocalDate.now().plusDays(8).getDayOfWeek() + ", " + LocalDate.now().plusDays(8).getMonth() + " "
				+ LocalDate.now().plusDays(8).getDayOfMonth()
				+ System.getProperty("line.separator") + System.getProperty("line.separator"), outContent.toString());
	}
}

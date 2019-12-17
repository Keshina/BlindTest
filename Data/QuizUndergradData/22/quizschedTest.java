
package quizretakes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class quizschedTest {
	quizsched schedule;
	quizzes quizList;
	retakes retakesList;
	courseBean course;
	LocalDate current;
	courseReader readCourse;
	String courseID = "swe437";
	String courseTitle = "Software testing";
	String retakeDuration = "14";
	String dataLocation = "/var/www/CS/webapps/offutt/WEB-INF/data/";
	LocalDate endSkip = LocalDate.of(2019, 01, 25);
	LocalDate startSkip = LocalDate.of(2019, 01, 21);
	String Intro;
	String Date;
	String Until;
	String finalString;
	String nl = System.getProperty("line.separator");

	/*
	 * The declaration method is an example of controllability because it sets up the tests
	 */
	@Before
	public void declaration() {
		current = LocalDate.now();
		quizList = new quizzes();
		retakesList = new retakes();

		Intro = nl + nl + "******************************************************************************" + nl
				+ "GMU quiz retake scheduler for class Software testing" + nl
				+ "******************************************************************************" + nl + nl + nl
				+ "You can sign up for quiz retakes within the next two weeks. " + nl
				+ "Enter your name (as it appears on the class roster), " + nl
				+ "then select which date, time, and quiz you wish to retake from the following list." + nl + nl;
		Date = "Today is " + current.getDayOfWeek() + ", " + current.getMonth() + " " + current.getDayOfMonth() + nl;
		Until = "Currently scheduling quizzes for the next two weeks, until " + current.plusWeeks(2).getDayOfWeek()
				+ ", " + current.plusWeeks(2).getMonth() + " " + current.plusWeeks(2).getDayOfMonth() + nl;
		finalString = Intro + Date + Until + nl;
	}

	/**
	 * Test for a successful print, with same course, retakes and quizzes input as the original code
	 * assertTrue is used to test for similiar outputs
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testSuccessPrint() {
		// start controllability
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		quizBean qb1 = new quizBean(1, 01, 29, 10, 30);
		quizBean qb2 = new quizBean(2, 02, 05, 10, 30);
		quizList.addQuiz(qb1);
		quizList.addQuiz(qb2);
		retakeBean qr1 = new retakeBean(1, "EB 4430", 01, 30, 15, 30);
		retakeBean qr2 = new retakeBean(2, "???", 02, 05, 16, 00);
		retakesList.addRetake(qr1);
		retakesList.addRetake(qr2);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		// end controllability

		// start observability
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		System.setOut(streamOut);
		assertTrue(outStream.toString().equals(finalString));
		// end observability
	}

	/**
	 * Test for null retakesList, expected different outputs if the retakesList is null
	 * assertFalse is used to test for different outputs
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testNullRetake() {
		// start controllability
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		retakesList = null;
		quizBean qb4 = new quizBean(5, 01, 30, 10, 30);
		quizList.addQuiz(qb4);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		boolean falseTest = false;
		// end controllability

		// start observability
		try {
			schedule.printQuizScheduleForm(quizList, retakesList, course);
		} catch (NullPointerException e) {
			falseTest = true;
		}
		System.out.flush();
		System.setOut(streamOut);
		assertFalse(outStream.toString().equals(finalString));
		// end observability
	}

	/**
	 * Test for null quiz, expected different outputs if the quiz is null
	 * assertFalse is used to test for different outputs
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testNullQuiz() {
		// start controllability
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		quizList = null;
		retakeBean qr4 = new retakeBean(4, "EB 4430", 03, 06, 15, 30);
		retakesList.addRetake(qr4);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		boolean falseTest = false;
		// end controllability

		// start observability
		try {
			schedule.printQuizScheduleForm(quizList, retakesList, course);
		} catch (NullPointerException e) {
			falseTest = true;
		}
		System.out.flush();
		System.setOut(streamOut);
		assertFalse(outStream.toString().equals(finalString));
		// end observability
	}

	/**
	 * Test for wrong course title input, expected different outputs as the input is "Software" -
	 * instead of "Software engineering"
	 * assertFalse is used to test for different outputs
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testWrongCourseTitle() {
		// start controllability
		course = new courseBean(courseID, "Software", retakeDuration, startSkip, endSkip, dataLocation);
		ByteArrayOutputStream outStream1 = new ByteArrayOutputStream();
		PrintStream stream1 = new PrintStream(outStream1);
		PrintStream streamOut1 = System.out;
		System.setOut(stream1);
		// end controllability

		// start observability
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		System.setOut(streamOut1);
		assertFalse(outStream1.toString().equals(finalString));
		// end observability
	}

	/**
	 * Test for null course, expected different outputs if the course is null
	 * assertFalse is used to test for different outputs
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testNullCourse() {
		// start controllability
		course = null;
		ByteArrayOutputStream outStream2 = new ByteArrayOutputStream();
		PrintStream stream2 = new PrintStream(outStream2);
		PrintStream streamOut2 = System.out;
		System.setOut(stream2);
		boolean falseTest = false;
		// end controllability

		// start observability
		try {
			schedule.printQuizScheduleForm(quizList, retakesList, course);
		} catch (NullPointerException e) {
			falseTest = true;
		}
		System.out.flush();
		System.setOut(streamOut2);
		assertFalse(outStream2.toString().equals(finalString));
		// end observability
	}

	/**
	 * Test for when skipWeek is the case, expected different outputs will happen
	 * assertFalse is used to test for different outputs
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testSkipWeeks() {
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip.plusWeeks(3), endSkip.plusMonths(3),
				dataLocation);
		// quizzes
		quizBean qb1 = new quizBean(1, 01, 29, 10, 30);
		quizBean qb2 = new quizBean(2, 02, 05, 10, 30);
		quizList.addQuiz(qb1);
		quizList.addQuiz(qb2);
		// retakes
		retakeBean qr1 = new retakeBean(1, "EB 4430", 03, 10, 15, 30);
		retakeBean qr2 = new retakeBean(2, "???", 02, 05, 16, 00);

		retakesList.addRetake(qr1);
		retakesList.addRetake(qr2);

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		// end controllability

		// start observability
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		System.setOut(streamOut);

		assertFalse(outStream.toString().equals(finalString));
		// end observability

	}

}

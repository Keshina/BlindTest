package quizretakes;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Properties;

import org.junit.Test;

public class quizschedTest {
	private static int daysAvailable = 14;

	/*
	 * For testSkipdays1, we want to test if we wanted to take a test within the
	 * next two weeks if there exists a skip week, we would get into the skip
	 * the loop to notify there is a skip week
	 * 
	 * For the next three tests the only parameter we change is the local date.
	 * if the local date is more than 14 days from skip week, we do not get into
	 * skip week
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */
	@Test
	public void testSkipdays1() {
		boolean skip = false;
		LocalDate startSkip = LocalDate.of(2019, Month.MARCH, 11);
		LocalDate endSkip = LocalDate.of(2019, Month.MARCH, 15);
		quizzes quiz = new quizzes(1, 10, 30, 15, 30);
		retakes retake = new retakes(1, "EB 4554", 2, 26, 15, 30);
		courseBean course = new courseBean("swe437", "Software testing", "14", startSkip, endSkip,
				">/var/www/CS/webapps/offutt/WEB-INF/data/");
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 24);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		if (!endDay.isBefore(startSkip) && !endDay.isAfter(endSkip)) { // endDay
			{ // endDay is in a skip week, add 7 to endDay
				endDay = endDay.plusDays(new Long(7));
				skip = true;
			}
		}

		assertEquals(false, skip);

	}
	/*
	 * For testSkipdays2, we want to test if we wanted to take a test within the
	 * next two weeks if there exists a skip week, we would get into the skip
	 * the loop to notify there is a skip week
	 * 
	 * The only parameter we change is the local date. if the local date is more
	 * than 14 days from skip week, we do not get into skip week
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void testSkipdays2() {
		boolean skip = false;
		LocalDate startSkip = LocalDate.of(2019, Month.MARCH, 11);
		LocalDate endSkip = LocalDate.of(2019, Month.MARCH, 15);
		quizzes quiz = new quizzes(1, 10, 30, 15, 30);
		retakes retake = new retakes(1, "EB 4554", 2, 26, 15, 30);
		courseBean course = new courseBean("swe437", "Software testing", "14", startSkip, endSkip,
				">/var/www/CS/webapps/offutt/WEB-INF/data/");
		Properties retakeQuizIDProps = new Properties();
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 25);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		LocalDate origEndDay = endDay;
		if (!endDay.isBefore(startSkip) && !endDay.isAfter(endSkip)) { // endDay
			{ // endDay is in a skip week, add 7 to endDay
				endDay = endDay.plusDays(new Long(7));
				skip = true;
			}
		}

		assertEquals(true, skip);

	}

	/*
	 * For testSkipdays3 , we want to test if we wanted to take a test within
	 * the next two weeks if there exists a skip week, we would get into the
	 * skip the loop to notify there is a skip week
	 * 
	 * The only parameter we change is the local date. if the local date is more
	 * than 14 days from skip week, we do not get into skip week
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */
	@Test
	public void testSkipdays3() {
		boolean skip = false;
		LocalDate startSkip = LocalDate.of(2019, Month.MARCH, 11);
		LocalDate endSkip = LocalDate.of(2019, Month.MARCH, 15);
		quizzes quiz = new quizzes(1, 10, 30, 15, 30);
		retakes retake = new retakes(1, "EB 4554", 2, 26, 15, 30);
		courseBean course = new courseBean("swe437", "Software testing", "14", startSkip, endSkip,
				">/var/www/CS/webapps/offutt/WEB-INF/data/");
		Properties retakeQuizIDProps = new Properties();
		LocalDate today = LocalDate.of(2019, Month.MARCH, 1);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		LocalDate origEndDay = endDay;
		if (!endDay.isBefore(startSkip) && !endDay.isAfter(endSkip)) { // endDay
			{ // endDay is in a skip week, add 7 to endDay
				endDay = endDay.plusDays(new Long(7));
				skip = true;
			}
		}

		assertEquals(true, skip);

	}

	/*
	 * For testSkipdays4, we want to test if we wanted to take a test within the
	 * next two weeks if there exists a skip week, we would get into the skip
	 * the loop to notify there is a skip week
	 * 
	 * The only parameter we change is the local date. if the local date is more
	 * than 14 days from skip week, we do not get into skip week
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */
	@Test
	public void testSkipdays4() {
		boolean skip = false;
		LocalDate startSkip = LocalDate.of(2019, Month.MARCH, 11);
		LocalDate endSkip = LocalDate.of(2019, Month.MARCH, 15);
		quizzes quiz = new quizzes(1, 10, 30, 15, 30);
		retakes retake = new retakes(1, "EB 4554", 2, 26, 15, 30);
		courseBean course = new courseBean("swe437", "Software testing", "14", startSkip, endSkip,
				">/var/www/CS/webapps/offutt/WEB-INF/data/");
		Properties retakeQuizIDProps = new Properties();
		LocalDate today = LocalDate.of(2019, Month.MARCH, 2);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		LocalDate origEndDay = endDay;
		if (!endDay.isBefore(startSkip) && !endDay.isAfter(endSkip)) { // endDay
			{ // endDay is in a skip week, add 7 to endDay
				endDay = endDay.plusDays(new Long(7));
				skip = true;
			}
		}

		assertEquals(false, skip);

	}

	/**
	 * For retakebeatntest 1, we want to see if we can retake a particular quiz
	 * r1.getdate() gives you the date the test is administered if the test is
	 * before our constant date, february 22nd, you can retake the quiz if it is
	 * after 14 days, you can not
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void retakeBeanTest1() {
		retakes retakesList = new retakes();
		retakeBean r1 = new retakeBean(1, "EB 4554", 2, 22, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);
		retakesList.addRetake(r1);
		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 22);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;
		for (retakeBean r : retakesList) {
			LocalDate retakeDay = r.getDate();
			if (!(retakeDay.isBefore(today)) && !(retakeDay.isAfter(endDay))) {
				test = true;
			}
		}
		assertEquals(true, test);

	}

	/**
	 * For retakebeatntest 2, we want to see if we can retake a particular quiz
	 * r1.getdate() gives you the date the test is administered if the test is
	 * before our constant date, february 22nd, you can retake the quiz if it is
	 * after 14 days, you can not
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void retakeBeanTest2() {
		retakes retakesList = new retakes();
		retakeBean r1 = new retakeBean(1, "EB 4554", 2, 21, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);
		retakesList.addRetake(r1);
		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 22);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;
		for (retakeBean r : retakesList) {
			LocalDate retakeDay = r.getDate();
			if (!(retakeDay.isBefore(today)) && !(retakeDay.isAfter(endDay))) {
				test = true;
			}
		}
		assertEquals(false, test);

	}

	/**
	 * For retakebeatntest 3, we want to see if we can retake a particular quiz
	 * r1.getdate() gives you the date the test is administered if the test is
	 * before our constant date, february 22nd, you can retake the quiz if it is
	 * after 14 days, you can not
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void retakeBeanTest3() {
		retakes retakesList = new retakes();
		retakeBean r1 = new retakeBean(1, "EB 4554", 3, 8, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);
		retakesList.addRetake(r1);
		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 22);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;
		for (retakeBean r : retakesList) {
			LocalDate retakeDay = r.getDate();
			if (!(retakeDay.isBefore(today)) && !(retakeDay.isAfter(endDay))) {
				test = true;
			}
		}
		assertEquals(true, test);

	}

	/**
	 * For retakebeatntest 4, we want to see if we can retake a particular quiz
	 * r1.getdate() gives you the date the test is administered if the test is
	 * before our constant date, february 22nd, you can retake the quiz if it is
	 * after 14 days, you can not
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void retakeBeanTest4() {
		retakes retakesList = new retakes();
		retakeBean r1 = new retakeBean(1, "EB 4554", 3, 9, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);
		retakesList.addRetake(r1);
		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 22);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;
		for (retakeBean r : retakesList) {
			LocalDate retakeDay = r.getDate();
			if (!(retakeDay.isBefore(today)) && !(retakeDay.isAfter(endDay))) {
				test = true;
			}
		}
		assertEquals(false, test);

	}

	/**
	 * for testSkipBean1, we test to see if the retake itself is within a skip
	 * week, we would change the boolean to skip, that way we know to add
	 * another week to quiz retake
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void testSkipBean1() {
		boolean skip = true;

		retakes retakesList = new retakes();
		retakeBean r1 = new retakeBean(1, "EB 4554", 3, 9, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);
		retakesList.addRetake(r1);
		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 22);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;
		for (retakeBean r : retakesList) {
			LocalDate retakeDay = r.getDate();
			if (skip && retakeDay.isAfter(endDay)) {
				skip = false;
			}
		}
		assertEquals(false, skip);

	}

	/**
	 * for testSkipBean2, we test to see if the retake itself is within a skip
	 * week, we would change the boolean to skip, that way we know to add
	 * another week to quiz retake
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void testSkipBean2() {
		boolean skip = false;

		retakes retakesList = new retakes();
		retakeBean r1 = new retakeBean(1, "EB 4554", 3, 9, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);
		retakesList.addRetake(r1);
		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 22);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;
		for (retakeBean r : retakesList) {
			LocalDate retakeDay = r.getDate();
			if (skip && retakeDay.isAfter(endDay)) {
				skip = true;
			}
		}
		assertEquals(false, skip);
	}

	/**for testQuizBean1, we test to see a quiz is available for retake,
	 * given a particular retake date thats been assigned. If the assigned retake date
	 * is outside the bounds of the quiz, then you can not retake that particular quiz
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */
	
	@Test
	public void testQuizBean1() {
		quizBean quiz = new quizBean(1, 2, 27, 15, 30);
		LocalDate quizDay = quiz.getDate();
		LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));

		retakeBean r1 = new retakeBean(1, "EB 4554", 3, 13, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);

		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 27);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;

		LocalDate retakeDay = r1.getDate();

		if (!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay) && !today.isAfter(retakeDay)
				&& !retakeDay.isAfter(endDay)) {
			test = true;
		}
		assertEquals(true, test);
	}
	
	/**for testQuizBean2, we test to see a quiz is available for retake,
	 * given a particular retake date thats been assigned. If the assigned retake date
	 * is outside the bounds of the quiz, then you can not retake that particular quiz
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void testQuizBean2() {
		quizBean quiz = new quizBean(1, 2, 27, 15, 30);
		LocalDate quizDay = quiz.getDate();
		LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));

		retakeBean r1 = new retakeBean(1, "EB 4554", 3, 14, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);

		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 27);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;

		LocalDate retakeDay = r1.getDate();

		if (!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay) && !today.isAfter(retakeDay)
				&& !retakeDay.isAfter(endDay)) {
			test = true;
		}
		assertEquals(false, test);
	}
	
	/**for testQuizBean3, we test to see a quiz is available for retake,
	 * given a particular retake date thats been assigned. If the assigned retake date
	 * is outside the bounds of the quiz, then you can not retake that particular quiz
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void testQuizBean3() {
		quizBean quiz = new quizBean(1, 2, 27, 15, 30);
		LocalDate quizDay = quiz.getDate();
		LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));

		retakeBean r1 = new retakeBean(1, "EB 4554", 2, 27, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);

		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 27);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;

		LocalDate retakeDay = r1.getDate();

		if (!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay) && !today.isAfter(retakeDay)
				&& !retakeDay.isAfter(endDay)) {
			test = true;
		}
		assertEquals(true, test);
	}
	
	/**for testQuizBean4, we test to see a quiz is available for retake,
	 * given a particular retake date thats been assigned. If the assigned retake date
	 * is outside the bounds of the quiz, then you can not retake that particular quiz
	 * 
	 * OBSERVABILITY - Since we are testing a complicated if statement in our
	 * test, there is a little less observability. We have to be very careful to
	 * understand what the outcome of the if statement should be before we
	 * create the test code
	 * 
	 * CONTROLLABILITY - The controllability of the test is very simple. There
	 * is no user input, just static input to see if the correct case comes out
	 * 
	 */

	@Test
	public void testQuizBean4() {
		quizBean quiz = new quizBean(1, 2, 27, 15, 30);
		LocalDate quizDay = quiz.getDate();
		LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));

		retakeBean r1 = new retakeBean(1, "EB 4554", 2, 26, 15, 30);
		// retakeBean r2 = new retakeBean(2, "EB 4554", 2, 3, 15, 30);

		// retakesList.addRetake(r2);
		LocalDate today = LocalDate.of(2019, Month.FEBRUARY, 27);
		LocalDate endDay = today.plusDays(new Long(daysAvailable));
		boolean test = false;

		LocalDate retakeDay = r1.getDate();

		if (!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay) && !today.isAfter(retakeDay)
				&& !retakeDay.isAfter(endDay)) {
			test = true;
		}
		assertEquals(false, test);
	}
}

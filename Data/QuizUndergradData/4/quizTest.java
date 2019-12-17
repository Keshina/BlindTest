package quizretakes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class quizTest {
	
	@Test 
	public void testForNull() {
		try {
			quizsched quizSchedule = new quizsched();
			ArrayList<String> list = new ArrayList<String>();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			PrintStream data = System.out;
			System.setOut(new PrintStream(stream));
			quizSchedule.printQuizScheduleForm(null, null, null);
			System.setOut(data);
			String output = new String(stream.toByteArray());
		}
		catch (NullPointerException e) {
			return;
		}
		fail("NullPointerException expected");
	}	
	
	/* Observability - It is very easy to observe the outputs of the print statements
	 * -The data output is collected from the print statements
	 * -the arraylist output is then tested for the date
	 * -the answer is the output
	 * 
	 * 
	 * controllability - Every variable is initialized 
	 * - It starts with quizList, retakesList, course as inputs 
	 * - Then the print statements get captured into data
	 * - This data becomes inputs for the arraylist
	 * - The arraylist value is tested as an input for the answer
	 * 
	 */
	@Test
	public void date() throws Exception {
		quizsched quizSchedule = new quizsched();
		retakes retakesList = new retakes();
		quizzes quizList = new quizzes(); 
		retakesReader rr = new retakesReader();
		quizReader qr = new quizReader(); 	
		courseReader cr = new courseReader();
		LocalDate today = LocalDate.now();
		retakesList = rr.read("/Users/Bryan/Desktop/quizretakes/quiz-retakes-swe437.xml");
		quizList = qr.read("/Users/Bryan/Desktop/quizretakes/quiz-orig-swe437.xml");
		courseBean course  = cr.read("/Users/Bryan/Desktop/quizretakes/course-swe437.xml");
		ArrayList<String> list = new ArrayList<String>();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream data = System.out;
		System.setOut(new PrintStream(stream));
		quizSchedule.printQuizScheduleForm(quizList, retakesList, course);
		System.setOut(data);
		String output = new String(stream.toByteArray());
		int count = 0;
		for (String out: output.split("\n")) {
			list.add(out);
		}
		assertEquals("Today is "+ today.getDayOfWeek() + ", " + today.getMonth() + " " + today.getDayOfMonth(), list.get(11));
	}
	
	
	/* Observability - It is very easy to observe the outputs of the print statements
	 * -The data output is collected from the print statements
	 * -the arraylist output is then tested for the date
	 * -the answer is the output
	 * 
	 * 
	 * controllability - Every variable is initialized 
	 * - It starts with quizList, retakesList, course as inputs 
	 * - Then the print statements get captured into data
	 * - This data becomes inputs for the arraylist
	 * - The arraylist value is tested as an input for the answer
	 * 
	 */
	@Test
	public void weeks() throws Exception {
		quizsched quizSchedule = new quizsched();
		retakes retakesList = new retakes();
		quizzes quizList = new quizzes(); 
		retakesReader rr = new retakesReader();
		quizReader qr = new quizReader(); 	
		courseReader cr = new courseReader();
		LocalDate today = LocalDate.now();
		retakesList = rr.read("/Users/Bryan/Desktop/quizretakes/quiz-retakes-swe437.xml");
		quizList = qr.read("/Users/Bryan/Desktop/quizretakes/quiz-orig-swe437.xml");
		courseBean course  = cr.read("/Users/Bryan/Desktop/quizretakes/course-swe437.xml");
		ArrayList<String> list = new ArrayList<String>();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream data = System.out;
		System.setOut(new PrintStream(stream));
		quizSchedule.printQuizScheduleForm(quizList, retakesList, course);
		System.setOut(data);
		String output = new String(stream.toByteArray());
		LocalDate endDay = today.plusDays(new Long(14));
		int count = 0;
		for (String out: output.split("\n")) {
			list.add(out);
		}
		assertEquals("Currently scheduling quizzes for the next two weeks, until " + (endDay.getDayOfWeek()) + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth(), list.get(12));
	}
	
	/* Observability - It is very easy to observe the outputs of the print statements
	 * -The data output is collected from the print statements
	 * -the arraylist output comes from the data
	 * - the string output comes from arraylist value
	 * - A char is then taken from the string output
	 * -the answer output is compared between two chars. 
	 * 
	 * 
	 * controllability - Every variable is initialized 
	 * - It starts with quizList, retakesList, course as inputs 
	 * - Then the print statements get captured into data
	 * - This data becomes inputs for the arraylist
	 * - The arraylist value is tested as an input for the string
	 * - The string becomes an input for the char
	 * - the char becomes an input for the answer
	 */
	@Test
	public void firstRetakeID() throws Exception {
		quizsched quizSchedule = new quizsched();
		retakes retakesList = new retakes();
		quizzes quizList = new quizzes(); 
		retakesReader rr = new retakesReader();
		quizReader qr = new quizReader(); 	
		courseReader cr = new courseReader();
		LocalDate today = LocalDate.now();
		retakesList = rr.read("/Users/Bryan/Desktop/quizretakes/quiz-retakes-swe437.xml");
		quizList = qr.read("/Users/Bryan/Desktop/quizretakes/quiz-orig-swe437.xml");
		courseBean course  = cr.read("/Users/Bryan/Desktop/quizretakes/course-swe437.xml");
		ArrayList<String> list = new ArrayList<String>();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream data = System.out;
		System.setOut(new PrintStream(stream));
		quizSchedule.printQuizScheduleForm(quizList, retakesList, course);
		System.setOut(data);
		String output = new String(stream.toByteArray());
		int count = 0;
		for (String out: output.split("\n")) {
			list.add(out);
		}
		String quiz = list.get(14);
		char retakeID = quiz.charAt(4);
		assertEquals('1', retakeID);
	}
	
	/* Observability - It is very easy to observe the outputs of the print statements
	 * -The data output is collected from the print statements
	 * -the arraylist output comes from the data
	 * - the string output comes from arraylist value
	 * - A char is then taken from the string output
	 * -the answer output is compared between two chars. 
	 * 
	 * 
	 * controllability - Every variable is initialized 
	 * - It starts with quizList, retakesList, course as inputs 
	 * - Then the print statements get captured into data
	 * - This data becomes inputs for the arraylist
	 * - The arraylist value is tested as an input for the string
	 * - The string becomes an input for the char
	 * - the char becomes an input for the answer
	 */
	@Test
	public void lastRetakeID() throws Exception {
		quizsched quizSchedule = new quizsched();
		retakes retakesList = new retakes();
		quizzes quizList = new quizzes(); 
		retakesReader rr = new retakesReader();
		quizReader qr = new quizReader(); 	
		courseReader cr = new courseReader();
		LocalDate today = LocalDate.now();
		retakesList = rr.read("/Users/Bryan/Desktop/quizretakes/quiz-retakes-swe437.xml");
		quizList = qr.read("/Users/Bryan/Desktop/quizretakes/quiz-orig-swe437.xml");
		courseBean course  = cr.read("/Users/Bryan/Desktop/quizretakes/course-swe437.xml");
		ArrayList<String> list = new ArrayList<String>();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream data = System.out;
		System.setOut(new PrintStream(stream));
		quizSchedule.printQuizScheduleForm(quizList, retakesList, course);
		System.setOut(data);
		String output = new String(stream.toByteArray());
		int count = 0;
		for (String out: output.split("\n")) {
			list.add(out);
		}
		String quiz = list.get(list.size()-1);
		char retakeID = quiz.charAt(4);
		assertEquals('8', retakeID);
	}
	
}

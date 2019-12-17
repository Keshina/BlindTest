import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
//import org.junit.jupiter.api.Assertions;

/**
 * 
 */

/**
 *
 */
public class CompleteTO {
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();// .muteForSuccessfulTests();

	@Test
	public void completeTest() throws Exception {
		String courseId = "swe437";
		try {
			systemInMock.provideLines(courseId);
			quizsched.main(null);
		} catch (NoSuchElementException e) {
			System.out.println("Next user input expected");
			String[] test = systemOutRule.getLog().split("\n");
			LocalDate date = LocalDate.now();
			quizsched q = new quizsched();
			courseBean course = q.getCourseFile(courseId);
			int daysAvailable = Integer.parseInt(course.getRetakeDuration());

			quizzes quizList; /* CLI */
			retakes retakesList; /* CLI */
			quizList = q.getQuizzes(courseId);
			retakesList = q.getRetakes(courseId);
			boolean skip = false;
			LocalDate startSkip = course.getStartSkip();
			LocalDate endSkip = course.getEndSkip();
			String output;
			LocalDate today =  LocalDate.now();
			LocalDate endDay = today.plusDays(new Long(daysAvailable));
			output = "******************************************************************************\n";
			output += "University quiz retake scheduler for class "+course.getCourseTitle()+"\n";
			output += "******************************************************************************";
			output += "\n";
			output += "\n";

			output += "\n";
			output += "You can sign up for quiz retakes within the next two weeks.\n";
			output += "Enter your name (as it appears on the class roster),\n";
			output += "then select which date, time, and quiz you wish to retake from the following list.\n";
			output += "\n";
			LocalDate origEndDay = endDay;
			// if endDay is between startSkip and endSkip, add 7 to endDay
			//Fault1 start today and endDay should be before the skip week or the two should be after the skip week

				if (! ( (today.isBefore(startSkip) && endDay.isBefore(startSkip)) ||
				         (today.isAfter(endSkip) && endDay.isAfter(endSkip)) ) ) {	
					endDay = endDay.plusDays(new Long(7));
					skip = true;
				}
			//Fault1 end

			output += "Today is ";
			output += today.getDayOfWeek() + ", " + today.getMonth() + " " + today.getDayOfMonth() + "\n";
			output += "Currently scheduling quizzes for the next two weeks, until ";
			output += endDay.getDayOfWeek() + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth() + "\n";
			int quizRetakeCount = 0; /* CLI */
			for (retakeBean r : retakesList) {
				LocalDate retakeDay = r.getDate();
				LocalTime retakeTime = r.getTime();
				if(!(retakeDay.isBefore(today)) && !(retakeDay.isAfter(endDay))) {
					// if skip && retakeDay is after the skip week, print a message
			    	//Fault 4: skip message needs to be after endSkip  
					if(skip && retakeDay.isAfter(endSkip)) {
						output += "      Skipping a week, no quiz or retakes.\n";
						// Just print for the FIRST retake day after the skip week
						skip = false;
					}
					//Fault 5
					if (!(retakeDay.isAfter(startSkip)&&retakeDay.isBefore(endSkip))) {
					output += "RETAKE: " + retakeDay.getDayOfWeek() + ", " + retakeDay.getMonth() + " "
							+ retakeDay.getDayOfMonth() + ", at " + r.timeAsString() + " in " + r.getLocation() + "\n";

					for (quizBean qb : quizList) {
						LocalDate quizDay = qb.getDate();
						LocalTime quizTime = qb.getTime();
						LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));
						
						//Fault2 start
						 if ((quizDay.isBefore(startSkip) && startSkip.isBefore(lastAvailableDay)) ||
					                (quizDay.isBefore(endSkip) && endSkip.isBefore(lastAvailableDay)))
					            {
					               lastAvailableDay = lastAvailableDay.plusDays(new Long(7));
					            }
						 //Fault2 end
						 //Fault 3 start
						if (!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay)
								&& !today.isAfter(retakeDay) && !retakeDay.isAfter(endDay)) {
							if( ! (retakeDay.equals(lastAvailableDay) && retakeTime.isAfter(quizTime)) )
				               { 
							quizRetakeCount++; /* CLI */
							
							output += quizRetakeCount + ") "; /* CLI */
							output += "Quiz " + qb.getID() + " from " + quizDay.getDayOfWeek() + ", "
									+ quizDay.getMonth() + " " + quizDay.getDayOfMonth() + "\n";
				               }}
						//fault 3 end
					}
				}//Issue
				}
			}
			String printed = test[9].trim();
			for (int i = 10; i < test.length - 1; i++) {
				printed += "\n" + test[i].trim();
			}
			assertEquals(output, printed);

		}
	}

	
	
}

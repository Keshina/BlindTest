

package quizretakes;

import org.junit.*;
//import org.junit.Assert;
import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintStream;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.time.*;
import java.lang.Long;
import java.lang.String;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.time.*;

import java.util.Properties;

import java.util.Scanner;


public class quizschedTest 
{
   //Used to capture the output printed to Sys.out, for testing outputs
   private ByteArrayOutputStream sysOutput = new ByteArrayOutputStream();
   
   /* The following is used to create the course to pass into printQuizScheduleForm
      I decided not to test invalid course inputs to this method because the verification
      for invalid courses is outside this method. Therefore, if main (which verifies that the course is valid)
      allows course to be passed in, it is assumed to be correct, as it has already been verified.
   */
   LocalDate startSkip = LocalDate.of(2019, 1, 21);
   LocalDate endSkip   = LocalDate.of(2019, 1, 25);
   LocalDate today = LocalDate.now();
   String courseID = "swe437";
   String courseTitle = "Software testing";
   String dataLocation = "/var/www/CS/webapps/offutt/WEB-INF/data/";
   String retakeDuration = "14";
   
   //Set the output to go into this print stream 
   @Before public void setUp() 
   {
       System.setOut(new PrintStream(sysOutput));
   }
   
   //Restore output to sys.out, to not interfere w/ other things
   @After
   public void restoreStreams() 
   {
       System.setOut(System.out);
   }

   /** Correct input test, should print a working form with one retake session available with one quiz **/
   @Test public void testCorrectInput() 
   {
      quizzes quizList = new quizzes(6, 3, 5, 10, 30); //Controllability 
      retakes retakesList = new retakes(1, "BH D002", 3, 8, 10, 0); //Controllability 
      courseBean course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation); //Controllability 
      
      quizsched.printQuizScheduleForm(quizList, retakesList, course); //Controllability
      String quizSchedOutput = sysOutput.toString(); //Observability
      String checkRetakeLength = quizSchedOutput.substring(quizSchedOutput.indexOf("Currently"), quizSchedOutput.indexOf("RETAKE:")); //Observability
      checkRetakeLength = checkRetakeLength.substring(checkRetakeLength.length()-4, checkRetakeLength.length()-2); //Should get the end day of the retake period 
      
      //Calculates the correct end day of the retake period, and then gets the retake end day according to what was printed
      int endRetakeDayCalculated = today.plusDays(14).getDayOfMonth();
      int endRetakeDay = Integer.parseInt(checkRetakeLength);  //Observability
      
      //Gets the line about retake session date and location
      String retakeString = quizSchedOutput.substring(quizSchedOutput.indexOf("RETAKE:"), quizSchedOutput.indexOf("D002") + 4); //Observability
      
      //Gets the line specifying which quiz is available to retake
      String quizListing =  quizSchedOutput.substring(quizSchedOutput.indexOf("1)"), quizSchedOutput.indexOf("5") + 1); //Observability
      
      
      assertEquals("Test if retake was printed correctly", "RETAKE: FRIDAY, MARCH 8, at 10:00 in BH D002", retakeString);
      assertEquals("Test if retake period is accurate", endRetakeDayCalculated, endRetakeDay);
      assertEquals("Test if quiz listing is accurate", "1) Quiz 6 from TUESDAY, MARCH 5", quizListing);        
   }
   
   /** Tests for when the quizList is empty **/
   @Test public void testEmptyQuiz() 
   {
      quizzes quizList = new quizzes(); //Controllability 
      retakes retakesList = new retakes(1, "BH D002", 3, 8, 10, 0); //Controllability 
      courseBean course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation); //Controllability 
      
      quizsched.printQuizScheduleForm(quizList, retakesList, course); //Controllability
      String quizSchedOutput = sysOutput.toString(); //Observability
      String checkRetakeLength = quizSchedOutput.substring(quizSchedOutput.indexOf("Currently"), quizSchedOutput.indexOf("RETAKE:")); //Observability
      checkRetakeLength = checkRetakeLength.substring(checkRetakeLength.length()-4, checkRetakeLength.length()-2); //Should get the end day of the retake period 
      
      //Calculates the correct end day of the retake period, and then gets the retake end day according to what was printed
      int endRetakeDayCalculated = today.plusDays(14).getDayOfMonth();
      int endRetakeDay = Integer.parseInt(checkRetakeLength); //Observability
      
      //Gets the line about retake session date and location
      String retakeString = quizSchedOutput.substring(quizSchedOutput.indexOf("RETAKE:"), quizSchedOutput.indexOf("D002") + 4); //Observability
      
      //Gets the remaining characters after the retake session listing, should be a couple of newlines and blank spaces
      String quizListing =  quizSchedOutput.substring(quizSchedOutput.indexOf("D002") + 4, quizSchedOutput.length()); //Observability
      
      
      assertEquals("Test if retake was printed correctly", "RETAKE: FRIDAY, MARCH 8, at 10:00 in BH D002", retakeString);
      assertEquals("Test if retake period is accurate", endRetakeDayCalculated, endRetakeDay);
      assertEquals("Test if quiz listing is accurate", "\r\n\r\n", quizListing); //Ghetto way of checking if there's nothing after the retake session listing, since there's no quizzes
   }
   
   /* Tests for when the quizList is empty */
   @Test public void testEmptyRetakes() 
   {
      quizzes quizList = new quizzes(5, 2, 26, 10, 30); //Controllability 
      retakes retakesList = new retakes(); //Controllability 
      courseBean course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation); //Controllability 
      
      quizsched.printQuizScheduleForm(quizList, retakesList, course); //Controllability
      String quizSchedOutput = sysOutput.toString(); //Observability
      
      //Gets the line about retake session date and location
      //If there are no retake sessions, then the last part of the output should be "until RETAKE END DATE".
      //Since retake end date varies by day, it needs to be calculated then compared. 
      String lastPartOfOutput = quizSchedOutput.substring(quizSchedOutput.indexOf("until") + 6, quizSchedOutput.length() - 4); //Observability
      LocalDate endDay = LocalDate.now().plusDays(14);
      
      String endDayString = endDay.getDayOfWeek() + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth();
      
      /*The following is observability*/
      assertEquals("Test if retake period is accurate", endDayString, lastPartOfOutput);
   }
   
   /* Tests for when the quizList is empty */
   @Test public void testPassedRetakes() 
   {
      quizzes quizList = new quizzes(5, 2, 26, 10, 30); //Controllability 
      retakes retakesList = new retakes(1, "BH D002", 1, 30, 10, 0); //Controllability 
      courseBean course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation); //Controllability 
      
      quizsched.printQuizScheduleForm(quizList, retakesList, course); //Controllability
      String quizSchedOutput = sysOutput.toString(); //Observability 
      
      //Gets the line about retake session date and location
      //If the retake sessions are in the past, then the last part of the output should be "until RETAKE END DATE".
      //Since retake end date varies by day, it needs to be calculated then compared. 
      String lastPartOfOutput = quizSchedOutput.substring(quizSchedOutput.indexOf("until") + 6, quizSchedOutput.length() - 4); //Observability
      LocalDate endDay = LocalDate.now().plusDays(14);
      
      String endDayString = endDay.getDayOfWeek() + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth();
      
      assertEquals("Test if retake period is accurate", endDayString, lastPartOfOutput);
   }
}

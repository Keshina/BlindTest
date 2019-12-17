package quizretakes;

//Test file for quizsched.java


import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.time.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class quizschedTest{

   //Parameters required for printQuizScheduleForm
   private quizzes quizList;
   private retakes retakesList;
   private courseBean courseTest;
   //Parameters required for quizzes
   private int quizID;
   private int month;
   private int day;
   private int hour;
   private int minute;
   //Parameters required for retakes
   private int ID;
   private String location;
   //Parameters required for courseBean
   private String courseID;
   private String courseTitle;
   private String retakeDuration;
   private LocalDate startSkip;
   private LocalDate endSkip;
   private String dataLocation;
   //Variable to store current time
   private LocalDate today;
   //Variable to redirect system.out to string
   private ByteArrayOutputStream outputStream;
   private PrintStream pStream;
   private PrintStream original;
   
   //Before each test, reset parameters to original state
   @Before
   public void setUp(){
      quizList = new quizzes();
      retakesList = new retakes();
      today = LocalDate.now();
      //Code I found online to redirect system.out to variable
      // Create a stream to hold the output
      outputStream = new ByteArrayOutputStream();
      pStream = new PrintStream (outputStream);
      //Saves the old System.out
      original = System.out;
      //Sets our stream in Java
      System.setOut(pStream);
   }
   
   //After each test, reset the system
   public void cleanUp(){
      //Resets System.out to original state
      System.out.flush();
      System.setOut(original);
   }
   
   //This is an intial test case so you guys have an idea how to structure future ones
   @Test
   public void testNormalInput(){
      //Creates and adds a new quiz to current quizzes
      quizID = 1; month = 2; day = 27; hour = 5; minute = 54; //Controllability
      //Parameter for quizBean is (int quizID, int month, int day, int hour, int minute)
      quizBean quizTest = new quizBean (quizID, month, day, hour, minute); 
      quizList.addQuiz(quizTest);
      
      //Creates and adds a new retake session to retakes
      ID = 1; location = "class"; month = 2; day = 28; hour = 15; minute = 30; //Controllability
      //Parameter for retakeBean is (int ID, String location, int month, int day, int hour, int minute)
      retakeBean retakeTest = new retakeBean (ID, location, month, day, hour, minute);
      retakesList.addRetake(retakeTest);
      
      //Creates a new courseBean /////////////////////               (I don't know if these are correct inputs. HELP!)
      courseID = "SWE437"; courseTitle = "Software testing"; retakeDuration = "14"; //Controllability
      startSkip = LocalDate.of(2019,3,11); endSkip = LocalDate.of(2019,3,17); dataLocation = "quizretakes"; //Controllability
      //Parameter for courseBean is (String courseID, String courseTitle, 
      //String retakeDuration, LocalDate startSkip, LocalDate endSkip, String dataLocation)
      courseTest = new courseBean (courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
      
      //Next output will be saved into pStream
      //Changed private to public in printQuizScheduleForm
      quizsched.printQuizScheduleForm(quizList, retakesList, courseTest);
      //Saves output to string variable
      String output = outputStream.toString();
      //Reset System with original
      cleanUp();
      System.out.println("printing" + output);
      //No clue about the output for the actual method. ///////////                                               HELP!
      Assert.assertTrue("Base Test Framework", output.equals("Some kind of output")); //Observability
   }
}

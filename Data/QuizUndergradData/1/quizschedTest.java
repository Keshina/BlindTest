package quizretakes;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.time.*;


public class quizschedTest {

//Input Domains:
   //Parameters for printQuizScheduleForm
   private quizzes quizList; 
   private retakes retakesList;
   private courseBean course;
   
   //Variables for quizzes
   private int quizID;
   private int month;
   private int day;
   private int hour; 
   private int minute;
   
   //Variables for retakes
   private int ID;
   private String location; //rest of variables are redundant
   
   //Variables for courseBean
   private String courseID;
   private String courseTitle;
   private String retakeDuration;
   private LocalDate startSkip;
   private LocalDate endSkip;
   private String dataLocation;
   
   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
      quizList = new quizzes(); 
      retakesList = new retakes();
      courseBean course;
   }

   @After public void tearDown(){
      quizList = null;
      retakesList = null;
      course = null;
   }
   
   @Test
   public void test1(){
      //create new quiz with controllability
      quizID = 1;//~~controllability~~
      month = 2;
      day = 27;
      hour = 11;
      minute = 7;//~~~~~~~~~~~~~~~~~~~
      quizBean qbean = new quizBean(quizID, month, day, hour, minute);
      quizList.addQuiz(qbean);
      
      //create retake quiz with controllability
      ID = 1;     //~~controllability~~
      location = "classroom 1";
      month = 3;
      day = 1;
      hour = 10;
      minute = 30;//~~~~~~~~~~~~~~~~~~~
      retakeBean rbean = new retakeBean(ID, location, month, day, hour, minute);
      retakesList.addRetake(rbean);
      
      //create coursebean with controlabillity
      courseID = "swe437";          //~~controllability~~
      courseTitle = "Software Testing and Maintenance";
      retakeDuration = "14";
      startSkip = LocalDate.of(2019, 3, 11);
      endSkip = LocalDate.of(2019, 3, 16);
      dataLocation = "quizretakes/";//~~~~~~~~~~~~~~~~~~~
      course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
      
      //Changed printQuizScheduleForm from Private to Public
      quizsched.printQuizScheduleForm(quizList, retakesList, course);
      
      //Output assert to check test and show Observability
      //Assert.assertEquals("Correct base case",1,1); //Observability 
      //I do not know how to do this ;-;
   }
   
   @Test
   public void testmultiplequizzes1(){
      //create new quizzes with controllability
      quizID = 1;//~~controllability~~
      month = 2;
      day = 27;
      hour = 11;
      minute = 7;//~~~~~~~~~~~~~~~~~~~
      quizBean qbean = new quizBean(quizID, month, day, hour, minute);
      quizList.addQuiz(qbean);
      
      quizID = 2;//~~controllability~~
      month = 3;
      day = 1;
      hour = 1;
      minute = 25;//~~~~~~~~~~~~~~~~~~~
      qbean = new quizBean(quizID, month, day, hour, minute);
      quizList.addQuiz(qbean);
      
      //create retake quiz with controllability
      ID = 1;     //~~controllability~~
      location = "classroom 1";
      month = 3;
      day = 1;
      hour = 10;
      minute = 30;//~~~~~~~~~~~~~~~~~~~
      retakeBean rbean = new retakeBean(ID, location, month, day, hour, minute);
      retakesList.addRetake(rbean);
      
      //create coursebean with controlabillity
      courseID = "swe437";          //~~controllability~~
      courseTitle = "Software Testing and Maintenance";
      retakeDuration = "14";
      startSkip = LocalDate.of(2019, 3, 11);
      endSkip = LocalDate.of(2019, 3, 16);
      dataLocation = "quizretakes/";//~~~~~~~~~~~~~~~~~~~
      course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
      
      //Changed printQuizScheduleForm from Private to Public
      quizsched.printQuizScheduleForm(quizList, retakesList, course);
      
      //Output assert to check test and show Observability
      //Assert.assertEquals("Correct multiple quiz case",1,1); //Observability
   }
   
   @Test
   public void testmultipleretakes1(){
      //create new quiz with controllability
      quizID = 1;//~~controllability~~
      month = 2;
      day = 27;
      hour = 11;
      minute = 7;//~~~~~~~~~~~~~~~~~~~
      quizBean qbean = new quizBean(quizID, month, day, hour, minute);
      quizList.addQuiz(qbean);
      
      //create retake quizzes with controllability
      ID = 1;     //~~controllability~~
      location = "classroom 1";
      month = 3;
      day = 1;
      hour = 10;
      minute = 30;//~~~~~~~~~~~~~~~~~~~
      retakeBean rbean = new retakeBean(ID, location, month, day, hour, minute);
      retakesList.addRetake(rbean);
      
      ID = 2;     //~~controllability~~
      location = "classroom 1";
      month = 3;
      day = 3;
      hour = 3;
      minute = 15;//~~~~~~~~~~~~~~~~~~~
      rbean = new retakeBean(ID, location, month, day, hour, minute);
      retakesList.addRetake(rbean);
      
      //create coursebean with controlabillity
      courseID = "swe437";          //~~controllability~~
      courseTitle = "Software Testing and Maintenance";
      retakeDuration = "14";
      startSkip = LocalDate.of(2019, 3, 11);
      endSkip = LocalDate.of(2019, 3, 16);
      dataLocation = "quizretakes/";//~~~~~~~~~~~~~~~~~~~
      course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
      
      //Changed printQuizScheduleForm from Private to Public
      quizsched.printQuizScheduleForm(quizList, retakesList, course);
      
      //Output assert to check test and show Observability
      //Assert.assertEquals("Correct multiple retakes case",1,1); //Observability
   }
   
   @Test
   public void testmultipleretakes2(){
      //create new quizzes with controllability
      quizID = 1;//~~controllability~~
      month = 2;
      day = 27;
      hour = 11;
      minute = 7;//~~~~~~~~~~~~~~~~~~~
      quizBean qbean = new quizBean(quizID, month, day, hour, minute);
      quizList.addQuiz(qbean);
      
      quizID = 2;//~~controllability~~
      month = 3;
      day = 1;
      hour = 1;
      minute = 25;//~~~~~~~~~~~~~~~~~~~
      qbean = new quizBean(quizID, month, day, hour, minute);
      quizList.addQuiz(qbean);
      
      //create retake quizzes with controllability
      ID = 1;     //~~controllability~~
      location = "classroom 1";
      month = 3;
      day = 1;
      hour = 10;
      minute = 30;//~~~~~~~~~~~~~~~~~~~
      retakeBean rbean = new retakeBean(ID, location, month, day, hour, minute);
      retakesList.addRetake(rbean);
      
      ID = 2;     //~~controllability~~
      location = "classroom 1";
      month = 3;
      day = 4;
      hour = 1;
      minute = 45;//~~~~~~~~~~~~~~~~~~~
      rbean = new retakeBean(ID, location, month, day, hour, minute);
      retakesList.addRetake(rbean);
      
      //create coursebean with controlabillity
      courseID = "swe437";          //~~controllability~~
      courseTitle = "Software Testing and Maintenance";
      retakeDuration = "14";
      startSkip = LocalDate.of(2019, 3, 11);
      endSkip = LocalDate.of(2019, 3, 16);
      dataLocation = "quizretakes/";//~~~~~~~~~~~~~~~~~~~
      course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
      
      //Changed printQuizScheduleForm from Private to Public
      quizsched.printQuizScheduleForm(quizList, retakesList, course);
      
      //Output assert to check test and show Observability
      //Assert.assertEquals("Correct multiple retakes case 2",1,1); //Observability
   }
}

/**
 * 
 */
package quizretakes;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;


public class testCases {

	public String path = "/Users/ukeshsilwal/eclipse-workspace/quizretakes/src/quizretakes/";
	public String courseFile = path+"course-swe437.xml";
	public String quizFile = path+"quiz-orig-swe437.xml";
	public String retakeFile = path+"quiz-retakes-swe437.xml";
	public String apptsFile = path+"quiz-appts-swe437.txt";
	public courseBean course;
	public quizzes qzs;
	public retakes retakesList;
	 int daysAvailable  = 0;
	
	public testCases() throws IOException, ParserConfigurationException, SAXException
	{
		 courseReader cr = new courseReader(); // create coursereader instance
		 course = cr.read(courseFile);// read course
		 int daysAvailable = Integer.parseInt(course.getRetakeDuration());

		 quizReader qr = new quizReader();// create quiz reader instance
		 qzs = qr.read(quizFile);// read quiz file
		 
		 retakesList = new retakes();
		 retakesReader rr    = new retakesReader();
		 retakesList         = rr.read(retakeFile);
	}
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCourse()  throws Exception
	{
		// Controllable
		 courseReader cr = new courseReader(); // create coursereader instance
		 courseBean course = cr.read(courseFile);// read course
		 LocalDate startdate = LocalDate.parse("2019-01-21");// create start date
		 LocalDate enddate = LocalDate.parse("2019-01-25");// create end date
		 
		 // observable 
		 assertEquals(course.getCourseID(),"swe437");// test course id
		 assertEquals(course.getCourseTitle(),"Software testing");// test title
		 assertEquals(course.getRetakeDuration(),"14");// test duration
		 
		 assertEquals(course.getStartSkip(),startdate);		 // test start date
		 assertEquals(course.getEndSkip(),enddate);// test end date
		 assertEquals(course.getDataLocation(),"/var/www/CS/webapps/offutt/WEB-INF/data/");// test location
		 
		
	}

	@Test
	public void testQuiz() throws Exception
	{
		// controllable 
		quizReader qr = new quizReader();// create quiz reader instance
		quizzes qzs = qr.read(quizFile);// read quiz file
		Iterator<quizBean> iter = qzs.iterator();// get quizzes iterator
		
		// observable 
		// test for first entry
		quizBean qb1 = iter.next();// get first entry in quiz list
		assertEquals(qb1.getID(),1);// test id
		 LocalDate givendate = LocalDate.parse("2019-01-29");// create date
		assertEquals(qb1.getDate(),givendate);// test date
		assertEquals(qb1.timeAsString(),"10:30");// test time
		
		// test for second entry
		quizBean qb2 = iter.next();// get first entry in quiz list
		assertEquals(qb2.getID(),2);// test id
		givendate = LocalDate.parse("2019-02-05");// create date
		assertEquals(qb2.getDate(),givendate);// test date
		assertEquals(qb2.timeAsString(),"10:30");// test time
		
		// test for third entry
		quizBean qb3 = iter.next();// get first entry in quiz list
		assertEquals(qb3.getID(),3);// test id
		givendate = LocalDate.parse("2019-02-06");// create date
		assertEquals(qb3.getDate(),givendate);// test date
		assertEquals(qb3.timeAsString(),"10:30");// test time	
	}
	
	@Test 
	public void testAppts() throws Exception
	{
		// controllable
		apptsReader ar = new apptsReader();
		ArrayList<apptBean> list = ar.read(apptsFile);
			
		// observable 
		assertEquals(list.size(),6);// test list size
		// get first entry in list
		apptBean ab1 = list.get(0);
		assertEquals(ab1.getQuizID(),3);
		assertEquals(ab1.getRetakeID(),1);
		assertEquals(ab1.getName(),"ukesh");
		
	}
	
	@Test 
	public void testQuizRetake() throws Exception
	{
		Iterator<retakeBean> iter = retakesList.iterator();
		LocalDate givendate = LocalDate.parse("2019-02-14");// create date
		// observable 
		// test for first entry
		retakeBean rb1 = iter.next();// get first entry in quiz list
		assertEquals(rb1.getID(),1);// test id		 
		assertEquals(rb1.getDate(),givendate);// test date
		assertEquals(rb1.timeAsString(),"15:30");// test time
		assertEquals(rb1.getLocation(),"EB 4430");//test location
		
		givendate = LocalDate.parse("2019-02-15");// create date
		// observable 
		// test for second entry
		retakeBean rb2 = iter.next();// get first entry in quiz list
		assertEquals(rb2.getID(),2);// test id		 
		assertEquals(rb2.getDate(),givendate);// test date
		assertEquals(rb2.timeAsString(),"16:00");// test time
		assertEquals(rb2.getLocation(),"EB 4430");//test location
		
		
		givendate = LocalDate.parse("2019-02-18");// create date
		// observable 
		// test for third entry
		retakeBean rb3 = iter.next();// get first entry in quiz list
		assertEquals(rb3.getID(),3);// test id		 
		assertEquals(rb3.getDate(),givendate);// test date
		assertEquals(rb3.timeAsString(),"16:00");// test time
		assertEquals(rb3.getLocation(),"EB 4430");//test location
	}
	
	@Test
	public void testprintQuizScheduleForm() throws Exception
	{
			
		// controllable 
		 LocalDate startDate = LocalDate.parse("2019-01-21");// create date
		 LocalDate endDate = LocalDate.parse("2019-01-25");// create date
		 LocalDate endD = LocalDate.parse("2019-02-28");// create date
		 
		 LocalDate startSkip = course.getStartSkip();
		 LocalDate endSkip   = course.getEndSkip();
		 
		 // observable
		 assertEquals(startSkip,startDate);// test startskip
		 assertEquals(endSkip,endDate);// test endskip
		 boolean skip = false;
		 
		 // controllable
		 LocalDate today  = LocalDate.now();
		   LocalDate endDay = today.plusDays(new Long(daysAvailable));
		   LocalDate origEndDay = endDay;
		   // if endDay is between startSkip and endSkip, add 7 to endDay
		   if(!endDay.isBefore(startSkip) && !endDay.isAfter(endSkip))
		   {  // endDay is in a skip week, add 7 to endDay
		      endDay = endDay.plusDays(new Long(7));
		      skip = true;
		   }
		 
		   
		   // Unique integer for each retake and quiz pair
		   int quizRetakeCount = 0; /* CLI */
		   for(retakeBean r: retakesList)
		   {
		      LocalDate retakeDay = r.getDate();
		      if(!(retakeDay.isBefore(today)) && !(retakeDay.isAfter(endDay)))
		      {
		         // if skip && retakeDay is after the skip week, print a message
		         if(skip && retakeDay.isAfter(origEndDay))
		         {  // A "skip" week such as spring break.
		          //  System.out.println("      Skipping a week, no quiz or retakes.");
		            // Just print for the FIRST retake day after the skip week
		            skip = false;
		         }

		         for(quizBean q: qzs)
		         {
		            LocalDate quizDay = q.getDate();
		            LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));

		            if(!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay) &&
		                !today.isAfter(retakeDay) && !retakeDay.isAfter(endDay))
		            {
		               quizRetakeCount++; /* CLI */
		             }
		         }
		      }
		   }
		 
		   // observable 
		   assertEquals(endDay,endD);// test endday
		   assertEquals(skip,false);// test skip variable
		   assertEquals(quizRetakeCount,0);// test quiz retake count
		   
	}
	
	/*
	 * @Test
	public void testRemoveDuplicates()
	{
	words.add("cookie");
	words.add("cake");
	words.add("cake");
	words.add("pie");
	words.removeDuplicates();
	assertTrue("removeDuplicates method", words.getFirst().equals("cookie"));
	}
	(4 points) Describe the flaw in terms of the RIPR model. Be as precise, specific, and concise as you can. For full credit, you must use the terminology in the book.
	the assert method validates only the first element in the list, there for if there is fault in next list index or next state,
	it will not capture or throw error. 
	
	(2 points) Rewrite the test method to correct the flaw.
	the solution is to iterate over complete list to identify fault. 
	
	*/
}

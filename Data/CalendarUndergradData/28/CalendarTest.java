
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class CalendarTest {
	ByteArrayOutputStream baos;
	PrintStream ps;
	PrintStream out;

	@Before
	public void setUp() throws Exception {
	        baos = new ByteArrayOutputStream();
                ps = new PrintStream(baos);
                out = System.out;
                System.setOut(ps);
	}

	@After
	public void tearDown() throws Exception{
		baos = null;
		ps = null;
		out = null;
	}

	@Test 
	public void test1() {
		String data = "2 2019";
                // Controllability:	These are typical inputs.
		//			This type of input will be the most common from the user.
		//			Any input exceptions are most likely caused by Scanner/reading faults.

		System.setIn(new ByteArrayInputStream(data.getBytes()));	
		Calendar.main(null);
		
		System.out.flush();
		System.setOut(out);			

		String expectedOutput = 
		"Enter month: Enter year:    February 2019\n" +
		" S  M Tu  W Th  F  S\n" +
		"                1  2 \n" +
		" 3  4  5  6  7  8  9 \n" + 
		"10 11 12 13 14 15 16 \n" + 
		"17 18 19 20 21 22 23 \n" + 
		"24 25 26 27 28 \n";
		// Observability: 	Valid test for February when not a leap year. 
		//			Helps observe the behavior under normal circumstances.
		//			If this is a failure, shows that weekDay method is most likely the fault.

		assertEquals(expectedOutput, baos.toString());
	}

	@Test 
        public void test2() {
                String data = "12 2019";
                // Controllability:     These are typical inputs.
                //                      This type of input will be the most common from the user.
		//			Any input exceptions are most likely caused by Scanner/reading faults.

                System.setIn(new ByteArrayInputStream(data.getBytes()));
                Calendar.main(null);

                System.out.flush();
                System.setOut(out);

                String expectedOutput =
                "Enter month: Enter year:    December 2019\n" +
                " S  M Tu  W Th  F  S\n" +
                " 1  2  3  4  5  6  7 \n" +
                " 8  9 10 11 12 13 14 \n" +
                "15 16 17 18 19 20 21 \n" +
                "22 23 24 25 26 27 28 \n" +
                "29 30 31 \n";
		// Observability:       Valid test for a non-February month when not a leap year.
                //                      Helps observe the behavior under normal circumstances.
		//			If this is a failure, shows that weekDay method is most likely the fault.
                
                assertEquals(expectedOutput, baos.toString());
        }

        @Test
        public void test3() {
                String data = "2 2020";
                // Controllability:     These are typical inputs.
                //                      This type of input will be the most common from the user.
		//			Any input exceptions are most likely caused by Scanner/reading faults.

                System.setIn(new ByteArrayInputStream(data.getBytes()));
                Calendar.main(null);

                System.out.flush();
                System.setOut(out);

                String expectedOutput =
                "Enter month: Enter year:    February 2020\n" +
                " S  M Tu  W Th  F  S\n" +
                "                   1 \n" +
                " 2  3  4  5  6  7  8 \n" +
                " 9 10 11 12 13 14 15 \n" +
                "16 17 18 19 20 21 22 \n" +
                "23 24 25 26 27 28 29 \n";
                // Observability:       Valid test for February when on a leap year. 
                //                      Helps observe the behavior under semi-regular circumstances.
		//			If this test is a failure, shows the isLeapYear method most likely is the fault.

                assertEquals(expectedOutput, baos.toString());
        }

        @Test
        public void test4() {
                String data = "12 2020";
                // Controllability:     These are typical inputs.
                //                      This type of input will be the most common from the user.
		//			Any input exceptions are most likely caused by Scanner/reading faults.

                System.setIn(new ByteArrayInputStream(data.getBytes()));
                Calendar.main(null);

                System.out.flush();
                System.setOut(out);

                String expectedOutput =
                "Enter month: Enter year:    December 2020\n" +
                " S  M Tu  W Th  F  S\n" +
                "       1  2  3  4  5 \n" +
                " 6  7  8  9 10 11 12 \n" +
                "13 14 15 16 17 18 19 \n" +
                "20 21 22 23 24 25 26 \n" +
                "27 28 29 30 31 \n";
                // Observability:       Valid test for non-February month when on a leap year. 
                //                      Helps observe the behavior under semi-regular circumstances.
		//			If this test is a failure, shows the isLeapYear method most likely is the fault.

                assertEquals(expectedOutput, baos.toString());
        }

        @Test (expected = IllegalArgumentException.class)
        public void test5() {
                String data = "2 -100";
		// Addresses Controllability by allowing a correct input for the first argument, but illegal in the second
		// Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));
		
		try {
			Calendar.main(null);
		}
		catch (IllegalArgumentException e) {
			return;
		}
		fail ("IllegalArgumentException expected."); 
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
        }

        @Test (expected = IllegalArgumentException.class)
        public void test6() {
                String data = "-10 2019";
		// Addresses Controllability by inputting an incorrect argument for the first argument, but correct for the second
                // Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));

                try {
                        Calendar.main(null);
                }
                catch (IllegalArgumentException e) {
                        return;
                }
                fail ("IllegalArgumentException expected.");
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
        }

        @Test (expected = IllegalArgumentException.class)
        public void test7() {
                String data = "-10 -100";
		// Addresses Controllability by inputting two incorrect arguments
                // Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));

                try {
                        Calendar.main(null);
                }
                catch (IllegalArgumentException e) {
                        return;
                }
                fail ("IllegalArgumentException expected.");
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
        }

        @Test (expected = IllegalArgumentException.class)
        public void test8() {
                String data = "20 2019";
		// Addresses Controllability by inputting an incorrect argument for the first argument, but correct for the second
                // Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));

                try {
                        Calendar.main(null);
                }
                catch (IllegalArgumentException e) {
                        return;
                }
                fail ("IllegalArgumentException expected.");
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
        }

        @Test (expected = IllegalArgumentException.class)
        public void test9() {
                String data = "20 -100";
		// Addresses Controllability by inputting two incorrect arguments
                // Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));

                try {
                        Calendar.main(null);
                }
                catch (IllegalArgumentException e) {
                        return;
                }
                fail ("IllegalArgumentException expected.");
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
        }

        @Test (expected = IllegalArgumentException.class)
        public void test10() {
                String data = "December 2020";
		// Addresses Controllability by inputting an incorrect argument for the first argument, but correct for the second
                // Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));

                try {
                        Calendar.main(null);
                }
                catch (IllegalArgumentException e) {
                        return;
                }
                fail ("IllegalArgumentException expected.");
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
        }

        @Test (expected = IllegalArgumentException.class)
        public void test11() {
                String data = "12 Twenty";
		// Addresses Controllability by allowing a correct input for the first argument, but illegal in the second
                // Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));

                try {
                        Calendar.main(null);
                }
                catch (IllegalArgumentException e) {
                        return;
                }
                fail ("IllegalArgumentException expected.");
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
        }

	@Test (expected = NullPointerException.class)
        public void test12() {
                String data = ("" + "2020");
		// Addresses Controllability by inputting an incorrect argument for the first argument, but correct for the second
                // Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));

                try {
                        Calendar.main(null);
                }
                catch (NullPointerException e) {
                        return;
                }
                fail ("NullPointerException expected.");
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
	}

	@Test (expected = NullPointerException.class)
        public void test13() {
                String data = ("12" + "");
		// Addresses Controllability by allowing a correct input for the first argument, but illegal in the second
                // Shows if I'm able to reach the correct statement for Exception handling

                System.setIn(new ByteArrayInputStream(data.getBytes()));

                try {
                        Calendar.main(null);
                }
                catch (NullPointerException e) {
                        return;
                }
                fail ("NullPointerException expected.");
		// Addresses Observability by asserting the correct Exception, otherwise shows fault in Exception handling. 
        }
}

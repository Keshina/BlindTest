import org.junit.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class quizschedTest {
    private InputStream systemIn = System.in;
    private PrintStream systemOut = System.out;
    private FileInputStream fileInputStream;
    private String appointmentsFile = "quizretakes\\quiz-appts-swe437.txt";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//
    @Test
    public void testCorrectlyAddedUser() throws Throwable {
        // This is the controllability segment as we are scripting the inputs to it from this file.
        String fileName = "testCorrectlyAddedUser.txt";
        fileInputStream = new FileInputStream(new File(fileName));
        System.setIn(fileInputStream);
        quizsched.main(null);
        // This is the observability segment as we are reading the output of the program for testing purposes.
        String fileContents = new String(Files.readAllBytes(Paths.get(appointmentsFile)));
        assertTrue("File contains correctly added user", fileContents.contains("Correct"));
    }

    @Test
    public void testIncorrectCourse() throws Throwable {
        // This is the controllability segment as we are scripting the inputs to it from this file.
        String fileName = "testIncorrectCourse.txt";
        fileInputStream = new FileInputStream(new File(fileName));
        System.setIn(fileInputStream);
        // This is the observability segment as we are reading the output of the program for testing purposes.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        quizsched.main(null);
        System.out.flush();
        System.setOut(systemOut);
        assertTrue("Course Name is incorrect", outputStream.toString().contains("Can't find the data files"));
    }

    @Test
    public void retakeIDIncorrect() throws Throwable {
        // This is the controllability segment as we are scripting the inputs to it from this file.
        String fileName = "testIncorrectRetakeID.txt";
        fileInputStream = new FileInputStream(new File(fileName));
        System.setIn(fileInputStream);
        // This is the observability segment as we are reading the output of the program for testing purposes.
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        quizsched.main(null);
        System.out.flush();
        System.setOut(systemOut);
        assertTrue("Retake ID is incorrect", outputStream.toString().contains("I don't have a retake time for that number. Please try again."));
    }

}
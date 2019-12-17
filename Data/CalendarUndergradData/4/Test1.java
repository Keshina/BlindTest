import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

class Test1 {
	/*
	 * Leap year 
	 * 
	 * The part of this test that address Observability is is when the Console outputs the correct
	 * calendar with the inputed month and year by checking with private methods such as weekday and leapyear.  
	 * 
	 * The part of this test that address Controlability is when the program asks the user to input
	 * the month and year they would like. 
	 */
	@Test
	void leapYear() {
		String[] args = {"2","2020"};
		Calendar cal1 = new Calendar();
		cal1.main(args);	}

	/*
	 * Legal month range/ year range
	 * 
	 * The part of this test that address Observability is is when the Console outputs the correct
	 * calendar with the inputed month and year by checking with private methods such as weekday and leapyear.  
	 * 
	 * The part of this test that address Controlability is when the program asks the user to input
	 * the month and year they would like. 
	 */
	@Test
	void legalMonth() {
		String[] args = {"1","2001"};
		Calendar cal1 = new Calendar();
		cal1.main(args);
	}
	@Test
	void legalMonth2() {
		String[] args = {"12","23"};
		Calendar cal1 = new Calendar();
		cal1.main(args);
	}

	/*
	 * Illegal month range
	 * 
	 * The part of this test that address Observability is is when the Console outputs the correct
	 * calendar with the inputed month and year by checking with private methods such as weekday and leapyear.  
	 * 
	 * The part of this test that address Controlability is when the program asks the user to input
	 * the month and year they would like. 
	 */
	@Test
	void illegalMonth() {
		String[] args = {"13","2001"};
		Calendar cal1 = new Calendar();
		cal1.main(args);
	}
	@Test
	void illegalMonth2() {
		String[] args = {"-1","2001"};
		Calendar cal1 = new Calendar();
		cal1.main(args);
	}
	@Test
	void illegalMonth4() {
		String[] args = {"0","2001"};
		Calendar cal1 = new Calendar();
		cal1.main(args);
	}
	
	/*
	 * Illegal year
	 * 
	 * The part of this test that address Observability is is when the Console outputs the correct
	 * calendar with the inputed month and year by checking with private methods such as weekday and leapyear.  
	 * 
	 * The part of this test that address Controlability is when the program asks the user to input
	 * the month and year they would like. 
	 */
	@Test
	void illegalYear() {
		String[] args = {"1","-1"};
		Calendar cal1 = new Calendar();
		cal1.main(args);
	}
	@Test
	void illegalYear2() {
		String[] args = {"1","10000000000"};
		Calendar cal1 = new Calendar();
		cal1.main(args);
	}
	
}

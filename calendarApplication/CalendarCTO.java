import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class CalendarCTO {

	/**
	 * @throws java.lang.Exception
	 */
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	private String month;
	private String year;

	@Before
	public void init() {
		month = "9";
		year = "2019";
		systemInMock.provideLines(month, year);
	}

	@Test
	public void testMonthAndYear() {
		int m = Integer.parseInt(month);
		int y = Integer.parseInt(year);

		String[] months = { "", "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		CalendarFaults.main(null);
		String[] test = systemOutRule.getLog().split("\n");
		assertTrue(test[0].contains(months[m] + " " + year));

	}

	@Test
	public void testMonthAndYearInput() {

		assertThrows(RuntimeException.class, () -> {
			CalendarFaults.main(null);
		});

	}

	@Test
	public void testWeekDays() {
		CalendarFaults.main(null);
		String[] test = systemOutRule.getLog().split("\n");
		assertTrue(test[1].contains("S  M Tu  W Th  F  S"));
	}

	@Test
	public void calendarDates() {
		int m = Integer.parseInt(month);
		int y = Integer.parseInt(year);
		int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		boolean isLeapYear = false;
		if ((y % 4 == 0) && (y % 100 != 0))
			isLeapYear = true;
		if (y % 400 == 0)
			isLeapYear = true;
		// check for leap year
		if (m == 2 && isLeapYear)
			days[m] = 29;

		String expectedString = "";

		// starting day of the week

		int yo = y - (14 - m) / 12;
		int x = yo + yo / 4 - yo / 100 + yo / 400;
		int mo = m + 12 * ((14 - m) / 12) - 2;
		int wd = (1 + x + (31 * mo) / 12) % 7;

		// print calendar
		for (int i = 0; i < wd; i++) {
			expectedString += "   ";
		}
		for (int i = 1; i <= days[m]; i++) {
			expectedString += String.format("%2d ", i);
			if (((i + wd) % 7 == 0) || (i == days[m]))
				expectedString += "\n";

		}

		String regex = "\\s+$";
		expectedString = expectedString.replaceAll(regex, "");

		String[] es = expectedString.split("\n");

		// Split lines and replace all trailing spaces

		for (int i = 0; i < es.length; i++) {
			if (i == 0) {
				expectedString = es[i].replaceAll(regex, "");
				expectedString = expectedString;
			}

			else
				expectedString += "\n" + es[i].replaceAll(regex, "");

		}

		CalendarFaults.main(null);
		String printed = "";
		String[] test = systemOutRule.getLog().split("S  M Tu  W Th  F  S");
		test = test[1].split("\n");

		// Split lines and replace all trailing spaces
		for (int i = 1; i < test.length; i++) {
			if (i == 1) {
				printed = test[i].replaceAll(regex, "");
			} else
				printed += "\n" + test[i].replaceAll(regex, "");
		}

		assertEquals(expectedString, printed);
	}

}

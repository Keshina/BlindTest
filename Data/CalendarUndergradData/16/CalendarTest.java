import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;


class CalendarTest {

	@Test
	public void test() {
		System.out.println();

		//We control the inputs by putting 'String data'
		//as the input into the program
		String data = "5" +
		        "\n10";
		System.setIn(new ByteArrayInputStream(data.getBytes()));

		/**We don't anticipate any exceptions so we will not
		 * assert it. (This is the "happy run")
		 */
		Calendar.main(null);
	}

	@Test
	public void test2() {
		System.out.println();

		//We control the inputs by putting 'data'
		//as the input into the program
		String data = "z" +
		        "\n2019";
		System.setIn(new ByteArrayInputStream(data.getBytes()));

		/**We are expecting an exception due to our
		 * non-numerical input, so we assert it with
		 * assertThrows(ArrayIndexOutOfBoundsException)
		 */
		assertThrows(NumberFormatException.class,
				() -> Calendar.main(null),
				"The input is a number!!");//Prints if the exception does not happen
	}

	@Test
	public void test3() {

		System.out.println();

		//We control the inputs by putting 'data'
		//as the input into the program
		String data = "13" +
		        "\n4015";

		System.setIn(new ByteArrayInputStream(data.getBytes()));

		/**We are expecting an array out of bounds exception
		 * so we assert it with
		 * assertThrows(ArrayIndexOutOfBoundsException)
		 */
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> Calendar.main(null),
				"Month/Year input is not erronious!");//Prints if the exception does not happen

	}

	@Test
	public void test4() {
		System.out.println();

		//We control the inputs by putting 'data'
		//as the input into the program
		String data = "-1" +
		        "\n2000";

		System.setIn(new ByteArrayInputStream(data.getBytes()));

		/**We are expecting an array out of bounds exception
		 * so we assert it with
		 * assertThrows(ArrayIndexOutOfBoundsException)
		 */
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> Calendar.main(null),
				"Month/Year input is not erronious!");//Prints if the exception does not happen
	}

	@Test
	public void test5() {
		System.out.println();

		//We control the inputs by putting 'data'
		//as the input into the program
		String data = "5" +
		        "\n-23224";
		System.setIn(new ByteArrayInputStream(data.getBytes()));

		/**We want to make sure there's no out of bounds
		 * exception, so we assert it with
		 * assertThrows(ArrayIndexOutOfBoundsException)
		 */
		assertThrows(ArrayIndexOutOfBoundsException.class,
				() -> Calendar.main(null),
				"Month/Year input is not erronious!");//Prints if the exception does not happen

	}
}

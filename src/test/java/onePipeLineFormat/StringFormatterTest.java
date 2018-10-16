package onePipeLineFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

//Junit 4
class StringFormatterTest {

	@Test
	void format_single_line() {
		StringFormatter stringFormatter = new StringFormatter();
		String formatted = stringFormatter.format("|1|   4|5|  7    |");
		assertEquals("| 1 | 4 | 5 | 7 |", formatted);
	}

	@Test
	void format_single_line_empty_inside() {
		StringFormatter stringFormatter = new StringFormatter();
		String formatted = stringFormatter.format("|123||123|");
		assertEquals("| 123 | | 123 |", formatted);
	}

	@Test
	void format_nothing() {
		StringFormatter stringFormatter = new StringFormatter();
		String formatted = stringFormatter.format("");
		assertEquals("", formatted);
	}

	@Test
	void format_single_no_spaces() {
		StringFormatter stringFormatter = new StringFormatter();
		String formatted = stringFormatter.format("|1|");
		assertEquals("| 1 |", formatted);
	}

	@Test
	void format_single_many_spaces() {
		StringFormatter stringFormatter = new StringFormatter();
		String formatted = stringFormatter.format("    |    1    |    ");
		assertEquals("| 1 |", formatted);
	}

	@Test
	void format_single_no_closing_pipe() {
		StringFormatter stringFormatter = new StringFormatter();
		String formatted = stringFormatter.format("    |    1        ");
		assertEquals("| 1", formatted);
	}

	@Test
	void format_double_no_closing_pipe() {
		StringFormatter stringFormatter = new StringFormatter();
		String formatted = stringFormatter.format("    |    1  |    2      ");
		assertEquals("| 1 | 2", formatted);
	}

	@Test
	void format_double() {
		StringFormatter stringFormatter = new StringFormatter();
		String formatted = stringFormatter.format("    |    1  |    2      |");
		assertEquals("| 1 | 2 |", formatted);
	}

	@Test
	void no_beginning_pipe() {
		StringFormatter stringFormatter = new StringFormatter();
		String input = "       1  |    2      |";
		String formatted = stringFormatter.format("       1  |    2      |");
		assertEquals(input, formatted);
	}

	@Test
	void single_word() {
		StringFormatter stringFormatter = new StringFormatter();
		String input = "   123";
		String formatted = stringFormatter.format("   123");
		assertEquals(input, formatted);
	}

}

package pipeTableFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TableFormatterTest {

	@Test
	void format_single_line() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[1];
		string[0] = "|123||123|";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123 | | 123 |", formatted[0]);
	}

	@Test
	void format_nothing() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[1];
		string[0] = "";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("", formatted[0]);
	}

	@Test
	void format_table() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[4];
		string[0] = "|  123|  |123  |";
		string[1] = "  |tian|  jia  |feng|123|";
		string[2] = "";
		string[3] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |     |", formatted[0]);
		assertEquals("| tian | jia | feng | 123 |", formatted[1]);
		assertEquals("", formatted[2]);
		assertEquals("| T | J | F", formatted[3]);
	}

	@Test
	void format_table_no_closing_pipe() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[4];
		string[0] = "|123||123|";
		string[1] = "|tian|jia|feng|123";
		string[2] = "|1|";
		string[3] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |    ", formatted[0]);
		assertEquals("| tian | jia | feng | 123", formatted[1]);
		assertEquals("| 1    |     |      |    ", formatted[2]);
		assertEquals("| T    | J   | F    |    ", formatted[3]);
	}

	@Test
	void format_table_no_closing_pipe_2() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[4];
		string[0] = "|123||123";
		string[1] = "|tian|jia|feng|123|";
		string[2] = "|1|";
		string[3] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |     |", formatted[0]);
		assertEquals("| tian | jia | feng | 123 |", formatted[1]);
		assertEquals("| 1    |     |      |     |", formatted[2]);
		assertEquals("| T    | J   | F    |     |", formatted[3]);
	}

	@Test
	void format_table_no_beginning_pipe_inside() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|123||123|";
		string[1] = "|tian|jia|feng|123|";
		string[2] = "   123| 234 | tian| jia| feng|";
		string[3] = "|   T|J   |F";
		string[4] = "   213";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |     |", formatted[0]);
		assertEquals("| tian | jia | feng | 123 |", formatted[1]);
		assertEquals("   123| 234 | tian| jia| feng|", formatted[2]);
		assertEquals("| T | J | F", formatted[3]);
		assertEquals("   213", formatted[4]);
	}

	@Test
	void format_table_no_beginning_pipe_inside_2() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[6];
		string[0] = "|123||123|";
		string[1] = "|tian|jia|feng|123|";
		string[2] = "   123| 234 | tian| jia| feng|";
		string[3] = "   213";
		string[4] = "|   T|J   |F";
		string[5] = "|tian|jia|f|";

		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |     |", formatted[0]);
		assertEquals("| tian | jia | feng | 123 |", formatted[1]);
		assertEquals("   123| 234 | tian| jia| feng|", formatted[2]);
		assertEquals("   213", formatted[3]);
		assertEquals("| T    | J   | F |", formatted[4]);
		assertEquals("| tian | jia | f |", formatted[5]);

	}
}

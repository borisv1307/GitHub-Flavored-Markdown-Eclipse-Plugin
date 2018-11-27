package table_formatter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TableFormatterTest {

	@Test
	public void format_single_line() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[1];
		string[0] = "|123||123|";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("|123||123|", formatted[0]);
	}

	@Test
	public void format_nothing() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[1];
		string[0] = "";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("", formatted[0]);
	}

	@Test
	public void format_table() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "123||123  ";
		string[1] = "--- |:--| ---  |";
		string[2] = "tian|  jia  |feng|123|123";
		string[3] = "";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("123  |     | 123  |     |    ", formatted[0]);
		assertEquals("---- | :-- | ---- | --- | ---", formatted[1]);
		assertEquals("tian | jia | feng | 123 | 123", formatted[2]);
		assertEquals("", formatted[3]);
		assertEquals("|   T|J   |F", formatted[4]);
	}

	@Test
	public void format_table_2() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|  123|  |123  |";
		string[1] = "|  --- |t| --:  |";
		string[2] = "  |tian|  jia  |feng|123|";
		string[3] = "123";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("|  123|  |123  |", formatted[0]);
		assertEquals("|  --- |t| --:  |", formatted[1]);
		assertEquals("  |tian|  jia  |feng|123|", formatted[2]);
		assertEquals("123", formatted[3]);
		assertEquals("|   T|J   |F", formatted[4]);
	}

	@Test
	public void format_table_3() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|  123|  |123  |";
		string[1] = "|  --- |-| --t  |";
		string[2] = "  |tian|  jia  |feng|123|";
		string[3] = "";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("|  123|  |123  |", formatted[0]);
		assertEquals("|  --- |-| --t  |", formatted[1]);
		assertEquals("  |tian|  jia  |feng|123|", formatted[2]);
		assertEquals("", formatted[3]);
		assertEquals("|   T|J   |F", formatted[4]);
	}

	@Test
	public void format_table_4() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|  123|  |123  |";
		string[1] = "|  --- |-123-| --:  |";
		string[2] = "  |tian|  jia  |feng|123|";
		string[3] = "";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("|  123|  |123  |", formatted[0]);
		assertEquals("|  --- |-123-| --:  |", formatted[1]);
		assertEquals("  |tian|  jia  |feng|123|", formatted[2]);
		assertEquals("", formatted[3]);
		assertEquals("|   T|J   |F", formatted[4]);
	}

	@Test
	public void format_table_5() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|  123|  |123  |";
		string[1] = "|  --- || --:  |";
		string[2] = "  |tian|  jia  |feng|123|";
		string[3] = "";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("|  123|  |123  |", formatted[0]);
		assertEquals("|  --- || --:  |", formatted[1]);
		assertEquals("  |tian|  jia  |feng|123|", formatted[2]);
		assertEquals("", formatted[3]);
		assertEquals("|   T|J   |F", formatted[4]);
	}

	@Test
	public void format_table_6() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|  123|  |123  |";
		string[1] = "|  --- |t--| --:  |";
		string[2] = "  |tian|  jia  |feng|123|";
		string[3] = "";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("|  123|  |123  |", formatted[0]);
		assertEquals("|  --- |t--| --:  |", formatted[1]);
		assertEquals("  |tian|  jia  |feng|123|", formatted[2]);
		assertEquals("", formatted[3]);
		assertEquals("|   T|J   |F", formatted[4]);
	}

	@Test
	public void format_table_7() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|  123|  |123  |";
		string[1] = "|  --- |--t| --:  |";
		string[2] = "  |tian|  jia  |feng|123|";
		string[3] = "";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("|  123|  |123  |", formatted[0]);
		assertEquals("|  --- |--t| --:  |", formatted[1]);
		assertEquals("  |tian|  jia  |feng|123|", formatted[2]);
		assertEquals("", formatted[3]);
		assertEquals("|   T|J   |F", formatted[4]);
	}

	@Test
	public void format_table_8() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "123||123  ";
		string[1] = "--- |:--| ---  |";
		string[2] = "tian|  jia  |feng|";
		string[3] = "";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("123  |     | 123 ", formatted[0]);
		assertEquals("---- | :-- | ----", formatted[1]);
		assertEquals("tian | jia | feng", formatted[2]);
		assertEquals("", formatted[3]);
		assertEquals("|   T|J   |F", formatted[4]);
	}

	@Test
	public void format_table_no_closing_pipe() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|123||123|";
		string[1] = "|---|---|---|---";
		string[2] = "|tian|jia|feng|123";
		string[3] = "|1|";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |     |", formatted[0]);
		assertEquals("| ---- | --- | ---- | --- |", formatted[1]);
		assertEquals("| tian | jia | feng | 123 |", formatted[2]);
		assertEquals("| 1    |     |      |     |", formatted[3]);
		assertEquals("| T    | J   | F    |     |", formatted[4]);
	}

	@Test
	public void format_table_no_closing_pipe_2() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|123||123";
		string[1] = "|:--|--:|---";
		string[2] = "|tian|jia|feng|123|";
		string[3] = "|1|";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |    ", formatted[0]);
		assertEquals("| :--- | --: | ---- | ---", formatted[1]);
		assertEquals("| tian | jia | feng | 123", formatted[2]);
		assertEquals("| 1    |     |      |    ", formatted[3]);
		assertEquals("| T    | J   | F    |    ", formatted[4]);
	}

	@Test
	public void format_table_no_beginning_pipe() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "123||123";
		string[1] = "|:--|--:|---";
		string[2] = "|tian|jia|feng|123|";
		string[3] = "|1|";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("123  |     | 123  |    ", formatted[0]);
		assertEquals(":--- | --: | ---- | ---", formatted[1]);
		assertEquals("tian | jia | feng | 123", formatted[2]);
		assertEquals("1    |     |      |    ", formatted[3]);
		assertEquals("T    | J   | F    |    ", formatted[4]);
	}

	@Test
	public void format_table_no_beginning_pipe_2() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "123||123|";
		string[1] = "|:--|--:|---";
		string[2] = "|tian|jia|feng|123|";
		string[3] = "|1|";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("123  |     | 123  |     |", formatted[0]);
		assertEquals(":--- | --: | ---- | --- |", formatted[1]);
		assertEquals("tian | jia | feng | 123 |", formatted[2]);
		assertEquals("1    |     |      |     |", formatted[3]);
		assertEquals("T    | J   | F    |     |", formatted[4]);
	}

	@Test
	public void format_table_no_beginning_pipe_inside() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[5];
		string[0] = "|123||123|";
		string[1] = "|---|---|----|---|";
		string[2] = "|tian|jia|feng|123|";
		string[3] = "   123| 234 | tian| jia| feng|";
		string[4] = "|   T|J   |F";
		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |     |      |", formatted[0]);
		assertEquals("| ---- | --- | ---- | --- | ---- |", formatted[1]);
		assertEquals("| tian | jia | feng | 123 |      |", formatted[2]);
		assertEquals("| 123  | 234 | tian | jia | feng |", formatted[3]);
		assertEquals("| T    | J   | F    |     |      |", formatted[4]);
	}

	@Test
	public void format_table_no_beginning_pipe_inside_2() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[8];
		string[0] = "|123||123|";
		string[1] = "|---|---|---|";
		string[2] = "|tian|jia|feng|123|";
		string[3] = "   123| 234 | tian| jia|";
		string[4] = "   |213";
		string[5] = "|   T|J   |F";
		string[6] = "|---|---|---|";
		string[7] = "|tian|jia|f|";

		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |     |", formatted[0]);
		assertEquals("| ---- | --- | ---- | --- |", formatted[1]);
		assertEquals("| tian | jia | feng | 123 |", formatted[2]);
		assertEquals("| 123  | 234 | tian | jia |", formatted[3]);
		assertEquals("| 213  |     |      |     |", formatted[4]);
		assertEquals("| T    | J   | F    |     |", formatted[5]);
		assertEquals("| ---  | --- | ---  |     |", formatted[6]);
		assertEquals("| tian | jia | f    |     |", formatted[7]);
	}

	@Test
	public void format_table_trim_hyphens() {
		PipeTableFormat pipeTableFormat = new PipeTableFormat();
		String[] string = new String[8];
		string[0] = "|123||123|";
		string[1] = "|--------|---------|-----|";
		string[2] = "|tian|jia|feng|123|";
		string[3] = "   123| 234 | tian| jia|";
		string[4] = "   |213";
		string[5] = "|   T|J   |F";
		string[6] = "|---|---|---|";
		string[7] = "|tian|jia|f|";

		String[] formatted = pipeTableFormat.preprocess(string);
		assertEquals("| 123  |     | 123  |     |", formatted[0]);
		assertEquals("| ---- | --- | ---- | --- |", formatted[1]);
		assertEquals("| tian | jia | feng | 123 |", formatted[2]);
		assertEquals("| 123  | 234 | tian | jia |", formatted[3]);
		assertEquals("| 213  |     |      |     |", formatted[4]);
		assertEquals("| T    | J   | F    |     |", formatted[5]);
		assertEquals("| ---  | --- | ---  |     |", formatted[6]);
		assertEquals("| tian | jia | f    |     |", formatted[7]);
	}
}

package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringArrayTest {

	@Test
	public void combineStringWithoutDeliminator() {
		String[] string = new String[3];
		string[0] = "Test String";
		string[1] = "Test";
		string[2] = "This is a test";

		String combinedString = util.StringArray.join(string, null);

		assertEquals("Test StringTestThis is a test", combinedString);
	}

	@Test
	public void combineStringWithDeliminator() {
		String[] string = new String[3];
		string[0] = "Test String";
		string[1] = "Test";
		string[2] = "This is a test";

		String combinedString = util.StringArray.join(string, " deliminator ");

		assertEquals("Test String deliminator Test deliminator This is a test deliminator ", combinedString);
	}
	
	@Test
	public void split_string_with_deliminator_1() {
		String string = "1|2|3";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("1", combinedString[0]);
		assertEquals("2", combinedString[1]);
		assertEquals("3", combinedString[2]);
	}
	
	@Test
	public void split_string_with_deliminator_2() {
		String string = "|1|2|3";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("", combinedString[0]);
		assertEquals("1", combinedString[1]);
		assertEquals("2", combinedString[2]);
		assertEquals("3", combinedString[3]);
	}
	
	@Test
	public void split_string_with_deliminator_3() {
		String string = "1|2|3|";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("1", combinedString[0]);
		assertEquals("2", combinedString[1]);
		assertEquals("3", combinedString[2]);
		assertEquals("", combinedString[3]);
	}
	
	@Test
	public void split_string_with_deliminator_4() {
		String string = "|1|2|3|";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("", combinedString[0]);
		assertEquals("1", combinedString[1]);
		assertEquals("2", combinedString[2]);
		assertEquals("3", combinedString[3]);
		assertEquals("", combinedString[4]);
	}
	
	@Test
	public void split_string_with_deliminator_5() {
		String string = "|1|2|3|||";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("", combinedString[0]);
		assertEquals("1", combinedString[1]);
		assertEquals("2", combinedString[2]);
		assertEquals("3", combinedString[3]);
		assertEquals("", combinedString[4]);
		assertEquals("", combinedString[5]);
		assertEquals("", combinedString[6]);
	}
	
	@Test
	public void split_string_with_deliminator_6() {
		String string = "|1|2|||3|";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("", combinedString[0]);
		assertEquals("1", combinedString[1]);
		assertEquals("2", combinedString[2]);
		assertEquals("", combinedString[3]);
		assertEquals("", combinedString[4]);
		assertEquals("3", combinedString[5]);
		assertEquals("", combinedString[6]);
	}
	
	@Test
	public void split_string_with_deliminator_7() {
		String string = "123|";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("123", combinedString[0]);
		assertEquals("", combinedString[1]);
	}
	
	@Test
	public void split_string_with_deliminator_8() {
		String string = "123";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("123", combinedString[0]);
	}
	
	@Test
	public void split_string_with_deliminator_9() {
		String string = "|123";

		String[] combinedString = util.StringArray.split(string, '|');

		assertEquals("", combinedString[0]);
		assertEquals("123", combinedString[1]);
	}
}

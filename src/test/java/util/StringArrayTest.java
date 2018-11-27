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
}

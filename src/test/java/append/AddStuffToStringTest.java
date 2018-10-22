package append;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddStuffToStringTest {

	@Test
	public void asterisk_italics() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.surroundSingleCharacter("word", "*");
		assertEquals("*word*", actual);
	}

	@Test
	public void underscore_italics() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.surroundSingleCharacter("word", "_");
		assertEquals("_word_", actual);
	}

	@Test
	public void accent_inline_code() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.surroundSingleCharacter("word", "`");
		assertEquals("`word`", actual);
	}

	@Test
	public void asterisk_bold() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.surroundDoubleCharacter("word", "*");
		assertEquals("**word**", actual);
	}

	@Test
	public void underscore_bold() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.surroundDoubleCharacter("word", "_");
		assertEquals("__word__", actual);
	}

	@Test
	public void asterisk_unordered_list() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.addSingleCharacter("word", "*");
		assertEquals("*word", actual);
	}

	@Test
	public void pound_header1() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.addSingleCharacter("word", "#");
		assertEquals("#word", actual);
	}

	@Test
	public void pound_header2() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.addDoubleCharacter("word", "#");
		assertEquals("##word", actual);
	}

	@Test
	public void pound_header3() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.addTripleCharacter("word", "#");
		assertEquals("###word", actual);
	}

	@Test
	public void pound_header4() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.addFourCharacter("word", "#");
		assertEquals("####word", actual);
	}

	@Test
	public void pound_header5() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.addFiveCharacter("word", "#");
		assertEquals("#####word", actual);
	}

	@Test
	public void pound_header6() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.addSixCharacter("word", "#");
		assertEquals("######word", actual);
	}

	@Test
	public void horizontal_rule() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.horizontalRule("word");
		assertEquals("word\n***\n", actual);
	}

	@Test
	public void addLink() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.linkToURL("word");
		assertEquals("[word](url \"Title\")", actual);
	}

	@Test
	public void addBlockQuote() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.blockQuote("word");
		assertEquals("\n> word\n", actual);
	}

	@Test
	public void addPicture() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.addLinkToImage("word");
		assertEquals("![word](url)", actual);
	}

	@Test
	public void addOrderedList() {
		AddStuffToString addStuffToString = new AddStuffToString();
		String actual = addStuffToString.orderedList("word\nword\n");
		assertEquals("1.  word\n1.  word\n", actual);
	}

	// TODO make a function and test for an ordered list (each new line starts with
	// "1."
}

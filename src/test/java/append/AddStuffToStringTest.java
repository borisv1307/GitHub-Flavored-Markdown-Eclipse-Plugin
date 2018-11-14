package append;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddStuffToStringTest {

	@Test
	public void asterisk_italics() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.surroundSingleCharacter("word", "*");
		assertEquals("*word*", actual);
	}

	@Test
	public void underscore_italics() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.surroundSingleCharacter("word", "_");
		assertEquals("_word_", actual);
	}

	@Test
	public void accent_inline_code() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.surroundSingleCharacter("word", "`");
		assertEquals("`word`", actual);
	}

	@Test
	public void asterisk_bold() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.surroundDoubleCharacter("word", "*");
		assertEquals("**word**", actual);
	}

	@Test
	public void underscore_bold() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.surroundDoubleCharacter("word", "_");
		assertEquals("__word__", actual);
	}

	@Test
	public void asterisk_unordered_list() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addSingleCharacter("word", "*");
		assertEquals("*word", actual);
	}

	@Test
	public void pound_header1() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addSingleCharacter("word", "#");
		assertEquals("#word", actual);
	}

	@Test
	public void pound_header2() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addDoubleCharacter("word", "#");
		assertEquals("##word", actual);
	}

	@Test
	public void pound_header3() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addTripleCharacter("word", "#");
		assertEquals("###word", actual);
	}

	@Test
	public void pound_header4() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addFourCharacter("word", "#");
		assertEquals("####word", actual);
	}

	@Test
	public void pound_header5() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addFiveCharacter("word", "#");
		assertEquals("#####word", actual);
	}

	@Test
	public void pound_header6() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addSixCharacter("word", "#");
		assertEquals("######word", actual);
	}

	@Test
	public void horizontal_rule() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.horizontalRule("word");
		assertEquals("word\n***\n", actual);
	}

	@Test
	public void addLink() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.linkToURL("word");
		assertEquals("[word](url \"Title\")", actual);
	}

	@Test
	public void addBlockQuote() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.blockQuote("word");
		assertEquals("\n> word\n", actual);
	}

	@Test
	public void addPicture() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addLinkToImage("word");
		assertEquals("![word](url)", actual);
	}

	@Test
	public void addOrderedList() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.orderedList("word\nword\n");
		assertEquals("1.  word\n1.  word\n", actual);
	}

	// TODO make a function and test for an ordered list (each new line starts with
	// "1."
}

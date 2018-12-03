package append;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddSyntaxToStringTest {

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
		assertEquals("* word", actual);
	}

	@Test
	public void pound_header1() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addSingleCharacter("word", "#");
		assertEquals("# word", actual);
	}

	@Test
	public void pound_header2() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addDoubleCharacter("word", "#");
		assertEquals("## word", actual);
	}

	@Test
	public void pound_header3() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addTripleCharacter("word", "#");
		assertEquals("### word", actual);
	}

	@Test
	public void pound_header4() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addFourCharacter("word", "#");
		assertEquals("#### word", actual);
	}

	@Test
	public void pound_header5() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addFiveCharacter("word", "#");
		assertEquals("##### word", actual);
	}

	@Test
	public void pound_header6() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addSixCharacter("word", "#");
		assertEquals("###### word", actual);
	}

	@Test
	public void horizontal_rule() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.horizontalRule("word");
		assertEquals("word\n***\n", actual);
	}

	@Test
	public void add_link() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.linkToURL("word");
		assertEquals("[word](url \"Title\")", actual);
	}

	@Test
	public void add_block_quote() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addCharToEachLine("word\nword2", ">");
		assertEquals("> word\n> word2", actual);
	}

	@Test
	public void add_picture() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addLinkToImage("word");
		assertEquals("![word](url)", actual);
	}

	@Test
	public void add_ordered_list() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addCharToEachLine("word\nword2\nword3", "1.");
		assertEquals("1. word\n1. word2\n1. word3", actual);
	}
	
 	@Test
	public void addUnorderedList() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.addCharToEachLine("word\nword2\nword3", "*");
		assertEquals("* word\n* word2\n* word3", actual);
	}
 	
 	@Test
	public void addCodeBlock() {
		AddSyntaxToString addStuffToString = new AddSyntaxToString();
		String actual = addStuffToString.codeBlock("word\nword2\nword3", "\t");
		assertEquals("\n\tword\n\tword2\n\tword3\n", actual);
	}
}

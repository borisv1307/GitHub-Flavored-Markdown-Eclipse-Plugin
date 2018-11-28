package markdown_syntax_suggestion_helper_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import markdown_syntax_suggestion_helper.MarkdownSyntaxSuggestionConstants;

public class MarkdownSyntaxSuggestionConstantsTest {

	@Test
	public void isHeader_1() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		boolean test = markdownSyntaxSuggestionConstants.isHeader("|123|123|");
		assertEquals(true, test);
	}

	@Test
	public void isHeader_2() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		boolean test = markdownSyntaxSuggestionConstants.isHeader("123|123");
		assertEquals(true, test);
	}

	@Test
	public void isHeader_3() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		boolean test = markdownSyntaxSuggestionConstants.isHeader("|123|123");
		assertEquals(true, test);
	}

	@Test
	public void isHeader_4() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		boolean test = markdownSyntaxSuggestionConstants.isHeader("123|123|");
		assertEquals(true, test);
	}

	@Test
	public void isHeader_5() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		boolean test = markdownSyntaxSuggestionConstants.isHeader("1231231|");
		assertEquals(true, test);
	}

	@Test
	public void isNotHeader_1() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		boolean test = markdownSyntaxSuggestionConstants.isHeader("   |   ");
		assertEquals(false, test);
	}

	@Test
	public void isNotHeader_2() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		boolean test = markdownSyntaxSuggestionConstants.isHeader("123123");
		assertEquals(false, test);
	}

	@Test
	public void getArrayIsNotHeader() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		String[] test = markdownSyntaxSuggestionConstants.getArrayOfConstants("123123");
		assertEquals(12, test.length);
		assertEquals("# - Heading 1", test[0]);
	}

	@Test
	public void getArrayIsHeader() {
		MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
		String[] test = markdownSyntaxSuggestionConstants.getArrayOfConstants("123|123");
		assertEquals(13, test.length);
		assertEquals("table - automatically add second line", test[0]);
	}

}

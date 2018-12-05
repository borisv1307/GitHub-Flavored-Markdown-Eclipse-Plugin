package markdown_syntax_suggestion_helper_test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import markdown_syntax_suggestion_helper.MarkdownSyntaxSuggestionHelper;

public class MarkdownSyntaxSuggestionHelperTest {

	@Test
	public void general_condition_1() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("table - automatically add second line",
				"|123|123|");
		assertEquals("|123|123|\n| --- | --- |", formatted);
	}

	@Test
	public void general_condition_2() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("table - automatically add second line",
				"|1|1|");
		assertEquals("|1|1|\n| --- | --- |", formatted);
	}

	@Test
	public void no_beginning_pipe() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("table - automatically add second line",
				"123|123|");
		assertEquals("123|123|\n--- | --- |", formatted);
	}

	@Test
	public void no_closing_pipe() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("table - automatically add second line",
				"|123|123");
		assertEquals("|123|123\n| --- | ---", formatted);
	}

	@Test
	public void no_beginning_and_closing_pipe_1() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("table - automatically add second line",
				"123|123");
		assertEquals("123|123\n--- | ---", formatted);
	}

	@Test
	public void no_beginning_and_closing_pipe_2() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("table - automatically add second line",
				"1|1");
		assertEquals("1|1\n--- | ---", formatted);
	}

	@Test
	public void do_not_have_any_content() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("table - automatically add second line",
				"|||");
		assertEquals("|||\n| --- | --- |", formatted);
	}

	@Test
	public void header1() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("# - Heading 1", "header");
		assertEquals("# header", formatted);
	}

	@Test
	public void header2() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("## - Heading 2", "header");
		assertEquals("## header", formatted);
	}

	@Test
	public void header3() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("### - Heading 3", "header");
		assertEquals("### header", formatted);
	}

	@Test
	public void header4() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("#### - Heading 4", "header");
		assertEquals("#### header", formatted);
	}

	@Test
	public void header5() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("##### - Heading 5", "header");
		assertEquals("##### header", formatted);
	}

	@Test
	public void header6() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("###### - Heading 6", "header");
		assertEquals("###### header", formatted);
	}

	@Test
	public void blockQuote() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("> Blockquote - add > to each line", "test");
		assertEquals("> test", formatted);
	}

	@Test
	public void link() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("[] - Link(inline) - [Text]", "test");
		assertEquals("[test](url \"Title\")", formatted);
	}

	@Test
	public void codeBlock() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("\t - Code block (indented with tab)",
				"test");
		assertEquals("\n\ttest\n", formatted);
	}

	@Test
	public void inlineBlock() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("`inline code` - A span of code inline",
				"test");
		assertEquals("`test`", formatted);
	}

	@Test
	public void bold() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("**bold** - bold", "test");
		assertEquals("**test**", formatted);
	}

	@Test
	public void italic() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("*italics* - italics", "test");
		assertEquals("*test*", formatted);
	}

	@Test
	public void default_condition() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("", "test");
		assertEquals("", formatted);
	}
	
	@Test
	public void ordered_list() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("1. Ordered List - add 1. to each line", "test\ntest");
		assertEquals("1. test\n1. test", formatted);
	}
	
	@Test
	public void unordered_list() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion("* Unordered List - add * to each line", "test\ntest");
		assertEquals("* test\n* test", formatted);
	}
}

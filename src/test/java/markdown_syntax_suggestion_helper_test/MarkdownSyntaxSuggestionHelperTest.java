package markdown_syntax_suggestion_helper_test;

import static org.junit.Assert.*;

import org.junit.Test;

import markdown_syntax_suggestion_helper.MarkdownSyntaxSuggestionHelper;

public class MarkdownSyntaxSuggestionHelperTest {

	@Test
	public void general_condition_1() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(12,"|123|123|");
		assertEquals("|123|123|\n| --- | --- |", formatted);
	}
	
	@Test
	public void general_condition_2() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(12,"|1|1|");
		assertEquals("|1|1|\n| --- | --- |", formatted);
	}
	
	@Test
	public void no_beginning_pipe() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(12,"123|123|");
		assertEquals("123|123|\n--- | --- |", formatted);
	}
	
	@Test
	public void no_closing_pipe() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(12,"|123|123");
		assertEquals("|123|123\n| --- | ---", formatted);
	}
	
	@Test
	public void no_beginning_and_closing_pipe_1() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(12,"123|123");
		assertEquals("123|123\n--- | ---", formatted);
	}
	
	@Test
	public void no_beginning_and_closing_pipe_2() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(12,"1|1");
		assertEquals("1|1\n--- | ---", formatted);
	}
	
	@Test
	public void do_not_have_any_content() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(12,"|||");
		assertEquals("|||\n| --- | --- |", formatted);
	}
	
	@Test
	public void header1() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(0,"header");
		assertEquals("# header", formatted);
	}
	
	@Test
	public void header2() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(1,"header");
		assertEquals("## header", formatted);
	}
	
	@Test
	public void header3() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(2,"header");
		assertEquals("### header", formatted);
	}
	
	@Test
	public void header4() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(3,"header");
		assertEquals("#### header", formatted);
	}
	
	@Test
	public void header5() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(4,"header");
		assertEquals("##### header", formatted);
	}
	
	@Test
	public void header6() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(5,"header");
		assertEquals("###### header", formatted);
	}
	
	@Test
	public void blockQuote() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(6,"test");
		assertEquals("\n> test\n", formatted);
	}
	
	@Test
	public void link() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(7,"test");
		assertEquals("[test](url \"Title\")", formatted);
	}
	
	@Test
	public void codeBlock() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(8,"test");
		assertEquals("\n\ttest\n", formatted);
	}
	
	@Test
	public void inlineBlock() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(9,"test");
		assertEquals("`test`", formatted);
	}
	
	@Test
	public void bold() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(10,"test");
		assertEquals("**test**", formatted);
	}
	
	@Test
	public void italic() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(11,"test");
		assertEquals("_test_", formatted);
	}
	
	@Test
	public void default_condition() {
		MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();
		String formatted = markdownSyntaxSuggestionHelper.applySuggestion(13,"test");
		assertEquals("", formatted);
	}

}

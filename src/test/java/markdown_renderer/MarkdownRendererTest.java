package markdown_renderer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MarkdownRendererTest {

	private static final String RESOURCE_LOCATION = "src/test/resources/commonmark/";
	MarkdownRenderer markdownRenderer;

	@Before
	public void setUp() {
		markdownRenderer = new MarkdownRenderer();
	}

	@Test
	public void italics() {
		readAndAssertFileContents("italics.html", "*test*");
	}

	@Test
	public void bold() {
		readAndAssertFileContents("bold.html", "**test**");
	}

	@Test
	public void single_paragraph() {
		readAndAssertFileContents("single-paragraph.html", "test\ntest");
	}

	@Test
	public void two_paragraphs() {
		readAndAssertFileContents("two-paragraphs.html", "test\n\ntest");
	}

	@Test
	public void simple_table() {
		readAndAssertFileContents("simple-table.html",
				"| First Header  | Second Header |\n" + "| ------------- | ------------- |\n"
						+ "| Content Cell  | Content Cell  |\n" + "| Content Cell  | Content Cell  |");
	}

	@Test
	public void align_attributed_table() {
		readAndAssertFileContents("align-attributed-table.html",
				"| Left-aligned | Center-aligned | Right-aligned |\n"
						+ "| :---         |     :---:      |          ---: |\n"
						+ "| git status   | git status     | git status    |\n"
						+ "| git diff     | git diff       | git diff      |");
  }
  
  @Test
	public void unorderedList() {
		readAndAssertFileContents("unorderedList.html", "* test\n* test\n* test\n");
	}

	@Test
	public void horizontalRule() {
		readAndAssertFileContents("horizontalRule.html", "***");
	}

	@Test
	public void code() {
		readAndAssertFileContents("code.html", "`test`");
	}

	@Test
	public void header1() {
		readAndAssertFileContents("header1.html", "# H1 Test");
	}

	@Test
	public void header2() {
		readAndAssertFileContents("header2.html", "## H2 Test");
	}

	@Test
	public void header3() {
		readAndAssertFileContents("header3.html", "### H3 Test");
	}

	@Test
	public void header4() {
		readAndAssertFileContents("header4.html", "#### H4 Test");
	}

	@Test
	public void orderedList() {
		readAndAssertFileContents("orderedList.html", "1. test\n1. test\n1. test\n");
	}

//	TODO add CSS of gfm blockquote
//	.markdown-body blockquote {
//		padding: 0 1em;
//		color: #6a737d;
//    	border-left: 0.25em solid #dfe2e5;
//	}

	@Test
	public void blockquote() {
		readAndAssertFileContents("blockquote.html", "> test");
	}

	@Test
	public void backticks() {
		readAndAssertFileContents("backticks.html", "```\n<\n >\n```");
	}

	@Test
	public void tildes() {
		readAndAssertFileContents("tildes.html", "~~~\n<\n >\n~~~");
	}

	@Test
	public void notEnoughBackticks() {
		readAndAssertFileContents("notEnoughBackticks.html", "``\nfoo\n``");
	}

	@Test
	public void unclosedBlocks() {
		readAndAssertFileContents("unclosedBlocks.html", "> ```\n> aaa\n\nbbb");
	}

	// Four spaces indentation produces an indented code block
	@Test
	public void fourSpacesIndentation() {
		readAndAssertFileContents("fourSpacesIndentation.html", "    ```\n    aaa\n    ```");
	}

	// Closing fences may be indented by 0-3 spaces, and their indentation need not
	// match that of the opening fence
	@Test
	public void indentedClosingFence() {
		readAndAssertFileContents("indentedClosingFence.html", "   ```\naaa\n  ```");
	}

	// Code fences (opening and closing) cannot contain internal spaces
	@Test
	public void containingSpaces() {
		readAndAssertFileContents("containingSpaces.html", "``` ```\naaa");
	}

	// Closing code fences cannot have info strings
	@Test
	public void closingFenceContainStrings() {
		readAndAssertFileContents("closingFenceContainStrings.html", "```\n``` aaa\n```");
	}

	private void readAndAssertFileContents(String filename, String contents) {
		try {
			String buffer = FileReaderHelper.readFileToBuffer(RESOURCE_LOCATION + filename);
			assertEquals(buffer, markdownRenderer.render(contents));
		} catch (IOException e) {
			fail();
		}
	}

}

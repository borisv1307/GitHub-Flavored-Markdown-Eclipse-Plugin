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
	public void indented_code_block() {
		readAndAssertFileContents("indented-code-block-e76.html", "    a simple\n      indented code block");
	}

	@Test
	public void indented_code_block_first_line() {
		readAndAssertFileContents("indented-code-block-e85.html", "        foo\n    bar");
	}

	@Test
	public void unorderedList() {
		readAndAssertFileContents("unorderedList.html", "* test\n* test\n* test\n");
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
	public void unordered_list() {
		readAndAssertFileContents("unorderedList.html", "* test\n* test\n* test\n");
	}

	@Test
	public void horizontal_rule() {
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
	public void header5() {
		readAndAssertFileContents("header5.html", "##### H5 Test");
	}

	@Test
	public void header6() {
		readAndAssertFileContents("header6.html", "###### H6 Test");
	}

	@Test
	public void ordered_list() {
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
	public void not_enough_backticks() {
		readAndAssertFileContents("notEnoughBackticks.html", "``\nfoo\n``");
	}

	@Test
	public void unclosed_blocks() {
		readAndAssertFileContents("unclosedBlocks.html", "> ```\n> aaa\n\nbbb");
	}

	// Four spaces indentation produces an indented code block
	@Test
	public void four_spaces_indentation() {
		readAndAssertFileContents("fourSpacesIndentation.html", "    ```\n    aaa\n    ```");
	}

	// Closing fences may be indented by 0-3 spaces, and their indentation need not
	// match that of the opening fence
	@Test
	public void closing_fence_intended_and_need_not_match_opening_fence() {
		readAndAssertFileContents("indentedClosingFence.html", "   ```\naaa\n  ```");
	}

	// Code fences (opening and closing) cannot contain internal spaces
	@Test
	public void code_fence_cannot_contain_internal_spaces() {
		readAndAssertFileContents("containingSpaces.html", "``` ```\naaa");
	}

	// Closing code fences cannot have info strings
	@Test
	public void closing_fence_cannot_contain_strings() {
		readAndAssertFileContents("closingFenceContainStrings.html", "```\n``` aaa\n```");
	}

	@Test
	public void strikethrough() {
		readAndAssertFileContents("strikethrough.html", "~~test~~");
	}

	@Test
	public void standard_image_1() {
		readAndAssertFileContents("standardImage1.html", "![foo](/url \"title\")");
	}

	@Test
	public void standard_image_2() {
		readAndAssertFileContents("standardImage2.html",
				"![foo *bar*]\n" + "\n" + "[foo *bar*]: train.jpg \"train & tracks\"");
	}

	@Test
	public void standard_image_3() {
		readAndAssertFileContents("standardImage3.html", "![foo ![bar](/url)](/url2)");
	}

	@Test
	public void standard_image_4() {
		readAndAssertFileContents("standardImage3.html", "![foo [bar](/url)](/url2)");
	}

	@Test
	public void reference_style() {
		readAndAssertFileContents("referenceStyleImage.html", "![foo][bar]\n" + "\n" + "[bar]: /url");
	}

	@Test
	public void collapsed() {
		readAndAssertFileContents("collapsedImage.html", "![*foo* bar][]\n" + "\n" + "[*foo* bar]: /url \"title\"");
	}

	@Test
	public void shortcut1() {
		readAndAssertFileContents("standardImage1.html", "![foo]\n" + "\n" + "[foo]: /url \"title\"");
	}

	@Test
	public void shortcut2() {
		readAndAssertFileContents("collapsedImage.html", "![*foo* bar]\n" + "\n" + "[*foo* bar]: /url \"title\"");
	}

	@Test
	public void case_insensitive() {
		readAndAssertFileContents("caseInsensitive.html", "![Foo]\n" + "\n" + "[foo]: /url \"title\"");
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

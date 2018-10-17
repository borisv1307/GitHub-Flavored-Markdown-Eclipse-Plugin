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

	private void readAndAssertFileContents(String filename, String contents) {
		try {
			String buffer = FileReaderHelper.readFileToBuffer(RESOURCE_LOCATION + filename);
			assertEquals(buffer, markdownRenderer.render(contents));
		} catch (IOException e) {
			fail();
		}
	}

}

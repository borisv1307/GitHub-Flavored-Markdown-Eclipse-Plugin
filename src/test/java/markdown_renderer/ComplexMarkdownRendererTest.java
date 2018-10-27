package markdown_renderer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ComplexMarkdownRendererTest {

	private static final String RESOURCE_LOCATION = "src/test/resources/commonmark/";
	MarkdownRenderer markdownRenderer;

	@Before
	public void setUp() {
		markdownRenderer = new MarkdownRenderer();
	}

	@Test
	public void combine_excess_italics_with_bold() {
		readAndAssertFileContents("edgeCase_CombineExcessItalicsWithBold.html", "__***_**Test**_***__");
	}

	@Test
	public void seven_pound_symbols() {
		readAndAssertFileContents("edgeCase_SevenPoundSymbols.html", "####### Test");
	}

	@Test
	public void combine_italics_with_bold() {
		readAndAssertFileContents("edgeCase_CombineItalicsWithBold.html", "_**Test**_");
	}

	@Test
	public void combine_unordered_ordered_list() {
		readAndAssertFileContents("edgeCase_CombineUnorderedOrderedList.html", "1. * Test");
	}

	@Test
	public void emphasis_plus_inline_code() {
		readAndAssertFileContents("edgeCase_EmphasisPlusInlineCode.html", "*`text`*");
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
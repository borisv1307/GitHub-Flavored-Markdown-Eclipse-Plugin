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
	public void CombineExcessItalicsWithBold() {
		readAndAssertFileContents("edgeCase_CombineExcessItalicsWithBold.html", "__***_**Test**_***__");
	}

	@Test
	public void SevenPoundSymbols() {
		readAndAssertFileContents("edgeCase_SevenPoundSymbols.html", "####### Test");
	}

	@Test
	public void CombineItalicsWithBold() {
		readAndAssertFileContents("edgeCase_CombineItalicsWithBold.html", "_**Test**_");
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
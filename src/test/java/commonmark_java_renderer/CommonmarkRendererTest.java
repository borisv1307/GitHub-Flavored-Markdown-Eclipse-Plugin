package commonmark_java_renderer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

public class CommonmarkRendererTest {

	@Test
	public void italics() {
		CommonmarkRenderer cr = new CommonmarkRenderer();

		try {
			String buffer = FileReaderHelper.readFileToBuffer("src/test/resources/commonmark/italics.html");
			assertEquals(buffer, cr.render("*test*"));
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void bold() {
		CommonmarkRenderer cr = new CommonmarkRenderer();

		try {
			String buffer = FileReaderHelper.readFileToBuffer("src/test/resources/commonmark/bold.html");
			assertEquals(buffer, cr.render("**test**"));
    } catch (IOException e) {
			fail();
		}
	}
      
  @Test  
	public void single_paragraph() {
		CommonmarkRenderer cr = new CommonmarkRenderer();

		try {
			String buffer = FileReaderHelper.readFileToBuffer("src/test/resources/commonmark/single-paragraph.html");
			assertEquals(buffer, cr.render("test\ntest"));
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void two_paragraphs() {
		CommonmarkRenderer cr = new CommonmarkRenderer();

		try {
			String buffer = FileReaderHelper.readFileToBuffer("src/test/resources/commonmark/two-paragraphs.html");
			assertEquals(buffer, cr.render("test\n\ntest"));
		} catch (IOException e) {
			fail();
		}
	}
}

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
			String buffer = FileReaderHelper.readFileToBuffer("resources/italics.html");
			assertEquals(buffer, cr.render("*test*"));
		} catch (IOException e) {
			fail();
		}
	}

}

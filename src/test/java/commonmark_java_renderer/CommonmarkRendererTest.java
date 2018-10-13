package commonmark_java_renderer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommonmarkRendererTest {

	@Test
	public void test_italics() {
		CommonmarkRenderer cr = new CommonmarkRenderer();
		assertEquals(cr.render("*test*"), "<p><em>test</em></p>\n");
	}

}

package github_api;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import github_api.MarkdownRenderer;

public class MarkdownRendererTest {

	@Test
	public void render_markdown() {
		MarkdownRenderer mr = new MarkdownRenderer();
		mr.setRawBuf("*hello*");
		mr.renderMarkdown();
		String output = mr.getRenderedMarkdown();
		assertEquals("<p><em>hello</em></p>\n", output);
	}

	@Test
	public void render_markdown_empty_raw_buffer() {
		MarkdownRenderer mr = new MarkdownRenderer();
		assertEquals(mr.renderMarkdown(), false);
	}

	@Test
	public void render_markdown_bad_connection() {
		MarkdownRenderer mr = new MarkdownRenderer();
		mr.setAPIEndpointURL("not!url");
		mr.setRawBuf("*hello*");
		assertEquals(mr.renderMarkdown(), false);
	}
}

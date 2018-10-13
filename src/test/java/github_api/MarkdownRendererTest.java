package github_api;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MarkdownRendererTest {

	MarkdownRenderer markdownRenderer;

	@Before
	public void setUp() {
		markdownRenderer = new MarkdownRenderer(new GithubMarkdownAPIConnection());
	}

	@Test
	public void string_buffers_empty_upon_creation() {
		assertTrue(markdownRenderer.getRenderedMarkdown().equals(""));
	}

}

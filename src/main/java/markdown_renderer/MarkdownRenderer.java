package markdown_renderer;

import java.util.Arrays;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownRenderer {

	private List<Extension> extensions = null;
	private Parser parser = null;
	private HtmlRenderer renderer = null;

	public String render(String content) {
		Node document = parser.parse(content);
		return renderer.render(document);
	}

	public MarkdownRenderer() {
		this.extensions = Arrays.asList(TablesExtension.create(), StrikethroughExtension.create());
		this.parser = Parser.builder().extensions(extensions).build();
		this.renderer = HtmlRenderer.builder().extensions(extensions).build();
	}
}

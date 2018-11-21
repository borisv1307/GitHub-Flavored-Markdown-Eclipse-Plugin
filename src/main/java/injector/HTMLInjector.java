package injector;

import java.io.IOException;

import wrapper.BufferedReaderWrapper;

public class HTMLInjector {
	private CSSInjector cssInjector;

	public HTMLInjector(BufferedReaderWrapper bufferedReaderWrapper) throws IOException {
		cssInjector = new CSSInjector(bufferedReaderWrapper);

	}

	public String inject(String htmlFileName, String renderedMarkdown) {
		return "<!DOCTYPE html>\n" + "<html>" + "<head>\n" + "<meta charset=\"utf-8\">\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "\n<style>\n"
				+ cssInjector.getCSS() + "\n</style>\n" + "<title>" + htmlFileName + "</title>\n"
				+ "<article class=\"markdown-body\">\n" + "</head>" + "<body>" + renderedMarkdown + "</body>\n"
				+ "</html>\n" + "<article>";
	}
}

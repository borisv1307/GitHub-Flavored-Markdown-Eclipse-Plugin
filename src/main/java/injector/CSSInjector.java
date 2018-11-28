package injector;

import java.io.BufferedReader;
import java.io.IOException;

import wrapper.BufferedReaderWrapper;

public class CSSInjector {

	static final String CSS_LOCATION = "/src/main/java/github_markdown_css/github-markdown.css";
	private StringBuilder cssContent;

	public CSSInjector(BufferedReaderWrapper bufferedReaderWrapper) throws IOException {
		cssContent = new StringBuilder();

		BufferedReader br = bufferedReaderWrapper.getFileFromLocation(CSS_LOCATION);
		String line = null;

		while ((line = br.readLine()) != null) {
			cssContent.append(line);
			cssContent.append("\n");
		}

		br.close();
	}

	public String getCSS() {
		return cssContent.toString();
	}
}

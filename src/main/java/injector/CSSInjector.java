package injector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CSSInjector {

	private static final String CSS_LOCATION = "/src/main/java/github_markdown_css/github-markdown.css";
	private static StringBuffer cssContent;

	public CSSInjector() {
		if (cssContent == null || cssContent.length() == 0) {
			cssContent = new StringBuffer();

			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(CSS_LOCATION)));
				String line = null;

				while ((line = br.readLine()) != null) {
					cssContent.append(line);
					cssContent.append("\n");
				}

				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("File '" + CSS_LOCATION + "' not found");
			} catch (IOException e) {
				System.out.println("Unable to read file '" + CSS_LOCATION + "'");
			}
		}
	}

	public String getCSS() {
		return cssContent.toString();
	}
}

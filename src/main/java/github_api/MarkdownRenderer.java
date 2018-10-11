package github_api;
import java.io.IOException;
import java.net.HttpURLConnection;

public class MarkdownRenderer {
	private StringBuffer rawBuffer;
	private StringBuffer markdownBuffer;
	private GithubMarkdownAPIConnection githubMarkdownAPIConnection;

	MarkdownRenderer(GithubMarkdownAPIConnection githubMarkdownAPIConnection) {
		this.githubMarkdownAPIConnection = githubMarkdownAPIConnection;
		this.markdownBuffer = new StringBuffer();
		this.rawBuffer = new StringBuffer();
	}

	public void setAPIEndpointURL(String url) {
		githubMarkdownAPIConnection.setEndpointURL(url);
	}

	public void setRawBuf(String text) {
		rawBuffer.append(text);
	}

	public boolean renderMarkdown() {
		if (rawBuffer.length() < 1) {
			return false;
		}

		HttpURLConnection con;

		try {
			githubMarkdownAPIConnection.connect();
			githubMarkdownAPIConnection.post(rawBuffer.toString());
		} catch (IOException e) {
			return false;
		}

		con = githubMarkdownAPIConnection.getConnection();

		APIDataRetriever dr = new APIDataRetriever();
		markdownBuffer.append(dr.getDataFromConnection(con));

		con.disconnect();
		return true;
	}

	public String getRenderedMarkdown() {
		return markdownBuffer.toString();
	}

}

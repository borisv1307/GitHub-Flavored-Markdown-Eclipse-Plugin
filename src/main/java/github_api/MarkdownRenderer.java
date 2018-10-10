package github_api;
import java.io.IOException;
import java.net.HttpURLConnection;

public class MarkdownRenderer {
	private StringBuffer rbuf = new StringBuffer();
	private StringBuffer mbuf = new StringBuffer();
	private GithubMarkdownAPIConnection gmac = null;

	MarkdownRenderer() {
		gmac = new GithubMarkdownAPIConnection();
	}

	public void setAPIEndpointURL(String url) {
		gmac.setEndpointURL(url);
	}

	public void setRawBuf(String text) {
		rbuf.append(text);
	}

	public boolean renderMarkdown() {
		if (rbuf.length() < 1) {
			return false;
		}

		HttpURLConnection con;

		try {
			gmac.connect();
			gmac.post(rbuf.toString());
		} catch (IOException e) {
			return false;
		}

		con = gmac.getConnection();

		APIDataRetriever dr = new APIDataRetriever();
		mbuf.append(dr.getDataFromConnection(con));

		con.disconnect();
		return true;
	}

	public String getRenderedMarkdown() {
		return mbuf.toString();
	}

}

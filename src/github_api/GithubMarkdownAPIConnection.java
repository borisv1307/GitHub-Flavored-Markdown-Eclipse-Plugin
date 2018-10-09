package github_api;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GithubMarkdownAPIConnection {

	private String endpoint_url = "https://api.github.com/markdown/raw";
	private HttpURLConnection con = null;

	public void setEndpointURL(String url) {
		endpoint_url = url;
	}

	public void connect() throws IOException {
		if (con != null) {
			return;
		}
		try {
			URL api_endpoint = new URL(endpoint_url);
			this.con = (HttpURLConnection) api_endpoint.openConnection();
			this.con.setRequestMethod("POST");
			this.con.setRequestProperty("Content-Type", "text/plain");
			this.con.setDoOutput(true);
			return;
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
			throw e;
		}
	}

	public void post(String buffer) throws IOException {
		try {
			DataOutputStream out = new DataOutputStream(this.con.getOutputStream());
			out.write(buffer.toString().getBytes());
			out.flush();
			out.close();
			return;
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
			throw e;
		}
	}

	public HttpURLConnection getConnection() {
		return this.con;
	}
}

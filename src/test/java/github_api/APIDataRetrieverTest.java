package github_api;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.Test;

import github_api.APIDataRetriever;
import github_api.GithubMarkdownAPIConnection;

public class APIDataRetrieverTest {

	@Test
	public void get_data_from_connection_test() {
		String buffer = "*test*";
		String recieved = null;
		GithubMarkdownAPIConnection gmac = new GithubMarkdownAPIConnection();
		HttpURLConnection con;

		try {
			gmac.connect();
			gmac.post(buffer.toString());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
			return;
		}

		con = gmac.getConnection();
		if (con == null) {
			return;
		}

		APIDataRetriever dr = new APIDataRetriever();
		recieved = dr.getDataFromConnection(con);

		assertEquals(recieved, "<p><em>test</em></p>\n");
	}

	@Test
	public void get_data_from_connection_fail_test() {
		HttpURLConnection con = null;

		APIDataRetriever dr = new APIDataRetriever();
		String recieved = dr.getDataFromConnection(con);

		assertEquals(recieved, null);
	}

	@Test
	public void get_data_from_connection_fail_http_ok() {
		GithubMarkdownAPIConnection gmac = new GithubMarkdownAPIConnection();
		gmac.setEndpointURL("https://api.github.com/nonexistant");
		try {
			gmac.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpURLConnection con = gmac.getConnection();
		APIDataRetriever dr = new APIDataRetriever();
		String recieved = dr.getDataFromConnection(con);

		assertEquals(recieved, null);
	}
}

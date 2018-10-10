package github_api;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import github_api.GithubMarkdownAPIConnection;

public class GithubMarkdownAPIConnectionTest {

	@Test
	public void connect_and_post_malformed_url() {
		GithubMarkdownAPIConnection gmac = new GithubMarkdownAPIConnection();
		gmac.setEndpointURL("not!url");
		try {
			gmac.connect();
			gmac.post("*test*");
		} catch (IOException e) {
			assertEquals("no protocol: not!url", e.getMessage());
		}
	}

	@Test
	public void second_connection_test() {
		GithubMarkdownAPIConnection gmac = new GithubMarkdownAPIConnection();
		long date1;
		long date2;

		try {
			gmac.connect();
			date1 = gmac.getConnection().getDate();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			gmac.connect();
			date2 = gmac.getConnection().getDate();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		assertEquals(date1, date2);
	}

	// TODO add test for success
	@Test
	public void connect_test() {
		GithubMarkdownAPIConnection gmac = new GithubMarkdownAPIConnection();

		try {
			gmac.connect();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		try {
			assertEquals(gmac.getConnection().getResponseCode(), java.net.HttpURLConnection.HTTP_OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

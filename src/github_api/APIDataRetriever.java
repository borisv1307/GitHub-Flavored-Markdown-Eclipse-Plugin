package github_api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class APIDataRetriever {
	public String getDataFromConnection(HttpURLConnection con) {
		StringBuffer buf = new StringBuffer();
		try {
			if (con != null && con.getResponseCode() == HttpURLConnection.HTTP_OK) {

				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;

				while ((line = br.readLine()) != null) {
					buf.append(line).append("\n");
				}
				br.close();
			} else {
				System.out.println("POST failed: " + (con == null ? "connection = null" : con.getResponseMessage()));
				return null;
			}
		} catch (IOException e) {
			System.out.println("IOException occured when retrieving data from api: " + e.toString());
		}
		return buf.toString();
	}
}

package wrapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedReaderWrapper {

	public BufferedReader getFileFromLocation(String location) {
		return new BufferedReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(location)));
	}
}

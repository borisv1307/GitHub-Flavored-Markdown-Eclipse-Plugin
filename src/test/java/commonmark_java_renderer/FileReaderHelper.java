package commonmark_java_renderer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReaderHelper {
	public static String readFileToBuffer(String filename) throws FileNotFoundException, IOException {
		try {
			InputStream is = new FileInputStream(filename);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = null;
			StringBuffer sb = new StringBuffer();

			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}

			br.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			System.out.println("File '" + filename + "' not found");
			throw e;
		} catch (IOException e) {
			System.out.println("Unable to read file '" + filename + "'");
			throw e;
		}
	}
}

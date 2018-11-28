package util;

public class StringArray {

	public static String join(String[] strings, String deliminator) {
		StringBuilder builder = new StringBuilder();
		for (String line : strings) {
			builder.append(line);
			if (deliminator != null)
				builder.append(deliminator);
		}
		return builder.toString();
	}
}

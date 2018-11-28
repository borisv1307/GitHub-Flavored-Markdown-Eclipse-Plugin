package util;

public class StringArray {

	private StringArray() {

	}

	public static String join(String[] strings, String deliminator) {
		StringBuilder builder = new StringBuilder();
		for (String line : strings) {
			builder.append(line);
			if (deliminator != null)
				builder.append(deliminator);
		}
		return builder.toString();
	}

	public static String[] split(String string, char deliminator) {
		int lengthOfString = string.length();
		int numOfComponent = 1;
		for (int i = 0; i < lengthOfString; i++) {
			if (string.charAt(i) == deliminator) {
				numOfComponent++;
			}
		}
		String[] stringArray = new String[numOfComponent];
		if (numOfComponent == 1) {
			stringArray[0] = string;
			return stringArray;
		}
		int pointer = 0;
		int counter = 0;
		int i = 0;
		while (counter < numOfComponent - 1) {
			if (string.charAt(i) == deliminator) {
				stringArray[counter] = string.substring(pointer, i);
				pointer = i + 1;
				counter++;
			}
			i++;
		}

		stringArray[numOfComponent - 1] = string.substring(pointer, string.length());
		return stringArray;
	}

}

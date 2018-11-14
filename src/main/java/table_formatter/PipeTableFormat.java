package table_formatter;

import java.util.Arrays;

import string_formatter.StringFormatter;

public class PipeTableFormat {
	public static String[] preprocess(String[] string) { // divide string into several independent tables
		int ending = 0;
		int beginning = 0;
		while (ending < string.length) {
			int count = 0;
			for (int i = ending; i < string.length; i++) {
				ending = i;
				if (string[i].trim().isEmpty() || string[i].trim().charAt(0) != '|') {
					break;
				} else {
					count++;
				}
			}
			int temp = beginning + count;
			String[] table = Arrays.copyOfRange(string, beginning, temp);
			table = format(table);
			for (int i = beginning; i < temp; i++) {
				string[i] = table[i - beginning];
			}
			beginning = ++ending;
		}
		return string;
	}

	public static String[] format(String[] string) {
		StringFormatter stringFormatter = new StringFormatter();
		if (string.length == 1) {
			string[0] = stringFormatter.format(string[0]);
			return string;
		}
		String[][] format = new String[string.length][];
		int lengthOfComponents = 0;
		for (int i = 0; i < string.length; i++) {
			string[i] = stringFormatter.format(string[i]); // format every line
			format[i] = string[i].split("\\|", -1);
			if (format[i].length > lengthOfComponents) { // calculate the number of
															// columns
				lengthOfComponents = format[i].length;
			}
		}
		int length = format.length;
		for (int i = 0; i < length; i++) {
			format[i] = Arrays.copyOf(format[i], lengthOfComponents);
		}
		for (int m = 1; m < lengthOfComponents; m++) {
			int lengthOfColumn = getLengthOfColumn(format, m);
			for (int n = 0; n < length; n++) {
				if (format[n][m] != null) {
					int numberOfSpaces = lengthOfColumn - format[n][m].length();
					format[n][m] += addSpaces(numberOfSpaces);
				} else {
					format[n][m] = addSpaces(lengthOfColumn);
				}
			}
		}
		for (int i = 0; i < length; i++) {
			join(string, format, i);
		}
		return string;
	}

	private static void join(String[] string, String[][] format, int i) {
		string[i] = "";
		int lengthOfFormatI = format[i].length;
		for (int j = 0; j < lengthOfFormatI - 1; j++) {
			string[i] += format[i][j] + "|";
		}
		if (!format[i][lengthOfFormatI - 1].isEmpty()) {
			string[i] += format[i][lengthOfFormatI - 1] + "|";
			string[i] = string[i].substring(0, string[i].length() - 1);
		}
	}

	private static int getLengthOfColumn(String[][] format, int m) {
		int length = 0;
		for (int n = 0; n < format.length; n++) {
			if (format[n][m] != null && format[n][m].length() > length) {
				length = format[n][m].length();
			}
		}
		return length;
	}

	private static String addSpaces(int numberOfSpaces) {
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < numberOfSpaces; i++) {

			strB.append(" ");
		}
		return strB.toString();
	}
}

package onePipeLineFormat;

public class StringFormatter {

	public String format(String string) {
		if (string.isEmpty()) { // input nothing
			return "";
		}
		String original_line = string;
		string = string.trim();
		String formatted_line = "";
		String[] temp = string.split("\\|", -1);
		if (!temp[0].isEmpty()) { // no beginning pipe
			return original_line;
		}
		for (int i = 1; i < temp.length - 1; i++) {
			if (!temp[i].isEmpty()) {
				temp[i] = " " + temp[i].trim() + " ";
			} else {
				temp[i] = " "; // |123||123| -> |123| | 123|
			}
		}
		if (!temp[temp.length - 1].isEmpty()) { // no closing pipe
			temp[temp.length - 1] = " " + temp[temp.length - 1].trim();
		}
		formatted_line = String.join("|", temp);
		return formatted_line;
	}
}

package onePipeLineFormat;

import org.junit.platform.commons.util.StringUtils;

public class StringFormatter {

	public String format(String string) {
		if (StringUtils.isBlank(string)) { // input nothing
			return "";
		}
		String original_line = string;
		string = string.trim();
		String formatted_line = "";
		String[] temp = string.split("\\|", -1);
		if (!StringUtils.isBlank(temp[0])) { // no beginning pipe
			return original_line;
		}
		for (int i = 1; i < temp.length - 1; i++) {
			if (!StringUtils.isBlank(temp[i])) {
				temp[i] = " " + temp[i].trim() + " ";
			} else {
				temp[i] = " "; // |123||123| -> |123| | 123|
			}
		}
		if (!StringUtils.isBlank(temp[temp.length - 1])) { // no closing pipe
			temp[temp.length - 1] = " " + temp[temp.length - 1].trim();
		}
		formatted_line = String.join("|", temp);
		return formatted_line;
	}
}

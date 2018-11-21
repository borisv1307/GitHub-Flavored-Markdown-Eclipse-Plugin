package string_formatter;

public class StringFormatter {

	public String format(String string) {
		if (string.isEmpty()) { // input nothing
			return "";
		}
		String originalLine = string;
		string = string.trim();
		String formattedLine = "";
		String[] temp = string.split("\\|", -1);
		int length = temp.length;
		if(temp.length == 1) {
			return originalLine;
		}
		if(!temp[0].isEmpty()) {
			temp[0] = temp[0].trim() + " ";
		}
		for (int i = 1; i < length - 1; i++) {
			if (!temp[i].isEmpty()) {
				temp[i] = " " + temp[i].trim() + " ";
			} else {
				temp[i] = " "; // |123||123| -> |123| | 123|
			}
		}
		if (!temp[length - 1].isEmpty()) { // no closing pipe
			temp[length - 1] = " " + temp[length - 1].trim();
		}
		formattedLine = join(temp, length);
		return formattedLine.substring(0,formattedLine.length()-1);
	}
	
	private String join(String[] temp, int length) {
		StringBuilder str = new StringBuilder();
		for(int i = 0;i<length;i++) {
			str.append(temp[i]+"|");
		}
		return str.toString();
	}
}

package append;

public class AddStuffToString {

	public String surroundSingleCharacter(String string, String character) {
		string = character + string + character;
		return string;
	}

	public String surroundDoubleCharacter(String str, String character) {
		str = character + character + str + character + character;
		return str;
	}

	public String addSingleCharacter(String str, String character) {
		str = character + str;
		return str;
	}

	public String addDoubleCharacter(String str, String character) {
		str = character + character + str;
		return str;
	}

	public String addTripleCharacter(String str, String character) {
		str = character + character + character + str;
		return str;
	}

	public String horizontalRule(String str) {
		str = str + "\n***\n";
		return str;
	}

	public String linkToURL(String str) {
		str = "[" + str + "](Replace with Link)";
		return str;
	}

	public String blockQuote(String str) {
		str = "\n> " + str + "\n";
		return str;
	}

	public String addLinkToImage() {
		String str = "![Alt Text](url)";
		return str;
	}

	public String orderedList(String str) {
		str = str.replaceAll("(\n)", "\n1.  ");
		str = "1.  " + str;
		str = str.substring(0, str.length() - 4);
		return str;
	}
}

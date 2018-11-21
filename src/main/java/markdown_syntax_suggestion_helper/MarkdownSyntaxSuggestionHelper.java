package markdown_syntax_suggestion_helper;

import append.AddSyntaxToString;

public class MarkdownSyntaxSuggestionHelper {
	private AddSyntaxToString syntax = new AddSyntaxToString();
	private String markdownSyntaxSuggestionSelection = "";

	public String applySuggestion(String string, String selectedContent) {
		switch (string) {
		case "# - Heading 1":
			markdownSyntaxSuggestionSelection = syntax.addSingleCharacter(selectedContent, "#");
			break;
		case "## - Heading 2":
			markdownSyntaxSuggestionSelection = syntax.addDoubleCharacter(selectedContent, "#");
			break;
		case "### - Heading 3":
			markdownSyntaxSuggestionSelection = syntax.addTripleCharacter(selectedContent, "#");
			break;
		case "#### - Heading 4":
			markdownSyntaxSuggestionSelection = syntax.addFourCharacter(selectedContent, "#");
			break;
		case "##### - Heading 5":
			markdownSyntaxSuggestionSelection = syntax.addFiveCharacter(selectedContent, "#");
			break;
		case "###### - Heading 6":
			markdownSyntaxSuggestionSelection = syntax.addSixCharacter(selectedContent, "#");
			break;
		case "> - Blockquote":
			markdownSyntaxSuggestionSelection = syntax.blockQuote(selectedContent);
			break;
		case "[] - Link(inline) - [Text]":
			markdownSyntaxSuggestionSelection = syntax.linkToURL(selectedContent);
			break;
		case "\t - Code block (indented with tab)":
			markdownSyntaxSuggestionSelection = syntax.surroundDoubleCharacter(selectedContent, "\n\t", "\n");
			break;
		case "`inline code` - A span of code inline":
			markdownSyntaxSuggestionSelection = syntax.surroundSingleCharacter(selectedContent, "`");
			break;
		case "**strong** - strong":
			markdownSyntaxSuggestionSelection = syntax.surroundSingleCharacter(selectedContent, "**");
			break;
		case "_emphasis_ - emphasis":
			markdownSyntaxSuggestionSelection = syntax.surroundSingleCharacter(selectedContent, "_");
			break;
		case "table - automatically add second line":
			markdownSyntaxSuggestionSelection = addSecondLineToTable(selectedContent);
			break;
		default:
			break;
		}
		return markdownSyntaxSuggestionSelection;
	}

	private String addSecondLineToTable(String selectedContent) {
		String[] decompose = selectedContent.trim().split("\\|", -1);
		int lengthOfTable = decompose.length;
		String[] secondLineComponents = new String[lengthOfTable];
		int numberOfSpaces = 0;
		if (!decompose[0].isEmpty()) {
			numberOfSpaces = decompose[0].trim().length() > 2 ? decompose[0].trim().length() : 3;
			secondLineComponents[0] = addHyphen(numberOfSpaces, 0);
		}
		if (!decompose[lengthOfTable - 1].isEmpty()) {
			numberOfSpaces = decompose[lengthOfTable - 1].trim().length() > 2
					? decompose[lengthOfTable - 1].trim().length()
					: 3;
			secondLineComponents[lengthOfTable - 1] = addHyphen(numberOfSpaces, 1);
		}
		for (int i = 1; i < lengthOfTable - 1; i++) {
			numberOfSpaces = decompose[i].trim().length() > 2 ? decompose[i].trim().length() : 3;
			secondLineComponents[i] = addHyphen(numberOfSpaces, 2);
		}
		String secondLine = join(secondLineComponents);
		return selectedContent + "\n" + secondLine;

	}

	// type: 0 - first element in no beginning pipe
	// 1 - last element in no closing pipe
	// 2 - general condition
	private static String addHyphen(int numberOfSpaces, int type) {
		StringBuilder strB = new StringBuilder();
		for (int i = 0; i < numberOfSpaces; i++) {
			strB.append("-");
		}
		if (type == 0) {
			return strB.toString() + " ";
		}
		if (type == 1) {
			return " " + strB.toString();
		}
		return " " + strB.toString() + " ";
	}

	private static String join(String[] string) {
		String result = "";
		StringBuilder strB = new StringBuilder();
		int length = string.length;
		if (string[0] != null) {
			result += string[0] + "|";
		} else {
			result += "|";
		}
		for (int i = 1; i < length - 1; i++) {
			strB.append(string[i] + "|");
		}
		result += strB.toString();
		if (string[length - 1] != null) {
			result += string[length - 1];
		}
		return result;
	}

}

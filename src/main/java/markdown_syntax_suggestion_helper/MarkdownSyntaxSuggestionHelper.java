package markdown_syntax_suggestion_helper;

import append.AddSyntaxToString;

public class MarkdownSyntaxSuggestionHelper {
	private AddSyntaxToString syntax = new AddSyntaxToString();
	private String markdownSyntaxSuggestionSelection = "";

	public String applySuggestion(String string, String selectedContent) {
		switch (string) {
		case MarkdownSyntaxSuggestionConstants.HEADING_ONE:
			markdownSyntaxSuggestionSelection = syntax.addSingleCharacter(selectedContent, "#");
			break;
		case MarkdownSyntaxSuggestionConstants.HEADING_TWO:
			markdownSyntaxSuggestionSelection = syntax.addDoubleCharacter(selectedContent, "#");
			break;
		case MarkdownSyntaxSuggestionConstants.HEADING_THREE:
			markdownSyntaxSuggestionSelection = syntax.addTripleCharacter(selectedContent, "#");
			break;
		case MarkdownSyntaxSuggestionConstants.HEADING_FOUR:
			markdownSyntaxSuggestionSelection = syntax.addFourCharacter(selectedContent, "#");
			break;
		case MarkdownSyntaxSuggestionConstants.HEADING_FIVE:
			markdownSyntaxSuggestionSelection = syntax.addFiveCharacter(selectedContent, "#");
			break;
		case MarkdownSyntaxSuggestionConstants.HEADING_SIX:
			markdownSyntaxSuggestionSelection = syntax.addSixCharacter(selectedContent, "#");
			break;
		case MarkdownSyntaxSuggestionConstants.BLOCKQUOTE:
			markdownSyntaxSuggestionSelection = syntax.blockQuote(selectedContent);
			break;
		case MarkdownSyntaxSuggestionConstants.LINK:
			markdownSyntaxSuggestionSelection = syntax.linkToURL(selectedContent);
			break;
		case MarkdownSyntaxSuggestionConstants.CODE_BLOCK:
			markdownSyntaxSuggestionSelection = syntax.surroundDoubleCharacter(selectedContent, "\n\t", "\n");
			break;
		case MarkdownSyntaxSuggestionConstants.INLINE_CODE:
			markdownSyntaxSuggestionSelection = syntax.surroundSingleCharacter(selectedContent, "`");
			break;
		case MarkdownSyntaxSuggestionConstants.BOLD:
			markdownSyntaxSuggestionSelection = syntax.surroundSingleCharacter(selectedContent, "**");
			break;
		case MarkdownSyntaxSuggestionConstants.ITALIC:
			markdownSyntaxSuggestionSelection = syntax.surroundSingleCharacter(selectedContent, "_");
			break;
		case MarkdownSyntaxSuggestionConstants.TABLE:
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

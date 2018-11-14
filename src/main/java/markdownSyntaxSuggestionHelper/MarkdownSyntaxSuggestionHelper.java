package markdownSyntaxSuggestionHelper;

import append.AddSyntaxToString;

public class MarkdownSyntaxSuggestionHelper {
	private AddSyntaxToString syntax = new AddSyntaxToString();
	private String markdownSyntaxSuggestionSelection = "";

	public String applySuggestion(int index, String selectedContent) {
		switch (index) {
		case 0:
			markdownSyntaxSuggestionSelection = syntax.addSingleCharacter(selectedContent, "#");
			break;
		case 1:
			markdownSyntaxSuggestionSelection = syntax.addDoubleCharacter(selectedContent, "#");
			break;
		case 2:
			markdownSyntaxSuggestionSelection = syntax.addTripleCharacter(selectedContent, "#");
			break;
		case 3:
			markdownSyntaxSuggestionSelection = syntax.addFourCharacter(selectedContent, "#");
			break;
		case 4:
			markdownSyntaxSuggestionSelection = syntax.addFiveCharacter(selectedContent, "#");
			break;
		case 5:
			markdownSyntaxSuggestionSelection = syntax.addSixCharacter(selectedContent, "#");
			break;
		case 6:
			markdownSyntaxSuggestionSelection = syntax.blockQuote(selectedContent);
			break;
		case 7:
			markdownSyntaxSuggestionSelection = syntax.linkToURL(selectedContent);
			break;
		case 8:
			markdownSyntaxSuggestionSelection = syntax.surroundDoubleCharacter(selectedContent, "\n\t", "\n");
			break;
		case 9:
			markdownSyntaxSuggestionSelection = syntax.surroundDoubleCharacter(selectedContent, "'", "' ");
			break;
		case 10:
			markdownSyntaxSuggestionSelection = syntax.surroundSingleCharacter(selectedContent, "**");
			break;
		case 11:
			markdownSyntaxSuggestionSelection = syntax.surroundSingleCharacter(selectedContent, "_");
			break;
		default:
			break;
		}
		return markdownSyntaxSuggestionSelection;
	}

}

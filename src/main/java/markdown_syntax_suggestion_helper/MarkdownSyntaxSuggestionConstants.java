package markdown_syntax_suggestion_helper;

public class MarkdownSyntaxSuggestionConstants {
	public static final String HEADING_ONE = "# - Heading 1";
	public static final String HEADING_TWO = "## - Heading 2";
	public static final String HEADING_THREE = "### - Heading 3";
	public static final String HEADING_FOUR = "#### - Heading 4";
	public static final String HEADING_FIVE = "##### - Heading 5";
	public static final String HEADING_SIX = "###### - Heading 6";
	public static final String BLOCKQUOTE = "> Blockquote - add > to each line";
	public static final String LINK = "[] - Link(inline) - [Text]";
	public static final String CODE_BLOCK = "\t - Code block (indented with tab)";
	public static final String INLINE_CODE = "`inline code` - A span of code inline";
	public static final String BOLD = "**bold** - bold";
	public static final String ITALIC = "*italics* - italics";
	public static final String TABLE = "table - automatically add second line";
	public static final String UNORDERED_LIST = "* Unordered List - add * to each line";
	public static final String ORDERED_LIST = "1. Ordered List - add 1. to each line";

	public String[] getArrayOfConstants(String selectedContent) {
		if (isHeader(selectedContent)) {
			return new String[] { TABLE, HEADING_ONE, HEADING_TWO, HEADING_THREE, HEADING_FOUR, HEADING_FIVE,
					HEADING_SIX, BLOCKQUOTE, LINK, CODE_BLOCK, INLINE_CODE, BOLD, ITALIC, UNORDERED_LIST,
					ORDERED_LIST };
		} else {
			return new String[] { HEADING_ONE, HEADING_TWO, HEADING_THREE, HEADING_FOUR, HEADING_FIVE, HEADING_SIX,
					BLOCKQUOTE, LINK, CODE_BLOCK, INLINE_CODE, BOLD, ITALIC, UNORDERED_LIST,
					ORDERED_LIST };
		}
	}

	public boolean isHeader(String selectedContent) {
		boolean test;
		if (selectedContent.contains("|") && selectedContent.trim().length() > 1) {
			test = true;
		} else {
			test = false;
		}
		return test;
	}
}

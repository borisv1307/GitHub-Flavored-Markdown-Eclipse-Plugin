package markdown_syntax_suggestion_helper;

public class MarkdownSyntaxSuggestionConstants {
	public static final String HEADING_ONE = "# - Heading 1";
	public static final String HEADING_TWO = "## - Heading 2";
	public static final String HEADING_THREE = "### - Heading 3";
	public static final String HEADING_FOUR = "#### - Heading 4";
	public static final String HEADING_FIVE = "##### - Heading 5";
	public static final String HEADING_SIX = "###### - Heading 6";
	public static final String BLOCKQUOTE = "> - Blockquote";
	public static final String LINK = "[] - Link(inline) - [Text]";
	public static final String CODE_BLOCK = "\t - Code block (indented with tab)";
	public static final String INLINE_CODE = "`inline code` - A span of code inline";
	public static final String BOLD = "**strong** - strong";
	public static final String ITALIC = "_emphasis_ - emphasis";
	public static final String TABLE = "table - automatically add second line";

	public String[] getArrayOfConstants(String selectedContent) {
		if (isHeader(selectedContent)) {
			return new String[] { TABLE, HEADING_ONE, HEADING_TWO, HEADING_THREE, HEADING_FOUR, HEADING_FIVE,
					HEADING_SIX, BLOCKQUOTE, LINK, CODE_BLOCK, INLINE_CODE, BOLD, ITALIC };
		} else {
			return new String[] { HEADING_ONE, HEADING_TWO, HEADING_THREE, HEADING_FOUR, HEADING_FIVE, HEADING_SIX,
					BLOCKQUOTE, LINK, CODE_BLOCK, INLINE_CODE, BOLD, ITALIC };
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
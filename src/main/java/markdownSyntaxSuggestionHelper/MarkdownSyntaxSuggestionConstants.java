package markdownSyntaxSuggestionHelper;

public class MarkdownSyntaxSuggestionConstants {
	private static final String HEADING_ONE = "# - Heading 1";
	private static final String HEADING_TWO = "## - Heading 2";
	private static final String HEADING_THREE = "### - Heading 3";
	private static final String HEADING_FOUR = "#### - Heading 4";
	private static final String HEADING_FIVE = "##### - Heading 5";
	private static final String HEADING_SIX = "###### - Heading 6";
	private static final String BLOCKQUOTE = "> - Blockquote";
	private static final String LINK = "[] - Link(inlint) - [Text]";
	private static final String CODE_BLOCK = "\t - Code block (indented with tab)";
	private static final String INLINE_CODE = "'inline code' - A span of code inline";
	private static final String BOLD = "**strong** - strong";
	private static final String ITALIC = "_emphasis_ - emphasis";

	public String[] getArrayOfConstants() {
		return new String[]{ HEADING_ONE, HEADING_TWO, HEADING_THREE, HEADING_FOUR, HEADING_FIVE, HEADING_SIX,
			BLOCKQUOTE, LINK, CODE_BLOCK, INLINE_CODE, BOLD, ITALIC };
	}
}

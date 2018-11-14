package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileNameCreatorTest {
	private FileNameCreator fileNameCreator = new FileNameCreator();
	
	@Test
	public void verifyGenericNameMdExtension() {
		String mdFileName1 = "File1.md";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName1);
		assertEquals(created_name, "File1.html");
	}
	
	@Test
	public void verifyNameWithSpecialCharactersMdExtension() {
		String mdFileName2 = "'ä','ü' and 'ö'.md";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName2);
		assertEquals(created_name, "'ä','ü' and 'ö'.html");
	}
	
	@Test
	public void verifyGenericNameMarkdownExtension() {
		String mdFileName3 = "File2.markdown";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName3);
		assertEquals(created_name, "File2.html");
	}
	
	@Test
	public void verifyNameWithSpecialCharactersMarkdownExtension() {
		String mdFileName4 = "#@!$%^&*()_+.markdown";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName4);
		assertEquals(created_name, "#@!$%^&*()_+.html");
	}

}

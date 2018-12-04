package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileNameCreatorTest {
	private FileNameCreator fileNameCreator = new FileNameCreator();

	@Test
	public void verifyGenericNameMdExtension() {
		String mdFileName1 = "File1.md";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName1);
		assertEquals("File1 (Preview).html", created_name);
	}

	@Test
	public void verifyNameWithSpecialCharactersMdExtension() {
		String mdFileName2 = "'ä','ü' and 'ö'.md";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName2);
		assertEquals("'ä','ü' and 'ö' (Preview).html", created_name);
	}

	@Test
	public void verifyGenericNameMarkdownExtension() {
		String mdFileName3 = "File2.markdown";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName3);
		assertEquals("File2 (Preview).html", created_name);
	}

	@Test
	public void verifyNameWithSpecialCharactersMarkdownExtension() {
		String mdFileName4 = "#@!$%^&*()_+.markdown";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName4);
		assertEquals("#@!$%^&*()_+ (Preview).html", created_name);
	}

	@Test
	public void verifyGenericTempNameMdExtension() {
		String mdFileName1 = "File1.md";
		String created_name = fileNameCreator.getTempFileName(mdFileName1);
		assertEquals("File1 (Preview)", created_name);
	}

	@Test
	public void verifyTempNameWithSpecialCharactersMdExtension() {
		String mdFileName2 = "'ä','ü' and 'ö'.md";
		String created_name = fileNameCreator.getTempFileName(mdFileName2);
		assertEquals("'ä','ü' and 'ö' (Preview)", created_name);
	}

	@Test
	public void verifyGenericTempNameMarkdownExtension() {
		String mdFileName3 = "File2.markdown";
		String created_name = fileNameCreator.getTempFileName(mdFileName3);
		assertEquals("File2 (Preview)", created_name);
	}

	@Test
	public void verifyTempNameWithSpecialCharactersMarkdownExtension() {
		String mdFileName4 = "#@!$%^&*()_+.markdown";
		String created_name = fileNameCreator.getTempFileName(mdFileName4);
		assertEquals("#@!$%^&*()_+ (Preview)", created_name);
	}

}

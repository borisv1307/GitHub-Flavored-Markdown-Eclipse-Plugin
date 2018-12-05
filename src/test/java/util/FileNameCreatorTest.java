package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileNameCreatorTest {
	private FileNameCreator fileNameCreator = new FileNameCreator();

	@Test
	public void verify_generic_name_md_extension() {
		String mdFileName1 = "File1.md";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName1);
		assertEquals("File1.html", created_name);
	}

	@Test
	public void verify_name_with_special_characters_md_extension() {
		String mdFileName2 = "'ä','ü' and 'ö'.md";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName2);
		assertEquals("'ä','ü' and 'ö'.html", created_name);
	}

	@Test
	public void verify_generic_name_markdown_extension() {
		String mdFileName3 = "File2.markdown";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName3);
		assertEquals("File2.html", created_name);
	}

	@Test
	public void verify_name_with_special_characters_markdown_extension() {
		String mdFileName4 = "#@!$%^&*()_+.markdown";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName4);
		assertEquals("#@!$%^&*()_+.html", created_name);
	}

	@Test
	public void verify_generic_temp_name_md_extension() {
		String mdFileName1 = "File1.md";
		String created_name = fileNameCreator.getTempFileName(mdFileName1);
		assertEquals("File1 (Preview)", created_name);
	}

	@Test
	public void verify_temp_name_with_special_characters_md_extension() {
		String mdFileName2 = "'ä','ü' and 'ö'.md";
		String created_name = fileNameCreator.getTempFileName(mdFileName2);
		assertEquals("'ä','ü' and 'ö' (Preview)", created_name);
	}

	@Test
	public void verify_generic_temp_name_markdown_extension() {
		String mdFileName3 = "File2.markdown";
		String created_name = fileNameCreator.getTempFileName(mdFileName3);
		assertEquals("File2 (Preview)", created_name);
	}

	@Test
	public void verify_temp_name_with_special_characters_markdown_extension() {
		String mdFileName4 = "#@!$%^&*()_+.markdown";
		String created_name = fileNameCreator.getTempFileName(mdFileName4);
		assertEquals("#@!$%^&*()_+ (Preview)", created_name);
	}

	@Test
	public void verify_generic_name_mdown_extension() {
		String mdFileName5 = "File3.mdown";
		String created_name = fileNameCreator.getHtmlFileName(mdFileName5);
		assertEquals("File3.html", created_name);
	}

	@Test
	public void verify_generic_temp_name_mrkdwn_extension() {
		String mdFileName6 = "File4.mrkdwn";
		String created_name = fileNameCreator.getTempFileName(mdFileName6);
		assertEquals("File4 (Preview)", created_name);
	}
}

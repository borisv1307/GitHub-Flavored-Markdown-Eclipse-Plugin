package util;

public class FileNameCreator {
	public String getTempFileName(String mdFileName) {
		return mdFileName.substring(0, mdFileName.lastIndexOf('.')) + " (Preview)";
	}

	public String getHtmlFileName(String mdFileName) {
		return mdFileName.substring(0, mdFileName.lastIndexOf('.')) + " (Preview).html";
	}
}

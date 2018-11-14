package util;

public class FileNameCreator {
	public String getHtmlFileName(String mdFileName) {
		return mdFileName.substring(0, mdFileName.lastIndexOf('.'))+ ".html";
	}
}

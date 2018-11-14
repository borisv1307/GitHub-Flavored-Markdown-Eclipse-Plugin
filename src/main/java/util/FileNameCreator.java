package util;

public class FileNameCreator {
	public String getHtmlFileName(String mdFileName) {
		String fileName = mdFileName.substring(0, mdFileName.lastIndexOf('.'))+ ".html";
		return fileName;
	}
}

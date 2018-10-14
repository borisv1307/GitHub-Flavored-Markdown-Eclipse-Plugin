package githubflavoredmarkdowneclipseplugin;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractTextEditor;

public class MarkdownEditor extends AbstractTextEditor {

	public MarkdownEditor() {

		setSourceViewerConfiguration(new TextSourceViewerConfiguration());

		setDocumentProvider(new TextFileDocumentProvider());

		IWebBrowser browser;
		try {
			browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser("prump");
			browser.openURL(new URL("http://www.google.com"));
		} catch (MalformedURLException | PartInitException e) {
			e.printStackTrace();
		}
	}
}

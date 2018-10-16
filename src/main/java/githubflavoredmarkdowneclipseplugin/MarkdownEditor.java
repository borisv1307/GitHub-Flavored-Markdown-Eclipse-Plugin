package githubflavoredmarkdowneclipseplugin;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractTextEditor;

import java.util.logging.Logger;


public class MarkdownEditor extends AbstractTextEditor implements IResourceChangeListener {
	public MarkdownEditor() {

		setSourceViewerConfiguration(new TextSourceViewerConfiguration());

		setDocumentProvider(new TextFileDocumentProvider());

		IWebBrowser browser;
		try {
			browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser("prump");
			try {
				browser.openURL(new URL("http://www.google.com"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void createActions() {
		super.createActions();
		// ... add other editor actions here
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Log.info(event.toString());
	}
}

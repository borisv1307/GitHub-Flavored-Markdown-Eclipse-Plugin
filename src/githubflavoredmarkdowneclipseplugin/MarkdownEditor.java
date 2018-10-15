package githubflavoredmarkdowneclipseplugin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractTextEditor;

public class MarkdownEditor extends AbstractTextEditor implements IResourceChangeListener {
	public MarkdownEditor() throws MalformedURLException {

		setSourceViewerConfiguration(new TextSourceViewerConfiguration());

		setDocumentProvider(new TextFileDocumentProvider());

		IWebBrowser browser;
		try {
			browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser("prump");
			Activator activator = Activator.getDefault();
			URL url = FileLocator.find(activator.getBundle(), new Path("index.html"));
			URL file = FileLocator.toFileURL(url);
			activator.log(file.toString());
			browser.openURL(file);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		Activator.getDefault().log(event.toString());
	}
}

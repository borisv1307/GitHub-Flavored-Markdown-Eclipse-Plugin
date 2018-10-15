package githubflavoredmarkdowneclipseplugin;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
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
			Activator activator = Activator.getDefault();
			browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(activator.PLUGIN_ID);
			URL url = FileLocator.find(activator.getBundle(), new Path("index.html"));
			URL file = FileLocator.toFileURL(url);
			browser.openURL(file);
		} catch (PartInitException | IOException e) {
			e.printStackTrace();
		}
	}
}

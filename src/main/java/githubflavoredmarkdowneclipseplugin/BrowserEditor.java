package githubflavoredmarkdowneclipseplugin;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.IWebBrowser;

public class BrowserEditor {
	private IWebBrowser browser;

	public BrowserEditor(IWorkbench workbench, String plugin_id) {
		if (browser == null) {
			try {
				browser = workbench.getBrowserSupport().createBrowser(plugin_id);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadFileInBrowser(Path file, IWorkbenchPartSite site) {
		URL htmlFile;
		try {
			htmlFile = file.toUri().toURL();
			browser.openURL(htmlFile);
			IWorkbenchPart part = site.getPart();
			site.getPage().activate(part);
		} catch (IOException | PartInitException e) {
			e.printStackTrace();
		}
	}

}

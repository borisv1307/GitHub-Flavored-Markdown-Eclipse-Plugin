package githubflavoredmarkdowneclipseplugin;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;

import markdown_renderer.MarkdownRenderer;
import table_formatter.PipeTableFormat;

public class MarkdownEditor extends AbstractTextEditor {

	private Activator activator;
	private MarkdownRenderer markdownRenderer;
	private IWebBrowser browser;

	public MarkdownEditor() throws FileNotFoundException {

		setSourceViewerConfiguration(new TextSourceViewerConfiguration());

		setDocumentProvider(new TextFileDocumentProvider());

		// Activator manages connections to the Workbench
		activator = Activator.getDefault();

		markdownRenderer = new MarkdownRenderer();
	}

	private IFile saveMarkdown(IEditorInput editorInput, IDocument document, IProgressMonitor progressMonitor) {
		IProject project = getCurrentProject(editorInput);

		String mdFileName = editorInput.getName();
		String fileName = mdFileName.substring(0, mdFileName.lastIndexOf('.'));
		String htmlFileName = fileName + ".html";
		IFile file = project.getFile(htmlFileName);

		String markdownString = "<!DOCTYPE html>\n" + "<html>" + "<head>\n" + "<meta charset=\"utf-8\">\n" + "<title>"
				+ htmlFileName + "</title>\n" + "</head>" + "<body>" + markdownRenderer.render(document.get())
				+ "</body>\n" + "</html>";
		try {
			if (!project.isOpen())
				project.open(progressMonitor);
			if (file.exists())
				file.delete(true, progressMonitor);
			if (!file.exists()) {
				byte[] bytes = markdownString.getBytes();
				InputStream source = new ByteArrayInputStream(bytes);
				file.create(source, IResource.NONE, progressMonitor);
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	private void loadFileInBrowser(IFile file) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		try {
			if (browser == null)
				browser = workbench.getBrowserSupport().createBrowser(Activator.PLUGIN_ID);
			URL htmlFile = FileLocator.toFileURL(file.getLocationURI().toURL());
			browser.openURL(htmlFile);
			IWorkbenchPartSite site = this.getSite();
			IWorkbenchPart part = site.getPart();
			site.getPage().activate(part);
		} catch (IOException | PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		super.init(site, editorInput);
		IDocumentProvider documentProvider = getDocumentProvider();
		IDocument document = documentProvider.getDocument(editorInput);
		IFile htmlFile = saveMarkdown(editorInput, document, null);
		loadFileInBrowser(htmlFile);
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {

		IDocumentProvider documentProvider = getDocumentProvider();
		if (documentProvider == null)
			return;
		IEditorInput editorInput = getEditorInput();
		IDocument document = documentProvider.getDocument(editorInput);
		if (documentProvider.isDeleted(getEditorInput())) {

			if (isSaveAsAllowed()) {

				/*
				 * 1GEUSSR: ITPUI:ALL - User should never loose changes made in the editors.
				 * Changed Behavior to make sure that if called inside a regular save (because
				 * of deletion of input element) there is a way to report back to the caller.
				 */
				performSaveAs(progressMonitor);

			} else {

			}

		} else {
			String[] string = new String[1];
			string[0] = document.get();
			String formattedString = PipeTableFormat.format(string)[0];
			// Calculating the position of the cursor
			String[] cursorPosition = this.getCursorPosition().split("\\ : ");
			int cursorLength = Integer.parseInt(cursorPosition[0]) * Integer.parseInt(cursorPosition[1]) - 1;
			// This sets the cursor on at the start of the file
			document.set(formattedString);
			// Move the cursor
			this.setHighlightRange(cursorLength, 0, true);
			IFile htmlFile = saveMarkdown(editorInput, document, progressMonitor);
			loadFileInBrowser(htmlFile);
			performSave(false, progressMonitor);
		}
	}

	private IProject getCurrentProject(IEditorInput editorInput) {
		IProject project = editorInput.getAdapter(IProject.class);
		if (project == null) {
			IResource resource = editorInput.getAdapter(IResource.class);
			if (resource != null) {
				project = resource.getProject();
			}
		}
		return project;
	}
}

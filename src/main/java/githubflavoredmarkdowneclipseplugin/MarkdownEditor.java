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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;

import markdown_renderer.MarkdownRenderer;

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

	private IFile saveMarkdown(IEditorInput editorInput, IProgressMonitor progressMonitor) {

		IDocumentProvider documentProvider = this.getDocumentProvider();
		IDocument document = documentProvider.getDocument(editorInput);
		IProject project = getCurrentProject(editorInput);
		String fileName = editorInput.getName();
		IFile file = project.getFile(fileName + ".html");

		String markdownString = markdownRenderer
				.render("<!DOCTYPE html>\n" + "<html>" + "<head>\n" + "<meta charset=\"utf-8\">\n" + "<title>"
						+ fileName + "</title>\n" + "</head>" + "<body>" + document.get() + "</body>\n" + "</html>");
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
		try {
			if (browser == null)
				browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(Activator.PLUGIN_ID);
			URL htmlFile = FileLocator.toFileURL(file.getLocationURI().toURL());
			browser.openURL(htmlFile);
		} catch (IOException | PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		super.init(site, editorInput);
		IFile htmlFile = saveMarkdown(editorInput, null);
		loadFileInBrowser(htmlFile);
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {

		IDocumentProvider p = getDocumentProvider();
		if (p == null)
			return;
		IEditorInput editorInput = getEditorInput();
		if (p.isDeleted(getEditorInput())) {

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
			IFile htmlFile = saveMarkdown(editorInput, progressMonitor);
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

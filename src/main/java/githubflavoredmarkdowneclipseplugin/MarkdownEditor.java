package githubflavoredmarkdowneclipseplugin;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;

import markdown_renderer.MarkdownRenderer;
import table_formatter.PipeTableFormat;
import util.FileNameCreator;
import githubflavoredmarkdowneclipseplugin.BrowserEditor;

public class MarkdownEditor extends AbstractTextEditor {

	private Activator activator;
	private MarkdownRenderer markdownRenderer;
	private BrowserEditor browserEditor;
	private FileNameCreator fileNameCreator;

	public MarkdownEditor() throws FileNotFoundException {

		setSourceViewerConfiguration(new TextSourceViewerConfiguration());

		setDocumentProvider(new TextFileDocumentProvider());

		// Activator manages connections to the Workbench
		activator = Activator.getDefault();
		markdownRenderer = new MarkdownRenderer();
		browserEditor = new BrowserEditor(PlatformUI.getWorkbench(), Activator.PLUGIN_ID);
		fileNameCreator = new FileNameCreator();
	}

	private IFile saveMarkdown(IEditorInput editorInput, IDocument document, IProgressMonitor progressMonitor) {
		IProject project = getCurrentProject(editorInput);

		String htmlFileName = fileNameCreator.getHtmlFileName(editorInput.getName());
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

	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		super.init(site, editorInput);
		IDocumentProvider documentProvider = getDocumentProvider();
		IDocument document = documentProvider.getDocument(editorInput);
		IFile htmlFile = saveMarkdown(editorInput, document, null);
		browserEditor.loadFileInBrowser(htmlFile, this.getSite());
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
			// Convert document from string to string array with each instance a single line
			// of the document
			String[] stringArrayOfDocument = document.get().split("\n");
			String[] formattedLines = PipeTableFormat.preprocess(stringArrayOfDocument);
			StringBuilder builder = new StringBuilder();
			for (String line : formattedLines) {
				builder.append(line);
				builder.append("\n");
			}
			String formattedDocument = builder.toString();

			// Calculating the position of the cursor
			ISelectionProvider selectionProvider = this.getSelectionProvider();
			ISelection selection = selectionProvider.getSelection();
			int cursorLength = 0;
			if (selection instanceof ITextSelection) {
				ITextSelection textSelection = (ITextSelection) selection;
				cursorLength = textSelection.getOffset(); // etc.
				activator.log(Integer.toString(cursorLength));
			}
			// This sets the cursor on at the start of the file
			document.set(formattedDocument);

			// Move the cursor
			this.setHighlightRange(cursorLength, 0, true);
			IFile htmlFile = saveMarkdown(editorInput, document, progressMonitor);
			browserEditor.loadFileInBrowser(htmlFile, this.getSite());
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

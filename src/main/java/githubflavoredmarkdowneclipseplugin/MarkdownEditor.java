package githubflavoredmarkdowneclipseplugin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;

import injector.HTMLInjector;
import markdown_renderer.MarkdownRenderer;
import markdown_syntax_suggestion_window.MarkdownSyntaxSuggestionWindow;
import preferences.PreferenceMonitor;
import table_formatter.PipeTableFormat;
import util.FileNameCreator;
import wrapper.BufferedReaderWrapper;

public class MarkdownEditor extends AbstractTextEditor {

	private Activator activator;
	private MarkdownRenderer markdownRenderer;
	private BrowserEditor browserEditor;
	private FileNameCreator fileNameCreator;
	private MarkdownSyntaxSuggestionWindow autoComplete;
	private StyledText styledText;
	private Point point;
	private PreferenceMonitor preferences;
	private HTMLInjector htmlInjector;
	private static final int POPUP_OFFSET = 20;

	public MarkdownEditor() throws IOException {

		setSourceViewerConfiguration(new TextSourceViewerConfiguration());
		setDocumentProvider(new TextFileDocumentProvider());

		// Activator manages connections to the Workbench
		activator = Activator.getDefault();
		markdownRenderer = new MarkdownRenderer();
		browserEditor = new BrowserEditor(PlatformUI.getWorkbench(), Activator.PLUGIN_ID);
		fileNameCreator = new FileNameCreator();
		preferences = new PreferenceMonitor();
		htmlInjector = new HTMLInjector(new BufferedReaderWrapper());
		autoComplete = new MarkdownSyntaxSuggestionWindow(this);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		super.createNavigationActions();
		
		StyledText textWidget = getSourceViewer().getTextWidget();
		textWidget.setKeyBinding(SWT.DEL, ST.DELETE_NEXT);
		
		ISourceViewer fSourceViewer = super.getSourceViewer();
		styledText = fSourceViewer.getTextWidget();
		styledText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (preferences.autocomplete()) {
					try {
						if (e.stateMask == SWT.CTRL && e.keyCode == SWT.SPACE) {
							String text = styledText.getSelectionText();
							Composite control = styledText.getParent();
							// this function comes from org.eclipse.jface.fieldassist, how do they get the
							// coordinates
							Point location = control.getDisplay().map(control.getParent(), null, control.getLocation());
							Rectangle selectedBlock = styledText.getBlockSelectionBounds();
							int xLocation = location.x + selectedBlock.x + selectedBlock.width + POPUP_OFFSET;
							int yLocation = location.y + selectedBlock.y + selectedBlock.height;
							point = styledText.getSelectionRange();
							if (!text.isEmpty()) {
								autoComplete.show(text, xLocation, yLocation);
							}
						}
					} catch (Exception exception) {
						addErrorFile(
								"Suggestion feature did not work, if error persists disable the suggestion feature in your preferences. Preferences > Markdown Editor (GFM) Preference > Use suggestion feature");
					}

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private Path saveTempMarkdown(IEditorInput editorInput, IDocument document, IProgressMonitor progressMonitor) {
		String htmlFileName = fileNameCreator.getTempFileName(editorInput.getName());
		Path file = null;

		String markdownString = htmlInjector.inject(htmlFileName, markdownRenderer.render(document.get()));

		try {
			file = Files.createTempFile(htmlFileName, ".html");
			byte[] bytes = markdownString.getBytes();
			Files.write(file, bytes);
			file.toFile().deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	private IFile saveMarkdown(IEditorInput editorInput, IDocument document, IProgressMonitor progressMonitor) {
		String htmlFileName = fileNameCreator.getHtmlFileName(editorInput.getName());

		String markdownString = htmlInjector.inject(htmlFileName, markdownRenderer.render(document.get()));
		IProject project = getCurrentProject(editorInput);
		IFile file = project.getFile(htmlFileName);
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
			e.printStackTrace();
		}
		return file;
	}

	public void replace(String text) {
		ISourceViewer fSourceViewer = super.getSourceViewer();
		styledText = fSourceViewer.getTextWidget();
		point = styledText.getSelectionRange();
		styledText.replaceTextRange(point.x, point.y, text);
	}

	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		super.init(site, editorInput);
		IDocumentProvider documentProvider = getDocumentProvider();
		IDocument document = documentProvider.getDocument(editorInput);
		if (preferences.tempFile()) {
			Path htmlFile = saveTempMarkdown(editorInput, document, null);
			browserEditor.loadFileInBrowser(htmlFile, this.getSite());
		} else {
			IFile htmlFile = saveMarkdown(editorInput, document, null);
			browserEditor.loadFileInBrowser(htmlFile, this.getSite());
		}
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
			String[] formattedLines = null;
			String[] stringArrayOfDocument = document.get().split("\n", -1);

			if (preferences.formatTable()) {
				try {
					formattedLines = PipeTableFormat.preprocess(stringArrayOfDocument);
				} catch (Exception e) {
					addErrorFile(
							"Table formatter did not work, if error persists disable the table formatter in your preferences. Preferences > Markdown Editor (GFM) Preference > Use automatic table formatting feature");
				}
			} else {
				formattedLines = stringArrayOfDocument;
			}
			String formattedDocument = util.StringArray.join(formattedLines, "\n");

			// Calculating the position of the cursor
			ISelectionProvider selectionProvider = this.getSelectionProvider();
			ISelection selection = selectionProvider.getSelection();
			int cursorLength = 0;
			if (selection instanceof ITextSelection) {
				ITextSelection textSelection = (ITextSelection) selection;
				cursorLength = textSelection.getOffset(); // etc.
			}
			// Replace the document with the formatted string
			document.set(formattedDocument);

			// Move the cursor
			this.setHighlightRange(cursorLength, 0, true);
			if (preferences.tempFile()) {
				Path htmlFile = saveTempMarkdown(editorInput, document, progressMonitor);
				browserEditor.loadFileInBrowser(htmlFile, this.getSite());
			} else {
				IFile htmlFile = saveMarkdown(editorInput, document, progressMonitor);
				browserEditor.loadFileInBrowser(htmlFile, this.getSite());
			}
			performSave(false, progressMonitor);
		}
	}

	private void addErrorFile(String errorMessage) {
		IEditorInput editorInput = getEditorInput();
		IProject project = getCurrentProject(editorInput);
		IFile file = project.getFile("MarkdownEditorError.md");
		errorMessage = new Date().toString() + "\n" + errorMessage + "\n\n";
		try {
			if (!project.isOpen())
				project.open(null);
			if (file.exists()) {
				InputStream source = new ByteArrayInputStream(errorMessage.getBytes());
				file.appendContents(source, true, true, null);
			}
			if (!file.exists()) {
				byte[] bytes = errorMessage.getBytes();
				InputStream source = new ByteArrayInputStream(bytes);
				file.create(source, IResource.NONE, null);
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private IProject getCurrentProject(IEditorInput editorInput) {
		IProject project = (IProject) editorInput.getAdapter(IProject.class);
		if (project == null) {
			IResource resource = (IResource) editorInput.getAdapter(IResource.class);
			if (resource != null) {
				project = resource.getProject();
			}
		}
		return project;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
}

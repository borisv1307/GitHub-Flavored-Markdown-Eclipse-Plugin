package githubflavoredmarkdowneclipseplugin;

import java.io.FileNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
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

import injector.HTMLInjector;
import markdown_renderer.MarkdownRenderer;
import markdown_syntax_suggestion_window.MarkdownSyntaxSuggestionWindow;
import preferences.PreferenceMonitor;
import table_formatter.PipeTableFormat;
import wrapper.BufferedReaderWrapper;

public class MarkdownEditor extends AbstractTextEditor {

	private Activator activator;
	private MarkdownRenderer markdownRenderer;
	private MarkdownSyntaxSuggestionWindow autoComplete;
	private StyledText styledText;
	private Point point;
	private IWebBrowser browser;
	private PreferenceMonitor preferences;
	private HTMLInjector htmlInjector;
	private static final int POPUP_OFFSET = 20;

	public MarkdownEditor() throws IOException {

		setSourceViewerConfiguration(new TextSourceViewerConfiguration());
		setDocumentProvider(new TextFileDocumentProvider());

		// Activator manages connections to the Workbench
		activator = Activator.getDefault();

		markdownRenderer = new MarkdownRenderer();
		preferences = new PreferenceMonitor();
		htmlInjector = new HTMLInjector(new BufferedReaderWrapper());
		autoComplete = new MarkdownSyntaxSuggestionWindow(this);
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		ISourceViewer fSourceViewer = super.getSourceViewer();
		styledText = fSourceViewer.getTextWidget();
		styledText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (preferences.autocomplete()) {
					if (e.stateMask == SWT.CTRL && e.keyCode == SWT.SPACE) {
						String text = styledText.getSelectionText();
						Composite control = styledText.getParent();
						// this function comes from org.eclipse.jface.fieldassist, how do they get the coordinates
						Point location = control.getDisplay().map(control.getParent(), null, control.getLocation());
						Rectangle selectedBlock = styledText.getBlockSelectionBounds();
						int xLocation = location.x+selectedBlock.x+selectedBlock.width+POPUP_OFFSET;
						int yLocation = location.y+selectedBlock.y+selectedBlock.height;
						point = styledText.getSelectionRange();
						if (!text.isEmpty()) {
							autoComplete.show(text, xLocation, yLocation);
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private Path saveMarkdown(IEditorInput editorInput, IDocument document, IProgressMonitor progressMonitor) {
		String mdFileName = editorInput.getName();
		String fileName = mdFileName.substring(0, mdFileName.lastIndexOf('.'));
		String htmlFileName = fileName + "html";
		Path file = null;

		String markdownString = htmlInjector.inject(htmlFileName, markdownRenderer.render(document.get()));

		try {
			byte[] bytes = markdownString.getBytes();
			file = Files.createTempFile(fileName, ".html");
			Files.write(file, bytes);
			file.toFile().deleteOnExit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	private void loadFileInBrowser(Path file) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		try {
			if (browser == null)
				browser = workbench.getBrowserSupport().createBrowser(Activator.PLUGIN_ID);
			URL htmlFile = file.toUri().toURL();
			browser.openURL(htmlFile);
			IWorkbenchPartSite site = this.getSite();
			IWorkbenchPart part = site.getPart();
			site.getPage().activate(part);
		} catch (IOException | PartInitException e) {
			e.printStackTrace();
		}
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
		Path htmlFile = saveMarkdown(editorInput, document, null);
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
			// Convert document from string to string array with each instance a single line
			// of the document
			String[] formattedLines;
			String[] stringArrayOfDocument = document.get().split("\n",-1);

			if (preferences.formatTable()) {
				formattedLines = PipeTableFormat.preprocess(stringArrayOfDocument);
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
				activator.log(Integer.toString(cursorLength));
			}
			// Replace the document with the formatted string
			document.set(formattedDocument);

			// Move the cursor
			this.setHighlightRange(cursorLength, 0, true);
			Path htmlFile = saveMarkdown(editorInput, document, progressMonitor);
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

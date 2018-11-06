package githubflavoredmarkdowneclipseplugin;


import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.editors.text.TextFileDocumentProvider;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;

import autocomplete.AutoComplete;
import markdown_renderer.MarkdownRenderer;

public class MarkdownEditor extends AbstractTextEditor{

	private Activator activator;
	private MarkdownRenderer markdownRenderer;
	private AutoComplete panel = new AutoComplete();
	private StyledText styledText;
	private Point point;

	public MarkdownEditor() throws FileNotFoundException {

		setSourceViewerConfiguration(new TextSourceViewerConfiguration());
		TextFileDocumentProvider provider = new TextFileDocumentProvider();
		setDocumentProvider(provider);

		// Activator manages connections to the Workbench
		activator = Activator.getDefault();
		
		markdownRenderer = new MarkdownRenderer();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		ISourceViewer fSourceViewer = super.getSourceViewer();
		styledText= fSourceViewer.getTextWidget();
		styledText.addKeyListener(new KeyListener() {


			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.stateMask == SWT.CTRL && e.keyCode == SWT.ALT) {
					String text=styledText.getSelectionText();
					point = styledText.getSelectionRange();
//					System.out.println("point:"+point);
//					styledText.replaceTextRange(point.x, point.y, text+"test");
					if(!text.isEmpty()) {
						panel.show(text);
						
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});
	}
	
	public void getSelectedContent(String selectedContent) {
//		styledText.replaceTextRange(point.x, point.y, selectedContent);
	}


	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		super.init(site, editorInput);
		IDocumentProvider documentProvider = this.getDocumentProvider();
		IDocument document = documentProvider.getDocument(editorInput);

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject("markdown");
		IFolder folder = project.getFolder("html");
		IFile file = folder.getFile("markdown.html");
		String markdownString = markdownRenderer.render(document.get());
		try {
			if (!project.exists())

				project.create(null);

			if (!project.isOpen())
				project.open(null);
			if (file.exists())
				file.delete(true, null);
			if (!file.exists()) {
				byte[] bytes = markdownString.getBytes();
				InputStream source = new ByteArrayInputStream(bytes);
				file.create(source, IResource.NONE, null);
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IWebBrowser browser;
		try {
			browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(activator.PLUGIN_ID);
			URL url = FileLocator.find(activator.getBundle(), new Path("index.html"));
			URL htmlFile = FileLocator.toFileURL(file.getLocationURI().toURL());
			browser.openURL(htmlFile);
		} catch (PartInitException | IOException e) {
			e.printStackTrace();
		}
	}
	
}

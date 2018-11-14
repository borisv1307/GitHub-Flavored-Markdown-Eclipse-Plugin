package markdownSyntaxSuggestionWindow;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.eclipse.swt.widgets.Display;

import githubflavoredmarkdowneclipseplugin.MarkdownEditor;
import markdownSyntaxSuggestionHelper.MarkdownSyntaxSuggestionConstants;
import markdownSyntaxSuggestionHelper.MarkdownSyntaxSuggestionHelper;

public class MarkdownSyntaxSuggestionWindow extends JFrame {
	private JFrame frame = new JFrame("");
	private Container container = frame.getContentPane();
	private KeyListener listener;
	public String selectedContent = "";
	private JList list = null;
	private MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
	private MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();

	MarkdownEditor markdownEditor;

	public MarkdownSyntaxSuggestionWindow(MarkdownEditor markdownEditor) {
		frame.setUndecorated(true);
		list = new JList(markdownSyntaxSuggestionConstants.getArrayOfConstants());
		container.add(list);
		container.add(new JScrollPane(list));
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addListener();
		this.markdownEditor = markdownEditor;
	}

	public void addListener() {
		listener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					frame.dispose();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					selectedContent = markdownSyntaxSuggestionHelper.applySuggestion(list.getSelectedIndex(),
							selectedContent);
					frame.dispose();
					Display.getDefault().syncExec(new Runnable() {
						@Override
						public void run() {
							markdownEditor.replace(selectedContent);
						}
					});
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		};
		list.addKeyListener(listener);

	}

	public void show(String slection) {
		Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
		this.frame.setLocation(point.x, point.y);
		selectedContent = slection;
		frame.setVisible(true);
	}

}

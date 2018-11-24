package markdown_syntax_suggestion_window;

import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.eclipse.swt.widgets.Display;

import githubflavoredmarkdowneclipseplugin.MarkdownEditor;
import markdown_syntax_suggestion_helper.MarkdownSyntaxSuggestionConstants;
import markdown_syntax_suggestion_helper.MarkdownSyntaxSuggestionHelper;

public class MarkdownSyntaxSuggestionWindow extends JFrame {
	private JFrame frame = new JFrame("");
	private Container container = frame.getContentPane();
	private String selectedContent = "";
	private JList list = null;
	private MarkdownSyntaxSuggestionConstants markdownSyntaxSuggestionConstants = new MarkdownSyntaxSuggestionConstants();
	private MarkdownSyntaxSuggestionHelper markdownSyntaxSuggestionHelper = new MarkdownSyntaxSuggestionHelper();

	MarkdownEditor markdownEditor;

	public MarkdownSyntaxSuggestionWindow(MarkdownEditor markdownEditor) {
		frame.setUndecorated(true);
		frame.setSize(450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.markdownEditor = markdownEditor;
	}

	private void addKeyListener() {
		list.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					frame.dispose();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					selectedContent = markdownSyntaxSuggestionHelper.applySuggestion(list.getSelectedValue().toString(),
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
		});
	}

	private void addMouseListener() {
		list.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					selectedContent = markdownSyntaxSuggestionHelper.applySuggestion(list.getSelectedValue().toString(),
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
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void show(String selection, int xLocation, int yLocation) {
		Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
		this.frame.setLocation(xLocation, yLocation);
		list = new JList(markdownSyntaxSuggestionConstants.getArrayOfConstants(selection));
		list.setFont(new Font("Arial", Font.BOLD, 22));
		if (container.getComponents() != null) {
			container.removeAll();
		}
		container.add(list);
		container.add(new JScrollPane(list));
		addKeyListener();
		addMouseListener();
		selectedContent = selection;
		frame.setVisible(true);
	}
}

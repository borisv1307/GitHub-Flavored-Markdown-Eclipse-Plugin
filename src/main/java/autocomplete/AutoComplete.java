package autocomplete;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import append.AddStuffToString;
import githubflavoredmarkdowneclipseplugin.MarkdownEditor;

public class AutoComplete extends JFrame {
	private JFrame frame = new JFrame("");
	private Container container = frame.getContentPane();
	private KeyListener listener;
	public String selectedContent = "";
	private JList list = null;
	private String[] str = { "# - Heading 1", "## - Heading 2", "### - Heading 3", "#### - Heading 4",
			"##### - Heading 5", "###### - Heading 6", "> - Block quote", "[] - Link(inlint) - [Text]",
			"\t - Code block (indented with tab)", "'inline code' - A span of code inline", "**strong** - strong",
			"_emphasis_ - emphasis" };

	MarkdownEditor markdownEditor;

	public AutoComplete(MarkdownEditor markdownEditor) {
		frame.setUndecorated(true);
		list = new JList(str);
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
					markdownEditor.test();
					AddStuffToString stuff = new AddStuffToString();
					switch (list.getSelectedIndex()) {
					case 0:
						selectedContent = stuff.addSingleCharacter(selectedContent, "#");
						break;
					case 1:
						selectedContent = stuff.addDoubleCharacter(selectedContent, "#");
						break;
					case 2:
						selectedContent = stuff.addTripleCharacter(selectedContent, "#");
						break;
					case 3:
						selectedContent = stuff.addFourCharacter(selectedContent, "#");
						break;
					case 4:
						selectedContent = stuff.addFiveCharacter(selectedContent, "#");
						break;
					case 5:
						selectedContent = stuff.addSixCharacter(selectedContent, "#");
						break;
					case 6:
						selectedContent = stuff.blockQuote(selectedContent);
						break;
					case 7:
						selectedContent = stuff.linkToURL(selectedContent);
						break;
					case 8:
						selectedContent = stuff.surroundDoubleCharacter(selectedContent, "\n\t", "\n");
						break;
					case 9:
						selectedContent = stuff.surroundDoubleCharacter(selectedContent, "'", "' ");
						break;
					case 10:
						selectedContent = stuff.surroundSingleCharacter(selectedContent, "**");
						break;
					case 11:
						selectedContent = stuff.surroundSingleCharacter(selectedContent, "_");
						break;
					default:
						break;
					}
					frame.dispose();
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

	@Override
	public void show() {
		Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
		this.frame.setLocation(point.x, point.y);
		frame.setVisible(true);
	}

//	public static void main(String[] args) {
//		AutoComplete panel = new AutoComplete();
//		panel.show("test");
//	}
}

package autocomplete;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import append.AddStuffToString;

public class AutoComplete extends JFrame {
	private JFrame frame = new JFrame("");
	private Container container = frame.getContentPane();
	private KeyListener listener;
	private String selectedContent = "";
	private JList list = null;
	private String[] str = { "# - Heading 1", "## - Heading 2", "### - Heading 3", "#### - Heading 4",
			"##### - Heading 5", "###### - Heading 6", "> - Block quote", "[] - Link(inlint) - [Text]",
			"\t - Code block (indented with tab)", "'inline code' - A span of code inline", "**strong** - strong",
			"_emphasis_ - emphasis" };

	public AutoComplete() {
		frame.setUndecorated(true);
		Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
		this.frame.setLocation(point.x, point.y);
		list = new JList(str);
		container.add(list);
		container.add(new JScrollPane(list));
		frame.setSize(300, 200);
		addListener();
	}

	private void addListener() {
		listener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					frame.dispose();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					AddStuffToString stuff = new AddStuffToString();
					String result = "";
					switch (list.getSelectedIndex()) {
					case 0:
						result = stuff.addSingleCharacter(selectedContent, "#");
						break;
					case 1:
						result = stuff.addDoubleCharacter(selectedContent, "#");
						break;
					case 2:
						result = stuff.addTripleCharacter(selectedContent, "#");
						break;
					case 3:
						result = stuff.addFourCharacter(selectedContent, "#");
						break;
					case 4:
						result = stuff.addFiveCharacter(selectedContent, "#");
						break;
					case 5:
						result = stuff.addSixCharacter(selectedContent, "#");
						break;
					case 6:
						result = stuff.blockQuote(selectedContent);
						break;
					case 7:
						result = stuff.linkToURL(selectedContent);
						break;
					case 8:
						result = stuff.surroundDoubleCharacter(selectedContent, "\n\t", "\n");
						break;
					case 9:
						result = stuff.surroundDoubleCharacter(selectedContent, "'", "' ");
						break;
					case 10:
						result = stuff.surroundSingleCharacter(selectedContent, "**");
						break;
					case 11:
						result = stuff.surroundSingleCharacter(selectedContent, "_");
						break;
					default:
						break;
					}
					frame.dispose();
					System.out.println(result);
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

	public void show(final String selection) {
		frame.setVisible(true);
		selectedContent = selection;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		AutoComplete panel = new AutoComplete();
		panel.show("test");
	}

}

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
	private JList<String> list = null;
	private String text = "";
	private String str[] = { "# - Heading 1", "## - Heading 2", "### - Heading 3", "#### - Heading 4",
			"##### - Heading 5", "###### - Heading 6", "> - Block quote", "[] - Link(inlint) - [Text]",
			"\t - Code block (indented with tab)", "'inline code' - A span of code inline", "**strong** - strong",
			"_emphasis_ - emphasis" };
	private AddStuffToString stuff = new AddStuffToString();

	public AutoComplete() {
		frame.setUndecorated(true);
		Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
		this.frame.setLocation(point.x, point.y);
		list = new JList<String>(str);
		container.add(list);
		container.add(new JScrollPane(list));
		frame.setSize(300, 200);
	}

	private void addListener(final String selectedContent) {
		list.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//					System.out.println("esc");
					frame.dispose();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					switch (list.getSelectedIndex()) {
					case 0:
						text = stuff.addSingleCharacter(selectedContent, "#");
						break;
					case 1:
						text = stuff.addDoubleCharacter(selectedContent, "#");
						break;
					case 2:
						text = stuff.addTripleCharacter(selectedContent, "#");
						break;
					case 3:
						text = stuff.addFourCharacter(selectedContent, "#");
						break;
					case 4:
						text = stuff.addFiveCharacter(selectedContent, "#");
						break;
					case 5:
						text = stuff.addSixCharacter(selectedContent, "#");
						break;
					case 6:
						text = stuff.blockQuote(selectedContent);
						break;
					case 7:
						text = stuff.linkToURL(selectedContent);
						break;
					case 8:
						text = stuff.surroundDoubleCharacter(selectedContent, "\n\t", "\n");
						break;
					case 9:
						text = stuff.surroundDoubleCharacter(selectedContent, "'", "' ");
						break;
					case 10:
						text = stuff.surroundSingleCharacter(selectedContent, "**");
						break;
					case 11:
						text = stuff.surroundSingleCharacter(selectedContent, "_");
						break;
					default:
						break;
					}
//					System.out.println(text);
					frame.dispose();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

		});
	}

	public void show(final String selection) {
		frame.setVisible(true);
		addListener(selection);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		AutoComplete panel = new AutoComplete();
		panel.show("test");
	}

}

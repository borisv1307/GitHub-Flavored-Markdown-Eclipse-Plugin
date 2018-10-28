package autoComplete;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import append.AddStuffToString;

public class JScrollPaneTest extends JFrame{
	private JFrame frame = new JFrame("hello world");
	private Container container = frame.getContentPane();
	private JList<String> list = null;
	private String text = "";
	private AddStuffToString stuff = new AddStuffToString();
	    public JScrollPaneTest(){
	    	frame.setUndecorated(true);  	
	    	Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
	    	this.frame.setLocation(point.x, point.y);
	    	String str[] ={"#-Heading 1","##-Heading 2","###-Heading 3","####-Heading 4","#####-Heading 5","######-Heading 6"};

	    	list = new JList<String>(str);
//	    	list.setBorder(BorderFactory.createTitledBorder("Markdown Grammer"));
//	    	list.setSelectedIndex(0);
	    	list.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						System.out.println("esc");
						frame.dispose();
					}
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
//						System.out.println(list.getSelectedIndex());
						switch(list.getSelectedIndex()) {
						case 0:
							text = stuff.addSingleCharacter("123", "#");
							break;
						case 1:
							text = stuff.addDoubleCharacter("", "#");
							break;
						case 2:
							text = stuff.addTripleCharacter("", "#");
							break;
						case 3:
							text = stuff.addFourCharacter("", "#");
							break;
						case 4:
							text = stuff.addFiveCharacter("", "#");
							break;
						case 5:
							text = stuff.addSixCharacter("", "#");
							break;
						}
						System.out.println(text);
						frame.dispose();
					}
					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
	    		
	    	});
	    	container.add(list);
	    	container.add(new JScrollPane(list));
	    	frame.setSize(400,200);
	    }
	    
	    public void show() {
	    	frame.setVisible(true);
	    	list.setFocusable(true);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    
	    public static void main(String[] args) {
	        // TODO Auto-generated method stub
	        JScrollPaneTest panel=new JScrollPaneTest();
	        panel.show();
	    }

	}

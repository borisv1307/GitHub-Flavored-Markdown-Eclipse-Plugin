package autoComplete;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import append.AddStuffToString;
public class MyContentAdapter implements IControlContentAdapter{
	private AddStuffToString stuff = new AddStuffToString();
	@Override
	public String getControlContents(Control control) {
		return ((Text) control).getText();
	}

	@Override
	public void setControlContents(Control control, String text,
			int cursorPosition) {
//		System.out.println(cursorPosition);
//		System.out.println(text);
		switch(text) {
		case "heading1":
			text = stuff.addSingleCharacter("", "#");
		case "heading2":
			text = stuff.addDoubleCharacter("", "#");
		case "heading3":
			text = stuff.addTripleCharacter("", "#");
		case "heading4":
			text = stuff.addFourCharacter("", "#");
		case "heading5":
			text = stuff.addFiveCharacter("", "#");
		case "heading6":
			text = stuff.addSixCharacter("", "#");
		}
		((Text) control).setText(text);
//		((Text) control).setSelection(cursorPosition, cursorPosition);
	}

	@Override
	public void insertControlContents(Control control, String text,
			int cursorPosition) {
		Point selection = ((Text) control).getSelection();
		((Text) control).insert(text);
		// Insert will leave the cursor at the end of the inserted text. If this
		// is not what we wanted, reset the selection.
		if (cursorPosition < text.length()) {
			((Text) control).setSelection(selection.x + cursorPosition,
					selection.x + cursorPosition);
		}
	}

	@Override
	public int getCursorPosition(Control control) {
		return ((Text) control).getCaretPosition();
	}

	@Override
	public Rectangle getInsertionBounds(Control control) {
		Text text = (Text) control;
		Point caretOrigin = text.getCaretLocation();
		// We fudge the y pixels due to problems with getCaretLocation
		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=52520
		return new Rectangle(caretOrigin.x + text.getClientArea().x,
				caretOrigin.y + text.getClientArea().y + 3, 1, text.getLineHeight());
	}

	@Override
	public void setCursorPosition(Control control, int position) {
		((Text) control).setSelection(new Point(position, position));
	}

	/**
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter2#getSelection(org.eclipse.swt.widgets.Control)
	 *
	 * @since 3.4
	 */
	public Point getSelection(Control control) {
		return ((Text) control).getSelection();
	}

	/**
	 * @see org.eclipse.jface.fieldassist.IControlContentAdapter2#setSelection(org.eclipse.swt.widgets.Control,
	 *      org.eclipse.swt.graphics.Point)
	 *
	 * @since 3.4
	 */
	public void setSelection(Control control, Point range) {
		((Text) control).setSelection(range);
	}
}

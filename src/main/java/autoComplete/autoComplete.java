package autoComplete;

import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import append.AddStuffToString;

public class autoComplete {
	public static void main(String[] args) {
		Display display = Display.getDefault();//获取Display
		Shell shell = new Shell();
		shell.setSize(300, 150); //设置窗体大小
		shell.setLocation(500, 200);//设置窗体位置
		shell.setText("自动补全");//设置标题
		 
		 
		//Text控件
		Text text = new Text(shell, SWT.BORDER);//添加一个Text控件
		text.setBounds(50, 20, 180, 28);//由于Shell没有加布局,这里设置它的位置和大小
		 
		//Text控件的自动补全
		MyContentAdapter adapter = new MyContentAdapter();
		
		String [] str = {"heading1","heading2","heading3","heading4","heading5","heading6"};
		SimpleContentProposalProvider proposalProvider = new SimpleContentProposalProvider(str);
		proposalProvider.setFiltering(true);
		AutoCompleteField auto_complete = new AutoCompleteField(text,adapter,str);	 
		 
		shell.open();
		while (!shell.isDisposed()) {//循环不让窗体关掉
		    if (!display.readAndDispatch()) {
		        display.sleep();
		    }
		}
		display.dispose();


	}
}

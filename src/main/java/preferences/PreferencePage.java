package preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import githubflavoredmarkdowneclipseplugin.Activator;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	private Activator activator;

	public PreferencePage() {
		super(GRID);
		IPreferenceStore store = activator.getDefault().getPreferenceStore();
		setPreferenceStore(store);
		setDescription("General settings for GFM Editor");
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		Composite parent = getFieldEditorParent();

		Label label = new Label(getFieldEditorParent(), SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
		addField(new BooleanFieldEditor(PreferenceConstants.P_TABLE,
				"&Use automatic table formatting feature (experimental)", parent));
		addField(new BooleanFieldEditor(PreferenceConstants.P_SUGGEST, "&Use suggestion feature", parent));
		addField(new BooleanFieldEditor(PreferenceConstants.P_TEMP_FILES, "&Hide html files", parent));

		Label label0 = new Label(getFieldEditorParent(), SWT.NONE);
		label0.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
		addField(new StringFieldEditor(PreferenceConstants.P_POPUP_WIDTH,
				"Enter the width of the suggestion popup window", parent));
		addField(new StringFieldEditor(PreferenceConstants.P_POPUP_HEIGHT,
				"Enter the height of the suggestion popup window", parent));

		Label label1 = new Label(getFieldEditorParent(), SWT.NONE);
		label1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
		label1.setText("	For a small window, the suggested size is 200 width and 100 height");

		Label label2 = new Label(getFieldEditorParent(), SWT.NONE);
		label2.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
		label2.setText("	For a medium window, the suggested size is 450 width and 300 height");

		Label label3 = new Label(getFieldEditorParent(), SWT.NONE);
		label3.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
		label3.setText("	For a large window, the suggested size is 650 width and 500 height");

		Label label4 = new Label(getFieldEditorParent(), SWT.NONE);
		label4.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));

		addField(new StringFieldEditor(PreferenceConstants.P_FONT_SIZE,
				"Enter the size of the text in the suggestion popup window", parent));
		addField(new BooleanFieldEditor(PreferenceConstants.P_LOG_ERRORS, "Enable error logs", parent));
	}

}

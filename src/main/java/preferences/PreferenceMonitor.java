package preferences;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import githubflavoredmarkdowneclipseplugin.Activator;

public class PreferenceMonitor implements IPreferenceChangeListener {
	private IPreferenceStore store;
	private static final int[] SMALL = { 200, 100 };
	private static final int[] MEDIUM = { 450, 300 };
	private static final int[] LARGE = { 650, 500 };
	private Activator activator;

	public PreferenceMonitor() {
		store = activator.getDefault().getPreferenceStore();
		store.addPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty() == "P_POPUP_SIZE") {
					setPopupSize();
				}
			}
		});
	}

	@Override
	public void preferenceChange(PreferenceChangeEvent event) {
		store = activator.getDefault().getPreferenceStore();
	}

	public boolean formatTable() {
		return store.getBoolean("P_TABLE");
	}

	public void setPopupSize() {
		String size = store.getString("P_POPUP_SIZE");
		if (size == "small") {
			store.setValue("P_POPUP_WIDTH", Integer.toString(SMALL[0]));
			store.setValue("P_POPUP_HEIGHT", Integer.toString(SMALL[1]));
		} else if (size == "large") {
			store.setValue("P_POPUP_WIDTH", Integer.toString(LARGE[0]));
			store.setValue("P_POPUP_HEIGHT", Integer.toString(LARGE[1]));
		} else {
			store.setValue("P_POPUP_WIDTH", Integer.toString(MEDIUM[0]));
			store.setValue("P_POPUP_HEIGHT", Integer.toString(MEDIUM[1]));
		}
	}

	public int popupWidth() {
		String size = store.getString("P_POPUP_WIDTH");
		int width;
		try {
			width = Integer.parseInt(size);
		} catch (NumberFormatException e) {
			// string is not a number. Use default
			return 450;
		}
		return width;
	}

	public int popupHeight() {
		String size = store.getString("P_POPUP_HEIGHT");
		int height;
		try {
			height = Integer.parseInt(size);
		} catch (NumberFormatException e) {
			// string is not a number. Use default
			return 300;
		}
		return height;
	}

	public int popupFontSize() {
		String size = store.getString("P_FONT_SIZE");
		int font;
		try {
			font = Integer.parseInt(size);
		} catch (NumberFormatException e) {
			// string is not a number. Use default
			return 16;
		}
		return font;
	}

	public boolean autocomplete() {
		return store.getBoolean("P_SUGGEST");
	}

	public boolean tempFile() {
		return store.getBoolean("P_TEMP_FILES");
	}

	public boolean logErrors() {
		return store.getBoolean("P_LOG_ERRORS");
	}

}

package preferences;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.jface.preference.IPreferenceStore;

import githubflavoredmarkdowneclipseplugin.Activator;

public class PreferenceMonitor implements IPreferenceChangeListener {
	private IPreferenceStore store;
	private static final int[] SMALL = { 200, 100 };
	private static final int[] MEDIUM = { 450, 300 };
	private static final int[] LARGE = { 650, 500 };
	private Activator activator;

	public PreferenceMonitor() {
		store = activator.getDefault().getPreferenceStore();
	}

	@Override
	public void preferenceChange(PreferenceChangeEvent event) {
		store = activator.getDefault().getPreferenceStore();
	}

	public boolean formatTable() {
		return store.getBoolean("P_TABLE");
	}

	public int[] popupSize() {
		String size = store.getString("P_POPUP");
		if (size.equals("large")) {
			return LARGE;
		} else if (size.equals("small")) {
			return SMALL;
		} else {
			return MEDIUM;
		}
	public boolean autocomplete() {
		return store.getBoolean("P_SUGGEST");
	}

}

package preferences;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.jface.preference.IPreferenceStore;

import githubflavoredmarkdowneclipseplugin.Activator;

public class PreferenceMonitor implements IPreferenceChangeListener {
	private IPreferenceStore store;
	private static final int[] SMALL = { 350, 200 };
	private static final int[] MEDIUM = { 450, 300 };
	private static final int[] LARGE = { 550, 400 };

	public PreferenceMonitor() {
		store = Activator.getDefault().getPreferenceStore();
	}

	@Override
	public void preferenceChange(PreferenceChangeEvent event) {
		store = Activator.getDefault().getPreferenceStore();
	}

	public boolean formatTable() {
		return store.getBoolean("P_TABLE");
	}

}

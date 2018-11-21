package preferences;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.jface.preference.IPreferenceStore;

import githubflavoredmarkdowneclipseplugin.Activator;

public class PreferenceMonitor implements IPreferenceChangeListener {
	private IPreferenceStore store;
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

	public boolean autocomplete() {
		return store.getBoolean("P_SUGGEST");
	}

}

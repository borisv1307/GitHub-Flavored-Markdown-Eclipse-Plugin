package githubflavoredmarkdowneclipseplugin.preferences;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;

import githubflavoredmarkdowneclipseplugin.Activator;

public class PreferenceMonitor implements IPreferenceChangeListener{
	private IPreferenceStore store;
	
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

package preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import githubflavoredmarkdowneclipseplugin.Activator;

public class PreferenceInitializer extends AbstractPreferenceInitializer {
	private Activator activator;

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_TABLE, true);
		store.setDefault(PreferenceConstants.P_SUGGEST, true);
		store.setDefault(PreferenceConstants.P_TEMP_FILES, true);
		store.setDefault(PreferenceConstants.P_POPUP_WIDTH, "450");
		store.setDefault(PreferenceConstants.P_POPUP_HEIGHT, "300");
		store.setDefault(PreferenceConstants.P_FONT_SIZE, "16");
		store.setDefault(PreferenceConstants.P_LOG_ERRORS, true);
	}

}

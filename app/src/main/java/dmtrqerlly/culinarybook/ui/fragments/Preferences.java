package dmtrqerlly.culinarybook.ui.fragments;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import dmtrqerlly.culinarybook.BuildConfig;
import dmtrqerlly.culinarybook.R;
import dmtrqerlly.culinarybook.utils.PreferenceHelper;

public class Preferences extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_fragment);

        final Preference appInfo = findPreference("appInfo");
        appInfo.setSummary(BuildConfig.VERSION_NAME);
        final CheckBoxPreference checkUpdate = (CheckBoxPreference) findPreference(PreferenceHelper.CHECK_UPDATE);
        final ListPreference themes = (ListPreference) findPreference(PreferenceHelper.THEMES);
        final String entry = getPreferenceManager().getSharedPreferences().getString(PreferenceHelper.THEMES, "1");

        themes.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(final Preference pPreference, final Object pO) {
                if (!entry.equals(pO.toString())) {
                    themes.setValue(pO.toString());
                    getActivity().recreate();
                }
                return false;
            }
        });
    }
}
package com.createchance.androidsettingsdemo;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrefsFragmentTwo extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private PreferenceScreen screen = null;


    public PrefsFragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference_settings);

        screen = getPreferenceScreen();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);

        SwitchPreference switchPreference = (SwitchPreference) findPreference("show_advanced_setting");
        if (switchPreference.isChecked()) {
            addPreferencesFromResource(R.xml.preference_advanced_setting);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        getActivity().setTitle("设置2");
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("DEBUG", "key: " + key);
        if ("show_advanced_setting".equals(key)) {
            boolean enabled = sharedPreferences.getBoolean(key, false);

            if (enabled) {
                // show advanced setting here.
                addPreferencesFromResource(R.xml.preference_advanced_setting);
            } else {
                // remove advanced setting here.
                PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("advanced_settings");
                if (preferenceCategory != null) {
                    screen.removePreference(preferenceCategory);
                }
            }
        }
    }
}

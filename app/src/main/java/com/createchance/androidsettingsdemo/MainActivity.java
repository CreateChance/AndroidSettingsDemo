package com.createchance.androidsettingsdemo;

import android.preference.PreferenceActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);

        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        Log.d("DEBUG", "isValidFragment, fragmentName: " + fragmentName);

        return true;
    }
}

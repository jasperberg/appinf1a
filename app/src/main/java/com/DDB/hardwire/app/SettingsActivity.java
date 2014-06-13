package com.DDB.hardwire.app;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.DDB.R;

public class SettingsActivity extends PreferenceActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);

    }
}

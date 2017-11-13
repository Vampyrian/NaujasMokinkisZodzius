package com.pv.vampyrian.mokinkiszodzius.ui.preference;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.pv.vampyrian.mokinkiszodzius.R;

public class MyPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }
}

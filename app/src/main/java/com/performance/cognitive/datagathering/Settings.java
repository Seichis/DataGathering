package com.performance.cognitive.datagathering;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity implements OnSharedPreferenceChangeListener{

    private static final String KEY_EDIT_TEXT_PREFERENCE = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    protected void onResume(){
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
        updatePreference(KEY_EDIT_TEXT_PREFERENCE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        updatePreference(key);
    }

    private void updatePreference(String key){
        if (key.equals(KEY_EDIT_TEXT_PREFERENCE)){
            Preference preference = findPreference(key);
            if (preference instanceof EditTextPreference){
                EditTextPreference editTextPreference =  (EditTextPreference)preference;
                if (editTextPreference.getText().trim().length() > 0){
                    editTextPreference.setSummary("Entered time is  " + editTextPreference.getText() + " msec");
                }else{
                    editTextPreference.setSummary("Enter time in msec");
                }
            }
        }
    }

}
package com.CarlosJimenez.SlotsApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsActivity extends AppCompatActivity {


    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            Preference button = getPreferenceManager().findPreference("balanceBtn");
            if (button != null) {
                button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference arg0) {
                        Toast.makeText(getActivity(), "$1000 added to your balance",
                                Toast.LENGTH_SHORT).show();

                        SharedPreferences mSharedPrefs;
                        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        int currentBalance = 0;
                        currentBalance = mSharedPrefs.getInt("balance",currentBalance);
                        SharedPreferences.Editor editor = mSharedPrefs.edit();
                        currentBalance+=1000;
                        editor.putInt("balance",currentBalance);
                        editor.commit();
                        return true;
                    }
                });

            }
            }
        }




    }
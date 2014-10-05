package de.hwr_berlin.lifepartner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;

import de.hwr_berlin.rp5000.R;


public class SettingsActivity extends LifePartnerActivity {


    private CheckedTextView btnColorblindMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnColorblindMode = (CheckedTextView) findViewById(R.id.btn_colorblind_mode);

        setupColorblindMode();
    }

    private void setupColorblindMode() {
        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        if (settings.getBoolean(PREF_COLOR_BLIND_MODE, false)) {
            btnColorblindMode.setChecked(true);
            btnColorblindMode.setCheckMarkDrawable(getResources().getDrawable(
                    R.drawable.checkbox_on));
        }

        btnColorblindMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleColorBlindMode();
            }
        });
    }

    private void toggleColorBlindMode() {
        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        if (btnColorblindMode.isChecked()) {
            editor.putBoolean(PREF_COLOR_BLIND_MODE, false);
            btnColorblindMode.setChecked(false);
            btnColorblindMode.setCheckMarkDrawable(getResources().getDrawable(
                    R.drawable.checkbox_off));
        } else {
            editor.putBoolean(PREF_COLOR_BLIND_MODE, true);
            btnColorblindMode.setChecked(true);
            btnColorblindMode.setCheckMarkDrawable(getResources().getDrawable(
                    R.drawable.checkbox_on));
        }
        editor.commit();
    }

    public void startAppManager(View view) {
        startActivity(new Intent(this, AppManagerActivity.class));
    }
}

package de.hwr_berlin.lifepartner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;


public class SettingsActivity extends LifePartnerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupCheckboxButton(R.id.btn_colorblind_mode, PREF_SETTING_COLORBLINDMODE);
        setupCheckboxButton(R.id.btn_texttospeech, PREF_SETTING_TEXTTOSPEECH);
    }

    private void setupCheckboxButton(int buttonId, String preference) {
        final CheckedTextView button = (CheckedTextView) findViewById(buttonId);
        SharedPreferences settings = getSharedPreferences(PREFS, 0);

        if (settings.getBoolean(preference, false)) {
            button.setChecked(true);
            button.setCheckMarkDrawable(getResources().getDrawable(
                    R.drawable.checkbox_on));
        }

        final String preferenceFinal = preference;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCheckbox(button, preferenceFinal);
            }
        });
    }

    private void toggleCheckbox(CheckedTextView button, String preference) {
        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        if (button.isChecked()) {
            editor.putBoolean(preference, false);
            button.setChecked(false);
            button.setCheckMarkDrawable(getResources().getDrawable(
                    R.drawable.checkbox_off));
        } else {
            editor.putBoolean(preference, true);
            button.setChecked(true);
            button.setCheckMarkDrawable(getResources().getDrawable(
                    R.drawable.checkbox_on));
        }
        editor.commit();
    }

    public void startAppManager(View view) {
        startActivity(new Intent(this, AppManagerActivity.class));
    }
}

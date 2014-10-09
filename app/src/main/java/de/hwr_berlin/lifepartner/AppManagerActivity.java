package de.hwr_berlin.lifepartner;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


public class AppManagerActivity extends LifePartnerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);

        setupAppToggles();
    }

    private void setupAppToggles() {
        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
        if (prefs.getBoolean(PREF_TOGGLE_APP_CAMERA, false)) {
            findViewById(R.id.toggle_app_camera).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
        if (prefs.getBoolean(PREF_TOGGLE_APP_ALARM, false)) {
            findViewById(R.id.toggle_app_alarm).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
        if (prefs.getBoolean(PREF_TOGGLE_APP_CALENDAR, false)) {
            findViewById(R.id.toggle_app_calendar).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
        if (prefs.getBoolean(PREF_TOGGLE_APP_CALCULATOR, false)) {
            findViewById(R.id.toggle_app_calculator).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
        if (prefs.getBoolean(PREF_TOGGLE_APP_FLASHLIGHT, false)) {
            findViewById(R.id.toggle_app_flashlight).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
        if (prefs.getBoolean(PREF_TOGGLE_APP_INTERNET, false)) {
            findViewById(R.id.toggle_app_internet).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
        if (prefs.getBoolean(PREF_TOGGLE_APP_EMAIL, false)) {
            findViewById(R.id.toggle_app_email).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
        if (prefs.getBoolean(PREF_TOGGLE_APP_MUSIC, false)) {
            findViewById(R.id.toggle_app_music).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
        if (prefs.getBoolean(PREF_TOGGLE_APP_MAPS, false)) {
            findViewById(R.id.toggle_app_maps).setBackground(getResources().getDrawable(R.drawable.checkbox_on));
        }
    }

    public void toggle(View view) {

        switch (view.getId()) {
            case R.id.toggle_app_camera:
                toggleApp(view, PREF_TOGGLE_APP_CAMERA);
                break;
            case R.id.toggle_app_alarm:
                toggleApp(view, PREF_TOGGLE_APP_ALARM);
                break;
            case R.id.toggle_app_calendar:
                toggleApp(view, PREF_TOGGLE_APP_CALENDAR);
                break;
            case R.id.toggle_app_calculator:
                toggleApp(view, PREF_TOGGLE_APP_CALCULATOR);
                break;
            case R.id.toggle_app_flashlight:
                toggleApp(view, PREF_TOGGLE_APP_FLASHLIGHT);
                break;
            case R.id.toggle_app_internet:
                toggleApp(view, PREF_TOGGLE_APP_INTERNET);
                break;
            case R.id.toggle_app_email:
                toggleApp(view, PREF_TOGGLE_APP_EMAIL);
                break;
            case R.id.toggle_app_music:
                toggleApp(view, PREF_TOGGLE_APP_MUSIC);
                break;
            case R.id.toggle_app_maps:
                toggleApp(view, PREF_TOGGLE_APP_MAPS);
                break;
        }

    }

    private void toggleApp(View view, String preferencesName) {
        SharedPreferences prefs = getSharedPreferences(PREFS, 0);
        SharedPreferences.Editor editor = prefs.edit();

        if (!prefs.getBoolean(preferencesName, false)) {
            view.setBackground(getResources().getDrawable(R.drawable.checkbox_on));
            editor.putBoolean(preferencesName, true);
        } else {
            view.setBackground(getResources().getDrawable(R.drawable.checkbox_off));
            editor.putBoolean(preferencesName, false);
        }

        editor.commit();
    }
}

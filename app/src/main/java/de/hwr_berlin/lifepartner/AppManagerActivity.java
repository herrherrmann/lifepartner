package de.hwr_berlin.lifepartner;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import de.hwr_berlin.rp5000.R;


public class AppManagerActivity extends LifePartnerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
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

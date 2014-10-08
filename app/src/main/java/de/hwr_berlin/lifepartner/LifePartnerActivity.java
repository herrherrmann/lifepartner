package de.hwr_berlin.lifepartner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import de.hwr_berlin.rp5000.R;

public class LifePartnerActivity extends Activity {

    public static final String PREFS = "LifePartnerSettings";
    public static final String PREF_SETTING_COLORBLINDMODE = "colorBlindMode";
    public static final String PREF_SETTING_TEXTTOSPEECH = "textToSpeech";
    public static final String PREF_CAMERA_ZOOM = "cameraZoom";

    public static final String PREF_TOGGLE_APP_CAMERA = "toggleAppCamera";
    public static final String PREF_TOGGLE_APP_ALARM = "toggleAppAlarm";
    public static final String PREF_TOGGLE_APP_CALENDAR = "toggleAppCalendar";
    public static final String PREF_TOGGLE_APP_CALCULATOR = "toggleAppCalculator";
    public static final String PREF_TOGGLE_APP_FLASHLIGHT = "toggleAppFlashlight";
    public static final String PREF_TOGGLE_APP_INTERNET = "toggleAppInternet";
    public static final String PREF_TOGGLE_APP_EMAIL = "toggleAppEmail";
    public static final String PREF_TOGGLE_APP_MUSIC = "toggleAppMusic";
    public static final String PREF_TOGGLE_APP_MAPS = "toggleAppMaps";

    int onStartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onStartCount = 1;
        if (savedInstanceState == null) // 1st time
        {
            this.overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_left);
        } else // already created so reverse animation
        {
            onStartCount = 2;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_right);

        } else if (onStartCount == 1) {
            onStartCount++;
        }
    }

    public void finish(View view) {
        finish();
    }
}
package de.hwr_berlin.lifepartner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import de.hwr_berlin.rp5000.R;

public class LifePartnerActivity extends Activity {

    public static final String PREFS = "LifePartnerSettings";
    public static final String PREF_COLOR_BLIND_MODE = "colorBlindMode";
    public static final String PREF_CAMERA_ZOOM = "cameraZoom";

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
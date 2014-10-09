package de.hwr_berlin.lifepartner;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SosActivity extends LifePartnerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
    }

    public void sendSos(View view) {
        ImageView sosRing = (ImageView) findViewById(R.id.sos_ring);

        // Rotation Time!
        RotateAnimation ra = new RotateAnimation(0, 359, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(1200);
        ra.setInterpolator(new LinearInterpolator());
        ra.setFillAfter(true);
        ra.setRepeatCount(Animation.INFINITE);
        sosRing.startAnimation(ra);

        TextView sosInfo = (TextView) findViewById(R.id.sos_info);
        sosInfo.setText(getString(R.string.sos_calling));

        Button btnSosSend = (Button) findViewById(R.id.btn_sos_send);
        btnSosSend.setText(getString(R.string.sos_call_sent));
        btnSosSend.setBackground(getResources().getDrawable(R.color.app_settings));
        btnSosSend.setVisibility(View.GONE);

        Button btnSosCancel = (Button) findViewById(R.id.btn_sos_cancel);
        btnSosCancel.setText(getString(R.string.sos_call_cancel));
    }
}

package de.hwr_berlin.lifepartner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hwr_berlin.rp5000.R;

public class SosActivity extends LifePartnerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
    }

    public void sendSos(View view) {
        TextView sosInfo = (TextView) findViewById(R.id.sos_info);
        sosInfo.setText("Wir versuchen nun, Beate Frühling zu erreichen ...");

        Button btnSosSend = (Button) findViewById(R.id.btn_sos_send);
        btnSosSend.setText("Notruf gesendet");
        btnSosSend.setBackground(getResources().getDrawable(R.color.app_settings));
        btnSosSend.setVisibility(View.GONE);

        Button btnSosCancel = (Button) findViewById(R.id.btn_sos_cancel);
        btnSosCancel.setText("Notruf abbrechen");
    }
}

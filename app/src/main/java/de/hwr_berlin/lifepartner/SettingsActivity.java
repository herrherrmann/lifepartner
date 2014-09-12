package de.hwr_berlin.lifepartner;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;

import de.hwr_berlin.rp5000.R;


public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupColorblindModeCheckbox();
    }

    private void setupColorblindModeCheckbox() {
        final CheckedTextView btnColorblindMode = (CheckedTextView) findViewById(R.id.btn_colorblind_mode);
        btnColorblindMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnColorblindMode.isChecked()) {
                    btnColorblindMode.setChecked(false);
                    btnColorblindMode.setCheckMarkDrawable(getResources().getDrawable(R.drawable.checkbox_off));
                } else {
                    btnColorblindMode.setChecked(true);
                    btnColorblindMode.setCheckMarkDrawable(getResources().getDrawable(R.drawable.checkbox_on));
                }
            }
        });
    }

    public void finish(View view) {
        finish();
    }
}

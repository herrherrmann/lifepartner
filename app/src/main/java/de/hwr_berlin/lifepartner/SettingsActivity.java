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

        final CheckedTextView btnColorblindMode = (CheckedTextView) findViewById(R.id.btn_colorblind_mode);
        btnColorblindMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("debug", "Check it!");
                if (btnColorblindMode.isChecked()) {
                    btnColorblindMode.setChecked(false);
                } else {
                    btnColorblindMode.setChecked(true);
                }
                // btnColorblindMode.toggle();
            }
        });
    }

//    public void checkColorblindMode(View view){
//        Toast.makeText(this, "Check it!", Toast.LENGTH_SHORT).show();
//        CheckedTextView btnColorblindMode = (CheckedTextView) findViewById(R.id.btn_colorblind_mode);
//        btnColorblindMode.toggle();
//    }

}

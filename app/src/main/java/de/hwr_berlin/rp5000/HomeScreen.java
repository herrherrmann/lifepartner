package de.hwr_berlin.rp5000;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import de.hwr_berlin.rp5000.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class HomeScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);

        final View contentView = findViewById(R.id.fullscreen_content);


    }
}

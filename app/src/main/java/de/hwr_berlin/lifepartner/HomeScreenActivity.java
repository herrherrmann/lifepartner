package de.hwr_berlin.lifepartner;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class HomeScreenActivity extends LifePartnerActivity implements TextToSpeech.OnInitListener {

    private static Context context;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);
        final View contentView = findViewById(R.id.fullscreen_content);
        context = contentView.getContext();
        tts = new TextToSpeech(this, this);

        updateStatusBarDate();
        updateStatusBarBattery();
        updateStatusBarSignal();
        // updateAppBtnSms();
    }

    private void setupApps() {
        LinearLayout appSelection = (LinearLayout) findViewById(R.id.app_selection);
        appSelection.removeAllViews();

        // Standard Apps
        Button btnPhone = createAppButton(R.string.app_phone, R.drawable.phone, R.color.app_phone);
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAppPhone(view);
            }
        });
        appSelection.addView(btnPhone);

        Button btnSms = createAppButton(R.string.app_sms, R.drawable.sms_closed, R.color.app_sms);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAppSms(view);
            }
        });
        appSelection.addView(btnSms);

        Button btnMagnifier = createAppButton(R.string.app_magnifier, R.drawable.magnifier, R.color.app_magnifier);
        btnMagnifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAppMagnifier(view);
            }
        });
        appSelection.addView(btnMagnifier);

        Button btnHealth = createAppButton(R.string.app_health, R.drawable.health_apple, R.color.app_health);
        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAppHealth(view);
            }
        });
        appSelection.addView(btnHealth);

        Button btnSos = createAppButton(R.string.app_sos, R.drawable.sos, R.color.app_sos);
        btnSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAppSos(view);
            }
        });
        appSelection.addView(btnSos);

        // Dynamically selected Apps
        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        if (settings.getBoolean(PREF_TOGGLE_APP_CAMERA, false)) {
            appSelection.addView(createAppButton(R.string.app_camera, R.drawable.camera, R.color.app_camera));
        }
        if (settings.getBoolean(PREF_TOGGLE_APP_ALARM, false)) {
            appSelection.addView(createAppButton(R.string.app_alarm, R.drawable.alarm_clock, R.color.app_alarm));
        }
        if (settings.getBoolean(PREF_TOGGLE_APP_CALENDAR, false)) {
            appSelection.addView(createAppButton(R.string.app_calendar, R.drawable.calendar, R.color.app_calendar));
        }
        if (settings.getBoolean(PREF_TOGGLE_APP_CALCULATOR, false)) {
            appSelection.addView(createAppButton(R.string.app_calculator, R.drawable.calculator, R.color.app_calculator));
        }
        if (settings.getBoolean(PREF_TOGGLE_APP_FLASHLIGHT, false)) {
            appSelection.addView(createAppButton(R.string.app_flashlight, R.drawable.light, R.color.app_flashlight));
        }
        if (settings.getBoolean(PREF_TOGGLE_APP_INTERNET, false)) {
            appSelection.addView(createAppButton(R.string.app_internet, R.drawable.chrome, R.color.app_internet));
        }
        if (settings.getBoolean(PREF_TOGGLE_APP_EMAIL, false)) {
            appSelection.addView(createAppButton(R.string.app_email, R.drawable.email, R.color.app_email));
        }
        if (settings.getBoolean(PREF_TOGGLE_APP_MUSIC, false)) {
            appSelection.addView(createAppButton(R.string.app_music, R.drawable.music, R.color.app_music));
        }
        if (settings.getBoolean(PREF_TOGGLE_APP_MAPS, false)) {
            appSelection.addView(createAppButton(R.string.app_maps, R.drawable.maps, R.color.app_maps));
        }

        // Settings always at the end
        Button btnSettings = createAppButton(R.string.app_settings, R.drawable.settings, R.color.app_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAppSettings(view);
            }
        });
        appSelection.addView(btnSettings);
    }

    private Button createAppButton(int name, int icon, int color) {
        Button appBtn = (Button) getLayoutInflater().inflate(R.layout.home_screen_app_button, null);
        appBtn.setText(getResources().getString(name));
        appBtn.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(icon), null, null, null);
        appBtn.setBackgroundColor(getResources().getColor(color));

        return appBtn;
    }

    /**
     * Currently only adds an onClickListener for the tts output.
     */
    private void updateStatusBarTime() {
        TextClock statusBarTime = (TextClock) findViewById(R.id.status_bar_time);
        statusBarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tts.isSpeaking()) {
                    String hour = new SimpleDateFormat("h").format(System.currentTimeMillis());
                    String minute = new SimpleDateFormat("m").format(System.currentTimeMillis());
                    textToSpeech("Es ist " + hour + " Uhr " + minute + ".");
                }
            }
        });
    }

    private void updateStatusBarDate() {
        TextView statusBarDate = (TextView) findViewById(R.id.status_bar_date);
        String dateString = new SimpleDateFormat("EE, dd.MM.yy").format(System.currentTimeMillis());
        statusBarDate.setText(dateString);
        statusBarDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tts.isSpeaking()) {
                    String weekDay = new SimpleDateFormat("EEEE").format(
                            System.currentTimeMillis());
                    String fullDate = new SimpleDateFormat("d. MMMM yyyy").format(
                            System.currentTimeMillis());
                    textToSpeech("Heute ist " + weekDay + ", der " + fullDate + ".");
                }
            }
        });
    }

    private void updateStatusBarBattery() {
        ImageView statusBarBattery = (ImageView) findViewById(R.id.status_bar_battery);
        float batteryLevel = getBatteryLevel();
        if (batteryLevel >= 0.9) {
            statusBarBattery.setImageDrawable(getResources().getDrawable(R.drawable.battery_3));
        } else if (batteryLevel < 0.9 && batteryLevel > 0.3) {
            statusBarBattery.setImageDrawable(getResources().getDrawable(R.drawable.battery_2));
        } else {
            statusBarBattery.setImageDrawable(getResources().getDrawable(R.drawable.battery_1));
        }

        statusBarBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tts.isSpeaking()) {
                    textToSpeech("Die Batterie ist zu " + (int) (getBatteryLevel() * 100) +
                            "% geladen.");
                }
            }
        });
    }

    private void updateStatusBarSignal() {
        ImageView statusBarSignal = (ImageView) findViewById(R.id.status_bar_signal);
        statusBarSignal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tts.isSpeaking()) {
                    textToSpeech("Die Empfangsqualität ist gut.");
                }
            }
        });
    }

    private float getBatteryLevel() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return level / (float) scale;
    }

    /*
    private void updateAppBtnSms() {
        Button appBtnSms = (Button) findViewById(R.id.app_btn_sms);
        if (getUnreadSms() > 0) {
            appBtnSms.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(
                    R.drawable.sms_open), null, null, null);
            appBtnSms.setText(getResources().getString(R.string.app_sms) + " (" + getUnreadSms() +
                    ")");
        } else {
            appBtnSms.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(
                    R.drawable.sms_closed), null, null, null);
            appBtnSms.setText(getResources().getString(R.string.app_sms));
        }
    } */

    private int getUnreadSms() {
        final Uri SMS_INBOX = Uri.parse("content://sms/inbox");

        Cursor c = context.getContentResolver().query(SMS_INBOX, null, "read = 0", null, null);
        int unreadSms = c.getCount();
        c.deactivate();
        return unreadSms;
    }

    private void textToSpeech(String message) {
        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        if (settings.getBoolean(PREF_SETTING_TEXTTOSPEECH, false)) {
            tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupApps();
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
        }
        tts.shutdown();

        super.onDestroy();
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.getDefault());
        } else {
            tts = null;
            Log.d("debug", "TTS konnte nicht initialisiert werden.");
        }
    }

    /*
    * Starting Apps
    * */
    public void startAppPhone(View view) {
        startActivity(new Intent(this, PhoneActivity.class));
    }

    public void startAppSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void startAppSms(View view) {
        startActivity(new Intent(this, SmsActivity.class));
    }

    public void startAppMagnifier(View view) {
        startActivity(new Intent(this, CameraActivity.class));
    }

    public void startAppSos(View view) {
        startActivity(new Intent(this, SosActivity.class));
    }

    public void startAppHealth(View view) {
        startActivity(new Intent(this, HealthActivity.class));
    }
}

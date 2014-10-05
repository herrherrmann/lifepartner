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
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

import de.hwr_berlin.rp5000.R;

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

		updateAppBtnSms();
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
	}

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
	* Starting other Activities
	* */
	public void startPhone(View view) {
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
		startActivity(new Intent(this, PhoneActivity.class));
	}

	public void startSettings(View view) {
		startActivity(new Intent(this, SettingsActivity.class));
	}

	public void startSms(View view) {
		startActivity(new Intent(this, SmsActivity.class));
	}

	public void startMagnifier(View view) {
		startActivity(new Intent(this, CameraActivity.class));
	}
}

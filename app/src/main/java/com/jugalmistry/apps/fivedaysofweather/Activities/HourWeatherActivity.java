package com.jugalmistry.apps.fivedaysofweather.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jugalmistry.apps.fivedaysofweather.R;

public class HourWeatherActivity extends AppCompatActivity {
    private TextView date, time, shortD, longD, tmin, tmax, humid;
    private ImageView iconNewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_weather);
        Intent intent = getIntent();
        String dateS = intent.getStringExtra("DATE");
        String temMin = intent.getStringExtra("TEMP_MIN");
        String temMax = intent.getStringExtra("TEMP_MAX");
        String humidity = intent.getStringExtra("HUMIDITY");
        String longDesc = intent.getStringExtra("LONG_DESC");
        String sDesc = intent.getStringExtra("SHORT_DESC");
        String iconID = intent.getStringExtra("ICON_ID");
        int iconResID = intent.getIntExtra("ICO_RES_ID",0);
        date = (TextView) findViewById(R.id.datetv);
        time = (TextView) findViewById(R.id.timetv);
        shortD = (TextView) findViewById(R.id.shortDesctv);
        longD = (TextView) findViewById(R.id.longdesctv);
        tmin = (TextView) findViewById(R.id.temMin);
        tmax = (TextView) findViewById(R.id.temMax);
        humid = (TextView) findViewById(R.id.humiditytv);
        iconNewActivity = (ImageView) findViewById(R.id.iconNewActivty);
        date.setText(dateS.substring(0,10));
        time.setText(dateS.substring(11,16));
        shortD.setText(sDesc);
        longD.setText(longDesc);
        tmax.setText("Max : " + temMax + " °C");
        tmin.setText("Max : " + temMin + " °C");
        humid.setText("Humidity : " + humidity + "%");
        iconNewActivity.setImageResource(iconResID);

    }
}

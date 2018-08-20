package com.jugalmistry.apps.fivedaysofweather.AdaptersAndHolders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jugalmistry.apps.fivedaysofweather.R;
import com.jugalmistry.apps.fivedaysofweather.Utilities.WeatherData;
import com.jugalmistry.apps.fivedaysofweather.Activities.HourWeatherActivity;

public class WeatherHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView descrip, temperature;
    private ImageView icon;
    private Context context;
    private WeatherData weatherData;
    private int iconResID;

    public WeatherHolder(Context context, View view){
        super(view);
        this.context = context;
        this.descrip = (TextView) view.findViewById(R.id.descrip);
        this.temperature = (TextView) view.findViewById(R.id.temperature);
        this.icon = (ImageView) view.findViewById(R.id.iconHour);
        view.setOnClickListener(this);
    }

    public void bindWeatherData(WeatherData weatherData){
        this.weatherData = weatherData;
        this.descrip.setText(weatherData.getShortDesc());
        this.temperature.setText(weatherData.getTemp() + " °C");
        String iconID = weatherData.getIconID();
        if(iconID.equals("01d")){
            iconResID = R.drawable.ico01d;
            icon.setImageResource(R.drawable.ico01d);
        } else if(iconID.equals("01n")){
            iconResID = R.drawable.ico01n;
            icon.setImageResource(R.drawable.ico01n);
        } else if(iconID.equals("02d")){
            iconResID = R.drawable.ico02d;
            icon.setImageResource(R.drawable.ico02d);
        } else if(iconID.equals("02n")){
            iconResID = R.drawable.ico02n;
            icon.setImageResource(R.drawable.ico02n);
        } else if(iconID.equals("03d") || iconID.equals("03n")){
            iconResID = R.drawable.ico03d;
            icon.setImageResource(R.drawable.ico03d);
        } else if (iconID.equals("04d") || iconID.equals("04n")){
            iconResID = R.drawable.ico04d;
            icon.setImageResource(R.drawable.ico04d);
        } else if (iconID.equals("09d") || iconID.equals("09n")){
            iconResID = R.drawable.ico09d;
            icon.setImageResource(R.drawable.ico09d);
        } else if(iconID.equals("10d")){
            iconResID = R.drawable.ico10d;
            icon.setImageResource(R.drawable.ico10d);
        } else if(iconID.equals("10n")){
            iconResID = R.drawable.ico10n;
            icon.setImageResource(R.drawable.ico10n);
        } else if(iconID.equals("11d")){
            iconResID = R.drawable.ico11d;
            icon.setImageResource(R.drawable.ico11d);
        } else if(iconID.equals("11n")){
            iconResID = R.drawable.ico11n;
            icon.setImageResource(R.drawable.ico11n);
        } else if(iconID.equals("13d")){
            iconResID = R.drawable.ico13d;
            icon.setImageResource(R.drawable.ico13d);
        } else if(iconID.equals("13n")){
            iconResID = R.drawable.ico13n;
            icon.setImageResource(R.drawable.ico13n);
        } else if(iconID.equals("50d")){
            iconResID = R.drawable.ico50d;
            icon.setImageResource(R.drawable.ico50d);
        } else if(iconID.equals("50n")){
            iconResID = R.drawable.ico50n;
            icon.setImageResource(R.drawable.ico50n);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,HourWeatherActivity.class);
        intent.putExtra("DATE",weatherData.getDate());
        intent.putExtra("TEMP_MIN",weatherData.getMinTemp());
        intent.putExtra("TEMP_MAX",weatherData.getMaxTemp());
        intent.putExtra("HUMIDITY",weatherData.getHumidity());
        intent.putExtra("LONG_DESC",weatherData.getDescription());
        intent.putExtra("SHORT_DESC",weatherData.getShortDesc());
        intent.putExtra("ICO_RES_ID",iconResID);
        context.startActivity(intent);
    }
}

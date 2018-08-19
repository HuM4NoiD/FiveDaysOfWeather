package com.jugalmistry.apps.fivedaysofweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends ArrayAdapter<WeatherData> {
    private static final String TAG = "WeatherAdapter";
    private final int layoutResourceID;
    private LayoutInflater layoutInflater;
    private ArrayList<WeatherData> block;

    public WeatherAdapter(@NonNull Context context, int resource, ArrayList<WeatherData> block) {
        super(context, resource, block);
        this.layoutResourceID = resource;
        this.block = block;
        this.layoutInflater = LayoutInflater.from(context);
        Log.d(TAG, "WeatherAdapter: called constructor");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(layoutResourceID,parent,false);
        }
        Log.d(TAG, "getView: entered");
        WeatherData weatherData = block.get(position);
        TextView temp = (TextView) convertView.findViewById(R.id.temperature);
        temp.setText(weatherData.getTemp() + "°C");
        TextView shortDesc = (TextView) convertView.findViewById(R.id.descrip);
        shortDesc.setText(weatherData.getShortDesc());
        return convertView;
    }
}

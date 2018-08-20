package com.jugalmistry.apps.fivedaysofweather.AdaptersAndHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jugalmistry.apps.fivedaysofweather.Utilities.WeatherData;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {
    private static final String TAG = "WeatherAdapter";
    private final int layoutResourceID;
    private LayoutInflater layoutInflater;
    private ArrayList<WeatherData> block;
    private Context context;

    public WeatherAdapter(@NonNull Context context, int resource, ArrayList<WeatherData> block) {
        this.layoutResourceID = resource;
        this.context = context;
        this.block = block;
        this.layoutInflater = LayoutInflater.from(context);
        Log.d(TAG, "WeatherAdapter: called constructor");
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.layoutResourceID, parent, false);
        return new WeatherHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
        WeatherData weather = this.block.get(position);
        holder.bindWeatherData(weather);
    }

    @Override
    public int getItemCount() {
        return this.block.size();
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if(convertView == null){
//            convertView = layoutInflater.inflate(layoutResourceID,parent,false);
//        }
//        Log.d(TAG, "getView: entered");
//        WeatherData weatherData = block.get(position);
//        TextView temp = (TextView) convertView.findViewById(R.id.temperature);
//        temp.setText(weatherData.getTemp() + "°C");
//        TextView shortDesc = (TextView) convertView.findViewById(R.id.descrip);
//        shortDesc.setText(weatherData.getShortDesc());
//        return convertView;
//    }
}

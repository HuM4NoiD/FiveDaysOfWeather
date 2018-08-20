package com.jugalmistry.apps.fivedaysofweather.AdaptersAndHolders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jugalmistry.apps.fivedaysofweather.Utilities.JsonToWeatherData;
import com.jugalmistry.apps.fivedaysofweather.R;
import com.jugalmistry.apps.fivedaysofweather.Utilities.WeatherData;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DayAdapter extends ArrayAdapter<String> {
    private static final String TAG = "DayAdapter";
    private ArrayList<String> dayBlock;
    private LayoutInflater layoutInflater;
    private int layoutresourceID;
    private ArrayList<WeatherData> dayWeather, fullBlock;
    private Context context;
    JsonToWeatherData json = new JsonToWeatherData();

    public DayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> dayBlock, ArrayList<WeatherData> weatherBlock) {
        super(context, resource, dayBlock);
        this.context = context;
        this.dayBlock = dayBlock;
        this.fullBlock = weatherBlock;
        layoutInflater = LayoutInflater.from(context);
        this.layoutresourceID = resource;
        if(fullBlock == null){
            Log.e(TAG, "DayAdapter: full block is null");
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(layoutresourceID,parent,false);
        }
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView minTempFoDay = (TextView) convertView.findViewById(R.id.minTempOfDay);
        TextView maxTempFoDay = (TextView) convertView.findViewById(R.id.maxTempOfDay);
        String dateString = dayBlock.get(position);
        dayWeather = json.extractByDate(fullBlock,dateString);
        date.setText(dateString);
        DecimalFormat df = new DecimalFormat(".##");
        float mint = 500, maxt = 0;
        String mint1 = "", maxt1 = "";
        for(WeatherData data : dayWeather){
            if(mint > Float.parseFloat(data.getMinTemp())){
                mint = Float.parseFloat(data.getMinTemp());
                mint1 = df.format(mint);
                Log.d(TAG, "getView: mint : " + mint);
            }
            if (maxt < Float.parseFloat(data.getMaxTemp())){
                maxt = Float.parseFloat(data.getMaxTemp());
                maxt1 = df.format(maxt);
            }
        }
        minTempFoDay.setText("Min : " + mint1 + " °C");
        maxTempFoDay.setText("Max : " + maxt1 + " °C");
        WeatherAdapter weatherAdapter = new WeatherAdapter(context,R.layout.weather_holder,dayWeather);
        RecyclerView weatherHolderListView = (RecyclerView) convertView.findViewById(R.id.wHoldLV);
        weatherHolderListView.setHasFixedSize(true);
        weatherHolderListView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        weatherHolderListView.setAdapter(weatherAdapter);

        return convertView;
    }
}

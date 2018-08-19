package com.jugalmistry.apps.fivedaysofweather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonToWeatherData {
    private static final String TAG = "JsonToWeatherData";

    public ArrayList<WeatherData> extractor(String jsonData){
        Log.d(TAG, "extractor: in the method");
        if(jsonData == null){
            return null; // if there is no json data is received
        } else {
            ArrayList<WeatherData> weatherDataArrayList = new ArrayList<WeatherData>();
            Log.d(TAG, "extractor: in the else field");
            try{
                Log.d(TAG, "extractor: in try block");
                JSONObject root = new JSONObject(jsonData);
                int count = root.getInt("cnt");
                JSONArray wList = root.getJSONArray("list");
                for (int i = 0; i < count; ++i){
                    WeatherData weather = new WeatherData();
                    JSONObject wBlock = wList.getJSONObject(i);
                    weather.setDate(wBlock.getString("dt_txt"));
                    JSONObject mainObj = wBlock.getJSONObject("main");
                    weather.setTemp(String.valueOf(mainObj.getDouble("temp")));
                    weather.setMinTemp(String.valueOf(mainObj.getDouble("temp_min")));
                    weather.setMaxTemp(String.valueOf(mainObj.getDouble("temp_max")));
                    weather.setHumidity(String.valueOf(mainObj.getInt("humidity")));
                    JSONArray warray = wBlock.getJSONArray("weather");
                    JSONObject weatherObj = warray.getJSONObject(0);
                    weather.setDescription(weatherObj.getString("description"));
                    weather.setShortDesc(weatherObj.getString("main"));
                    weather.setIconID(weatherObj.getString("icon"));
                    weatherDataArrayList.add(weather);
                    Log.d(TAG, "extractor: temp field is :" + weather.getTemp());
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return weatherDataArrayList;
        }
    }

    public ArrayList<WeatherData> extractByDate(ArrayList<WeatherData> fullList,String date){
        ArrayList<WeatherData> dayweatherList = new ArrayList<WeatherData>();
        for( WeatherData weather : fullList ){
            if( ( weather.getDate().substring(0,9) ).equals(date) ){
                dayweatherList.add(weather);
            }
        }
        return dayweatherList;
    }
}

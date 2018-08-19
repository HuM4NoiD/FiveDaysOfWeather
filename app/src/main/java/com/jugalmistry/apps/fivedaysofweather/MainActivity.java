package com.jugalmistry.apps.fivedaysofweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String responseJSON = null;
    ListView listView;
    ArrayList<WeatherData> weatherDataArrayList;
    WeatherAdapter weatherAdapter = null;
    EditText cityName;
    String city = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.dayList);
        cityName = (EditText) findViewById(R.id.cityName);
        Button load = (Button) findViewById(R.id.loadButton);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city = cityName.getText().toString();
                Log.d(TAG, "onClick: city is : " + city);
                if(city == null){
                    Toast toast = null;
                    toast.makeText(MainActivity.this,"Please Enter a city before continuing",Toast.LENGTH_LONG);
                    toast.show();
                } else {

                    String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + (city.toLowerCase()) + "&units=metric&appid=8b10912e19fde267f36f6cb785ee7efd";

                    Log.d(TAG, "onCreate: staring download task");
                    DownloadJSON downloadJSON = new DownloadJSON();
                    downloadJSON.execute(url);
                    Log.d(TAG, "onCreate: after downloadtask");
                }
            }
        });


        if(weatherDataArrayList == null){
            Log.d(TAG, "onCreate: ArrayList is Still null");
        }
    }

    private class DownloadJSON extends AsyncTask<String, Void, String>{
        private static final String TAG = "DownloadJSON";

        private String downloadJSON(String url){
            StringBuilder jsonResult = new StringBuilder();
            try{
                URL apiURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
                int responseCode = connection.getResponseCode();
                Log.d(TAG, "downloadJSON: Response code "+ responseCode);
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                int charReader;
                char[] inputBuffer = new char[500];
                while(true){
                    charReader = reader.read(inputBuffer);
                    if(charReader < 0){
                        break;
                    }
                    if(charReader > 0){
                        jsonResult.append(String.copyValueOf(inputBuffer, 0, charReader));
                    }
                }
                reader.close();
                return jsonResult.toString();
            }catch (MalformedURLException e){
                Log.e(TAG, "downloadJSON: URL is Invalid");
            }catch (IOException e){
                Log.e(TAG, "downloadJSON: IO Error");
            }
            return null;
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: url is : " + strings[0]);
            String jsonResponse = downloadJSON(strings[0]);
            if(jsonResponse == null){
                Log.e(TAG, "doInBackground: Error downloading");
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            super.onPostExecute(jsonResponse);
            Log.d(TAG, "onPostExecute: json received is : " + jsonResponse);
            if(jsonResponse != null){
                JsonToWeatherData jtwd = new JsonToWeatherData();
                weatherDataArrayList = jtwd.extractor(jsonResponse);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                String date1 = simpleDateFormat.format(calendar.getTime());
                calendar.add(Calendar.DATE,1);
                String date2 = simpleDateFormat.format(calendar.getTime());
                calendar.add(Calendar.DATE,1);
                String date3 = simpleDateFormat.format(calendar.getTime());
                calendar.add(Calendar.DATE,1);
                String date4 = simpleDateFormat.format(calendar.getTime());
                calendar.add(Calendar.DATE,1);
                String date5 = simpleDateFormat.format(calendar.getTime());
                ArrayList<String> days = new ArrayList<>();
                days.add(date1);
                days.add(date2);
                days.add(date3);
                days.add(date4);
                days.add(date5);
//                DayAdapter day = new DayAdapter(MainActivity.this,R.layout.layout_day_card,days,weatherDataArrayList);
//                listView.setAdapter(day);
                weatherAdapter = new WeatherAdapter(MainActivity.this,R.layout.weather_holder,weatherDataArrayList);
                listView.setAdapter(weatherAdapter);
            } else {
                Log.d(TAG, "onPostExecute: no json recieved, city is Wrong");
                Toast toast = Toast.makeText(MainActivity.this,"Please provide a valid city!",Toast.LENGTH_LONG);
                toast.show();
            }
        }

    }
}

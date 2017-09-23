package com.bdjobs.training.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Authenticator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView cityTV1, tempTV2, hourlyForcastTV, weatherforecastTV3, dateTV4, humidityTV7, humidityTextTV8, speedTV9, speedTextTV10, percipitationTV11, percipitationTextTV12;
    GridView weatherGV;
    ProgressBar loadingPB;
    ImageView humidityIMV2, speedIMV3, percipitationIMV3;
    View viewLine;
    Button LogoutBTNW;
    public static final String TAG = "MainActivity";
    // RelativeLayout
    //ImageView weatherIMV;
    //  String baseUrl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    String urlY1 = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22";
    String urlY2 = "%2C%20bd%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    public ArrayList<WeatherForecast> WFarraylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findViewById(R.id.loadingPanel).setVisibility(View.GONE);

        GridView gridView = (GridView) findViewById(R.id.gridview);

        GridAdapter weatherGridView = new GridAdapter(this, WFarraylist);
        gridView.setAdapter(weatherGridView);
        String url = urlY1 + "Dhaka" + urlY2;
        //---------------------------------------

        loadingPB = (ProgressBar) findViewById(R.id.progressBar);
        cityTV1 = (TextView) findViewById(R.id.cityTV1);
        tempTV2 = (TextView) findViewById(R.id.tempTV2);
        weatherforecastTV3 = (TextView) findViewById(R.id.weatherforecastTV3);
        dateTV4 = (TextView) findViewById(R.id.dateTV4);
        humidityTV7 = (TextView) findViewById(R.id.humidityTV7);
        humidityTextTV8 = (TextView) findViewById(R.id.humidityTextTV8);
        speedTV9 = (TextView) findViewById(R.id.speedTV9);
        speedTextTV10 = (TextView) findViewById(R.id.speedTextTV10);
        percipitationTV11 = (TextView) findViewById(R.id.percipitationTV11);
        percipitationTextTV12 = (TextView) findViewById(R.id.percipitationTextTV12);
        weatherGV = (GridView) findViewById(R.id.gridview);

        humidityTextTV8 = (TextView) findViewById(R.id.humidityTextTV8);
        humidityIMV2 = (ImageView) findViewById(R.id.humidityIMV2);

        speedIMV3 = (ImageView) findViewById(R.id.speedIMV3);
        speedTextTV10 = (TextView) findViewById(R.id.speedTextTV10);

        percipitationIMV3 = (ImageView) findViewById(R.id.percipitationIMV3);
        percipitationTextTV12 = (TextView) findViewById(R.id.percipitationTextTV12);
        viewLine = (View) findViewById(R.id.view);
        hourlyForcastTV = (TextView) findViewById(R.id.hourlyForcastTV);
        LogoutBTNW = (Button) findViewById(R.id.LogoutBTNW);
        onClickListeners();
        //showProgressDialog();
        getData(url);
        // hideProgressDialog();
//----------------------

    }
    private void onClickListeners() {


        LogoutBTNW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent =  new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.d(TAG,"back pressed");
        finishAffinity();
       // System.exit(0);
    }

    public void getData(String url) {
        showProgressDialog();
        JsonObjectRequest yahooWeatherRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject queryObject = response.getJSONObject("query");
                            JSONObject resultsObject = queryObject.getJSONObject("results");
                            JSONObject channelObject = resultsObject.getJSONObject("channel");
                            JSONObject unitsObject = channelObject.getJSONObject("units");
                            JSONObject locationObject = channelObject.getJSONObject("location");
                            JSONObject itemObject = channelObject.getJSONObject("item");
                            JSONObject conditionObject = itemObject.getJSONObject("condition");
                            JSONObject atmosphereObject = channelObject.getJSONObject("atmosphere");
                            JSONObject windObject = channelObject.getJSONObject("wind");
                            JSONObject astronomyObject = channelObject.getJSONObject("astronomy");
//-----------------------------------------------------------------------------------------------------------------------------
                            String temperature = conditionObject.getString("temp");
                            String temperatureText = conditionObject.getString("text");
                            String date = conditionObject.getString("date");
                            String[] parts = date.split("2017");
                            String part1 = parts[0];
                            String part2 = parts[1];


                            String windSpeed = windObject.getString("speed");
                            String humidity = atmosphereObject.getString("humidity");
                            String pressure = atmosphereObject.getString("pressure");
                            String sunrise = astronomyObject.getString("sunrise");
                            String sunset = astronomyObject.getString("sunset");
                            float tempcelsius = Float.parseFloat(temperature);
                            tempcelsius = ((tempcelsius - 32) * 5) / 9;
                            int intvalue = (int) Math.round(tempcelsius);
                            String convertedTempCelsius = Integer.toString(intvalue);

                            String cityName = locationObject.getString("city");
                            String temperatureFarenhinte = unitsObject.getString("temperature");
                            String speedMPH = unitsObject.getString("speed");
                            //  mTextView.setText(cityName);

//--------------------------------------------------------------------------------------------
                            JSONArray forecastObject = itemObject.getJSONArray("forecast");
//                            JSONArray resultArray = forecastObject.getJSONArray("forecast");
                            for (int i = 0; i < forecastObject.length(); i++) {
                                JSONObject json = forecastObject.getJSONObject(i);
                                String forecasttextTV = json.getString("text");
                                String forecastdateTV = json.getString("date");
                                if (forecasttextTV.equals("Thunderstorms")) {
                                    WeatherForecast weatherForecast = new WeatherForecast(forecasttextTV, forecastdateTV, R.drawable.ic_storm);
                                    WFarraylist.add(weatherForecast);
                                } else {
                                    WeatherForecast weatherForecast = new WeatherForecast(forecasttextTV, forecastdateTV, R.drawable.ic_walking_with_a_storm);
                                    WFarraylist.add(weatherForecast);
                                }

                                // Toast.makeText(MainActivity.this, forecasttextTV, Toast.LENGTH_SHORT).show();
                            }
//-------------------------------------------------------------------------------------------------------------------
                            //  Toast.makeText(MainActivity.this, cityName, Toast.LENGTH_SHORT).show();
                            cityTV1.setText(cityName);
                            tempTV2.setText(convertedTempCelsius + "\u2103");

                            weatherforecastTV3.setText(temperatureText);

                            dateTV4.setSingleLine(false);
                            //nline.setText("first line\n"+"second line\n"+"third line");
                            dateTV4.setText("    " + part1 + "\n" + part2);
                            speedTV9.setText(windSpeed + speedMPH);
                            humidityTV7.setText(humidity + "%");
                            percipitationTV11.setText(pressure);
                            humidityIMV2.setVisibility(View.VISIBLE);
                            humidityTextTV8.setVisibility(View.VISIBLE);

                            speedIMV3.setVisibility(View.VISIBLE);
                            speedTextTV10.setVisibility(View.VISIBLE);

                            viewLine.setVisibility(View.VISIBLE);
                            percipitationIMV3.setVisibility(View.VISIBLE);
                            percipitationTextTV12.setVisibility(View.VISIBLE);
                            hourlyForcastTV.setVisibility(View.VISIBLE);
                            GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), WFarraylist);
                            weatherGV.setAdapter(gridAdapter);
                            hideProgressDialog();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        // findViewById(R.id.loadingPanel).setVisibility(View.GONE);

                        Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_LONG).show();

                    }
                });

        //AppController.getInstance().addToRequestQueue(yahooWeatherRequest);
        MySingleton.getInstance(this).addToRequestQueue(yahooWeatherRequest);

    }

    private void showProgressDialog() {
        if (!loadingPB.isShown())

            loadingPB.setVisibility(View.VISIBLE);
    }

    private void hideProgressDialog() {
        if (loadingPB.isShown())

            loadingPB.setVisibility(View.GONE);
    }


}

package com.bdjobs.training.weatherapp;

import android.widget.ImageView;

/**
 * Created by FIROZ HASAN on 8/17/2017.
 */

public class WeatherForecast {
    String weatherText, weatherDate;
    int weatherIMV;



    public WeatherForecast(String weatherText, String weatherDate, int weatherIMV) {
        this.weatherText = weatherText;
        this.weatherDate = weatherDate;
        this.weatherIMV = weatherIMV;
    }


    public WeatherForecast(String weatherText, String weatherDate) {
        this.weatherText = weatherText;
        this.weatherDate = weatherDate;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public String getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(String weatherDate) {
        this.weatherDate = weatherDate;
    }
    public int getWeatherIMV() {
        return weatherIMV;
    }

    public void setWeatherIMV(int weatherIMV) {
        this.weatherIMV = weatherIMV;
    }

}

package com.bdjobs.training.weatherapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FIROZ HASAN on 8/17/2017.
 */

public class GridAdapter extends ArrayAdapter {
    ArrayList<WeatherForecast> gridforecasts = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;

    public GridAdapter(@NonNull Context context, ArrayList<WeatherForecast> gridforecasts) {
        super(context, R.layout.weather_list_view_item, gridforecasts);
        this.context = context;
        this.gridforecasts = gridforecasts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.weather_list_view_item, parent, false);

            viewHolder.weatherForecastText = (TextView) convertView.findViewById(R.id.forecasttextTV);
            viewHolder.weatherForecastDate = (TextView) convertView.findViewById(R.id.forecastdateTV);
            viewHolder.weatherIMVViewHolder = (ImageView) convertView.findViewById(R.id.dailyforecastIMV);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.weatherForecastText.setText(gridforecasts.get(position).weatherText);
        viewHolder.weatherForecastDate.setText(gridforecasts.get(position).weatherDate);
        viewHolder.weatherIMVViewHolder.setImageResource(gridforecasts.get(position).weatherIMV);

        return convertView;
    }

    public static class ViewHolder {
        TextView weatherForecastDate, weatherForecastText;
        ImageView weatherIMVViewHolder;
    }
}

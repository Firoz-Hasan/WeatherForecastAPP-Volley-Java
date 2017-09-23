//package com.bdjobs.training.weatherapp;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.text.Spannable;
//import android.text.SpannableStringBuilder;
//import android.text.style.ImageSpan;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
///**
// * Created by firozhasan on 8/13/17.
// */
//
//public class WeatherAdapter extends BaseAdapter {
//
//    private final Context mContext;
//    private final WeatherType[] weatherTypes;
//
//    public WeatherAdapter(Context mContext, WeatherType[] weatherTypes) {
//        this.mContext = mContext;
//        this.weatherTypes = weatherTypes;
//    }
//
//    @Override
//    public int getCount() {
//        return weatherTypes.length;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return 0;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        TextView dummyTextView = new TextView(mContext);
//
//        //dummyTextView.setText("Rain");
////        dummyTextView.setSingleLine(false);
////        dummyTextView.setText("Rain\n"+"Icon \n"+"5pm");
////        dummyTextView.setTextColor(Color.parseColor("#FFFFFF"));
////        return dummyTextView;
//
//        SpannableStringBuilder ssb = new SpannableStringBuilder("Rain\n"+"i\n"+"5pm");
//
//        ssb.setSpan(new ImageSpan(mContext, R.drawable.cloud_icon_1), 5, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//
//        dummyTextView.setText(ssb, TextView.BufferType.SPANNABLE);
//        dummyTextView.setTextColor(Color.parseColor("#FFFFFF"));
//
//
//
//
//        return dummyTextView;
//
//    }
//
//
//}

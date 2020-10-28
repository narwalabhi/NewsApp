package com.narwalabhi.assignment.Utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static class MyDate{
        public String date;
        public String time;
    }

    private static final String TAG = DateUtils.class.getSimpleName();

    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }

    public static MyDate getDate(String publishedAt) {
        String newDate;
        Date date = null;
        MyDate myDate = new MyDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm", new Locale(getCountry()));
        try {
            if(publishedAt.length() <= 20)
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(publishedAt);
            else
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'").parse(publishedAt);
            newDate = dateFormat.format(date);
            myDate.date = newDate;
            myDate.time = timeFormat.format(date);
            Log.d(TAG, "getDate: " + myDate.time + " " + newDate);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "getDate: " + e.getMessage());
        }
        return myDate;

    }

}

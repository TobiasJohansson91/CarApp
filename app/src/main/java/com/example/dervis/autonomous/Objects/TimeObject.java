package com.example.dervis.autonomous.Objects;

import com.example.dervis.autonomous.Helpers.ResourceGetter;
import com.example.dervis.autonomous.R;

import java.util.Arrays;
import java.util.List;

public class TimeObject {
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SUNDAY = 7;

    public static final List<String> dayList = Arrays.asList(ResourceGetter.getString(R.string.monday), ResourceGetter.getString(R.string.tuesday), ResourceGetter.getString(R.string.wednesday), ResourceGetter.getString(R.string.thursday), ResourceGetter.getString(R.string.friday), ResourceGetter.getString(R.string.saturday), ResourceGetter.getString(R.string.sunday));

    private int weekDay;
    private int hour;
    private int minute;

    public TimeObject(int weekDay, int hour, int minute) {
        this.weekDay = weekDay;
        this.hour = hour;
        this.minute = minute;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String timeToString(){
        return "" + hour + ":" + minute;
    }

    public String weekDayToString(){
        return dayList.get(weekDay-1);
    }

}

package com.example.dervis.autonomous.Objects;

public class ListObjTimer {

    private int position = -1;
    private int degrees;
    private boolean alarmOn;
    private TimeObject alarmTime;

    public ListObjTimer(){
        alarmOn = true;
        degrees = -275;
    }

    public ListObjTimer(int day, int hour, int minute, boolean alarmOn, int degrees){
        setAlarmTime(day, hour, minute);
        this.alarmOn = alarmOn;
        this.degrees = degrees;
    }

    public TimeObject getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(int day, int hour, int minute) {
        TimeObject alarmTime = new TimeObject(day, hour, minute);
        this.alarmTime = alarmTime;
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }

    public void setAlarmOn(boolean alarmOn) {
        this.alarmOn = alarmOn;
    }

    @Override
    public String toString() {
        return "" + degrees + "°C " + alarmTime.weekDayToString() + " at " + alarmTime.timeToString();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

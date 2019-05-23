package com.example.dervis.autonomous.Objects;

public class WheelSpeedObj {
    public final int wheelSpeed1;
    public final int wheelSpeed2;
    public final int wheelSpeed3;
    public final int wheelSpeed4;
    public final int totalSpeed;

    public WheelSpeedObj(int wheelSpeed1, int wheelSpeed2, int wheelSpeed3, int wheelSpeed4) {
        this.wheelSpeed1 = wheelSpeed1;
        this.wheelSpeed2 = wheelSpeed2;
        this.wheelSpeed3 = wheelSpeed3;
        this.wheelSpeed4 = wheelSpeed4;
        this.totalSpeed = (wheelSpeed1 + wheelSpeed2 + wheelSpeed3 + wheelSpeed4)/4;;
    }
}

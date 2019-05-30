package com.example.dervis.autonomous.Constants;

import android.util.Log;

public class Commands {
    public static final byte[] ARM_MOTORS = new byte[]{(byte) Filters.CMD_SET_PARAMS, (byte) Filters.ARM_MOTORS};
    public static final byte[] DISARM_MOTORS = new byte[]{(byte) Filters.CMD_SET_PARAMS, (byte) Filters.DISARM_MOTORS};
    public static final byte[] HAZARD_LIGHT = new byte[]{(byte) Filters.CMD_TO_PI, (byte) Filters.SET_TURNLIGHT, (byte) Filters.HAZARDLIGHT};
    public static final byte[] LEFT_TURN_LIGHT = new byte[]{(byte) Filters.CMD_TO_PI, (byte) Filters.SET_TURNLIGHT, (byte) Filters.LEFT_TURNLIGHT};
    public static final byte[] RIGHT_TURN_LIGHT = new byte[]{(byte) Filters.CMD_TO_PI, (byte) Filters.SET_TURNLIGHT, (byte) Filters.RIGHT_TURNLIGHT};
    public static final byte[] HONK_HORN = new byte[]{(byte) Filters.CMD_TO_PI, (byte) Filters.HONKHORN};

    public static byte[] speedCommand(short speed){
        if (speed < 0)
            return new byte[]{(byte) Filters.CMD_SPEED, (byte) Filters.CAR_SPD, (byte) 0xff, (byte) speed};
        else
            return new byte[] {(byte) Filters.CMD_SPEED, (byte) Filters.CAR_SPD, (byte) 0, (byte) speed};
    }

    public static byte[] turnSpeedCommand(short turnSpeed){
        if (turnSpeed < 0)
            return new byte[] {(byte) Filters.CMD_SPEED, (byte) Filters.TURN_SPD, (byte) 0xff, (byte) turnSpeed};
        else
            return new byte[] {(byte) Filters.CMD_SPEED, (byte) Filters.TURN_SPD, (byte) 0, (byte) turnSpeed};

    }
}

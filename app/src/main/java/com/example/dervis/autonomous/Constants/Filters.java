package com.example.dervis.autonomous.Constants;

public class Filters {

    //Commands
    public static final int R_OK = 0xA0;
    public static final int CMD_STATUS = 0x01;  // Status commands / requests
    public static final int HEARTBEAT = 0x01;
    public static final int HANDSHAKE = 0x02;
    public static final int ASK_STATUS = 0x03;

    public static final int CMD_SET_PARAMS = 0x02;
    public static final int SET_MOT_THR = 0x01;
    public static final int ARM_MOTORS = 0x03;
    public static final int DISARM_MOTORS = 0x04;

    public static final int CMD_SPEED = 0x10; //Simple speed commands
    public static final int WHEEL_SPD = 0x01;  //int8 right, int8 left
    public static final int CAR_SPD = 0x02;  //int16 speed
    public static final int TURN_SPD = 0x03;  //int16 deg/s

    public static final int CMD_SPEED_CL = 0x11;  //Closed loop control
    public static final int DIST_CL = 0x01;  //int16 cm
    public static final int TURN_CL = 0x02;  //int16 deg
    public static final int TURN_ABS_CL = 0x03;  //int16 heading

    public static final int SENS = 0x30;
    public static final int SENS_LIDAR = 0x01;  //int8 quality, int16 angle, int16 distance
    public static final int SENS_COMPASS = 0x04;  //int16 heading
    public static final int SENS_IMAGE = 0x06;
    public static final int SENS_SPEED = 0x05;  //int16 speed, int16 turn
    public static final int SENS_P_BATT = 0x09; //int16 mVolt, int16 mA
    public static final int SENS_SONAR = 0x0A; //int16 mm, int8 id

    public static final byte[] IMAGE_FILTER = new byte[]{(byte) SENS, (byte) SENS_IMAGE};
    public static final byte[] LIDAR_FILTER = new byte[]{(byte) SENS, (byte) SENS_LIDAR};
    public static final byte[] SONAR_FILTER = new byte[]{(byte) SENS, (byte) SENS_SONAR};
    public static final byte[] SPEED_FILTER = new byte[]{(byte) SENS, (byte) SENS_SPEED};
    public static final byte[] COMPASS_FILTER = new byte[]{(byte) SENS, (byte) SENS_COMPASS};
    public static final byte[] BATTERY_FILTER = new byte[]{(byte) SENS, (byte) SENS_P_BATT};

    public static final byte[] OK_MESSAGE = new byte[]{(byte) 0, (byte) R_OK};

    public static final int FLAG_MAIN = 112;
    public static final int FLAG_VIDEO = 113;
}

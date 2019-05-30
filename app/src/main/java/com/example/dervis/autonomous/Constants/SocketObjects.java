package com.example.dervis.autonomous.Constants;

import com.example.dervis.autonomous.Objects.SocketObj;

import java.util.Arrays;
import java.util.List;

public class SocketObjects {
    public static final SocketObj SPEED_SOCKETOBJ = new SocketObj(Sockets.SPEED_SOCKET_STRING, Filters.SPEED_FILTER);
    public static final SocketObj BATTERY_SOCKETOBJ = new SocketObj(Sockets.BATTERY_SOCKET_STRING, Filters.BATTERY_FILTER);
    public static final SocketObj IMAGE_SOCKETOBJ = new SocketObj(Sockets.IMAGE_SOCKET_STRING, Filters.IMAGE_FILTER);
    public static final SocketObj LIDAR_SOCKETOBJ = new SocketObj(Sockets.LIDAR_SOCKET_STRING, Filters.LIDAR_FILTER);
    public static final SocketObj SONAR_SOCKETOBJ = new SocketObj(Sockets.SONAR_SOCKET_STRING, Filters.SONAR_FILTER);
    public static final SocketObj COMPASS_SOCKETOBJ = new SocketObj(Sockets.COMPASS_SOCKET_STRING, Filters.COMPASS_FILTER);

    public static final List<SocketObj> VIDEO_SOCKETOBJ_LIST = Arrays.asList(IMAGE_SOCKETOBJ, SPEED_SOCKETOBJ);
    public static final List<SocketObj> ENGINEERING_DIAGNOSTIC_SOCKETOBJ_LIST = Arrays.asList(BATTERY_SOCKETOBJ, IMAGE_SOCKETOBJ, LIDAR_SOCKETOBJ, SONAR_SOCKETOBJ, COMPASS_SOCKETOBJ);
    public static final List<SocketObj> MAIN_SOCKETOBJ_LIST = Arrays.asList(BATTERY_SOCKETOBJ);
}

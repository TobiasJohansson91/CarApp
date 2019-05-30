package com.example.dervis.autonomous.Constants;

import java.util.HashMap;

public class ConnectedStatus {

    public static final HashMap<String, Boolean> connectedSockets = new HashMap<String, Boolean>(){{
        put(Sockets.SPEED_SOCKET_STRING, false);
        put(Sockets.IMAGE_SOCKET_STRING, false);
        put(Sockets.COMPASS_SOCKET_STRING, false);
        put(Sockets.LIDAR_SOCKET_STRING, false);
        put(Sockets.SONAR_SOCKET_STRING, false);
        put(Sockets.BATTERY_SOCKET_STRING, false);
    }};
}

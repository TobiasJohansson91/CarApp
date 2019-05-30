package com.example.dervis.autonomous.Objects;

public class ConnectedSocketObj {

    private final String socketName;
    private boolean connected;

    public ConnectedSocketObj(String socketName, boolean connected) {
        this.socketName = socketName;
        this.connected = connected;
    }

    public String getSocketName() {
        return socketName;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}

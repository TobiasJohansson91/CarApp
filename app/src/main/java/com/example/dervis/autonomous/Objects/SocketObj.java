package com.example.dervis.autonomous.Objects;

public class SocketObj {
    public final String socketName;
    public final byte[] filterBytes;


    public SocketObj(String socketName, byte[] filterBytes) {
        this.socketName = socketName;
        this.filterBytes = filterBytes;
    }
}

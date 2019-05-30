package com.example.dervis.autonomous.Objects;

import android.graphics.drawable.Drawable;

public class ListObjDiagnostics extends ListObjIcon{
    public final String socketName;
    private String value = "";
    private boolean connected = false;

    public ListObjDiagnostics(String title, Drawable icon, int iconId, String socketName) {
        super(0, title, "", icon, iconId);
        this.socketName = socketName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    //socketname, icon, title, value, connected
}

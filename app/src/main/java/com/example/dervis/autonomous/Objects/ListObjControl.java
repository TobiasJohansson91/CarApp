package com.example.dervis.autonomous.Objects;

import android.graphics.drawable.Drawable;

public class ListObjControl extends ListObjIcon {

    private String descriptionOn;
    private String descriptionOff;
    private boolean switchOn = false;

    public ListObjControl(int id, String title, String descriptionOn, String descriptionOff, Drawable icon, int iconId) {
        super(id, title, descriptionOn, icon, iconId);
        this.descriptionOn = descriptionOn;
        this.descriptionOff = descriptionOff;
    }

    public boolean isSwitchOn() {
        return switchOn;
    }

    public void setSwitchOn(boolean switchOn) {
        this.switchOn = switchOn;
    }

    public String getDescription() {
        return switchOn ? descriptionOn : descriptionOff;
    }
}
